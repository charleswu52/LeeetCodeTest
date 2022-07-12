package leetcode_everyday._2022.Jul;

/**
 * @author WuChao
 * @create 2022/7/12 9:59
 */
public class _12_1252 {
    /**
     * 每日一题：2022/7/12
     * <p>
     * 1252. 奇数值单元格的数目
     * <p>
     * 难度：easy
     * <p>
     * 给你一个 m x n 的矩阵，最开始的时候，每个单元格中的值都是 0。
     * <p>
     * 另有一个二维索引数组 indices，indices[i] = [ri, ci] 指向矩阵中的某个位置，其中 ri 和 ci 分别表示指定的行和列（从 0 开始编号）。
     * <p>
     * 对 indices[i] 所指向的每个位置，应同时执行下述增量操作：
     * <p>
     * ri 行上的所有单元格，加 1 。
     * ci 列上的所有单元格，加 1 。
     * 给你 m、n 和 indices 。请你在执行完所有 indices 指定的增量操作后，返回矩阵中 奇数值单元格 的数目。
     * <p>
     * 示例
     * <p>
     * 输入：m = 2, n = 2, indices = [[1,1],[0,0]]
     * <p>
     * 输出：0
     * <p>
     * 解释：最后的矩阵是 [[2,2],[2,2]]，里面没有奇数。
     * <p>
     * 范围
     * <p>
     * 1 <= m, n <= 50
     * 1 <= indices.length <= 100
     * 0 <= ri < m
     * 0 <= ci < n
     * <p>
     * 进阶
     * 你可以设计一个时间复杂度为 O(n + m + indices.length) 且仅用 O(n + m) 额外空间的算法来解决此问题吗？
     */

    /*
    思路1：创建数组模拟
     */
    public int oddCells(int m, int n, int[][] indices) {
        int lens = indices.length;
        boolean[] rows = new boolean[m];
        boolean[] cols = new boolean[n];
        int row = 0, col = 0;
        for (int i = 0; i < lens; i++) {
            row += (rows[indices[i][0]] = !rows[indices[i][0]]) ? 1 : -1;
            col += (cols[indices[i][1]] = !cols[indices[i][1]]) ? 1 : -1;
        }
        return row * (n - col) + col * (m - row);
    }
}
