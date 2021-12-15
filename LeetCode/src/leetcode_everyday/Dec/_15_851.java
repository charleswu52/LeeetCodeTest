package leetcode_everyday.Dec;

import java.util.*;

/**
 * @author WuChao
 * @create 2021/12/15 上午10:14
 */
public class _15_851 {
    /**
     * 每日一题：2021/12/15
     * <p>
     * 851. 喧闹和富有
     * <p>
     * 难度：medium
     * <p>
     * 有一组 n 个人作为实验对象，从 0 到 n - 1 编号，其中每个人都有不同数目的钱，以及不同程度的安静值（quietness）。
     * 为了方便起见，我们将编号为 x 的人简称为 "person x "。
     *
     * 给你一个数组 richer ，其中 richer[i] = [ai, bi] 表示 person ai 比 person bi 更有钱。另给你一个整数数组 quiet ，其中
     * quiet[i] 是 person i 的安静值。richer 中所给出的数据 逻辑自恰（也就是说，在 person x 比 person y 更有钱的同时，
     * 不会出现 person y 比 person x 更有钱的情况 ）。
     *
     * 现在，返回一个整数数组 answer 作为答案，其中 answer[x] = y 的前提是，在所有拥有的钱肯定不少于 person x 的人中，person y
     * 是最安静的人（也就是安静值 quiet[y] 最小的人）。
     *
     * <p>
     * 示例 1：
     * <p>
     * 例子：
     * 输入：richer = [[1,0],[2,1],[3,1],[3,7],[4,3],[5,3],[6,3]], quiet = [3,2,5,4,6,1,7,0]
     * 输出：[5,5,2,5,4,5,6,7]
     * 解释：
     * answer[0] = 5，
     * person 5 比 person 3 有更多的钱，person 3 比 person 1 有更多的钱，person 1 比 person 0 有更多的钱。
     * 唯一较为安静（有较低的安静值 quiet[x]）的人是 person 7，
     * 但是目前还不清楚他是否比 person 0 更有钱。
     * answer[7] = 7，
     * 在所有拥有的钱肯定不少于 person 7 的人中（这可能包括 person 3，4，5，6 以及 7），
     * 最安静（有较低安静值 quiet[x]）的人是 person 7。
     * 其他的答案也可以用类似的推理来解释。
     *
     * <p>
     * 范围
     * <p>
     * n == quiet.length
     * 1 <= n <= 500
     * 0 <= quiet[i] < n
     * quiet 的所有值 互不相同
     * 0 <= richer.length <= n * (n - 1) / 2
     * 0 <= ai, bi < n
     * ai != bi
     * richer 中的所有数对 互不相同
     * 对 richer 的观察在逻辑上是一致的
     */


    /*
    思路： 拓扑排序
    使用 richer 数组进行建立拓扑图（邻接矩阵|邻接表），对每一组richer[i]= (a_i,b_i)而言，添加一条从a到b的有向边。
    题目中指明了 图中不存在环，即DAG
    在建图的过程中，统计每个节点的入度，在图中跑一遍拓扑排序来求得答案 ans
    起始时，每个 ans[i] = i,然后将统计的入度为0的节点进行入队，每次出队时，将该节点删掉，对该DAG带来的影响是【节点的邻点的入度减一】，
    若更新入度后数值为0.则将该邻点进行入队操作。
    同时在遍历拓扑排序的过程中，若 t -> u,即t比u有钱，则尝试使用ans[t]来更新ans[u](检查此时两者的安静值，若满足
    quiet[ans[t]]  < quiet[ans[u]],则用ans[t] 更新ans[u])
     */
    public int[] loudAndRich(int[][] richer, int[] quiet) {
        int n = quiet.length;
        // 存储图 邻接矩阵
        Map<Integer, List<Integer>> map = new HashMap<>();
        // 入度表
        int[] in = new int[n];
        for (int[] r : richer) {
            int a = r[0], b = r[1];
            List<Integer> list = map.getOrDefault(a, new ArrayList<>());
            list.add(b);
            map.put(a, list);
            in[b]++;
        }
        Deque<Integer> deque = new ArrayDeque<>();
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = i;
            if (in[i] == 0) {
                deque.addLast(i);
            }
        }
        while (!deque.isEmpty()) {
            int t = deque.pollFirst();
            for (int u : map.getOrDefault(t, new ArrayList<>())) {
                if (quiet[ans[t]] < quiet[ans[u]]) {
                    ans[u] = ans[t];
                }
                if (--in[u] == 0) {
                    deque.addLast(u);
                }
            }
        }
        return ans;



    }
}
