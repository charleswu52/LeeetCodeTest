package leetcode_everyday.Oct;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author WuChao
 * @create 2021/8/15 19:29
 */
public class _6_847 {
    /**
     * 每日一题：2021/8/6
     * 847. 访问所有节点的最短路径
     * 难度：hard
     * <p>
     * 存在一个由 n 个节点组成的无向连通图，图中的节点按从 0 到 n - 1 编号。
     * 给你一个数组 graph 表示这个图。其中，graph[i] 是一个列表，由所有与节点 i 直接相连的节点组成。
     * 返回能够访问所有节点的最短路径的长度。你可以在任一节点开始和停止，也可以多次重访节点，并且可以重用边。
     *
     * <p>
     * 示例 1：
     * 输入：graph = [[1,2,3],[0],[0],[0]]
     * 输出：4
     * 解释：一种可能的路径为 [1,0,2,0,3]
     *
     * 示例 2：
     * 输入：graph = [[1],[0,2,4],[1,3,4],[2],[1,2]]
     * 输出：4
     * 解释：一种可能的路径为 [0,1,4,2,3]
     *
     * <p>
     * 注意:
     * n == graph.length
     * 1 <= n <= 12
     * 0 <= graph[i].length < n
     * graph[i] 不包含 i
     * 如果 graph[a] 包含 b ，那么 graph[b] 也包含 a
     * 输入的图总是连通图
     */

    /*
    思路：广度优先搜索 + 状态压缩

    状态压缩：
    状态压缩也即用一个变量来表示当前状态，比较常用的方式是利用一个 n 位 kk 进制数 mask 表示当前 n 个节点的所处的 k 个不同状态。
    对于本题而言，某个节点只需要记录是否遍历过，所以利用二进制即可，由于本题数据范围 n≤12，所以不会超过 int 型范围。

    一般而言，mask 从低到高第 i 位为 0 表示第 i 个节点还未被访问过，为 1 则相反。例如，假设有 3 个点，点 1 遍历过，点 2,3 未遍历，
    则 mask = (001)_2；若点 3 遍历过，点 1,2 未遍历，则 mask = (100)_2。特别地，三个点均未遍历时，mask = (000)_2 = 0，均遍历过时，
    mask = (111)_2 = 2^k - 1

    一些状态压缩的基本操作如下：
        （1）访问第 i 个点的状态：state=(1 << i) & mask
        （2）更改第 i 个点状态为 1：mask = mask | (1 << i)
     */

    public int shortestPathLength(int[][] graph) {
        int n = graph.length;

        // 初始化队列以及标记数组，存入起点
        Queue<int[]> queue = new LinkedList<>();// 数组中保存三个属性 idx mask dist
        boolean[][] visited = new boolean[n][1 << n];
        for (int i = 0; i < n; i++) {
            queue.offer(new int[]{i, 1 << i, 0});
            visited[i][1 << i] = true;
        }

        // 开始搜素
        while (!queue.isEmpty()) {
            int[] tuple = queue.poll();
            int idx = tuple[0], mask = tuple[1], dist = tuple[2];

            if (mask == (1 << n) - 1) {
                return dist;
            }

            for (int x : graph[idx]) {
                int next_mask = mask | (1 << x);
                if (!visited[x][next_mask]) {
                    queue.offer(new int[]{x, next_mask, dist + 1});
                    visited[x][next_mask] = true;
                }
            }
        }

        return 0;



    }
}
