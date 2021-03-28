import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int t = scn.nextInt();
        for (int c = 1; c <= t; c++) {
            int n = scn.nextInt();
            int m = scn.nextInt();
            long[] arr1 = new long[n];
            long sum = 0;
            for (int i = 0; i < n; i++) {
                arr1[i] = scn.nextInt();
                // sum += arr1[i];
            }

            long[] arr2 = new long[m];
            for (int i = 0; i < m; i++) {
                arr2[i] = scn.nextInt();
                // sum -= arr2[i];
            }

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
            if ((m - p1) != (n - p2)) {
                System.out.println("Alice");
                continue;
            }
            boolean sym = true;
            while (p1 < m) {
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

    }

}