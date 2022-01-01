package leetcode_everyday._2021.Mar;

/**
 * @author WuChao
 * @since 2021/3/30 上午8:01
 */
public class _30 {
    /**
     * 每日一题：2021/3/30
     * 74. 搜索二维矩阵
     * 难度: medium
     * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
     * 每行中的整数从左到右按升序排列。
     * 每行的第一个整数大于前一行的最后一个整数。
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
     * -10^4 <= matrix[i][j], target <= 10^4
     */


    /*
    题目解析：
    题意很明确，在一个数组中搜索目标值。但是这个矩阵有个特点就是 每一行都是升序排列的，而且下一行的数还大于上一行的最后一个数。
    矩阵的这一特点就可以看成一组升序的数字放到了一个二维数组中。
    这样的话就可以使用二分法来进行搜索,不过与普通二分不同之处就是
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        //使用二分法进行搜索
        int left = 0, right = m * n - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int temp = arrayNum(matrix, mid);
            if ( temp == target) {
                return true;
            } else if (temp < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;

    }

    public int arrayNum(int[][] matrix, int count) {
        int n = matrix[0].length;
        int i = count / n, j = count % n;
        return matrix[i][j];
    }

    public void test() {

    }
}
