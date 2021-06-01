package chp16;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author WuChao
 * @since 2021/6/1 上午10:09
 */
public class _19 {
    /**
     * 程序员面试金典(version 6) -  面试题 16.19. 水域大小
     * 难度: medium
     * <p>
     * 有一个用于表示一片土地的整数矩阵land，该矩阵中每个点的值代表对应地点的海拔高度。若值为0则表示水域。由垂直、水平或对角连接的水域为池塘。
     * 池塘的大小是指相连接的水域的个数。
     * 编写一个方法来计算矩阵中所有池塘的大小，返回值需要从小到大排序。
     * <p>
     * 示例:
     * 输入：
     * [
     *   [0,2,1,0],
     *   [0,1,0,1],
     *   [1,1,0,1],
     *   [0,1,0,1]
     * ]
     * 输出： [1,2,4]
     * <p>
     * 数据范围：
     * 0 < len(land) <= 1000
     * 0 < len(land[i]) <= 1000
     */

    /**
     * DFS 深搜
     */

    boolean[][] visited;
    int row, col;

    public int[] pondSizes(int[][] land) {
        row = land.length;
        col = land[0].length;
        visited = new boolean[row][col];
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (!visited[i][j] && land[i][j] == 0) {
                    int res = dfs(land, i, j);
                    if (res > 0) {
                        ans.add(res);
                    }
                }
            }
        }
        Collections.sort(ans);
        int[] ant = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            ant[i] = ans.get(i);
        }
        return ant;


    }

    public int dfs(int[][] land, int r, int c) {
        if (r < 0 || r >= row || c < 0 || c >= col || visited[r][c] || land[r][c] != 0) {
            return 0;
        }
        visited[r][c] = true;
        return dfs(land, r - 1, c) + dfs(land, r + 1, c) + dfs(land, r, c - 1) + dfs(land, r, c + 1)
                + dfs(land, r - 1, c - 1) + dfs(land, r - 1, c + 1) + dfs(land, r + 1, c - 1) + dfs(land, r + 1, c + 1) + 1;
    }
}
