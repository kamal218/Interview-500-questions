import java.util.*;

import javax.lang.model.util.ElementScanner6;

public class AdvancedSearch {
    public static void main(String[] args) {
        solve();
    }

    public static void solve() {
        set1();// IP Batch Pepcoding
        // set2();// YoutTube pepcoding
        // set3();// Miscelanneous + Leetcode
    }

    public static void set1() {
        // CLASS 1 Binary Search
        // int ans = capacityToShipWithinKDays();
        // int ans = splitArrayMinimizeLargestSum();
        // int ans = kokoEatingBananas();
        // int ans = smallestDivisorGivenThreshold();
        // int ans=paintersProblem();

        // CLASS 2
        // MedianFinder obj=new MedianFinder(); // Median in a data stream
        // MedianFinderFollowUp obj = new MedianFinderFollowUp();
        int[] ans = kthSmallestPrimeFraction();
        System.out.println(ans[0] + " " + ans[1]);

        // System.out.println(ans);
    }

    public static void set3() {
        // int ans = majorityElement1();
        // List<Integer> ans=majorityElement2();
        // List<Integer> ans = majorityElementGeneral();
        // int ans = searchWhereAdjDiffIsAtMostK();
        // List<Integer> ans = commonInThreeSortedArray();
        // List<List<Integer>> ans = twoSum();
        // List<List<Integer>> ans = twoSumMap();
        // List<List<Integer>> ans = threeSum();
        // List<List<Integer>> ans = fourSum();//O(n^3)
        // List<List<Integer>> ans = quadrupletSum();// O(n^2)

        // int ans = countTwoSumLessThanX();
        int ans = countThreeSumLessThanX();
        System.out.println(ans);
    }

    /******************** SET1 IP BATCH *********************************/
    public static int binarySearch(int[] arr, int si, int ei, int tar) {
        while (si <= ei) {
            int mid = (si + (ei - si) / 2);
            if (arr[mid] == tar)
                return mid;
            else if (arr[mid] < tar)
                si = mid + 1;
            else
                ei = mid - 1;
        }
        return -1;
    }

    public static int capacityToShipWithinKDays() {
        // input
        int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        int D = 5;

        int lo = Integer.MIN_VALUE;// min capacity
        int hi = 0;// max capacity
        int ans = -1;
        for (int ele : arr) {
            lo = Math.max(lo, ele);
            hi += ele;
        }

        while (lo <= hi) {
            int cap = (lo + (hi - lo) / 2);
            int d = countDays(arr, cap);// days to ship with "cap" capacity

            if (d <= D) {
                ans = cap;
                hi = cap - 1;
            } else {
                lo = cap + 1;
            }

        }
        return ans; // we can also return lo
    }

    public static int countDays(int[] arr, int cap) {
        int ccap = 0, days = 1;
        for (int ele : arr) {
            ccap += ele;
            if (ccap > cap) {
                days++;
                ccap = ele;
            }
        }
        return days;
    }

    public static int splitArrayMinimizeLargestSum() {
        int[] arr = { 7, 2, 5, 10, 8 };
        int m = 2;
        int lo = Integer.MIN_VALUE;// min capacity
        int hi = 0;// max capacity
        int ans = -1;
        for (int ele : arr) {
            lo = Math.max(lo, ele);
            hi += ele;
        }

        while (lo <= hi) {
            int sum = (lo + (hi - lo) / 2);
            int n = countSubarrays(arr, sum);// subarrays division

            if (n <= m) {
                ans = sum;
                hi = sum - 1;
            } else {
                lo = sum + 1;
            }

        }
        return ans;// return lo also works because lo will be at the leftmost favourable value
    }

    public static int countSubarrays(int[] arr, int sum) {
        int csum = 0, n = 1;
        for (int ele : arr) {
            csum += ele;
            if (csum > sum) {
                n++;
                csum = ele;
            }
        }
        return n;
    }

    public static int kokoEatingBananas() {
        int[] arr = { 30, 11, 23, 4, 20 };
        int h = 5;

        int lo = 1;
        int hi = Integer.MIN_VALUE;
        for (int ele : arr)
            hi = Math.max(hi, ele);

        int ans = Integer.MAX_VALUE;
        while (lo <= hi) {
            int cap = (lo + (hi - lo) / 2);
            int hours = countHours(arr, cap);

            if (hours <= h) {
                ans = cap;
                hi = cap - 1;
            } else {
                lo = cap + 1;
            }
        }
        return ans;
    }

    public static int countHours(int[] arr, int cap) {
        int hours = 0;
        for (int ele : arr) {
            hours += (ele / cap);
            hours += (ele % cap == 0) ? 0 : 1;
            /*
             * hours+=Math.ceil((ele*1.0)/cap);
             */
        }
        return hours;
    }

