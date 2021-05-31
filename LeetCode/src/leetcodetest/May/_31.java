package leetcodetest.May;

/**
 * @author WuChao
 * @since 2021/5/31 上午8:05
 */
public class _31 {
    /**
     * 每日一题：2021/5/31
     * 342. 4的幂
     * 难度: easy
     * <p>
     * 给定一个整数，写一个函数来判断它是否是 4 的幂次方。如果是，返回 true ；否则，返回 false 。
     *
     * 整数 n 是 4 的幂次方需满足：存在整数 x 使得 n == 4^x
     *
     * <p>
     * 示例:
     * 输入：n = 16
     * 输出：true
     *
     * 输入：n = 1
     * 输出：true
     * <p>
     * 数据范围：
     * -2^31 <= n <= 2^31 - 1
     *
     * 进阶：你能够不使用循环/递归解决此问题吗
     */
    /*
    思路：同 昨天 _30题目，使用换底公式求解
     */
    public boolean isPowerOfFour(int n) {
        double v = Math.log10(n) / Math.log10(4);
        return v == (int) v;
    }
}
