package leetcode_everyday._2021.Mar;

import org.junit.Test;

/**
 * @author WuChao
 * @since 2021/3/29 上午7:45
 */
public class _29 {
    /**
     * 每日一题：2021/3/29
     * 190. 颠倒二进制位
     * 难度: easy
     * 颠倒给定的 32 位无符号整数的二进制位。
     * <p>
     * 示例：
     * 输入: 00000010100101000001111010011100
     * 输出: 00111001011110000010100101000000
     * 解释: 输入的二进制串 00000010100101000001111010011100 表示无符号整数 43261596，
     *      因此返回 964176192，其二进制表示形式为 00111001011110000010100101000000。
     *
     * <p>
     * 数据范围：
     * 输入是一个长度为 32 的二进制字符串
     */

    /**
     * 这个就很骚了，直接调用的Java中Integer类中的reverse(int n)方法将数字进行反转
     * 源码中的实现可参考 Integer的reverse方法
     * @param n
     * @return
     */
    public int reverseBits(int n) {
        return Integer.reverse(n);

    }

    @Test
    public void test() {
        int n = 429496793;
        System.out.println(reverseBits(n));

    }
}
