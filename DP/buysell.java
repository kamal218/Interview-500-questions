import java.util.*;

public class buysell {
    public static void main(String[] args) {
        // int ans = buySellSingleTransaction();
        // int ans = buySellInfiniteTransaction();
        // int ans = buySellTwoTransaction();
        // int ans=buySellKTransaction2DDP();
        // int ans=buySellKTransaction1DDP();
        // int ans=buySellInfiniteTransactionWithFee();
        int ans = buySellInfiniteTransactionWithCoolDown();
        System.out.println(ans);
    }

    public static int buySellSingleTransaction() {
        int[] arr = { 7, 1, 5, 3, 6, 4 };
        int ans = 0;
        String res = "";
        int min_ = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (min_ > arr[i])
                min_ = arr[i];
            if (arr[i] - min_ > ans) {
                ans = arr[i] - min_;
                res = min_ + "->" + arr[i];
            }
        }
        System.out.println(res);
        return ans;
    }

    public static int buySellInfiniteTransaction() {
        int[] arr = { 7, 1, 5, 3, 6, 4 };
        List<List<Integer>> res = new ArrayList<>();
        int bd = 0, sd = 0;
        int ans = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i - 1] && bd == i - 1) {// skip only if buying day has repetiotion from starting
                bd++;
                sd++;
                continue;
            }
            if (arr[i] - arr[i - 1] >= 0) {
                sd++;
            } else {
                if (bd != sd)
                    res.add(Arrays.asList(arr[bd], arr[sd]));
                ans += (arr[sd] - arr[bd]);
                sd = i;
                bd = i;
            }
        }
        if (sd != bd)
            res.add(Arrays.asList(arr[bd], arr[sd]));
        ans += (arr[sd] - arr[bd]);
        System.out.println(res);
        return ans;
    }

    public static int buySellTwoTransaction() {
        int[] arr = { 3, 3, 5, 0, 0, 3, 1, 4 };
        int ans = 0;
        int[] left = new int[arr.length];
        String[] l = new String[arr.length];
        int lsf = arr[0];
        l[0] = arr[0] + " ";
        for (int i = 1; i < arr.length; i++) {
            lsf = Math.min(lsf, arr[i]);
            if (left[i - 1] < arr[i] - lsf) {
                left[i] = arr[i] - lsf;
                l[i] = lsf + " " + arr[i] + " ";
            } else {
                left[i] = left[i - 1];
                l[i] = l[i - 1];
            }
        }
        int[] right = new int[arr.length];
        String[] r = new String[arr.length];
        int msf = arr[arr.length - 1];
        r[arr.length - 1] = arr[arr.length - 1] + " ";
        for (int i = arr.length - 2; i >= 0; i--) {
            msf = Math.max(arr[i], msf);
            if (right[i + 1] > msf - arr[i]) {
                right[i] = right[i + 1];
                r[i] = r[i + 1];
            } else {
                right[i] = msf - arr[i];
                r[i] = arr[i] + " " + msf + " ";
            }
        }
        String res = "";
        for (int i = 0; i < arr.length; i++) {
            if (ans < left[i] + right[i]) {
                ans = left[i] + right[i];
                res = l[i] + r[i];
            }
        }
        System.out.println(res);
        return ans;
    }

    public static int buySellKTransaction2DDP() {
        int[] arr = { 3, 2, 6, 5, 0, 3 };
        int K = 2;
        if (arr.length == 0)
            return 0;
        int[][] dp = new int[K + 1][arr.length];
        for (int i = 1; i <= K; i++) {
            for (int j = 1; j < arr.length; j++) {
                dp[i][j] = dp[i][j - 1];
                for (int k = 0; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][k] + arr[j] - arr[k]);
                }
            }
        }
        return dp[K][arr.length - 1];
    }

    public static int buySellKTransaction1DDP() {
        int[] arr = { 3, 2, 6, 5, 0, 3 };
        int K = 2;
        if (arr.length == 0)
            return 0;
        int[] dp = new int[arr.length];
        int[] temp = new int[arr.length];
        int cans = 0, pans = 0;
        for (int i = 1; i <= K; i++) {
            pans = 0;
            for (int j = 1; j < arr.length; j++) {
                cans = pans;
                for (int k = 0; k < j; k++) {
                    cans = Math.max(cans, dp[k] + arr[j] - arr[k]);
                }
                pans = cans;
                temp[j] = cans;
            }
            dp = temp;
            temp = new int[arr.length];
        }
        return dp[arr.length - 1];
    }

    public static int buySellInfiniteTransactionWithFee() {
        int[] arr = { 1, 3, 2, 8, 4, 9 };
        int cash = 0;
        int fee = 0;
        int hold = -arr[0];
        for (int i = 1; i < arr.length; i++) {
            hold = Math.max(hold, cash - arr[i]);
            cash = Math.max(cash, hold + arr[i] - fee);
        }
        return cash;
    }

    public static int buySellInfiniteTransactionWithCoolDown() {
        int[] arr = { 1, 2, 3, 0, 2 };
        int obsp = -arr[0];
        int ossp = 0;
        int ocsp = 0;
        int nbsp = 0, nssp = 0, ncsp = 0;
        String sell = "";
        String buy = "b";
        String cd = "";
        for (int i = 1; i < arr.length; i++) {
            nssp = 0;
            ncsp = 0;
            nbsp = 0;
            if (obsp >= ocsp - arr[i]) {
                nbsp = obsp;
            } else {
                buy = (cd + "b");
                nbsp = ocsp - arr[i];
            }
            if (ossp >= obsp + arr[i]) {
                nssp = ossp;
            } else {
                sell = (buy + "s");
                nssp = obsp + arr[i];
            }
            if (ocsp >= ossp) {
                ncsp = ocsp;
            } else {
                cd = (sell + "c");
                ncsp = ossp;
            }
            ossp = nssp;
            ocsp = ncsp;
            obsp = nbsp;
        }
        System.out.println(sell);
        return ossp;
    }
}