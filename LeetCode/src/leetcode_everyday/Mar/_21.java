package leetcode_everyday.Mar;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author WuChao
 * @since 2021/3/21 上午8:01
 */
public class _21 {
    /**
     * 每日一题：2021/3/21
     * 73. 矩阵置零
     * 难度: medium
     * 给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。
     * <p>
     * 示例:
     * 输入:
     * [
     * [0,1,2,0],
     * [3,4,5,2],
     * [1,3,1,5]
     * ]
     * 输出:
     * [
     * [0,0,0,0],
     * [0,4,5,0],
     * [0,3,1,0]
     * ]
     * <p>
     * 数据范围：
     * <p>
     * 进阶思考：
     * 一个直接的解决方案是使用 O(mn) 的额外空间，但这并不是一个好的解决方案。
     * 一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
     * 你能想出一个常数空间的解决方案吗？
     */

    /*
    本题难点在于要求空间复杂度是O(1)的
    那么思考如何保存要置为0的行和列呢
     */
    /*
    之前最开始的想法是遍历一遍数组，把出现0的那个元素所在的行和列的第一个元素置为0，然后通过再遍历数组把为0 的那行和列都置为0
    但是没有考虑到在标记的过程中应该避开第一行和第一列，遍历剩余的行和列，同时再用第一行和第一列去更新剩余的行和列
    然后对第一行和第一列在最开始的时候要先遍历一遍看是否需要置为0，在最后的时候再更新第一行和第一列
     */

    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        if (m < 1) {
            return;
        }
        int n =matrix[0].length;
        boolean firstCol = false, firstRow = false;
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                firstCol=true;//第一列应该置0
            }
        }
        for (int i = 0; i < n; i++) {
            if (matrix[0][i] == 0) {
                firstRow = true;// 第一行应该置0
            }
        }
        // 使用除第一行第一列的剩余元素去标记第一行和第一列
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        // 使用标记了的第一行或第一列再去更新剩余的元素
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        // 最后根据之前对第一行、第一列的标记再去更新第一行、第一列
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

    @Test
    public void test() {
        int[][] matrix = {{0, 1, 2, 0},
                {3, 4, 5, 2},
                {1, 3, 1, 5}};
        setZeroes(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }

}
