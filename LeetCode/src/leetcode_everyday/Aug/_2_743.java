package leetcode_everyday.Aug;

import java.util.*;

/**
 * @author WuChao
 * @create 2021/8/2 8:25
 */
public class _2_743 {
    /**
     * 每日一题：2021/8/2
     * 743. 网络延迟时间
     * 难度: medium
     * <p>
     * 有 n 个网络节点，标记为 1 到 n。
     *
     * 给你一个列表 times，表示信号经过 有向 边的传递时间。 times[i] = (ui, vi, wi)，其中 ui 是源节点，vi 是目标节点，
     * wi 是一个信号从源节点传递到目标节点的时间。
     *
     * 现在，从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1 。
     *
     * <p>
     * 输入：times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
     * 输出：2
     *
     * 输入：times = [[1,2,1]], n = 2, k = 1
     * 输出：1
     *
     * <p>
     * 数据范围：
     * 1 <= k <= n <= 100
     * 1 <= times.length <= 6000
     * times[i].length == 3
     * 1 <= ui, vi <= n
     * ui != vi
     * 0 <= wi <= 100
     * 所有 (ui, vi) 对都 互不相同（即，不含重复边）
     *
     */

    /*
    题目解析：
    很明显是图上的单源最短路径问题。
    求出图上节点k到其他所有点的最短路径，其中的最大值就是答案，如果存在从k无法到达的点，则返回-1

    思路：Dijkstra算法（地杰斯特拉）
    将所有节点分成两类：已确定从起点到当前点的最短路长度的节点，以及未确定从起点到当前点的最短路长度的节点（下面简称「未确定节点」和「已确定节点」）。

    每次从「未确定节点」中取一个与起点距离最短的点，将它归类为「已确定节点」，并用它「更新」从起点到其他所有「未确定节点」的距离。
    直到所有点都被归类为「已确定节点」。

    用节点 A「更新」节点 B 的意思是，用起点到节点 A 的最短路长度加上从节点 A 到节点 B 的边的长度，去比较起点到节点 B 的最短路长度，
    如果前者小于后者，就用前者更新后者。这种操作也被叫做「松弛」。

    这里暗含的信息是：每次选择「未确定节点」时，起点到它的最短路径的长度可以

     */

    /*
    实现方法1：枚举
    代码将节点编号减小了 11，从而使节点编号位于 [0,n-1][0,n−1] 范围。
    枚举写法的复杂度如下：
        时间复杂度：O(n^2+m)，其中 m 是数组 times 的长度。
        空间复杂度：O(n^2)。邻接矩阵需占用 O(n^2) 的空间。
     */
    public int networkDelayTime(int[][] times, int n, int k) {
        // 构造节点的邻接矩阵，初始值默认均不可达（INF）
        final int INF = Integer.MAX_VALUE / 2;
        int[][] g = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(g[i], INF);
        }
        // 邻接矩阵赋值
        for (int[] t : times) {
            int x = t[0] - 1;
            int y = t[1] - 1;
            g[x][y] = t[2];
        }

        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        dist[k - 1] = 0;
        boolean[] uesd = new boolean[n];
        for (int i = 0; i < n; i++) {
            int x = -1;
            for (int y = 0; y < n; y++) {
                if (!uesd[y] && (x == -1 || dist[y] < dist[x])) {
                    x = y;
                }
            }
            uesd[x] = true;
            for (int y = 0; y < n; y++) {
                dist[y] = Math.min(dist[y], dist[x] + g[x][y]);
            }
        }
        int ans = Arrays.stream(dist).max().getAsInt();
        return ans == INF ? -1 : ans;
    }

    /*
    实现2：小根堆
    使用小根堆来寻找【未确定节点】中与起点距离最近的点
    时间复杂度：O(mlogm)，其中 mm 是数组 times 的长度。
    空间复杂度：O(n+m)。

    由于本题边数远大于点数，是一张稠密图，因此在运行时间上，枚举写法要略快于堆的写
     */
    public int networkDelayTim2(int[][] times, int n, int k) {
        final int INF = Integer.MAX_VALUE / 2;
        List<int[]>[] g = new List[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }
        for (int[] t : times) {
            int x = t[0] - 1, y = t[1] - 1;
            g[x].add(new int[]{y, t[2]});
        }

        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        dist[k - 1] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);
        pq.offer(new int[]{0, k - 1});
        while (!pq.isEmpty()) {
            int[] p = pq.poll();
            int time = p[0], x = p[1];
            if (dist[x] < time) {
                continue;
            }
            for (int[] e : g[x]) {
                int y = e[0], d = dist[x] + e[1];
                if (d < dist[y]) {
                    dist[y] = d;
                    pq.offer(new int[]{d, y});
                }
            }
        }
        int ans = Arrays.stream(dist).max().getAsInt();
        return ans == INF ? -1 : ans;


    }
}
