package leetcode_everyday.Feb;

import java.util.Arrays;

public class _24 {
    /**
     * 每日一题：2021/2/24
     * 832. 翻转图像
     * 难度: easy
     * 给定一个二进制矩阵 A，我们想先水平翻转图像，然后反转图像并返回结果。
     * 水平翻转图片就是将图片的每一行都进行翻转，即逆序。例如，水平翻转 [1, 1, 0] 的结果是 [0, 1, 1]。
     * 反转图片的意思是图片中的 0 全部被 1 替换，1 全部被 0 替换。例如，反转 [0, 1, 1] 的结果是 [1, 0, 0]。
     * <p>
     * 输入：[[1,1,0],[1,0,1],[0,0,0]]
     * 输出：[[1,0,0],[0,1,0],[1,1,1]]
     * 解释：首先翻转每一行: [[0,1,1],[1,0,1],[0,0,0]]；
     * 然后反转图片: [[1,0,0],[0,1,0],[1,1,1]]
     * <p>
     * 数据范围：
     * 1 <= A.length = A[0].length <= 20
     * 0 <= A[i][j] <= 1
     */

    /*
    暴力直接的做法：按照题目翻转的要去做
     */
    public int[][] flipAndInvertImage(int[][] A) {
        int len = A.length;
        for (int i = 0; i < len; i++) {
            int left = 0, right = len - 1;
            while (left < right) {
                int temp = A[i][left];
                A[i][left] = A[i][right];
                A[i][right] = temp;
                // 交换完成后接着翻转
                A[i][left] = 1 - A[i][left];
                A[i][right] = 1 - A[i][right];
                left++;
                right--;
            }
            if (left == right) {
                A[i][left] = 1 - A[i][left];
            }
        }
        return A;

    }

    public void _21_2_24() {
        int[][] A = {{1, 1, 0}, {1, 0, 1}, {0, 0, 0}};
        System.out.println(Arrays.deepToString(flipAndInvertImage(A)));
    }
}
