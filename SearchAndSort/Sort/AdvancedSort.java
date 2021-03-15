import java.util.*;

public class AdvancedSort {
    public static void main(String[] args) {
        // 1D ARRAY SORT
        // sortArray();

        // 2D ARRAY(MATRIX) SORTING
        // matrixSort();

        // STRING SORT
        // ApplicationOfCountSort();

        // QUESTIONS RELATED TO SORTING
        questions();
    }

    public static void sortArray() {
        // int[] arr = { -2, 4, 6, 35, 65, 25, 25, 34, -7, -8, 97, 22 };
        // insertionSort(arr);
        // selectionSort(arr);
        // bubbleSort(arr);
        // mergeSort(arr);
        // quickSort(arr);
        // countSort(arr);
        // radixSort(arr);
        // heapSort(arr);
        // ApplicationOfCountSort();
        // displayArray(arr);
    }

    public static void matrixSort() {
        // int[][] mat = { { 1, 6, 3 }, { 9, 5, 8 }, { 7, 4, 2 } };
        // sortAsOneD(mat);// sorted rows as well as columns
        // sortDiagonally(mat);
        // displayMatrix(mat);
    }

    public static void ApplicationOfCountSort() {
        // SORT DATES
        // String[] arr = { "12041996", "20101996", "05061997", "12041989", "11081987"
        // };
        // sortDates(arr);/
        // for (String s : arr)
        // System.out.println(s);

        // SORT STRING
        // sortString("bbccdefb#$^$#baa");

        // SORT STRING ARRAY
        // String[] arr = { "word", "world", "ward", "aka" };
        // sortStringArray(arr);
        // for (String s : arr)
        // System.out.println(s);
    }

    public static void questions() {
        // mergeTwoSortedArrays();//O(n*m)
        // mergeTwoSortedArraysEfficiently();// O((n+m)log(n+m))

        // restoreString();
        // incDecString();
        // canApBeFormedSort();
        // minSubsequenceMaxSum();
        // maxUnitsInTruck();
        // sortArrayByParityII_m1();
        // sortArrayByParityII_m2();
        // sortByBits();
        // sortByBitsCountSort();
        // averageSalary();
        // relativeSortArray();
        // frequencySort();
        // canFormArray();
        // maxWidthOfVerticalArea();
        // int ans = countInversion();
        // int ans = reversePairs();
        // System.out.println(ans);
    }

    public static void insertionSort(int[] arr) {
        // logic:for ith index array from 0 to i must be sorted
        int len = arr.length;
        for (int i = 1; i < len; i++) {
            int j = i - 1;
            while (j >= 0 && arr[j] > arr[j + 1]) {
                swap(arr, j + 1, j);
                j--;
            }
        }
    }

