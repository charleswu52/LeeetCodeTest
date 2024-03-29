package leetcode_everyday._2021.Jul;

import org.junit.Test;

import java.util.*;

/**
 * @author WuChao
 * @create 2021/7/1 8:36
 */
public class _1 {
    /**
     * 每日一题：2021/7/1
     * LCP 07. 传递信息
     * 难度: easy
     * <p>
     * 小朋友 A 在和 ta 的小伙伴们玩传信息游戏，游戏规则如下：
     * <p>
     * 1. 有 n 名玩家，所有玩家编号分别为 0 ～ n-1，其中小朋友 A 的编号为 0
     * 2. 每个玩家都有固定的若干个可传信息的其他玩家（也可能没有）。传信息的关系是单向的（比如 A 可以向 B 传信息，但 B 不能向 A 传信息）。
     * 3. 每轮信息必须需要传递给另一个人，且信息可重复经过同一个人
     * 给定总玩家数 n，以及按 [玩家编号,对应可传递玩家编号] 关系组成的二维数组 relation。返回信息从小 A (编号 0 ) 经过 k 轮传递到编号为 n-1 的小伙伴处的方案数；若不能到达，返回 0。
     * <p>
     * 示例 1：
     * <p>
     * 输入：n = 5, relation = [[0,2],[2,1],[3,4],[2,3],[1,4],[2,0],[0,4]], k = 3
     * <p>
     * 输出：3
     * <p>
     * 解释：信息从小 A 编号 0 处开始，经 3 轮传递，到达编号 4。共有 3 种方案，分别是 0->2->0->4， 0->2->1->4， 0->2->3->4。
     * <p>
     * 示例 2：
     * <p>
     * 输入：n = 3, relation = [[0,2],[2,1]], k = 2
     * <p>
     * 输出：0
     * <p>
     * 解释：信息不能从小 A 处经过 2 轮传递到编号 2
     * <p>
     * 限制：
     * <p>
     * 2 <= n <= 10
     * 1 <= k <= 5
     * 1 <= relation.length <= 90, 且 relation[i].length == 2
     * 0 <= relation[i][0],relation[i][1] < n 且 relation[i][0] != relation[i][1]
     */


    /*
    思路：BFS
     */
    public int numWays(int n, int[][] relation, int k) {
        Map<Integer, List<Integer>> store = new HashMap<>();
        for (int[] r : relation) {
            if (!store.containsKey(r[0])) {
                store.put(r[0], new ArrayList<Integer>() {{
                    add(r[1]);
                }});
            } else {
                List<Integer> list = store.get(r[0]);
                list.add(r[1]);
                store.put(r[0], list);
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        int step = 0;
        int res = 0;
        while (step < k && !queue.isEmpty()) {
            step++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                List<Integer> list = store.get(cur);
                if (list != null) {
                    for (int next : list) {
                        if (step == k  && next == n - 1) {
                            res++;
                        } else {
                            queue.offer(next);
                        }
                    }
                }
            }

        }
        return res;
    }

    @Test
    public void test() {
        int n = 5;
        int[][] relation = {{0, 2}, {2, 1}, {3, 4}, {2, 3}, {1, 4}, {2, 0}, {0, 4}};
        int k = 3;
        System.out.println(numWays(n, relation, k));
    }
}
