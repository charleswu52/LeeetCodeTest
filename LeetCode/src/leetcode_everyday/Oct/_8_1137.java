package leetcode_everyday.Oct;

/**
 * @author WuChao
 * @create 2021/8/9 13:49
 */
public class _8_1137 {
    /**
     * 每日一题：2021/8/8
     * 1137. 第 N 个泰波那契数
     * 难度：easy
     * <p>
     * 泰波那契序列 Tn 定义如下：
     * <p>
     * T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0 的条件下 Tn+3 = Tn + Tn+1 + Tn+2
     * <p>
     * 给你整数 n，请返回第 n 个泰波那契数 Tn 的值。
     *
     *
     * <p>
     * 输入：n = 4
     * 输出：4
     * 解释：
     * T_3 = 0 + 1 + 1 = 2
     * T_4 = 1 + 1 + 2 = 4
     *
     *
     * <p>
     * 注意:
     * 0 <= n <= 37
     * 答案保证是一个 32 位整数，即 answer <= 2^31 - 1。
     */

    /*
    思路：跟斐波那契数列一样，只不过定义略有不同

     */
    public int tribonacci(int n) {
        if (n == 0) {
            return 0;
        } else if (n < 3) {
            return 1;
        }
        int[] ans = new int[n + 1];
        ans[0] = 0;
        ans[1] = 1;
        ans[2] = 1;
        for (int i = 3; i <= n; i++) {
            ans[i] = ans[i - 3] + ans[i - 2] + ans[i - 1];
        }
        return ans[n];
    }
}
