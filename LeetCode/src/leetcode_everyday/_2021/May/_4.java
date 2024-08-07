package leetcode_everyday._2021.May;

import java.util.Arrays;

/**
 * @author WuChao
 * @since 2021/5/5 下午4:20
 */
public class _4 {
    /**
     * 每日一题：2021/5/4
     * 1473. 粉刷房子 III
     * 难度: hard
     * 在一个小城市里，有 m 个房子排成一排，你需要给每个房子涂上 n 种颜色之一（颜色编号为 1 到 n）。
     * 有的房子去年夏天已经涂过颜色了，所以这些房子不需要被重新涂色。
     *
     * 我们将连续相同颜色尽可能多的房子称为一个街区。（比方说 houses = [1,2,2,3,3,2,1,1] ，它包含 5 个街区
     * [{1}, {2,2}, {3,3}, {2}, {1,1}] 。）
     *
     * 给你一个数组 houses ，一个 m * n 的矩阵 cost 和一个整数 target ，其中：
     *
     * houses[i]：是第 i 个房子的颜色，0 表示这个房子还没有被涂色。
     * cost[i][j]：是将第 i 个房子涂成颜色 j+1 的花费。
     * 请你返回房子涂色方案的最小总花费，使得每个房子都被涂色后，恰好组成 target 个街区。如果没有可用的涂色方案，请返回 -1 。
     *
     *
     * <p>
     * 示例：
     * 输入：houses = [0,0,0,0,0], cost = [[1,10],[10,1],[10,1],[1,10],[5,1]], m = 5, n = 2, target = 3
     * 输出：9
     * 解释：房子涂色方案为 [1,2,2,1,1]
     * 此方案包含 target = 3 个街区，分别是 [{1}, {2,2}, {1,1}]。
     * 涂色的总花费为 (1 + 1 + 1 + 1 + 5) = 9。
     *
     *
     * <p>
     * 数据范围：
     * m == houses.length == cost.length
     * n == cost[i].length
     * 1 <= m <= 100
     * 1 <= n <= 20
     * 1 <= target <= m
     * 0 <= houses[i]  <= n
     * 1 <= cost[i][j] <= 10^4
     *
     */

    // 极大值
    // 选择 Integer.MAX_VALUE / 2 的原因是防止整数相加溢出
    static final int INFTY = Integer.MAX_VALUE / 2;

    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        // 将颜色调整为从 0 开始编号，没有被涂色标记为 -1
        for (int i = 0; i < m; ++i) {
            --houses[i];
        }

        // dp 所有元素初始化为极大值
        int[][][] dp = new int[m][n][target];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                Arrays.fill(dp[i][j], INFTY);
            }
        }

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (houses[i] != -1 && houses[i] != j) {
                    continue;
                }

                for (int k = 0; k < target; ++k) {
                    for (int j0 = 0; j0 < n; ++j0) {
                        if (j == j0) {
                            if (i == 0) {
                                if (k == 0) {
                                    dp[i][j][k] = 0;
                                }
                            } else {
                                dp[i][j][k] = Math.min(dp[i][j][k], dp[i - 1][j][k]);
                            }
                        } else if (i > 0 && k > 0) {
                            dp[i][j][k] = Math.min(dp[i][j][k], dp[i - 1][j0][k - 1]);
                        }
                    }

                    if (dp[i][j][k] != INFTY && houses[i] == -1) {
                        dp[i][j][k] += cost[i][j];
                    }
                }
            }
        }

        int ans = INFTY;
        for (int j = 0; j < n; ++j) {
            ans = Math.min(ans, dp[m - 1][j][target - 1]);
        }
        return ans == INFTY ? -1 : ans;
    }
}
