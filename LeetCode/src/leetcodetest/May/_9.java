package leetcodetest.May;

import java.util.Arrays;

/**
 * @author WuChao
 * @since 2021/5/9 上午8:15
 */
public class _9 {
    /**
     * 每日一题：2021/5/9
     * 1482. 制作 m 束花所需的最少天数
     * 难度: medium
     * <p>
     * 给你一个整数数组 bloomDay，以及两个整数 m 和 k 。
     *
     * 现需要制作 m 束花。制作花束时，需要使用花园中 相邻的 k 朵花 。
     *
     * 花园中有 n 朵花，第 i 朵花会在 bloomDay[i] 时盛开，恰好 可以用于 一束 花中。
     *
     * 请你返回从花园中摘 m 束花需要等待的最少的天数。如果不能摘到 m 束花则返回 -1
     *
     *
     * <p>
     * 示例：
     * 输入：bloomDay = [1,10,3,10,2], m = 3, k = 1
     * 输出：3
     * 解释：让我们一起观察这三天的花开过程，x 表示花开，而 _ 表示花还未开。
     * 现在需要制作 3 束花，每束只需要 1 朵。
     * 1 天后：[x, _, _, _, _]   // 只能制作 1 束花
     * 2 天后：[x, _, _, _, x]   // 只能制作 2 束花
     * 3 天后：[x, _, x, _, x]   // 可以制作 3 束花，答案为 3
     *
     *
     * <p>
     * 数据范围：
     * bloomDay.length == n
     * 1 <= n <= 10^5
     * 1 <= bloomDay[i] <= 10^9
     * 1 <= m <= 10^6
     * 1 <= k <= n
     */

    /*
    思路：二分查找
    首先根据花朵的总数量(bloomDay.length)与制作m束花所需的全部花的数量(m*k)之比，是否可以满足制作要求
    满足的情况下，对制作天数使用二分查找来对天数[1,bloomDay.max]进行查找满足条件的最少天数
    因此还需要一个辅助函数用于判断在给定的天数内能否制作出指定数量的花束，辅助函数的参数除了 bloomDay、m 和 k 之外，
    还有一个参数 days 表示指定天数。

    对于辅助函数的实现，可以遍历数组 bloomDay，计算其中的长度为 k 且最大元素不超过 days 的不重合的连续子数组的数量，
    如果符合要求的不重合的连续子数组的数量大于或等于 mm 则返回  true，否则返回  false。
     */

    /**
     *
     * @param bloomDay 每朵花开花的天数
     * @param m 需要制作的花的总数
     * @param k 每一束花需要相邻的花的数量
     * @return 是否可以满足制作要求
     */
    public int minDays(int[] bloomDay, int m, int k) {
        if (m * k > bloomDay.length) {
            return -1;
        }

        int left = 1, right = Arrays.stream(bloomDay).max().getAsInt();
        while (left < right) {
            int days = left + ((right - left) >> 1);
            if (check(bloomDay, days, m, k)) {
                right = days;
            } else {
                left = days + 1;
            }
        }
        return right;


    }

    /**
     * 辅助函数：检查在给定天数下是否满足制作了m朵花
     * @param bloomDay
     * @param days
     * @param m
     * @param k
     * @return
     */
    public boolean check(int[] bloomDay, int days, int m, int k) {
        int sum = 0, flowers = 0;
        for (int i = 0; i < bloomDay.length && sum < m; i++) {
            if (bloomDay[i] <= days) {
                flowers++;
                if (flowers == k) {
                    sum++;
                    flowers = 0;
                }
            } else {
                flowers = 0;
            }

        }
        return sum >= m;
    }
}
