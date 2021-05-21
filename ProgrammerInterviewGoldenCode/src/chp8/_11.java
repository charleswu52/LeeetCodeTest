package chp8;

/**
 * @author WuChao
 * @since 2021/5/21 上午9:07
 */
public class _11 {
    /**
     * 程序员面试金典(version 6) - 面试题 08.11. 硬币
     * 难度: medium
     * <p>
     * 硬币。给定数量不限的硬币，币值为25分、10分、5分和1分，编写代码计算n分有几种表示法。(结果可能会很大，你需要将结果模上1000000007)
     *
     * <p>
     * 示例:
     * 输入: n = 5
     * 输出：2
     * 解释: 有两种方式可以凑成总金额:
     * 5=5
     * 5=1+1+1+1+1
     * <p>
     * 输入: n = 10
     * 输出：4
     * 解释: 有四种方式可以凑成总金额:
     * 10=10
     * 10=5+5
     * 10=5+1+1+1+1+1
     * 10=1+1+1+1+1+1+1+1+1+1
     * <p>
     * 数据范围：
     * 0 <= n (总金额) <= 1000000
     */

    /*
    动态规划：属于 完全背包+
     */
    public int waysToChange(int n) {
        int[] dp = new int[n + 1];

        int[] coins = new int[]{1,5,10,25};


        //刚好可以用一个硬币凑成的情况，是一种情况
        // while i == coin :
        //dp[i] = dp[i - coin] => dp[0]
        dp[0] = 1;

        /**
         * dp方程：dp[i] += dp[i - coin];
         */

        for(int coin : coins) {
            for(int i = coin; i <= n; i++) {
                dp[i] = (dp[i] + dp[i - coin]) % 1000000007;
            }
        }

        return dp[n];

    }

}
