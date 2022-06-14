package leetcode_everyday._2022.Jun;

/**
 * @Author CharlesWu
 * @Create 2022/6/14 9:53
 * @Version 1.0
 * @Description
 * @Note
 */
public class _14_498 {
    /**
     * 每日一题：2022/6/14
     * <p>
     * 498. 对角线遍历
     * <p>
     * 难度：medium
     * <p>
     * 给你一个大小为 m x n 的矩阵 mat ，请以对角线遍历的顺序，用一个数组返回这个矩阵中的所有元素。
     * <p>
     * 示例
     * <p>
     * 输入：mat = [[1,2,3],[4,5,6],[7,8,9]]
     * <p>
     * 输出：[1,2,4,7,5,3,6,8,9]
     * <p>
     * 范围
     * <p>
     * m == mat.length
     * n == mat[i].length
     * 1 <= m, n <= 104
     * 1 <= m * n <= 104
     * -105 <= mat[i][j] <= 105
     */


    /*
    思路：模拟
     */
    public int[] findDiagonalOrder(int[][] mat) {
        if (mat == null || mat.length == 0) {
            return new int[]{};
        }
        int m = mat.length, n = mat[0].length;
        int r = 0, c = 0;
        int[] res = new int[m * n];
        for (int i = 0; i < res.length; i++) {
            res[i] = mat[r][c];
            // r+c 等于遍历的层数，偶数向上遍历，奇数向下遍历
            if ((r + c) % 2 == 0) {
                if (c == n - 1) {
                    // 向下移动一格准备向下遍历
                    r++;
                } else if (r == 0) {
                    // 向右移动一格准备向下遍历
                    c++;
                } else {
                    // 向上移动
                    r--;
                    c++;
                }
            } else {
                if (r == m - 1) {
                    // 向右移动一格准备向上遍历
                    c++;
                } else if (c == 0) {
                    // 向下移动一格准备向上遍历
                    r++;
                } else {
                    // 向下移动
                    r++;
                    c--;
                }
            }
        }
        return res;

    }
}
