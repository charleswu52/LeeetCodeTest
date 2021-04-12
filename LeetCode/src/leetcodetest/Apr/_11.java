package leetcodetest.Apr;

/**
 * @author WuChao
 * @since 2021/4/11 上午7:59
 */
public class _11 {
    /**
     * 每日一题：2021/4/10
     * 264. 丑数 II
     * 难度: medium
     * <p>
     * 给你一个整数 n ，请你找出并返回第 n 个 丑数 。
     * <p>
     * 丑数 就是只包含质因数 2、3 和/或 5 的正整数。
     * <p>
     * 示例：
     * 输入：n = 10
     * 输出：12
     * 解释：[1, 2, 3, 4, 5, 6, 8, 9, 10, 12] 是由前 10 个丑数组成的序列。.
     *
     * <p>
     * 数据范围：
     * 1 <= n <= 1690
     */

    /*
    题目解析：使用动态规划
    根据丑数的递推性质： 丑数只包含因子 2, 3, 52,3,5 ，因此有 “丑数 = 某较小丑数 × 某因子” （例如：10=5×2）。
    设已知长度为 n 的丑数序列 x_1, x_2,..., x_n，求第 n+1n+1 个丑数 x_n+1。根根据递推性质，
    丑数 x_n+1 只可能是以下三种情况其中之一（索引 a, b, c为未知数）：
    x_n+1 = x_a*2,x_b*3,x_c*5
    其中 a,b,c是索引下标属于[1,n]
    递推公式： x_n+1 = min(x_a*2,x_b*3,x_c*5)
    a,b,c三个索引下标分别为乘2 3 5，向前移动的前提是其数等于当前dp[i]的值
     */
    public int nthUglyNumber(int n) {
        int[] dp = new int[n];
        dp[0] = 1;  // 第一个丑数是1
        int a = 0, b = 0, c = 0; // 前面较小丑数的索引下标
        for (int i = 1; i < n; i++) {
            int x = dp[a] * 2, y = dp[b] * 3, z = dp[c] * 5;
            dp[i] = Math.min(x, Math.min(y, z));
            if (dp[i] == x) a++;
            if (dp[i] == y) b++;
            if (dp[i] == z) c++;
        }
        return dp[n - 1];
    }

}
