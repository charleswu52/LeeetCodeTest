/**
 * @author WuChao
 * @since 2021/3/15 上午8:26
 */
public class _19 {
    /**
     * 剑指 Offer 19. 正则表达式匹配
     * 难度: ！！！ hard !!!
     * 请实现一个函数用来匹配包含'. '和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（含0次）。
     * 在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但与"aa.a"和"ab*a"均不匹配。
     *
     * <p>
     * 示例：
     * 输入:
     * s = "aa"
     * p = "a"
     * 输出: false
     * 解释: "a" 无法匹配 "aa" 整个字符串。
     *
     * <p>
     * 数据范围：
     * s 可能为空，且只包含从 a-z 的小写字母。
     * p 可能为空，且只包含从 a-z 的小写字母以及字符 . 和 *，无连续的 '*'。
     */

    public boolean isMatch(String s, String p) {
        int m = s.length() + 1, n = p.length() + 1;
        boolean[][] dp = new boolean[m][n]; // dp数组
        // 初始化状态
        dp[0][0] = true;
        for (int j = 2; j < n; j += 2) {
            // 初始化首行
            dp[0][j] = dp[0][j - 2] && p.charAt(j - 1) == '*';
        }
        //状态转移
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (p.charAt(j - 1) == '*') {
                    if (dp[i][j - 2]) { // 去掉了当前*以及之前的那个字符
                        dp[i][j] = true;
                    } else if (dp[i - 1][j] && s.charAt(i - 1) == p.charAt(j - 2)) { //让 p[j-2]多出现一次
                        dp[i][j] = true;
                    } else if (dp[i - 1][j] && p.charAt(j - 2) == '.') {   // 让 '.' 多出现一次
                        dp[i][j] = true;
                    }
                } else {
                    if (dp[i - 1][j - 1] && s.charAt(i - 1) == p.charAt(j - 1)) { // 让p[j-1]多出现一次
                        dp[i][j] = true;
                    } else if (dp[i - 1][j - 1] && p.charAt(j - 1) == '.') {    // 将字符'.'看作 s[i-1]
                        dp[i][j] = true;
                    }
                }
            }
        }
        return dp[m - 1][n - 1];

    }
    public void sword2Offer_19(){
        String s = "aaa";
        String p = "ab*ac*a";
        System.out.println(isMatch(s, p));
    }
}
