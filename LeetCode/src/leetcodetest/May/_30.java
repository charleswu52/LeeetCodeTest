package leetcodetest.May;

import org.junit.Test;

/**
 * @author WuChao
 * @since 2021/5/30 上午8:31
 */
public class _30 {
    /**
     * 每日一题：2021/5/30
     * 231. 2 的幂
     * 难度: easy
     * <p>
     * 给你一个整数 n，请你判断该整数是否是 2 的幂次方。如果是，返回 true ；否则，返回 false 。
     *
     * 如果存在一个整数 x 使得 n == 2^x ，则认为 n 是 2 的幂次方。
     *
     * <p>
     * 示例:
     * 输入：n = 16
     * 输出：true
     * 解释：24 = 16
     *
     * <p>
     * 数据范围：
     * -231 <= n <= 231 - 1
     *
     * 进阶：你能够不使用循环/递归解决此问题吗
     */

    /*
    思路1：数学-- 使用换底公式
    原题 2^x = n ==> x = log_2 n
    使用 换底公式 ==> x = (log_10 n) / (log_10 2)
    如果 x 是整数返回true ,否则返回 false
     */
    public boolean isPowerOfTwo(int n) {
        double log = Math.log10(n) / Math.log10(2);
        return log == (int) log;
    }

    @Test
    public void test() {
        int n = 1;
        System.out.println(isPowerOfTwo(n));
    }

}
