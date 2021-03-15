import java.util.*;

public class sort {
    public static void main(String[] args) {
        int[] arr = { 15, 9, 7, 3, 12, 85, 75, 41, 68 };
        // swapping 4 methods are discussed at the end
        // selectionSort(arr);//best:O(n^2)+average:(O(n^2))+worst:(O(n^2))+space:O(1)+stable+inplace
        // display(arr);
        // bubbleSort(arr);//best for sorted
        // arrays//best:O(n)+average:(O(n^2))+worst:(O(n^2))+space:O(1)+stable+inplace
        // display(arr);
        // insertionSort(arr);//best for sorted
        // arrays////best:O(n)+average:(O(n^2))+worst:(O(n^2))+space:O(1)+stable+inplace
        // display(arr);
        // mergeSortOutPlace(arr);//best:O(nlog(n))+average:(O(nlog(n)))+worst:(O(nlog(n)))+space:O(n)+stable+outplace
        // Note: merge sort can be made inplace but time complexity will be O(n^2) in
        // worst case
        // display(arr);
        // mergeSortInPlace(arr);
        // display(arr);
        // int[] arr = { 1, 0, 0, 2, 2, 1, 1, 2, 0, 0, 2, 1 };
        // sortColors(arr);
        // partition(arr, arr[0]);
        // quickSortPartition(arr, 0, arr.length - 1);
        // display(arr);
        // quickSort(arr);best:O(nlog(n))+average:(O(nlog(n)))+worst:(O(n^2)+space:O(1)+unstable+inplace
        // display(arr);
        // heapSort(arr);best:O(nlog(n))+average:(O(nlog(n)))+worst:(O(nlog(n)))+space:O(1)+unstable+inplace
        // display(arr);
        // display(countSort(arr));best:O(n+k)+average:(O(n+k))+worst:(O(n+k))+space:O(n+k)+stable+outplace
        // Note: count sort is udsed o sort the arrays having less range so character
        // array can be sorted using count sort
        // radixSort(arr);
        // display(arr);

        // 2d matrix sort
        // sortMatrix();

    }

    public static void sortMatrix() {
        // sort like a 1D matrix / sorted rown as well as columns
        // sortMatrixAsOneD();
        // sortDiagonally();
    }

    public static void selectionSort(int[] arr) {
        int len = arr.length;
        int mIdx = 0;
        for (int i = 0; i < len - 1; i++) {
            mIdx = i;
            for (int j = i + 1; j < len; j++) {
                if (arr[j] < arr[mIdx])
                    mIdx = j;
            }
            if (i != mIdx)
                swap(arr, i, mIdx);
        }
    }

    public static void bubbleSort(int[] arr) {
        int len = arr.length;
        boolean call = false;
        for (int i = len - 1; i > 0; i--) {
            call = false;
            for (int j = 1; j <= i; j++) {
                if (arr[j] < arr[j - 1]) {
                    swap(arr, j, j - 1);
                    call = true;
                }
            }
            if (!call)
                return;
        }
    }

