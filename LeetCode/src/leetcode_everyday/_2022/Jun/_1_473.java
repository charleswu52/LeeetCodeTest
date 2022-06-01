package leetcode_everyday._2022.Jun;

import java.util.Arrays;

/**
 * @Author CharlesWu
 * @Create 2022/6/1 9:22
 * @Version 1.0
 * @Description
 * @Note
 */
public class _1_473 {
    /**
     * 每日一题：2022/6/1
     * 473. 火柴拼正方形
     * 难度: medium
     * <p>
     * 你将得到一个整数数组 matchsticks ，其中 matchsticks[i] 是第 i 个火柴棒的长度。你要用 所有的火柴棍 拼成一个正方形。
     * 你 不能折断 任何一根火柴棒，但你可以把它们连在一起，而且每根火柴棒必须 使用一次 。
     *
     * 如果你能使这个正方形，则返回 true ，否则返回 false 。
     * <p>
     * 示例:
     * 输入: matchsticks = [1,1,2,2,2]
     *
     * 输出: true
     *
     * 解释: 能拼成一个边长为2的正方形，每边两根火柴。
     * <p>
     * 数据范围：
     * 1 <= matchsticks.length <= 15
     * 1 <= matchsticks[i] <= 10^8
     */


    /*
    思路：状态压缩 + 动态规划
     */
    public boolean makesquare(int[] matchsticks) {
        int sum = Arrays.stream(matchsticks).sum();
        if (sum % 4 != 0) {
            return false;
        }
        int len = sum / 4, n = matchsticks.length;
        int[] dp = new int[1 << n];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int state = 1; state < (1 << n); state++) {
            for (int i = 0; i < n; i++) {
                if ((state & (1 << i)) == 0) {
                    continue;
                }
                int s1 = state & ~(1 << i);
                if (dp[s1] >= 0 && dp[s1] + matchsticks[i] <= len) {
                    dp[state] = (dp[s1] + matchsticks[i]) % len;
                    break;
                }
            }
        }
        return dp[(1 << n) - 1] == 0;


    }
}

