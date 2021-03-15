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
        // String ans = nextGreaterElement3("230241");
        // int ans = nextGreaterElementLeetcode(21);
        // napoleanCakePrefixSum();
        // int ans=majorityElement();
        // List<Integer> ans=majorityElement2();
        // List<Integer> ans = majorityElementGeneral();
        // int ans = maxChunksToSorted01();
        // int ans = maxChunksToSorted02();
        // int ans = maxProductOfThreeNumbers();
        // int ans=largestAtLeastTwice();
        // int[] ans=arrayProductExceptItself();

        // CLASS 3
        // int ans=numSubarrayBoundedMax();
        // int ans = maxSumSubarray();
        // int ans = kConLeetCode();
        // segreegate01();
        // segregate012();
        // sortArrayByParity();
        // sortArrayByParity2();
        // List<Integer> ans = partitionLabels();

        System.out.println(ans);
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

    public static int nextGreaterElementLeetcode(int n) {
        StringBuilder sb = new StringBuilder();
        while (n != 0) {
            sb.append(n % 10);
            n /= 10;
        }
        String res = nextGreaterElement3(sb.reverse().toString());
        if (res == "")
            return -1;
        long ans = 0;
        for (int i = 0; i < res.length(); i++) {
            ans = ans * 10 + (res.charAt(i) - '0');
            if (ans > Integer.MAX_VALUE)
                return -1;
        }
        return (int) ans;
    }

    public static String nextGreaterElement3(String str) {
        int st = str.length() - 2;
        StringBuilder sb = new StringBuilder(str);
        while (st >= 0) {
            if (str.charAt(st) < str.charAt(st + 1)) {
                break;
            }
            st--;
        }
        if (st == -1) {
            return "";// decreasing string
        }
        int end = str.length() - 1;
        while (end > st) {
            if (str.charAt(end) > str.charAt(st))
                break;
            end--;
        }
        // swap chars
        char temp = sb.charAt(st);
        sb.setCharAt(st, sb.charAt(end));
        sb.setCharAt(end, temp);
        // reverse array i.e sort
        int i = st + 1;
        int j = str.length() - 1;
        while (i <= j) {
            temp = sb.charAt(i);
            sb.setCharAt(i, sb.charAt(j));
            sb.setCharAt(j, temp);
            i++;
            j--;
        }
        return sb.toString();
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

    public static int maxChunksToSorted01() {
        int[] arr = { 1, 2, 4, 3, 0 };
        int ans = 0;
        int chunk = -1;
        for (int i = 0; i < arr.length; i++) {
            if (i > chunk) {// new chunk starts
                ans++;
                chunk = arr[i];
            } else {// updated chunk size
                chunk = Math.max(chunk, arr[i]);
            }
        }
        return ans;
    }

    public static int maxChunksToSorted02() {
        int[] arr = {};
        int ans = 1;
        int len = arr.length;
        int[] min = new int[len];
        int[] max = new int[len];
        max[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            max[i] = Math.max(arr[i], max[i - 1]);
        }
        min[len - 1] = arr[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            min[i] = Math.min(min[i + 1], arr[i]);
        }
        for (int i = 0; i < arr.length - 1; i++) {
            if (max[i] <= min[i + 1]) {
                ans++;
            }
        }
        return ans;
    }

    public static int numSubarrayBoundedMax() {
        int[] arr = { 73, 55, 36, 5, 55, 14, 9, 7, 72, 52 };
        int l = 32;
        int r = 69;
        int ans = 0;
        int i = 0, j = 0;
        int pcount = 0;
        while (j < arr.length) {
            if (arr[j] < l) {
                ans += pcount;
            } else if (arr[j] > r) {
                i = j + 1;
                pcount = 0;
            } else {
                pcount = (j - i + 1);
                ans += pcount;
            }
            j++;
        }
        return ans;
    }

    public static int maxSumSubarray(int[] arr) {
        // int[] arr = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
        int csum = 0;
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            csum += arr[i];
            if (ans < csum)
                ans = csum;
            if (csum < 0)
                csum = 0;
        }
        return ans;
    }

    public static long kConLeetCode() {
        int[] arr = { 1, -2, 1 };
        int k = 3;
        if (k == 1)
            return maxSumSubarray(arr);
        long sum = 0;
        for (int ele : arr)
            sum += ele;
        int[] kd = new int[2 * arr.length];
        for (int i = 0; i < kd.length; i++)
            kd[i] = arr[i % arr.length];
        long ans = 0;
        if (sum <= 0) {
            ans = maxSumSubarray(kd);
        } else {
            ans = maxSumSubarray(kd) + (k - 2) * sum;
        }
        if (ans < 0)
            return 0;
        return (int) (ans % (1000000007));
    }

    public static void segreegate01() {
        int[] arr = { 0, 1, 1, 1, 0, 0, 1, 1, 1, 0 };
        int zero = 0;
        int one = 0;
        // 0 to zero-1 ->0
        // zero to one-1 ->one
        while (one < arr.length) {
            if (arr[one] == 0) {
                swap(arr, zero, one);
                zero++;
            }
            one++;
        }
        for (int ele : arr)
            System.out.print(ele + " ");
    }

    public static void segregate012() {
        int[] arr = { 0, 2, 2, 2, 1, 1, 0, 0, 0, 1, 2, 2 };
        int zero = 0, one = 0, two = arr.length - 1;
        while (one <= two) {
            if (arr[one] == 0) {
                swap(arr, one, zero);
                one++;
                zero++;
            } else if (arr[one] == 1) {
                one++;
            } else {
                swap(arr, one, two);
                two--;
            }
        }
        for (int ele : arr) {
            System.out.print(ele + " ");
        }
    }

    public static void sortArrayByParity() {
        int[] arr = {};
        int lastOdd = 0, pt = 0;
        while (pt < arr.length) {
            if (arr[pt] % 2 == 0) {
                swap(arr, lastOdd, pt);
                lastOdd++;
            }
            pt++;
        }
        for (int ele : arr)
            System.out.print(ele + " ");
    }

    public static void sortArrayByParity2() {
        int[] arr = { 1, 2, 3, 4 };
        int even = 0, odd = 1;
        while (odd < arr.length) {
            if (arr[odd] % 2 == 0) {
                while (arr[even] % 2 == 0)
                    even += 2;
                swap(arr, even, odd);
            }
            odd += 2;
        }
        for (int ele : arr)
            System.out.print(ele + " ");
    }

    public static List<Integer> partitionLabels() {
        String str = "ababcbacadefegdehijhklij";
        List<Integer> ans = new ArrayList<>();
        int[] map = new int[26];
        for (int i = 0; i < str.length(); i++) {
            map[str.charAt(i) - 'a'] = i;
        }
        int si = 0;
        int ei = 0;
        int pt = 0;
        while (pt < str.length()) {
            ei = Math.max(ei, map[str.charAt(pt) - 'a']);
            if (pt == ei) {
                ans.add(pt - si + 1);
                si = pt + 1;
            }
            pt++;
        }
        return ans;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void swap_01(int[] arr, int i, int j) {
        arr[j] = ((arr[i] + arr[j]) - (arr[i] = arr[j]));
    }

    public static void swap_02(int[] arr, int i, int j) {
        arr[i] = arr[i] + arr[j];
        arr[j] = arr[i] - arr[j];
        arr[i] = arr[i] - arr[j];
    }

    public static void swap_03(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
    }

}