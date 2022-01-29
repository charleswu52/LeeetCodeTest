package leetcode_everyday._2022.Jan;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author WuChao
 * @create 2022/1/29 9:57
 */
public class _29_1765 {
    /**
     * 每日一题：2022/1/29
     * <p>
     * 1765. 地图中的最高点
     * <p>
     * 难度：medium
     * <p>
     * 给你一个大小为 m x n 的整数矩阵 isWater ，它代表了一个由 陆地 和 水域 单元格组成的地图。
     * <p>
     * 如果 isWater[i][j] == 0 ，格子 (i, j) 是一个 陆地 格子。
     * 如果 isWater[i][j] == 1 ，格子 (i, j) 是一个 水域 格子。
     * 你需要按照如下规则给每个单元格安排高度：
     * <p>
     * 每个格子的高度都必须是非负的。
     * 如果一个格子是是 水域 ，那么它的高度必须为 0 。
     * 任意相邻的格子高度差 至多 为 1 。当两个格子在正东、南、西、北方向上相互紧挨着，就称它们为相邻的格子。（也就是说它们有一条公共边）
     * 找到一种安排高度的方案，使得矩阵中的最高高度值 最大 。
     * <p>
     * 请你返回一个大小为 m x n 的整数矩阵 height ，其中 height[i][j] 是格子 (i, j) 的高度。如果有多种解法，请返回 任意一个 。
     * <p>
     * 输入：isWater = [[0,1],[0,0]]
     * <p>
     * 输出：[[1,0],[2,1]]
     * <p>
     * 范围
     * <p>
     * m == isWater.length
     * n == isWater[i].length
     * 1 <= m, n <= 1000
     * isWater[i][j] 要么是 0 ，要么是 1 。
     * 至少有 1 个水域格子。
     */

    /*
    思路：多源BFS
     */
    public int[][] highestPeak(int[][] isWater) {
        int m = isWater.length, n = isWater[0].length;
        int[][] res = new int[m][n];
        Deque<int[]> deque = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (isWater[i][j] == 1) {
                    deque.addLast(new int[]{i, j});
                }
                res[i][j] = isWater[i][j] == 1 ? 0 : -1;
            }
        }
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!deque.isEmpty()) {
            int[] ints = deque.pollFirst();
            int x = ints[0], y = ints[1];
            for (int[] dir : dirs) {
                int dx = x + dir[0], dy = y + dir[1];
                if (dx < 0 || dx >= m || dy < 0 || dy >= n) continue;
                if (res[dx][dy] != -1) {
                    continue;
                }
                res[dx][dy] = res[x][y] + 1;
                deque.addLast(new int[]{dx, dy});

            }
        }
        return res;

    }
}
