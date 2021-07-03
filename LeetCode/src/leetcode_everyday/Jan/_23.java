package leetcode_everyday.Jan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _23 {

    /**
     * 每日一题：2021/1/23
     * 1319. 连通网络的操作次数
     * 难度： medium
     * 用以太网线缆将 n 台计算机连接成一个网络，计算机的编号从 0 到 n-1。线缆用 connections 表示，
     * 其中 connections[i] = [a, b] 连接了计算机 a 和 b。
     * 网络中的任何一台计算机都可以通过网络直接或者间接访问同一个网络中其他任意一台计算机。
     * 给你这个计算机网络的初始布线 connections，你可以拔开任意两台直连计算机之间的线缆，并用它连接一对未直连的计算机。
     * 请你计算并返回使所有计算机都连通所需的最少操作次数。如果不可能，则返回 -1 。
     *
     * 示例：
     * 输入：n = 4, connections = [[0,1],[0,2],[1,2]]
     * 输出：1
     * 解释：拔下计算机 1 和 2 之间的线缆，并将它插到计算机 1 和 3 上。
     *
     * 数据范围：
     * 1. 1 <= n <= 10^5
     * 2. 1 <= connections.length <= min(n*(n-1)/2, 10^5)
     * 3. connections[i].length == 2
     * 4. 0 <= connections[i][0], connections[i][1] < n
     * 5. connections[i][0] != connections[i][1]
     * 6. 没有重复的连接。
     * 7. 两台计算机不会通过多条线缆连接。
     */

    /*
    深度优先搜索 / 并查集 两种解题方法
    统计图上连通分量的个数，然后找到多余的边，即在一个连通分量中多余的边，去掉后连通性保持不变；
    用这种边来连接两个不连通的连通分量即可
     */


    /**
     * 方法1：深度优先搜索
     * 我们可以使用深度优先搜索来得到图中的连通分量数。
     * 具体地，初始时所有节点的状态均为「待搜索」。我们每次选择一个「待搜索」的节点，从该节点开始进行深度优先搜索，
     * 并将所有搜索到的节点的状态更改为「已搜索」，这样我们就找到了一个连通分量。
     *
     * @param n
     * @param connections
     * @return
     */

    List<Integer>[] edges;
    boolean[] used;

    public int makeConnected(int n, int[][] connections) {
        int len = connections.length;
        if (len < n - 1) {
            return -1;
        }
        edges = new List[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<Integer>();
        }
        for (int[] conn : connections) {
            edges[conn[0]].add(conn[1]);
            edges[conn[1]].add(conn[0]);
        }
        used = new boolean[n];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (!used[i]) {
                dfs(i);
                ans++;
            }
        }
        return ans - 1;
    }

    public void dfs(int u) {
        used[u] = true;
        for (int v : edges[u]) {
            if (!used[v]) {
                dfs(v);
            }
        }
    }


    /**
     * 并查集的思路
     * 并查集本身就是用来维护连通性的数据结构。
     * 如果其包含 n 个节点，那么初始时连通分量数为 n，每成功进行一次合并操作，连通分量数就会减少 1。
     */

    public int makeConnected2(int n, int[][] connections) {
        if (connections.length < n - 1) {
            return -1;
        }

        UnionFind uf = new UnionFind(n);
        for (int[] conn : connections) {
            uf.unite(conn[0], conn[1]);
        }

        return uf.getSetCount() - 1;
    }

    // 并查集模板
    class UnionFind {
        int[] parent;
        int[] size;
        int n;

        // 当前连通分量的数目
        int setCount;

        public UnionFind(int n) {
            this.n = n;
            this.parent = new int[n];
            this.size = new int[n];
            this.setCount = n;
            Arrays.fill(size, 1);
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int findSet(int x) {
            return parent[x] == x ? x : (parent[x] = findSet(parent[x]));
        }

        public boolean unite(int x, int y) {
            x = findSet(x);
            y = findSet(y);
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
            x = findSet(x);
            y = findSet(y);
            return x == y;
        }

        public int getSetCount() {
            return setCount;
        }
    }


    /**
     * 测试函数
     */
    public void _21_1_23() {
        int n = 4;
        int[][] connections = {{0, 1}, {0, 2}, {1, 2}};
        System.out.println(makeConnected2(n, connections));
    }


}
