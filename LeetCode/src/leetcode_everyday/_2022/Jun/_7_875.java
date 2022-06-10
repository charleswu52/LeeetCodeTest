package leetcode_everyday._2022.Jun;

/**
 * @Author CharlesWu
 * @Create 2022/6/10 11:21
 * @Version 1.0
 * @Description
 * @Note
 */
public class _7_875 {
    /**
     * 每日一题：2022/6/7
     * 875. 爱吃香蕉的珂珂
     * 难度: medium
     * <p>
     * 珂珂喜欢吃香蕉。这里有 n 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 h 小时后回来。
     *
     * 珂珂可以决定她吃香蕉的速度 k （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 k 根。如果这堆香蕉少于 k 根，
     * 她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。
     *
     * 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
     *
     * 返回她可以在 h 小时内吃掉所有香蕉的最小速度 k（k 为整数）。
     * <p>
     * 示例:
     * <p>
     * 输入：piles = [3,6,7,11], h = 8
     *
     * 输出：4
     * <p>
     * 数据范围：
     * <p>
     * 1 <= piles.length <= 10^4
     * piles.length <= h <= 10^9
     * 1 <= piles[i] <= 10^9
     */

    /*
    思路：二分查找
     */
    public int minEatingSpeed(int[] piles, int h) {
        int low = 1, high = 0;
        for (int pile : piles) {
            high = Math.max(high, pile);
        }
        int k = high;
        while (low < high) {
            int speed = (high - low) / 2 + low;
            long time = getTime(piles, speed);
            if (time <= h) {
                k = speed;
                high = speed;
            } else {
                low = speed + 1;
            }
        }
        return k;

    }

    private long getTime(int[] piles, int speed) {
        long time = 0;
        for (int pile : piles) {
            int curTime = (pile + speed - 1) / speed;
            time += curTime;
        }
        return time;
    }
}
