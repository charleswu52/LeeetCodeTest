package leetcode_everyday._2021.Aug;

/**
 * @author WuChao
 * @create 2021/8/3 8:28
 */
public class _3_581 {
    /**
     * 每日一题：2021/8/3
     * 581. 最短无序连续子数组
     * 难度：medium
     * <p>
     * 给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
     * <p>
     * 请你找出符合题意的 最短 子数组，并输出它的长度。
     *
     * <p>
     * 示例 1：
     * 输入：nums = [2,6,4,8,10,9,15]
     * 输出：5
     * 解释：你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
     * <p>
     * 示例 2：
     * 输入：nums = [1,2,3,4]
     * 输出：0
     * <p>
     * 示例 3：
     * 输入：nums = [1]
     * 输出：0
     *
     * <p>
     * 提示：
     * 1 <= nums.length <= 10^4
     * -10^5 <= nums[i] <= 10^5
     * <p>
     * 进阶：你可以设计一个时间复杂度为 O(n) 的解决方案吗？
     */

    /*
    题目解析：
    要找到一个数组中最小的无序子数组的个数

    思路：
    要使时间复杂度为O(n),需要遍历两遍这个数组，找到无序数组中的左右两个端点

     */
    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;

        // 需要扫描两遍
        // 扫描过程中要记录其元素左/右 边最大/小的数，然后无序数组的下标是小于/大于其当前最大/小的数的元素的下标

        // 从右边向左扫描，正常应该是递减的，所以右边遍历过去的元素应该记录一个最小值，当左边的数大于该最小值的时候，要记录/更新该数据的下标，同时还要更新最大值
        int min = nums[n-1];
        int left = n;
        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] > min) {
                left = i;
            }
            min = Math.min(min, nums[i]);
        }

        // 从左到右扫描，正常应该是递增的，所有左边遍历过去的元素应该是一个最大值，当右边的数小于该最大值的时候，要记录/更新该数据的下标，同时还要更新最大值
        int max = nums[0];
        int right = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] < max) {
                right = i;
            }
            max = Math.max(max, nums[i]);
        }

        return Math.max(right - left + 1, 0);

    }

}
