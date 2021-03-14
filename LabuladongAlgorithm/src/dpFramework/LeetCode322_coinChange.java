package dpFramework;

import java.util.Arrays;

/**
 * @author WuChao
 * @since 2021/3/14 下午3:41
 */
public class LeetCode322_coinChange {
    /**
     * 学习DP框架的例子
     * 322. 零钱兑换
     * 难度: medium
     * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
     * 如果没有任何一种硬币组合能组成总金额，返回 -1。
     * 你可以认为每种硬币的数量是无限的。
     * <p>
     * 输入：coins = [1, 2, 5], amount = 11
     * 输出：3
     * 解释：11 = 5 + 5 + 1
     * <p>
     * 数据范围：
     * 1 <= coins.length <= 12
     * 1 <= coins[i] <= 231 - 1
     * 0 <= amount <= 104
     */

    // 暴力递归
    public int coinChange1(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }
        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            int sub = coinChange1(coins, amount - coin);
            if (sub < 0) {
                continue;
            }
            res = Math.min(res, sub + 1);
        }
        return res != Integer.MAX_VALUE ? res : -1;

    }

    // 带备忘录的递归
    private int[] store;

    public int coinChange2(int[] coins, int amount) {
        this.store = new int[amount + 1];
        Arrays.fill(store, -2);
        return dp(coins, amount);

    }

    public int dp(int[] coins, int amount) {
        if (amount < 0) {
            return -1;
        }
        if (store[amount] != -2) {
            return store[amount];
        }
        if (amount == 0) {
            return 0;
        }
        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            int sub = dp(coins, amount - coin);
            if (sub == -1) {
                continue;
            }
            res = Math.min(res, sub + 1);
        }
        store[amount] = res != Integer.MAX_VALUE ? res : -1;
        return store[amount];
    }

    // dp数组的迭代解法
    public int coinChange3(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        // base case
        dp[0] = 0;
        // 外层循环遍历所有状态的所有取值
        for (int i = 0; i < dp.length; i++) {
            // 内层循环求所有选择的最小值
            for (int coin : coins) {
                // 子问题无解
                if (i - coin < 0) {
                    continue;
                }
                dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }


    public static void main(String[] args) {
        LeetCode322_coinChange test = new LeetCode322_coinChange();
        int[] coins = {1, 2, 5};
        int amount = 11;
        System.out.println(test.coinChange1(coins, amount));
        System.out.println(test.coinChange2(coins, amount));
        System.out.println(test.coinChange3(coins, amount));

    }
}
