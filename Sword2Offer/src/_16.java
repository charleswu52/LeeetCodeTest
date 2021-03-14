/**
 * @author WuChao
 * @since 2021/3/14 上午8:54
 */
public class _16 {
    /**
     * 剑指 Offer 16. 数值的整数次方
     * 难度: medium
     * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，x^n）。不得使用库函数，同时不需要考虑大数问题。
     * <p>
     * 示例：
     * 输入：x = 2.00000, n = 10
     * 输出：1024.00000
     *
     * 输入：x = 2.00000, n = -2
     * 输出：0.25000
     * 解释：2-2 = 1/22 = 1/4 = 0.25
     * <p>
     * 数据范围：
     * -100.0 < x < 100.0
     * -2^31 <= n <= 2^31-1
     * -10^4 <= xn <= 10^4
     */

    /**
     * 题解：快速幂（二分法）
     * 二分推导： x^n = x^{n/2} * x^{n/2} = (x^2)^{n/2}，令 n/2 为整数，则需要分为奇偶两种情况
     *          当 n 为偶数： x^n = (x^2)^{n/2}
     *          当 n 为奇数： x^n = x(x^2)^{n/2}x
     * 运算的小技巧：转化为位运算
     *  向下整除 n / 2 等价于 右移一位 n>>1 ；
     *  取余数  n % 2 等价于 判断二进制最右一位值 n& 1 ；
     */
    public double myPow(double x, int n) {
        if (x == 0) {
            return 0;
        }
        if (n == 0) {
            return 1;
        }
        if (x == 1) {
            return 1;
        }
        if (x == -1) {
            if (n < 0) {
                n = -n;
            }
            if (n % 2 == 0) {
                return 1;
            } else {
                return -1;
            }
        }

        /*
        long b = n;
        double res = 1;
        if (b < 0) {
            x = 1 / x;
            b = -b;
        }
        while (b > 0) {
            if ((b & 1) == 1) {
                res *= x;
            }
            x *= x;
            b >>= 1;
        }
        */
        long b = n;
        if (b < 0) {
            x = 1 / x;
            b = -b;
        }
        double res2 = 1;
        for (; b > 0; b >>= 1) {
            if ((b & 1) == 1) {
                res2 *= x;
            }
            x *= x;
        }
        return res2;

    }
    public void sword2Offer_16() {
        double x = 2;
        int n = 10;
        System.out.println(myPow(x, n));

    }
}
