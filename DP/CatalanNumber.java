import java.util.*;

public class CatalanNumber {
    public static void main(String[] args) {
        catalan();
    }

    public static void catalan() {
        // int ans = catalanRec(8);
        // int ans = catalanDP(9);
        // int ans = catalanOptimized(9);
        Application();
        // System.out.println(ans);
    }

    public static void Application() {
        // countbst/valleys and mountains/bal
        // brackets/non-intersecting chords/dyckword

        
        // int ans = numberOfBSTRec(8);
        // int ans = numberOfBSTCatalan(8);
        // List<TreeNode> ans = createUniqueBST(3);

        // int ans = countValidParenthesesCatalan(3);
        // List<String> ans = createValidParentheses(3);

        // System.out.println(ans);
    }

    public static int catalanRec(int n) {
        if (n <= 1)
            return 1;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += ((catalanRec(i) * catalanRec(n - i - 1)));
        }
        return ans;
    }

    public static int catalanDP(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += (dp[j] * dp[i - j - 1]);
            }
        }
        return dp[n];
    }

    public static int catalanOptimized(int n) {
        int ans = 1;
        for (int i = 1; i <= n; i++) {
            ans *= (4 * i - 2);
            ans /= (i + 1);
        }
        return ans;
    }

    public static int numberOfBSTRec(int n) {
        int[][] dp = new int[n][n];
        return numberOfBSTRec_(0, n - 1, dp);
    }

    public static int numberOfBSTRec_(int si, int ei, int[][] dp) {
        if (si >= ei)
            return 1;
        int ans = 0;
        if (dp[si][ei] != 0)
            return dp[si][ei];
        for (int i = si; i <= ei; i++) {
            ans += (numberOfBSTRec_(si, i - 1, dp) * numberOfBSTRec_(i + 1, ei, dp));
        }
        dp[si][ei] = ans;
        return ans;
    }

    public static int numberOfBSTCatalan(int n) {
        if (n < 2)
            return 1;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            int si = 0, ei = i - 1;
            while (si < i && ei >= 0) {
                dp[i] += dp[si] * dp[ei];
                si++;
                ei--;
            }
        }
        return dp[n];
    }

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }

    }

    public static List<TreeNode> createUniqueBST(int n) {
        return createUniqueBST_(0, n - 1);
    }

    public static List<TreeNode> createUniqueBST_(int si, int ei) {
        if (si > ei) {
            List<TreeNode> base = new ArrayList<>();
            base.add(null);
            return base;
        }
        List<TreeNode> list = new ArrayList();
        for (int i = si; i <= ei; i++) {
            List<TreeNode> left = createUniqueBST_(si, i - 1);
            List<TreeNode> right = createUniqueBST_(i + 1, ei);
            for (TreeNode l : left) {
                for (TreeNode r : right) {
                    TreeNode head = new TreeNode(i + 1);
                    head.left = l;
                    head.right = r;
                    list.add(head);
                }
            }
        }
        return list;
    }

    public static int countValidParenthesesCatalan(int n) {
        if (n < 2)
            return 1;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            int si = 0, ei = i - 1;
            while (si < i && ei >= 0) {
                dp[i] += dp[si] * dp[ei];
                si++;
                ei--;
            }
        }
        return dp[n];
    }

    public static List<String> createValidParentheses(int n) {
        List<String> ans = new ArrayList<>();
        createValidParentheses_(0, 0, n, "", ans);
        return ans;
    }

    public static void createValidParentheses_(int open, int close, int max, String str, List<String> ans) {
        if (open == close && open == max) {
            ans.add(str);
        }
        if (open < max) {
            createValidParentheses_(open + 1, close, max, str + "(", ans);
        }
        if (open > close) {
            createValidParentheses_(open, close + 1, max, str + ")", ans);
        }
    }

}