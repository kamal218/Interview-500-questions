import java.util.*;

import javax.lang.model.util.ElementScanner6;

public class questions {
    public static void main(String[] args) {
        solve();
    }

    public static void solve() {
        set1();// pepcoding youtube
        set2();// pepcodng IP Batch
        set3();// Leetcode
    }

    public static void set1() {

        // int ans=kThLargestElementInArraySort();//o(nlog(n))+O(1)
        // int ans = kThLargestElementInArraySortDontCountDup();//o(nlog(n))+O(1)
        // int ans = kThLargestElementInArrayMaxHeap();//o(nlog(n))+O(n)
        // int ans = kThLargestElementInArrayMinHeap();//o(nlog(k))+O(log(k))
        // int ans = kthLargestElementInArrayNoSpace();//o(nlog(k))+O(1)

        // sortNearlySortedArray();

        // medianInAStreamTwoHeap();

        // mergeKSortedList();

        // System.out.println(ans);
    }

    public static void set2() {
        // kClosestPQ(points, K)
        // kClosestSort(points, k)
        // minimumRefuelingStations();
        // String ans = rearrangeSuchThatNoTwoAreSame();
        // String ans = rearrangeSuchThatNoKAreSame();
        // int ans=trappingRainWaterTwoArray();
        // int ans=trappingrainWaterNoSpace();
        // int ans = trappingRainWaterThreeD();
        // int ans = kthSmallestElementInSortedMatrix();
        int[] ans = kthSmallestPrimeFraction();
        System.out.println(ans[0] + "" + ans[1]);
    }

    public static void set3() {
        // kthSmallestElementInSortedMatrix();
        // topKFrequentWords();

    }

    public static int kThLargestElementInArraySort() {
        int[] arr = { 1, 2, 2, 2, 3 };
        int k = 3;
        int len = arr.length;
        Arrays.sort(arr);
        if (k > len)
            return -1;
        return len - k;
    }

    public static int kThLargestElementInArraySortDontCountDup() {
        int[] arr = { 1, 2, 2, 2, 3 };
        int k = 3;
        int len = arr.length;
        Arrays.sort(arr);
        int kmothmin_ = -1;
        for (int i = len - 1; i >= 0; i--) {
            if (k == 0)
                break;
            if (arr[i] != kmothmin_) {
                k--;
                kmothmin_ = arr[i];
            }
        }
        return k == 0 ? kmothmin_ : -1;
    }

