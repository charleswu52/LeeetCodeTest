package leetcode_everyday._2022.Feb;

/**
 * @author WuChao
 * @create 2022/2/26 8:44
 */
public class _26_2016 {
    /**
     * 每日一题：2022/2/26
     * <p>
     * 2016. 增量元素之间的最大差值
     * <p>
     * 难度：easy
     * <p>
     * 给你一个下标从 0 开始的整数数组 nums ，该数组的大小为 n ，请你计算 nums[j] - nums[i] 能求得的 最大差值 ，
     * 其中 0 <= i < j < n 且 nums[i] < nums[j] 。
     *
     * 返回 最大差值 。如果不存在满足要求的 i 和 j ，返回 -1 。
     * <p>
     * 示例
     * <p>
     * 输入：nums = [7,1,5,4]
     *
     * 输出：4
     *
     * 解释：
     *
     * 最大差值出现在 i = 1 且 j = 2 时，nums[j] - nums[i] = 5 - 1 = 4 。
     * 注意，尽管 i = 1 且 j = 0 时 ，nums[j] - nums[i] = 7 - 1 = 6 > 4 ，但 i > j 不满足题面要求，所以 6 不是有效的答案。
     * <p>
     * 范围
     * <p>
     * n == nums.length
     * 2 <= n <= 1000
     * 1 <= nums[i] <= 109
     */

    public int maximumDifference(int[] nums) {
        int max = 0;
        int minNum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            max = Math.max(max, nums[i] - minNum);
            minNum = Math.min(nums[i], minNum);
        }
        return max == 0 ? -1 : max;
    }
}
