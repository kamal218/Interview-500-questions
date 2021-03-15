import javax.lang.model.util.ElementScanner6;

public class uglyNumber {
    public static void main(String[] args) {
        // boolean ans = checkIfAnUglyNumber(456);//O(logn)+O(1)
        // int ans = nThUglyNumber();//O(nlogn)+O(1)
        int ans = nThUglyNumberDP();// O(n)+O(n)
        // int ans = nThUglyNumberBinarySearch();
        System.out.println(ans);
    }

    public static boolean checkIfAnUglyNumber(int n) {
        if (n == 0)
            return false;
        while (n % 2 == 0) {
            n /= 2;
        }
        while (n % 3 == 0) {
            n /= 3;
        }
        while (n % 5 == 0) {
            n /= 5;
        }
        return n == 1;
    }

    public static int nThUglyNumber() {
        int n = 11;
        int count = 0;
        int i = 0;
        while (count != n) {
            i++;
            boolean res = checkIfAnUglyNumber(i);
            if (res)
                count++;
        }
        return i;
    }

    public static int nThUglyNumberDP() {
        int n = 1690;
        int[] dp = new int[n];
        dp[0] = 1;
        int i = 0, j = 0, k = 0;
        int pt = 1;
        int n2 = 2, n3 = 3, n5 = 5;
        while (pt < n) {
            int min_ = Math.min(Math.min(n2, n3), n5);
            dp[pt] = min_;
            if (min_ == n2) {
                i++;
                n2 = dp[i] * 2;
            }
            if (min_ == n3) {
                j++;
                n3 = dp[j] * 3;
            }
            if (min_ == n5) {
                k++;
                n5 = dp[k] * 5;
            }
            pt++;
        }
        return dp[n - 1];
    }

    public static int nThUglyNumberBinarySearch() {
        int n = 10;
        int si = 1, ei = 20;
        int ans = 0;
        int[] pow = new int[32];
        pow[0] = 1;
        for (int i = 1; i < 31; i++) {
            pow[i] = pow[i - 1] * 2;
            // System.out.println(pow[i]);
        }
        while (si <= ei) {
            int mid = (si + (ei - si) / 2);
            int count = 0;
            for (long i = 1; i * i <= mid; i *= 3) {
                for (long j = 1; j * i <= mid; j *= 5) {
                    count += countUgly((int) (mid / (i * j)), pow);
                }
            }
            System.out.println(mid + " " + count);
            if (count < n) {
                si = mid + 1;
            } else {
                ans = mid;
                ei = mid - 1;
            }
        }
        return ans;
    }

    public static int countUgly(int n, int[] pow) {
        int si = 0, ei = 30;
        while (si <= ei) {
            int mid = (si + (ei - si) / 2);
            if (pow[mid] == n)
                return mid + 1;
            else if (pow[mid] < n)
                si = mid + 1;
            else
                ei = mid - 1;
        }
        return si;
    }
}