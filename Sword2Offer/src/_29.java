

/**
 * @author WuChao
 * @since 2021/3/17 上午10:12
 */
public class _29 {

    /**
     * 剑指 Offer 29. 顺时针打印矩阵
     * 难度: easy
     * <p>
     * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
     * <p>
     * 示例：
     * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
     * 输出：[1,2,3,6,9,8,7,4,5]
     * <p>
     * 数据范围：
     * 0 <= matrix.length <= 100
     * 0 <= matrix[i].length <= 100
     */

    public int[] spiralOrder(int[][] matrix) {
        if (matrix.length < 1) {
            return new int[]{};
        }
        int m = matrix.length, n = matrix[0].length;
        int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int[][] visited = new int[m][n];
        int i = 0, j = 0, k = 0;
        int count = m * n;
        int[] res = new int[count];
        int l = 0;
        while (l < count) {
            res[l++] = matrix[i][j];
            visited[i][j] = 1;
            int nextI = i + direction[k][0], nextJ = j + direction[k][1];
            if (nextI < 0 || nextI >= m || nextJ < 0 || nextJ >= n || visited[nextI][nextJ] == 1) {
                k = (k + 1) % 4;// 换方向
            }
            i += direction[k][0];
            j += direction[k][1];
        }
        return res;

    }
}
