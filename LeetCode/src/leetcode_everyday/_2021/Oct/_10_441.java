package leetcode_everyday._2021.Oct;

/**
 * @author WuChao
 * @create 2021/10/10 21:44
 */
public class _10_441 {
    /**
     * 每日一题：2021/10/10
     * <p>
     * 441. 排列硬币
     * <p>
     * 难度：easy
     * <p>
     * 你总共有 n 枚硬币，并计划将它们按阶梯状排列。对于一个由 k 行组成的阶梯，其第 i 行必须正好有 i 枚硬币。阶梯的最后一行 可能 是不完整的。
     * 给你一个数字 n ，计算并返回可形成 完整阶梯行 的总行数。
     * <p>
     * 示例 1：
     * <p>
     * 输入：n = 5
     * <p>
     * 输出：2
     * <p>
     * 解释：因为第三行不完整，所以返回 2 。
     * <p>
     * 示例 2：
     * <p>
     * 输入：n = 8
     * <p>
     * 输出：3
     * <p>
     * 解释：因为第四行不完整，所以返回 3 。
     */

    /*
    思路1： 数学公式
    前1+2+3+...+n = n(n+1)/2
    设给定的值为n，得到 x^2 + x -2n = 0
    问题变为求x的值，二元一次方程求解 (根号(1+8n) -1)/2 即为解
     */
    public int arrangeCoins(int n) {
        return (int) (Math.sqrt(1 + (long) 8 * n) - 1) / 2;

    }

    /*
    思路2：二分查找
    同样的还是利用那个求和公式，只不过这里是用二分查找结果
     */
    public int arrangeCoins2(int n) {
        int left = 1, right = n;
        while (left < right) {
            int mid = left + ((right - left + 1) >> 1);
            if ((long) mid * (mid + 1) <= (long) 2 * n) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;

    }

}

