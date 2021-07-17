package leetcode_hot100.top60;

/**
 * @author WuChao
 * @create 2021/7/17 13:45
 */
public class _200 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 200. 岛屿数量
     * 难度：medium
     * <p>
     * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
     * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
     * 此外，你可以假设该网格的四条边均被水包围。
     * <p>
     * 示例：
     * 输入：grid = [
     * ["1","1","1","1","0"],
     * ["1","1","0","1","0"],
     * ["1","1","0","0","0"],
     * ["0","0","0","0","0"]
     * ]
     * 输出：1
     * <p>
     * 输入：grid = [
     * ["1","1","0","0","0"],
     * ["1","1","0","0","0"],
     * ["0","0","1","0","0"],
     * ["0","0","0","1","1"]
     * ]
     * 输出：3
     * <p>
     * 进阶：
     * <p>
     * 数据范围
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 300
     * grid[i][j] 的值为 '0' 或 '1'
     */

    /*
    题目解析：
    多个1连在一块是陆地，周围被水包围着看做岛屿，求图中岛屿的数量
    看这个

    思路：DFS

    从一个grid为'1'的表格出发，岛屿数加1，然后从此坐标开始进行dfs搜索，
    上下左右，遇到1 的就当做是一个岛屿，遇到0返回
    遇到1后的还要把它置为0

     */
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    res++;
                    dfs(grid, i, j);
                }
            }
        }
        return res;
    }

    public void dfs(char[][] grid, int i, int j) {
        int r = grid.length, c = grid[0].length;
        if (i < 0 || i >= r || j < 0 || j >= c || grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        dfs(grid, i - 1, j);
        dfs(grid, i + 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i, j + 1);
    }
}
