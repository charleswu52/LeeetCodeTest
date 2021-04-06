package chp1;

/**
 * @author WuChao
 * @since 2021/4/6 下午12:45
 */
public class _7 {
    /**
     * 程序员面试金典(version 6) - 面试题 01.07. 旋转矩阵
     * 难度: medium
     * <p>
     * 给你一幅由 N × N 矩阵表示的图像，其中每个像素的大小为 4 字节。请你设计一种算法，将图像旋转 90 度。
     * 不占用额外内存空间能否做到？
     *
     * <p>
     * 例如：
     * 给定 matrix =
     * [
     *   [1,2,3],
     *   [4,5,6],
     *   [7,8,9]
     * ],
     *
     * 原地旋转输入矩阵，使其变为:
     * [
     *   [7,4,1],
     *   [8,5,2],
     *   [9,6,3]
     * ]
     *
     * <p>
     * 数据范围：
     * matrix.length == n
     * matrix[i].length == n
     * 1 <= n <= 20
     * -1000 <= matrix[i][j] <= 1000
     */

    /**
     * 之前做到的一道题目：
     * 采取的思路是：原地变化-- 先水平翻转，再按照对角线翻转
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // 水平翻转
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - i - 1][j];
                matrix[n - i - 1][j] = temp;
            }
        }
        // 按主对角线翻转
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

}
