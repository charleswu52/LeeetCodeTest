package leetcode_everyday._2021.Oct;

/**
 * @author WuChao
 * @create 2021/10/29 11:19
 */
public class _29_335 {
    /**
     * 每日一题：2021/10/29
     * <p>
     * 335. 路径交叉
     * <p>
     * 难度：hard
     * <p>
     * 给你一个整数数组 distance 。
     * <p>
     * 从 X-Y 平面上的点 (0,0) 开始，先向北移动 distance[0] 米，然后向西移动 distance[1] 米，向南移动 distance[2] 米，
     * 向东移动 distance[3] 米，持续移动。也就是说，每次移动后你的方位会发生逆时针变化。
     * <p>
     * 判断你所经过的路径是否相交。如果相交，返回 true ；否则，返回 false 。
     * <p>
     * 示例1：
     * <p>
     * 输入：distance = [1,2,3,4]
     * <p>
     * 输出：false
     * <p>
     * 范围
     * <p>
     * 1 <= distance.length <= 10^5
     * 1 <= distance[i] <= 10^5
     */

    public boolean isSelfCrossing(int[] d) {
        int n = d.length;
        if (n < 4) return false;
        for (int i = 3; i < n; i++) {
            if (d[i] >= d[i - 2] && d[i - 1] <= d[i - 3]) return true;
            if (i >= 4 && d[i - 1] == d[i - 3] && d[i] + d[i - 4] >= d[i - 2]) return true;
            if (i >= 5 && d[i - 1] <= d[i - 3] && d[i - 2] > d[i - 4] && d[i] + d[i - 4] >= d[i - 2] && d[i - 1] + d[i - 5] >= d[i - 3])
                return true;
        }
        return false;
    }
}
