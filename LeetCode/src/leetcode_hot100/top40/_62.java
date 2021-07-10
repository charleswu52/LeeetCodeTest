package leetcode_hot100.top40;

/**
 * @author WuChao
 * @create 2021/7/10 11:11
 */
public class _62 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 62. 不同路径
     * 难度：medium
     * <p>
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
     * 问总共有多少条不同的路径？
     * <p>
     * 示例
     * 输入：m = 3, n = 2
     * 输出：3
     * 解释：
     * 从左上角开始，总共有 3 条路径可以到达右下角。
     * 1. 向右 -> 向下 -> 向下
     * 2. 向下 -> 向下 -> 向右
     * 3. 向下 -> 向右 -> 向下
     * <p>
     * 范围
     * 1 <= m, n <= 100
     * 题目数据保证答案小于等于 2 * 10^9
     */

    /*
    思路1：DFS
    超时
     */
    int res = 0;
    boolean[][] visited;
    public int uniquePaths(int m, int n) {

        visited = new boolean[m][n];
        dfs(0, 0, m - 1, n - 1);
        return res;

    }

    public void dfs(int fromX, int fromY, int toX, int toY) {
        if (fromX < 0 || fromX > toX || fromY < 0 || fromY > toY) {
            return;

        }
        if (visited[fromX][fromY]) {
            return;
        }
        if (fromX == toX && fromY == toY) {
            res++;
            return;
        }
        visited[fromX][fromY] = true;
        dfs(fromX, fromY + 1, toX, toY);
        dfs(fromX + 1, fromY, toX, toY);
        visited[fromX][fromY] = false;
    }

    /*
    思路2： 动态规划
    dp[i][j] 表示 从 (0,0) 到 (i,j) 有多少种不同的走法
    初始化 dp[i][0] = 1 只有第一列一条路径
          dp[0][j] = 1 只有第一行一条路径
    其他情况 能从上面或者左边过来
    dp[i][j] = dp[i-1][j] + dp[i][j-1]

     */

    public int uniquePaths2(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];

    }
}
