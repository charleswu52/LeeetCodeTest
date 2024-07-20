package leetcode_everyday._2024.Jul;

/**
 * @author charles
 * @create 2024/7/20
 */
public class _20_2850 {
    /**
     * 每日一题：2024/7/20
     * 2850. 将石头分散到网格图的最少移动次数
     * https://leetcode.cn/problems/minimum-moves-to-spread-stones-over-grid/
     */
    public static int minimumMoves(int[][] grid) {
        return dfs(grid, 0, 0);
    }

    public static int dfs(int[][] grid, int currentCol, int currentRow) {
        if (currentCol >= 3) {
            return 0;
        }
        if(currentRow >= 3) {
            return dfs(grid, currentCol + 1, 0);
        }

        if(grid[currentCol][currentRow] != 0) {
            return dfs(grid, currentCol, currentRow + 1);
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == currentCol && j == currentRow) {
                    continue;
                }
                if (grid[i][j] <= 1) {
                    continue;
                }
                grid[i][j] -= 1;
                res = Math.min(res, dfs(grid, currentCol, currentRow + 1) + Math.abs(i - currentCol) + Math.abs(j - currentRow));
                grid[i][j] += 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] grid = {{1, 1, 0}, {1, 1, 1}, {1, 2, 1}};
        System.out.println(minimumMoves(grid));
    }
}
