package chp8;

import org.junit.Test;

/**
 * @author WuChao
 * @since 2021/5/10 上午8:24
 */
public class _1 {
    /**
     * 程序员面试金典(version 6) - 面试题 08.01. 三步问题
     * 难度: easy
     * <p>
     * 三步问题。有个小孩正在上楼梯，楼梯有n阶台阶，小孩一次可以上1阶、2阶或3阶。
     * 实现一种方法，计算小孩有多少种上楼梯的方式。结果可能很大，你需要对结果模1000000007。
     *
     *
     * <p>
     * 示例:
     * 输入：n = 3
     * 输出：4
     * 说明: 有四种走法
     * <p>
     * 输入：n = 5
     * 输出：13
     *
     * <p>
     * 数据范围：
     * n范围在[1, 1000000]之间
     */

    /*
    思路：最简单的动态规划问题
    dp[n]表示登上第n集阶梯的走法：
    初始状态:
            dp[0] =1;dp[1] =1;
            dp[2]= dp[0]+dp[1]=2;
    状态转移方程：
            dp[n] = dp[n-3]+dp[n-2]+dp[n-1]; n>=3
     */
    public int waysToStep(int n) {
        if (n == 1) {
            return 1;

        }
        if (n == 2) {
            return 2;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000007;
            dp[i] = (dp[i] + dp[i - 3]) % 1000000007;
        }
        return dp[n];

    }

    @Test
    public void test() {
        System.out.println(waysToStep(61));
    }
}