    public static int kThLargestElementInArrayMaxHeap() {
        int[] arr = { 1, 2, 2, 2, 3 };
        int k = 5;
        int len = arr.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());// max heap
        for (int ele : arr)
            pq.add(ele);
        while (k-- > 1) {
            pq.poll();
        }
        return pq.peek();
    }

    public static int kThLargestElementInArrayMinHeap() {
        int[] arr = { 1, 2, 2, 2, 3 };
        int k = 4;
        int len = arr.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>();// min heap
        for (int ele : arr) {
            pq.add(ele);
            if (pq.size() > k)
                pq.poll();
        }
        return pq.peek();
    }

    public static int kthLargestElementInArrayNoSpace() {
        int[] arr = { 1, 2, 2, 2, 3 };
        int k = 1;
        int len = arr.length;
        int si = 0, ei = len - 1;
        int nidx = len - k;
        while (si <= ei) {
            int pivot = partition(arr, si, ei);
            if (pivot == nidx)
                return arr[pivot];
            else if (pivot < nidx)
                si = pivot + 1;
            else
                ei = pivot - 1;
        }
        return -1;
    }

    public static int partition(int[] arr, int si, int ei) {
        int pivot = arr[ei];
        int i = si, j = si;
        while (j <= ei) {
            if (arr[j] <= pivot) {
                swap(arr, i, j);
                i++;
            }
            j++;
        }
        return i - 1;
    }

    public static void sortNearlySortedArray() {
        int[] arr = { 2, 3, 1, 4, 6, 7, 5, 8, 9 };
        int k = 2;
        int len = arr.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i <= k; i++)
            pq.add(arr[i]);
        for (int i = k + 1; i < len; i++) {
            arr[i - k - 1] = pq.poll();
            pq.add(arr[i]);
        }
        while (pq.size() != 0) {
            arr[len - k - 1] = pq.poll();
            k--;
        }
        displayArray(arr);
    }

    public static double medianInAStreamTwoHeap(int val) {
        PriorityQueue<Integer> maxpq = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minpq = new PriorityQueue<>();
        minpq.add(val);// adding in in pq
        maxpq.add(minpq.poll());// balancing
        if (maxpq.size() - minpq.size() > 1)
            minpq.add(maxpq.poll()); // maintain size
        return (minpq.size() == maxpq.size() ? (((double) minpq.peek() + (double) maxpq.peek()) / 2)
                : (double) maxpq.peek());
    }

    // public ListNode mergeKLists(ListNode[] lists) {
    // if (lists.length == 0)
    // return null;
    // PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> {
    // return a.val - b.val;
    // });
    // ListNode dummy = new ListNode(-1);
    // for (int i = 0; i < lists.length; i++) {
    // if (lists[i] != null)
    // pq.add(lists[i]);
    // }
    // ListNode curr = dummy;
    // while (pq.size() != 0) {
    // ListNode top = pq.remove();
    // curr.next = top;
    // curr = curr.next;
    // if (top.next != null)
    // pq.add(top.next);
    // }
    // return dummy.next;
    // }

    public static int[][] kClosestPQ(int[][] points, int K) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (a, b) -> ((b[0] * b[0] + b[1] * b[1]) - (a[0] * a[0] + a[1] * a[1])));
        for (int[] ar : points) {
            pq.add(ar);
            if (pq.size() > K)
                pq.poll();
        }
        int[][] ans = new int[K][2];
        int i = K - 1;
        while (pq.size() != 0) {
            int[] temp = pq.poll();
            ans[i][0] = temp[0];
            ans[i][1] = temp[1];
            i--;
        }
        return ans;
    }

    public static int[][] kClosestSort(int[][] points, int k) {
        Arrays.sort(points, (a, b) -> (a[0] * a[0] + a[1] * a[1]) - (b[0] * b[0] + b[1] * b[1]));
        int[][] ans = new int[k][2];
        for (int i = 0; i < k; i++) {
            ans[i][0] = points[i][0];
            ans[i][1] = points[i][1];
        }
        return ans;
    }

    public static class CFPair {
        Character ch;
        int freq = 0;

        public CFPair(Character ch, int freq) {
            this.ch = ch;
            this.freq = freq;
        }
    }

    public static String rearrangeSuchThatNoTwoAreSame() {
        String input = "aaabc";
        StringBuilder sb = new StringBuilder();
        int[] map = new int[26];
        for (char ch : input.toCharArray()) {
            map[ch - 'a']++;
        }
        PriorityQueue<CFPair> pq = new PriorityQueue<>((a, b) -> (b.freq - a.freq));
        for (int i = 0; i < 26; i++) {
            if (map[i] != 0) {
                pq.add(new CFPair((char) ('a' + i), map[i]));
            }
        }
        CFPair temp = pq.poll();
        sb.append(temp.ch);
        temp.freq--;
        while (pq.size() != 0) {
            CFPair mPair = pq.poll();
            sb.append(mPair.ch);
            mPair.freq--;
            if (temp.freq != 0)
                pq.add(temp);
            temp = mPair;
        }
        if (temp.freq > 0)
            return "";
        return sb.toString();
    }

    public static String rearrangeSuchThatNoKAreSame() {
        String input = "aaabc";
        int k = 2;
        StringBuilder sb = new StringBuilder();
        int[] map = new int[26];
        for (char ch : input.toCharArray()) {
            map[ch - 'a']++;
        }
        PriorityQueue<CFPair> pq = new PriorityQueue<>((a, b) -> (b.freq - a.freq));
        for (int i = 0; i < 26; i++) {
            if (map[i] != 0) {
                pq.add(new CFPair((char) ('a' + i), map[i]));
            }
        }
        Queue<CFPair> que = new LinkedList<>();
        for (int i = 0; i < k - 1; i++) {
            CFPair temp = pq.poll();
            sb.append(temp.ch);
            temp.freq--;
            que.add(temp);
        }
        while (pq.size() != 0) {
            CFPair temp = pq.poll();
            sb.append(temp.ch);
            temp.freq--;
            CFPair queTop = que.poll();
            if (queTop.freq != 0)
                pq.add(queTop);
            que.add(temp);
        }
        while (que.size() != 0) {
            if (que.poll().freq != 0)
                return "";
        }
        return sb.toString();
    }

    public static int trappingRainWaterTwoArray() {
        int[] h = {};
        int[] lm = new int[h.length];
        int[] rm = new int[h.length];
        int ans = 0;
        int lMax = Integer.MIN_VALUE;
        int rMax = Integer.MIN_VALUE;
        for (int i = 0; i < h.length; i++) {
            lm[i] = Math.max(h[i], lMax);
            lMax = lm[i];
        }
        for (int i = h.length - 1; i >= 0; i--) {
            rm[i] = Math.max(h[i], rMax);
            rMax = rm[i];
        }
        for (int i = 0; i < h.length; i++) {
            ans += Math.min(lm[i], rm[i]) - h[i];
        }
        return ans;
    }

    public static int trappingrainWaterNoSpace() {
        int[] height = {};
        if (height.length == 0)
            return 0;
        int len = height.length;
        int lmax = height[0];
        int rmax = height[len - 1];
        int ans = 0;
        int si = 0;
        int ei = len - 1;
        while (si <= ei) {
            if (height[si] < height[ei]) {
                if (lmax > height[si]) {
                    ans += lmax - height[si];
                } else {
                    lmax = height[si];
                }
                si++;
            } else {
                if (rmax > height[ei]) {
                    ans += rmax - height[ei];
                } else {
                    rmax = height[ei];
                }
                ei--;
            }
        }
        return ans;
    }

    public static class fracPair {
        int i = 0;
        int j = 0;

        public fracPair(int i, int j) {
            this.i = i;
            this.j = j;
        }

    }

    public static int[] kthSmallestPrimeFraction() {
        int[] arr = { 1, 2, 3, 5, 7 };
        int k = 3;
        PriorityQueue<fracPair> pq = new PriorityQueue<>((a, b) -> (arr[a.i] * arr[b.j] - arr[a.j] * arr[b.i]));
        for (int i = 0; i < arr.length - 1; i++) {
            pq.add(new fracPair(i, arr.length - 1));
        }
        while (k != 1) {
            fracPair top = pq.poll();
            if (top.j - 1 != top.i) {
                pq.add(new fracPair(top.i, top.j - 1));
            }
            k--;
        }
        return new int[] { arr[pq.peek().i], arr[pq.peek().j] };
    }

    //////////////////////// SWAP FUNCTIONS/////////////////
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void swap01(int[] arr, int i, int j) {
        arr[i] = arr[i] - arr[j];
        arr[j] = arr[i] + arr[j];
        arr[i] = arr[j] - arr[i];
    }

    public static void swap02(int[] arr, int i, int j) {
        arr[i] = ((arr[i] + arr[j]) - (arr[i] = arr[j]));
    }

    public static void swap03(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    public static void displayArray(int[] arr) {
        for (int ele : arr) {
            System.out.print(ele + " ");
        }
        System.out.println();
    }

}