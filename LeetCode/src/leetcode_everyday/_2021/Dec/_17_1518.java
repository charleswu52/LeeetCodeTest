package leetcode_everyday._2021.Dec;

/**
 * @author WuChao
 * @create 2021/12/17 上午9:45
 */
public class _17_1518 {
    /**
     * 每日一题：2021/12/17
     * <p>
     * 1518. 换酒问题
     * <p>
     * 难度：easy
     * <p>
     * 小区便利店正在促销，用 numExchange 个空酒瓶可以兑换一瓶新酒。你购入了 numBottles 瓶酒。
     *
     * 如果喝掉了酒瓶中的酒，那么酒瓶就会变成空的。
     *
     * 请你计算 最多 能喝到多少瓶酒。
     *
     * <p>
     * 示例 1：
     * <p>
     * 例子：
     * 输入：numBottles = 9, numExchange = 3
     * 输出：13
     * 解释：你可以用 3 个空酒瓶兑换 1 瓶酒。
     * 所以最多能喝到 9 + 3 + 1 = 13 瓶酒。
     *
     * <p>
     * 范围
     * <p>
     * 1 <= numBottles <= 100
     * 2 <= numExchange <= 100
     */

    /*
    思路：数学
     */
    public int numWaterBottles(int numBottles, int numExchange) {
        int ans = numBottles;
        while (numBottles >= numExchange) {
            int change = numBottles / numExchange;
            numBottles %= numExchange;
            ans += change;
            numBottles += change;
        }


        return ans;

    }
}
