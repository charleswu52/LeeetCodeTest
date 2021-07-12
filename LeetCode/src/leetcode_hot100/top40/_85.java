package leetcode_hot100.top40;

/**
 * @author WuChao
 * @create 2021/7/12 18:31
 */
public class _85 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 85. 最大矩形
     * 难度：hard
     * <p>
     * 给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
     * <p>
     * 示例
     * 输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
     * 输出：6
     * 解释：最大矩形如上图所示。
     *
     * 输入：matrix = [["0"]]
     * 输出：0
     *
     * <p>
     * 范围：
     * rows == matrix.length
     * cols == matrix[0].length
     * 0 <= row, cols <= 200
     * matrix[i][j] 为 '0' 或 '1'
     */

    /*
    思路：
    https://leetcode-cn.com/problems/maximal-rectangle/solution/zui-da-ju-xing-by-leetcode-solution-bjlu/
    与  84 题类似的思路，不过要先将二维矩阵进行一个转换成类似 柱状图的问题，再进行求解。
    第一步：
        先计算出矩阵的每个元素左边连续1的数量，使用二维数组left记录，其中left[i][j]为第i行第j列元素的左边连续1的数量
    第二步：
        对于矩阵中的任意一个点，枚举以该点为右下角的全1矩形。
        当考察以 matrix[i][j] 为右下角的矩形时，我们枚举满足 0≤k≤i 的所有可能的 k，此时矩阵的最大宽度就为
        left[i][j],left[i−1][j],…,left[k][j] 的最小值。

的最小值。

     */
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        if (m == 0) {
            return 0;
        }
        int n = matrix[0].length;
        int[][] left = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    left[i][j] = (j == 0 ? 0 : left[i][j - 1]) + 1;
                }
            }
        }

        int ret = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '0') {
                    continue;
                }
                int width = left[i][j];
                int area = width;
                for (int k = i - 1; k >= 0; k--) {
                    width = Math.min(width, left[k][j]);
                    area = Math.max(area, (i - k + 1) * width);
                }
                ret = Math.max(ret, area);
            }
        }
        return ret;
    }

}
