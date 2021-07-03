package leetcode_everyday.May;

/**
 * @author WuChao
 * @since 2021/5/24 上午7:52
 */
public class _24 {
    /**
     * 每日一题：2021/5/24
     * 664. 奇怪的打印机
     * 难度: hard
     * <p>
     * 有台奇怪的打印机有以下两个特殊要求：
     *
     * 打印机每次只能打印由 同一个字符 组成的序列。
     * 每次可以在任意起始和结束位置打印新字符，并且会覆盖掉原来已有的字符。
     * 给你一个字符串 s ，你的任务是计算这个打印机打印它需要的最少打印次数。
     * <p>
     * 示例：
     * 输入：s = "aaabbb"
     * 输出：2
     * 解释：首先打印 "aaa" 然后打印 "bbb"。
     *
     * 输入：s = "aba"
     * 输出：2
     * 解释：首先打印 "aaa" 然后在第二个位置打印 "b" 覆盖掉原来的字符 'a'。
     * <p>
     * 数据范围：
     * 1 <= s.length <= 100
     * s 由小写英文字母组成
     */

    /*
    思路：使用动态规划
    dp数组dp[i][j] 表示打印s[i,j]所需的最少操作次数。
    考虑两个区间端点处的值来推导出dp数组的递推方程
    当 s[i] = s[j] 时，f[i][j] = f[i][j-1]
    当 s[i]!= s[j] 时，需要分别完成左右两部分区间的打印工作，记两个区间为[i,k]和[k+1,j] (i<=k<j)
                      f[i][j] = min k:i->j-1 (f[i][k] + f[k+1][j])
    边界条件： i = j 时，f[i][j] = 1
    为了保证在计算动态规划的方程时f[i][k]和f[k+1][j]都已经计算过，需要改变一下动态规划的计算顺序，从大到小地枚举i,并从小到大地枚举j，
    这样可以保证当计算f[i][j]时，f[k+1][j]都已经被计算过

     */
    public int strangePrinter(String s) {
        int n = s.length();
        if (n == 0) {
            return n;
        }
        int[][] dp = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i][j - 1];
                } else {
                    int minn = Integer.MAX_VALUE;
                    for (int k = i; k < j; k++) {
                        minn = Math.min(minn, dp[i][k] + dp[k + 1][j]);
                    }
                    dp[i][j] = minn;
                }
            }
        }
        return dp[0][n - 1];
    }
}