    public static void insertionSort(int[] arr) {
        int len = arr.length;
        for (int i = 1; i < len; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1])
                    swap(arr, j, j - 1);
                else
                    break;
            }
        }
    }

    public static void mergeSortOutPlace(int[] arr) {
        mergeSortHelp(arr, 0, arr.length - 1);
    }

    public static void mergeSortHelp(int[] arr, int si, int ei) {
        if (si >= ei)
            return;
        int mid = (si + ei) / 2;
        mergeSortHelp(arr, si, mid);
        mergeSortHelp(arr, mid + 1, ei);
        mergeOutPlace(arr, si, mid, ei);
    }

    public static void mergeOutPlace(int[] arr, int si, int mid, int ei) {
        int s1 = si;
        int s2 = mid + 1;
        int e1 = mid;
        int e2 = ei;
        int len1 = mid - si + 1;
        int len2 = ei - mid;
        int[] left = new int[len1]; // e1-s1+1
        int[] right = new int[len2]; // e2-s2+1
        for (int i = 0; i < len1; i++) {
            left[i] = arr[s1 + i];
        }
        for (int i = 0; i < len2; i++) {
            right[i] = arr[s2 + i];
        }
        // merge left and right in arr
        int idx = s1;
        int l = 0, r = 0;
        while (l < len1 && r < len2) {
            if (left[l] <= right[r]) {
                arr[idx] = left[l];
                l++;
            } else {
                arr[idx] = right[r];
                r++;
            }
            idx++;
        }
        while (l < len1) {
            arr[idx] = left[l];
            l++;
            idx++;
        }
        while (r < len2) {
            arr[idx] = right[r];
            r++;
            idx++;
        }
    }

    public static void mergeSortInPlace(int[] arr) {
        mergeSortHelp(arr, 0, arr.length - 1);
    }

    public static void mergeSortHelpInPlace(int[] arr, int si, int ei) {
        if (si >= ei)
            return;
        int mid = (si + ei) / 2;
        mergeSortHelpInPlace(arr, si, mid);
        mergeSortHelpInPlace(arr, mid + 1, ei);
        mergeInPlace(arr, si, mid, ei);
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

    public static void sortColors(int[] arr) {
        int one = 0, two = arr.length - 1;
        int pt = 0;
        while (pt <= two) {
            if (arr[pt] == 0) {
                swap(arr, pt, one);
                one++;
                pt++;
            } else if (arr[pt] == 1) {
                pt++;
            } else {
                swap(arr, pt, two);
                two--;
            }
        }
    }

    public static void partition(int[] arr, int val) {
        int pt = 0, i = 0;
        while (pt < arr.length) {
            if (arr[pt] <= val) {
                swap(arr, pt, i);
                i++;
                pt++;
            } else {
                pt++;
            }
        }
    }

    public static void quickSort(int[] arr) {
        quickSorthelp(arr, 0, arr.length - 1);
    }

    public static void quickSorthelp(int[] arr, int si, int ei) {
        if (si >= ei)
            return;
        int idx = quickSortPartition(arr, si, ei);
        quickSorthelp(arr, si, idx - 1);
        quickSorthelp(arr, idx + 1, ei);
    }

    public static int quickSortPartition(int[] arr, int si, int ei) {
        int pt = si, i = si;
        int val = arr[ei];
        while (pt <= ei) {
            if (arr[pt] <= val) {
                swap(arr, pt, i);
                i++;
                pt++;
            } else {
                pt++;
            }
        }
        return i;
    }

    public static void heapSort(int[] nums) {
        for (int i = nums.length - 1; i >= 0; i--) {
            downHeapify(nums, i, nums.length);
        }
        // for(int i=0;i<nums.length;i++)
        // System.out.print(nums[i]+" ");
        // sort the heap

        for (int i = 0; i < nums.length; i++) {
            swap(nums, 0, nums.length - i - 1);
            downHeapify(nums, 0, nums.length - i - 1);
        }
    }

    public static void downHeapify(int[] nums, int par, int end) {
        int lc = (par * 2) + 1;
        int rc = (par * 2) + 2;
        int idx = par;
        if (lc < end && nums[lc] > nums[idx])
            idx = lc;
        if (rc < end && nums[rc] > nums[idx])
            idx = rc;
        if (idx != par) {
            swap(nums, par, idx);
            downHeapify(nums, idx, end);
        }
    }

    public static int[] countSort(int[] nums) {
        int min_ = Integer.MAX_VALUE, max_ = Integer.MIN_VALUE;
        for (int ele : nums) {
            if (min_ > ele)
                min_ = ele;
            if (max_ < ele)
                max_ = ele;
        }
        int[] freq = new int[max_ - min_ + 1];
        for (int i = 0; i < nums.length; i++) {
            freq[nums[i] - min_]++;
        }
        for (int i = 1; i < freq.length; i++) {
            freq[i] = freq[i - 1] + freq[i];
        }
        int[] ans = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            int idx = freq[nums[i] - min_] - 1;
            ans[idx] = nums[i];
            freq[nums[i] - min_]--;
        }
        return ans;
    }

    public static void radixSort(int[] nums) {
        int max_ = Integer.MIN_VALUE;
        for (int ele : nums) {
            if (max_ < ele)
                max_ = ele;
        }
        int exp = 1;
        while (exp < max_) {
            countSortForRadix(nums, exp);
            exp *= 10;
        }
    }

    public static void countSortForRadix(int[] nums, int e) {

        int[] freq = new int[10];
        for (int i = 0; i < nums.length; i++) {
            freq[nums[i] / e % 10]++;
        }
        for (int i = 1; i < freq.length; i++) {
            freq[i] = freq[i - 1] + freq[i];
        }
        int[] ans = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            int idx = freq[nums[i] / e % 10] - 1;
            ans[idx] = nums[i];
            freq[nums[i] / e % 10]--;
        }
        for (int i = 0; i < ans.length; i++)
            nums[i] = ans[i];
    }

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

    public static void sortMatrixAsOneD() {
        int[][] mat = {};
        int r = mat.length;
        int c = mat[0].length;
        int mid_idx = 0;
        for (int i = 0; i < r * c; i++) {
            mid_idx = i;
            for (int j = i + 1; j < r * c; j++) {
                if (mat[j / c][j % c] < mat[mid_idx / c][mid_idx % c])
                    mid_idx = j;
            }
            if (mid_idx != i)
                swapM(mat, i / c, i % c, mid_idx / c, mid_idx % c);
        }
    }

    public static void swapM(int[][] arr, int i, int j, int ii, int jj) {
        arr[ii][jj] = ((arr[i][j] + arr[ii][jj]) - (arr[i][j] = arr[ii][jj]));
    }

    public static void sortDiagonally() {
        int[][] mat = {};
        HashMap<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (!map.containsKey(i - j))
                    map.put(i - j, new PriorityQueue<>());
                map.get(i - j).add(mat[i][j]);
            }
        }
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                mat[i][j] = map.get(i - j).remove();
            }
        }
    }

    public static void display(int[] arr) {
        for (int ele : arr) {
            System.out.print(ele + " ");
        }
        System.out.println();
    }
}