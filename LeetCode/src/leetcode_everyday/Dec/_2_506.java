package leetcode_everyday.Dec;

import java.util.PriorityQueue;

/**
 * @author WuChao
 * @create 2021/12/2 8:37
 */
public class _2_506 {
    /**
     * 每日一题：2021/12/2
     * 506. 相对名次
     * 难度: easy
     * <p>
     * 给你一个长度为 n 的整数数组 score ，其中 score[i] 是第 i 位运动员在比赛中的得分。所有得分都 互不相同 。
     *
     * 运动员将根据得分 决定名次 ，其中名次第 1 的运动员得分最高，名次第 2 的运动员得分第 2 高，依此类推。运动员的名次决定了他们的获奖情况：
     *
     * 名次第 1 的运动员获金牌 "Gold Medal" 。
     * 名次第 2 的运动员获银牌 "Silver Medal" 。
     * 名次第 3 的运动员获铜牌 "Bronze Medal" 。
     * 从名次第 4 到第 n 的运动员，只能获得他们的名次编号（即，名次第 x 的运动员获得编号 "x"）。
     * 使用长度为 n 的数组 answer 返回获奖，其中 answer[i] 是第 i 位运动员的获奖情况。
     * <p>
     * 输入：score = [10,3,8,9,4]
     * 输出：["Gold Medal","5","Bronze Medal","Silver Medal","4"]
     * 解释：名次为 [1st, 5th, 3rd, 2nd, 4th] 。
     *
     * <p>
     * 数据范围：
     * n == score.length
     * 1 <= n <= 10^4
     * 0 <= score[i] <= 10^6
     * score 中的所有值 互不相同
     */

    public String[] findRelativeRanks(int[] score) {
        int n = score.length;

        PriorityQueue<int[]> store = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
        for (int i = 0; i < n; i++) {
            store.offer(new int[]{i, score[i]});
        }
        String[] res = new String[n];
        int idx = 0;
        while (!store.isEmpty()) {
            ++idx;
            int index = store.poll()[0];
            if (idx == 1) {
                res[index] = "Gold Medal";
            } else if (idx == 2) {
                res[index] = "Silver Medal";
            } else if (idx == 3) {
                res[index] = "Bronze Medal";

            } else {
                res[index] = "" + idx;

            }

        }
        return res;


    }


}
