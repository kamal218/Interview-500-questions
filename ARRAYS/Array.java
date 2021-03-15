import java.util.HashMap;
import java.util.Arrays;
import java.util.Comparator;

public class Array {
    public static void main(String[] args) {
        questions();
    }

    public static void questions() {
        // question 1
        // method 1 O(n) + O(n*k)
        // int[] arr = { -1, 2, 1 };
        // System.out.println(kConcatination_01(arr, 5));
        // method 2 O(n) + O(1)
        // System.out.println(kConcatination_02(arr, k));

        // Question 2
        // method 1 O(n^2) + O(1)
        // int[] arr = { 10, 2, -14, 10 };
        // int tar = -12;
        // int[] ans;
        // ans = targetSumSubArray_01(arr, tar);
        // method 2 (only for +ve values) using sliding window O(n) + O(1)
        // int[] arr={1,2,3,4,5,6,7,8};
        // int tar=18;
        // int[] ans = targetSumSubArray_02(arr, tar);
        // method 3 using hashmap O(n) + O(n)
        // int[] arr = { 10, 2, -14, 10, -20, 6 };
        // int tar = -22;
        // int[] ans = targetSumSubArray_03(arr, tar);
        // method 3 tricky O(n) + O(1) (improved method 2)
        // int[] arr = { 10, 2, -14, 10, -20, 6 };
        // int tar = -22;
        // int[] ans = targetSumSubArray_04(arr, tar);
        // displayArray(ans);

        // Question 3 Equilibrium index
        // int[] arr = { -7, 1, 5, 2, -4, 3, 0 };
        // method 1 O(n^2)
        // System.out.println(equilIndex_01(arr));
        // System.out.println(equilIndex_02(arr));

        // Question 4 two sum
        // int[] arr = { 0, 1, 1, 0, 2, -1, -1 };
        // int sum = 1;
        // method 1 O(n^2) + O(1)
        // pairsWithGivenSum_01(arr, sum);
        // method 2 O(nlog(n)) + O(1) [Note:only for 1 pair && not applicable when
        // indices are needed]
        // pairsWithGivenSum_02(arr, sum);
        // method 3 using hashmap O(n) + O(n)
        // pairsWithGivenSum_03(arr, sum);
        // method 4 same as method 3 but with 1 for loop
        // pairsWithGivenSum_04(arr, sum);

        // Question 5 chocolate distribution
        // Method 1 O(nlog(n)) + O(1)
        // int[] arr = { 3, 4, 1, 9, 56, 7, 9, 12 };
        // System.out.println(chocolateDistribution(arr, 5));

        // Question 6(recent by google) sort(0.1.2, 1.1.2, 1.1.3.......)
        // String[] arr = { "1.1.0", "1.2.1", "0.9.1", "1.1.2", "1.3.4" };
        // SortList(arr);
        // displayStringArray(arr);

        // Question 7 Trapping Rain water
        // int[] arr = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
        // Method 1 O(n) + O(2n)
        // System.out.println(trapRainWater_01(arr));
        // Method 2 O(n) + O(1)
        // System.out.println(trapRainWater_02(arr));

        // Question 8 zig zag conversion of array
        // int[] arr = { 4, 3, 7, 8, 6, 2, 1 };
        // Method 1 O(nlog(n)) + O(1)
        // displayArray(zigZagCOnversion_01(arr));
        // Method 2 O(n) + O(n)
        // displayArray(zigZagCOnversion_02(arr));

        // Question 9 Segregate 0 and 1
        // Method 1 count 0s and 1s O(2n) + O(1)
        // int[] arr = { 0, 1, 0, 1, 0, 0, 1, 1, 1, 0 };
        // displayArray(segregate0And1_01(arr));
        // Method 2 using 2 ponters O(n) + O(1)
        // displayArray(segregate0And1_02(arr));

        // Question 10 segregate 0, 1 and 2
        // Method 1 count 0, 1 and 2 and same as segregate 0 and 1
        // Method 2
        // int[] arr = { 0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1 };
        // displayArray(segregate0And1And2_02(arr));

        // Question 11 Move zeroes to end
        // int[] arr = { 1, 9, 8, 4, 0, 0, 2, 7, 0, 6, 0 };
        // displayArray(moveZeroesToEnd(arr));

        // QUESTION 12 Search in a sorted rotated array (no duplicates)
        // int[] arr = { 3, 4, 5, 6, 7, 1, 2, 3 };
        // int tar = 7;
        // System.out.println(searchRotArray(arr, tar));

        // QUESTION 13 Search in a rotated sorted array (duplicates)
        // int[] arr = { 6, 6, 6, 7, 8, 1, 2, 2, 2, 3, 3, 4, 6, 6 };
        // int tar = 1;
        // System.out.println(searchRotArrayDup(arr, tar));

        // Question 14 find max value of i*arr[i] (maximum multiplier combination)
        // Method 1 for all rotations O(n^2) + O(1)
        // int[] arr = { 1, 2, 3, 4, 5, 6 };
        // System.out.println(findMaxMulComb_01(arr));
        // Method 2 using trick O(n) + O(1)
        // System.out.println(findMaxMulComb_02(arr));

        // Question 15 find pair sum in rotated sorted array
        // Efficient method is to find pivot and do normal binary search pair method
        // int[] arr = { 11, 15, 6, 8, 9, 10 };
        // int tar = 16;
        // System.out.println(doesPairSumExist(arr, tar));

        // Question 16 stock buy and sell (one transaction is allowed)
        int[] arr = { 7, 1, 5, 3, 6, 4 };
        // Method 1 naive approach O(n^2) + O(1)
        // System.out.println(stockBuySell_01(arr));
        // Method 2 using maxima after a minima O(n) + O(1)
        // System.out.println(stockBuySell_02(arr));

        // Question 17 stock buy and sell (infinite transaction)
        // System.out.println(stockBuySell_2_01(arr));
    }

