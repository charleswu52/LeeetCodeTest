package leetcode_everyday.Aug;

import java.util.*;

/**
 * @author WuChao
 * @create 2021/8/25 10:01
 */
public class _25_797 {
    /**
     * 每日一题：2021/8/25
     * 797. 所有可能的路径
     * 难度：medium
     * <p>
     * 给你一个有 n 个节点的 有向无环图（DAG），请你找出所有从节点 0 到节点 n-1 的路径并输出（不要求按特定顺序）
     * <p>
     * 二维数组的第 i 个数组中的单元都表示有向图中 i 号节点所能到达的下一些节点，空就是没有下一个结点了。
     * <p>
     * 译者注：有向图是有方向的，即规定了 a→b 你就不能从 b→a 。
     *
     * <p>
     * 示例 1：
     * 输入：graph = [[1,2],[3],[3],[]]
     * 输出：[[0,1,3],[0,2,3]]
     * 解释：有两条路径 0 -> 1 -> 3 和 0 -> 2 -> 3
     * <p>
     * 示例 2：
     * 输入：graph = [[4,3,1],[3,2,4],[3],[4],[]]
     * 输出：[[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
     *
     * <p>
     * 注意:
     * n == graph.length
     * 2 <= n <= 15
     * 0 <= graph[i][j] < n
     * graph[i][j] != i（即，不存在自环）
     * graph[i] 中的所有元素 互不相同
     * 保证输入为 有向无环图（DAG）
     */

    /*
    思路：DFS
     */

    List<List<Integer>> res = new ArrayList<>();
    Deque<Integer> stack = new ArrayDeque<>();

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        stack.offerLast(0);
        dfs(graph, 0, graph.length - 1);
        return res;
    }

    public void dfs(int[][] graph, int x, int n) {
        if (x == n) {
            res.add(new ArrayList<>(stack));
            return;
        }
        for (int y : graph[x]) {
            stack.offerLast(y);
            dfs(graph, y, n);
            stack.pollLast();
        }
    }
}
