package leetcode_hot100.top40;

/**
 * @author WuChao
 * @create 2021/7/10 12:33
 */
public class _72 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 72. 编辑距离
     * 难度：easy
     * <p>
     * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
     * <p>
     * 你可以对一个单词进行如下三种操作：
     * <p>
     * 插入一个字符
     * 删除一个字符
     * 替换一个字符
     * <p>
     * 示例
     * 输入：word1 = "horse", word2 = "ros"
     * 输出：3
     * 解释：
     * horse -> rorse (将 'h' 替换为 'r')
     * rorse -> rose (删除 'r')
     * rose -> ros (删除 'e')
     * 示例 2：
     *
     * <p>
     * 范围
     * 0 <= word1.length, word2.length <= 500
     * word1 和 word2 由小写英文字母组成
     */

    /*
    思路：动态规划
    莱文斯特算法（距离）
    dp[i][j] 表示 Word1 的前 i 个字符与Word2的前j个字符之间的莱文斯特距离
    初始状态：
        当i或j为0时， dp[i][0] = i,dp[0][j] =j
     一般情况：
        word1 删除 一个字符到 word2 ：dp[i][j] = do[i-1][j]+1
        word1 插入 一个 字符到 word2：dp[i][j] = do[i][j-1]+1
        word1 替换 一个 字符到 word2：dp[i][j] = do[i-1][j-1]+cost ,其中如果当前对应字符相等cost=0，否则等于1
        dp[i][j] = min(上述三种情况)
     */
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }
        // 其他情况
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int cost;
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    cost = 0;
                } else {
                    cost = 1;
                }
                int insCost = dp[i - 1][j] + 1;
                int delCost = dp[i][j - 1] + 1;
                int repCost = dp[i - 1][j - 1] + cost;
                dp[i][j] = Math.min(Math.min(insCost, delCost), repCost);
            }
        }
        return dp[m][n];


    }
}
