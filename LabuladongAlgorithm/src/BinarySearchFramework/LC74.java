package BinarySearchFramework;

/**
 * @author WuChao
 * @since 2021/4/7 下午7:44
 */
public class LC74 {
    /**
     * 二分搜索专题：LeetCode 74. 搜索二维矩阵
     * 难度: medium
     *
     * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
     *
     * 每行中的整数从左到右按升序排列。
     * 每行的第一个整数大于前一行的最后一个整数。
     *
     *
     * <p>
     * 示例：
     * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
     * 输出：true
     *
     * <p>
     * 数据范围：
     * m == matrix.length
     * n == matrix[i].length
     * 1 <= m, n <= 100
     * -104 <= matrix[i][j], target <= 104
     */

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0) {
            return false;
        }
        int row = matrix.length;
        int col = matrix[0].length;

        int left = 0, right = row * col - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            // 将二二维数组下标变为一维
            int r = mid / col;
            int c = mid % col;
            if (matrix[r][c] == target) {
                return true;
            } else if (matrix[r][c] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }

}
