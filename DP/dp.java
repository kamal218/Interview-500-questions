import java.util.Scanner;
import java.util.*;

public class dp {
    public static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) {
        solve();
    }

    public static void solve() {
        // SET 1 IP_BATCH
        set1();

        // SET2 PEPCODING YOUTUBE
        // set2();

        // SET3 LEETCODE + MISC
        set3();
    }

    public static void set1() {
        // LIS SET CLASS1
        // int ans = longestIncreasingSubsequence();
        // int ans = longestIncreasingSubsequenceOptimized();
        // int ans = buildingBridges();
        // int ans = maxEnvelopes();
        // int ans = boxStacking();
        // int ans = minNumberOfIncSubseq();
        // System.out.println(ans);
    }

    public static void set2() {
        // bestTimeToBuyAndSell();
        // bestTimeToBuyAndSell2();
        // bestTimeToBuyAndSell2_();
        // bestTimeToBuyAndSell_Fee();
        // bestTimeToBuyAndSell_CoolDown();
        // bestTimeToBuyAndSell_2Transactions();
        // bestTimeToBuyAndSell_2Transactions_opt();
        // LIS();
        // LDS();
        // maxSumIncSubseq();
        // lonIncContSubstring();
        // countPallinSubstring();
        // countPallinSubstring_();
        // countPallindromicSubstring_();
        // longestPallindromicSubstring();
        // longestPallindromicSubstring_();
        // countPallinSubseq();
        // longestPallinSubseq();
        // catalanNumber();
        // catalanNumber_();//countbst/valleys and mountains/bal
        // brackets/non-intersecting chords/dyckword
        // optimalSubBST();
        // optimalSubBSTOptimized();
        // perfectSquareMin();
        // longestCommonSubstring();
        // System.out.println(longestCommonSubstring_memo("sunday", "ssunday", 0, new
        // int[7][7]));
        // longestCommonSubseq();
        // kadanes();
        // kadanes_();
        // kConcate();
        // maxSubArrayOneDeletion();
    }

    public static void set3() {
        int ans = findNumberOfLIS();
        System.out.println(ans);
    }

    public static int longestIncreasingSubsequence() {
        int[] arr = { 10, 9, 2, 5, 3, 7, 101, 18 };
        int[] dp = new int[arr.length];
        dp[0] = 1;
        StringBuilder[] sequence = new StringBuilder[arr.length];
        sequence[0] = new StringBuilder();
        sequence[0].append(arr[0] + " ");
        int ansidx = 0;
        for (int i = 1; i < dp.length; i++) {
            dp[i] = 1;
            sequence[i] = new StringBuilder();
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    sequence[i] = new StringBuilder(sequence[j] + " ");
                }
            }
            sequence[i].append(arr[i]);
            if (dp[i] > dp[ansidx])
                ansidx = i;
        }
        System.out.println(sequence[ansidx].toString() + " ");
        return dp[ansidx];
    }

    public static int longestIncreasingSubsequenceOptimized() {
        int[] arr = { 10, 9, 2, 5, 3, 7, 101, 18 };
        int ans = 1;
        for (int i = 1; i < arr.length; i++) {
            int idx = binarySearchLIS(arr, 0, ans - 1, arr[i]);
            if (idx == -1) {// nothing to do with duplicate numbers
                continue;
            }
            arr[idx] = arr[i];
            if (idx + 1 > ans)
                ans = idx + 1;
            // System.out.println(idx);
        }
        return ans;
    }

    public static int binarySearchLIS(int[] arr, int si, int ei, int tar) {
        while (si <= ei) {
            int mid = (si + ei) / 2;
            if (arr[mid] == tar)
                return -1;
            else if (arr[mid] < tar)
                si = mid + 1;
            else
                ei = mid - 1;
        }
        return si;
    }

    public static int buildingBridges() {
        // Note: we need duplicacy in LIS see line->128
        int[][] arr = { { 1, 8 }, { 2, 1 }, { 3, 4 }, { 4, 3 }, { 6, 5 }, { 5, 5 }, { 6, 2 }, { 7, 6 }, { 8, 7 } };
        // int[][] arr = { { 6, 2 }, { 4, 3 }, { 2, 6 }, { 1, 5 } };
        Arrays.sort(arr, (a, b) -> (a[0] - b[0]));
        int ans = 1;
        for (int i = 1; i < arr.length; i++) {
            // LIS on y coordinate
            int idx = binarySearchBridge(arr, 0, ans - 1, arr[i][1]);
            // if (idx == -1) {//we need duplicacy so keep it commented
            // continue;
            // }
            arr[idx][1] = arr[i][1];
            if (idx + 1 > ans)
                ans = idx + 1;
        }
        return ans;
    }

    public static int binarySearchBridge(int[][] arr, int si, int ei, int tar) {
        while (si <= ei) {
            int mid = (si + ei) / 2;

            if (arr[mid][1] <= tar)// no need for equal basecase
                si = mid + 1;
            else
                ei = mid - 1;
        }
        return si;
    }

    public static int maxEnvelopes() {
        int[][] arr = { { 5, 4 }, { 6, 4 }, { 6, 7 }, { 2, 3 } };
        if (arr.length == 0)
            return 0;
        Arrays.sort(arr, new Comparator<>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[0] != b[0])
                    return a[0] - b[0];
                return b[1] - a[1];
            }
        });
        // for(int[] ar:arr){
        // for(int ele:ar){
        // System.out.print(ele+" ");
        // }
        // System.out.println();
        // }
        int ans = 1;
        for (int i = 1; i < arr.length; i++) {
            // LIS on y coordinate
            int idx = binarySearchEnvelope(arr, 0, ans - 1, arr[i][1]);
            if (idx == -1) {// we need duplicacy so keep it commented
                continue;
            }
            arr[idx][1] = arr[i][1];
            if (idx + 1 > ans)
                ans = idx + 1;
        }
        return ans;
    }

    public static int binarySearchEnvelope(int[][] arr, int si, int ei, int tar) {
        while (si <= ei) {
            int mid = (si + ei) / 2;
            if (arr[mid][1] == tar)
                return -1;
            else if (arr[mid][1] < tar)
                si = mid + 1;
            else
                ei = mid - 1;
        }
        return si;
    }

    public static int boxStacking() {
        int[][] arr = { { 4, 6, 7 }, { 1, 2, 3 }, { 4, 5, 6 }, { 10, 12, 32 } };
        int[][] narr = new int[3 * arr.length][3];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < 3; j++) {
                narr[3 * i + j][0] = arr[i][j];
                narr[3 * i + j][1] = Math.min(arr[i][(j + 1) % 3], arr[i][(j + 2) % 3]);
                narr[3 * i + j][2] = Math.max(arr[i][(j + 1) % 3], arr[i][(j + 2) % 3]);
            }
        }
        int ansidx = 0;
        // sort array on the basis of i->1
        Arrays.sort(narr, new Comparator<>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[1] != b[1])
                    return a[1] - b[1];
                return b[2] - a[2];
            }
        });
        // find lis on the basis of i->2 without duplicacy
        int tempidx = 0;
        for (int i = 1; i < narr.length; i++) {
            tempidx = -1;
            for (int j = 0; j < i; j++) {
                if (narr[j][2] < narr[i][2]) {
                    if (tempidx == -1 || narr[tempidx][0] < narr[j][0]) {
                        tempidx = j;
                    }
                }
            }
            if (tempidx != -1) {
                narr[i][0] += narr[tempidx][0];
            }
            if (narr[ansidx][0] < narr[i][0])
                ansidx = i;
        }
        return narr[ansidx][0];
    }

    public static int minNumberOfIncSubseq() {
        int[] arr = { 10, 9, 2, 5, 3, 7, 101, 18 };
        return LDS(arr);
    }

    public static int LDS(int[] arr) {
        int ans = 1;
        for (int i = 1; i < arr.length; i++) {
            int idx = binarySearchLDS(arr, 0, ans - 1, arr[i]);
            arr[idx] = arr[i];
            if (idx + 1 > ans)
                ans = idx + 1;
        }
        return ans;
    }

    public static int binarySearchLDS(int[] arr, int si, int ei, int tar) {
        while (si <= ei) {
            int mid = (si + ei) / 2;
            if (arr[mid] < tar) {
                ei = mid - 1;
            } else {
                si = mid + 1;
            }
        }
        return si;
    }

    /***************************************************************************/

    public static void bestTimeToBuyAndSell() {
        int[] prices = { 7, 1, 5, 3, 6, 4 };
        int ans = 0;
        int min_ = prices[0];
        for (int cost : prices) {
            if (cost - min_ > ans)
                ans = cost - min_;
            else if (cost < min_)
                min_ = cost;
        }
        System.out.println(ans);
    }

    public static void bestTimeToBuyAndSell2() {
        int[] prices = { 7, 1, 5, 3, 6, 4 };
        int bd = 0, sd = 0;
        int ans = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] >= prices[i - 1]) {
                sd++;
            } else {
                ans += prices[sd] - prices[bd];
                sd = i;
                bd = i;
            }
        }
        ans += prices[sd] - prices[bd];
        System.out.println(ans);
    }

    public static void bestTimeToBuyAndSell2_() {
        int[] prices = { 7, 1, 5, 3, 6, 4 };
        int maxprofit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1])
                maxprofit += prices[i] - prices[i - 1];
        }
        System.out.println(maxprofit);
    }

    public static void bestTimeToBuyAndSell_Fee() {
        int[] prices = { 7, 1, 5, 3, 6, 4 };
        int fee = 2;
        int obsp = -prices[0];
        int ossp = 0;
        int nbsp = 0;
        int nssp = 0;
        for (int i = 1; i < prices.length; i++) {
            nssp = 0;
            nbsp = 0;
            // bought state profit
            if (ossp - prices[i] > obsp) {
                nbsp = ossp - prices[i];
            } else {
                nbsp = obsp;
            }
            if (obsp + prices[i] - fee > ossp) {
                nssp = obsp + prices[i] - fee;
            } else {
                nssp = ossp;
            }
            ossp = nssp;
            obsp = nbsp;
        }
        System.out.println(nssp);
    }

    public static void bestTimeToBuyAndSell_CoolDown() {
        int[] prices = { 7, 1, 5, 3, 6, 4 };
        int obsp = -prices[0];
        int ossp = 0;
        int ocsp = 0;
        int nssp = 0, nbsp = 0, ncsp = 0;
        for (int i = 1; i < prices.length; i++) {
            nssp = 0;
            nbsp = 0;
            ncsp = 0;
            nbsp = Math.max(obsp, ocsp - prices[i]);
            nssp = Math.max(ossp, prices[i] + obsp);
            ncsp = Math.max(ocsp, ossp);
            ossp = nssp;
            ocsp = ncsp;
            obsp = nbsp;
        }
        System.out.println(nssp);
    }

    public static void bestTimeToBuyAndSell_2Transactions() {
        int[] prices = { 3, 3, 5, 0, 0, 3, 1, 4 };

        int lsf = prices[0];
        int ans = 0;
        int[] ltrmax = new int[prices.length];
        ltrmax[0] = 0;
        for (int i = 1; i < prices.length; i++) {
            ltrmax[i] = ltrmax[i - 1];
            if (prices[i] - lsf > ltrmax[i - 1])
                ltrmax[i] = prices[i] - lsf;
            if (prices[i] < lsf)
                lsf = prices[i];
        }
        int msf = prices[prices.length - 1];
        int[] rtlmax = new int[prices.length];
        for (int i = prices.length - 2; i >= 0; i--) {
            rtlmax[i] = rtlmax[i + 1];
            if (msf - prices[i] > rtlmax[i + 1])
                rtlmax[i] = msf - prices[i];
            if (prices[i] > msf)
                msf = prices[i];
        }
        for (int i = 0; i < prices.length; i++) {
            if (ans < ltrmax[i] + rtlmax[i])
                ans = ltrmax[i] + rtlmax[i];
        }
    }

    public static void bestTimeToBuyAndSell_2Transactions_opt() {

        int[] prices = { 3, 3, 5, 0, 0, 3, 1, 4 };
        int lsf = prices[0];
        int ans = 0;
        int[] ltrmax = new int[prices.length];
        ltrmax[0] = 0;
        for (int i = 1; i < prices.length; i++) {
            ltrmax[i] = ltrmax[i - 1];
            if (prices[i] - lsf > ltrmax[i - 1])
                ltrmax[i] = prices[i] - lsf;
            if (prices[i] < lsf)
                lsf = prices[i];
        }
        int msf = prices[prices.length - 1];
        int rmax = 0;
        for (int i = prices.length - 2; i >= 0; i--) {
            if (rmax < msf - prices[i])
                rmax = msf - prices[i];
            if (ans < rmax + ltrmax[i])
                ans = rmax + ltrmax[i];
            if (prices[i] > msf)
                msf = prices[i];
        }
        System.out.println(ans);
    }

    public static int[] LIS() {
        int[] nums = { 10, 9, 2, 5, 3, 7, 101, 18 };
        int ans = 0;
        int[] dp = new int[nums.length];
        dp[nums.length - 1] = 0;
        for (int i = nums.length - 2; i >= 0; i--) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] > nums[i] && dp[i] < dp[j] + 1)
                    dp[i] = dp[j] + 1;
            }
            ans = Math.max(dp[i], ans);
        }
        System.out.println(ans + 1);
        return dp;
    }

    public static int[] LDS() {
        int[] nums = { 10, 9, 2, 5, 3, 7, 101, 18 };
        int ans = 0;
        int[] dp = new int[nums.length];
        dp[nums.length - 1] = 0;
        for (int i = nums.length - 2; i >= 0; i--) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[i] && dp[i] < dp[j] + 1)
                    dp[i] = dp[j] + 1;
            }
            ans = Math.max(dp[i], ans);
        }
        System.out.println(ans + 1);
        return dp;
    }

    public static void maxSumIncSubseq() {
        int[] nums = { 10, 22, 9, 33, 21, 50, 41, 60, 80, 1 };
        int[] dp = new int[nums.length];
        int ans = Integer.MIN_VALUE;
        dp[0] = nums[0];
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i] && nums[i] + dp[j] > dp[i])
                    dp[i] = nums[i] + dp[j];
            }
            ans = Math.max(ans, dp[i]);
        }
        System.out.println(ans);
    }

    public static void lonIncContSubstring() {
        int[] nums = { 1, 3, 5, 4, 7 };
        int ans = 0, j = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (i > 0 && nums[i - 1] >= nums[i])
                j = i;
            ans = Math.max(ans, i - j + 1);
        }
        System.out.println(ans);
    }

    public static boolean[][] countPallinSubstring() {
        String str = "abccbc";
        boolean[][] dp = new boolean[str.length()][str.length()];
        int ans = 0;
        for (int gap = 0; gap < str.length(); gap++) {
            int si = 0, ei = gap;
            while (ei < str.length()) {
                if (gap == 0) {
                    dp[si][ei] = true;
                } else if (gap == 1 && str.charAt(si) == str.charAt(ei)) {
                    dp[si][ei] = true;
                } else if (gap >= 2 && str.charAt(si) == str.charAt(ei) && dp[si + 1][ei - 1]) {
                    dp[si][ei] = true;
                }
                if (dp[si][ei])
                    ans++;
                si++;
                ei++;
            }
        }
        // for (int i = 0; i < dp.length; i++) {
        // for (int j = 0; j < dp[0].length; j++) {
        // System.out.print(dp[i][j] + " ");
        // }
        // System.out.println();
        // }
        System.out.println(ans);
        return dp;
    }

    public static void countPallinSubstring_() {
        String S = "abccbc";
        int N = S.length(), ans = 0;
        for (int center = 0; center <= 2 * N - 1; ++center) {
            int left = center / 2;
            int right = left + center % 2;
            while (left >= 0 && right < N && S.charAt(left) == S.charAt(right)) {
                ans++;
                left--;
                right++;
            }
        }
        System.out.println(ans);
    }

    public static void countPallindromicSubstring_() {
        boolean[][] dp = countPallinSubstring();
        String str = "abccbc";
        int[][] mdp = new int[str.length()][str.length()];
        for (int gap = 0; gap < str.length(); gap++) {
            int si = 0, ei = gap;
            while (ei < str.length()) {
                if (gap == 0) {
                    mdp[si][ei] = 1;
                } else if (gap == 1 && str.charAt(si) == str.charAt(ei)) {
                    mdp[si][ei] += mdp[si + 1][ei] + mdp[si][ei - 1] - mdp[si + 1][ei - 1] + 1;
                } else {
                    mdp[si][ei] += mdp[si + 1][ei] + mdp[si][ei - 1] - mdp[si + 1][ei - 1];
                    if (str.charAt(si) == str.charAt(ei) && dp[si + 1][ei - 1])
                        mdp[si][ei] += 1;
                }
                si++;
                ei++;
            }
        }
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(mdp[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(mdp[0][str.length() - 1]);
    }

    public static void longestPallindromicSubstring() {
        String S = "abccbc";
        int N = S.length(), ans = 0;
        for (int center = 0; center <= 2 * N - 1; ++center) {
            int left = center / 2;
            int right = left + center % 2;
            while (left >= 0 && right < N && S.charAt(left) == S.charAt(right)) {
                left--;
                right++;
            }
            ans = Math.max(ans, right - left - 1);
        }
        System.out.println(ans);
    }

    public static void longestPallindromicSubstring_() {
        boolean[][] dp = countPallinSubstring();
        String str = "abccbc";
        int[][] mdp = new int[str.length()][str.length()];
        for (int gap = 0; gap < str.length(); gap++) {
            int si = 0, ei = gap;
            while (ei < str.length()) {
                if (gap == 0) {
                    mdp[si][ei] = 1;
                } else {
                    if (str.charAt(si) == str.charAt(ei) && dp[si][ei]) {
                        mdp[si][ei] = ei - si + 1;
                    } else {
                        mdp[si][ei] = Math.max(mdp[si + 1][ei], mdp[si][ei - 1]);
                    }
                }
                si++;
                ei++;
            }
        }
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(mdp[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(mdp[0][str.length() - 1]);
    }

    public static void countPallinSubseq() {
        String str = "abccbc";
        int[][] dp = new int[str.length()][str.length()];
        for (int gap = 0; gap < str.length(); gap++) {
            int si = 0, ei = gap;
            while (ei < str.length()) {
                if (gap == 0) {
                    dp[si][ei] = 1;
                } else {
                    dp[si][ei] += dp[si][ei - 1] + dp[si + 1][ei] - dp[si + 1][ei - 1];
                    if (str.charAt(si) == str.charAt(ei)) {
                        dp[si][ei] += dp[si + 1][ei - 1] + 1;
                    }
                }
                si++;
                ei++;
            }
        }
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(dp[0][str.length() - 1]);
    }

    public static void longestPallinSubseq() {
        String str = "aabaaba";
        int[][] dp = new int[str.length()][str.length()];
        for (int gap = 0; gap < str.length(); gap++) {
            int si = 0, ei = gap;
            while (ei < str.length()) {
                if (gap == 0) {
                    dp[si][ei] = 1;
                } else {
                    if (str.charAt(si) == str.charAt(ei)) {
                        dp[si][ei] = dp[si + 1][ei - 1] + 2;
                    } else {
                        dp[si][ei] = Math.max(dp[si][ei - 1], dp[si + 1][ei]);
                    }
                }
                si++;
                ei++;
            }
        }
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(dp[0][str.length() - 1]);
    }

    public static void distinctSubseqII(String S) {
        long[] dp = new long[S.length() + 1];
        dp[0] = 1;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 1; i <= S.length(); i++) {
            char ch = S.charAt(i - 1);
            dp[i] = (dp[i - 1]) * 2;
            if (map.containsKey(ch)) {
                int j = map.get(ch);
                dp[i] -= dp[j - 1];
            }
            map.put(ch, i);
            dp[i] = dp[i] % (1000000007);
        }
        // for(int i=0;i<dp.length;i++)
        // System.out.print(dp[i]+" ");
        if (dp[S.length()] < 0)
            dp[S.length()] += 1000000007;
        System.out.println(dp[S.length()] - 1);
    }

    public static void catalanNumber() {
        int n = scn.nextInt();
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < n; i++) {
            int si = 0, ei = i - 1;
            while (si < i && ei >= 0) {
                dp[i] += dp[si] * dp[ei];
                si++;
                ei--;
            }
        }
        System.out.println(dp[n - 1]);
    }

    public static void catalanNumber_() {
        int n = scn.nextInt();
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        System.out.println(dp[n - 1]);
    }

    public static void optimalSubBST() {
        int[] tree = { 10, 12, 20 };
        int[] find = { 34, 8, 50 };
        int n = tree.length;
        int[][] dp = new int[n][n];
        for (int gap = 0; gap < tree.length; gap++) {
            int si = 0, ei = gap;
            while (ei < dp.length) {
                if (gap == 0) {
                    dp[si][ei] = find[si];
                } else {
                    dp[si][ei] = Integer.MAX_VALUE;
                    int freq = 0;
                    for (int k = si; k <= ei; k++) {
                        freq += find[k];
                    }
                    for (int k = si; k <= ei; k++) {
                        int left = (k - 1) < si ? 0 : dp[si][k - 1];
                        int right = (k + 1) > ei ? 0 : dp[k + 1][ei];
                        dp[si][ei] = Math.min(dp[si][ei], left + right + freq);
                    }
                }
                si++;
                ei++;
            }
        }
        System.out.println(dp[0][n - 1]);
    }

    public static void optimalSubBSTOptimized() {
        int[] tree = { 10, 12, 20 };
        int[] find = { 34, 8, 50 };
        int n = tree.length;
        int[][] dp = new int[n][n];
        int[] freq = new int[n];
        freq[0] = find[0];
        for (int j = 1; j < freq.length; j++) {
            freq[j] += freq[j - 1] + find[j];
        }
        for (int gap = 0; gap < tree.length; gap++) {
            int si = 0, ei = gap;
            while (ei < dp.length) {
                if (gap == 0) {
                    dp[si][ei] = find[si];
                } else {
                    dp[si][ei] = Integer.MAX_VALUE;
                    int f = 0;
                    f = si == 0 ? freq[ei] : freq[ei] - freq[si - 1];
                    for (int k = si; k <= ei; k++) {
                        int left = (k - 1) < si ? 0 : dp[si][k - 1];
                        int right = (k + 1) > ei ? 0 : dp[k + 1][ei];
                        dp[si][ei] = Math.min(dp[si][ei], left + right + f);
                    }
                }
                si++;
                ei++;
            }
        }
        System.out.println(dp[0][n - 1]);
    }

    public static void perfectSquareMin() {
        int n = 1547;
        int[] dp = new int[n + 1];
        String[] ans = new String[n + 1];
        ans[0] = "";
        ans[1] = "1";
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i < dp.length; i++) {
            int min_ = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                if (dp[i - (j * j)] < min_) {
                    min_ = dp[i - (j * j)];
                    ans[i] = j + " " + ans[i - (j * j)];
                }
            }
            dp[i] = min_ + 1;
        }
        System.out.println(dp[n]);
        System.out.println(ans[n]);
    }

    public static void longestCommonSubstring() {
        String str1 = "xyzabcp";
        String str2 = "pqabcxy";
        int[][] dp = new int[str1.length()][str2.length()];
        int ans = 0;
        for (int i = 1; i < dp[0].length; i++) {
            for (int j = 1; j < dp.length; j++) {
                if (str1.charAt(i) == str2.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = 0;
                }
                ans = Math.max(ans, dp[i][j]);
            }
        }
        System.out.println(ans);
    }

    public static int longestCommonSubstring_memo(String str1, String str2, int count, int[][] dp) {
        if (str1.length() == 0 || str2.length() == 0)
            return 0;
        if (dp[dp.length - str1.length()][dp[0].length - str2.length()] != 0)
            return dp[dp.length - str1.length()][dp[0].length - str2.length()];
        if (str1.charAt(0) == str2.charAt(0)) {
            count = longestCommonSubstring_memo(str1.substring(1), str2.substring(1), count + 1, dp) + 1;
        }
        count = Math.max(Math.max(longestCommonSubstring_memo(str1.substring(1), str2, 0, dp),
                longestCommonSubstring_memo(str1, str2.substring(1), 0, dp)), count);
        dp[dp.length - str1.length()][dp[0].length - str2.length()] = count;
        return count;
    }

    public static void longestCommonSubseq() {
        String str1 = "ssunday";
        String str2 = "ssssssssssknoday";
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];
        for (int i = dp.length - 2; i >= 0; i--) {
            for (int j = dp[0].length - 2; j >= 0; j--) {
                if (str1.charAt(i) == str2.charAt(j))
                    dp[i][j] += dp[i + 1][j + 1] + 1;
                else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }
        System.out.println(dp[0][0]);
    }

    public static void kadanes() {
        int[] nums = { 1 };
        int ans = Integer.MIN_VALUE;
        int csum = 0;
        for (int i = 0; i < nums.length; i++) {
            csum += nums[i];
            if (ans < csum)
                ans = csum;
            if (csum < 0)
                csum = 0;
        }
        System.out.println(ans);
    }

    public static void kadanes_() {
        int[] nums = { 6, -1, 3, 4 };
        int ans = Integer.MIN_VALUE;
        int csum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (csum > 0) {
                csum += nums[i];
            } else {
                csum = nums[i];
            }
            if (ans < csum)
                ans = csum;
        }
        System.out.println(ans);
    }

    public static void kConcate() {
        int[] arr = { 6, -1, 3 };
        // if (k == 1)
        // System.out.println(kadanes(arr));
        long sum = 0;
        for (int ele : arr)
            sum += ele;
        int[] kd = new int[2 * arr.length];
        for (int i = 0; i < kd.length; i++)
            kd[i] = arr[i % arr.length];
        long ans = 0;
        // if (sum <= 0) {
        // ans = kadanes(kd);
        // } else {
        // // ans = kadanes(kd) + (k - 2) * sum;
        // }
    }

    public static void maxSubArrayOneDeletion() {
        int[] arr = { 6, -1, 33, -10 };
        if (arr.length == 0)
            System.out.println(0);
        if (arr.length == 1)
            System.out.println(arr[0]);
        int[] rtlkd = new int[arr.length];
        int csum = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (csum >= 0)
                csum += arr[i];
            else
                csum = arr[i];
            rtlkd[i] = csum;
        }
        int ans = Integer.MIN_VALUE;
        if (ans < rtlkd[1])
            ans = rtlkd[1];
        csum = arr[0];
        for (int i = 1; i < arr.length - 1; i++) {
            ans = Math.max(Math.max(Math.max(rtlkd[i + 1], csum), csum + rtlkd[i + 1]), ans);
            if (csum >= 0)
                csum += arr[i];
            else
                csum = arr[i];

        }
        if (ans < csum)
            ans = csum;
        System.out.println(ans);
    }

    /********************************************/

    public static int findNumberOfLIS() {
        int[] arr = { 1, 3, 5, 4, 6 };
        int ans = 0;
        int len = 0;
        int[] dp = new int[arr.length];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] += dp[j];
                        ans = 1;
                    } else if (dp[j] + 1 == dp[i]) {
                        ans++;
                    }
                }
            }
            if (len < dp[i])
                len = dp[i];
        }
        return ans;
    }
}