package leetcode_everyday.Aug;

/**
 * @author WuChao
 * @create 2021/8/15 15:50
 */
public class _12_516 {
    /**
     * 每日一题：2021/8/12
     * 516. 最长回文子序列
     * 难度：medium
     * <p>
     * 给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。
     * <p>
     * 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
     * <p>
     * 示例 1：
     * 输入：s = "bbbab"
     * 输出：4
     * 解释：一个可能的最长回文子序列为 "bbbb" 。
     * <p>
     * 输入：s = "cbbd"
     * 输出：2
     * 解释：一个可能的最长回文子序列为 "bb" 。
     *
     * <p>
     * 注意:
     * 1 <= s.length <= 1000
     * s 仅由小写英文字母组成
     */

    /*
    思路：动态规划

    dp定义：
    用  dp[i][j] 表示字符串 s 的下标范围 [i, j] 内的最长回文子序列的长度。假设字符串 s 的长度为 n，则只有当 0≤i≤j<n 时，
    才会有 dp[i][j]>0，否则 dp[i][j]=0。

    由于任何长度为 1 的子序列都是回文子序列，因此动态规划的边界情况是，对任意 0≤i<n，都有 dp[i][i]=1。

    当 i < j 时，计算 dp[i][j] 需要分别考虑 s[i] 和 s[j] 相等和不相等的情况：
        如果 s[i] = s[j]，则首先得到 s 的下标范围 [i+1, j-1] 内的最长回文子序列，然后在该子序列的首尾分别添加 s[i] 和 s[j]，
            即可得到 s 的下标范围 [i, j] 内的最长回文子序列，因此 dp[i][j]=dp[i+1][j−1]+2；
        如果 s[i] != s[j]，则 s[i] 和 s[j] 不可能同时作为同一个回文子序列的首尾，因此
            dp[i][j]=max(dp[i+1][j],dp[i][j−1])。

    由于状态转移方程都是从长度较短的子序列向长度较长的子序列转移，因此需要注意动态规划的循环顺序。
    最终得到 dp[0][n−1] 即为字符串 ss 的最长回文子序列的长度。
     */
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;
            char c1 = s.charAt(i);
            for (int j = i + 1; j < n; j++) {
                char c2 = s.charAt(j);
                if (c1 == c2) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }
}
