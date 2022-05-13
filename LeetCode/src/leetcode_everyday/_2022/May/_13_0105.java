package leetcode_everyday._2022.May;

/**
 * @author WuChao
 * @create 2022/5/13 9:01
 */
public class _13_0105 {
    /**
     * 每日一题：2022/5/13
     * <p>
     * 面试题 01.05. 一次编辑
     * <p>
     * 难度：medium
     * <p>
     * 字符串有三种编辑操作:插入一个字符、删除一个字符或者替换一个字符。 给定两个字符串，编写一个函数判定它们是否只需要一次(或者零次)编辑。
     * <p>
     * 示例
     * <p>
     * 输入:
     * first = "pale"
     * second = "ple"
     * <p>
     * 输出: True
     */

    /*
    思路：动态规划（经典编辑距离算法）
     */
    public boolean oneEditAway(String first, String second) {
        int m = first.length(), n = second.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < m + 1; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j < n + 1; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                int cost = 1;
                if (first.charAt(i - 1) == second.charAt(j - 1)) {
                    cost = 0;
                }
                int delete = dp[i - 1][j] + 1;
                int insert = dp[i][j - 1] + 1;
                int sub = dp[i - 1][j - 1] + cost;
                dp[i][j] = Math.min(delete, Math.min(sub, insert));

            }
        }
        return dp[m][n] <= 1;


    }
}
