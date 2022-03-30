package leetcode_everyday._2022.Mar;

import java.util.*;

/**
 * @author WuChao
 * @create 2022/3/30 8:54
 */
public class _30_1606 {
    /**
     * 每日一题：2022/3/30
     * <p>
     * 1606. 找到处理最多请求的服务器
     * <p>
     * 难度：hard
     * <p>
     * 你有 k 个服务器，编号为 0 到 k-1 ，它们可以同时处理多个请求组。每个服务器有无穷的计算能力但是 不能同时处理超过一个请求 。
     * 请求分配到服务器的规则如下：
     * <p>
     * 第 i （序号从 0 开始）个请求到达。
     * 如果所有服务器都已被占据，那么该请求被舍弃（完全不处理）。
     * 如果第 (i % k) 个服务器空闲，那么对应服务器会处理该请求。
     * 否则，将请求安排给下一个空闲的服务器（服务器构成一个环，必要的话可能从第 0 个服务器开始继续找下一个空闲的服务器）。
     * 比方说，如果第 i 个服务器在忙，那么会查看第 (i+1) 个服务器，第 (i+2) 个服务器等等。
     * <p>
     * 给你一个 严格递增 的正整数数组 arrival ，表示第 i 个任务的到达时间，和另一个数组 load ，
     * 其中 load[i] 表示第 i 个请求的工作量（也就是服务器完成它所需要的时间）。你的任务是找到 最繁忙的服务器 。
     * <p>
     * 最繁忙定义为一个服务器处理的请求数是所有服务器里最多的。
     * <p>
     * 请你返回包含所有 最繁忙服务器 序号的列表，你可以以任意顺序返回这个列表。
     * <p>
     * 示例1
     * <p>
     * 输入：k = 3, arrival = [1,2,3,4], load = [1,2,1,2]
     * <p>
     * 输出：[0]
     * <p>
     * 解释：
     * 前 3 个请求分别被前 3 个服务器处理。
     * 请求 3 进来，由于服务器 0 空闲，它被服务器 0 处理。
     * 服务器 0 处理了两个请求，服务器 1 和 2 分别处理了一个请求。所以服务器 0 是最忙的服务器。
     * <p>
     * 范围
     * <p>
     * 1 <= k <= 10^5
     * 1 <= arrival.length, load.length <= 10^5
     * arrival.length == load.length
     * 1 <= arrival[i], load[i] <= 10^9
     * arrival 保证 严格递增 。
     */

    /*
    思路：数据结构应用题
     */

    int[] cnts = new int[100005];
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        List<Integer> res = new ArrayList<>();
        int n = arrival.length, max = 0;
        Map<Integer, Integer> map = new HashMap<>();
        PriorityQueue<int[]> busy = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        TreeSet<Integer> free = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            free.add(i);
        }
        for (int i = 0; i < n; i++) {
            int start = arrival[i], end = arrival[i] + load[i];
            while (!busy.isEmpty() && busy.peek()[1] <= start) {
                free.add(busy.poll()[0]);
            }
            Integer use = free.ceiling(i % k);
            if (use == null) {
                use = free.ceiling(0);
            }
            if (use == null) {
                continue;
            }
            free.remove(use);
            busy.add(new int[]{use, end});
            max = Math.max(max, ++cnts[use]);
        }
        for (int i = 0; i < k; i++) {
            if (cnts[i] == max) {
                res.add(i);
            }
        }
        return res;
    }
}
