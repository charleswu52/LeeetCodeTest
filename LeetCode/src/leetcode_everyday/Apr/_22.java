package leetcode_everyday.Apr;

/**
 * @author WuChao
 * @since 2021/4/23 上午8:12
 */
public class _22 {
    /**
     * 每日一题：2021/4/22
     * 363. 矩形区域不超过 K 的最大数值和
     * 难度: hard
     * <p>
     * 给你一个 m x n 的矩阵 matrix 和一个整数 k ，找出并返回矩阵内部矩形区域的不超过 k 的最大数值和。
     * <p>
     * 题目数据保证总会存在一个数值和不超过 k 的矩形区域。
     *
     * <p>
     * 示例：
     * 输入：matrix = [[1,0,1],[0,-2,3]], k = 2
     * 输出：2
     * 解释：蓝色边框圈出来的矩形区域 [[0, 1], [-2, 3]] 的数值和是 2，且 2 是不超过 k 的最大数字（k = 2）。
     *
     * <p>
     * 数据范围：
     * m == matrix.length
     * n == matrix[i].length
     * 1 <= m, n <= 100
     * -100 <= matrix[i][j] <= 100
     * -105 <= k <= 105
     */

    public int maxSumSubmatrix(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        int max = Integer.MIN_VALUE;
        for (int i1 = 1; i1 <= m; i1++) {
            for (int j1 = 1; j1 <= n; j1++) {
                int[][] dp = new int[m + 1][n + 1];
                dp[i1][j1] = matrix[i1 - 1][j1 - 1];
                for (int i2 = i1; i2 <= m; i2++) {
                    for (int j2 = j1; j2 <= n; j2++) {
                        dp[i2][j2] = dp[i2 - 1][j2] + dp[i2][j2 - 1] - dp[i2 - 1][j2 - 1] + matrix[i2 - 1][j2 - 1];
                        if (dp[i2][j2] <= k && dp[i2][j2] > max) {
                            max = dp[i2][j2];
                        }
                    }
                }
            }
        }
        return max;


    }


}
