import org.junit.Test;

/**
 * @author WuChao
 * @since 2021/3/24 上午10:28
 */
public class _47 {
    /**
     * 剑指 Offer 47. 礼物的最大价值
     * 难度: medium
     * <p>
     * 在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。你可以从棋盘的左上角开始拿格子里的礼物，
     * 并每次向右或者向下移动一格、直到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
     *
     * <p>
     * 示例：
     * 输入:
     * [
     * [1,3,1],
     * [1,5,1],
     * [4,2,1]
     * ]
     * 输出: 12
     * 解释: 路径 1→3→5→2→1 可以拿到最多价值的礼物
     *
     * <p>
     * 数据范围：
     * 0 < grid.length <= 200
     * 0 < grid[0].length <= 200
     */

    /*
    题解：动态规划的思想
    除了入口单元格(0,0),其他单元格只能从上边单元格或左边单元格到达。
    那此单元格的大小 为 grid[i][j] = max(grid[i][j-1],grid[i-1][j]) +grid[i][j]
    特别的，第一行与第一列的单元格的大小（grid[0][0]除外），直接由上边的或者左边的单元格的值累加得到
     */
    public int maxValue(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                if (i == 0) {
                    grid[i][j] += grid[i][j - 1];
                } else if (j == 0) {
                    grid[i][j] += grid[i - 1][j];
                } else {
                    grid[i][j] += Math.max(grid[i - 1][j], grid[i][j - 1]);
                }
            }
        }
        return grid[m - 1][n - 1];
    }

    @Test
    public void test() {
        int[][] grid = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        System.out.println(maxValue(grid));

    }
}
