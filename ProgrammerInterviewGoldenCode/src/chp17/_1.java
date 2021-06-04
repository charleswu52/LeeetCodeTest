package chp17;

import org.junit.Test;

/**
 * @author WuChao
 * @since 2021/6/4 上午9:26
 */
public class _1 {
    /**
     * 程序员面试金典(version 6) -  面试题 17.01. 不用加号的加法
     * 难度: easy
     * <p>
     * 设计一个函数把两个数字相加。不得使用 + 或者其他算术运算符。
     * <p>
     * 示例:
     * 输入: a = 1, b = 1
     * 输出: 2
     *
     * <p>
     * 数据范围：
     */

    /*
    思路:位运算实现加法
    x^y //执行加法
    (x&y)<<1 //进位操作

     */
    public int add(int a, int b) {
        int jw = a & b;// 进位
        int jg = a ^ b;// 个位
        while (jw!=0) {
            int t_a = jg;
            int t_b = jw << 1;
            jw = t_a & t_b;
            jg = t_a ^ t_b;
        }
        return jg;

    }

    @Test
    public void test() {
        int a = 200, b = 100;
        System.out.println(add(a, b));
    }
}
