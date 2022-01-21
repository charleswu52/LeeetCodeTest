package leetcode_everyday._2022.Jan;

import java.util.*;

/**
 * @author WuChao
 * @create 2022/1/21 10:17
 */
public class _21_1345 {
    /**
     * 每日一题：2022/1/21
     * <p>
     * 1345. 跳跃游戏 IV
     * <p>
     * 难度：hard
     * <p>
     * 给你一个整数数组 arr ，你一开始在数组的第一个元素处（下标为 0）。
     * <p>
     * 每一步，你可以从下标 i 跳到下标：
     * <p>
     * i + 1 满足：i + 1 < arr.length
     * i - 1 满足：i - 1 >= 0
     * j 满足：arr[i] == arr[j] 且 i != j
     * 请你返回到达数组最后一个元素的下标处所需的 最少操作次数 。
     * <p>
     * 注意：任何时候你都不能跳到数组外面。
     * <p>
     * 示例 1：
     * <p>
     * 输入：arr = [100,-23,-23,404,100,23,23,23,3,404]
     * <p>
     * 输出：3
     * <p>
     * 解释：那你需要跳跃 3 次，下标依次为 0 --> 4 --> 3 --> 9 。下标 9 为数组的最后一个元素的下标。
     * <p>
     * 范围
     * <p>
     * 1 <= arr.length <= 5 * 10^4
     * -10^8 <= arr[i] <= 10^8
     */

    /*
    思路： 广度优先搜索

     */
    public int minJumps(int[] arr) {
        Map<Integer, List<Integer>> idxSameValue = new HashMap<Integer, List<Integer>>();
        for (int i = 0; i < arr.length; i++) {
            idxSameValue.putIfAbsent(arr[i], new ArrayList<Integer>());
            idxSameValue.get(arr[i]).add(i);
        }
        Set<Integer> visitedIndex = new HashSet<Integer>();
        Queue<int[]> queue = new ArrayDeque<int[]>();
        queue.offer(new int[]{0, 0});
        visitedIndex.add(0);
        while (!queue.isEmpty()) {
            int[] idxStep = queue.poll();
            int idx = idxStep[0], step = idxStep[1];
            if (idx == arr.length - 1) {
                return step;
            }
            int v = arr[idx];
            step++;
            if (idxSameValue.containsKey(v)) {
                for (int i : idxSameValue.get(v)) {
                    if (visitedIndex.add(i)) {
                        queue.offer(new int[]{i, step});
                    }
                }
                idxSameValue.remove(v);
            }
            if (idx + 1 < arr.length && visitedIndex.add(idx + 1)) {
                queue.offer(new int[]{idx + 1, step});
            }
            if (idx - 1 >= 0 && visitedIndex.add(idx - 1)) {
                queue.offer(new int[]{idx - 1, step});
            }
        }
        return -1;
    }
}
