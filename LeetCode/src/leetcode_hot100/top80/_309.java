package leetcode_hot100.top80;

/**
 * @author WuChao
 * @create 2021/7/24 8:45
 */
public class _309 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 309. 最佳买卖股票时机含冷冻期
     * 难度：medium
     * <p>
     * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。
     * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
     * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
     * <p>
     * 输入: [1,2,3,0,2]
     * 输出: 3
     * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
     */

    /*
    思路：动态规划
    不要被“冷冻期”打倒，所谓冷冻期就是说如果昨天卖出了，今天就不可以买入，所以受影响的就是买入的日期，只有在今天想买入的时候判断一下前一天是否刚
    卖出即可，关键的一天其实是卖出的那一天而不是卖出的后一天
    对于每一天也是有持有股票和不持有股票这两种情况，但是后一种不持有又分为当前卖出和没卖出两种每卖出不持有就是冷冻期导致的
    所以对于每一天有三种状态：
        dp[i][0] : 不持股且当天没卖出;
        dp[i][1] : 持股;
        dp[i][2] : 不持股且当天卖出;
    初始化 dp[0][0] = 0 ; dp[0][1] = -price[0]; dp[i][2] = 0

    每种状态的计算公式
        dp[i][0] = max(dp[i-1][0],dp[i-1][2]) // 前一天也没有股票
        dp[i][1] = max(dp[i-1][1],dp[i-1][0]-price[i]) // 昨天也有或者昨天没有今天买了
        dp[i][2] = do[i-1][1]+price[i]  // 昨天持股今天卖出
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;

        int res = 0;
        int[][] dp = new int[n][3];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[0][2] = 0;

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            dp[i][2] = dp[i - 1][1] + prices[i];

        }
        res = Math.max(dp[n - 1][0], Math.max(dp[n - 1][1], dp[n - 1][2]));
        return res;
    }
}
