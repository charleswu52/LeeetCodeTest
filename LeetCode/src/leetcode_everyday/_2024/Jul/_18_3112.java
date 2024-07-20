package leetcode_everyday._2024.Jul;

import java.util.*;

/**
 * @author charles
 * @create 2024/7/20
 */
public class _18_3112 {
    /**
     * 每日一题：2024/7/18
     * 3112. 访问消失节点的最少时间
     * https://leetcode.cn/problems/minimum-time-to-visit-disappearing-nodes/description/?envType=daily-question&envId=2024-07-18
     */
    public int[] minimumTime(int n, int[][] edges, int[] disappear) {
        List<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], length = edge[2];
            graph[u].add(new int[]{v, length});
            graph[v].add(new int[]{u, length});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.add(new int[]{0, 0});

        int[] answer = new int[n];
        Arrays.fill(answer, -1);
        boolean[] visited = new boolean[n];
        int[] minTime = new int[n];
        Arrays.fill(minTime, Integer.MAX_VALUE);
        minTime[0] = 0;

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int time = current[0];
            int node = current[1];

            if (visited[node] || time >= disappear[node]) continue;

            visited[node] = true;
            answer[node] = time;

            for (int[] neighbor : graph[node]) {
                int nextNode = neighbor[0];
                int nextTime = time + neighbor[1];

                if (!visited[nextNode] && nextTime < disappear[nextNode] && nextTime < minTime[nextNode]) {
                    minTime[nextNode] = nextTime;
                    pq.add(new int[]{nextTime, nextNode});
                }
            }
        }

        return answer;

    }

    public static void main(String[] args) {

    }
}
