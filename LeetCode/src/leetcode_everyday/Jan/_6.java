package leetcode_everyday.Jan;

import java.util.*;

public class _6 {
    /**
     * 每日一题：2021/1/5
     * 给你一个变量对数组 equations 和一个实数值数组 values 作为已知条件，
     * 其中 equations[i] = [Ai, Bi] 和 values[i] 共同表示等式 Ai / Bi = values[i] 。
     * 每个 Ai 或 Bi 是一个表示单个变量的字符串。
     * 另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，请你根据已知条件找出 Cj / Dj = ? 的结果作为答案。
     * 返回 所有问题的答案 。如果存在某个无法确定的答案，则用 -1.0 替代这个答案。
     * 注意：输入总是有效的。你可以假设除法运算中不会出现除数为 0 的情况，且不存在任何矛盾的结果。
     */

    /**
     * 思路：使用基于图的算法，将原问题转化为图上的问题来考虑
     * 原问题转化为：给定图中的一些点（变量），以及某些边的权值（两个变量的比值），试对任意两点（两个变量）求出其路径长（两个变量的比值）。
     *              试对任意两点（两个变量）求出其路径长（两个变量的比值）
     * 然后，首先需要遍历equations数组，找出其中所有不同的字符串，并通过哈希表将每个不同的字符串映射成整数
     * 构建完图之后，对于任何一个查询，就可以从起点出发，通过广度优先搜索的方式，不断更新起点与当前点之间的路径长度，直到搜索到终点为止。
     *
     */

    public static double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int nvars =0;   // 将给定的equations中的变量映射成整数，因此需要计数
        Map<String, Integer> variables = new HashMap<String, Integer>();

        int n = equations.size();
        for (int i = 0; i < n; i++) {
            if (!variables.containsKey(equations.get(i).get(0))) {
                variables.put(equations.get(i).get(0),nvars++);
            }
            if (!equations.contains(equations.get(i).get(1))) {
                variables.put(equations.get(i).get(1),nvars++);
            }
        }
        // 对于每个点，存储其直接连接到的所有点以及对应的权值
        List<Pair>[] edges = new List[nvars];
        for (int i = 0; i < nvars; i++) {
            edges[i] = new ArrayList<Pair>();
        }
        for (int i = 0; i < n; i++) {
            int va = variables.get(equations.get(i).get(0)), vb = variables.get(equations.get(i).get(1));
            edges[va].add(new Pair(vb, values[i]));
            edges[vb].add(new Pair(va, 1.0 / values[i]));
        }
        // 以上只是根据条件建立图的准备工作，下面开始解决每一个查询要求
        // 开始遍历每一个查询，从起点出发，使用广度优先搜索的方式不断更新起点与当前点之间的路径长度
        int queriesCount = queries.size();
        double[] res = new double[queriesCount];
        for (int i = 0; i < queriesCount; i++) {
            List<String> query = queries.get(i);
            double result = -1.0;
            if (variables.containsKey(query.get(0)) && variables.containsKey(query.get(1))) {
                int ia = variables.get(query.get(0)), ib = variables.get(query.get(1));// 找到要计算的两个点对应的变量编号
                if (ia == ib) {
                    result = 1.0;
                } else {
                    // 使用队列进行广度优先遍历
                    Queue<Integer> points = new LinkedList<Integer>();
                    points.offer(ia);
                    double[] ratios = new double[nvars];
                    Arrays.fill(ratios, -1.0);  // 初始化结果数组
                    ratios[ia] = 1.0;
                    while (!points.isEmpty() && ratios[ib] < 0) {
                        int x = points.poll(); // 出队
                        for (Pair pair : edges[x]) {
                            int y = pair.index;
                            double val = pair.value;
                            if (ratios[y] < 0) {
                                ratios[y] = ratios[x] * val;
                                points.offer(y);
                            }
                        }
                    }
                    result = ratios[ib];
                }
            }
            res[i] = result;
        }
        return res;

    }

    /**
     * 思路2：Floyd算法
     * 对于查询数量很多的情形，如果为每次查询都独立搜索一次，则效率会变低。
     * 为此，我们不妨对图先做一定的预处理，随后就可以在较短的时间内回答每个查询。
     * 因此可以使用Floyd算法预先计算出任意两点之间的距离
     */
    public static double[] calcEquation2(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int nvars =0;   // 将给定的equations中的变量映射成整数，因此需要计数
        Map<String, Integer> variables = new HashMap<String, Integer>();

        int n = equations.size();
        for (int i = 0; i < n; i++) {
            if (!variables.containsKey(equations.get(i).get(0))) {
                variables.put(equations.get(i).get(0),nvars++);
            }
            if (!equations.contains(equations.get(i).get(1))) {
                variables.put(equations.get(i).get(1),nvars++);
            }
        }
        // 构建图
        double[][] graph = new double[nvars][nvars];
        for (int i = 0; i < nvars; i++) {
            Arrays.fill(graph[i], -1.0);    //初始化整个图
        }
        // 已知的除法结果先构建到图上
        for (int i = 0; i < n; i++) {
            int va = variables.get(equations.get(i).get(0)), vb = variables.get(equations.get(i).get(1));
            graph[va][vb] = values[i];
            graph[vb][va] = 1.0 / values[i];
        }

        // 由已知的点 再预先计算出其他任意两点之间的距离
        for (int k = 0; k < nvars; k++) {
            for (int i = 0; i < nvars; i++) {
                for (int j = 0; j < nvars; j++) {
                    if (graph[i][k] > 0 && graph[k][j] > 0) {
                        graph[i][j] = graph[i][k] * graph[k][j];
                    }
                }
            }
        }

        // 根据查询需求 直接从graph图中搜索得到答案
        int queriesCount = queries.size();
        double[] res = new double[queriesCount];
        for (int i = 0; i < queriesCount; i++) {
            List<String> query = queries.get(i);
            double result = -1.0;
            if (variables.containsKey(query.get(0)) && variables.containsKey(query.get(1))) {
                int ia = variables.get(query.get(0)), ib = variables.get(query.get(1));
                if (graph[ia][ib] > 0) {
                    result = graph[ia][ib];
                }
            }
            res[i] = result;
        }

        return res;

    }


        public static void _21_1_6(){

    }

    static class Pair{
        int index;
        double value;

        public Pair(int index, double value) {
            this.index = index;
            this.value = value;
        }
    }


}
