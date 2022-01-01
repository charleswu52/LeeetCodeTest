package leetcode_everyday._2021.Dec;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author WuChao
 * @create 2021/12/30 上午9:23
 */
public class _30_846 {
    /**
     * 每日一题：2021/12/30
     * <p>
     * 846. 一手顺子
     * <p>
     * 难度：medium
     * <p>
     * Alice 手中有一把牌，她想要重新排列这些牌，分成若干组，使每一组的牌数都是 groupSize ，并且由 groupSize 张连续的牌组成。
     *
     * 给你一个整数数组 hand 其中 hand[i] 是写在第 i 张牌，和一个整数 groupSize 。如果她可能重新排列这些牌，返回 true ；否则，返回 false 。
     * <p>
     * 示例 1：
     * <p>
     * 输入：hand = [1,2,3,6,2,3,4,7,8], groupSize = 3
     *
     * 输出：true
     *
     * 解释：Alice 手中的牌可以被重新排列为 [1,2,3]，[2,3,4]，[6,7,8]。
     * <p>
     * 示例 2：
     * <p>
     * 输入：hand = [1,2,3,4,5], groupSize = 4
     *
     * 输出：false
     *
     * 解释：Alice 手中的牌无法被重新排列成几个大小为 4 的组。
     * <p>
     * 范围
     * <p>
     * 1 <= hand.length <= 104
     * 0 <= hand[i] <= 109
     * 1 <= groupSize <= hand.length
     **/

    /*
    思路：数组模拟 + 哈希表 + 优先队列

    用 哈希表 统计 每个数字出现的次数，
    使用 优先队列（小根堆） 维护所有的hand[i],每次从优先队列的队头取出堆顶元素来尝试将其作为顺子的首个元素，若构造过程中没有出现剩余出现次数不足-1
    的话，说明整个构造过程中没有冲突，返回true，否则返回false。
     */
    public boolean isNStraightHand(int[] hand, int groupSize) {
        Map<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((o1, o2) -> o1 - o2);
        for (int num : hand) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            priorityQueue.offer(num);
        }
        while (!priorityQueue.isEmpty()) {
            int temp = priorityQueue.poll();
            if (map.get(temp) == 0) {
                continue;
            }
            for (int i = 0; i < groupSize; i++) {
                int cnt = map.getOrDefault(temp + i, 0);
                if (cnt == 0) {
                    return false;
                }
                map.put(temp + i, cnt - 1);
            }
        }
        return true;
    }
}
