package leetcodetest.Jun;

import org.junit.Test;

/**
 * @author WuChao
 * @create 2021/6/23 8:32
 */
public class _23 {
    /**
     * 每日一题：2021/6/23
     * 剑指 Offer 15. 二进制中1的个数
     * 难度: medium
     * <p>
     * 请实现一个函数，输入一个整数（以二进制串形式），输出该数二进制表示中 1 的个数。
     * 例如，把 9 表示成二进制是 1001，有 2 位是 1。因此，如果输入 9，则该函数输出 2。
     * <p>
     *
     * <p>
     * 输入：00000000000000000000000010000000
     * 输出：1
     * 解释：输入的二进制串 00000000000000000000000010000000 中，共有一位为 '1'。
     * <p>
     * 数据范围：
     * 输入必须是长度为 32 的 二进制串 。
     */


    /*
    简单水题
     */
    /*
    方法1: 直接调库
     */
    public int hammingWeight(int n) {
        // 投机取巧的方法
        return Integer.bitCount(n);
    }

    /*
    方法2: 位运算
     */
    public int hammingWeight2(int n) {
        int res = 0;
        while (n != 0) {
            res += n & 1;
            n >>>= 1;
        }
        return res;
    }

    @Test
    public void test() {
        System.out.println(hammingWeight2(9));
    }
}
