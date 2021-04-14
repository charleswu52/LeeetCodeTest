package chp4;

/**
 * @author WuChao
 * @since 2021/4/11 上午8:52
 */
public class _1 {
    /**
     * 程序员面试金典(version 6) - 面试题 04.01. 节点间通路
     * 难度: medium
     * <p>
     * 节点间通路。给定有向图，设计一个算法，找出两个节点之间是否存在一条路径。
     * <p>
     * 例如：
     * 输入：n = 3, graph = [[0, 1], [0, 2], [1, 2], [1, 2]], start = 0, target = 2
     * 输出：true
     * <p>
     * 数据范围：
     * 节点数量n在[0, 1e5]范围内。
     * 节点编号大于等于 0 小于 n。
     * 图中可能存在自环和平行边。
     */

    /*
    题目解析：使用DFS 深度搜索，同时考虑图中可能存在自环的情况

    使用DFS深度优先搜索，进行递归搜索，逐渐压缩搜索区间，直至最终找到start与target在同同一路径内，说查找成功

    注意：采用DFS解决的过程中，应当注意当正序递归搜索时，会出现超时的情况，所以采用逆序搜索的方法
     */
    boolean[] visited = null;

    public boolean findWhetherExistsPath(int n, int[][] graph, int start, int target) {
        visited = new boolean[graph.length];
        // DFS
        return dfs(graph, start, target);


    }

    public boolean dfs(int[][] graph, int start, int target) {
        // 深度优先搜索
        for (int i = 0; i < graph.length; i++) {
            // 确保当前路径未被访问
            if (!visited[i]) {
                // 若当前路径起点与终点相符，则直接返回结果
                if (graph[i][0] == start && graph[i][1] == target) {
                    return true;
                }
                // 设置访问标志
                visited[i] = true;
                // DFS关键代码，思路：同时逐渐压缩搜索区间
                if(graph[i][1] == target && dfs(graph, start, graph[i][0])) {
                    return true;
                }
                //清除访问标志
                visited[i] = false;
            }
        }
        return false;
    }
}
