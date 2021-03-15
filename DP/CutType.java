import java.util.*;

public class CutType {
    public static void main(String[] args) {
        solve();
    }

    public static void solve() {
        // MATRIX CHAIN MULTIPLICATION
        // int ans = MCMRec();
        // int ans = MCMDP();
        // int ans = MCMDPWithString();

        // MINIMUM PALLINDROMIC PARTITIONING
        // List<List<String>> ans=pallindromicCut1();//all pallindromic cuts
        // int ans = pallindromicCut2();// min pallindromic cuts
        // int ans=pallindromicCut1_DP();
        // int ans = pallindromicCut3();

        // BURST BALLOON
        // int ans = burstBalloon();
        // int ans = burstBalloonDP();

        // BOOLEAN PARENTHISIZATION
        // int ans = booleanParenthisization();
        // int ans = booleanParenthisizationDP();

        // MI MAX CVALUE OF EXPRESSION
        // pairMM ans = minMaxExpression();
        pairMM ans = minMaxExpressionDP();

        System.out.println(ans);
    }

    public static int MCMRec() {
        int[] arr = { 10, 30, 5, 60 };
        int[][] dp = new int[arr.length][arr.length];
        int ans = MCMRec_(arr, 0, arr.length - 1, dp);
        return ans;
    }

    public static int MCMRec_(int[] arr, int si, int ei, int[][] dp) {
        if (si + 1 == ei)
            return 0;
        int ans = Integer.MAX_VALUE;
        if (dp[si][ei] != 0)
            return dp[si][ei];
        for (int i = si + 1; i < ei; i++) {
            int left = MCMRec_(arr, si, i, dp);
            int right = MCMRec_(arr, i, ei, dp);
            ans = Math.min(ans, left + right + (arr[si] * arr[i] * arr[ei]));
        }
        dp[si][ei] = ans;
        return ans;
    }

    public static int MCMDP() {
        int[] arr = { 2, 40, 2, 40, 5 };
        int[][] dp = new int[arr.length][arr.length];
        for (int gap = 2; gap < arr.length; gap++) {
            int si = 0;
            int ei = gap;
            while (ei < arr.length) {
                dp[si][ei] = Integer.MAX_VALUE;
                for (int cut = si + 1; cut < ei; cut++) {
                    dp[si][ei] = Math.min(dp[si][ei], dp[si][cut] + dp[cut][ei] + arr[si] * arr[cut] * arr[ei]);
                }
                si++;
                ei++;
            }
        }
        return dp[0][arr.length - 1];
    }

    public static int MCMDPWithString() {
        int[] arr = { 10, 30, 5, 60 };

        int[][] dp = new int[arr.length][arr.length];
        String[][] str = new String[arr.length][arr.length];

        for (int gap = 1; gap < arr.length; gap++) {
            int si = 0;
            int ei = gap;
            if (gap == 1) {
                while (ei < arr.length) {
                    str[si][ei] = (char) ('A' + si) + "";
                    si++;
                    ei++;
                }
                continue;
            }
            while (ei < arr.length) {
                dp[si][ei] = Integer.MAX_VALUE;
                for (int cut = si + 1; cut < ei; cut++) {
                    int left = dp[si][cut];
                    int right = dp[cut][ei];
                    int curr = left + right + arr[si] * arr[cut] * arr[ei];
                    if (curr < dp[si][ei]) {
                        dp[si][ei] = curr;
                        String l = str[si][cut];
                        String r = str[cut][ei];
                        if (left == 0 && right == 0) {
                            str[si][ei] = l + r;
                        } else if (left == 0) {
                            str[si][ei] = l + "(" + r + ")";
                        } else if (right == 0) {
                            str[si][ei] = "(" + l + ")" + r;
                        } else {
                            str[si][ei] = "(" + l + ")" + "(" + r + ")";
                        }
                    }
                }
                si++;
                ei++;
            }
        }
        System.out.println(str[0][arr.length - 1]);
        return dp[0][arr.length - 1];
    }

