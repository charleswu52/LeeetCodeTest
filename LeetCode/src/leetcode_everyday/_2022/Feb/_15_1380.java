package leetcode_everyday._2022.Feb;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WuChao
 * @create 2022/2/15 9:54
 */
public class _15_1380 {
    /**
     * 每日一题：2022/2/15
     * <p>
     * 1380. 矩阵中的幸运数
     * <p>
     * 难度：easy
     * <p>
     * 给你一个 m * n 的矩阵，矩阵中的数字 各不相同 。请你按 任意 顺序返回矩阵中的所有幸运数。
     *
     * 幸运数是指矩阵中满足同时下列两个条件的元素：
     *
     * 在同一行的所有元素中最小
     * 在同一列的所有元素中最大
     * <p>
     * 示例
     * <p>
     * 输入：matrix = [[1,10,4,2],[9,3,8,7],[15,16,17,12]]
     *
     * 输出：[12]
     *
     * 解释：12 是唯一的幸运数，因为它是其所在行中的最小值，也是所在列中的最大值。
     * <p>
     * 范围
     * <p>
     * m == mat.length
     * n == mat[i].length
     * 1 <= n, m <= 50
     * 1 <= matrix[i][j] <= 10^5
     * 矩阵中的所有元素都是不同的
     */

    public List<Integer> luckyNumbers (int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int curMin, curCol;
            curMin = matrix[i][0];
            curCol = 0;
            for (int j = 1; j < n; j++) {
                if (curMin > matrix[i][j]) {
                    curMin = matrix[i][j];
                    curCol = j;
                }
            }
            boolean isAns = true;
            for (int k = 0; k < m; k++) {
                if (matrix[k][curCol] > curMin) {
                    isAns = false;
                    break;
                }
            }
            if (isAns) {
                res.add(curMin);
            }


        }
        return res;

    }
}
