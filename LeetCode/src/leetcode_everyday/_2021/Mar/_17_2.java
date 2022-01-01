package leetcode_everyday._2021.Mar;

/**
 * @author WuChao
 * @since 2021/3/17 上午8:02
 */
public class _17_2 {

    /**
     * 每日一题：2021/3/17
     * 115. 不同的子序列
     * 难度: hard
     * 给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。
     * 字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。
     * （例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）
     *
     * 题目数据保证答案符合 32 位带符号整数范围。
     *
     * <p>
     * 示例:
     * 输入：s = "babgbag", t = "bag"
     * 输出：5
     * 解释：
     * 如下图所示, 有 5 种可以从 s 中得到 "bag" 的方案。
     * (上箭头符号 ^ 表示选取的字母)
     * babgbag
     * ^^ ^
     * babgbag
     * ^^    ^
     * babgbag
     * ^    ^^
     * babgbag
     *   ^  ^^
     * babgbag
     *     ^^^
     *
     *
     * <p>
     * 数据范围：
     * 0 <= s.length, t.length <= 1000
     * s 和 t 由英文字母组成
     */

    /*
    dp 思路：
    dp[i][j]：从开头到s[i-1]的子串中，出现『从开头到t[i-1]的子串』的 次数。
              即：前 i 个字符的 s 子串中，出现前 j 个字符的 t 子串的次数。
    状态转移方程：
              当s[i-1] != t[j-1]时，有dp[i][j] = dp[i-1][j]
              当s[i-1] == t[j-1]时，有dp[i][j] = dp[i][j] = dp[i-1][j-1] + dp[i-1][j]
    base case：
              j==0时，dp[i][0] = 1
              i==0时，dp[0][j] = 0


     */

    public int numDistinct(String s, String t) {
        int m = s.length(), n = t.length();
        if (m < n) {
            return 0;
        }
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < m + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                if (j == 0) {// base case:
                    dp[i][j] = 1;
                } else if (i == 0) { // base case
                    dp[i][j] = 0;
                } else {
                    if (s.charAt(i - 1) != t.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j];
                    } else {
                        dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                    }
                }
            }
        }
        return dp[m][n];


    }

    public void _21_3_17_2(){
        String s = "babgbag", t = "bag";
        System.out.println(numDistinct(s, t));

    }
}
