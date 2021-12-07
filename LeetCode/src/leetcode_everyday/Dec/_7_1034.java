package leetcode_everyday.Dec;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @author WuChao
 * @create 2021/12/7 下午2:18
 */
public class _7_1034 {
    /**
     * 每日一题：2021/12/7
     * <p>
     * 423. 从英文中重建数字
     * <p>
     * 难度：medium
     * <p>
     * 给你一个大小为 m x n 的整数矩阵 grid ，表示一个网格。另给你三个整数 row、col 和 color 。网格中的每个值表示该位置处的网格块的颜色。
     * <p>
     * 当两个网格块的颜色相同，而且在四个方向中任意一个方向上相邻时，它们属于同一 连通分量 。
     * <p>
     * 连通分量的边界 是指连通分量中的所有与不在分量中的网格块相邻（四个方向上）的所有网格块，或者在网格的边界上（第一行/列或最后一行/列）
     * 的所有网格块。
     * <p>
     * 请你使用指定颜色 color 为所有包含网格块 grid[row][col] 的 连通分量的边界 进行着色，并返回最终的网格 grid 。
     * <p>
     * 示例 1：
     * <p>
     * 输入：grid = [[1,1],[1,2]], row = 0, col = 0, color = 3
     * <p>
     * 输出：[[3,3],[3,2]]
     *
     * <p>
     * 示例 2：
     * <p>
     * 输入：grid = [[1,2,2],[2,3,2]], row = 0, col = 1, color = 3
     * <p>
     * 输出：[[1,3,3],[2,3,3]]
     * <p>
     * 范围
     * <p>
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 50
     * 1 <= grid[i][j], color <= 1000
     * 0 <= row < m
     * 0 <= col < n
     */

    /*
    思路：BFS
    记录下当前格子的颜色，然后进行BFS 对四个方向上 是这个颜色的进行记录 最后对记录下的所有下标进行染色
     */
    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        List<int[]> borders = new ArrayList<>();
        int originalColor = grid[row][col];  // 记录下原始颜色
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 上下左右四个方向
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{row, col});
        visited[row][col] = true;
        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            int x = node[0], y = node[1];
            boolean isBorder = false;
            for (int i = 0; i < 4; i++) {
                int nextX = dirs[i][0] + x, nextY = dirs[i][1] + y;
                if (!(nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && grid[nextX][nextY] == originalColor)) {
                    isBorder = true;
                } else if (!visited[nextX][nextY]) {
                    visited[nextX][nextY] = true;
                    queue.offer(new int[]{nextX, nextY});
                }
            }
            if (isBorder) {
                borders.add(new int[]{x, y});
            }
        }
        for (int i = 0; i < borders.size(); i++) {
            int x = borders.get(i)[0], y = borders.get(i)[1];
            grid[x][y] = color;
        }
        return grid;

    }
}