import java.util.*;

public class AdvancedArrays {
    public static void main(String[] args) {
        // SET1 IP_BATCH
        set1();
    }

    public static void set1() {
        // CLASS 1
        // boolean ans = longPressedName();
        // int[] ans = rangeAddition();
        // int ans=maxRangeQuery();//o/p is 3
        // String ans = orderlyQueue();//O/p is "acb"
        // int ans = containerWithMostWater();// O/P is 49

        // CLASS 2
        // int[] ans = sortedSquares();
        // relativePrimePairs();
        // napoleanCakeJumpGame();
        // napoleanCakePrefixSum();
        // int ans=majorityElement();
        // List<Integer> ans=majorityElement2();
        // List<Integer> ans = majorityElementGeneral();
        // int ans = maxProductOfThreeNumbers();
        // int ans=largestAtLeastTwice();
        // int[] ans=arrayProductExceptItself();
        // System.out.println(ans);
    }

    public static boolean longPressedName() {
        String name = "alex";
        String typed = "aalleeexx";
        // "alex"
        // "aaleex"

        // "leelee"
        // "lleeelee"
        int p1 = 0, p2 = 0;
        while (p1 < name.length() && p2 < typed.length()) {
            if (name.charAt(p1) == typed.charAt(p2)) {
                p1++;
                p2++;
            } else {
                if (p2 > 0 && typed.charAt(p2 - 1) == typed.charAt(p2)) {
                    while (p2 < typed.length() && typed.charAt(p2 - 1) == typed.charAt(p2))
                        p2++;
                } else {
                    return false;
                }
            }
        }
        if (p2 == typed.length() && p1 != name.length())
            return false;
        while (p2 != typed.length()) {
            if (p2 > 0 && typed.charAt(p2) == typed.charAt(p2 - 1))
                p2++;
            else
                return false;
        }
        return true;
    }

    public static int[] rangeAddition() {
        int length = 10;
        int[][] u = { { 2, 4, 6 }, { 5, 6, 8 }, { 1, 9, -4 } };
        int[] ans = new int[length];
        for (int i = 0; i < u.length; i++) {
            int s = u[i][0];
            int e = u[i][1];
            int v = u[i][2];
            ans[s] += v;
            if (e != length - 1)
                ans[e + 1] += -v;
        }
        for (int i = 1; i < ans.length; i++) {
            ans[i] += ans[i - 1];
        }
        return ans;
    }

