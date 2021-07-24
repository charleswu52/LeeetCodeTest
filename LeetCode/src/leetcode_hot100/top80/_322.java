package leetcode_hot100.top80;

import java.util.Arrays;

/**
 * @author WuChao
 * @create 2021/7/24 10:56
 */
public class _322 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 322. 零钱兑换
     * 难度：medium
     * <p>
     * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
     * <p>
     * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
     * <p>
     * 你可以认为每种硬币的数量是无限的。
     *
     * <p>
     * 输入：coins = [1, 2, 5], amount = 11
     * 输出：3
     * 解释：11 = 5 + 5 + 1
     * <p>
     * 输入：coins = [2], amount = 3
     * 输出：-1
     * <p>
     * 1 <= coins.length <= 12
     * 1 <= coins[i] <= 231 - 1
     * 0 <= amount <= 104
     */

    /*
    思路：动态规划
    dp[i]表示能凑到金额i所需的最少硬币个数,假设在计算dp[i]之前已经计算出 do[0] 到 dp[i-1]的答案
    dp[i] = min(j=0,...n-1) dp[i-coin_j]+1

    采用自下而上的方式进行思考。仍定义 dp(i) 为组成金额 i 所需最少的硬币数量，假设在计算 dp(i) 之前，
    我们已经计算出 dp(0)...dp(i−1) 的答案。 则 dp(i) 对应的转移方程应为
        dp(i)=min_{j=0…n−1} dp(i−c_j) +1 ,其中 c_j 代表的是第 j 枚硬币的面值，即我们枚举最后一枚硬币面额是 c_j，那么需要从 i-c_j
        这个金额的状态 dp(i-c_j) 转移过来，再算上枚举的这枚硬币数量 1 的贡献，由于要硬币数量最少，
        所以 dp(i) 为前面能转移过来的状态的最小值加上枚举的硬币数量 1 。
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];

    }
}
