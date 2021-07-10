package leetcode_hot100.top40;

/**
 * @author WuChao
 * @create 2021/7/10 12:58
 */
public class _64 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 64. 最小路径和
     * 难度：easy
     * <p>
     * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
     * <p>
     * 说明：每次只能向下或者向右移动一步。
     * <p>
     * 示例
     * 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
     * 输出：7
     * 解释：因为路径 1→3→1→1→1 的总和最小。
     *
     * <p>
     * 范围
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 200
     * 0 <= grid[i][j] <= 100
     */

    /*
    思路：使用动态规划
    跟 62 题一样
    dp[i][j] 表示从 （0,0）到当前的位置的总和
     */

    public int minPathSum(int[][] grid) {
        if (grid == null) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        if (m == 0 || n == 0) {
            return 0;
        }
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    dp[0][0] = grid[i][j];
                } else if (i == 0) {
                    dp[i][j] = dp[i][j - 1] + grid[i][j];// 只能从左边到
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + grid[i][j];// 只能从左边到
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                }
            }
        }
        return dp[m - 1][n - 1];

    }

}
