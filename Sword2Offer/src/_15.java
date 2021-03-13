/**
 * @author WuChao
 * @since 2021/3/13 上午10:46
 */
public class _15 {
    /**
     * 剑指 Offer 15. 二进制中1的个数
     * 难度: easy
     * 请实现一个函数，输入一个整数（以二进制串形式），输出该数二进制表示中 1 的个数。
     * 例如，把 9 表示成二进制是 1001，有 2 位是 1。因此，如果输入 9，则该函数输出 2。
     * <p>
     * 示例：
     * 输入: 10
     * 输出: 36
     * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
     * <p>
     * 数据范围：
     * 2 <= n <= 1000
     */

    public int hammingWeight(int n) {
        // 直接调库里的方法
        return Integer.bitCount(n);
    }

    // 按位与运算的方式
    public int hammingWeight2(int n) {
        // 与运算的方式来做 判断最后一位数是否是1,判断完成后右移一位，就这样循环移位判断
        int res = 0;
        while (n != 0) {
            res += n & 1;
            n >>>= 1;    //无符号右移一位 java中无符号右移是 >>>
        }
        return res;//Integer.bitCount(n);
    }


    public void sword2Offer_15() {
        int n = 00000000000000000000000000001011;
        System.out.println(hammingWeight(n));
        System.out.println(hammingWeight2(n));
    }
}
