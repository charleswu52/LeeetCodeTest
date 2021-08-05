package leetcode_everyday.Oct;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author WuChao
 * @create 2021/8/5 17:41
 */
public class _5_802 {
    /**
     * 每日一题：2021/8/5
     * 802. 找到最终的安全状态
     * 难度：medium
     * <p>
     * 在有向图中，以某个节点为起始节点，从该点出发，每一步沿着图中的一条有向边行走。如果到达的节点是终点（即它没有连出的有向边），则停止。
     *
     * 对于一个起始节点，如果从该节点出发，无论每一步选择沿哪条有向边行走，最后必然在有限步内到达终点，则将该起始节点称作是 安全 的。
     *
     * 返回一个由图中所有安全的起始节点组成的数组作为答案。答案数组中的元素应当按 升序 排列。
     *
     * 该有向图有 n 个节点，按 0 到 n - 1 编号，其中 n 是 graph 的节点数。图以下述形式给出：graph[i] 是编号 j 节点的一个列表，满足 (i, j) 是图的一条有向边。
     *
     *
     *
     *
     * <p>
     * 示例 1:
     * 输入：graph = [[1,2],[2,3],[5],[0],[5],[],[]]
     * 输出：[2,4,5,6]
     *
     * 输入：graph = [[1,2,3,4],[1,2],[3,4],[0,4],[]]
     * 输出：[4]
     * <p>
     * 注意:
     * n == graph.length
     * 1 <= n <= 104
     * 0 <= graph[i].length <= n
     * graph[i] 按严格递增顺序排列。
     * 图中可能包含自环。
     * 图中边的数目在范围 [1, 4 * 104] 内。
     */


    /*
    思路：拓扑排序
    根据题意，若一个节点没有出边，则该节点是安全的；若一个节点出边相连的点都是安全的，则该节点也是安全的。

    根据这一性质，我们可以将图中所有边反向，得到一个反图，然后在反图上运行拓扑排序。

    具体来说，首先得到反图 rg 及其入度数组 inDeg。将所有入度为 0 的点加入队列，然后不断取出队首元素，
    将其出边相连的点的入度减一，若该点入度减一后为 0，则将该点加入队列，如此循环直至队列为空。
    循环结束后，所有入度为 0 的节点均为安全的。我们遍历入度数组，并将入度为 0 的点加入答案列表。
     */

    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        // 将原图重建为一张反图
        List<List<Integer>> rg = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            rg.add(new ArrayList<>());
        }
        // 入度数组
        int[] inDeg = new int[n];
        for (int x = 0; x < n; x++) {
            for (int y : graph[x]) {
                rg.get(y).add(x);
            }
            inDeg[x] = graph[x].length;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDeg[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int y = queue.poll();
            for (int x : rg.get(y)) {
                if (--inDeg[x] == 0) {
                    queue.offer(x);
                }
            }
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (inDeg[i] == 0) {
                res.add(i);
            }
        }
        return res;
    }
}
