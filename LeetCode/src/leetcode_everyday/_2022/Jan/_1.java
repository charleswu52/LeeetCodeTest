package leetcode_everyday._2022.Jan;

import jdk.internal.org.objectweb.asm.tree.analysis.Analyzer;

/**
 * @author WuChao
 * @create 2022/1/1 13:06
 */
public class _1 {
    /**
     * 每日一题：2022/1/1
     * <p>
     * 2022. 将一维数组转变成二维数组
     * <p>
     * 难度：easy
     * <p>
     * 给你一个下标从 0 开始的一维整数数组 original 和两个整数 m 和 n 。你需要使用 original 中 所有 元素创建一个 m 行 n 列的二维数组。
     *
     * original 中下标从 0 到 n - 1 （都 包含 ）的元素构成二维数组的第一行，下标从 n 到 2 * n - 1 （都 包含 ）
     * 的元素构成二维数组的第二行，依此类推。
     *
     * 请你根据上述过程返回一个 m x n 的二维数组。如果无法构成这样的二维数组，请你返回一个空的二维数组。
     * <p>
     * 示例 1：
     * <p>
     * 输入：original = [1,2,3,4], m = 2, n = 2
     *
     * 输出：[[1,2],[3,4]]
     *
     * 解释：
     *
     * 构造出的二维数组应该包含 2 行 2 列。
     * original 中第一个 n=2 的部分为 [1,2] ，构成二维数组的第一行。
     * original 中第二个 n=2 的部分为 [3,4] ，构成二维数组的第二行。
     * <p>
     * 范围
     * <p>
     * 1 <= original.length <= 5 * 104
     * 1 <= original[i] <= 105
     * 1 <= m, n <= 4 * 104
     **/

    /*
    简单数组模拟
     */

    public int[][] construct2DArray(int[] original, int m, int n) {
        int len = original.length;
        if (len != m * n) {
            return new int[][]{};
        }
        int[][] ans = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans[i][j] = original[i * n + j];
            }
        }
        return ans;


    }
}
