import java.util.*;

public class IncludeExclude {
    public static void main(String[] args) {
        // binaryStringWithNoAdjZero();
        // arrangeBuildings();
        // subseqaplusbpluscplus();
        // maxSumNonAdj();
        // paintFence();
        // paintHouse();
        paintHouse2();

    }

    public static void binaryStringWithNoAdjZero() {
        int n = 6;
        int zero = 1;
        int one = 1;
        for (int i = 1; i < n; i++) {
            int temp = zero;
            zero = one;
            one = (one + temp);
        }
        System.out.print(one + zero);
    }

    public static void arrangeBuildings() {
        int n = 6;
        int build = 1;
        int nbuild = 1;
        for (int i = 1; i < n; i++) {
            int temp = build;
            build = nbuild;
            nbuild = (nbuild + temp);
        }
        long ans = (nbuild + build) * (nbuild + build);
        System.out.print(ans);
    }

    public static void subseqaplusbpluscplus() {
        String s = "abcabc";
        int a = 0, ab = 0, abc = 0;
        ArrayList<String> astr = new ArrayList<>();
        ArrayList<String> abstr = new ArrayList<>();
        ArrayList<String> abcstr = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == 'a') {
                a = 2 * a + 1;
                int size = astr.size();
                for (int j = 0; j < size; j++) {
                    astr.add(astr.get(j) + "a");
                }
                astr.add("a");
            } else if (ch == 'b') {
                ab = 2 * ab + a;
                int size = abstr.size();
                for (int j = 0; j < size; j++) {
                    abstr.add(abstr.get(j) + "b");
                }
                for (int j = 0; j < astr.size(); j++) {
                    abstr.add(astr.get(j) + "b");
                }
            } else {
                abc = 2 * abc + ab;
                int size = abcstr.size();
                for (int j = 0; j < size; j++) {
                    abcstr.add(abcstr.get(j) + "c");
                }
                for (int j = 0; j < abstr.size(); j++) {
                    abcstr.add(abstr.get(j) + "c");
                }
            }
        }
        System.out.println(abcstr);
        System.out.println(abc);
    }

    public static void maxSumNonAdj() {
        int[] arr = { 5, 10, 10, 100, 5, 6 };
        int inc = arr[0];
        int exc = 0;
        for (int i = 1; i < arr.length; i++) {
            int ninc = arr[i] + exc;
            int nexc = Math.max(exc, inc);

            inc = ninc;
            exc = nexc;
        }
        System.out.println(Math.max(inc, exc));
    }

    public static int paintFence() {
        int n = 3, k = 2;// o/p6
        int same = 0, diff = k;
        if (k == 0 || n == 0)
            return 0;
        for (int i = 1; i < n; i++) {
            int nsame = (diff);
            int ndiff = (same + diff) * (k - 1);
            same = nsame;
            diff = ndiff;
        }
        return same + diff;
    }

    public static int paintHouse() {
        int[][] costs = { { 14, 2, 11 }, { 11, 14, 5 }, { 14, 3, 10 } };// o/p 10
        if (costs.length == 0)
            return 0;

        int ans = Integer.MAX_VALUE;
        for (int i = 1; i < costs.length; i++) {
            for (int j = 0; j < 3; j++) {
                costs[i][j] = Math.min(costs[i - 1][(j + 1) % 3], costs[i - 1][(j + 2) % 3]) + costs[i][j];
            }
        }
        int len = costs.length;
        ans = Math.min(costs[len - 1][0], Math.min(costs[len - 1][1], costs[len - 1][2]));
        return ans;
    }

    public static int paintHouse2() {
        int[][] costs = { { 14, 2, 11 }, { 11, 14, 5 }, { 14, 3, 10 } };
        int len = costs.length;
        if (len == 0)
            return 0;
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        for (int i = 0; i < costs[0].length; i++) {
            if (costs[0][i] < min1) {
                min2 = min1;
                min1 = costs[0][i];
            } else if (costs[0][i] < min2) {
                min2 = costs[0][i];
            }
        }
        for (int i = 1; i < len; i++) {
            int nmin1 = Integer.MAX_VALUE, nmin2 = Integer.MAX_VALUE;
            for (int j = 0; j < costs[0].length; j++) {
                int curr = costs[i - 1][j];
                if (min1 == curr) {
                    costs[i][j] += min2;
                } else {
                    costs[i][j] += min1;
                }
                if (costs[i][j] < nmin1) {
                    nmin2 = nmin1;
                    nmin1 = costs[i][j];
                } else if (costs[i][j] < nmin2) {
                    nmin2 = costs[i][j];
                }
            }
            min1 = nmin1;
            min2 = nmin2;
        }
        return min1;
    }

}