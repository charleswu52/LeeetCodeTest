package leetcodetest.Mar;

/**
 * @author WuChao
 * @since 2021/3/2 上午7:51
 */
public class _2 {
    /**
     * 每日一题：2021/3/2
     * 304. 二维区域和检索 - 矩阵不可变
     * 难度: medium
     * 给定一个二维矩阵，计算其子矩形范围内元素的总和，该子矩阵的左上角为 (row1, col1) ，右下角为 (row2, col2)。
     *
     * <p>
     * 给定 matrix = [
     * [3, 0, 1, 4, 2],
     * [5, 6, 3, 2, 1],
     * [1, 2, 0, 1, 5],
     * [4, 1, 0, 1, 7],
     * [1, 0, 3, 0, 5]
     * ]
     * <p>
     * sumRegion(2, 1, 4, 3) -> 8
     * sumRegion(1, 1, 2, 2) -> 11
     * sumRegion(1, 2, 2, 4) -> 12
     *
     * <p>
     * 数据范围：
     * 你可以假设矩阵不可变。
     * 会多次调用 sumRegion 方法。
     * 你可以假设 row1 ≤ row2 且 col1 ≤ col2
     */
    class NumMatrix {

        int[][] matrix;

        public NumMatrix(int[][] matrix) {
            this.matrix = matrix;
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            int res = 0;
            for (int i = row1; i <= row2; i++) {
                for (int j = col1; j <= col2; j++) {
                    res += this.matrix[i][j];
                }
            }
            return res;
        }
    }

    public void _21_3_2() {
        int[][] matrix = {{3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}};
        NumMatrix numMatrix = new NumMatrix(matrix);
        System.out.println(numMatrix.sumRegion(2, 1, 4, 3));
        System.out.println(numMatrix.sumRegion(1, 1, 2, 2));
        System.out.println(numMatrix.sumRegion(1, 2, 2, 4));
    }
}