package leetcode_everyday._2021.Feb;

import java.util.HashMap;

public class _22 {
    /**
     * 每日一题：2021/2/22
     * 766. 托普利茨矩阵
     * 难度: easy
     * 给你一个 m x n 的矩阵 matrix 。如果这个矩阵是托普利茨矩阵，返回 true ；否则，返回 false 。
     * 如果矩阵上每一条由左上到右下的对角线上的元素都相同，那么这个矩阵是 托普利茨矩阵 。
     * <p>
     * 示例：
     * 输入：matrix = [[1,2,3,4],[5,1,2,3],[9,5,1,2]]
     * 输出：true
     * 解释：
     * 在上述矩阵中, 其对角线为:
     * "[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]"。
     * 各条对角线上的所有元素均相同, 因此答案是 True 。
     * <p>
     * 数据范围：
     * m == matrix.length
     * n == matrix[i].length
     * 1 <= m, n <= 20
     * 0 <= matrix[i][j] <= 99
     * 进阶：
     * 如果矩阵存储在磁盘上，并且内存有限，以至于一次最多只能将矩阵的一行加载到内存中，该怎么办？
     * 如果矩阵太大，以至于一次只能将不完整的一行加载到内存中，该怎么办？
     */


    /*
    比较暴力笨拙的方法
    遍历对角线的方法 ，将主对角线分上半部分和下半部分 分别遍历
    有点麻烦 需要重新思考题目要求
     */
    public boolean isToeplitzMatrix(int[][] matrix) {
        int r = matrix.length, c = matrix[0].length;
        for (int k = 0; k < c; k++) {
            HashMap<Integer, Integer> store = new HashMap<>();//Map<Integer, Integer>();
            for (int i = k; i < c; i++) {
                for (int j = 0; j < r && i < c; j++) {
                    int v = matrix[j][i++];
                    store.put(v, store.getOrDefault(v, 0) + 1);
                }
                if (store.size() > 1) {
                    return false;
                }
                store.clear();
            }
        }
        for (int k = 0; k < r; k++) {
            HashMap<Integer, Integer> store = new HashMap<>();//Map<Integer, Integer>();
            for (int i = k; i < r; i++) {
                for (int j = 0; j < c && i < r; j++) {
                    int v = matrix[i++][j];
                    store.put(v, store.getOrDefault(v, 0) + 1);
                }
                if (store.size() > 1) {
                    return false;
                }
                store.clear();
            }
        }
        return true;
    }

    /*
    官方题解：
    根据定义，当且仅当矩阵中每个元素都与其左上角相邻的元素（如果存在）相等时，该矩阵为托普利茨矩阵。
    因此，我们遍历该矩阵，将每一个元素和它左上角的元素相比对即可。

    很聪明的解法！！！
     */
    public boolean isToeplitzMatrix2(int[][] matrix) {
        int r = matrix.length, c = matrix[0].length;
        for (int i = 1; i < r; i++) {
            for (int j = 1; j < c; j++) {
                if (matrix[i][j] != matrix[i - 1][j - 1]) {
                    return false;
                }
            }
        }
        return true;
    }


    public void _21_2_22() {
        int[][] matrix = {{1, 2, 3, 4}, {5, 1, 2, 3}, {9, 5, 1, 2}};
        System.out.println(isToeplitzMatrix(matrix));
        System.out.println(isToeplitzMatrix2(matrix));

    }
}
