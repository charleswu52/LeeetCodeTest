package leetcodetest.May;

import java.util.HashMap;

/**
 * @author WuChao
 * @since 2021/5/29 上午8:42
 */
public class _29 {
    /**
     * 每日一题：2021/5/29
     * 1074. 元素和为目标值的子矩阵数量
     * 难度: hard
     * <p>
     * 给出矩阵 matrix 和目标值 target，返回元素总和等于目标值的非空子矩阵的数量。
     *
     * 子矩阵 x1, y1, x2, y2 是满足 x1 <= x <= x2 且 y1 <= y <= y2 的所有单元 matrix[x][y] 的集合。
     *
     * 如果 (x1, y1, x2, y2) 和 (x1', y1', x2', y2') 两个子矩阵中部分坐标不同（如：x1 != x1'），那么这两个子矩阵也不同。
     * <p>
     * 示例:
     * 输入：matrix = [[0,1,0],[1,1,1],[0,1,0]], target = 0
     * 输出：4
     * 解释：四个只含 0 的 1x1 子矩阵。
     *
     * 输入：matrix = [[1,-1],[-1,1]], target = 0
     * 输出：5
     * 解释：两个 1x2 子矩阵，加上两个 2x1 子矩阵，再加上一个 2x2 子矩阵。
     * <p>
     * 数据范围：
     * 1 <= matrix.length <= 100
     * 1 <= matrix[0].length <= 100
     * -1000 <= matrix[i] <= 1000
     * -10^8 <= target <= 10^8
     */

    /*
    思路：参考29_2的思路 前缀和 +哈希表
    只不过这里变成了二维矩阵
    对于每列的元素和 sum 的计算，我们在枚举子矩阵上边界 i 时，初始下边界 j 为 i，此时 sum 就是矩阵第 i 行的元素。
    每次向下延长下边界 j 时，我们可以将矩阵第 j 行的元素累加到 sum 中。


     */

    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int res = 0;
        int m = matrix.length, n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            int[] sum = new int[n]; // 每一列元素的和值
            for (int j = i; j >= 0; j--) {
                for (int col = 0; col < n; col++) {
                    sum[col] += matrix[j][col];// 更新每列的元素和
                }
                res += subArraySum(sum, target);
            }

        }
        return res;

    }

    public int subArraySum(int[] sum, int target) {
        HashMap<Integer, Integer> store = new HashMap<>();
        store.put(0, 1);
        int res = 0, pre = 0;
        for (int i : sum) {
            pre += i;
            if (store.containsKey(pre - target)) {
                res += store.get(pre - target);
            }
            store.put(pre, store.getOrDefault(pre, 0) + 1);
        }
        return res;
    }

}
