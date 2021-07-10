package leetcode_hot100.top40;

/**
 * @author WuChao
 * @create 2021/7/10 9:07
 */
public class _53 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 53. 最大子序和
     * 难度：easy
     * <p>
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * <p>
     * 示例
     * <p>
     * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
     * 输出：6
     * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
     * <p>
     * 范围
     * 1 <= nums.length <= 3 * 104
     * -10^5 <= nums[i] <= 10^5
     *进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。
     */
    public int maxSubArray(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = nums[0];
        int res = dp[0];
        for (int i = 1; i < len; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            res = Math.max(res, dp[i]);
        }
        return res;

    }
}
