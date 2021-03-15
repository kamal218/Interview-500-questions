
public class stringtype {
    public static void main(String[] args) {
        solve();
    }

    public static void solve() {
        // boolean[][] ans = allPalindromicSubstring();
        // int ans = allPalindromicSubstringNoSpace();
        // int ans = longestPalindromicSubstring();
        // int ans = longestPalindromicSubstringNoSpace();
        // int ans = longestNonPalindromicSubstring();
        // int ans = longestPalindromicSubstringWithoutFindindPalindromeRec();
        int ans = longestCommonSubstringRec();
        System.out.println(ans);
    }

    public static boolean[][] allPalindromicSubstring() {
        String str = "aabbc";
        boolean[][] dp = new boolean[str.length()][str.length()];
        int ans = 0;
        String longString = "";
        for (int gap = 0; gap < str.length(); gap++) {
            int si = 0, ei = gap;
            while (ei < str.length()) {
                if (gap == 0) {
                    dp[si][ei] = true;
                } else if (gap == 1) {
                    if (str.charAt(si) == str.charAt(ei)) {
                        dp[si][ei] = true;
                    }
                } else {
                    if (str.charAt(si) == str.charAt(ei) & dp[si + 1][ei - 1]) {
                        dp[si][ei] = true;
                    }
                }
                if (dp[si][ei]) {
                    if (longString.length() < ei - si + 1) {
                        longString = str.substring(si, ei + 1);
                    }
                    // System.out.println(str.substring(si, ei + 1));
                    ans++;
                }
                si++;
                ei++;
            }
        }
        // System.out.println("Longest pallindromic substring:" + longString);
        return dp;
    }

    public static int allPalindromicSubstringNoSpace() {
        String str = "aabbc";
        int ans = 0;
        int len = str.length();
        for (int i = 0; i < 2 * len - 1; i++) {
            if (i % 2 == 0) {// odd length palin
                System.out.println(str.charAt(i / 2));
                ans++;
                int l = (i / 2) - 1;
                int r = (i / 2) + 1;
                while (l >= 0 && r <= len - 1 && str.charAt(l) == str.charAt(r)) {
                    System.out.println(str.substring(l, r + 1));
                    ans++;
                    l--;
                    r++;
                }
            } else {// even length palin
                int l = (i - 1) / 2;
                int r = (i + 1) / 2;
                while (l >= 0 && r <= len - 1 && str.charAt(l) == str.charAt(r)) {
                    System.out.println(str.substring(l, r + 1));
                    ans++;
                    l--;
                    r++;
                }
            }
        }
        return ans;
    }

    public static int longestPalindromicSubstring() {

        String str = "aabbc";
        int len = str.length();
        int[][] dp = new int[len][len];
        boolean[][] allPalin = allPalindromicSubstring();

        for (int gap = 0; gap < str.length(); gap++) {
            int si = 0, ei = gap;
            while (ei < str.length()) {
                if (gap == 0) {
                    dp[si][ei] = 1;
                } else if (gap == 1 && str.charAt(si) == str.charAt(ei)) {
                    dp[si][ei] = 2;
                } else if (str.charAt(si) == str.charAt(ei) && allPalin[si + 1][ei - 1]) {
                    dp[si][ei] = dp[si + 1][ei - 1] + 2;
                } else {
                    dp[si][ei] = Math.max(dp[si + 1][ei], dp[si][ei - 1]);
                }
                si++;
                ei++;
            }
        }
        return dp[0][len - 1];
    }

    public static int longestPalindromicSubstringNoSpace() {
        String str = "aabbc";
        int ans = 0;
        int len = str.length();
        String res = "";
        for (int i = 0; i < 2 * len - 1; i++) {
            int l = 0, r = 0;
            if (i % 2 == 0) {// odd length palin
                ans++;
                l = (i / 2) - 1;
                r = (i / 2) + 1;
                while (l >= 0 && r <= len - 1 && str.charAt(l) == str.charAt(r)) {
                    ans++;
                    l--;
                    r++;
                }
            } else {// even length palin
                l = (i - 1) / 2;
                r = (i + 1) / 2;
                while (l >= 0 && r <= len - 1 && str.charAt(l) == str.charAt(r)) {
                    ans++;
                    l--;
                    r++;
                }
            }
            if (res.length() < r - l - 1) {
                res = str.substring(l + 1, r);
            }
        }
        System.out.println(res);
        return ans;
    }

    public static int longestNonPalindromicSubstring() {
        String str = "aaaaaaaaabaaaaaaaaa";
        if (str.length() <= 1)
            return 0;
        char ch = str.charAt(0);
        boolean same = true;
        // if all characters are same then answer is 0
        // else if already palindrome answer if n-1
        // else answer is n
        int i = 0;
        int j = str.length() - 1;
        while (i <= j) {
            if (str.charAt(i) != str.charAt(j))// complete string is not a palindrome
                return str.length();
            if (same && (str.charAt(i) != ch || str.charAt(j) != ch)) {
                same = false;
            }
            i++;
            j--;
        }
        if (same)// all char were same
            return 0;
        return str.length() - 1;
    }

    public static int longestPalindromicSubstringWithoutFindindPalindromeRec() {
        String str = "aabaa";
        return longestPalindromicSubstringWithoutFindindPalindromeRec_(str, 0, str.length() - 1, 0);
    }

    public static int longestPalindromicSubstringWithoutFindindPalindromeRec_(String str, int si, int ei, int count) {
        if (si == ei)
            return count + 1;
        if (si > ei)
            return count;
        if (str.charAt(si) == str.charAt(ei)) {
            int m = longestPalindromicSubstringWithoutFindindPalindromeRec_(str, si + 1, ei - 1, count + 2);
            int r = longestPalindromicSubstringWithoutFindindPalindromeRec_(str, si + 1, ei, 0);
            int l = longestPalindromicSubstringWithoutFindindPalindromeRec_(str, si, ei - 1, 0);
            return Math.max(m, Math.max(l, r));
        } else {
            int r = longestPalindromicSubstringWithoutFindindPalindromeRec_(str, si + 1, ei, 0);
            int l = longestPalindromicSubstringWithoutFindindPalindromeRec_(str, si, ei - 1, 0);
            return Math.max(l, r);
        }
    }

    public static int longestCommonSubstringRec() {
        String str1 = "ssunday";
        String str2 = "sunday";
        return longestCommonSubstringRec_(str1, str2, 0, 0);
    }

    public static int longestCommonSubstringRec_(String str1, String str2, int p1, int p2) {
        if (p1 == str1.length() || p2 == str2.length()) {
            return 0;
        }
        int ans = 0;
        if (str1.charAt(p1) == str2.charAt(p2)) {
            ans = longestCommonSubstringRec_(str1, str2, p1 + 1, p2 + 1) + 1;
        }
        int rem1 = longestCommonSubstringRec_(str1, str2, p1 + 1, p2);
        int rem2 = longestCommonSubstringRec_(str1, str2, p1, p2 + 1);
        return Math.max(ans, Math.max(rem1, rem2));
    }

}