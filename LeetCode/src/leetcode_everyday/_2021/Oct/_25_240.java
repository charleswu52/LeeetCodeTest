package leetcode_everyday._2021.Oct;

/**
 * @author WuChao
 * @create 2021/10/25 上午10:37
 */
public class _25_240 {
    /**
     * 每日一题：2021/10/25
     * <p>
     * 240. 搜索二维矩阵 II
     * <p>
     * 难度：medium
     * <p>
     * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
     *
     * 每行的元素从左到右升序排列。
     *
     * 每列的元素从上到下升序排列。
     * <p>
     * 示例1：
     * <p>
     * 输入：matrix = [[1,4,7,11,15],
     *                [2,5,8,12,19],
     *                [3,6,9,16,22],
     *                [10,13,14,17,24],
     *                [18,21,23,26,30]],
     *                target = 5
     *
     * 输出：true
     * <p>
     * 范围
     * <p>
     * m == matrix.length
     * n == matrix[i].length
     * 1 <= n, m <= 300
     * -10^9 <= matrix[i][j] <= 10^9
     * 每行的所有元素从左到右升序排列
     * 每列的所有元素从上到下升序排列
     * -10^9<= target <= 10^9
     *
     */

    /*
    思路1: 二分查找

     */

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        for (int[] arr : matrix) {
            int idx = search(arr, target);
            if (idx >= 0) {
                return true;
            }
        }
        return false;


    }

    public int search(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = ((right - left) >> 1) + left;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }

        }
        return -1;
    }

    /*
   思路2：Z字形查找
   从右上角(0,n-1)开始搜索，

     */

    public boolean searchMatrix2(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        int m = matrix.length, n = matrix[0].length;
        int x = 0, y = n - 1;
        while (x < m && y >= 0) {
            if (matrix[x][y] == target) {
                return true;
            }
            if (matrix[x][y] > target) {
                y--;
            } else {
                x++;
            }
        }
        return false;


    }
}