package leetcode_everyday.May;

import org.junit.Test;

/**
 * @author WuChao
 * @since 2021/5/27 上午8:03
 */
public class _27 {
    /**
     * 每日一题：2021/5/27
     * 461. 汉明距离
     * 难度: easy
     * <p>
     *两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
     *
     * 给出两个整数 x 和 y，计算它们之间的汉明距离。
     *
     * 注意：
     * 0 ≤ x, y < 231.
     *
     * <p>
     * 示例:
     * 输入: x = 1, y = 4
     *
     * 输出: 2
     *
     * 解释:
     * 1   (0 0 0 1)
     * 4   (0 1 0 0)
     *        ↑   ↑
     *
     * 上面的箭头指出了对应二进制位不同的位置。
     *
     * <p>
     * 数据范围：
     */

    public int hammingDistance(int x, int y) {
        int xor = x ^ y;
        int res = 0;
        while (xor != 0) {
            if ((xor & 1) == 1) {
                res++;
            }
            xor >>= 1;
        }
        return res;

    }

    @Test
    public void test() {
        int x = 1, y = 4;
        System.out.println(hammingDistance(x, y));
    }
}
