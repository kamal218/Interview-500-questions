public class matrix {
    public static void main(String[] args) {
        questions();
    }

    public static void questions() {
        // Question 1 matrix rotation clockwise and anti-clockwise
        int[][] mat = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
        displayMatrix(mat);
        // Method 1 using extra space O(n^2) + O(n^2)
        // displayMatrix(antiClockWise_01(mat));
        // displayMatrix(clockWise_01(mat));
        // Method 2 withot extra space O(n^2) + O(1)
        // displayMatrix(antiClockWise_02(mat));
        displayMatrix(clockWise_02(mat));
    }

    public static int[][] antiClockWise_01(int[][] mat) {
        int len = mat.length;
        int[][] ans = new int[len][len];

        for (int r = 0; r < len; r++) {
            for (int c = 0; c < len; c++) {
                ans[len - c - 1][r] = mat[r][c];
            }
        }
        return ans;
    }

    public static int[][] clockWise_01(int[][] mat) {
        int len = mat.length;
        int[][] ans = new int[len][len];

        for (int r = 0; r < len; r++) {
            for (int c = 0; c < len; c++) {
                ans[c][len - r - 1] = mat[r][c];
            }
        }
        return ans;
    }

    public static int[][] antiClockWise_02(int[][] mat) {
        // transpose and swap rows
        int si = 0, ei = 0;
        for (int gap = 1; gap < mat.length; gap++) { // diagonal travel for transpose
            si = 0;
            ei = gap;
            while (ei < mat.length) {
                int temp = mat[si][ei];
                mat[si][ei] = mat[ei][si];
                mat[ei][si] = temp;
                si++;
                ei++;
            }
        }
        si = 0;
        ei = mat.length - 1;
        while (si < ei) {
            for (int i = 0; i < mat.length; i++) {
                int temp = mat[si][i];
                mat[si][i] = mat[ei][i];
                mat[ei][i] = temp;
            }
            si++;
            ei--;
        }
        return mat;
    }

    public static int[][] clockWise_02(int[][] mat) {
        // transpose and swap rows
        int si = 0, ei = 0;
        for (int gap = 1; gap < mat.length; gap++) { // diagonal travel for transpose
            si = 0;
            ei = gap;
            while (ei < mat.length) {
                int temp = mat[si][ei];
                mat[si][ei] = mat[ei][si];
                mat[ei][si] = temp;
                si++;
                ei++;
            }
        }
        si = 0;
        ei = mat.length - 1;
        while (si < ei) {
            for (int i = 0; i < mat.length; i++) {
                int temp = mat[i][si];
                mat[i][si] = mat[i][ei];
                mat[i][ei] = temp;
            }
            si++;
            ei--;
        }
        return mat;
    }

    /****************************************************/

    public static void displayMatrix(int[][] mat) {
        for (int[] arr : mat) {
            for (int ele : arr) {
                System.out.print(ele + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}