    public static int smallestDivisorGivenThreshold() {
        int[] arr = { 1, 2, 5, 9 };
        int th = 6;

        int lo = 1;
        int hi = Integer.MIN_VALUE;
        for (int ele : arr)
            hi = Math.max(hi, ele);

        int ans = Integer.MAX_VALUE;
        while (lo <= hi) {
            int div = (lo + (hi - lo) / 2);
            int sum = sumOfDiv(arr, div);

            if (sum <= th) {
                ans = sum;
                hi = sum - 1;
            } else {
                lo = sum + 1;
            }
        }
        return ans;
    }

    public static int sumOfDiv(int[] arr, int cap) {
        int sum = 0;
        for (int ele : arr) {
            sum += (ele / cap);
            sum += (ele % cap == 0) ? 0 : 1;
            /*
             * sum+=Math.ceil((ele*1.0)/cap);
             */
        }
        return sum;
    }

    public static int paintersProblem() {
        int[] arr = { 1, 8, 11, 3 };
        int a = 10;
        int b = 1;
        // minimize the unit of paints that 1 painter does
        int lo = Integer.MIN_VALUE;
        int hi = 0;
        int ans = -1;
        for (int ele : arr) {
            lo = Math.max(lo, ele);
            hi += ele;
        }
        while (lo <= hi) {
            int unit = (lo - (hi - lo) / 2);
            int painter = countPainters(arr, unit);

            if (painter <= a) {
                hi = unit - 1;
            } else {
                lo = unit + 1;
            }
        }
        return lo * b;
    }

    public static int countPainters(int[] arr, int unit) {
        int cunit = 0, painters = 1;
        for (int ele : arr) {
            cunit += ele;
            if (cunit > unit) {
                painters++;
                cunit = ele;
            }
        }
        return painters;
    }

    public static class MedianFinder {
        PriorityQueue<Integer> maxpq = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minpq = new PriorityQueue<>();

        /** initialize your data structure here. */
        public MedianFinder() {

        }

        public void addNum(int val) {
            if (minpq.size() == maxpq.size()) {
                maxpq.add(val);
                minpq.add(maxpq.poll());
            } else {
                minpq.add(val);
                maxpq.add(minpq.poll());
            }
        }

        public double findMedian() {
            return (minpq.size() == maxpq.size() ? (((double) minpq.peek() + (double) maxpq.peek()) / 2)
                    : (double) minpq.peek());

        }
    }

    public static class MedianFinderFollowUp {
        // elements will be in between 0 and 100
        int[] arr = new int[101];
        int count = 0;

        /** initialize your data structure here. */
        public MedianFinderFollowUp() {

        }

        public void addNum(int val) {
            // simply update arr
            arr[val]++;
            count++;
        }

        public double findMedian() {
            int ccount = 0;
            int mid = (count + 1) / 2;
            int i = 0;
            while (true) {
                ccount += arr[i];
                if (ccount >= mid)
                    break;
                i++;
            }
            // odd count of elements
            if (count % 2 != 0)
                return (double) i;
            // even count of element
            int j = i + 1;
            while (ccount < mid + 1) {
                ccount += arr[j];
                j++;
            }
            j--;
            return ((double) i + (double) j) / 2;
        }
    }

    public static int[] kthSmallestPrimeFraction() {
        int[] arr = { 1, 2, 3, 5, 7 };
        int k = 3;
        int n = arr.length;
        double lo = 0.0, hi = 1.0;
        int p = 0, q = 0;
        while (true) {
            double mid = (lo + hi) / 2;
            // find the prime fraction having value less than eqaul to mid
            int count = 0;
            double maxFrac = 0.0;
            int j = 1;
            for (int i = 0; i < n - 1; i++) {
                while (j < n && arr[i] > (arr[j] * mid)) {
                    j++;
                }
                count += (n - j);
                if (j == n)
                    break;
                double currFrac = ((double)arr[i] / (double)arr[j]);

                if (currFrac > maxFrac) {
                    p = i;
                    q = j;
                    maxFrac = currFrac;
                }
            }
            if (count == k) {
                return new int[] { arr[p], arr[q] };
            } else if (count > k) {
                hi = mid;
            } else {
                lo = mid;
            }
        }
        // return new int[] { -1, -1 };
    }

    /************** SET3 LEETCODE + MISC *********************/

    public static int majorityElement1() {
        int[] arr = { 2, 2, 1, 1, 1, 2, 2 };
        int count = 0, val = 0;
        for (int ele : arr) {
            if (count == 0) {
                val = ele;
                count++;
            } else {
                if (ele == val) {
                    count++;
                } else {
                    count--;
                }
            }
        }
        if (count == 0)
            return -1;
        return val;
    }