    public static boolean[][] allPallindrome(String str) {
        int len = str.length();
        boolean[][] dp = new boolean[len][len];
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
                    if (str.charAt(si) == str.charAt(ei) && dp[si + 1][ei - 1] == true) {
                        dp[si][ei] = true;
                    }
                }
                si++;
                ei++;
            }
        }
        return dp;
    }

    // public static int pallindromePartitionRec() {
    // String str = "ababbbabbababa";
    // boolean[][] allPalin = allPallindrome(str);
    // int[][] dp = new int[str.length()][str.length()];
    // return pallindromePartitionRec_(str, 0, str.length() - 1, allPalin, dp);
    // }
    //
    // public static int pallindromePartitionRec_(String str, int si, int ei,
    // boolean[][] allPalin, int[][] dp) {
    // if (si == ei)
    // return 0;
    // if (dp[si][ei] != 0)
    // return dp[si][ei];
    // if (allPalin[si][ei])
    // return 0;
    // int ans = Integer.MAX_VALUE;
    // for (int i = si + 1; i <= ei; i++) {
    // int left = pallindromePartitionRec_(str, si, i - 1, allPalin, dp);
    // int right = pallindromePartitionRec_(str, i, ei, allPalin, dp);
    // ans = Math.min(ans, left + right + 1);
    // }
    // dp[si][ei] = ans;
    // return ans;
    // }

    public static List<List<String>> pallindromicCut1() {
        String str = "aab";
        List<List<String>> ans = new ArrayList<>();
        boolean[][] isPallin = allPallindrome(str);
        pallindromicCut1_(str, 0, new ArrayList<>(), ans, isPallin);
        return ans;
    }

    public static void pallindromicCut1_(String str, int si, List<String> list, List<List<String>> ans,
            boolean[][] isPallin) {
        if (si == str.length()) {
            ans.add(new ArrayList<>(list));
            return;
        }
        for (int i = si; i < str.length(); i++) {
            if (isPallin[si][i]) {
                list.add(str.substring(si, i + 1));
                pallindromicCut1_(str, i + 1, list, ans, isPallin);
                list.remove(list.size() - 1);
            }
        }
    }

    public static int pallindromicCut1_DP(String str, boolean[][] isPallin, int[] dp) {
        for (int i = 1; i < str.length(); i++) {
            if (isPallin[0][i]) {
                dp[i] = 0;
            } else {
                dp[i] = Integer.MAX_VALUE;
                for (int j = i; j >= 1; j--) {
                    if (isPallin[j][i]) {
                        dp[i] = Math.min(dp[i], dp[j - 1] + 1);
                    }
                }
            }
        }
        return dp[str.length() - 1];
    }

    public static int pallindromicCut2() {
        String str = "aab";
        boolean[][] isPallin = allPallindrome(str);
        int[] dp = new int[str.length()];
        return pallindromicCut2_(str, 0, isPallin, dp);
    }

    public static int pallindromicCut2_(String str, int si, boolean[][] isPallin, int[] dp) {

        if (isPallin[si][str.length() - 1])
            return 0;
        if (dp[si] != 0)
            return dp[si];
        int ans = Integer.MAX_VALUE;
        for (int i = si; i < str.length(); i++) {
            if (isPallin[si][i]) {
                ans = Math.min(ans, pallindromicCut2_(str, i + 1, isPallin, dp) + 1);
            }
        }
        dp[si] = ans;
        return ans;
    }

    public static int[][] allClosestPallindrome(String str) {
        int len = str.length();
        int[][] dp = new int[len][len];
        for (int gap = 1; gap < str.length(); gap++) {
            int si = 0, ei = gap;
            while (ei < str.length()) {
                if (gap == 1) {
                    if (str.charAt(si) != str.charAt(ei)) {
                        dp[si][ei] = 1;
                    }
                } else {
                    if (str.charAt(si) == str.charAt(ei)) {
                        dp[si][ei] = dp[si + 1][ei - 1];
                    } else {
                        dp[si][ei] = dp[si + 1][ei - 1] + 1;
                    }
                }
                si++;
                ei++;
            }
        }
        return dp;
    }

    public static int pallindromicCut3() {
        String str = "fyhowoxzyrincxivwarjuwxrwealesxsimsepjdqsstfggjnjhilvrwwytbgsqbpnwjaojfnmiqiqnyzijfmvekgakefjaxryyml";
        int k = 32;
        int[][] pallinDist = allClosestPallindrome(str);
        // for (int i = 0; i < str.length(); i++) {
        // for (int j = 0; j < str.length(); j++) {
        // System.out.print(pallinDist[i][j] + " ");
        // }
        // System.out.println();
        // }
        int[][] dp = new int[str.length()][k];
        return pallindromicCut3_(str, k - 1, 0, pallinDist, dp);
    }

    public static int pallindromicCut3_(String str, int k, int si, int[][] pallinDist, int[][] dp) {
        if (si == str.length())
            return 0;
        if (k == 0) {
            dp[si][k] = pallinDist[si][str.length() - 1];
            return dp[si][k];
        }
        if (dp[si][k] != 0)
            return dp[si][k];
        int ans = Integer.MAX_VALUE;
        for (int i = si; i < str.length() - k; i++) {
            ans = Math.min(ans, pallindromicCut3_(str, k - 1, i + 1, pallinDist, dp) + pallinDist[si][i]);
        }
        dp[si][k] = ans;
        return ans;
    }

    public static int burstBalloon() {
        int[] arr = { 3, 1, 5, 8 };
        int[][] dp = new int[arr.length + 2][arr.length + 2];
        return burstBalloon_(arr, -1, arr.length, dp);
    }

    public static int burstBalloon_(int[] arr, int si, int ei, int[][] dp) {
        if (si + 1 == ei) {
            return 0;
        }
        if (dp[si + 1][ei - 1] != 0)// save actual array answer to get safe from edge cases
            return dp[si + 1][ei - 1];
        int ans = Integer.MIN_VALUE;
        for (int b = si + 1; b < ei; b++) {
            int left = burstBalloon_(arr, si, b, dp);
            int right = burstBalloon_(arr, b, ei, dp);
            int l = si < 0 ? 1 : arr[si];
            int r = ei >= arr.length ? 1 : arr[ei];
            ans = Math.max(ans, (arr[b] * l * r + left + right));
        }
        dp[si + 1][ei - 1] = ans;
        return ans;
    }

    public static int burstBalloonDP() {
        int[] arr = { 3, 1, 5, 8 };
        int[][] dp = new int[arr.length + 2][arr.length + 2];
        for (int gap = 2; gap < dp.length; gap++) {
            int si = 0, ei = gap;
            while (ei < dp.length) {
                for (int cut = si + 1; cut < ei; cut++) {
                    int l = si == 0 ? 1 : arr[si - 1];
                    int r = ei == arr.length + 1 ? 1 : arr[ei - 1];
                    dp[si][ei] = Math.max(dp[si][ei], dp[si][cut] + dp[cut][ei] + l * r * arr[cut - 1]);
                }
                si++;
                ei++;
            }
        }
        // printDP(dp);
        return dp[0][arr.length + 1];
    }

    public static class pair {
        int tc = 0;
        int fc = 0;

        public pair(int tc, int fc) {
            this.tc = tc;
            this.fc = fc;
        }

        public pair() {

        }
    }

    public static int booleanParenthisization() {
        char[] exp = { 'T', 'F', 'F' };
        char[] sym = { '^', '|' };
        pair[][] dp = new pair[exp.length][exp.length];
        pair ans = booleanParenthisization_(exp, sym, 0, exp.length - 1, dp);
        return ans.tc;
    }

    public static pair booleanParenthisization_(char[] exp, char[] sym, int si, int ei, pair[][] dp) {
        if (si == ei) {
            return exp[si] == 'T' ? new pair(1, 0) : new pair(0, 1);
        }
        if (dp[si][ei] != null)
            return dp[si][ei];
        pair ans = new pair();
        for (int p = si; p < ei; p++) {
            pair left = booleanParenthisization_(exp, sym, si, p, dp);
            pair right = booleanParenthisization_(exp, sym, p + 1, ei, dp);
            if (sym[p] == '&') {
                ans.tc = ans.tc + (left.tc * right.tc);
                ans.fc = ans.fc + (left.tc * right.fc) + (left.fc * right.tc) + (left.fc * right.fc);
            } else if (sym[p] == '|') {
                ans.tc = ans.tc + (left.tc * right.fc) + (left.fc * right.tc) + (left.tc * right.tc);
                ans.fc = ans.fc + (left.fc * right.fc);
            } else {
                ans.tc = ans.tc + (left.tc * right.fc) + (left.fc * right.tc);
                ans.fc = ans.fc + (left.fc * right.fc) + (left.tc * right.tc);
            }
        }
        dp[si][ei] = ans;
        return ans;
    }

    public static int booleanParenthisizationDP() {
        char[] exp = { 'T', 'F', 'F' };
        char[] sym = { '^', '|' };
        pair[][] dp = new pair[exp.length][exp.length];
        return booleanParenthisizationDP_(exp, sym, dp).tc;
    }

    public static pair booleanParenthisizationDP_(char[] exp, char[] sym, pair[][] dp) {

        for (int gap = 0; gap < dp.length; gap++) {
            int si = 0, ei = gap;
            while (ei < dp.length) {
                if (gap == 0) {
                    if (exp[si] == 'T') {
                        dp[si][ei] = new pair(1, 0);
                    } else {
                        dp[si][ei] = new pair(0, 1);
                    }
                } else {
                    dp[si][ei] = new pair();
                    for (int p = si; p < ei; p++) {
                        pair left = dp[si][p];
                        pair right = dp[p + 1][ei];
                        if (sym[p] == '&') {
                            dp[si][ei].tc = dp[si][ei].tc + (left.tc * right.tc);
                            dp[si][ei].fc = dp[si][ei].fc + (left.tc * right.fc) + (left.fc * right.tc)
                                    + (left.fc * right.fc);
                        } else if (sym[p] == '|') {
                            dp[si][ei].tc = dp[si][ei].tc + (left.tc * right.fc) + (left.fc * right.tc)
                                    + (left.tc * right.tc);
                            dp[si][ei].fc = dp[si][ei].fc + (left.fc * right.fc);
                        } else {
                            dp[si][ei].tc = dp[si][ei].tc + (left.tc * right.fc) + (left.fc * right.tc);
                            dp[si][ei].fc = dp[si][ei].fc + (left.fc * right.fc) + (left.tc * right.tc);
                        }
                    }
                }
                si++;
                ei++;
            }
        }
        return dp[0][exp.length - 1];
    }

    public static class pairMM {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        public pairMM() {
        }

        public pairMM(int min, int max) {
            this.min = min;
            this.max = max;
        }

        @Override
        public String toString() {
            return "min-> " + min + "\n" + "max-> " + max;
        }
    }

    public static pairMM minMaxExpression() {
        int[] arr = { 1, 2, 3, 4, 5 };
        char[] exp = { '+', '*', '+', '*' };
        return minMaxExpression_(arr, exp, 0, arr.length - 1);
    }

    public static pairMM minMaxExpression_(int[] arr, char[] exp, int si, int ei) {
        if (si == ei) {
            return new pairMM(arr[si], arr[si]);
        }
        pairMM ans = new pairMM();
        for (int p = si; p < ei; p++) {
            pairMM left = minMaxExpression_(arr, exp, si, p);
            pairMM right = minMaxExpression_(arr, exp, p + 1, ei);
            if (exp[p] == '+') {
                ans.min = Math.min(ans.min, left.min + right.min);
                ans.max = Math.max(ans.max, left.max + right.max);
            } else {
                ans.min = Math.min(ans.min, left.min * right.min);
                ans.max = Math.max(ans.max, left.max * right.max);
            }
        }
        return ans;
    }

    public static pairMM minMaxExpressionDP() {
        int[] arr = { 1, 2, 3, 4, 5 };
        char[] exp = { '+', '*', '+', '*' };
        pairMM[][] dp = new pairMM[arr.length][arr.length];
        return minMaxExpressionDP_(arr, exp, dp);
    }

    public static pairMM minMaxExpressionDP_(int[] arr, char[] exp, pairMM[][] dp) {
        for (int gap = 0; gap < dp.length; gap++) {
            int si = 0, ei = gap;
            while (ei < dp.length) {
                if (gap == 0) {
                    dp[si][ei] = new pairMM(arr[si], arr[si]);
                } else {
                    dp[si][ei] = new pairMM();
                    for (int p = si; p < ei; p++) {
                        pairMM left = dp[si][p];
                        pairMM right = dp[p + 1][ei];
                        if (exp[p] == '+') {
                            dp[si][ei].min = Math.min(dp[si][ei].min, left.min + right.min);
                            dp[si][ei].max = Math.max(dp[si][ei].max, left.max + right.max);
                        } else {
                            dp[si][ei].min = Math.min(dp[si][ei].min, left.min * right.min);
                            dp[si][ei].max = Math.max(dp[si][ei].max, left.max * right.max);
                        }
                    }
                }
                si++;
                ei++;
            }
        }
        return dp[0][arr.length - 1];
    }

    public static void printDP(int[][] DP) {
        for (int[] ar : DP) {
            for (int ele : ar) {
                System.out.print(ele + " ");
            }
            System.out.println();
        }
    }
}