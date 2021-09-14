package leetcode_everyday.Sep;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WuChao
 * @create 2021/9/14 20:22
 */
public class _13_447 {
    /**
     * 每日一题：2021/9/13
     * 447. 回旋镖的数量
     * 难度：medium
     * <p>
     * 给定平面上 n 对 互不相同 的点 points ，其中 points[i] = [xi, yi] 。回旋镖 是由点 (i, j, k) 表示的元组 ，
     * 其中 i 和 j 之间的距离和 i 和 k 之间的距离相等（需要考虑元组的顺序）。
     * <p>
     * 返回平面上所有回旋镖的数量。
     *
     * <p>
     * 示例 1：
     * 输入：points = [[0,0],[1,0],[2,0]]
     * 输出：2
     * 解释：两个回旋镖为 [[1,0],[0,0],[2,0]] 和 [[1,0],[2,0],[0,0]]
     * <p>
     * 示例 2：
     * 输入：points = [[1,1],[2,2],[3,3]]
     * 输出：2
     * <p>
     * 示例 3：
     * 输入：points = [[1,1]]
     * 输出：0
     *
     * <p>
     * n == points.length
     * 1 <= n <= 500
     * points[i].length == 2
     * -104 <= xi, yi <= 104
     * 所有点都 互不相同
     */

    /*
    方法1：枚举+哈希表
    题目所描述的回旋镖可以视作一个 V 型的折线。我们可以枚举每个 points[i]，将其当作 V 型的拐点。设 points 中有 m 个点到 points[i]
    的距离均相等，我们需要从这 m 点中选出 2 个点当作回旋镖的 2 个端点，由于题目要求考虑元组的顺序，
    因此方案数即为在 m 个元素中选出 2 个不同元素的排列数，即：
        A_m^2 = m(m-1)

    据此，我们可以遍历 points，计算并统计所有点到 points[i] 的距离，将每个距离的出现次数记录在哈希表中，然后遍历哈希表，并
    用上述公式计算并累加回旋镖的个数。

    在代码实现时，我们可以直接保存距离的平方，避免复杂的开方运算。

     */
    public int numberOfBoomerangs(int[][] points) {
        int ans = 0;
        for (int[] p : points) {
            Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
            for (int[] q : points) {
                int dis = (p[0] - q[0]) * (p[0] - q[0]) + (p[1] - q[1]) * (p[1] - q[1]);
                cnt.put(dis, cnt.getOrDefault(dis, 0) + 1);
            }
            for (Map.Entry<Integer, Integer> entry : cnt.entrySet()) {
                int m = entry.getValue();
                ans += m * (m - 1);
            }
        }
        return ans;
    }


}
