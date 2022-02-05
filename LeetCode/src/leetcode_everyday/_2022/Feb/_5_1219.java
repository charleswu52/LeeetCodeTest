package leetcode_everyday._2022.Feb;

/**
 * @author WuChao
 * @create 2022/2/5 9:26
 */
public class _5_1219 {
    /**
     * 每日一题：2022/2/5
     * <p>
     * 1219. 黄金矿工
     * <p>
     * 难度：easy
     * <p>
     * 你要开发一座金矿，地质勘测学家已经探明了这座金矿中的资源分布，并用大小为 m * n 的网格 grid 进行了标注。
     * 每个单元格中的整数就表示这一单元格中的黄金数量；如果该单元格是空的，那么就是 0。
     * <p>
     * 为了使收益最大化，矿工需要按以下规则来开采黄金：
     * <p>
     * 每当矿工进入一个单元，就会收集该单元格中的所有黄金。
     * 矿工每次可以从当前位置向上下左右四个方向走。
     * 每个单元格只能被开采（进入）一次。
     * 不得开采（进入）黄金数目为 0 的单元格。
     * 矿工可以从网格中 任意一个 有黄金的单元格出发或者是停止。
     * <p>
     * 输入：grid = [[0,6,0],[5,8,7],[0,9,0]]
     * 输出：24
     * 解释：
     * [[0,6,0],
     * [5,8,7],
     * [0,9,0]]
     * 一种收集最多黄金的路线是：9 -> 8 -> 7。
     * <p>
     * 范围
     * <p>
     * 1 <= grid.length, grid[i].length <= 15
     * 0 <= grid[i][j] <= 100
     * 最多 25 个单元格中有黄金。
     */

    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int getMaximumGold(int[][] grid) {
        if (grid.length == 0) {
            return 0;
        }
        int result = 0;
        int rows = grid.length, clos = grid[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < clos; j++) {
                if (grid[i][j] != 0) {
                    result = Math.max(result, dfs(grid, i, j));
                }
            }
        }
        return result;


    }

    public int dfs(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) {
            return 0;
        }
        int temp = grid[i][j];
        grid[i][j] = 0;
        int max = 0;
        for (int k = 0; k < 4; k++) {
            int nextI = i + dirs[k][0], nextJ = j + dirs[k][1];
            max = Math.max(max, dfs(grid, nextI, nextJ));
        }
        grid[i][j] = temp;
        return grid[i][j] + max;

    }
}
