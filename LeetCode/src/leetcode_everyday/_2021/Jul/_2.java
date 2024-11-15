package leetcode_everyday._2021.Jul;

import java.util.Arrays;

/**
 * @author WuChao
 * @create 2021/7/2 8:20
 */
public class _2 {
    /**
     * 每日一题：2021/7/2
     * 1833. 雪糕的最大数量
     * 难度: medium
     * <p>
     * <p>
     * 夏日炎炎，小男孩 Tony 想买一些雪糕消消暑。
     *
     * 商店中新到 n 支雪糕，用长度为 n 的数组 costs 表示雪糕的定价，其中 costs[i] 表示第 i 支雪糕的现金价格。Tony 一共有 coins 现金可以用于消费，他想要买尽可能多的雪糕。
     *
     * 给你价格数组 costs 和现金量 coins ，请你计算并返回 Tony 用 coins 现金能够买到的雪糕的 最大数量 。
     *
     * 注意：Tony 可以按任意顺序购买雪糕。
     *
     *
     * <p>
     * 输入：costs = [1,3,2,4,1], coins = 7
     * 输出：4
     * 解释：Tony 可以买下标为 0、1、2、4 的雪糕，总价为 1 + 3 + 2 + 1 = 7
     * <p>
     * 输入：costs = [10,6,8,7,7,8], coins = 5
     * 输出：0
     * 解释：Tony 没有足够的钱买任何一支雪糕。
     * <p>
     * 限制：
     * <p>
     * costs.length == n
     * 1 <= n <= 105
     * 1 <= costs[i] <= 10^5
     * 1 <= coins <= 10^8
     */

    /*
    简单思路：排序+贪心
     */
    public int maxIceCream(int[] costs, int coins) {
        int res = 0;
        Arrays.sort(costs);
        for (int cost : costs) {
            if (coins >= cost) {
                res++;
                coins -= cost;
            } else {
                break;
            }
        }
        return res;

    }
}
