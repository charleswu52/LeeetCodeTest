package leetcode_everyday._2022.Jun;

/**
 * @Author CharlesWu
 * @Create 2022/6/10 11:06
 * @Version 1.0
 * @Description
 * @Note
 */
public class _10_730 {
    /**
     * 每日一题：2022/6/10
     * 730. 统计不同回文子序列
     * 难度: hard
     * <p>
     * 给定一个字符串 s，返回 s 中不同的非空「回文子序列」个数 。
     * <p>
     * 通过从 s 中删除 0 个或多个字符来获得子序列。
     * <p>
     * 如果一个字符序列与它反转后的字符序列一致，那么它是「回文字符序列」。
     * <p>
     * 如果有某个 i , 满足 ai != bi ，则两个序列 a1, a2, ... 和 b1, b2, ... 不同。
     * <p>
     * 注意：
     * <p>
     * 结果可能很大，你需要对 109 + 7 取模 。
     * <p>
     * 示例:
     * <p>
     * 输入：s = 'bccb'
     * 输出：6
     * 解释：6 个不同的非空回文子字符序列分别为：'b', 'c', 'bb', 'cc', 'bcb', 'bccb'。
     * 注意：'bcb' 虽然出现两次但仅计数一次。
     * <p>
     * 数据范围：
     * <p>
     * 1 <= s.length <= 1000
     * s[i] 仅包含 'a', 'b', 'c' 或 'd'
     */

    /*
    思路：动态规划
     */
    public int countPalindromicSubsequences(String s) {
        final int mod = 1000000007;
        int n = s.length();
        int[][][] dp = new int[4][n][n];
        for (int i = 0; i < n; i++) {
            dp[s.charAt(i) - 'a'][i][i] = 1;
        }
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len <= n; i++) {
                int j = i + len - 1;
                for (char c = 'a'; c <= 'd'; c++) {
                    int k = c - 'a';
                    if (s.charAt(i) == c && s.charAt(j) == c) {
                        dp[k][i][j] = (2 + (dp[0][i + 1][j - 1] + dp[1][i + 1][j - 1]) % mod + (dp[2][i + 1][j - 1] + dp[3][i + 1][j - 1]) % mod) % mod;
                    } else if (s.charAt(i) == c) {
                        dp[k][i][j] = dp[k][i][j - 1];
                    } else if (s.charAt(j) == c) {
                        dp[k][i][j] = dp[k][i + 1][j];
                    } else {
                        dp[k][i][j] = dp[k][i + 1][j - 1];
                    }
                }
            }
        }
        int res = 0;
        for (int i = 0; i < 4; i++) {
            res = (res + dp[i][0][n - 1]) % mod;
        }
        return res;

    }

}
