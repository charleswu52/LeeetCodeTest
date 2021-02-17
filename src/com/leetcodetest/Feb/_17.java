package com.leetcodetest.Feb;

import java.util.Arrays;

public class _17 {
    /**
     * 每日一题：2021/2/17
     * 566. 重塑矩阵
     * 难度: easy
     * 在MATLAB中，有一个非常有用的函数 reshape，它可以将一个矩阵重塑为另一个大小不同的新矩阵，但保留其原始数据。
     * 给出一个由二维数组表示的矩阵，以及两个正整数r和c，分别表示想要的重构的矩阵的行数和列数。
     * 重构后的矩阵需要将原始矩阵的所有元素以相同的 行遍历顺序 填充。
     * 如果具有给定参数的reshape操作是可行且合理的，则输出新的重塑矩阵；否则，输出原始矩阵。
     * <p>
     * 输入:
     * nums =
     * [[1,2],
     * [3,4]]
     * r = 1, c = 4
     * 输出:
     * [[1,2,3,4]]
     * 解释:
     * 行遍历nums的结果是 [1,2,3,4]。新的矩阵是 1 * 4 矩阵, 用之前的元素值一行一行填充新矩阵。
     * <p>
     * 数据范围：
     * 给定矩阵的宽和高范围在 [1, 100]。
     * 给定的 r 和 c 都是正数。
     */


    /*
    思路1：使用中间数组存储
     */
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        int oldR = nums.length, oldC = nums[0].length;
        int oldSize = oldR * oldC;
        int newSize = r * c;
        if (oldSize != newSize) {
            return nums;
        }
        int[] store = new int[newSize];
        int[][] res = new int[r][c];
        int count = 0;
        for (int i = 0; i < oldR; i++) {
            for (int j = 0; j < oldC; j++) {
                store[count++] = nums[i][j];
            }
        }
        count = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                res[i][j] = store[count++];
            }
        }
        return res;
    }

    /*
    思路2：不使用额外空间
    统计所有元素个数，然后重塑数组，行号是除列数，列号是对对列数取余

     */
    public int[][] matrixReshape2(int[][] nums, int r, int c) {
        int oldR = nums.length, oldC = nums[0].length;
        if (oldR * oldC != r * c) {
            return nums;
        }
        int[][] res = new int[r][c];
        for (int i = 0; i < r * c; i++) {
            res[i / c][i % c] = nums[i / oldC][i % oldC];
        }
        return res;

    }


    public void _21_2_17() {
        int[][] nums = {{1, 2}, {3, 4}};
        int r = 1, c = 4;
        System.out.println(Arrays.deepToString(matrixReshape(nums, r, c)));
        System.out.println(Arrays.deepToString(matrixReshape2(nums, r, c)));

    }
}
