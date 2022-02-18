package leetcode_everyday._2022.Feb;

/**
 * @author WuChao
 * @create 2022/2/18 8:53
 */
public class _18_1791 {
    /**
     * 每日一题：2022/2/18
     * <p>
     * 1791. 找出星型图的中心节点
     * <p>
     * 难度：easy
     * <p>
     * 有一个无向的 星型 图，由 n 个编号从 1 到 n 的节点组成。星型图有一个 中心 节点，并且恰有 n - 1 条边将中心节点与其他每个节点连接起来。
     *
     * 给你一个二维整数数组 edges ，其中 edges[i] = [ui, vi] 表示在节点 ui 和 vi 之间存在一条边。请你找出并返回 edges 所表示星型图的中心节点。
     *
     *
     * <p>
     * 示例
     * <p>
     * 输入：edges = [[1,2],[2,3],[4,2]]
     *
     * 输出：2
     *
     * 解释：如上图所示，节点 2 与其他每个节点都相连，所以节点 2 是中心节点。
     * <p>
     * 范围
     * <p>
     * 3 <= n <= 10^5
     * edges.length == n - 1
     * edges[i].length == 2
     * 1 <= ui, vi <= n
     * ui != vi
     * 题目数据给出的 edges 表示一个有效的星型图
     */

    public int findCenter(int[][] edges) {
        int a = edges[0][0], b = edges[0][1];
        int c = edges[1][0], d = edges[1][1];
        if (c == a || c == b) {
            return c;
        }
        if (d == a || d == b) {
            return d;
        }
        return 0;

    }
}
