package dp;

import java.util.ArrayList;

public class LeetCode931 {
    public int minFallingPathSum(int[][] A) {
        int n = A.length;
        if (n == 1) return A[0][0];

        int[] paths = new int[n];
        paths = A[0];
        for (int i = 1; i < n; i++) {
            int[] tmp = new int[n];
            tmp[0] = A[i][0] + Math.min(paths[0], paths[1]);
            tmp[n-1] = A[i][n-1] + Math.min(paths[n-1], paths[n-2]);
            for (int j = 1; j < n-1; j++) {
                tmp[j] = A[i][j] + Math.min(paths[j-1], Math.min(paths[j], paths[j+1]));
            }
            paths = tmp;
        }
        int min = paths[0];
        for (int i = 1; i < n; i++) {
            min = Math.min(min, paths[i]);
        }
        return min;
    }
}
