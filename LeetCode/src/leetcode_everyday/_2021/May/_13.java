package leetcode_everyday._2021.May;

/**
 * @author WuChao
 * @since 2021/5/13 上午8:03
 */
public class _13 {
    /**
     * 每日一题：2021/5/13
     * 1269. 停在原地的方案数
     * 难度: hard
     * <p>
     * 有一个长度为 arrLen 的数组，开始有一个指针在索引 0 处。
     * <p>
     * 每一步操作中，你可以将指针向左或向右移动 1 步，或者停在原地（指针不能被移动到数组范围外）。
     * <p>
     * 给你两个整数 steps 和 arrLen ，请你计算并返回：在恰好执行 steps 次操作以后，指针仍然指向索引 0 处的方案数。
     * <p>
     * 由于答案可能会很大，请返回方案数 模 10^9 + 7 后的结果。
     * <p>
     * 示例：
     * 输入：steps = 3, arrLen = 2
     * 输出：4
     * 解释：3 步后，总共有 4 种不同的方法可以停在索引 0 处。
     * 向右，向左，不动
     * 不动，向右，向左
     * 向右，不动，向左
     * 不动，不动，不动
     * <p>
     * 输入：steps = 2, arrLen = 4
     * 输出：2
     * 解释：2 步后，总共有 2 种不同的方法可以停在索引 0 处。
     * 向右，向左
     * 不动，不动
     * <p>
     *
     * <p>
     * 数据范围：
     * 1 <= steps <= 500
     * 1 <= arrLen <= 10^6
     */

    /*
    思路：动态规划
    可以移动steps步，索引可以停留的位置范围为[0,aarLen-1]
    对于求方案数一般采用动态规划的思路
    dp[i][j]表示在走了i步后，索引指针位于下标j的方案数。其中，i 的取值范围是 0≤i≤steps，j 的取值范围是 0≤j≤arrLen−1。
    此外，由于一共执行 steps 步操作，因此指针所在下标一定不会超过  steps，可以将 j 的取值范围进一步缩小到 0≤j≤min(arrLen−1,steps)。

    dp数组初始状态：
        dp[0][0] =1;
        dp[0][j]=0, 1<=j<=min(arrLen-1,steps)
    状态转移方程：
        每一步操作中，指针可以向左或向右移动 1 步，或者停在原地。因此，当 1≤i≤steps 时，
        状态 dp[i][j] 可以从  dp[i−1][j−1]、 dp[i−1][j] 和  dp[i−1][j+1] 这三个状态转移得到。
            dp[i][j] = dp[i-1][j-1]+dp[i-1][j]+dp[i-1][j+1]
    数组边界情况：
        当j=0时，dp[i-1][j-1]=0;当j=min(arrLen-1,steps)时，dp[i-1][j+1]=0
     */
    public int numWays(int steps, int arrLen) {
        final int MOD= 1000000007;
        int maxJ = Math.min(arrLen - 1, steps);
        int[][] dp = new int[steps + 1][maxJ + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= steps; i++) {
            for (int j = 0; j <= maxJ; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j - 1 >= 0) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][j - 1]) % MOD;
                }
                if (j + 1 <= maxJ) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][j + 1]) % MOD;
                }
            }
        }
        return dp[steps][0];
    }


}
