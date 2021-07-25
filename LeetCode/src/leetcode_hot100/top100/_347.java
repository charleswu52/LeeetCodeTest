package leetcode_hot100.top100;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author WuChao
 * @create 2021/7/25 10:19
 */
public class _347 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 347. 前 K 个高频元素
     * 难度：medium
     * <p>
     * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
     *
     *  <p>
     * 示例 1:
     *
     * 输入: nums = [1,1,1,2,2,3], k = 2
     * 输出: [1,2]
     * 示例 2:
     *
     * 输入: nums = [1], k = 1
     * 输出: [1]
     *
     *
     * 提示：
     *
     * 1 <= nums.length <= 105
     * k 的取值范围是 [1, 数组中不相同的元素的个数]
     * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的
     *
     *
     * 进阶：你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n 是数组大小。
     * <p>
     */

    /*
    思路1：哈希表 + 优先队列
     */

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> elementCount = new HashMap<>();
        for (int num : nums) {
            elementCount.put(num, elementCount.getOrDefault(num, 0) + 1);
        }
        // 优先队列，最小堆，按照元素出现的频率排序
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return elementCount.get(o1) - elementCount.get(o2);
            }
        });
        for (Integer element : elementCount.keySet()) {
            if (queue.size() < k) {
                queue.add(element);
            } else if (elementCount.get(element) > elementCount.get(queue.peek())) {
                queue.remove();
                queue.add(element);
            }
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = queue.poll();
        }
        return res;

    }
}
