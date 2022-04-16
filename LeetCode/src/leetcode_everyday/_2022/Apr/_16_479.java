package leetcode_everyday._2022.Apr;

/**
 * @author WuChao
 * @create 2022/4/16 8:53
 */
public class _16_479 {
    /**
     * 每日一题：2022/4/16
     * <p>
     * 479. 最大回文数乘积
     * <p>
     * 难度：hard
     * <p>
     * 给定一个整数 n ，返回 可表示为两个 n 位整数乘积的 最大回文整数 。因为答案可能非常大，所以返回它对 1337 取余
     * <p>
     * 示例
     * <p>
     * 示例 1:
     * <p>
     * 输入：n = 2
     * <p>
     * 输出：987
     * <p>
     * 解释：99 x 91 = 9009, 9009 % 1337 = 987
     * <p>
     * 范围
     * <p>
     * 1 <= n <= 8
     */
    int mod = 1337;
    public int largestPalindrome(int n) {
        if (n == 1) {
            return 9;
        }
        int upper = (int) Math.pow(10, n) - 1;
        int ans = 0;
        for (int left = upper; ans == 0; --left) { // 枚举回文数的左半部分
            long p = left;
            for (int x = left; x > 0; x /= 10) {
                p = p * 10 + x % 10; // 翻转左半部分到自身末尾，构造回文数p
            }
            for (long x = upper; x * x >= p; --x) {
                if (p % x == 0) { // p是x的因子
                    ans = (int) (p % mod);
                    break;
                }
            }
        }
        return ans;

    }
}
