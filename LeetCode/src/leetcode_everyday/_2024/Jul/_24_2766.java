package leetcode_everyday._2024.Jul;

import java.util.*;

/**
 * @author charles
 * @create 2024/7/27
 */
public class _24_2766 {
    /**
     * 每日一题：2024/7/24
     * 2766. 重新放置石块
     * https://leetcode.cn/problems/relocate-marbles/description/?envType=daily-question&envId=2024-07-22
     */

    public List<Integer> relocateMarbles(int[] nums, int[] moveFrom, int[] moveTo) {
        Map<Integer,Boolean> position = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            position.put(nums[i], true);
        }
        for (int i = 0; i < moveFrom.length; i++) {
            int from = moveFrom[i];
            int to = moveTo[i];
            if (position.containsKey(from) && from != to) {
                position.put(from, false);
                position.put(to, true);
            }
        }
        ArrayList<Integer> integers = new ArrayList<>();
        position.forEach((integer, aBoolean) -> {
            if (aBoolean) {
                integers.add(integer);
            }
        });
        Collections.sort(integers);
        return integers;
    }

}