    public static List<Integer> majorityElement2() {
        int[] arr = { 3, 2, 3 };
        int f1 = 0, f2 = 0, v1 = Integer.MAX_VALUE, v2 = Integer.MIN_VALUE;
        // creating answer
        for (int ele : arr) {
            if (ele == v1) {
                f1++;
            } else if (ele == v2) {
                f2++;
            } else if (f1 == 0) {
                v1 = ele;
                f1 = 1;
            } else if (f2 == 0) {
                v2 = ele;
                f2 = 1;
            } else {
                f1--;
                f2--;
            }
        }
        // counting frequency for answer elements
        int ct1 = 0;
        int ct2 = 0;
        for (int ele : arr) {
            if (ele == v1)
                ct1++;
            if (ele == v2)
                ct2++;
        }
        List<Integer> ans = new ArrayList<>();

        // checking if answer is correct
        if (ct1 > (arr.length / 3))
            ans.add(v1);
        if (ct2 > (arr.length / 3))
            ans.add(v2);
        return ans;
    }

    public static class majPair {
        int ele = 0;
        int count = 0;

        public majPair(int ele, int count) {
            this.ele = ele;
            this.count = count;
        }

        public majPair() {

        }
    }

    public static List<Integer> majorityElementGeneral() {
        int[] arr = { 3, 1, 2, 2, 1, 2, 3, 3 };
        int k = 4;
        List<Integer> ans = new ArrayList<>();
        majPair[] help = new majPair[k - 1];
        for (int i = 0; i < k - 1; i++) // initializing all indices
            help[i] = new majPair();

        for (int ele : arr) {
            int j = 0;
            // if ele is already present then update freq
            for (j = 0; j < k - 1; j++) {
                majPair p = help[j];
                if (p.ele == ele) {
                    p.count++;
                    break;
                }
            }
            // if ele is not present but someone has '0' count i.e empty space then put
            // count and ele
            int l = 0;
            if (j == k - 1) {
                for (l = 0; l < k - 1; l++) {
                    majPair p = help[l];
                    if (p.count == 0) {
                        p.ele = ele;
                        p.count = 1;
                        break;
                    }
                }
            }

            // if ele is not present and no empty space for new element so reduce every
            // count by '1'
            if (l == k - 1) {
                for (int m = 0; m < k - 1; m++) {
                    majPair p = help[l];
                    p.count--;
                }
            }
        }

        // check which of the answers are valid means > n/k
        for (int i = 0; i < k - 1; i++) {
            majPair p = help[i];
            int count = 0;
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] == p.ele)
                    count++;
            }
            if (count > (arr.length / k))
                ans.add(p.ele);
        }
        return ans;
    }

    public static int searchWhereAdjDiffIsAtMostK() {
        int[] arr = { 4, 5, 6, 7, 6 };
        int k = 1;
        int val = 6;
        int i = 0;
        while (i < arr.length) {
            if (arr[i] == val)
                return i;
            int jump = Math.max(1, Math.abs(arr[i] - val) / k);
            i += jump;
        }
        return -1;
    }

    public static List<Integer> commonInThreeSortedArray() {
        int[] a1 = { 1, 5, 10, 20, 40, 80 };
        int[] a2 = { 6, 7, 20, 80, 100 };
        int[] a3 = { 3, 4, 15, 20, 30, 70, 80, 120 };
        List<Integer> ans = new ArrayList<>();
        int a = 0, b = 0, c = 0;
        while (a < a1.length && b < a2.length && c < a3.length) {
            if (a1[a] == a2[b] && a2[b] == a3[c]) {
                ans.add(a1[a]);
                a++;
                b++;
                c++;
            } else if (a1[a] < a2[b])
                a++;
            else if (a2[b] < a3[c])
                b++;
            else
                c++;
        }
        return ans;
    }

    public static List<List<Integer>> twoSum() {
        int[] arr = { -1, 0, 1, 2 };
        int tar = 1;
        List<List<Integer>> ans = new ArrayList<>();
        int si = 0, ei = arr.length - 1;
        Arrays.sort(arr);
        while (si < ei) {
            int sum = arr[si] + arr[ei];
            // remove duplicacy
            if (si > 0 && arr[si] == arr[si - 1]) {
                si++;
                continue;
            } else if (ei < arr.length - 1 && arr[ei] == arr[ei + 1]) {
                ei--;
                continue;
            }
            // actual answer creation
            if (sum == tar) {
                ans.add(Arrays.asList(arr[si], arr[ei]));
                si++;
                ei--;
            } else if (sum < tar) {
                si++;
            } else {
                ei--;
            }
        }
        return ans;
    }

    public static List<List<Integer>> twoSumMap() {
        int[] arr = { -1, 0, 1, 2 };
        int tar = 1;
        List<List<Integer>> ans = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int val = map.getOrDefault(arr[i], 0);
            int diff = map.getOrDefault(tar - arr[i], 0);
            if (val < 0 || diff < 0) {
                continue;
            }
            if (diff > 0) {
                ans.add(Arrays.asList(arr[i], tar - arr[i]));
                map.put(arr[i], -1);
                map.put(tar - arr[i], -1);
            } else {
                map.put(arr[i], 1);
            }
        }
        return ans;
    }

    public static List<List<Integer>> threeSum() {
        int[] arr = { -1, 0, 1, 2, -1, -4 };
        int tar = 0;
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            if (i > 0 && arr[i] == arr[i - 1])
                continue;
            twoSumForThreeSum(arr, i + 1, arr[i], tar - arr[i], ans);
        }
        return ans;
    }

    public static void twoSumForThreeSum(int[] arr, int i, int v, int tar, List<List<Integer>> ans) {
        int si = i, ei = arr.length - 1;
        while (si < ei) {
            int sum = arr[si] + arr[ei];
            // remove duplicacy
            if (si > i && arr[si] == arr[si - 1]) {
                si++;
                continue;
            } else if (ei < arr.length - 1 && arr[ei] == arr[ei + 1]) {
                ei--;
                continue;
            }
            // actual answer creation
            if (sum == tar) {
                ans.add(Arrays.asList(v, arr[si], arr[ei]));
                si++;
                ei--;
            } else if (sum < tar) {
                si++;
            } else {
                ei--;
            }
        }
    }

    public static List<List<Integer>> fourSum() {
        int[] arr = { 1, 0, -1, 0, -2, 2 };
        int tar = 0;
        Arrays.sort(arr);
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (i > 0 && arr[i] == arr[i - 1])
                continue;
            for (int j = i + 1; j < arr.length; j++) {
                if (j > i + 1 && arr[j] == arr[j - 1])
                    continue;
                twoSumForFourSum(arr, j + 1, arr[i], arr[j], tar - arr[i] - arr[j], ans);
            }
        }
        return ans;
    }

    public static void twoSumForFourSum(int[] arr, int i, int v1, int v2, int tar, List<List<Integer>> ans) {
        int si = i, ei = arr.length - 1;
        while (si < ei) {
            int sum = arr[si] + arr[ei];
            // remove duplicacy
            if (si > i && arr[si] == arr[si - 1]) {
                si++;
                continue;
            } else if (ei < arr.length - 1 && arr[ei] == arr[ei + 1]) {
                ei--;
                continue;
            }
            // actual answer creation
            if (sum == tar) {
                ans.add(Arrays.asList(v1, v2, arr[si], arr[ei]));
                si++;
                ei--;
            } else if (sum < tar) {
                si++;
            } else {
                ei--;
            }
        }
    }

    public static class quadclass {
        int i = 0;
        int j = 0;

        public quadclass(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public static List<List<Integer>> quadrupletSum() {
        int[] arr = { 1, 0, -1, 0, -2, 2 };
        int tar = 0;
        HashSet<List<Integer>> set = new HashSet<>();
        HashMap<Integer, List<quadclass>> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                int sum = arr[i] + arr[j];
                if (!map.containsKey(sum))
                    map.put(sum, new ArrayList<>());
                map.get(sum).add(new quadclass(i, j));
            }
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                int sum = arr[i] + arr[j];
                List<quadclass> list = map.get(tar - sum);
                if (list != null && list.size() != 0) {
                    for (int p = 0; p < list.size(); p++) {
                        quadclass pair = list.get(p);
                        // check for overlapping
                        int k = pair.i;
                        int l = pair.j;
                        if (i != k && i != l && j != k && j != l) {
                            List<Integer> srt = new ArrayList<>();
                            srt.add(arr[i]);
                            srt.add(arr[j]);
                            srt.add(arr[k]);
                            srt.add(arr[l]);
                            Collections.sort(srt);
                            set.add(srt);
                        }
                        // check for duplicacy
                    }
                }
            }
        }
        return new ArrayList<>(set);
    }

    public static int countTwoSumLessThanX() {
        int[] arr = { 1, 2, 3, 4, 5, 6 };
        int tar = 7;
        int ans = 0;
        Arrays.sort(arr);
        int lo = 0, hi = arr.length - 1;
        while (lo < hi) {
            if (arr[lo] + arr[hi] < tar) {
                ans += (hi - lo);
                lo++;
            } else {
                hi--;
            }
        }
        return ans;
    }

    public static int countThreeSumLessThanX() {
        int[] arr = { 5, 1, 3, 4, 7 };
        int tar = 12;
        int ans = 0;
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            int j = i + 1, k = arr.length - 1;
            while (j < k) {
                if (arr[i] + arr[j] + arr[k] < tar) {
                    ans += (k - j);
                    j++;
                } else {
                    k--;
                }
            }
        }
        return ans;
    }

}