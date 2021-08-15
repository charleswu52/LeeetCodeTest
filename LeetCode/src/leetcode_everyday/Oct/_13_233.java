package leetcode_everyday.Oct;

/**
 * @author WuChao
 * @create 2021/8/15 15:33
 */
public class _13_233 {
    /**
     * 每日一题：2021/8/13
     * 233. 数字 1 的个数
     * 难度：hard
     * <p>
     * 给定一个整数 n，计算所有小于等于 n 的非负整数中数字 1 出现的个数。
     *
     * <p>
     * 示例
     * 输入：n = 13
     * 输出：6
     *
     * 输入：n = 0
     * 输出：0
     * <p>
     * 注意:
     * 0 <= n <= 2 * 10^9
     */

    public int countDigitOne(int n) {
        // mulk 表示 10^k
        // 在下面的代码中，可以发现 k 并没有被直接使用到（都是使用 10^k）
        // 但为了让代码看起来更加直观，这里保留了 k
        long mulk = 1;
        int ans = 0;
        for (int k = 0; n >= mulk; ++k) {
            ans += (n / (mulk * 10)) * mulk + Math.min(Math.max(n % (mulk * 10) - mulk + 1, 0), mulk);
            mulk *= 10;
        }
        return ans;
    }


}
