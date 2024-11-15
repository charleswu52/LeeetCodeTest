package leetcode_everyday._2022.Jun;

import java.util.Arrays;

/**
 * @author WuChao
 * @create 2022/6/27 10:52
 */
public class _25_2_091 {
    /**
     * 每日一题：2022/6/25
     * <p>
     * 剑指 Offer II 091. 粉刷房子
     * <p>
     * 难度：medium
     * <p>
     * 假如有一排房子，共 n 个，每个房子可以被粉刷成红色、蓝色或者绿色这三种颜色中的一种，你需要粉刷所有的房子并且使其相邻的两个房子颜色不能相同。
     * <p>
     * 当然，因为市场上不同颜色油漆的价格不同，所以房子粉刷成不同颜色的花费成本也是不同的。
     * <p>
     * 每个房子粉刷成不同颜色的花费是以一个 n x 3 的正整数矩阵 costs 来表示的。
     * <p>
     * 例如，costs[0][0] 表示第 0 号房子粉刷成红色的成本花费；costs[1][2] 表示第 1 号房子粉刷成绿色的花费，以此类推。
     * <p>
     * 请计算出粉刷完所有房子最少的花费成本。
     * <p>
     * 示例
     * <p>
     * 输入: costs = [[17,2,17],[16,16,5],[14,3,19]]
     * <p>
     * 输出: 10
     * <p>
     * 解释: 将 0 号房子粉刷成蓝色，1 号房子粉刷成绿色，2 号房子粉刷成蓝色。
     * 最少花费: 2 + 5 + 3 = 10。
     * <p>
     * 范围
     * <p>
     * costs.length == n
     * costs[i].length == 3
     * 1 <= n <= 100
     * 1 <= costs[i][j] <= 20
     */

    public int minCost(int[][] costs) {
        int n = costs.length;
        int[] dp = new int[3];

        for (int i = 0; i < 3; i++) {
            dp[i] = costs[0][i];
        }
        for (int i = 1; i < n; i++) {
            int[] dpNew = new int[3];
            for (int j = 0; j < 3; j++) {
                dpNew[j] = Math.min(dp[(j + 1) % 3], dp[(j + 2) % 3]) + costs[i][j];
            }
            dp = dpNew;
        }
        return Arrays.stream(dp).min().getAsInt();
    }
}
