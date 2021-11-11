package leetcode_everyday.Nov;

/**
 * @author WuChao
 * @create 2021/11/10 9:06
 */
public class _10_495 {
    /**
     * 每日一题：2021/11/10
     * <p>
     * 495. 提莫攻击
     * <p>
     * 难度：easy
     * <p>
     * 在《英雄联盟》的世界中，有一个叫 “提莫” 的英雄。他的攻击可以让敌方英雄艾希（编者注：寒冰射手）进入中毒状态。
     * <p>
     * 当提莫攻击艾希，艾希的中毒状态正好持续 duration 秒。
     * <p>
     * 正式地讲，提莫在 t 发起发起攻击意味着艾希在时间区间 [t, t + duration - 1]（含 t 和 t + duration - 1）处于中毒状态。如果提莫在中毒影响结束 前 再次攻击，中毒状态计时器将会 重置 ，在新的攻击之后，中毒影响将会在 duration 秒后结束。
     * <p>
     * 给你一个 非递减 的整数数组 timeSeries ，其中 timeSeries[i] 表示提莫在 timeSeries[i] 秒时对艾希发起攻击，以及一个表示中毒持续时间的整数 duration 。
     * <p>
     * 返回艾希处于中毒状态的 总 秒数。
     *
     * <p>
     * 示例1：
     * <p>
     * 输入：timeSeries = [1,2], duration = 2
     * <p>
     * 输出：3
     * <p>
     * 解释：提莫攻击对艾希的影响如下：
     * - 第 1 秒，提莫攻击艾希并使其立即中毒。中毒状态会维持 2 秒，即第 1 秒和第 2 秒。
     * - 第 2 秒，提莫再次攻击艾希，并重置中毒计时器，艾希中毒状态需要持续 2 秒，即第 2 秒和第 3 秒。
     * 艾希在第 1、2、3 秒处于中毒状态，所以总中毒秒数是 3 。
     *
     * <p>
     * 范围
     * <p>
     * 1 <= timeSeries.length <= 104
     * 0 <= timeSeries[i], duration <= 107
     * timeSeries 按 非递减 顺序排列
     */

    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int ans = 0;
        for (int i = 1; i < timeSeries.length; i++) {
            if (timeSeries[i] - timeSeries[i - 1] >= duration) {
                ans += duration;
            } else {
                ans += timeSeries[i] - timeSeries[i - 1];
            }
        }

        // 加上最后一次的中毒时间
        return ans + duration;


    }
}
