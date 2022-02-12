package leetcode_everyday._2022.Feb;

import java.util.Arrays;

/**
 * @author WuChao
 * @create 2022/2/12 9:01
 */
public class _12_1020 {
    /**
     * 每日一题：2022/2/12
     * <p>
     * 1020. 飞地的数量
     * <p>
     * 难度：medium
     * <p>
     * 给你一个大小为 m x n 的二进制矩阵 grid ，其中 0 表示一个海洋单元格、1 表示一个陆地单元格。
     * <p>
     * 一次 移动 是指从一个陆地单元格走到另一个相邻（上、下、左、右）的陆地单元格或跨过 grid 的边界。
     * <p>
     * 返回网格中 无法 在任意次数的移动中离开网格边界的陆地单元格的数量。
     *
     * <p>
     * 示例
     * <p>
     * 输入：grid = [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
     * <p>
     * 输出：3
     * <p>
     * 解释：有三个 1 被 0 包围。一个 1 没有被包围，因为它在边界上。
     * <p>
     * 范围
     * <p>
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 500
     * grid[i][j] 的值为 0 或 1
     */

    /*
    思路：深度优先搜素
    从四个边为1的格子进行深度优先搜索，将扩展到的为1的格子置为0
    最后统计所有为1的数量
     */
    int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    int m, n;

    public int numEnclaves(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (grid[0][i] == 1) {
                change(grid, 0, i);
            }
            if (grid[m - 1][i] == 1) {
                change(grid, m - 1, i);
            }
        }
        for (int i = 0; i < m; i++) {
            if (grid[i][0] == 1) {
                change(grid, i, 0);
            }
            if (grid[i][n - 1] == 1) {
                change(grid, i, n - 1);
            }
        }


        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res += grid[i][j];
            }
        }
        return res;

    }

    public void change(int[][] grid, int x, int y) {
        grid[x][y] = 0;
        for (int i = 0; i < 4; i++) {
            int next_x = x + dir[i][0], next_y = y + dir[i][1];
            if (next_x >= 0 && next_x < m && next_y >= 0 && next_y < n && grid[next_x][next_y] == 1) {
                change(grid, next_x, next_y);
            }
        }
    }

}
