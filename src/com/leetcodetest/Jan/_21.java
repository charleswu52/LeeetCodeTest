package com.leetcodetest.Jan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class _21 {
    /**
     * 每日一题：2021/1/21
     * 1489. 找到最小生成树里的关键边和伪关键边
     * 难度： hard
     * 给你一个 n 个点的带权无向连通图，节点编号为 0 到 n-1 ，同时还有一个数组 edges，其中 edges[i] = [from_i, to_i, weight_i] 表示在
     * from_i 和 to_i 节点之间有一条带权无向边。最小生成树 (MST) 是给定图中边的一个子集，它连接了所有节点且没有环，而且这些边的权值和最小。
     *
     * 请你找到给定图中最小生成树的所有关键边和伪关键边。如果从图中删去某条边，会导致最小生成树的权值和增加，那么我们就说它是一条关键边。
     * 伪关键边则是可能会出现在某些最小生成树中但不会出现在所有最小生成树中的边。
     *
     * 请注意，你可以分别以任意顺序返回关键边的下标和伪关键边的下标。
     *
     * 示例：
     * 输入：n = 5, edges = [[0,1,1],[1,2,1],[2,3,2],[0,3,2],[0,4,3],[3,4,3],[1,4,6]]
     * 输出：[[0,1],[2,3,4,5]]
     *
     * 数据范围：
     * 2 <= n <= 100
     * 1 <= edges.length <= min(200, n * (n - 1) / 2)
     * edges[i].length == 3
     * 0 <= from_i < to_i < n
     * 1 <= weight_i <= 1000
     * 所有 (from_i, to_i) 数对都是互不相同的。
     */

    /*
    题目解析： 最小生成树
    复习最小生成树的原理和算法（Prim 和 Kruskal）
    有两种解题方法：
    1. 方法一只需要枚举每一条边，并用略微修改的 Kruskal 算法判断其是否是关键边或伪关键边；
    2. 方法二利用了 Kruskal 算法的连通性性质，以及无向图找桥边的 Tarjan 算法，即使在竞赛中也不算容易，仅供读者挑战自我。

    参考链接：https://leetcode-cn.com/problems/find-critical-and-pseudo-critical-edges-in-minimum-spanning-tree/solution/zhao-dao-zui-xiao-sheng-cheng-shu-li-de-gu57q/
     */

    /**
     * 解题思路1：枚举 + 最小生成树判定
     *
     * 首先需要理解题目描述中对于「关键边」和「伪关键边」的定义：
     * 关键边：如果最小生成树中删去某条边，会导致最小生成树的权值和增加，那么我们就说它是一条关键边。
     * 也就是说，如果设原图最小生成树的权值为 value，那么去掉这条边后：
     *      要么整个图不连通，不存在最小生成树；
     *      要么整个图联通，对应的最小生成树的权值为 v，其严格大于 value。
     *
     * 伪关键边：可能会出现在某些最小生成树中但不会出现在所有最小生成树中的边。也就是说，我们可以在计算最小生成树的过程中，最先考虑这条边，
     * 即最先将这条边的两个端点在并查集中合并。设最终得到的最小生成树权值为 v，如果 v = value，那么这条边就是伪关键边。
     *
     * 需要注意的是，关键边也满足伪关键边对应的性质。因此，我们首先对原图执行 Kruskal 算法，得到最小生成树的权值 value，
     * 随后我们枚举每一条边，首先根据上面的方法判断其是否是关键边，如果不是关键边，再判断其是否是伪关键边。
     */
    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        int m = edges.length;
        int[][] newEdges = new int[m][4];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < 3; j++) {
                newEdges[i][j] = edges[i][j];
            }
            newEdges[i][3] = i;
        }
        Arrays.sort(newEdges, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });

        // 计算Value
        // 计算 value
        UnionFind ufStd = new UnionFind(n);
        int value = 0;
        for (int i = 0; i < m; ++i) {
            if (ufStd.unite(newEdges[i][0], newEdges[i][1])) {
                value += newEdges[i][2];
            }
        }

        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        for (int i = 0; i < 2; ++i) {
            ans.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < m; ++i) {
            // 判断是否是关键边
            UnionFind uf = new UnionFind(n);
            int v = 0;
            for (int j = 0; j < m; ++j) {
                if (i != j && uf.unite(newEdges[j][0], newEdges[j][1])) {
                    v += newEdges[j][2];
                }
            }
            if (uf.setCount != 1 || (uf.setCount == 1 && v > value)) {
                ans.get(0).add(newEdges[i][3]);
                continue;
            }

            // 判断是否是伪关键边
            uf = new UnionFind(n);
            uf.unite(newEdges[i][0], newEdges[i][1]);
            v = newEdges[i][2];
            for (int j = 0; j < m; ++j) {
                if (i != j && uf.unite(newEdges[j][0], newEdges[j][1])) {
                    v += newEdges[j][2];
                }
            }
            if (v == value) {
                ans.get(1).add(newEdges[i][3]);
            }
        }

        return ans;
    }


    // 并查集模板
    public class UnionFind {
        int[] parent;
        int[] size;
        int n;
        // 当前连通分量数目
        int setCount;

        public UnionFind(int n) {
            this.n = n;
            this.setCount = n;
            this.parent = new int[n];
            this.size = new int[n];
            Arrays.fill(size, 1);
            for (int i = 0; i < n; ++i) {
                parent[i] = i;
            }
        }

        public int findset(int x) {
            return parent[x] == x ? x : (parent[x] = findset(parent[x]));
        }

        public boolean unite(int x, int y) {
            x = findset(x);
            y = findset(y);
            if (x == y) {
                return false;
            }
            if (size[x] < size[y]) {
                int temp = x;
                x = y;
                y = temp;
            }
            parent[y] = x;
            size[x] += size[y];
            --setCount;
            return true;
        }

        public boolean connected(int x, int y) {
            x = findset(x);
            y = findset(y);
            return x == y;
        }
    }
    public void _21_1_21() {
        int n=5;
        int[][] edges = {{0, 1, 1}, {1, 2, 1}, {2, 3, 2}, {0, 3, 2}, {0, 4, 3}, {3, 4, 3}, {1, 4, 6}};
        System.out.println(findCriticalAndPseudoCriticalEdges(n, edges));
    }
}
