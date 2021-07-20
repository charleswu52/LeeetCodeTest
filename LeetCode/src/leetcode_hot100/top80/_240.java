package leetcode_hot100.top80;

/**
 * @author WuChao
 * @create 2021/7/20 15:08
 */
public class _240 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 240. 搜索二维矩阵 II
     * 难度：medium
     * <p>
     * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
     *
     * 每行的元素从左到右升序排列。
     * 每列的元素从上到下升序排列。
     * <p>
     * 示例 1：
     * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
     * 输出：true
     *
     * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
     * 输出：false
     *
     * <p>
     * 数据范围
     * m == matrix.length
     * n == matrix[i].length
     * 1 <= n, m <= 300
     * -109 <= matix[i][j] <= 109
     * 每行的所有元素从左到右升序排列
     * 每列的所有元素从上到下升序排列
     * -109 <= target <= 109
     */

    /*
    思路： 利用矩阵顺序排序的特性
    从左下角开始或者右上角开始查找，以从左下角开始查找为例
    如果当前元素的值 < target , 向右移动一列
    如果当前元素的值 > target , 向上移动一行
    如果当前元素的值 = target , 返回 true


     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        int i = m - 1, j = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] > target) {
                i--;
            } else {
                j++;
            }
        }
        return false;


    }
}
