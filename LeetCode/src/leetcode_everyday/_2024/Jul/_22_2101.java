package leetcode_everyday._2024.Jul;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author charles
 * @create 2024/7/21
 */
public class _22_2101 {
    /**
     * 每日一题：2024/7/27
     * 2101. 引爆最多的炸弹
     * https://leetcode.cn/problems/detonate-the-maximum-bombs/solutions/1152450/jian-tu-bao-li-mei-ju-suo-you-qi-dian-by-h4mj/?envType=daily-question&envId=2024-07-22
     */

    public int maximumDetonation(int[][] bombs) {
        int n = bombs.length;
        List<Integer>[] g = new ArrayList[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int i = 0; i < bombs.length; i++) {
            long x = bombs[i][0];
            long y = bombs[i][1];
            long r = bombs[i][2];
            for (int j = 0; j < bombs.length; j++) {
                long dx = x - bombs[j][0];
                long dy = y - bombs[j][1];
                long dr = bombs[j][2];
                if (j != i && dx * dx + dy * dy <= r * r) {
                    g[i].add(j);
                }
            }
        }
        int res = 0;
        boolean[] visit = new boolean[n];
        for (int i = 0; i < n && res < n; i++) {
            Arrays.fill(visit, false);
            res = Math.max(res, dfs(g, visit, i));
        }
        return res;

    }

    public int dfs(List<Integer>[] g, boolean[] visit, int x) {
        visit[x] = true;
        int cnt = 1;
        for (Integer j : g[x]) {
            if (!visit[j]) {
                cnt += dfs(g, visit, j);
            }
        }
        return cnt;
    }

}