    public static int kConcatination_01(int[] arr, int k) {
        int[] narr = new int[arr.length * k];
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < narr.length; i++) {
            narr[i] = arr[i % arr.length];
        }
        ans = kedane(narr);
        return ans;
    }

    public static int[] targetSumSubArray_01(int[] arr, int tar) {
        // find all subarray starting with i
        int csum = 0;
        int[] ans = new int[2];
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                csum += arr[j];
                if (csum == tar) {
                    ans[0] = i;
                    ans[1] = j;
                    return ans;
                }
            }
            csum = 0;
        }
        return ans;
    }

    public static int[] targetSumSubArray_02(int[] arr, int tar) {
        // if all the elements are +ve then use sliding window
        int csum = 0;
        int[] ans = new int[2];
        int st = 0;
        for (int i = 0; i < arr.length; i++) {
            csum += arr[i];
            while (csum > tar && st <= i) {
                csum -= arr[st];
                st++;
            }
            if (csum == tar) {
                ans[0] = st;
                ans[1] = i;
                return ans;
            }
        }
        return ans;
    }

    public static int[] targetSumSubArray_03(int[] arr, int tar) {
        // using hashmap to save the prefix sum
        int[] ans = new int[2];
        HashMap<Integer, Integer> map = new HashMap<>();
        int csum = 0;
        for (int i = 0; i < arr.length; i++) {
            csum += arr[i];
            if (csum == tar) {
                ans[0] = 0;
                ans[1] = i;
                break;
            }
            if (map.containsKey(csum - tar)) {
                ans[0] = map.get(csum - tar) + 1;
                ans[1] = i;
                break;
            }
            map.put(csum, i);
        }
        return ans;
    }

    // public static void targetSumSubArray_04(int[] arr, int tar) {
    // int min_ = Integer.MAX_VALUE;
    // for (int ele : arr) {
    // if (ele < min_)
    // min_ = ele;
    // }
    // int mabs = Math.abs(min_);
    // for (int i = 0; i < arr.length; i++) {
    // arr[i] += mabs;
    // }
    // int csum = 0, st = 0;
    // for (int i = 0; i < arr.length; i++) {
    // csum += arr[i];
    // while ((csum) > tar && st <= i) {

    // }
    // }

    // }

    public static int equilIndex_01(int[] arr) {
        int lsum = 0, rsum = 0;
        for (int i = 0; i < arr.length; i++) {
            lsum = 0;
            rsum = 0;
            for (int j = 0; j < i; j++) {
                lsum += arr[i];
            }
            for (int j = i + 1; j < arr.length; j++) {
                rsum += arr[i];
            }
            if (lsum == rsum)
                return i;
        }
        return -1;
    }

    public static int equilIndex_02(int[] arr) {
        // index -1 and arr.length are having value 0
        int sum = 0;
        for (int ele : arr)
            sum += ele;
        int lsum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i == 0 && sum - arr[i] == 0)
                return 0;
            if (i == arr.length - 1 && lsum == 0)
                return i;
            if (sum - lsum - arr[i] == lsum)
                return i;
            lsum += arr[i];
        }
        return -1;
    }

    public static void pairsWithGivenSum_01(int[] arr, int tar) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] + arr[j] == tar) {
                    System.out.println(arr[i] + " + " + arr[j]);
                }
            }
        }
    }

    public static void pairsWithGivenSum_02(int[] arr, int tar) {
        Arrays.sort(arr); // O(nlog(n))
        // now we can choose either binary search (nlog(n)) or 2 pointers approach(O(n))
        int i = 0;
        int j = arr.length - 1;
        while (i <= j) {
            if (arr[i] + arr[j] == tar) {
                System.out.println(arr[i] + " + " + arr[j]);
                break;
            } else if (arr[i] + arr[j] > tar)
                j--;
            else
                i++;
        }
    }

    public static void pairsWithGivenSum_03(int[] arr, int tar) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], i);
        }
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(tar - arr[i])) {
                System.out.println(arr[i] + " + " + (tar - arr[i]));
            }
        }
    }

    public static void pairsWithGivenSum_04(int[] arr, int tar) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(tar - arr[i])) {
                System.out.println(arr[i] + " + " + (tar - arr[i]));
                break;
            }
            map.put(arr[i], i);
        }
    }

    public static int chocolateDistribution(int[] arr, int m) {
        Arrays.sort(arr);
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i + m - 1 < arr.length; i++) {
            ans = Math.min(ans, arr[i + m - 1] - arr[i]);
        }
        return ans;
    }

    public static void SortList(String[] arr) {
        Comparator<String> comp = new Comparator<String>() {
            @Override
            public int compare(String a, String b) {

                if (a.charAt(0) != b.charAt(0))
                    return (a.charAt(0) - '0') - (b.charAt(0) - '0');

                if (a.charAt(2) != b.charAt(2))
                    return (a.charAt(2) - '0') - (b.charAt(2) - '0');

                return (a.charAt(4) - '0') - (b.charAt(4) - '0');

            }
        };
        Arrays.sort(arr, comp);
    }

    public static int trapRainWater_01(int[] arr) {
        int len = arr.length;
        int[] left = new int[len];
        int[] right = new int[len];
        left[0] = arr[0];
        right[len - 1] = arr[len - 1];
        for (int i = 1; i < len; i++) {
            left[i] = Math.max(arr[i], left[i - 1]);
        }
        for (int i = len - 2; i >= 0; i--) {
            right[i] = Math.max(arr[i], right[i + 1]);
        }
        int ans = 0;
        for (int i = 1; i < len - 1; i++) {
            ans += Math.min(left[i], right[i]) - arr[i];
        }
        return ans;
    }

    public static int trapRainWater_02(int[] arr) {
        // use lmax and rmax for consecutive maximums
        int ans = 0;
        int lmax = 0, rmax = 0;
        int lo = 0;
        int hi = arr.length - 1;
        while (lo <= hi) {
            if (arr[lo] < arr[hi]) {
                if (lmax < arr[lo]) {
                    lmax = arr[lo];
                } else {
                    ans += lmax - arr[lo];
                }
                lo++;
            } else {
                if (rmax < arr[hi]) {
                    rmax = arr[hi];
                } else {
                    ans += rmax - arr[hi];
                }
                hi--;
            }
        }
        return ans;
    }

    public static int[] zigZagCOnversion_01(int[] arr) {
        Arrays.sort(arr);
        for (int i = 1; i + 1 < arr.length; i += 2) {
            swap(arr, i, i + 1);
        }
        return arr;
    }

    public static int[] zigZagCOnversion_02(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (i % 2 == 0 && arr[i - 1] < arr[i]) {
                swap(arr, i, i - 1);
            } else if (i % 2 != 0 && arr[i - 1] > arr[i]) {
                swap(arr, i, i - 1);
            }
        }
        return arr;
    }

    public static int[] segregate0And1_01(int[] arr) {
        int zero = 0;
        for (int ele : arr) {
            if (ele == 0)
                zero++;
        }
        for (int i = 0; i < zero; i++)
            arr[i] = 0;
        for (int i = zero; i < arr.length; i++)
            arr[i] = 1;
        return arr;
    }

    public static int[] segregate0And1_02(int[] arr) {
        int one = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                arr[i] = 1;
                arr[one] = 0;
                one++;
            }
        }
        return arr;
    }

    public static int[] segregate0And1And2_02(int[] arr) {
        int one = 0, it = 0, two = arr.length - 1;
        while (it < arr.length && it <= two) {
            if (arr[it] == 0) {
                swap(arr, it, one);
                one++;
                it++;
            } else if (arr[it] == 2) {
                swap(arr, it, two);
                two--;
            } else {
                it++;
            }
        }
        return arr;
    }

    public static int[] moveZeroesToEnd(int[] arr) {
        int it = arr.length - 1;
        int nzero = arr.length - 1;
        while (it >= 0) {
            if (arr[it] == 0) {
                swap(arr, it, nzero);
                nzero--;
            }
            it--;
        }
        return arr;
    }

    public static int searchRotArray(int[] arr, int tar) {
        // calculate pivot and call binary search
        // case 1 already sorted
        if (arr[0] < arr[arr.length - 1])
            return binSearch(arr, 0, arr.length, tar);
        // case 2 all other cases find pivot
        int si = 0, ei = arr.length - 1, last = arr[arr.length - 1];
        while (si <= ei) {
            int mid = si + (ei - si) / 2;
            if (arr[mid] < last) {
                ei = mid - 1;
            } else {
                si = mid + 1;
            }
        }
        // pivot is ei <-> si
        if (arr[0] <= tar && arr[ei] >= tar) // left subarray
            return binSearch(arr, 0, ei, tar);
        else if (arr[si] <= tar && arr[arr.length - 1] >= tar) // right subarray
            return binSearch(arr, ei, arr.length - 1, tar);
        else
            return -1;
    }

    // public static int searchRotArrayDup(int[] arr, int tar) {
    // if (arr[0] == tar)
    // return 0;
    // int si = 0, ei = arr.length - 1;
    // // while edges are equal
    // while (si <= ei && arr[si] == arr[ei]) {
    // si++;
    // ei--;
    // }
    // // same logic as for non duplicates //6,2,2,2,2
    // }

    public static int findMaxMulComb_01(int[] arr) {
        // use 2 loops and rotate array
        int ans = Integer.MIN_VALUE;
        int index = 0;
        int cSum = 0;
        for (int i = 0; i < arr.length; i++) {
            cSum = 0;
            for (int j = 0; j < arr.length; j++) {
                cSum += arr[(i + j) % arr.length] * j;
            }
            if (cSum > ans) {
                index = i;
                ans = cSum;
            }
        }
        System.out.println(index);
        return ans;
    }

    public static int findMaxMulComb_02(int[] arr) {
        int cSum = 0, ans = Integer.MIN_VALUE, sum = 0, index = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            cSum += (i * arr[i]);
        }
        ans = cSum;
        for (int i = 1; i < arr.length; i++) {
            cSum = cSum + (arr[i - 1] * (arr.length - 1)) - (sum - arr[i - 1]);
            if (cSum > ans) {
                ans = cSum;
                index = i;
            }
        }
        System.out.println(index);
        return ans;
    }

    public static boolean doesPairSumExist(int[] arr, int tar) {
        // case 1 already sorted
        if (arr[0] < arr[arr.length - 1])
            return searchPair(arr, 0, arr.length, tar);
        // case 2 all other cases find pivot
        int si = 0, ei = arr.length - 1, last = arr[arr.length - 1];
        while (si <= ei) {
            int mid = si + (ei - si) / 2;
            if (arr[mid] < last) {
                ei = mid - 1;
            } else {
                si = mid + 1;
            }
        }
        return searchPair(arr, si, ei, tar);
    }

    public static boolean searchPair(int[] arr, int si, int ei, int tar) {
        while (si != ei) {
            if (arr[si] + arr[ei] == tar)
                return true;
            else if (arr[si] + arr[ei] > tar)
                ei = (ei + arr.length - 1) % arr.length;
            else
                si = (si + 1) % arr.length;
        }
        return false;
    }

    public static int stockBuySell_01(int[] arr) {
        // purchased at ith day and sell at jth day.
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] - arr[i] > ans)
                    ans = arr[j] - arr[i];
            }
        }
        return ans;
    }

    public static int stockBuySell_02(int[] arr) {
        int ans = 0, min_ = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min_)
                min_ = arr[i];
            else
                ans = Math.max(arr[i] - min_, ans);
        }
        return ans;
    }

    public static int stockBuySell_2_01(int[] arr) {
        int ans = 0, min_ = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min_)
                min_ = arr[i];
            else {
                ans += arr[i] - min_;
                min_ = arr[i];
            }
        }
        return ans;
    }

    /***********************************************/

    public static int binSearch(int[] arr, int si, int ei, int tar) {
        while (si <= ei) {
            int mid = si + (ei - si) / 2;
            if (arr[mid] == tar)
                return mid;
            else if (arr[mid] > tar)
                ei = mid - 1;
            else
                si = mid + 1;
        }
        return -1;
    }

    public static int kedane(int[] arr) {
        int ans = Integer.MIN_VALUE;
        int sum = 0;
        for (int ele : arr) {
            sum += ele;
            if (ans < sum)
                ans = sum;
            if (sum < 0)
                sum = 0;
        }
        return ans;
    }

    public static void displayArray(int[] arr) {
        System.out.println();
        for (int ele : arr) {
            System.out.print(ele + " ");
        }
        System.out.println();
    }

    public static void displayStringArray(String[] arr) {
        System.out.println();
        for (String ele : arr) {
            System.out.print(ele + "\t");
        }
        System.out.println();
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}