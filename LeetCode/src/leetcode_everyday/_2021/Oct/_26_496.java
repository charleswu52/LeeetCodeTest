package leetcode_everyday._2021.Oct;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * @author WuChao
 * @create 2021/10/26 8:53
 */
public class _26_496 {
    /**
     * 每日一题：2021/10/26
     * <p>
     * 496. 下一个更大元素 I
     * <p>
     * 难度：easy
     * <p>
     * 给你两个 没有重复元素 的数组 nums1 和 nums2 ，其中 nums1 是 nums2 的子集。
     * <p>
     * 请你找出 nums1 中每个元素在 nums2 中的下一个比其大的值。
     * <p>
     * nums1 中数字 x 的下一个更大元素是指 x 在nums2 中对应位置的右边的第一个比 x 大的元素。如果不存在，对应位置输出 -1 。
     * <p>
     * 示例1：
     * <p>
     * 输入: nums1 = [4,1,2], nums2 = [1,3,4,2].
     * <p>
     * 输出: [-1,3,-1]
     * <p>
     * 解释:
     * 对于 num1 中的数字 4 ，你无法在第二个数组中找到下一个更大的数字，因此输出 -1 。
     * 对于 num1 中的数字 1 ，第二个数组中数字1右边的下一个较大数字是 3 。
     * 对于 num1 中的数字 2 ，第二个数组中没有下一个更大的数字，因此输出 -1
     * <p>
     * 范围
     * <p>
     * 1 <= nums1.length <= nums2.length <= 1000
     * 0 <= nums1[i], nums2[i] <= 104
     * nums1和nums2中所有整数 互不相同
     * nums1 中的所有整数同样出现在 nums2 中
     * <p>
     * 进阶
     * <p>
     * 你可以设计一个时间复杂度为 O(nums1.length + nums2.length) 的解决方案吗？
     */


    /*
    思路：典型的单调栈问题
    哈希表 + 单调栈

     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length;
        int[] res = new int[len1];
        Map<Integer, Integer> map = new HashMap<>();
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = len2 - 1; i >= 0; i--) {
            int num = nums2[i];
            while (!stack.isEmpty() && num >= stack.peek()) {
                stack.pop();
            }
            map.put(num, stack.isEmpty() ? -1 : stack.peek());
            stack.push(num);
        }
        for (int i = 0; i < len1; i++) {
            res[i] = map.get(nums1[i]);
        }
        return res;
    }
}
