package leetcode_everyday._2022.Mar;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author WuChao
 * @create 2022/3/4 8:57
 */
public class _4_2104 {
    /**
     * 每日一题：2022/3/4
     * <p>
     * 2104. 子数组范围和
     * <p>
     * 难度：medium
     * <p>
     * 给你一个整数数组 nums 。nums 中，子数组的 范围 是子数组中最大元素和最小元素的差值。
     * <p>
     * 返回 nums 中 所有 子数组范围的 和 。
     * <p>
     * 子数组是数组中一个连续 非空 的元素序列。
     * <p>
     * 示例
     * <p>
     * 输入：nums = [1,2,3]
     * <p>
     * 输出：4
     * <p>
     * 解释：nums 的 6 个子数组如下所示：
     * [1]，范围 = 最大 - 最小 = 1 - 1 = 0
     * [2]，范围 = 2 - 2 = 0
     * [3]，范围 = 3 - 3 = 0
     * [1,2]，范围 = 2 - 1 = 1
     * [2,3]，范围 = 3 - 2 = 1
     * [1,2,3]，范围 = 3 - 1 = 2
     * 所有范围的和是 0 + 0 + 0 + 1 + 1 + 2 = 4
     * <p>
     * 范围
     * <p>
     * 1 <= nums.length <= 1000
     * -10^9 <= nums[i] <= 10^9
     * <p>
     * 进阶：你可以设计一种时间复杂度为 O(n) 的解决方案吗？
     */

    /*
    思路1：暴力，遍历子数组
    O(n^2)时间复杂度
     */
    public long subArrayRanges(int[] nums) {
        int len = nums.length, max = 0, min = 0;
        long res = 0;
        for (int i = 0; i < len; i++) {
            max = nums[i];
            min = nums[i];
            for (int j = i + 1; j < len; j++) {
                //维护 max min
                max = Math.max(max, nums[j]);
                min = Math.min(min, nums[j]);
                res += (max - min);
            }
        }
        return res;
    }
    /*
    思路2：单调栈
    https://leetcode-cn.com/problems/sum-of-subarray-ranges/solution/zi-shu-zu-fan-wei-he-by-leetcode-solutio-lamr/
     */

    public long subArrayRanges2(int[] nums) {
        int n = nums.length;
        int[] minLeft = new int[n];
        int[] minRight = new int[n];
        int[] maxLeft = new int[n];
        int[] maxRight = new int[n];
        Deque<Integer> minStack = new ArrayDeque<>();
        Deque<Integer> maxStack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!minStack.isEmpty() && nums[minStack.peek()] > nums[i]) {
                minStack.pop();
            }
            minLeft[i] = minStack.isEmpty() ? -1 : minStack.peek();
            minStack.push(i);
            // 如果 nums[maxStack.peek()] == nums[i], 那么根据定义，
            // nums[maxStack.peek()] 逻辑上小于 nums[i]，因为 maxStack.peek() < i
            while (!maxStack.isEmpty() && nums[maxStack.peek()] <= nums[i]) {
                maxStack.pop();
            }
            maxLeft[i] = maxStack.isEmpty() ? -1 : maxStack.peek();
            maxStack.push(i);
        }
        minStack.clear();
        maxStack.clear();
        for (int i = n - 1; i >= 0; i--) {
            // 如果 nums[minStack.peek()] == nums[i], 那么根据定义，
            // nums[minStack.peek()] 逻辑上大于 nums[i]，因为 minStack.peek() > i
            while (!minStack.isEmpty() && nums[minStack.peek()] >= nums[i]) {
                minStack.pop();
            }
            minRight[i] = minStack.isEmpty() ? n : minStack.peek();
            minStack.push(i);

            while (!maxStack.isEmpty() && nums[maxStack.peek()] < nums[i]) {
                maxStack.pop();
            }
            maxRight[i] = maxStack.isEmpty() ? n : maxStack.peek();
            maxStack.push(i);
        }
        long sumMax = 0, sumMin = 0;
        for (int i = 0; i < n; i++) {
            sumMax += (long) (maxRight[i] - i) * (i - maxLeft[i]) * nums[i];
            sumMin += (long) (minRight[i] - i) * (i - minLeft[i]) * nums[i];
        }
        return sumMax - sumMin;
    }
}
