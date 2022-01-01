package leetcode_everyday._2021.Mar;

import java.util.Arrays;

/**
 * @author WuChao
 * @since 2021/3/16 上午7:47
 */
public class _16 {
    /**
     * 每日一题：2021/3/16
     * 59. 螺旋矩阵 II
     * 难度: medium
     * 给你一个正整数 n ，生成一个包含 1 到 n^2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
     * <p>
     * 示例:
     * 输入：n = 3
     * 输出：[[1,2,3],[8,9,4],[7,6,5]]
     *
     * <p>
     * 数据范围：
     * 1 <= n <= 20
     */
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 方向数组
        int i = 0, j = 0, k = 0;
        int count = 1;
        while (count <= n * n) {
            matrix[i][j] = count;
            int nextI = i + direction[k][0], nextJ = j + direction[k][1];
            if (nextI < 0 || nextI >= n || nextJ < 0 || nextJ >= n || matrix[nextI][nextJ] != 0) {
                k = (k + 1) % 4;// 换方向
            }
            i += direction[k][0];
            j += direction[k][1];
            count++;
        }

        return matrix;
    }

    public void _21_3_16() {
        int n = 3;
        System.out.println(Arrays.deepToString(generateMatrix(n)));
    }

}
