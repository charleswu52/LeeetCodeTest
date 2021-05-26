package chp10;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author WuChao
 * @since 2021/5/26 上午10:57
 */
public class _9 {
    /**
     * 程序员面试金典(version 6) - 面试题 10.09. 排序矩阵查找
     * 难度: medium
     * <p>
     * 给定M×N矩阵，每一行、每一列都按升序排列，请编写代码找出某元素。
     *
     * <p>
     * 示例1:
     * 给定M×N矩阵，每一行、每一列都按升序排列，请编写代码找出某元素。
     * <p>
     * 示例:
     * <p>
     * 现有矩阵 matrix 如下：
     * <p>
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
     */

    /*
    思路1：朴素二分
     */
    public boolean searchMatrix(int[][] matrix, int target) {

        int m = matrix.length;
        if (m == 0) {
            return false;
        }
        int n = matrix[0].length;
        for (int[] rows : matrix) {
            List<Integer> list = Arrays.stream(rows).boxed().collect(Collectors.toList());
            int index = Collections.binarySearch(list, target);
            if (index >= 0 && index < n) {
                return true;
            }
        }
        return false;


    }

    /*
    改进二分：二分+右上角
     */
    public boolean searchMatrix2(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int m = matrix.length, n = matrix[0].length;
        int left = 0, right = n - 1;
        while (left < m && right >= 0) {
            if (matrix[left][right] == target) {
                return true;
            } else if (matrix[left][right] > target) {
                right--;
            } else {
                left++;
            }
        }
        return false;
    }


        @Test
    public void test() {
        int[][] matrix = {new int[]{1, 4}, new int[]{2, 5}};
        int target = 2;
        System.out.println(searchMatrix(matrix, target));

    }
}
