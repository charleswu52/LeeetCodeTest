package leetcode_hot100.top20;

/**
 * @author WuChao
 * @create 2021/7/4 9:30
 */
public class _10 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 10. 正则表达式匹配
     * 难度：hard
     * <p>
     * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
     * <p>
     * '.' 匹配任意单个字符
     * '*' 匹配零个或多个前面的那一个元素
     * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
     * <p>
     * 示例
     * <p>
     * 输入：s = "aa" p = "a"
     * 输出：false
     * 解释："a" 无法匹配 "aa" 整个字符串。
     * <p>
     * 输入：s = "aa" p = "a*"
     * 输出：true
     * 解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
     *
     * <p>
     * 范围
     * 0 <= s.length <= 20
     * 0 <= p.length <= 30
     * s 可能为空，且只包含从 a-z 的小写字母。
     * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
     * 保证每次出现字符 * 时，前面都匹配到有效的字符
     * 1 <= s.length <= 1000
     * s 仅由数字和英文字母（大写和/或小写）组成
     */

    /*
    思路：动态规划
    动态规划方程的定义：使用dp[i][j] 表示s的前i个字符与p中的前j个字符是否能够匹配
    从 模式串 p 的字符进行考虑：
      （1） 如果 p 的第 j 个字符是一个小写字母，那么我们必须在 s 中匹配一个相同的小写字母，即
            dp[i][j] = dp[i-1][j-1] ,if s[i] = s[j]
            dp[i][j] = false
       (2) 如果 p 的第 j 个字符是 *，那么就表示我们可以对 p 的第 j−1 个字符匹配任意自然数次。
           字母 + 星号的组合在匹配的过程中，本质上只会有两种情况：
                匹配 ss 末尾的一个字符，将该字符扔掉，而该组合还可以继续进行匹配；
                不匹配字符，将该组合扔掉，不再进行匹配。
           dp[i][j] = dp[i-1][j] | dp[i][j-2] , if s[i] = p[j-1]
           do[i][j] = dp[i][j-2] ,s[i] != p[j-1]
       (3) 在任意情况下，只要 p[j] 是 . 那么 p[j] 一定成功匹配 s 中的任意一个小写字母

       综上所述，dp[i][j] =
       if p[j]!='*' :
            dp[i][j] = dp[i-1][j-1], if matches(s[i],p[j])
            dp[i][j]  = false , others
       otherwise:
            dp[i][j] = dp[i-1][j] | dp[i][j-2] ,if matches(s[i],p[j-1])
            dp[i][j] = dp[i][j-2] , others
     */
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 0; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 2];
                    if (matches(s, p, i, j - 1)) {
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                } else {
                    if (matches(s, p, i, j)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
            }
        }
        return dp[m][n];
    }

    public boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }
}
