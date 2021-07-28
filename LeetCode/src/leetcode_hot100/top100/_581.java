package leetcode_hot100.top100;

import org.junit.Test;

/**
 * @author WuChao
 * @create 2021/7/28 14:03
 */
public class _581 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 581. 最短无序连续子数组
     * 难度：medium
     * <p>
     * 给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
     *
     * 请你找出符合题意的 最短 子数组，并输出它的长度。
     *
     * <p>
     * 示例 1：
     * 输入：nums = [2,6,4,8,10,9,15]
     * 输出：5
     * 解释：你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
     *
     * 示例 2：
     * 输入：nums = [1,2,3,4]
     * 输出：0
     *
     * 示例 3：
     * 输入：nums = [1]
     * 输出：0
     *
     * <p>
     * 提示：
     *
     * 1 <= nums.length <= 104
     * -105 <= nums[i] <= 105
     *
     *
     * 进阶：你可以设计一个时间复杂度为 O(n) 的解决方案吗？
     */

    /*
    思路：两次循环 时间O(n) 空间 O(1)
     */

    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;

        // 扫描两遍
        // 从右向左扫描，确定无序区间的最左端点，其中维护右边的一个最大值，当向左扫描遇到比该数还大的数的时候需要更新区间左端点，
        // 同时向左移动的时候也需要实时更新右边数字的最大值
        int mn = nums[n - 1], left = n;
        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] > mn) {
                left = i;
            }
            mn = Math.min(nums[i], mn);
        }
        // 从左向右扫描，确定无序区间的右端点，从左到右应该是递增的，因此遇到小于左边数字的时候，需要更新右端点的值
        int mx = nums[0], right = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] < mx) {
                right = i;
            }
            mx = Math.max(nums[i], mx);
        }

        return Math.max(right - left + 1, 0);
    }

    @Test
    public void test() {
        int[] nums = {1, 2,3 , 5, 7, 4, 6, 8};
        System.out.println(findUnsortedSubarray(nums));
    }
}
