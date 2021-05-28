package chp16;

/**
 * @author WuChao
 * @since 2021/5/28 下午12:46
 */
public class _7 {
    /**
     * 程序员面试金典(version 6) - 面试题 16.07. 最大数值
     * 难度: easy
     * <p>
     * 编写一个方法，找出两个数字a和b中最大的那一个。不得使用if-else或其他比较运算符。
     *
     * <p>
     * 示例:
     * 输入： a = 1, b = 2
     * 输出： 2
     *
     * <p>
     * 数据范围：
     * 1 <= a.length, b.length <= 100000
     * -2147483648 <= a[i], b[i] <= 2147483647
     * 正确结果在区间 [0, 2147483647] 内
     */

    public int maximum(int a, int b) {
        long diff = Math.abs((long) a - (long) b);
        return (int) (((long)a+(long) b+diff)/2);


    }
}
