package leetcode_hot100.top40;

/**
 * @author WuChao
 * @create 2021/7/10 8:26
 */
public class _48 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 48. 旋转图像
     * 难度：medium
     * <p>
     * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
     *
     * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
     * <p>
     * 示例
     * <p>
     * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
     * 输出：[[7,4,1],[8,5,2],[9,6,3]]
     * <p>
     * 范围
     * matrix.length == n
     * matrix[i].length == n
     * 1 <= n <= 20
     * -1000 <= matrix[i][j] <= 1000
     */

    public void rotate(int[][] matrix) {
        int len = matrix.length;
        // 水平翻转
        for (int i = 0; i < len / 2; i++) {
            for (int j = 0; j < len; j++) {
                int t = matrix[i][j];
                matrix[i][j] = matrix[len - i - 1][j];
                matrix[len - i - 1][j] = t;
            }
        }
        // 沿主对角线翻转
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < i; j++) {
                int t = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = t;
            }
        }

    }

}
