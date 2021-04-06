package chp1;

/**
 * @author WuChao
 * @since 2021/4/6 下午1:26
 */
public class _8 {
    /**
     * 程序员面试金典(version 6) - 面试题 01.08. 零矩阵
     * 难度: medium
     * <p>
     * 编写一种算法，若M × N矩阵中某个元素为0，则将其所在的行与列清零。
     *
     * <p>
     * 例如：
     * 输入：
     * [
     *   [1,1,1],
     *   [1,0,1],
     *   [1,1,1]
     * ]
     * 输出：
     * [
     *   [1,0,1],
     *   [0,0,0],
     *   [1,0,1]
     * ]
     *
     * <p>
     * 数据范围：
     */

    /*
    之前也做过，属于复习
    先遍历首行与首列，看原始是否
     */
    public void setZeroes(int[][] matrix) {
        if (matrix.length < 1) {
            return;
        }
        int m = matrix.length, n = matrix[0].length;
        boolean firstCol = false, firstRow = false;
        for (int i = 0; i < n; i++) {
            if (matrix[0][i] == 0) {
                firstRow = true;
                break;
            }
        }
        for (int i = 0; i < m; i++) {
            if (matrix[0][i] == 0) {
                firstCol =true;
                break;
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        // 这一步 再用第一行第一列去更新的时候出了问题
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (firstCol) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
        if (firstRow) {
            for (int i = 0; i < n; i++) {
                matrix[0][i] = 0;
            }
        }

    }
}
