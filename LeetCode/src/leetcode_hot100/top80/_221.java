package leetcode_hot100.top80;

/**
 * @author WuChao
 * @create 2021/7/18 12:42
 */
public class _221 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 221. 最大正方形
     * 难度：medium
     * <p>
     * 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
     * <p>
     * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
     * <p>
     * 示例：
     * <p>
     * 输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
     * 输出：4
     * <p>
     * 输入：matrix = [["0","1"],["1","0"]]
     * 输出：1
     * <p>
     * 输入：matrix = [["0"]]
     * 输出：0
     * <p>
     * 数据范围:
     * m == matrix.length
     * n == matrix[i].length
     * 1 <= m, n <= 300
     * matrix[i][j] 为 '0' 或 '1'
     */

    /*
    思路： 动态规划
    定义dp[i][j] 表示以(i,j)为右下角，只包含1的正方形的边长的最大值。
    遍历得出dp中所有元素后，dp中最大值即为结果
    转移方程：
    如果 matrix[i][j] 为 0 ，则dp[i][j] = 0
    如果 matrix[i][j] 为 1 ，则该值由上方，左方和左上方三个相邻位置的值决定
        dp[i][j] = min(dp[i-1][j],dp[i][j-1],dp[i-1][j-1])+1
    其他边界条件，如果 i 或 j 有一个为0，且matrix[i][j] = 1,dp[i][j] = 1


     */
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int res = 0;
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    }else {
                        dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                    }
                }
                res = Math.max(res, dp[i][j]);
            }
        }

        return res * res;
    }
}
