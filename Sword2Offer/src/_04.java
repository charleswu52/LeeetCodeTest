import java.util.Arrays;

/**
 * @author WuChao
 * @since 2021/3/9 上午9:42
 */
public class _04 {
    /**
     * 剑指 Offer 04. 二维数组中的查找
     * 难度: medium
     * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
     * 请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     * <p>
     * 示例:
     * 现有矩阵 matrix 如下：
     * [
     * [1,   4,  7, 11, 15],
     * [2,   5,  8, 12, 19],
     * [3,   6,  9, 16, 22],
     * [10, 13, 14, 17, 24],
     * [18, 21, 23, 26, 30]
     * ]
     * <p>
     * 给定 target = 5，返回 true。
     * 给定 target = 20，返回 false。
     * <p>
     * 数据范围：
     * 0 <= n <= 1000
     * 0 <= m <= 1000
     */

    /**
     * 思路1：直接使用半暴力解法
     * @param matrix
     * @param target
     * @return
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        for (int[] m : matrix) {
            System.out.println(Arrays.binarySearch(m, target));
            if (Arrays.binarySearch(m, target) >= 0) {
                return true;
            }
        }
        return false;

    }

    public void sword2Offer_04() {
        int[][] matrix = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };

        int target = 2;
        System.out.println(findNumberIn2DArray(matrix, target));
    }

}