package leetcode_hot100.top60;

/**
 * @author WuChao
 * @create 2021/7/14 8:52
 */
public class _121 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 121. 买卖股票的最佳时机
     * 难度：easy
     * <p>
     * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
     *
     * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
     *
     * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
     * <p>
     * 示例：
     * 输入：[7,1,5,3,6,4]
     * 输出：5
     * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
     *
     * 输入：prices = [7,6,4,3,1]
     * 输出：0
     * 解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
     * <p>
     * 数据范围；
     * 1 <= prices.length <= 105
     * 0 <= prices[i] <= 104
     */

    /*
    思路：一次遍历
    遍历过程中记录股票价格的最小值，然后用当前股票价格减去之前的最小股价，然后更新最大利润，同时更新最小价格

     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n < 2) {
            return 0;
        }
        int res = 0;
        int minPrice = prices[0];
        for (int i = 1; i < n; i++) {
            res = Math.max(res, prices[i] - minPrice);
            minPrice = Math.min(minPrice, prices[i]);
        }
        return res;
    }

}
