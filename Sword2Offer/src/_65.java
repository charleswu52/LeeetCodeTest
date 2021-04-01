import org.junit.Test;

/**
 * @author WuChao
 * @since 2021/4/1 上午10:49
 */
public class _65 {
    /**
     * 剑指 Offer 65. 不用加减乘除做加法
     * 难度: easy
     * <p>
     * 写一个函数，求两个整数之和，要求在函数体内不得使用 “+”、“-”、“*”、“/” 四则运算符号。
     * <p>
     * 例如：
     * 输入: a = 1, b = 1
     * 输出: 2
     *
     * <p>
     * 数据范围：
     * a, b 均可能是负数或 0
     * 结果不会溢出 32 位整数
     */

    /*
    题意解析：求两个数之和但是不允许使用加法求和
    显然是要考察差 位运算
     */

    /**
     * 使用位运算的方式求两个数的和
     *
     * ，无进位和 与 异或运算 规律相同，进位 和 与运算 规律相同（并需左移一位）。因此，无进位和 nn 与进位 cc 的计算公式如下；
     *  n=a⊕b         非进位和：异或运算
     *  c=a&b<<1      进位：与运算+左移一位
     *  和 s ==（非进位和 n ）+（进位 c ）。即可将 s=a+b 转化为：
     *  s=a+b⇒s=n+c
     *  循环求 n 和 c ，直至进位 c = 0 ；此时 s = n ，返回 n 即可。
     *  链接：http://dwz.date/eDdx
     * @param a
     * @param b
     * @return
     */
    public int add(int a, int b) {
        while(b != 0) { // 当进位为 0 时跳出
            int c = (a & b) << 1;  // c = 进位
            a ^= b; // a = 非进位和
            b = c; // b = 进位
        }
        return a;
    }

    @Test
    public void test() {
        int a = 4;
        int b = 3;
        System.out.println(add(a, b));

    }
}
