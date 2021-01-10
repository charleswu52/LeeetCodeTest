package com.leetcodetest.Jan;

public class _9 {
    /**
     * 每日一题：2021/1/9
     * 买卖股票的最佳时机 III
     *
     * 买卖股票的最佳时机专题已记录在 c++ 解题的 20/12/28 中进行了整理
     *
     * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
     * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     *
     * 示例：
     * 输入：prices = [3,3,5,0,0,3,1,4]
     * 输出：6
     * 解释：在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
     *      随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
     */

    /**
     * 思路：动态规划
     * 在一天结束的时候，可能有持股、可能未持股、可能卖出1次、卖出2次也可能未卖出这些情况
     * 定义状态转移数组：dp[天数][当天是否持股][卖出的次数] : dp[n][2][3]
     * 一天结束的时候有 2*3 6种状态：
     * 1.未持股，未卖出过股票：说明从未进行过买卖，利润为0
     * dp[i][0][0]=0
     * 2.未持股，卖出过1次股票：可能是今天卖出，也可能是之前卖的（昨天也未持股且卖出过）
     * dp[i][0][1]=max(dp[i-1][1][0]+prices[i],dp[i-1][0][1])
     * 3.未持股，卖出过2次股票:可能是今天卖出，也可能是之前卖的（昨天也未持股且卖出过）
     * dp[i][0][2]=max(dp[i-1][1][1]+prices[i],dp[i-1][0][2])
     * 4.持股，未卖出过股票：可能是今天买的，也可能是之前买的（昨天也持股）
     * dp[i][1][0]=max(dp[i-1][0][0]-prices[i],dp[i-1][1][0])
     * 5.持股，卖出过1次股票：可能是今天买的，也可能是之前买的（昨天也持股）
     * dp[i][1][1]=max(dp[i-1][0][1]-prices[i],dp[i-1][1][1])
     * 6.持股，卖出过2次股票：最多交易2次，这种情况不存在
     * dp[i][1][2]=MIN_VALUE
     *
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        int n = prices.length;
        if (n <= 1) {
            return 0;
        }
        int[][][] dp = new int[n][2][3];    // 创建动态规划数组
        int MIN_VALUE = Integer.MIN_VALUE / 2; // 防止溢出定义的最小值，原最小值减1就是最大值
        // 定义初始状态
        dp[0][0][0] = 0;    // 第一天
        dp[0][0][0] = 0;//第一天休息
        dp[0][0][1] = dp[0][1][1] = MIN_VALUE;//不可能
        dp[0][0][2] = dp[0][1][2] = MIN_VALUE;//不可能
        dp[0][1][0] = -prices[0];//买股票
        for (int i = 1; i < n; ++i) {
            dp[i][0][0] = 0;
            dp[i][0][1] = Math.max(dp[i - 1][1][0] + prices[i], dp[i - 1][0][1]);
            dp[i][0][2] = Math.max(dp[i - 1][1][1] + prices[i], dp[i - 1][0][2]);
            dp[i][1][0] = Math.max(dp[i - 1][0][0] - prices[i], dp[i - 1][1][0]);
            dp[i][1][1] = Math.max(dp[i - 1][0][1] - prices[i], dp[i - 1][1][1]);
            dp[i][1][2] = MIN_VALUE;
        }
        return Math.max(0, Math.max(dp[n - 1][0][1], dp[n - 1][0][2]));
    }

    public static void _21_1_9(){
        int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};
        System.out.println(maxProfit(prices));

    }
}
