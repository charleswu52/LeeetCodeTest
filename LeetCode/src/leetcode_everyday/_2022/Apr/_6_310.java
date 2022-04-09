package leetcode_everyday._2022.Apr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author WuChao
 * @create 2022/4/9 10:47
 */
public class _6_310 {
    /**
     * 每日一题：2022/4/6
     * <p>
     * 310. 最小高度树
     * <p>
     * 难度：medium
     * <p>
     * 树是一个无向图，其中任何两个顶点只通过一条路径连接。 换句话说，一个任何没有简单环路的连通图都是一棵树。
     * <p>
     * 给你一棵包含 n 个节点的树，标记为 0 到 n - 1 。给定数字 n 和一个有 n - 1 条无向边的 edges 列表（每一个边都是一对标签），
     * 其中 edges[i] = [ai, bi] 表示树中节点 ai 和 bi 之间存在一条无向边。
     * <p>
     * 可选择树中任何一个节点作为根。当选择节点 x 作为根节点时，设结果树的高度为 h 。在所有可能的树中，具有最小高度的树（即，min(h)）被称为 最小高度树 。
     * <p>
     * 请你找到所有的 最小高度树 并按 任意顺序 返回它们的根节点标签列表。
     * <p>
     * 树的 高度 是指根节点和叶子节点之间最长向下路径上边的数量。
     *
     * <p>
     * 示例
     * <p>
     * 输入: s = "abcde", goal = "cdeab"
     * <p>
     * 输出: true
     * <p>
     * 范围
     * <p>
     * 1 <= n <= 2 * 10^4
     * edges.length == n - 1
     * 0 <= ai, bi < n
     * ai != bi
     * 所有 (ai, bi) 互不相同
     * 给定的输入 保证 是一棵树，并且 不会有重复的边
     */

    /*
    思路：树形DP应用题
    参考：https://leetcode-cn.com/problems/minimum-height-trees/solution/by-ac_oier-7xio/
     */
    int N = 20010, M = N * 2, idx = 0;
    int[] he = new int[N], e = new int[M], ne = new int[M];
    int[] f1 = new int[N], f2 = new int[N], g = new int[N], p = new int[N];

    void add(int a, int b) {
        e[idx] = b;
        ne[idx] = he[a];
        he[a] = idx++;
    }

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        Arrays.fill(he, -1);
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            add(a, b);
            add(b, a);
        }
        dfs1(0, -1);
        dfs2(0, -1);
        List<Integer> ans = new ArrayList<>();
        int min = n;
        for (int i = 0; i < n; i++) {
            int cur = Math.max(f1[i], g[i]);
            if (cur < min) {
                min = cur;
                ans.clear();
                ans.add(i);
            } else if (cur == min) {
                ans.add(i);
            }
        }
        return ans;
    }

    int dfs1(int u, int fa) {
        for (int i = he[u]; i != -1; i = ne[i]) {
            int j = e[i];
            if (j == fa) continue;
            int sub = dfs1(j, u) + 1;
            if (sub > f1[u]) {
                f2[u] = f1[u];
                f1[u] = sub;
                p[u] = j;
            } else if (sub > f2[u]) {
                f2[u] = sub;
            }
        }
        return f1[u];
    }

    void dfs2(int u, int fa) {
        for (int i = he[u]; i != -1; i = ne[i]) {
            int j = e[i];
            if (j == fa) continue;
            if (p[u] != j) g[j] = Math.max(g[j], f1[u] + 1);
            else g[j] = Math.max(g[j], f2[u] + 1);
            g[j] = Math.max(g[j], g[u] + 1);
            dfs2(j, u);
        }
    }
}

