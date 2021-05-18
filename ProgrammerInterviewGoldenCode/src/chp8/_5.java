package chp8;

import org.junit.Test;

/**
 * @author WuChao
 * @since 2021/5/18 上午10:52
 */
public class _5 {
    /**
     * 程序员面试金典(version 6) - 面试题 08.04. 幂集
     * 难度: medium
     * <p>
     * 递归乘法。 写一个递归函数，不使用 * 运算符， 实现两个正整数的相乘。可以使用加号、减号、位移，但要吝啬一些。
     * <p>
     * 示例:
     * 输入：A = 3, B = 4
     * 输出：12
     * <p>
     * 输入：A = 1, B = 10
     * 输出：10
     * <p>
     * 数据范围：
     * 保证乘法范围不会溢出
     */

    /*
    思路：使用位运算实现

     */
    public int multiply(int A, int B) {
        int res = 0;
        if (A > B) {
            int t = A;
            A = B;
            B = A;
        }
        for (int i = 0; i < A; i++) {
            res += B;
        }
        return res;

    }

    int add(int a, int b) {
        int carry, add;
        do {
            add = a ^ b;
            carry = (a & b) << 1;
            a = add;
            b = carry;
        } while (carry != 0);
        return add;
    }

    @Test
    public void test() {
        int a = 3, b = 4;
        System.out.println(multiply(a, b));
    }
}
