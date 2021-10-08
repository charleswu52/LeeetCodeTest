package leetcode_everyday.Aug;


/**
 * @author WuChao
 * @create 2021/8/15 12:27
 */
public class _15_576 {
    /**
     * 每日一题：2021/8/15
     * 576. 出界的路径数
     * 难度：medium
     * <p>
     * 给你一个大小为 m x n 的网格和一个球。球的起始坐标为 [startRow, startColumn] 。
     * 你可以将球移到在四个方向上相邻的单元格内（可以穿过网格边界到达网格之外）。你 最多 可以移动 maxMove 次球。
     * <p>
     * 给你五个整数 m、n、maxMove、startRow 以及 startColumn ，找出并返回可以将球移出边界的路径数量。
     * 因为答案可能非常大，返回对 10^9 + 7 取余 后的结果。
     *
     * <p>
     * 示例 1：
     * 输入：m = 2, n = 2, maxMove = 2, startRow = 0, startColumn = 0
     * 输出：6
     * <p>
     * 示例 2：
     * 输入：m = 1, n = 3, maxMove = 3, startRow = 0, startColumn = 1
     * 输出：12
     * <p>
     * 注意:
     * 1 <= m, n <= 50
     * 0 <= maxMove <= 50
     * 0 <= startRow < m
     * 0 <= startColumn < n
     */

    /*
    思路：动态规划
    dp数组的定义
    定义 dp[i][j][k] 表示球移动 i  次后位于坐标(j,k)的路径数量
    动态规划的状态由移动次数、行和列决定，定义 dp[i][j][k] 表示球移动 i 次之后位于坐标 (j, k) 的路径数量。

    dp初始状态：
    当 i=0 时，球一定位于起始坐标 (startRow,startColumn)，因此动态规划的边界情况是：dp[0][startRow][startColumn]=1，
    当 (j,k) != (startRow,startColumn) 时有 dp[0][j][k]=0。

    如果球移动了 i 次之后位于坐标 (j, k)，且 i<maxMove，0≤j<m，0≤k<n，则移动第 i+1 次之后，球一定位于和坐标 (j, k) 相邻的一个坐标，记为 (j', k').
        当 0≤j′ <m 且 0≤k′<n 时，球在移动 i+1 次之后没有出界，将 dp[i][j][k] 的值加到 dp[i+1][j′][k′]；
        否则，球在第 i+1 次移动之后出界，将 dp[i][j][k] 的值加到出界的路径数。

    由于最多可以移动的次数是 maxMove，因此遍历 0≤i<maxMove，根据 dp[i][][] 计算 dp[i+1][][] 的值以及出界的路径数，即可得到最多移动
    maxMove 次的情况下的出界的路径数。

    根据上述思路，可以得到时间复杂度和空间复杂度都是 O(maxMove×m×n) 的实现。
     */
    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        final int mod = 1000000007;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int res = 0;
        int[][][] dp = new int[maxMove + 1][m][n];
        dp[0][startRow][startColumn] = 1;
        for (int i = 0; i < maxMove; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < n; k++) {
                    int count = dp[i][j][k];
                    if (count > 0) {
                        for (int[] direction : directions) {
                            int j1 = j + direction[0];
                            int k1 = k + direction[1];
                            if (j1 >= 0 && j1 < m && k1 >= 0 && k1 < n) {
                                dp[i + 1][j1][k1] = (dp[i + 1][j1][k1] + count) % mod;
                            } else {
                                res = (res + count) % mod;
                            }
                        }
                    }
                }
            }
        }
        return res;
    }


}
