package leetcode_everyday.Mar;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WuChao
 * @since 2021/3/15 上午7:37
 */
public class _15 {
    /**
     * 每日一题：2021/3/15
     * 54. 螺旋矩阵
     * 难度: medium
     * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
     * <p>
     * 示例:
     * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
     * 输出：[1,2,3,6,9,8,7,4,5]
     *
     * <p>
     * 数据范围：
     * m == matrix.length
     * n == matrix[i].length
     * 1 <= m, n <= 10
     * -100 <= matrix[i][j] <= 100
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 方向数组
        int[][] visited = new int[m][n]; // 标志访问数组
        int i = 0, j = 0, k = 0;
        int count = m * n;
        List<Integer> res = new ArrayList<>();
        while (count > 0) {
            res.add(matrix[i][j]);
            visited[i][j] = 1;
            int nextI = i + direction[k][0], nextJ = j + direction[k][1];
            if (nextI < 0 || nextI >= m || nextJ < 0 || nextJ >= n || visited[nextI][nextJ] == 1) {
                k = (k + 1) % 4;// 换方向
            }
            i += direction[k][0];
            j += direction[k][1];
            count--;
        }
        return res;


    }

    public void _21_3_15() {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println(spiralOrder(matrix));
    }


}
