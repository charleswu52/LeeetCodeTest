package leetcode_everyday._2021.Aug;

import java.util.*;

/**
 * @author WuChao
 * @create 2021/8/24 9:35
 */
public class _24_787 {
    /**
     * 每日一题：2021/8/24
     * 787. K 站中转内最便宜的航班
     * 难度：medium
     * <p>
     * 有 n 个城市通过一些航班连接。给你一个数组 flights ，其中 flights[i] = [fromi, toi, pricei] ，
     * 表示该航班都从城市 fromi 开始，以价格 pricei 抵达 toi 。
     * <p>
     * 现在给定所有的城市和航班，以及出发城市 src 和目的地 dst，你的任务是找到出一条最多经过 k 站中转的路线，
     * 使得从 src 到 dst 的 价格最便宜 ，并返回该价格。 如果不存在这样的路线，则输出 -1。
     *
     * <p>
     * 示例 1：
     * 输入:
     * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
     * src = 0, dst = 2, k = 1
     * 输出: 200
     * 解释:
     * 从城市 0 到城市 2 在 1 站中转以内的最便宜价格是 200，如图中红色所示。
     *
     *
     * <p>
     * 注意:
     * 1 <= n <= 100
     * 0 <= flights.length <= (n * (n - 1) / 2)
     * flights[i].length == 3
     * 0 <= fromi, toi < n
     * fromi != toi
     * 1 <= pricei <= 104
     * 航班没有重复，且不存在自环
     * 0 <= src, dst, k < n
     * src != dst
     */


    /*
    思路1：动态规划（官方题解）
    用 f[t][i] 表示通过恰好 t 次航班，从出发城市 src 到达城市 i 需要的最小花费。在进行状态转移时，我们可以枚举最后一次航班的起点 j
        f[t][i]= (j,i)∈flights min  {f[t−1][j]+cost(j,i)}

    其中 (j,i)∈flights 表示在给定的航班数组 flights 中存在从城市 j 出发到达城市 i 的航班，cost(j,i) 表示该航班的花费。
    该状态转移方程的意义在于，枚举最后一次航班的起点 j，那么前 t−1 次航班的最小花费为 f[t−1][j] 加上最后一次航班的花费 cost(j,i)
    中的最小值，即为 f[t][i]。

    由于我们最多只能中转 kk 次，也就是最多搭乘 k+1k+1 次航班，最终的答案即为
        f[1][dst],f[2][dst],⋯,f[k+1][dst]
    中的最小值。

    细节
    当 t=0 时，状态 f[t][i] 表示不搭乘航班到达城市 i 的最小花费，因此有：
        f[t][i] = 0, i = src;
                  ∞, i != src;
    也就是说，如果 i 是出发城市 src，那么花费为 0；否则 f[0][i] 不是一个合法的状态，由于在状态转移方程中我们需要求出的是最小值，
    因此可以将不合法的状态置为极大值 ∞。根据题目中给出的数据范围，航班的花费不超过 10^4 ，最多搭乘航班的次数 k+1 不超过 101，
    那么在实际的代码编写中，我们只要使得极大值大于 10^4 * 101，就可以将表示不合法状态的极大值与合法状态的花费进行区分。

    在状态转移中，我们需要使用二重循环枚举 t 和 i，随后枚举所有满足 (j,i)∈flights 的 j，这样做的劣势在于没有很好地利用数组 flights，
    为了保证时间复杂度较优，需要将 flights 中的所有航班存储在一个新的邻接表中。一种可行的解决方法是，我们只需要使用一重循环枚举 t，
    随后枚举 flights 中的每一个航班 (j,i,cost)，并用 f[t−1][j]+cost 更新 f[t][i]，这样就免去了邻接表的使用。

    注意到 f[t][i] 只会从 f[t−1][..] 转移而来，因此我们也可以使用两个长度为 n 的一维数组进行状态转移，减少空间的使用。
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        final int INF = 10000 * 101 + 1;
        int[][] f = new int[k + 2][n];
        for (int i = 0; i < k + 2; i++) {
            Arrays.fill(f[i], INF);
        }

        f[0][src] = 0;
        for (int t = 1; t <= k + 1; t++) {
            for (int[] flight : flights) {
                int j = flight[0], i = flight[1], cost = flight[2];
                f[t][i] = Math.min(f[t][i], f[t - 1][j] + cost);
            }
        }
        int ans = INF;
        for (int t = 1; t <= k + 1; t++) {
            ans = Math.min(ans, f[t][dst]);
        }
        return ans == INF ? -1 : ans;
    }


    /*
    思路2：DFS 搜索
    缺点：会超时
     */
    Map<Integer, List<int[]>> graph;
    int ans = Integer.MAX_VALUE;
    public int findCheapestPrice2(int n, int[][] flights, int src, int dst, int k) {
        // 根据数组构建图 u -> v,w
        graph = new HashMap<>();
        for (int[] f : flights) {
            graph.putIfAbsent(f[0], new ArrayList<>());
            graph.get(f[0]).add(new int[]{f[1], f[2]});
        }
        dfs(src, dst, k + 1, 0);
        return ans == Integer.MAX_VALUE ? -1 : ans;


    }

    private void dfs(int src, int dst, int k, int dist) {
        if (k < 0) {
            return;
        }
        if (src == dst) {
            ans = dist;
            return;
        }
        if (graph.containsKey(src)) {
            for (int[] f : graph.get(src)) {
                int u = f[0], w = f[1];
                if (dist + w > ans) {
                    continue;
                }
                dfs(u, dst, k - 1, dist + w);
            }
        }
    }
}