    public static void selectionSort(int[] arr) {
        // logic:find smallest element i onwards and swap with i
        int len = arr.length;
        for (int i = 0; i < arr.length; i++) {
            int minidx = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minidx])
                    minidx = j;
            }
            swap(arr, i, minidx);
        }
    }

    public static void bubbleSort(int[] arr) {
        // logic:bubble up the ith greatest element to the last ith position
        int len = arr.length;
        boolean flag = false;
        for (int i = len - 1; i > 0; i--) {
            flag = false;
            for (int j = 1; j <= i; j++) {
                if (arr[j] < arr[j - 1]) {
                    swap(arr, j, j - 1);
                    flag = true;
                }
            }
            if (!flag)// already sorted
                break;
        }
    }

    public static void mergeSort(int[] arr) {
        // logic:call mergesort for two halfs and then merge them
        mergeSortHelp(arr, 0, arr.length - 1);
    }

    public static void mergeSortHelp(int[] arr, int si, int ei) {
        if (si >= ei)
            return;
        int mid = (si + (ei - si) / 2);
        mergeSortHelp(arr, si, mid);
        mergeSortHelp(arr, mid + 1, ei);
        mergeSortedArrays(arr, si, mid, ei);
    }

    public static void mergeSortedArrays(int[] arr, int si, int mid, int ei) {
        int[] left = new int[mid - si + 1];
        for (int i = 0; i < left.length; i++) {
            left[i] = arr[si + i];
        }
        int[] right = new int[ei - mid];
        for (int i = 0; i < right.length; i++) {
            right[i] = arr[mid + 1 + i];
        }
        int i = 0, j = 0, k = si;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
            } else {
                arr[k] = right[j];
                j++;
            }
            k++;
        }
        while (i < left.length) {
            arr[k] = left[i];
            i++;
            k++;
        }
        while (j < right.length) {
            arr[k] = right[j];
            j++;
            k++;
        }
    }

    public static void mergeInPlace(int[] arr, int si, int mid, int ei) {
        int s1 = si;
        int s2 = mid + 1;
        while (s1 <= mid && s2 <= ei) {
            if (arr[s1] <= arr[s2]) {
                s1++;
            } else {
                int pt = s2;
                int v = arr[pt];
                while (pt != s1) {
                    arr[pt] = arr[pt - 1];
                    pt--;
                }
                arr[s1] = v;
                s1++;
                s2++;
                mid++;
            }
        }
    }

    public static void quickSort(int[] arr) {
        // logic:put an element at its correct poition and sort left and right to this
        // element
        quickSortHelp(arr, 0, arr.length - 1);
    }

    public static void quickSortHelp(int[] arr, int si, int ei) {
        if (si >= ei)
            return;
        int pivot = partitionQuickSort(arr, si, ei);
        quickSortHelp(arr, si, pivot - 1);
        quickSortHelp(arr, pivot + 1, ei);
    }

    public static int partitionQuickSort(int[] arr, int si, int ei) {
        int pivot = arr[ei];
        int i = si, j = si;
        while (j < arr.length) {
            if (arr[j] <= pivot) {
                swap(arr, i, j);
                i++;
                j++;
            } else {
                j++;
            }
        }
        return i - 1;
    }

    public static void countSort(int[] arr) {
        int min_ = Integer.MAX_VALUE, max_ = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min_)
                min_ = arr[i];
            if (arr[i] > max_)
                max_ = arr[i];
        }
        int[] freq = new int[max_ - min_ + 1];
        for (int i = 0; i < arr.length; i++) {
            freq[arr[i] - min_]++;
        }
        for (int i = 1; i < freq.length; i++) {
            freq[i] += freq[i - 1];
        }
        int[] ans = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            int idx = freq[arr[i] - min_] - 1;
            ans[idx] = arr[i];
            freq[arr[i] - min_]--;
        }
        for (int i = 0; i < ans.length; i++)
            arr[i] = ans[i];
    }

    public static void radixSort(int[] arr) {
        int max_ = Integer.MIN_VALUE;
        for (int ele : arr) {
            if (max_ < ele)
                max_ = ele;
        }
        int exp = 1;
        while (exp < max_) {
            countSortForRadix(arr, exp);
            exp *= 10;
        }
    }

    public static void countSortForRadix(int[] arr, int e) {

        int[] freq = new int[10];
        for (int i = 0; i < arr.length; i++) {
            freq[arr[i] / e % 10]++;
        }
        for (int i = 1; i < freq.length; i++) {
            freq[i] = freq[i - 1] + freq[i];
        }
        int[] ans = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            int idx = freq[arr[i] / e % 10] - 1;
            ans[idx] = arr[i];
            freq[arr[i] / e % 10]--;
        }
        for (int i = 0; i < ans.length; i++)
            arr[i] = ans[i];
    }

    public static void heapSort(int[] arr) {
        // convert array into heap
        for (int i = arr.length - 1; i >= 0; i--) {
            downHeapify(arr, i, arr.length);
        }
        // convert heap array into sorted array
        for (int i = 0; i < arr.length; i++) {
            swap(arr, 0, arr.length - i - 1);
            downHeapify(arr, 0, arr.length - i - 1);
        }
    }

    public static void downHeapify(int[] arr, int pi, int ei) {
        int lc = (pi * 2) + 1;
        int rc = (pi * 2) + 2;
        int idx = pi;
        if (lc < ei && arr[idx] < arr[lc])
            idx = lc;
        if (rc < ei && arr[idx] < arr[rc])
            idx = rc;
        if (idx != pi) {
            swap(arr, pi, idx);
            downHeapify(arr, idx, ei);
        }
    }

    public static void sortDates(String[] arr) {
        countSortForDate(arr, 1000000, 100, 32);// sort date
        countSortForDate(arr, 10000, 100, 13);// sort month
        countSortForDate(arr, 1, 10000, 5000);// sort year
    }

    public static void countSortForDate(String[] arr, int div, int mod, int range) {
        int[] freq = new int[range];
        for (int i = 0; i < arr.length; i++) {
            freq[Integer.parseInt(arr[i], 10) / div % mod]++;
        }
        for (int i = 1; i < freq.length; i++) {
            freq[i] = freq[i - 1] + freq[i];
        }
        String[] ans = new String[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            int idx = freq[Integer.parseInt(arr[i], 10) / div % mod] - 1;
            ans[idx] = arr[i];
            freq[Integer.parseInt(arr[i], 10) / div % mod]--;
        }
        for (int i = 0; i < ans.length; i++)
            arr[i] = ans[i];
    }

    public static void sortString(String arr) {
        int[] freq = new int[256];
        for (int i = 0; i < arr.length(); i++) {
            freq[arr.charAt(i)]++;
        }
        for (int i = 1; i < freq.length; i++) {
            freq[i] = freq[i - 1] + freq[i];
        }
        char[] ans = new char[arr.length()];
        for (int i = arr.length() - 1; i >= 0; i--) {
            int idx = freq[arr.charAt(i)] - 1;
            ans[idx] = arr.charAt(i);
            freq[arr.charAt(i)]--;
        }
        System.out.println(String.valueOf(ans));
    }

    public static void sortStringArray(String[] arr) {
        int mLen = 0;
        for (String s : arr)
            if (mLen < s.length())
                mLen = s.length();
        for (int i = 0; i < mLen; i++) {
            countSortStringArray(arr, i);
        }
    }

    public static void countSortStringArray(String[] arr, int idx) {
        int[] freq = new int[27];
        for (int i = 0; i < arr.length; i++) {
            if (idx >= arr[i].length()) {
                freq[0]++;
            } else {
                freq[arr[i].charAt(idx) - 'a' + 1]++;
            }
        }
        for (int i = 1; i < freq.length; i++) {
            freq[i] = freq[i - 1] + freq[i];
        }
        String[] ans = new String[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            int vidx = freq[0] - 1;
            if (arr[i].length() > idx) {
                vidx = freq[arr[i].charAt(idx) - 'a' + 1] - 1;
                ans[vidx] = arr[i];
                freq[arr[i].charAt(idx) - 'a' + 1]--;
            } else {
                ans[vidx] = arr[i];
                freq[0]--;
            }
        }
        for (int i = 0; i < arr.length; i++)
            arr[i] = ans[i];
    }

    public static void sortAsOneD(int[][] mat) {
        int r = mat.length;
        int c = mat[0].length;
        // insertion sort
        for (int i = 0; i < r * c; i++) {
            for (int j = i - 1; j >= 0; j--) {
                int r1 = j / c, c1 = j % c;
                int r2 = (j + 1) / c, c2 = (j + 1) % c;
                if (mat[r1][c1] > mat[r2][c2]) {
                    swapInMatrix(mat, r1, c1, r2, c2);
                }
            }
        }
    }

    public static void sortDiagonally(int[][] mat) {
        // \\\\\\\\\\\\\\\\\\\\->sort on this digonal means same diff
        HashMap<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        int r = mat.length, c = mat[0].length;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (!map.containsKey(i - j))
                    map.put(i - j, new PriorityQueue<>());
                map.get(i - j).add(mat[i][j]);
            }
        }
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                mat[i][j] = map.get(i - j).poll();
            }
        }
    }

    public static void mergeTwoSortedArrays() {
        int[] arr1 = { 1, 5, 9, 10, 15, 20 };
        int[] arr2 = { 2, 3, 8, 13 };
        int i = 0, j = 0;
        while (i < arr1.length) {
            if (arr1[i] > arr2[j]) {
                int temp = arr1[i];
                arr1[i] = arr2[j];
                arr2[j] = temp;
                int k = j + 1;
                while (k < arr2.length && arr2[k - 1] > arr2[k]) {
                    swap(arr2, k - 1, k);
                    k++;
                }
            }
            i++;
        }
        for (int ele : arr1) {
            System.out.print(ele + " ");
        }
        for (int ele : arr2) {
            System.out.print(ele + " ");
        }
    }

    public static void mergeTwoSortedArraysEfficiently() {
        int[] arr1 = { 1, 5, 9, 10, 15, 20 };
        int[] arr2 = { 2, 3, 8, 13 };
        int l1 = arr1.length, l2 = arr2.length;
        int ol = l1 + l2;
        int gap = (ol / 2) + (ol % 2);
        while (gap > 0) {
            // sort left
            int i = 0;
            for (i = 0; i + gap < l1; i++) {
                if (arr1[i] >= arr1[i + gap]) {
                    arr1[i + gap] = ((arr1[i] + arr1[i + gap]) - (arr1[i] = arr1[i + gap]));
                }
            }
            // sort combination
            for (int k = i, l = 0; k < l1 && l < l2; k++, l++) {
                if (arr1[k] >= arr2[l]) {
                    arr2[l] = ((arr1[k] + arr2[l]) - (arr1[k] = arr2[l]));
                }
            }
            // sort right
            for (int j = 0; j + gap < l2; j++) {
                if (arr2[j] >= arr2[j + gap]) {
                    arr2[j + gap] = ((arr2[j] + arr2[j + gap]) - (arr2[j] = arr2[j + gap]));
                }
            }
            if (gap <= 1)
                gap = 0;
            else
                gap = (gap / 2) + (gap % 2);
        }
        for (int ele : arr1) {
            System.out.print(ele + " ");
        }
        for (int ele : arr2) {
            System.out.print(ele + " ");
        }
    }

    public static String restoreString(String s, int[] indices) {
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < indices.length; i++) {
            sb.setCharAt(indices[i], s.charAt(i));
        }
        return sb.toString();
    }

    public static String incDecString(String s) {
        int[] map = new int[26];
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++)
            map[s.charAt(i) - 'a']++;

        boolean flag = true;
        boolean inc = true;

        while (flag) {
            flag = false;
            if (inc) {
                for (int i = 0; i < 26; i++) {
                    if (map[i] != 0) {
                        sb.append((char) (i + 'a'));
                        map[i]--;
                        flag = true;
                        inc = false;
                    }
                }
            } else {
                for (int i = 25; i >= 0; i--) {
                    if (map[i] != 0) {
                        sb.append((char) (i + 'a'));
                        map[i]--;
                        flag = true;
                        inc = true;
                    }
                }
            }
        }

        return sb.toString();
    }

    public static boolean canApBeFormedSort(int[] arr) {
        if (arr.length < 3)
            return true;
        Arrays.sort(arr);
        boolean res = true;
        int gap = arr[1] - arr[0];
        for (int i = 2; i < arr.length; i++) {
            if (arr[i] - arr[i - 1] != gap)
                return false;
        }

        return res;
    }

    public static List<Integer> minSubsequenceMaxSum(int[] arr) {
        List<Integer> ans = new ArrayList<>();
        Arrays.sort(arr);
        int sum = 0;
        for (int ele : arr)
            sum += ele;
        int csum = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            csum += arr[i];
            ans.add(arr[i]);
            if (csum > sum - csum)
                break;
        }
        return ans;
    }

    public static int maxUnitsInTruck(int[][] box, int tsize) {
        Arrays.sort(box, (a, b) -> (b[1] - a[1]));
        int ans = 0;
        int i = 0;
        while (tsize > 0 && i < box.length) {
            if (tsize > box[i][0]) {
                ans += (box[i][0] * box[i][1]);
                tsize -= box[i][0];
            } else {
                ans += (tsize * box[i][1]);
                tsize = 0;
            }
            i++;

        }
        return ans;
    }

    public static int[] sortArrayByParityII_m1(int[] A) {
        int i = 0;
        int j = 1;
        int even = 0;
        int odd = 0;
        while (i < A.length && j < A.length) {
            even = (A[i] % 2);
            odd = (A[j] % 2);
            if (even != 0 && odd == 0) {// botheven and odd are at wrong places
                A[j] = ((A[i] + A[j]) - (A[i] = A[j]));
                i += 2;
                j += 2;
            } else if (even != 0) {// only even s wrong placed
                j += 2;
            } else {// only odd is wrong placed
                i += 2;
            }
        }
        return A;
    }

    public static int[] sortArrayByParityII_m2(int[] A) {
        int i = 0;
        int j = 1;
        int even = 0;
        int odd = 0;
        while (i < A.length) {
            if (A[i] % 2 == 1) {// wrong placed even position hence swap with 1st odd element at odd position
                while (A[j] % 2 == 1) {
                    j += 2;
                }
                A[j] = ((A[i] + A[j]) - (A[i] = A[j]));
            }
            i += 2;
        }
        return A;
    }

    public static int[] sortByBits(int[] arr) {
        int[][] map = new int[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            map[i][0] = arr[i];
            map[i][1] = countSetBit(arr[i]);
        }
        Arrays.sort(map, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[1] != b[1])
                    return a[1] - b[1];
                else
                    return a[0] - b[0];
            }
        });
        for (int i = 0; i < arr.length; i++) {
            arr[i] = map[i][0];
        }
        return arr;
    }

    public static int[] sortByBitsCountSort(int[] arr) {
        Arrays.sort(arr);// sort on the basis of value
        // count sort on the number of bits
        int[] map = new int[33];
        for (int ele : arr) {
            map[countSetBit(ele)]++;
        }
        for (int i = 1; i < map.length; i++) {
            map[i] += map[i - 1];
        }
        int[] ans = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            int set = countSetBit(arr[i]);
            int idx = map[set] - 1;
            ans[idx] = arr[i];
            map[set]--;
        }
        return ans;
    }

    public static int countSetBit(int n) {
        int set = 0;
        while (n != 0) {
            n = (n & (n - 1));
            set++;
        }
        return set;
    }

    public static double averageSalary(int[] salary) {
        int sum = 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int ele : salary) {
            sum += ele;
            if (ele < min)
                min = ele;
            if (ele > max)
                max = ele;
        }
        return (sum - min - max) * 1.0 / (salary.length - 2);
    }

    public static int[] relativeSortArray(int[] arr1, int[] arr2) {
        int[] map = new int[1001];
        for (int ele : arr1)
            map[ele]++;
        int k = 0;
        for (int ele : arr2) {
            while (map[ele] != 0) {
                arr1[k] = ele;
                k++;
                map[ele]--;
            }
        }
        for (int i = 0; i < map.length; i++) {
            if (map[i] > 0) {
                while (map[i] != 0) {
                    arr1[k] = i;
                    k++;
                    map[i]--;
                }
            }
        }
        return arr1;
    }

    public static int[] frequencySort(int[] arr) {
        int[][] map = new int[201][2];
        for (int ele : arr) {
            map[ele + 100][1]++;
            map[ele + 100][0] = ele;
        }
        Arrays.sort(map, (a, b) -> (b[1] - a[1]));
        int k = 0;
        for (int i = map.length - 1; i >= 0; i--) {
            while (map[i][1] > 0) {
                arr[k] = map[i][0];
                k++;
                map[i][1]--;
            }
        }
        return arr;
    }

    public static boolean canFormArray(int[] arr, int[][] pieces) {
        HashMap<Integer, Integer> map = new HashMap<>();
        // save p[0] with length
        for (int i = 0; i < pieces.length; i++) {
            map.put(pieces[i][0], i);
        }
        for (int i = 0; i < arr.length;) {
            if (map.containsKey(arr[i])) {
                int idx = map.get(arr[i]);
                int len = pieces[idx].length;
                i++;
                for (int j = 1; j < len; j++) {
                    if (pieces[idx][j] != arr[i])
                        return false;
                    i++;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    public static int maxWidthOfVerticalArea(int[][] points) {
        Arrays.sort(points, (a, b) -> (a[0] - b[0]));
        int ans = 0;
        for (int i = 1; i < points.length; i++) {
            int d = points[i][0] - points[i - 1][0];
            if (d > ans)
                ans = d;
        }
        return ans;
    }

    public static int countInversion() {
        int[] arr = { 3, 1, 2 };
        int ans = countInversion_(arr, 0, arr.length - 1);
        displayArray(arr);
        return ans;
    }

    public static int countInversion_(int[] arr, int si, int ei) {
        if (si >= ei)
            return 0;
        int count = 0;
        int mid = (si + (ei - si) / 2);
        count += countInversion_(arr, si, mid);
        count += countInversion_(arr, mid + 1, ei);
        count += mergeCountInverion(arr, si, mid, ei);
        return count;
    }

    public static int mergeCountInverion(int[] arr, int si, int mid, int ei) {
        int count = 0;
        int[] left = new int[mid - si + 1];
        for (int i = 0; i < left.length; i++) {
            left[i] = arr[si + i];
        }
        int[] right = new int[ei - mid];
        for (int i = 0; i < right.length; i++) {
            right[i] = arr[mid + 1 + i];
        }
        int i = 0, j = 0, k = si;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
            } else {
                count += (mid + 1) - (i + si);
                arr[k] = right[j];
                j++;
            }
            k++;
        }
        while (i < left.length) {
            arr[k] = left[i];
            i++;
            k++;
        }
        while (j < right.length) {
            arr[k] = right[j];
            j++;
            k++;
        }
        return count;
    }

    public static int reversePairs() {
        int[] arr = { 1, 3, 2, 3, 1 };
        return reversePairsCount(arr, 0, arr.length - 1);
    }

    public static int reversePairsCount(int[] arr, int si, int ei) {
        if (si >= ei)
            return 0;
        int mid = (si + (ei - si) / 2);
        int ans = 0;
        ans += reversePairsCount(arr, si, mid);
        ans += reversePairsCount(arr, mid + 1, ei);
        int i = si, j = mid + 1;
        while (i <= mid && j <= ei) {
            if ((long) arr[i] > ((long) arr[j]) * 2) {
                ans += (mid + 1) - (i);
                j++;
            } else {
                i++;
            }
        }
        reversePairsMerge(arr, si, mid, ei);
        return ans;
    }

    public static void reversePairsMerge(int[] arr, int si, int mid, int ei) {
        int[] left = new int[mid - si + 1];
        for (int i = 0; i < left.length; i++) {
            left[i] = arr[si + i];
        }
        int[] right = new int[ei - mid];
        for (int i = 0; i < right.length; i++) {
            right[i] = arr[mid + i + 1];
        }
        int i = 0, j = 0, k = si;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
            } else {
                arr[k] = right[j];
                j++;
            }
            k++;
        }
        while (i < left.length) {
            arr[k] = left[i];
            i++;
            k++;
        }
        while (j < right.length) {
            arr[k] = right[j];
            j++;
            k++;
        }

    }
    /////////// DISPLAY AND SWAP FUNCTIONS///////////

    public static void displayArray(int[] arr) {
        for (int ele : arr) {
            System.out.print(ele + " ");
        }
        System.out.println();
    }

    public static void displayMatrix(int[][] mat) {
        for (int[] a : mat) {
            for (int b : a) {
                System.out.print(b + " ");
            }
            System.out.println();
        }
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

    public static void swapInMatrix(int[][] mat, int i, int j, int k, int l) {
        mat[k][l] = ((mat[i][j] + mat[k][l]) - (mat[i][j] = mat[k][l]));
    }
}