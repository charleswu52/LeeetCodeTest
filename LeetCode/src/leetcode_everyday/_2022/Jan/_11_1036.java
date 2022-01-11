package leetcode_everyday._2022.Jan;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * @author WuChao
 * @create 2022/1/11 21:43
 */
public class _11_1036 {
    /**
     * 每日一题：2022/1/11
     * <p>
     * 1036. 逃离大迷宫
     * <p>
     * 难度：hard
     * <p>
     * 在一个 10^6 x 10^6 的网格中，每个网格上方格的坐标为 (x, y) 。
     * <p>
     * 现在从源方格 source = [s_x, s_y] 开始出发，意图赶往目标方格 target = [t_x, t_y] 。数组 blocked 是封锁的方格列表，
     * 其中每个 blocked[i] = [x_i, y_i] 表示坐标为 (x_i, y_i) 的方格是禁止通行的。
     * <p>
     * 每次移动，都可以走到网格中在四个方向上相邻的方格，只要该方格 不 在给出的封锁列表 blocked 上。同时，不允许走出网格。
     * <p>
     * 只有在可以通过一系列的移动从源方格 source 到达目标方格 target 时才返回 true。否则，返回 false。
     *
     * <p>
     * 示例 1：
     * <p>
     * 输入：blocked = [[0,1],[1,0]], source = [0,0], target = [0,2]
     * <p>
     * 输出：false
     * <p>
     * 解释：
     * <p>
     * 从源方格无法到达目标方格，因为我们无法在网格中移动。
     * 无法向北或者向东移动是因为方格禁止通行。
     * 无法向南或者向西移动是因为不能走出网格。
     * <p>
     * 范围
     * <p>
     * 0 <= blocked.length <= 200
     * blocked[i].length == 2
     * 0 <= xi, yi < 106
     * source.length == target.length == 2
     * 0 <= sx, sy, tx, ty < 106
     * source != target
     * 题目数据保证 source 和 target 不在封锁列表内
     **/

    /*
    思路：
     */
    int EDGE = (int) 1e6, MAX = (int) 1e5;
    long BASE = 131L;
    Set<Long> set = new HashSet<>();
    int[][] dir = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public boolean isEscapePossible(int[][] blocked, int[] s, int[] t) {
        for (int[] p : blocked) set.add(p[0] * BASE + p[1]);
        int n = blocked.length;
        MAX = n * (n - 1) / 2; // 可直接使用 1e5
        return check(s, t) && check(t, s);
    }

    boolean check(int[] a, int[] b) {
        Set<Long> vis = new HashSet<>();
        Deque<int[]> d = new ArrayDeque<>();
        d.addLast(a);
        vis.add(a[0] * BASE + a[1]);
        while (!d.isEmpty() && vis.size() <= MAX) {
            int[] poll = d.pollFirst();
            int x = poll[0], y = poll[1];
            if (x == b[0] && y == b[1]) return true;
            for (int[] di : dir) {
                int nx = x + di[0], ny = y + di[1];
                if (nx < 0 || nx >= EDGE || ny < 0 || ny >= EDGE) continue;
                long hash = nx * BASE + ny;
                if (set.contains(hash)) continue;
                if (vis.contains(hash)) continue;
                d.addLast(new int[]{nx, ny});
                vis.add(hash);
            }
        }
        return vis.size() > MAX;
    }


}
