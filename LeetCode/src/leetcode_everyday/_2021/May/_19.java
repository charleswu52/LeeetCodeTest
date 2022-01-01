package leetcode_everyday._2021.May;

import java.util.*;

/**
 * @author WuChao
 * @since 2021/5/19 上午8:19
 */
public class _19 {
    /**
     * 每日一题：2021/5/19
     * 1738. 找出第 K 大的异或坐标值
     * 难度: medium
     * <p>
     * 给你一个二维矩阵 matrix 和一个整数 k ，矩阵大小为 m x n 由非负整数组成。
     *
     * 矩阵中坐标 (a, b) 的 值 可由对所有满足 0 <= i <= a < m 且 0 <= j <= b < n 的元素 matrix[i][j]（下标从 0 开始计数）执行异或运算得到。
     *
     * 请你找出 matrix 的所有坐标中第 k 大的值（k 的值从 1 开始计数）。
     *
     * <p>
     * 示例：
     * 输入：matrix = [[5,2],[1,6]], k = 1
     * 输出：7
     * 解释：坐标 (0,1) 的值是 5 XOR 2 = 7 ，为最大的值。
     * <p>
     * 输入：matrix = [[5,2],[1,6]], k = 2
     * 输出：5
     * 解释：坐标 (0,0) 的值是 5 = 5 ，为第 2 大的值。
     * <p>
     *
     * <p>
     * 数据范围：
     * m == matrix.length
     * n == matrix[i].length
     * 1 <= m, n <= 1000
     * 0 <= matrix[i][j] <= 106
     * 1 <= k <= m * n
     */
    /*
    还是前缀和的思路：
    这里要维护一个二维数组的前缀和
    设二维前缀和 pre(i,j) 表示矩阵 matrix 中所有满足 0≤x<i 且 0≤y<j 的元素执行按位异或运算的结果。
    与一维前缀和类似，要想快速得到 pre(i,j)，我们需要已经知道 pre(i−1,j)，pre(i,j−1) 以及 pre(i−1,j−1) 的结果，即：
        pre(i,j)=pre(i−1,j)⊕pre(i,j−1)⊕pre(i−1,j−1)⊕matrix(i,j)


     */
    /**
     *
     * @param matrix
     * @param k
     * @return
     */
    public int kthLargestValue(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        int[][] pre = new int[m + 1][n + 1];
        List<Integer> store = new ArrayList<>();
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                pre[i][j] = pre[i - 1][j] ^ pre[i][j - 1] ^ pre[i - 1][j - 1] ^ matrix[i-1][j-1];
                store.add(pre[i][j]);
            }
        }
        Collections.sort(store);  // 直接排序
        return store.get(store.size() - k);

    }

    public void test() {

    }


}