    public static int maxRangeQuery() {
        int n = 3;
        int k = 2;
        int[][] q = { { 2, 6 }, { 4, 9 }, { 1, 4 } };
        int[] arr = new int[100001];
        for (int i = 0; i < n; i++) {
            arr[q[i][0]]++;
            arr[q[i][1] + 1]--;
        }
        int[] kc = new int[100001];
        int[] kpoc = new int[100001];
        if (arr[0] == k)
            kc[0] = 1;
        if (arr[0] == k + 1)
            kpoc[0] = 1;
        for (int i = 1; i < arr.length; i++) {
            arr[i] += arr[i - 1];
            kc[i] = kc[i - 1];
            kpoc[i] = kpoc[i - 1];
            if (arr[i] == k) {
                kc[i] += 1;
            }
            if (arr[i] == k + 1) {
                kpoc[i] += 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < q.length; i++) {
            int s = q[i][0];
            int e = q[i][1];
            int g = 0, l = 0;
            if (s == 0) {
                g = kpoc[e];
                l = kc[e];
            } else {
                g = kpoc[e] - kpoc[s - 1];
                l = kc[e] - kc[s - 1];
            }
            if (ans < kc[100000] - l + g) {
                ans = kc[100000] - l + g;
            }
        }
        return ans;
    }

    public static String orderlyQueue() {
        String S = "cba";
        int k = 1;
        char[] arr = S.toCharArray();
        if (k > 1) {
            Arrays.sort(arr);
            return String.valueOf(arr);
        }
        String ans = S;
        for (int i = 0; i < S.length(); i++) {
            rotateStringByOne(arr);
            int d = ans.compareTo(String.valueOf(arr));
            if (d > 0) {
                ans = String.valueOf(arr);
            }
        }
        return ans;
    }

    public static void rotateStringByOne(char[] arr) {
        char ch = arr[0];
        for (int i = 0; i < arr.length - 1; i++) {
            arr[i] = arr[i + 1];
        }
        arr[arr.length - 1] = ch;
    }

    public static int containerWithMostWater() {
        int[] h = { 1, 8, 6, 2, 5, 4, 8, 3, 7 };
        int ans = 0;
        int i = 0, j = h.length - 1;
        while (i <= j) {
            ans = Math.max(ans, (j - i) * (Math.min(h[i], h[j])));
            if (h[i] < h[j])
                i++;
            else
                j--;
        }
        return ans;
    }

    public static int[] sortedSquares() {
        int[] nums = { -4, 0, 2, 3, 4 };
        int len = nums.length;
        int[] ans = new int[len];
        int neg = 0, pos = 0;
        while (pos < len && nums[pos] < 0)
            pos++;
        neg = pos - 1;
        int k = 0;
        while (pos < len && neg >= 0) {
            int n = -nums[neg];
            int p = nums[pos];
            if (n >= p) {
                ans[k] = p * p;
                pos++;
            } else {
                ans[k] = n * n;
                neg--;
            }
            k++;
        }
        while (pos < len) {
            ans[k] = nums[pos] * nums[pos];
            pos++;
            k++;
        }
        while (neg >= 0) {
            ans[k] = nums[neg] * nums[neg];
            neg--;
            k++;
        }
        return ans;
    }

    public static void rotateArray() {
        int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        int k = 4;
        int len = arr.length;
        k = k % len;// case 1 for more rotations
        if (k < 0) {// case 2 for negative rotations
            k = len + k;
        }
        swapRange(arr, 0, len - k - 1);// swap left part
        swapRange(arr, len - k, len - 1);// swap right part
        swapRange(arr, 0, len - 1);// swap whole array
    }

    public static void swapRange(int[] arr, int i, int j) {
        while (i < j) {
            arr[j] = ((arr[i] + arr[j]) - (arr[i] = arr[j]));
            i++;
            j--;
        }
    }

    public static void relativePrimePairs() {
        long l = 1, r = 8;
        System.out.println("YES");
        for (long pt = l; pt < r; pt += 2) {
            System.out.println(pt + " " + (pt + 1));
        }
    }

    public static void napoleanCakeJumpGame() {
        int[] arr = { 0, 3, 0, 0, 1, 3 };
        int[] ans = new int[arr.length];
        int min = Integer.MAX_VALUE;
        for (int i = arr.length - 1; i >= 0; i--) {
            min = Math.min(min, i + 1 - arr[i]);
            if (min <= i) {
                ans[i] = 1;
            }
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(ans[i] + " ");
        }
    }

    public static void napoleanCakePrefixSum() {
        int[] arr = { 0, 3, 0, 0, 1, 3 };
        int[] ans = new int[arr.length];
        int[] help = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                int idx = i - arr[i];
                help[i] += 1;
                if (idx >= 0)
                    help[idx] -= 1;
            }
        }
        if (help[arr.length - 1] > 0)
            ans[arr.length - 1] = 1;
        for (int i = arr.length - 2; i >= 0; i--) {
            help[i] += help[i + 1];
            if (help[i] != 0)
                ans[i] = 1;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(ans[i] + " ");
        }
    }

    public static int majorityElement() {
        int[] arr = { 2, 2, 1, 1, 1, 2, 2 };
        int ans = arr[0];
        int count = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == ans) {
                count++;
            } else if (count == 0) {
                ans = arr[i];
                count = 1;
            } else {
                count--;
            }
        }
        return ans;
    }

    public static List<Integer> majorityElement2() {
        int[] arr = { 3, 2, 3 };
        List<Integer> ans = new ArrayList<>();
        int n1 = arr[0];
        int n2 = arr[0];
        int c1 = 1;
        int c2 = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == n1) {
                c1++;
            } else if (arr[i] == n2) {
                c2++;
            } else if (c1 == 0) {
                n1 = arr[i];
                c1 = 1;
            } else if (c2 == 0) {
                n2 = arr[i];
                c2 = 1;
            } else {
                c1--;
                c2--;
            }
        }
        if (checkIfMajorityElement(arr, 3, n1) && c1 != 0)
            ans.add(n1);
        if (checkIfMajorityElement(arr, 3, n2) && c2 != 0)
            ans.add(n2);
        return ans;
    }

    public static List<Integer> majorityElementGeneral() {
        int[] arr = { 3, 1, 2, 2, 1, 2, 3, 3 };// O/p->[2,4]
        int k = 4;
        List<Integer> ans = new ArrayList<>();
        int len = arr.length;
        int[] num = new int[k - 1];
        int[] count = new int[k - 1];
        num[0] = arr[0];
        count[0] = 1;
        for (int i = 1; i < arr.length; i++) {
            int j = 0;
            // check if element is already present
            for (j = 0; j < num.length; j++) {
                if (num[j] == arr[i]) {
                    count[j]++;
                    break;
                }
            }
            // check if space is present
            if (j == count.length) {
                for (j = 0; j < count.length; j++) {
                    if (count[j] == 0) {
                        count[j] = 1;
                        num[j] = arr[i];
                        break;
                    }
                }
            }
            // if no one above are hit then reduce count for all
            if (j == count.length) {
                for (j = 0; j < count.length; j++) {
                    count[j]--;
                }
            }
        }
        for (int i = 0; i < num.length; i++) {
            if (checkIfMajorityElement(arr, k, num[i]))
                ans.add(num[i]);
        }
        return ans;
    }

    public static boolean checkIfMajorityElement(int[] arr, int k, int v) {
        int count = 0;
        for (int ele : arr) {
            if (ele == v)
                count++;
        }
        return count > (arr.length / k);
    }

    public static int maxProductOfThreeNumbers() {
        int[] arr = {};
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            // update max
            if (arr[i] > max1) {
                max3 = max2;
                max2 = max1;
                max1 = arr[i];
            } else if (arr[i] > max2) {
                max3 = max2;
                max2 = arr[i];
            } else if (arr[i] > max3) {
                max3 = arr[i];
            }
            // update min
            if (arr[i] < min1) {
                min2 = min1;
                min1 = arr[i];
            } else if (arr[i] < min2) {
                min2 = arr[i];
            }
        }
        return Math.max((min1 * min2 * max1), (max1 * max2 * max3));
    }

    public static int largestAtLeastTwice() {
        int[] arr = {};
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE;
        int idx = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max1) {
                idx = i;
                max2 = max1;
                max1 = arr[i];
            } else if (arr[i] > max2) {
                max2 = arr[i];
            }

        }
        return max1 >= (2 * max2) ? idx : -1;
    }

    public static int[] arrayProductExceptItself() {
        int[] arr = {};
        int[] ans = new int[arr.length];
        ans[0] = 1;
        ans[1] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            ans[i] = arr[i - 1] * ans[i - 1];
        }
        int r = 1;
        for (int i = arr.length - 1; i >= 0; i--) {
            ans[i] *= r;
            r *= arr[i];
        }
        return ans;
    }
}