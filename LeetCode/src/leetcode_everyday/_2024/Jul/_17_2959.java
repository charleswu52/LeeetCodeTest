
package leetcode_everyday._2024.Jul;

import java.util.Arrays;

/**
 * @author charles
 * @create 2024/7/20
 */
public class _17_2959 {
    /**
     * 每日一题：2024/7/17
     * 2959. 关闭分部的可行集合数目
     * https://leetcode.cn/problems/number-of-possible-sets-of-closing-branches/description/?envType=daily-question&envId=2024-07-21
     */
        public int numberOfSets(int n, int maxDistance, int[][] roads) {
            int[][] g = new int[n][n];
            for (int[] row : g) {
                Arrays.fill(row, Integer.MAX_VALUE / 2); // 防止加法溢出
            }
            for (int[] e : roads) {
                int x = e[0];
                int y = e[1];
                int wt = e[2];
                g[x][y] = Math.min(g[x][y], wt);
                g[y][x] = Math.min(g[y][x], wt);
            }

            int ans = 0;
            int[][] f = new int[n][n];
            next:
            for (int s = 0; s < (1 << n); s++) {
                for (int i = 0; i < n; i++) {
                    if ((s >> i & 1) == 1) {
                        System.arraycopy(g[i], 0, f[i], 0, n);
                    }
                }

                // Floyd 算法（只考虑在 s 中的节点）
                for (int k = 0; k < n; k++) {
                    if ((s >> k & 1) == 0) continue;
                    for (int i = 0; i < n; i++) {
                        if ((s >> i & 1) == 0) continue;
                        for (int j = 0; j < n; j++) {
                            f[i][j] = Math.min(f[i][j], f[i][k] + f[k][j]);
                        }
                    }
                }

                // 判断保留的节点之间的最短路是否均不超过 maxDistance
                for (int i = 0; i < n; i++) {
                    if ((s >> i & 1) == 0) continue;
                    for (int j = 0; j < i; j++) {
                        if ((s >> j & 1) == 1 && f[i][j] > maxDistance) {
                            continue next;
                        }
                    }
                }
                ans++;
            }
            return ans;
    }
}
