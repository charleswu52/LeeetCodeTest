package leetcode_everyday._2021.Oct;

/**
 * @author WuChao
 * @create 2021/10/12 22:21
 */
public class _12_29 {
    /**
     * 每日一题：2021/10/12
     * <p>
     * 29. 两数相除
     * <p>
     * 难度：medium
     * <p>
     * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
     * <p>
     * 返回被除数 dividend 除以除数 divisor 得到的商。
     * <p>
     * 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
     * <p>
     * 示例 1：
     * <p>
     * 输入: dividend = 10, divisor = 3
     * <p>
     * 输出: 3
     * <p>
     * 解释: 10/3 = truncate(3.33333..) = truncate(3) = 3
     * <p>
     * 提示
     * <p>
     * 被除数和除数均为 32 位有符号整数。
     * <p>
     * 除数不为 0。
     * <p>
     * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−2^31, 2^31− 1]。本题中，如果除法结果溢出，则返回 2^31− 1。
     */

    /*
    思路：越界问题只要对除数是1和-1单独讨论就完事了啊
    关于如何提高效率快速逼近结果

    举个例子：11 除以 3 。
    首先11比3大，结果至少是1， 然后我让3翻倍，就是6，发现11比3翻倍后还要大，那么结果就至少是2了，那我让这个6再翻倍，得12，11不比12大，
    吓死我了，差点让就让刚才的最小解2也翻倍得到4了。但是我知道最终结果肯定在2和4之间。也就是说2再加上某个数，这个数是多少呢？
    我让11减去刚才最后一次的结果6，剩下5，我们计算5是3的几倍，也就是除法，看，递归出现了。

    简言之。整理了一下思路，可以简单概括为： 60/8 = (60-32)/8 + 4 = (60-32-16)/8 + 2 + 4 = 1 + 2 + 4 = 7
     */

    public int divide(int dividend, int divisor) {
        // 先处理边界情况
        if (dividend == 0) {
            return 0;
        }
        if (divisor == 1) {
            return dividend;
        }
        if (divisor == -1) {
            if (dividend > Integer.MIN_VALUE) {
                return -dividend;
            } else {
                return Integer.MAX_VALUE;
            }
        }

        // 对商使用加法运算累加得到结果
        long a = dividend;
        long b = divisor;
        int sign = 1;
        if (a > 0 && b < 0 || a < 0 && b > 0) {
            sign = -1;
        }
        a = a > 0 ? a : -a;
        b = b > 0 ? b : -b;
        // 递归
        int res = div(a, b);
        if (sign > 0) {
            return res > Integer.MAX_VALUE ? Integer.MAX_VALUE : res;
        } else {
            return -res;
        }

    }

    public int div(long a, long b) {
        if (a < b) {
            return 0;
        }
        int count = 1;
        long tempB = b;
        while ((tempB + tempB) <= a) { // 被除数翻倍
            count = count + count; //解也翻倍
            tempB = tempB + tempB;
        }
        return count + div(a - tempB, b);
    }

}

