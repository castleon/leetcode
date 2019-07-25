package dp;

public class LeetCode1039 {
    /**
     * 给定 N，想象一个凸 N 边多边形，其顶点按顺时针顺序依次标记为 A[0], A[i], ..., A[N-1]。
     *
     * 假设您将多边形剖分为 N-2 个三角形。对于每个三角形，该三角形的值是顶点标记的乘积，三角剖分的分数是进行三角剖分后所有 N-2 个三角形的值之和。
     *
     * 返回多边形进行三角剖分后可以得到的最低分。
     *
     * 输入：[3,7,4,5]
     * 输出：144
     * 解释：有两种三角剖分，可能得分分别为：3*7*5 + 4*5*7 = 245，或 3*4*5 + 3*4*7 = 144。最低分数为 144。
     */

    private static int minScoreTriangulation(int[] A) {
        int n = A.length;
        if (n < 3) return 0;

        int[][] dp = new int[n][n];
        for (int j = 0; j < n; j++) {
            for (int i = j-1; i >= 0; i--) {
                if (j - i < 2) {
                    dp[i][j] = 0;
                } else {
                    for (int k = i+1; k < j; k++) {
                        if (dp[i][j] == 0) {
                            dp[i][j] = dp[i][k] + A[i] * A[k] * A[j] + dp[k][j];
                        } else {
                            dp[i][j] = Math.min(dp[i][j], dp[i][k] + A[i] * A[k] * A[j] + dp[k][j]);
                        }
                    }
                }
            }
        }
        return dp[0][n-1];
    }

    public static void main(String[] args) {
        int[] A = {3, 7, 4, 5};
        System.out.println(minScoreTriangulation(A));
    }
}
