package chp5;

import com.sun.imageio.plugins.common.I18N;
import org.junit.Test;

/**
 * @author WuChao
 * @since 2021/5/7 上午10:24
 */
public class _1 {
    /**
     * 程序员面试金典(version 6) - 面试题 05.01. 插入
     * 难度: easy
     * <p>
     * 给定两个整型数字 N 与 M，以及表示比特位置的 i 与 j（i <= j，且从 0 位开始计算）。
     * <p>
     * 编写一种方法，使 M 对应的二进制数字插入 N 对应的二进制数字的第 i ~ j 位区域，不足之处用 0 补齐。具体插入过程如图所示。
     * <p>
     * 题目保证从 i 位到 j 位足以容纳 M， 例如： M = 10011，则 i～j 区域至少可容纳 5 位。
     * <p>
     * 示例:
     * 输入：N = 1024(10000000000), M = 19(10011), i = 2, j = 6
     * 输出：N = 1100(10001001100)
     * <p>
     * 输入： N = 0, M = 31(11111), i = 0, j = 4
     * 输出：N = 31(11111)
     *
     * <p>
     * 数据范围：
     */

    public int insertBits(int N, int M, int i, int j) {

        String n = Integer.toBinaryString(N);
        String m = Integer.toBinaryString(M);


        int len = j - i + 1;
        String temp = "";
        if (m.length() < len) {
            for (int k = 0; k < len - m.length(); k++) {
                temp += "0";
            }
        }
        m = temp + m;

        temp = "";
        if (j + 1 > n.length()) {
            for (int k = 0; k < j + 1 - n.length(); k++) {
                temp += "0";
            }
        }
        n = temp + n;

        String res;
        if (n.length() <= m.length()) {
            res = m;
        } else {
            String head = n.substring(0, n.length() - j - 1);
            String rear = n.substring(n.length() - i);
            res = head + m + rear;
        }


        return Integer.parseInt(res, 2);

    }

    /*
    思路2：使用位运算
    取出i位置后面的数，让N左移j+1位后再右移j+1位，保证i~j之间的数全部置为0，M插入N内，实际上M扩大了2的i次方倍，所以让M左移i位，最后在加上i后面的数的总


     */
    public int insertBits2(int N, int M, int i, int j) {
        int index = 0;
        int right = 0;
        // 防止溢出 转换为long类型
        long n = N, m = M;
        while (index <= j) {
            // 取出i后面的数
            if (index < i) {
                right += (Math.pow(2, index) * (n % 2));
            }
            n >>= 1;
            index++;
        }
        n <<= index;
        m <<= i;
        return (int) (n + m + right);
    }


        @Test
    public void test() {
        int N = 1143207437;
        int M = 1017033;
        int i = 11, j = 31;
        System.out.println(insertBits(N, M, i, j));
        System.out.println(insertBits2(N, M, i, j));

    }

}
