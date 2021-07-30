package leetcode_everyday.Apr;

import org.junit.Test;

/**
 * @author WuChao
 * @since 2021/4/3 上午8:54
 */
public class _3_1143 {
    /**
     * 每日一题：2021/4/3
     * 1143. 最长公共子序列
     * 难度: medium
     * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
     * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
     * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
     * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
     * <p>
     * 示例：
     * 输入：text1 = "abcde", text2 = "ace"
     * 输出：3
     * 解释：最长公共子序列是 "ace" ，它的长度为 3 。
     * <p>
     * 数据范围：
     * 1 <= text1.length, text2.length <= 1000
     * text1 和 text2 仅由小写英文字符组成
     */

    /*
    题目解析: 典型的，之前学习动态规划时候的经典问题
    定义动态规划方程：dp[i][j] 表示 text1[0:i-1] 和 text2[0:j-1] 的最长公共子序列。
        （注：text1[0:i-1] 表示的是 text1 的 第 0 个元素到第 i - 1 个元素，两端都包含）
    状态转移方程：
        当 text1[i - 1] == text2[j - 1] 时，说明两个子字符串的最后一位相等，所以最长公共子序列又增加了 1，
            所以 dp[i][j] = dp[i - 1][j - 1] + 1；举个例子，比如对于 ac 和 bc 而言，
            他们的最长公共子序列的长度等于 a和 c的最长公共子序列长度 0 + 1 = 1。
        当 text1[i - 1] != text2[j - 1]时，说明两个子字符串的最后一位不相等，那么此时的状态 dp[i][j] 应该是
            dp[i - 1][j] 和 dp[i][j - 1] 的最大值。举个例子，比如对于 ace 和 bc 而言，他们的最长公共子序列的长度等于
                ① ace 和 b 的最长公共子序列长度0 与 ② ac 和 bc 的最长公共子序列长度1 的最大值，即 1。
        综上，该状态转移方程为：
            dp[i][j]=dp[i−1][j−1]+1,             当 text1[i−1]==text2[j−1];
            dp[i][j]=max(dp[i−1][j],dp[i][j−1]), 当 text1[i−1]!=text2[j−1]
    状态初始化：
        当 i = 0 或者 j = 0 时，dp[i][j] 初始化为 0.
    遍历方向：
        从小大，因为dp[i][j]依赖于dp[i-1][j-1] dp[i-1][j] dp[i][j-1]
        而且当i或j为0的时候，dp[i][j]也为0
    最终的返回结果：
        dp[len(text1]][len(text2)]

     */

    /**
     * 使用动态规划解决最长公共子序列问题
     *
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }

    @Test
    public void test() {
        String text1 = "abc", text2 = "abcde";
        System.out.println(longestCommonSubsequence(text1, text2));
    }

}
