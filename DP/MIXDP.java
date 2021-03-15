public class MIXDP {
    public static void main(String[] args) {
        solve();
    }

    public static void solve() {
        // boolean ans = JumpGame1();
        // int ans = JumpGame2();
        // boolean ans = JumpGame3();
        // int ans = twoKeyKeyBoard();
        // int ans = binomialCoefficientRec();
        // int ans = binomialCoefficientDp();// O(n*k)+O(n*k)
        // int ans = highwayBillBoard();
        // int ans = eggDrop();
        int ans = eggDropDP();
        System.out.println(ans);
    }

    public static boolean JumpGame1() {
        int[] nums = { 2, 3, 1, 1, 4 };
        int i = nums.length - 2;
        while (i >= 0) {
            if (nums[i] == 0) {
                int j = i - 1;
                while (j >= 0 && nums[j] <= i - j) {
                    j--;
                }
                if (j < 0)
                    return false;
                i = j;
            } else {
                i--;
            }
        }
        return true;
    }

    public static int JumpGame2() {
        int[] nums = { 2, 3, 1, 1, 4 };
        if (nums.length <= 1)
            return 0;
        int ans = 0;
        int njump = 0, cjump = 0;
        for (int i = 0; i < nums.length; i++) {
            njump = Math.max(njump, i + nums[i]);
            if (i == cjump) {
                ans++;
                cjump = njump;
            }
            if (cjump >= nums.length - 1)
                break;
        }
        return ans;
    }

    public static boolean JumpGame3() {
        int[] arr = { 4, 2, 3, 0, 3, 1, 2 };
        int start = 5;
        if (arr.length == 0 || start < 0 || start >= arr.length)
            return false;
        int[] vis = new int[arr.length];
        return JumpGame3_(arr, start, vis);
    }

    public static boolean JumpGame3_(int[] arr, int st, int[] vis) {
        vis[st] = 1;
        if (arr[st] == 0)
            return true;
        int idx1 = st + arr[st];
        int idx2 = st - arr[st];
        boolean res = false;
        if (idx1 < arr.length && vis[idx1] == 0) {
            vis[idx1] = 1;
            res = res || JumpGame3_(arr, idx1, vis);
            vis[idx1] = 0;
        }
        if (idx2 >= 0 && vis[idx2] == 0) {
            vis[idx2] = 1;
            res = res || JumpGame3_(arr, idx2, vis);
            vis[idx2] = 0;
        }
        return res;
    }

    public static int twoKeyKeyBoard() {
        int n = 58;
        int ans = 0;
        int c = n;
        int f = 0;
        for (int i = 2; i * i <= n; i++) {
            f = 0;
            while (c > 1 && c % i == 0) {
                f++;
                c /= i;
            }
            ans += (f * i);
            if (c == 1)
                break;
        }
        if (c != 1) {
            ans += c;
        }
        return ans;
    }

    public static int binomialCoefficientRec() {
        int n = 6;
        int k = 3;
        int[][] dp = new int[n + 1][k + 1];
        return binomialCoefficientRec_(n, k, dp);
    }

    public static int binomialCoefficientRec_(int n, int k, int[][] dp) {
        if (n == k || k == 0)
            return 1;
        if (dp[n][k] != 0)
            return dp[n][k];
        int ans = binomialCoefficientRec_(n - 1, k, dp) + binomialCoefficientRec_(n - 1, k - 1, dp);
        dp[n][k] = ans;
        return ans;
    }

    public static int binomialCoefficientDp() {
        int n = 6;
        int k = 3;
        int[][] dp = new int[k + 1][n + 1];
        return binomialCoefficientDp_(n, k, dp);
    }

    public static int binomialCoefficientDp_(int n, int k, int[][] dp) {
        for (int i = 0; i <= k; i++) {
            for (int j = i; j <= n; j++) {
                if (i == j || i == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1];
                }
            }
        }
        return dp[k][n];
    }

    public static int highwayBillBoard() {
        int highway = 15;
        int[] board = { 6, 9, 12, 14 };
        int[] rev = { 5, 6, 3, 7 };
        int limit = 2;
        int ans = 0;
        int[] dp = new int[highway + 1];
        int j = 0;
        for (int i = board[0]; i < dp.length; i++) {
            if (j >= board.length)
                break;
            if (i == board[j]) {
                int lbpidx = i - limit - 1;
                if (lbpidx < 0) {
                    dp[i] = rev[j];
                } else {
                    dp[i] = rev[j] + dp[lbpidx];
                }
                ans = Math.max(dp[i], ans);
                j++;
            } else {
                dp[i] = ans;
            }
        }
        return ans;
    }

    public static int eggDrop() {
        int floor = 100;
        int eggs = 2;
        int[][] dp = new int[floor + 1][eggs + 1];
        return eggDrop_(floor, eggs, dp);
    }

    public static int eggDrop_(int floor, int eggs, int[][] dp) {
        if (eggs == 1)
            return floor;
        if (floor == 0 || floor == 1)
            return floor;
        if (dp[floor][eggs] != 0)
            return dp[floor][eggs];
        int ans = Integer.MAX_VALUE;
        for (int drop = 1; drop <= floor; drop++) {
            ans = Math.min(ans, Math.max(eggDrop_(drop - 1, eggs - 1, dp), eggDrop_(floor - drop, eggs, dp)));
        }
        dp[floor][eggs] = ans + 1;
        return ans + 1;
    }

    public static int eggDropDP() {
        int floor = 100;
        int eggs = 2;
        int[][] dp = new int[eggs + 1][floor + 1];
        return eggDropDP_(floor, eggs, dp);
    }

    public static int eggDropDP_(int floor, int eggs, int[][] dp) {
        for (int i = 1; i <= eggs; i++) {
            for (int j = 1; j <= floor; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                if (i == 1 || (i > 1 && j == 1)) {
                    dp[i][j] = j;
                } else {
                    for (int drop = 1; drop <= j; drop++) {
                        dp[i][j] = Math.min(dp[i][j], Math.max(dp[i - 1][drop - 1], dp[i][j - drop]));
                    }
                    dp[i][j]++;
                }
            }
        }
        return dp[eggs][floor];
    }
}