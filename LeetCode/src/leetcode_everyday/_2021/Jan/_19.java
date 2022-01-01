package leetcode_everyday._2021.Jan;

import java.util.*;

public class _19 {
    /**
     * 每日一题：2021/1/19
     * 1584. 连接所有点的最小费用
     * 难度： medium
     * 给你一个 points 数组，表示 2D 平面上的一些点，其中 points[i] = [xi, yi]。
     * 连接点 [xi, yi] 和点 [xj, yj] 的费用为它们之间的 曼哈顿距离 ：|xi - xj| + |yi - yj| ，其中 |val| 表示 val 的绝对值。
     * 请你返回将所有点连接的最小总费用。只有任意两点之间 有且仅有 一条简单路径时，才认为所有点都已连接。
     * 示例：
     * 输入：points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
     * 输出：20
     */

    /*
    题目分析：
    将给定的坐标视为一张由n个节点组成的完全图，任意两点之间的距离均为它们的曼哈顿距离。问题就是在这个图中取得一个子图，
    恰好满足子图的任意两点之间 有且仅有 一条简单路径，且这个子图的所有边的总权值之和尽可能小。
    能够满足任意两点之间有且仅有一条简单路径只有树，且这棵树包含 n 个节点。称这棵树为给定的图的生成树，其中总权值最小的生成树，
    我们称其为最小生成树。 这样分析完就是要求最小生成树的问题。
     */

    /*
    思路：Kruskal算法 （最小生成树+并查集）
    Kruskal 算法是一种常见并且好写的最小生成树算法，由 \text{Kruskal}Kruskal 发明。该算法的基本思想是从小到大加入边，是一个贪心算法。
    其算法流程为：
    1. 将图 G={V,E} 中的所有边按照长度由小到大进行排序，等长的边可以按任意顺序。
    2. 初始化图 G'为 {V,∅}，从前向后扫描排序后的边，如果扫描到的边 e 在 G' 中连接了两个相异的连通块,则将它插入 G' 中。
    3. 最后得到的图 G' 就是图Ｇ的最小生成树。

    实现细节：
    在实际代码中，我们首先将这张完全图中的边全部提取到边集数组中，然后对所有边进行排序，从小到大进行枚举，每次贪心选边加入答案。
    使用并查集维护连通性，若当前边两端不连通即可选择这条边。
     */
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        DisjointSetUnion disjointSetUnion = new DisjointSetUnion(n);
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                edges.add(new Edge(dist(points, i, j), i, j));
            }
        }
        Collections.sort(edges, new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.len - o2.len;
            }
        });
        int res = 0, num = 1;
        for (Edge edge : edges) {
            int len = edge.len, x = edge.x, y = edge.y;
            if (disjointSetUnion.unionSet(x, y)) {
                res += len;
                num++;
                if (num == n) {
                    break;
                }
            }
        }
        return res;

    }

    public int dist(int[][] points, int x, int y) {
        return Math.abs(points[x][0] - points[y][0]) + Math.abs(points[x][1] - points[y][1]);
    }


    // 边集数组
    public class DisjointSetUnion {
        int[] f;
        int[] rank;
        int n;

        public DisjointSetUnion(int n) {
            this.n = n;
            this.rank = new int[n];
            Arrays.fill(this.rank, 1);
            this.f = new int[n];
            for (int i = 0; i < n; i++) {
                this.f[i] = i;
            }
        }

        public int find(int x) {
            return f[x] == x ? x : (f[x] = find(f[x]));
        }

        public boolean unionSet(int x, int y) {
            int fx = find(x), fy = find(y);
            if (fx == fy) {
                return false;
            }
            if (rank[fx] < rank[fy]) {
                int temp = fx;
                fx = fy;
                fy = temp;
            }
            rank[fx] += rank[fy];
            f[fy] = fx;
            return true;
        }
    }

    public class Edge {
        int len, x, y;

        public Edge(int len, int x, int y) {
            this.len = len;
            this.x = x;
            this.y = y;
        }
    }


    public void _21_1_19() {
        int[][] points = {{0, 0}, {2, 2}, {3, 10}, {5, 2}, {7, 0}};
        System.out.println(minCostConnectPoints(points));
    }
}
