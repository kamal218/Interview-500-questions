import java.util.*;

import java.util.PriorityQueue;

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

        // CLASS 4
        // meximization();
        // mArraysCodeeForces();
        // kLCMCodeForcesEasy();
        // kLCMCodeForcesHard();
        // consecutiveNumbersSum();
        // long ans = fastExponentiation(2, 4);
        // int[][] ans = fibonacciOptimized(3);
        // sieveOfEratosthenes();
        // primeInRange();
        // maximumSwap(2117);
        // maximumSwap_(2117);
        // minJump();

        // CLASS 5
        // minDominoRotations();
        // multiplyString();
        // pairWithGivenSum();
        // pairWithGivenDiff();
        // numRescueBoats();
        // smallestRange();
        // maxProduct();
        // reverseVowels("Hello");
        // int ans = minNumberOfPlatforms();

        // CLASS 6
        // firstMissingPositive();
        // kthMissingPositive();
        // rotateImage();
        // pushDominoes();
        // validPalindrome2();
        // sumOfSubseqWidths();
        // maxDistToClosest();
        // buddyNim();
        // sortTheArrayCodeForces();
        // int ans = maxSumTwoNoOverlapExtraSpace();
        int ans = maxSumTwoNoOverlapExtraSpaceOptimized();

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

    public static void meximization() {
        int[] arr = { 0, 1, 2 };
        Arrays.sort(arr);

        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] != arr[i + 1]) {
                System.out.print(arr[i] + " ");
                arr[i] = -1;
            }
        }
        System.out.print(arr[arr.length - 1] + " ");
        arr[arr.length - 1] = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != -1) {
                System.out.print(arr[i] + " ");
            }
        }
    }

    public static void mArraysCodeeForces() {
        int[] arr = {};
        int m = 4;
        int n = arr.length;
        int[] help = new int[m];
        for (int i = 0; i < n; i++) {
            int v = arr[i];
            help[v % m]++;
        }
        if (n == 1) {
            System.out.println(1);
            return;
        }
        int ans = 0;

        if (help[0] > 0)
            ans++;
        int si = 1;
        int ei = m - 1;
        while (si < ei) {
            if (help[si] == help[ei]) {
                if (help[si] != 0)
                    ans++;
            } else {
                int max = Math.max(help[si], help[ei]);
                int min = Math.min(help[si], help[ei]);
                ans++;
                max = max - min - 1;
                ans += max;
            }
            si++;
            ei--;
        }
        if (si == ei && help[si] > 0)
            ans++;
        System.out.println(ans);
    }

    public static void kLCMCodeForcesEasy() {
        int n = 3;

        if (n % 2 != 0) {
            System.out.println(1 + " " + n / 2 + " " + n / 2);
        } else if (n % 4 == 0) {
            System.out.println(n / 2 + " " + n / 4 + " " + n / 4);
        } else {
            System.out.println(2 + " " + (n - 2) / 2 + " " + (n - 2) / 2);
        }
    }

    public static void kLCMCodeForcesHard() {
        int n = 20;
        int k = 5;
        while (k != 3) {
            System.out.print(1 + " ");
            k--;
            n--;
        }
        if (n % 2 != 0) {
            System.out.println(1 + " " + n / 2 + " " + n / 2);
        } else if (n % 4 == 0) {
            System.out.println(n / 2 + " " + n / 4 + " " + n / 4);
        } else {
            System.out.println(2 + " " + (n - 2) / 2 + " " + (n - 2) / 2);
        }
    }

    public static int consecutiveNumbersSum() {
        int n = 20;
        int ans = 0;
        for (int k = 1; k * k < (2 * n); k++) {
            int st = (n - ((k - 1) * k) / 2);
            if (st % k == 0)
                ans++;
        }
        return ans;
    }

    public static long fastExponentiation(int a, int b) {
        if (b == 0)
            return 1;
        long res = fastExponentiation(a, b / 2);
        return b % 2 == 0 ? (res * res) : (res * res * a);
    }

    public static int[][] fibonacciOptimized(int n) {
        int[][] base = { { 1, 1 }, { 1, 0 } };
        if (n == 1)
            return base;
        int[][] res = fibonacciOptimized(n / 2);
        multiplyMatrix(res, res);
        return n % 2 == 0 ? multiplyMatrix(res, res) : multiplyMatrix(multiplyMatrix(res, res), base);
    }

    public static int[][] multiplyMatrix(int[][] m1, int[][] m2) {
        int[][] res = new int[2][2];
        res[0][0] = m1[0][0] * m2[0][0] + m1[0][1] * m2[1][0];
        res[0][1] = m1[0][0] * m2[0][1] + m1[0][1] * m2[1][1];
        res[1][0] = m1[1][0] * m2[0][0] + m1[1][1] * m2[1][0];
        res[1][1] = m1[1][0] * m2[0][1] + m1[1][1] * m2[1][1];
        return res;
    }

    public static void sieveOfEratosthenes() {
        int n = 100;
        boolean[] dp = new boolean[n + 1];
        for (long i = 2; i <= n; i++) {
            if (!dp[(int) i]) {
                for (long j = i * i; j <= n; j += i) {
                    dp[(int) j] = true;
                }
            }
        }
        for (int i = 2; i < dp.length; i++) {
            if (!dp[i]) {
                System.out.print(i + " ");
            }
        }
    }

    public static void primeInRange() {
        int st = 17;
        int end = 500;
        // create simple sieve for root(end)
        int rt = (int) Math.sqrt(end);
        boolean[] dp = new boolean[rt + 1];
        for (long i = 2; i < dp.length; i++) {
            if (!dp[(int) i]) {
                for (long j = i * i; j <= rt; j += i) {
                    dp[(int) j] = true;
                }
            }
        }

        boolean[] ndp = new boolean[end - st + 1];
        for (int k = 2; k < dp.length; k++) {
            if (!dp[k]) {
                int ceil = (st / k);
                if (st % k != 0 || st == 0)
                    ceil++;
                ceil = (ceil) * k;
                int idx = ceil - st;
                // System.out.println(ceil);
                if (st <= k) {
                    idx += k;
                }
                // System.out.println(idx + st);
                while (idx <= end - st) {
                    ndp[idx] = true;
                    idx += k;
                }
            }
        }
        for (int i = Math.max(st, 2); i <= end; i++) {
            int idx = i - st;
            if (!ndp[idx]) {
                System.out.println(i);
            }
        }
    }

    public static void wiggleSort() {
        int[] arr = {};
        // greater at odd index
        for (int i = 1; i < arr.length; i++) {
            if (i % 2 == 0) {
                if (arr[i] > arr[i - 1])
                    swap(arr, i, i - 1);
            } else {
                if (arr[i] < arr[i - 1])
                    swap(arr, i, i - 1);
            }
        }
    }

    public static int maximumSwap(int num) {
        // if number is one digit
        if (num < 10)
            return num;
        // use string Builderr for fast access of elements
        StringBuilder sb = new StringBuilder();
        while (num != 0) {
            sb.append((char) (num % 10 + '0'));
            num /= 10;
        }

        // save max index value upto current index
        int[] max = new int[sb.length()];
        max[0] = 0;
        for (int i = 1; i < sb.length(); i++) {
            if (sb.charAt(i) - '0' > sb.charAt(max[i - 1]) - '0') {
                max[i] = i;
            } else {
                max[i] = max[i - 1];
            }
        }

        // create answer if values can be swapped
        for (int i = max.length - 1; i >= 0; i--) {
            int dig1 = sb.charAt(i) - '0';
            int dig2 = sb.charAt(max[i]) - '0';
            if (dig1 < dig2) {
                char temp = sb.charAt(i);
                sb.setCharAt(i, sb.charAt(max[i]));
                sb.setCharAt(max[i], temp);
                break;
            }
        }

        int ans = 0;
        // convert string builder into number form
        for (int i = max.length - 1; i >= 0; i--) {
            ans = ans * 10 + (sb.charAt(i) - '0');
        }
        return ans;
    }

    public static int maximumSwap_(int num) {
        char[] A = Integer.toString(num).toCharArray();
        int[] last = new int[10];
        for (int i = 0; i < A.length; i++) {
            last[A[i] - '0'] = i;
        }

        for (int i = 0; i < A.length; i++) {
            for (int d = 9; d > A[i] - '0'; d--) {
                if (last[d] > i) {
                    char tmp = A[i];
                    A[i] = A[last[d]];
                    A[last[d]] = tmp;
                    return Integer.valueOf(new String(A));
                }
            }
        }
        return num;
    }

    public static int minJump() {
        int tar = 17;
        int jump = 1;
        int pos = 0;
        int ans = 0;
        while (pos < tar) {
            pos += jump;
            jump++;
            ans++;
        }
        if (pos == tar)
            return ans;
        if ((pos - tar) % 2 == 0)
            return ans + 1;
        pos += jump + 1;
        if ((pos - tar) % 2 == 0)
            return ans + 2;
        return ans + 3;
    }

    public static int minDominoRotations(int[] A, int[] B) {
        int ans = Integer.MAX_VALUE;
        // a same value
        int a1 = countDominoSwaps(A, B, true, A[0]);
        int a2 = countDominoSwaps(A, B, true, B[0]);
        // b same value
        int b1 = countDominoSwaps(A, B, false, B[0]);
        int b2 = countDominoSwaps(A, B, false, A[0]);

        ans = Math.min(a1, Math.min(a2, Math.min(b1, b2)));
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static int countDominoSwaps(int[] a, int[] b, boolean first, int val) {
        int ans = 0;
        for (int i = 0; i < a.length; i++) {
            if (first) {
                if (a[i] == val) {

                } else if (b[i] == val) {
                    ans++;
                } else {
                    return Integer.MAX_VALUE;
                }
            } else {
                if (b[i] == val) {

                } else if (a[i] == val) {
                    ans++;
                } else {
                    return Integer.MAX_VALUE;
                }
            }
        }
        return ans;
    }

    public static String multiplyString() {
        String str1 = "";
        String str2 = "";
        int n = str1.length();
        int m = str2.length();
        char[] arr1 = str1.toCharArray();
        char[] arr2 = str2.toCharArray();
        char[] ans = new char[n + m];
        Arrays.fill(ans, (char) (0 + '0'));
        int carry = 0;
        int mul = 0;
        int k = n + m;
        int p = n + m;
        for (int i = m - 1; i >= 0; i--) {
            int down = arr2[i] - '0';
            p--;
            k = p;
            carry = 0;
            for (int j = n - 1; j >= 0; j--) {
                int up = arr1[j] - '0';
                mul = (up * down) + carry;
                mul += (ans[k] - '0');
                carry = mul / 10;
                ans[k] = (char) ((mul % 10) + '0');
                k--;
            }
            if (carry != 0) {
                ans[k] = (char) (carry + '0');
            }
        }
        StringBuilder sb = new StringBuilder();
        boolean zero = true;
        int pt = 0;
        if (ans[0] == '0')
            pt = 1;
        for (; pt < n + m; pt++) {
            if (ans[pt] != '0')
                zero = false;
            sb.append(ans[pt]);
        }
        if (zero)
            return "0";
        return sb.toString();
    }

    public static boolean pairWithGivenSum() {
        int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8 };
        int tar = 10;
        int si = 0, ei = arr.length - 1;
        while (si < ei) {
            int sum = arr[si] + arr[ei];
            if (sum == tar)
                return true;
            else if (sum < tar)
                si++;
            else
                ei--;
        }
        return false;
    }

    public static boolean pairWithGivenDiff() {
        int[] arr = { 1, 2, 3, 4 };
        int tar = 1;
        int si = 0, ei = 1;
        while (si < arr.length && ei < arr.length) {
            int diff = arr[ei] - arr[si];
            if (si != ei && (diff == tar || diff == -tar))// -ve or +ve tar
                return true;
            else if (diff < tar)
                ei++;
            else
                si++;
        }
        return false;
    }

    public static int numRescueBoats(int[] arr, int l) {
        Arrays.sort(arr);
        int i = 0, j = arr.length - 1;
        int ans = 0;
        while (i <= j) {
            ans++;
            if (arr[i] + arr[j] <= l)
                i++;
            j--;
        }
        return ans;
    }

    public static class triple {
        int r = 0;
        int c = 0;
        int val = 0;

        public triple(int r, int c, int val) {
            this.r = r;
            this.c = c;
            this.val = val;
        }
    }

    public static int[] smallestRange(List<List<Integer>> nums) {
        PriorityQueue<triple> pq = new PriorityQueue<>((a, b) -> (a.val - b.val));
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.size(); i++) {
            pq.add(new triple(i, 0, nums.get(i).get(0)));
            max = Math.max(max, nums.get(i).get(0));
        }
        int sv = -(int) 1e6;
        int ev = -(int) 1e6;
        while (true) {
            triple min = pq.poll();
            if (sv == -(int) 1e6 || (ev - sv) > (max - min.val)) {
                sv = min.val;
                ev = max;
            }
            if (nums.get(min.r).size() - 1 != min.c) {
                pq.add(new triple(min.r, min.c + 1, nums.get(min.r).get(min.c + 1)));
                max = Math.max(max, nums.get(min.r).get(min.c + 1));
            } else {
                break;
            }
        }
        int[] ans = new int[2];
        ans[0] = sv;
        ans[1] = ev;
        return ans;
    }

    public static int maxProduct(int[] nums) {
        int ans = Integer.MIN_VALUE;
        int cmax = 1;

        for (int i = 0; i < nums.length; i++) {
            cmax *= nums[i];
            ans = Math.max(ans, cmax);
            if (cmax == 0)
                cmax = 1;
        }

        cmax = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            cmax *= nums[i];
            ans = Math.max(ans, cmax);
            if (cmax == 0)
                cmax = 1;
        }

        return ans;
    }

    public static String reverseVowels(String s) {
        int i = 0, j = s.length() - 1;

        char[] arr = s.toCharArray();
        while (i < j) {
            if (vow(arr[i]) && vow(arr[j])) {
                char ch = arr[i];
                arr[i] = arr[j];
                arr[j] = ch;
                i++;
                j--;
            } else if (vow(arr[i]))
                j--;
            else if (vow(arr[j]))
                i++;
            else {
                i++;
                j--;
            }
        }
        return String.valueOf(arr);
    }

    public static Boolean vow(char ch) {
        if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' || ch == 'A' || ch == 'E' || ch == 'I'
                || ch == 'O' || ch == 'U')
            return true;
        return false;
    }

    // public static int minNumberOfPlatforms() {
    // int arr[] = { 900, 940, 950, 1100, 1500, 1800 };
    // int dep[] = { 910, 1200, 1120, 1130, 1900, 2000 };
    // int ans = 1;
    // int cpf = 1;
    // int etime = dep[0];
    // for (int i = 1; i < arr.length; i++) {
    // if (arr[i] < etime) {
    // cpf++;
    // etime = Math.max(etime, dep[i]);
    // } else {
    // cpf = 1;
    // etime = Math.max(etime, dep[i]);
    // }
    // ans = Math.max(ans, cpf);
    // }
    // return ans;
    // }

    public static int firstMissingPositive() {
        int[] arr = {};
        int i = 0;
        while (i < arr.length) {
            if (arr[i] <= arr.length && arr[i] >= 1 && arr[arr[i] - 1] != arr[i]) {
                int temp = arr[arr[i] - 1];
                arr[arr[i] - 1] = arr[i];
                arr[i] = temp;
                i--;
            }
            i++;
        }
        for (i = 0; i < arr.length; i++) {
            if (arr[i] != i + 1)
                return i + 1;
        }
        return arr.length + 1;
    }

    public static int kthMissingPositive(int[] nums, int k) {
        int val = 1;
        int i = 0;
        while (i < nums.length && k != 0) {
            if (nums[i] == val) {
                val++;
                i++;
            } else {
                k--;
                val++;
            }
        }
        if (k == 0)
            return val - 1;
        return val + k - 1;
    }

    public static void rotateImage(int[][] mat) {
        for (int gap = 1; gap < mat.length; gap++) {
            int si = 0;
            int ei = gap;
            while (ei < mat.length) {
                int temp = mat[si][ei];
                mat[si][ei] = mat[ei][si];
                mat[ei][si] = temp;
                si++;
                ei++;
            }
        }
        int si = 0;
        int ei = mat.length - 1;
        while (si < ei) {
            for (int i = 0; i < mat.length; i++) {
                int temp = mat[i][si];
                mat[i][si] = mat[i][ei];
                mat[i][ei] = temp;
            }
            si++;
            ei--;
        }
    }

    public static String pushDominoes(String s) {
        StringBuilder sb = new StringBuilder();
        sb.append('L');
        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i));
        }
        sb.append('R');
        int st = 0, end = 1;
        while (end < sb.length()) {
            while (end < sb.length() && sb.charAt(end) == '.') {
                end++;
            }
            char l = sb.charAt(st);
            char r = sb.charAt(end);
            if (l == 'L' && r == 'L') {
                int k = end - 1;
                while (k > st) {
                    sb.setCharAt(k, 'L');
                    k--;
                }
            } else if (l == 'R' && r == 'R') {
                int k = end - 1;
                while (k > st) {
                    sb.setCharAt(k, 'R');
                    k--;
                }
            } else if (l == 'L' && r == 'R') {

            } else {
                int si = st + 1, ei = end - 1;
                while (si < ei) {
                    sb.setCharAt(si, 'R');
                    sb.setCharAt(ei, 'L');
                    si++;
                    ei--;
                }
            }
            st = end;
            end++;
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 1; i < sb.length() - 1; i++)
            ans.append(sb.charAt(i));
        return ans.toString();
    }

    public static boolean validPalindrome2(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j && s.charAt(i) == s.charAt(j)) {
            i++;
            j--;
        }
        if (i >= j)
            return true;
        return isPallinInRange(s, i + 1, j) || isPallinInRange(s, i, j - 1);

    }

    public static boolean isPallinInRange(String s, int i, int j) {
        while (i < j && s.charAt(i) == s.charAt(j)) {
            i++;
            j--;
        }
        return i >= j;
    }

    public static int sumOfSubseqWidths(int[] A) {
        Arrays.sort(A);
        long ans = 0;
        int mod = 1000000007;
        long[] pow = new long[A.length];
        pow[0] = 1;
        for (int i = 1; i < A.length; i++)
            pow[i] = (pow[i - 1] * 2) % mod;

        for (int i = 0; i < A.length; i++) {
            ans = (ans + (pow[i] - pow[A.length - i - 1]) * A[i]) % mod;
        }
        return (int) ans;
    }

    public static int maxDistToClosest(int[] seats) {
        int st = -1;
        int pt = 0;
        int ans = Integer.MIN_VALUE;
        while (seats[pt] != 1) {// for 0000001
            pt++;
        }
        ans = pt;
        st = pt;
        pt++;
        while (pt < seats.length) {
            if (seats[pt] == 1) {
                ans = Math.max(ans, (pt - st) / 2);
                st = pt;
            }
            pt++;
        }
        ans = Math.max(ans, seats.length - st - 1);// for 10000000000000000
        return ans;
    }

    public static void buddyNim() {
        int n = 5;
        int m = 4;
        long[] arr1 = new long[n];
        long[] arr2 = new long[m];

        // case 2 same sum hence check symmetricity
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        int p1 = 0, p2 = 0;
        while (p1 < n && arr1[p1] == 0) {
            p1++;
        }
        while (p2 < m && arr2[p2] == 0) {
            p2++;
        }
        if ((n - p1) != (m - p2)) {
            System.out.println("Alice");
            // continue;
        }
        boolean sym = true;
        while (p1 < n) {
            if (arr1[p1] != arr2[p2]) {
                sym = false;
                break;
            }
            p1++;
            p2++;
        }
        if (sym) {
            System.out.println("Bob");
        } else {
            System.out.println("Alice");
        }
    }

    public static void sortTheArrayCodeForces() {
        int n = 10;
        int[] arr = new int[n];
        int si = 0, ei = n - 1;
        while (si + 1 < n) {
            if (arr[si + 1] < arr[si])
                break;
            si++;
        }
        if (si == n - 1) {
            System.out.println("yes");
            System.out.println(1 + " " + 1);
            // continue;
        }
        while (ei - 1 >= 0) {
            if (arr[ei - 1] > arr[ei])
                break;
            ei--;
        }
        boolean rev = isSorted(arr, si, ei);
        int lv = si > 0 ? arr[si - 1] : -1;
        int rv = ei < n - 1 ? arr[ei + 1] : Integer.MAX_VALUE;
        if (rev && (lv <= arr[ei]) && (rv >= arr[si])) {
            System.out.println("yes");
            System.out.println((si + 1) + " " + (ei + 1));
        } else {
            System.out.println("no");
        }
    }

    public static boolean isSorted(int[] arr, int si, int ei) {
        si = si + 1;
        while (si <= ei) {
            if (arr[si] > arr[si - 1])
                return false;
            si++;
        }
        return true;
    }

    public static int bulbSwitch1() {
        // only perfect squares will be on due to odd factors
        int n = 10;
        return (int) Math.sqrt(n);
    }

    public static int bulbSwitch3() {
        int[] light = { 1, 2, 3, 4, 5 };
        int ans = 0;
        int max = light[0];
        for (int i = 0; i < light.length; i++) {
            max = Math.max(max, light[i]);
            if (i + 1 == max)
                ans++;
        }
        return ans;
    }

    public static int maxSumTwoNoOverlapExtraSpace() {
        int[] arr = { 3, 8, 1, 3, 2, 1, 8, 9, 0 };
        int l = 3;
        int m = 2;
        int[] leftl = maxSubarrayWithLLengthFromleft(arr, l);// array with max subarray upto i from left of length l
        int[] rightm = maxSubarrayWithLLengthFromRight(arr, m);// array with max subarray upto i from right of length m
        int[] leftm = maxSubarrayWithLLengthFromleft(arr, m);// array with max subarray upto i from left of length m
        int[] rightl = maxSubarrayWithLLengthFromRight(arr, l);// array with max subarray upto i from right of length l
        int ans = Integer.MIN_VALUE;
        for (int i = l - 1; i < arr.length - m; i++) {
            ans = Math.max(ans, leftl[i] + rightm[i + 1]);
        }
        for (int i = arr.length - l; i >= m; i--) {
            ans = Math.max(ans, leftm[i - 1] + rightl[i]);
        }
        return ans;
    }

    public static int[] maxSubarrayWithLLengthFromleft(int[] arr, int l) {
        int[] ans = new int[arr.length];
        int i = 0;
        int sum = 0;
        while (i < l) {
            sum += arr[i];
            i++;
        }
        ans[i - 1] = sum;
        while (i < arr.length) {
            sum += arr[i];
            sum -= arr[i - l];
            ans[i] = Math.max(ans[i - 1], sum);
            i++;
        }
        return ans;
    }

    public static int[] maxSubarrayWithLLengthFromRight(int[] arr, int l) {
        int n = arr.length;
        int[] ans = new int[n];
        int i = n - 1;
        int sum = 0;
        while (i >= n - l) {
            sum += arr[i];
            i--;
        }
        ans[i + 1] = sum;
        while (i >= 0) {
            sum += arr[i];
            sum -= arr[i + l];
            ans[i] = Math.max(ans[i + 1], sum);
            i--;
        }
        return ans;
    }

    public static int maxSumTwoNoOverlapExtraSpaceOptimized() {
        int[] arr = { 3, 8, 1, 3, 2, 1, 8, 9, 0 };
        int l = 3;
        int m = 2;
        int ans = Integer.MIN_VALUE;
        for (int i = 1; i < arr.length; i++)// prefix sum array
            arr[i] += arr[i - 1];
        int lmax = 0, mmax = 0;
        lmax = arr[l - 1];
        mmax = arr[m - 1];
        ans = arr[l + m - 1];
        for (int i = l + m; i < arr.length; i++) {
            lmax = Math.max(lmax, arr[i - m] - arr[i - l - m]);
            ans = Math.max(ans, lmax + arr[i] - arr[i - m]);
            mmax = Math.max(mmax, arr[i - l] - arr[i - l - m]);
            ans = Math.max(ans, mmax + arr[i] - arr[i - l]);
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