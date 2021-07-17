package leetcode_everyday.Jul;

/**
 * @author WuChao
 * @create 2021/7/17 7:49
 */
public class _17 {
    /**
     * 每日一题：2021/7/17
     * 剑指 Offer 42. 连续子数组的最大和
     * 难度: easy
     * <p>
     *     输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
     *     要求时间复杂度为O(n)。
     * <p>
     * 示例
     * 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
     * 输出: 6
     * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
     * <p>
     * 限制：
     * 1 <= arr.length <= 10^5
     * -100 <= arr[i] <= 100
     */

    /*
    思路：动态规划

    简单dp题，定义dp[i] 表示到i为止的子数组的和
    dp[i] = max(dp[i-1]+nums[i],nums[i])
    结果为dp中最大元素
    时间复杂度
     */
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        int res = nums[0];
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            res = Math.max(res, dp[i]);
        }
        return res;


    }
}
