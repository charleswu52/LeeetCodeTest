package leetcode_everyday._2022.Jul;

/**
 * @author WuChao
 * @create 2022/7/8 14:25
 */
public class _2_871 {
    /**
     * 每日一题：2022/7/2
     * <p>
     * 871. 最低加油次数
     * <p>
     * 难度：hard
     * <p>
     * 汽车从起点出发驶向目的地，该目的地位于出发位置东面 target 英里处。
     *
     * 沿途有加油站，每个 station[i] 代表一个加油站，它位于出发位置东面 station[i][0] 英里处，并且有 station[i][1] 升汽油。
     *
     * 假设汽车油箱的容量是无限的，其中最初有 startFuel 升燃料。它每行驶 1 英里就会用掉 1 升汽油。
     *
     * 当汽车到达加油站时，它可能停下来加油，将所有汽油从加油站转移到汽车中。
     *
     * 为了到达目的地，汽车所必要的最低加油次数是多少？如果无法到达目的地，则返回 -1 。
     *
     * 注意：如果汽车到达加油站时剩余燃料为 0，它仍然可以在那里加油。如果汽车到达目的地时剩余燃料为 0，仍然认为它已经到达目的地。
     * <p>
     * 示例
     * <p>
     * 输入：target = 1, startFuel = 1, stations = []
     *
     * 输出：0
     *
     * 解释：我们可以在不加油的情况下到达目的地。
     * <p>
     * 范围
     * <p>
     * 1 <= chips.length <= 100
     * 1 <= chips[i] <= 10^9
     */

    /*
    思路：动态规划
     */
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        int n = stations.length;
        long[] dp = new long[n + 1];
        dp[0] = startFuel;
        for (int i = 0; i < n; i++) {
            for (int j = i; j >= 0; j--) {
                if (dp[j] >= stations[i][0]) {
                    dp[j + 1] = Math.max(dp[j + 1], dp[j] + stations[i][1]);
                }
            }
        }
        for (int i = 0; i <= n; i++) {
            if (dp[i] >= target) {
                return i;
            }
        }
        return -1;
    }

}
