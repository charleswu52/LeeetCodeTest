package com.leetcodetest.Jan;

import java.util.LinkedList;
import java.util.Queue;

public class _7 {
    /**
     * 每日一题：2021/1/7
     * 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
     * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
     * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
     * 返回矩阵中 省份 的数量。
     */

    /**
     * 思路：使用基于图的算法，将原问题转化为图上的问题来考虑
     * 深搜和广搜都是可以的
     */

    /**
     * 深搜实现
     * 遍历所有城市，对于每个城市，如果该城市尚未被访问过，通过矩阵 isConnected 得到与该城市直接相连的城市有哪些，
     * 这些城市和该城市属于同一个连通分量，然后对这些城市继续深度优先搜索，直到同一个连通分量的所有城市都被访问到，即可得到一个省份。
     * 遍历完全部城市以后，即可得到连通分量的总数，即省份的总数。
     *
     * @param isConnected
     * @return
     */
    public static int findCircleNum(int[][] isConnected) {
        int n = isConnected.length; // 省份数量
        boolean[] visited = new boolean[n];
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(isConnected, visited, n, i);
                res++;
            }
        }
        return res;
    }

    public static void dfs(int[][] isConnected, boolean visited[], int n, int i) {
        for (int j = 0; j < n; j++) {
            if (isConnected[i][j] == 1 && !visited[j]) {
                visited[j] = true;
                dfs(isConnected, visited, n, j);
            }
        }
    }

    /**
     * 广搜实现
     * 对于每个城市，如果该城市尚未被访问过，则从该城市开始广度优先搜索，直到同一个连通分量中的所有城市都被访问到，即可得到一个省份。
     * @param isConnected
     * @return
     */
    public static int findCircleNum2(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int res = 0;
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                queue.offer(i);
                while (!queue.isEmpty()) {
                    int j = queue.poll();
                    visited[j] = true;
                    for (int k = 0; k < n; k++) {
                        if (isConnected[j][k] == 1 && !visited[k]) {
                            queue.offer(k);
                        }
                    }
                }
                res++;
            }
        }
        return res;
    }

    /**
     * 第3种思路：并查集实现
     * 初始时，每个城市都属于不同的连通分量。遍历矩阵 isConnected，如果两个城市之间有相连关系，则它们属于同一个连通分量，对它们进行合并。
     * 遍历矩阵 isConnected 的全部元素之后，计算连通分量的总数，即为省份的总数。
     *
     * @param isConnected
     * @return
     */
    public static int findCircleNum3(int[][] isConnected) {
        int n = isConnected.length;
        int[] parent = new int[n];  // 每个城市是属于谁的
        for (int i = 0; i < n; i++) {
            parent[i] = i;  //初始化每一个都属于它自己
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    union(parent, i, j);
                }
            }
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (parent[i] == i) {
                res++;
            }
        }
        return res;
    }

    /**
     * 两个节点合并为一个，index1被index2所在的集合合并
     * @param parent
     * @param index1
     * @param index2
     */
    public static void union(int[] parent, int index1, int index2) {
        parent[find(parent, index1)] = find(parent, index2);
    }

    /**
     * 查找每个节点的父节点是谁，即他在哪个集合里面
     * @param parent
     * @param index
     * @return
     */
    public static int find(int[] parent, int index) {
        if (parent[index] != index) {
            parent[index] = find(parent, parent[index]);    // 一层层往上找
        }
        return parent[index];
    }

    public static void _21_1_7() {
        int[][] isConnected = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        System.out.println(findCircleNum3(isConnected));

    }
}
