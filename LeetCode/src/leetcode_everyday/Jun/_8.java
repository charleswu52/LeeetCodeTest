package leetcode_everyday.Jun;

import java.util.Arrays;

/**
 * @author WuChao
 * @create 2021/6/8 上午8:39
 */
public class _8 {
    /**
     * 每日一题：2021/6/8
     * 1049. 最后一块石头的重量 II
     * 难度: medium
     * <p>
     * 有一堆石头，用整数数组 stones 表示。其中 stones[i] 表示第 i 块石头的重量。
     *
     * 每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
     *
     * 如果 x == y，那么两块石头都会被完全粉碎；
     * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
     * 最后，最多只会剩下一块 石头。返回此石头 最小的可能重量 。如果没有石头剩下，就返回 0。
     * <p>
     * 示例:
     * 输入：stones = [2,7,4,1,8,1]
     * 输出：1
     * 解释：
     * 组合 2 和 4，得到 2，所以数组转化为 [2,7,1,8,1]，
     * 组合 7 和 8，得到 1，所以数组转化为 [2,1,1,1]，
     * 组合 2 和 1，得到 1，所以数组转化为 [1,1,1]，
     * 组合 1 和 1，得到 0，所以数组转化为 [1]，这就是最优值。
     *
     * 输入：stones = [31,26,33,21,40]
     * 输出：5
     *
     * 输入：stones = [1,2]
     * 输出：1
     * <p>
     * 数据范围：
     * 1 <= stones.length <= 30
     * 1 <= stones[i] <= 100
     */

    /*

    题目解析：
    题意来说很好懂，但是要将题目进行转化 ！
    变成对每个石头的重量取 + 或 - ，然后对所有石头的重量求和，求最小值

    思路：  动态规划
    记石头的总重量为 sum，k_i=-1  的石头的重量之和为 neg，则其余 k_i=1 的石头的重量之和为 sum−neg。
    这样最终所有石头的重量为 sum - 2*neg
    要使最后一块石头的重量尽可能地小，neg 需要在不超过 ⌊sum/2⌋ 的前提下尽可能地大。
    因此本问题可以看作是背包容量为 ⌊sum/2⌋，物品重量和价值均为 stones_i 的 0-1 背包问题。

    对于该问题，定义二维布尔数组 dp，其中 dp[i+1][j] 表示前 i 个石头能否凑出重量 j。特别地，dp[0][] 为不选任何石头的状态，
    因此除了 dp[0][0] 为真，其余 dp[0][j] 全为假。

    对于第 i 个石头，考虑凑出重量 j：
        若 j<stones[i]，则不能选第 i 个石头，此时有 dp[i+1][j]=dp[i][j]；
        若 j≥stones[i]，存在选或不选两种决策，不选时有 dp[i+1][j]=dp[i][j]，选时需要考虑能否凑出重量 j−stones[i]，
            即 dp[i+1][j]=dp[i][j−stones[i]]。若二者均为假则 dp[i+1][j] 为假，否则 dp[i+1][j] 为真。
     */

    public int lastStoneWeightII(int[] stones) {
        int sum = Arrays.stream(stones).sum();
        int m = sum / 2, n = stones.length;
        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (j < stones[i]) {
                    dp[i + 1][j] = true;

                } else {
                    dp[i + 1][j] = dp[i][j] || dp[i][j - stones[i]];
                }
            }
        }
        for (int j = m; ; j--) {
            if (dp[n][j]) {
                return sum * 2 - j;
            }
        }

    }
}
