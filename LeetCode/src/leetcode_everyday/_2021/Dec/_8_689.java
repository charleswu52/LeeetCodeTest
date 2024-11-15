package leetcode_everyday._2021.Dec;

/**
 * @author WuChao
 * @create 2021/12/8 15:43
 */
public class _8_689 {
    /**
     * 每日一题：2021/12/8
     * 689. 三个无重叠子数组的最大和
     * 难度：hard
     * <p>
     * 给你一个整数数组 nums 和一个整数 k ，找出三个长度为 k 、互不重叠、且 3 * k 项的和最大的子数组，并返回这三个子数组。
     *
     * 以下标的数组形式返回结果，数组中的每一项分别指示每个子数组的起始位置（下标从 0 开始）。如果有多个结果，返回字典序最小的一个。
     * <p>
     * 示例1
     * <p>
     * 输入：nums = [1,2,1,2,6,7,5,1], k = 2
     *
     * 输出：[0,3,5]
     *
     * 解释：子数组 [1, 2], [2, 6], [7, 5] 对应的起始下标为 [0, 3, 5]。
     * 也可以取 [2, 1], 但是结果 [1, 3, 5] 在字典序上更大。
     * <p>
     * 范围
     * <p>
     * 1 <= nums.length <= 2 * 10^4
     * 1 <= nums[i] < 2^16
     * 1 <= k <= floor(nums.length / 3)
     */


    /*
    思路：滑动窗口
    https://leetcode-cn.com/problems/maximum-sum-of-3-non-overlapping-subarrays/solution/san-ge-wu-zhong-die-zi-shu-zu-de-zui-da-4a8lb/
     */
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int[] ans = new int[3];
        int sum1 = 0, maxSum1 = 0, maxSum1Idx = 0;
        int sum2 = 0, maxSum2 = 0, maxSum2Idx1 = 0, maxSum2Idx2 = 0;
        int sum3 = 0, maxTotal = 0;
        for (int i = k * 2; i < nums.length; i++) {
            sum1 += nums[i - k * 2];
            sum2 += nums[i - k];
            sum3 += nums[i];
            if (i >= k * 3 - 1) {
                if (sum1 > maxSum1) {
                    maxSum1 = sum1;
                    maxSum1Idx = i - k * 3 + 1;
                }
                if (maxSum1+sum2 > maxSum2) {
                    maxSum2 = maxSum1+sum2;
                    maxSum2Idx1 = maxSum1Idx;
                    maxSum2Idx2 = i - k * 2 + 1;
                }
                if (maxSum2+sum3 > maxTotal) {
                    maxTotal = maxSum2 + sum3;
                    ans[0] = maxSum2Idx1;
                    ans[1] = maxSum2Idx2;
                    ans[2] = i - k + 1;
                }
                sum1 -= nums[i - k * 3 + 1];
                sum2 -= nums[i - k * 2 + 1];
                sum3 -= nums[i - k + 1];
            }
        }
        return ans;

    }
}
