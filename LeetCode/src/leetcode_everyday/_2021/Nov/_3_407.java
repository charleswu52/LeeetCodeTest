package leetcode_everyday._2021.Nov;

import java.util.PriorityQueue;

/**
 * @author WuChao
 * @create 2021/11/3 8:45
 */
public class _3_407 {
    /**
     * 每日一题：2021/11/3
     * <p>
     * 407. 接雨水 II
     * <p>
     * 难度：hard
     * <p>
     * 给你一个 m x n 的矩阵，其中的值均为非负整数，代表二维高度图每个单元的高度，请计算图中形状最多能接多少体积的雨水。
     *
     * <p>
     * 示例1：
     * <p>
     * 输入: heightMap = [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]]
     * <p>
     * 输出: 4
     * <p>
     * 解释: 下雨后，雨水将会被上图蓝色的方块中。总的接雨水量为1+2+1=4。
     * <p>
     * 示例2：
     * <p>
     * 输入: heightMap = [[3,3,3,3,3],[3,2,2,2,3],[3,2,1,2,3],[3,2,2,2,3],[3,3,3,3,3]]
     * <p>
     * 输出: 10
     * <p>
     * 范围
     * <p>
     * m == heightMap.length
     * n == heightMap[i].length
     * 1 <= m, n <= 200
     * 0 <= heightMap[i][j] <= 2 * 10^4
     */

    /*
    思路：最小堆
    什么样的方块一定可以接住水：
        该方块不为最外层的方块；
        该方块自身的高度比其上下左右四个相邻的方块接水后的高度都要低；
     */

    public int trapRainWater(int[][] heightMap) {
        if (heightMap.length <= 2 || heightMap[0].length <= 2) {
            return 0;
        }
        int m = heightMap.length;
        int n = heightMap[0].length;
        boolean[][] visited = new boolean[m][n];
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    pq.offer(new int[]{i * n + j, heightMap[i][j]});
                    visited[i][j] = true;
                }
            }
        }
        int res = 0;
        int[] dirs = {-1, 0, 1, 0, -1};
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            for (int k = 0; k < 4; k++) {
                int nx = curr[0] / n + dirs[k];
                int ny = curr[0] % n + dirs[k + 1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && !visited[nx][ny]) {
                    if (curr[1] > heightMap[nx][ny]) {
                        res += curr[1] - heightMap[nx][ny];
                    }
                    pq.offer(new int[]{nx * n + ny, Math.max(heightMap[nx][ny], curr[1])});
                    visited[nx][ny] = true;
                }
            }
        }
        return res;


    }
}
