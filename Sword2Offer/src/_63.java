import org.junit.Test;

/**
 * @author WuChao
 * @since 2021/3/31 下午1:13
 */
public class _63 {
    /**
     * 剑指 Offer 63. 股票的最大利润
     * 难度: medium
     * <p>
     * 假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？
     *
     * <p>
     * 例如：
     * 输入: [7,1,5,3,6,4]
     * 输出: 5
     * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
     *
     * <p>
     * 数据范围：
     * 0 <= 数组长度 <= 10^5
     */

    /**
     * 买卖股票的最大值
     * 使用动态规划的思想：
     * 状态定义： 设动态规划列表 dp ，dp[i] 代表以 prices[i] 为结尾的子数组的最大利润（以下简称为 前 i 日的最大利润 ）。
     * 转移方程： 由于题目限定 “买卖该股票一次” ，因此前i日最大利润dp[i]等于前 i−1 日最大利润 dp[i-1] 和第 i 日卖出的最大利润中的最大值。
     *          前i日最大利润=max(前(i−1)日最大利润,第i日价格−前i日最低价格),即
     *           dp[i]=max(dp[i−1],prices[i]−min(prices[0:i]))
     *
     * 初始状态： dp[0]=0 ，即首日利润为 0 ；
     * 返回值： dp[n−1] ，其中 n 为 dp 列表长度。
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int res = 0;
        int n = prices.length;
        if (n< 1) {
            return res;
        }
        int minPrice = prices[0];
        int[] dp = new int[n];
        for (int i = 1; i < n; i++) {
            minPrice = Math.min(minPrice, prices[i]);
            dp[i] = Math.max(dp[i - 1], prices[i] - minPrice);
        }
        return dp[n - 1];
    }

    @Test
    public void test() {

    }
}
