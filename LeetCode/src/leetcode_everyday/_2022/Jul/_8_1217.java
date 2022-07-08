package leetcode_everyday._2022.Jul;

/**
 * @author WuChao
 * @create 2022/7/8 14:12
 */
public class _8_1217 {
    /**
     * 每日一题：2022/7/8
     * <p>
     * 1217. 玩筹码
     * <p>
     * 难度：easy
     * <p>
     * 有 n 个筹码。第 i 个筹码的位置是 position[i] 。
     * <p>
     * 我们需要把所有筹码移到同一个位置。在一步中，我们可以将第 i 个筹码的位置从 position[i] 改变为:
     * <p>
     * position[i] + 2 或 position[i] - 2 ，此时 cost = 0
     * position[i] + 1 或 position[i] - 1 ，此时 cost = 1
     * 返回将所有筹码移动到同一位置上所需要的 最小代价 。
     * <p>
     * 示例
     * <p>
     * 输入：position = [2,2,2,3,3]
     * <p>
     * 输出：2
     * <p>
     * 解释：我们可以把位置3的两个筹码移到位置2。每一步的成本为1。总成本= 2。
     * <p>
     * 范围
     * <p>
     * 1 <= chips.length <= 100
     * 1 <= chips[i] <= 10^9
     */


    /*
    思路：数学计算
    分别统计奇数位置的数量和偶数位置的数量，比较各自数量的多少，将较少的个数移到较多个数的位置上即可。
     */
    public int minCostToMoveChips(int[] position) {

        int odd = 0, even = 0;
        for (int pos : position) {
            if (pos % 2 == 0) {
                odd++;
            } else {
                even++;
            }
        }
        return Math.min(even, odd);

    }
}
