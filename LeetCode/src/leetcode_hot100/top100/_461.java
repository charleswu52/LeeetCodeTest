package leetcode_hot100.top100;

/**
 * @author WuChao
 * @create 2021/7/28 10:46
 */
public class _461 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 461. 汉明距离
     * 难度：easy
     * <p>
     * 两个整数之间的 汉明距离 指的是这两个数字对应二进制位不同的位置的数目。
     *
     * 给你两个整数 x 和 y，计算并返回它们之间的汉明距离。
     *
     * <p>
     *示例 1：
     *
     * 输入：x = 1, y = 4
     * 输出：2
     * 解释：
     * 1   (0 0 0 1)
     * 4   (0 1 0 0)
     *        ↑   ↑
     * 上面的箭头指出了对应二进制位不同的位置。
     * 示例 2：
     *
     * 输入：x = 3, y = 1
     * 输出：1
     *
     *
     * <p>
     */

    /*
    思路：位运算
    先对两个数进行异或运算，得到结果
    然后对这个结果计算其中1的个数，通过右移位运算实现
     */

    public int hammingDistance(int x, int y) {
        int ans = x ^ y;
        int res = 0;
        while (ans != 0) {
            res += ans & 1;
            ans >>= 1;
        }
        return res;

    }
}
