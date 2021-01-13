package com.leetcodetest.Jan;

public class _13 {
    /**
     * 每日一题：2021/1/13
     * <p>
     * 难度： medium
     * 在本问题中, 树指的是一个连通且无环的无向图。
     * 输入一个图，该图由一个有着N个节点 (节点值不重复1, 2, ..., N) 的树及一条附加的边构成。
     * 附加的边的两个顶点包含在1到N中间，这条附加的边不属于树中已存在的边。
     * 结果图是一个以边组成的二维数组。每一个边的元素是一对[u, v]，满足 u < v，表示连接顶点u和v的无向图的边。
     * 返回一条可以删去的边，使得结果图是一个有着N个节点的树。如果有多个答案，则返回二维数组中 最后出现 的边。
     * 答案边 [u, v] 应满足相同的格式 u < v。
     */
    /*
    题目解析：
    给定的是图中的边表，要求删除数组中 最后出现 的冗余边，使得图是一个连通的有向无环图
     */

    /**
     * 思路：并查集
     * 图论的一周··· 复习一下刚接触的并查集
     * 在一棵树中，边的数量比节点的数量少 1。如果一棵树有 N 个节点，则这棵树有 N−1 条边。
     * 这道题中的图在树的基础上多了一条附加的边，因此边的数量也是 N。
     * 树是一个连通且无环的无向图，在树中添加一条冗余边后就出现环，附加的边就是导致环出现的边。
     *
     * 可以通过并查集来寻找附加的边，初始时，每个节点都属于不同的连通分量。然后遍历每一条边，判断这条边连的两个顶点是否属于相同的连通分量。
     *  1.如果两个顶点属于不同的连通分量，说明在遍历到当前点边之前，这两个顶点之间不连通，
     *    因此当前点边不会导致环出现，于是合并这两个点的连通分量。
     *  2.如果两个顶点属于相同的连通分量，则说明在遍历到当前点边之前，这两个顶点之间已经连通，因此当前的边会导致环的出现，属于附加的边，
     *    因此需要返回当前点边作为答案。
     *
     */

    /**
     * @param edges
     * @return
     */
    public static int[] findRedundantConnection(int[][] edges) {
        int nodesCount = edges.length;
        int[] parent = new int[nodesCount + 1];
        //初始化每个节点所属的连通分量
        for (int i = 1; i <= nodesCount; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < nodesCount; i++) {
            int[] edge = edges[i];
            int node1 = edge[0], node2 = edge[1];
            if (find(parent, node1) != find(parent, node2)) {
                union(parent,node1, node2);
            }else {
                return edge;
            }
        }
        return new int[0];
    }

    // 并查集的方法实现

    /**
     * 合并操作
     * @param parent
     * @param index1
     * @param index2
     */
    public static void union(int[] parent, int index1, int index2) {
        parent[find(parent, index1)] = find(parent, index2);
    }

    /**
     * 查找当前点的父节点
     * @param parent
     * @param index
     * @return
     */
    public static int find(int[] parent, int index) {
        if (parent[index] != index) {
            parent[index] = find(parent, parent[index]);
        }
        return parent[index];
    }

    public static void _21_1_13() {
        int[][] edges = {{1, 2}, {1, 3}, {2, 3}};
        int[] res = findRedundantConnection(edges);
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i]+" ");
        }
    }

}
