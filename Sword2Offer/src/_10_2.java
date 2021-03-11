/**
 * @author WuChao
 * @since 2021/3/11 上午10:24
 */
public class _10_2 {
    /**
     * 剑指 Offer 10- II. 青蛙跳台阶问题
     * 难度: easy
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
     * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
     * <p>
     * <p>
     * 示例：
     * 输入：n = 2
     * 输出：2
     * <p>
     * 数据范围：
     * 0 <= n <= 100
     */


    public int numWays(int n) {
        if (n == 0) {
            return 1;
        }
        if (n > 0 && n < 3) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[0] =1;
        dp[1]= 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2])%1000000007;
        }

        return dp[n];
    }

    public void sword2Offer_10_2() {
        System.out.println(numWays(7));

    }
}
