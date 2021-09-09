package leetcode_everyday.Oct;

/**
 * @author WuChao
 * @create 2021/8/18 8:47
 */
public class _18_552 {
    /**
     * 每日一题：2021/8/18
     * 552. 学生出勤记录 II
     * 难度：hard
     * <p>
     * 给你一个字符串 s 表示一个学生的出勤记录，其中的每个字符用来标记当天的出勤情况（缺勤、迟到、到场）。记录中只含下面三种字符：
     * <p>
     * 'A'：Absent，缺勤
     * 'L'：Late，迟到
     * 'P'：Present，到场
     * <p>
     * 如果学生能够 同时 满足下面两个条件，则可以获得出勤奖励：
     * <p>
     * 按 总出勤 计，学生缺勤（'A'）严格 少于两天。
     * 学生 不会 存在 连续 3 天或 3 天以上的迟到（'L'）记录。
     * <p>
     * 给你一个整数 n ，表示出勤记录的长度（次数）。请你返回记录长度为 n 时，可能获得出勤奖励的记录情况 数量 。
     * 答案可能很大，所以返回对 109 + 7 取余 的结果。
     *
     * <p>
     * 输入：n = 2
     * 输出：8
     * 解释：
     * 有 8 种长度为 2 的记录将被视为可奖励：
     * "PP" , "AP", "PA", "LP", "PL", "AL", "LA", "LL"
     * 只有"AA"不会被视为可奖励，因为缺勤次数为 2 次（需要少于 2 次）。
     *
     * <p>
     * 注意:
     * 1 <= n <= 105
     */

    /*
    思路：动态规划
    可以使用动态规划计算可奖励的出勤记录的数量。

    由于可奖励的出勤记录要求缺勤次数少于 2 和连续迟到次数少于 3，
    因此动态规划的状态由总天数、缺勤次数和结尾连续迟到次数决定（由于不会记录连续迟到次数等于或多于 3 的情况，
    因此非结尾的连续迟到次数一定少于 3，只需要记录结尾连续迟到次数即可）。


    定义 dp[i][j][k] 表示前 i 天有 j 个‘A’ 且结尾有连续 k 个 ‘L’ 的可奖励的出勤记录的数量，其中 0≤i≤n，0≤j≤1，0≤k≤2。

    当 i=0 时，没有任何出勤记录，此时 ‘A’ 的数量和结尾连续 ‘L’ 的数量一定是 0，因此动态规划的边界情况是 dp[0][0][0]=1。

    当 1≤i≤n 时，dp[i][][] 的值从 dp[i−1][][] 的值转移得到，计算每个状态的值需要考虑第 i 天的出勤记录：

    如果第 i 天的出勤记录是 ‘P’，则前 ii 天和前 i−1 天的出勤记录相比，‘A’ 的数量不变，结尾连续 ‘L’ 的数量清零，因此对 0≤j≤1，有
        dp[i][j][0]:=dp[i][j][0]+ k=0 ->2 ∑  dp[i−1][j][k]

    如果第 i 天的出勤记录是 ‘A’，则前 i 天和前 i−1 天的出勤记录相比，‘A’ 的数量加 1，结尾连续 ‘L’ 的数量清零，
    此时要求前 i−1 天的出勤记录记录中的 ‘A’ 的数量必须为 0，否则前 i 天的出勤记录至少有 2 个 ‘A’，不满足可奖励的条件，因此有
        dp[i][1][0]:=dp[i][1][0]+ k=0->2 ∑ dp[i−1][0][k]

    如果第 i 天的出勤记录是 ‘L’，则前 i 天和前 i−1 天的出勤记录相比，‘A’ 的数量不变，结尾连续 ‘L’ 的数量加 1，此时要求前 i−1 天
    的出勤记录记录中的结尾连续 ‘L’ 的数量不超过 2，否则前 i 天的出勤记录的结尾至少有 3 个 ‘L’，不满足可奖励的条件，
    因此对 0≤j≤1 和 1≤k≤2，有
        dp[i][j][k]:=dp[i][j][k]+dp[i−1][j][k−1]

    上述状态转移方程对于 i=1i=1 也适用。
    计算长度为 nn 的所有可奖励的出勤记录的数量，即为计算 dp[n][][] 的所有元素之和。计算过程中需要将结果对 10^9+7 取模。

    根据上述思路，可以得到时间复杂度和空间复杂度都是 O(n) 的实现。
     */
    public int checkRecord(int n) {
        final int MOD = 1000000007;
        int[][][] dp = new int[n + 1][2][3]; // 长度，A 的数量，结尾连续 L 的数量
        dp[0][0][0] = 1;
        for (int i = 1; i <= n; i++) {
            // 以 P 结尾的数量
            for (int j = 0; j <= 1; j++) {
                for (int k = 0; k <= 2; k++) {
                    dp[i][j][0] = (dp[i][j][0] + dp[i - 1][j][k]) % MOD;
                }
            }
            // 以 A 结尾的数量
            for (int k = 0; k <= 2; k++) {
                dp[i][1][0] = (dp[i][1][0] + dp[i - 1][0][k]) % MOD;
            }
            // 以 L 结尾的数量
            for (int j = 0; j <= 1; j++) {
                for (int k = 1; k <= 2; k++) {
                    dp[i][j][k] = (dp[i][j][k] + dp[i - 1][j][k - 1]) % MOD;
                }
            }
        }
        int sum = 0;
        for (int j = 0; j <= 1; j++) {
            for (int k = 0; k <= 2; k++) {
                sum = (sum + dp[n][j][k]) % MOD;
            }
        }
        return sum;


    }
}