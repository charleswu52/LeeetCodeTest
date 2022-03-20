package leetcode_everyday._2022.Mar;

import java.util.*;

/**
 * @author WuChao
 * @create 2022/3/20 8:58
 */
public class _20_2039 {
    /**
     * 每日一题：2022/3/20
     * <p>
     * 2039. 网络空闲的时刻
     * <p>
     * 难度：medium
     * <p>
     * 给你一个有 n 个服务器的计算机网络，服务器编号为 0 到 n - 1 。同时给你一个二维整数数组 edges ，
     * 其中 edges[i] = [ui, vi] 表示服务器 ui 和 vi 之间有一条信息线路，在 一秒 内它们之间可以传输 任意 数目的信息。
     * 再给你一个长度为 n 且下标从 0 开始的整数数组 patience 。
     *
     * 题目保证所有服务器都是 相通 的，也就是说一个信息从任意服务器出发，都可以通过这些信息线路直接或间接地到达任何其他服务器。
     *
     * 编号为 0 的服务器是 主 服务器，其他服务器为 数据 服务器。每个数据服务器都要向主服务器发送信息，并等待回复。
     * 信息在服务器之间按 最优 线路传输，也就是说每个信息都会以 最少时间 到达主服务器。主服务器会处理 所有 新到达的信息并 立即 按照每条信息来时的路线 反方向 发送回复信息。
     *
     * 在 0 秒的开始，所有数据服务器都会发送各自需要处理的信息。从第 1 秒开始，每 一秒最 开始 时，
     * 每个数据服务器都会检查它是否收到了主服务器的回复信息（包括新发出信息的回复信息）：
     *
     * 如果还没收到任何回复信息，那么该服务器会周期性 重发 信息。数据服务器 i 每 patience[i] 秒都会重发一条信息，也就是说，
     * 数据服务器 i 在上一次发送信息给主服务器后的 patience[i] 秒 后 会重发一条信息给主服务器。
     * 否则，该数据服务器 不会重发 信息。
     * 当没有任何信息在线路上传输或者到达某服务器时，该计算机网络变为 空闲 状态。
     *
     * 请返回计算机网络变为 空闲 状态的 最早秒数
     * <p>
     * 示例1
     * <p>
     * 输入：edges = [[0,1],[1,2]], patience = [0,2,1]
     * 输出：8
     * 解释：
     * 0 秒最开始时，
     * - 数据服务器 1 给主服务器发出信息（用 1A 表示）。
     * - 数据服务器 2 给主服务器发出信息（用 2A 表示）。
     *
     * 1 秒时，
     * - 信息 1A 到达主服务器，主服务器立刻处理信息 1A 并发出 1A 的回复信息。
     * - 数据服务器 1 还没收到任何回复。距离上次发出信息过去了 1 秒（1 < patience[1] = 2），所以不会重发信息。
     * - 数据服务器 2 还没收到任何回复。距离上次发出信息过去了 1 秒（1 == patience[2] = 1），所以它重发一条信息（用 2B 表示）。
     *
     * 2 秒时，
     * - 回复信息 1A 到达服务器 1 ，服务器 1 不会再重发信息。
     * - 信息 2A 到达主服务器，主服务器立刻处理信息 2A 并发出 2A 的回复信息。
     * - 服务器 2 重发一条信息（用 2C 表示）。
     * ...
     * 4 秒时，
     * - 回复信息 2A 到达服务器 2 ，服务器 2 不会再重发信息。
     * ...
     * 7 秒时，回复信息 2D 到达服务器 2 。
     *
     * 从第 8 秒开始，不再有任何信息在服务器之间传输，也不再有信息到达服务器。
     * 所以第 8 秒是网络变空闲的最早时刻。
     * <p>
     * 范围
     * <p>
     * n == patience.length
     * 2 <= n <= 105
     * patience[0] == 0
     * 对于 1 <= i < n ，满足 1 <= patience[i] <= 105
     * 1 <= edges.length <= min(105, n * (n - 1) / 2)
     * edges[i].length == 2
     * 0 <= ui, vi < n
     * ui != vi
     * 不会有重边。
     * 每个服务器都直接或间接与别的服务器相连。
     */

    /* 思路： 计算每个数据服务器到主服务器的最短距离 该 dis*2 =time  就是第一条数据发出并返回需要的时间
    如果 patience[i] >= time 就不会重发信息
    如果 patience[i] <  time  那么在第一条信息返回请求时 求 重复发出的次数
    比较所有patience[i] 所有时间 取最大即为答案

    先要通过搜索找到每个数据服务器到主服务器0的最短距离,这通过 最短路径算法 迪杰斯特拉 BFS 搜索算法实现
     */

    public int networkBecomesIdle(int[][] edges, int[] patience) {
        int n = patience.length;
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        int[] distances = getDistance(graph, n);
        int res = 0;
        for (int i = 1; i < n; i++) {
            int path = distances[i] * 2;
            int retry = path <= distances[i] ? 0 : path % patience[i] == 0 ? path / patience[i] - 1 : path / patience[i];
            int curCost = retry * patience[i] + path;
            res = Math.max(res, curCost);
        }
        return res+1;
    }

    public int[] getDistance(List<List<Integer>> graph, int n) {
        int[] dis = new int[n];
        Arrays.fill(dis, n + 10);
        dis[0] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int next : graph.get(cur)) {
                if (dis[cur] + 1 < dis[next]) {
                    dis[next] = dis[cur] + 1;
                    queue.offer(next);
                }
            }
        }
        return dis;

    }
}
