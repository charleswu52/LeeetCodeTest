package leetcode_everyday._2022.Apr;

/**
 * @author WuChao
 * @create 2022/4/26 8:42
 */
public class _26_883 {
    /**
     * 每日一题：2022/4/26
     * <p>
     * 883. 三维形体投影面积
     * <p>
     * 难度：easy
     * <p>
     * 在 n x n 的网格 grid 中，我们放置了一些与 x，y，z 三轴对齐的 1 x 1 x 1 立方体。
     * <p>
     * 每个值 v = grid[i][j] 表示 v 个正方体叠放在单元格 (i, j) 上。
     * <p>
     * 现在，我们查看这些立方体在 xy 、yz 和 zx 平面上的投影。
     * <p>
     * 投影 就像影子，将 三维 形体映射到一个 二维 平面上。从顶部、前面和侧面看立方体时，我们会看到“影子”。
     * <p>
     * 返回 所有三个投影的总面积 。
     * <p>
     * 示例
     * <p>
     * 输入：[[1,2],[3,4]]
     * <p>
     * 输出：17
     * <p>
     * 解释：这里有该形体在三个轴对齐平面上的三个投影(“阴影部分”)。
     * <p>
     * 范围
     * <p>
     * n == grid.length == grid[i].length
     * 1 <= n <= 50
     * 0 <= grid[i][j] <= 50
     */

    public int projectionArea(int[][] grid) {
        int n = grid.length;
        int xy = 0, xz = 0, yz = 0;
        for (int i = 0; i < n; i++) {
            int yzHight = 0, xzHight = 0;
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 0) {
                    xy++;
                }
                yzHight = Math.max(yzHight, grid[i][j]);
                xzHight = Math.max(xzHight, grid[j][i]);
            }
            xz += xzHight;
            yz += yzHight;
        }
        return xy + yz + xz;


    }
}
