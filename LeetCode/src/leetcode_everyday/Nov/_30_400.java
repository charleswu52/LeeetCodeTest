package leetcode_everyday.Nov;

/**
 * @author WuChao
 * @create 2021/11/30 9:35
 */
public class _30_400 {
    /**
     * 每日一题：2021/11/30
     * <p>
     * 400. 第 N 位数字
     * <p>
     * 难度：medium
     * <p>
     * 给你一个整数 n ，请你在无限的整数序列 [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...] 中找出并返回第 n 位数字。
     * <p>
     * 示例
     * <p>
     * 输入：n = 11
     * <p>
     * 输出：0
     * <p>
     * 解释：第 11 位数字在序列 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... 里是 0 ，它是 10 的一部分。
     * <p>
     * 范围
     * <p>
     * 1 <= n <= 2^31 - 1
     */

    /*
    思路1：暴力--> 超出内存限制
    思路2：数学模拟
     */
    public int findNthDigit(int n) {
        int a = 1, b = 9;
        while (n > (long)a * b) {
            n -= a * b;
            a++;
            b *= 10;
        }
        int idx = n - 1;
        int start = (int) Math.pow(10, a - 1);
        int num = start + idx / a;
        int digitIndex = idx % a;
        int digit = (num / (int) Math.pow(10, a - digitIndex - 1)) % 10;
        return digit;

    }
}
