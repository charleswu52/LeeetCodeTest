import org.junit.Test;

/**
 * @author WuChao
 * @since 2021/3/21 下午1:52
 */
public class _42 {
    /**
     * 剑指 Offer 42. 连续子数组的最大和
     * 难度: easy
     * <p>
     * 输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
     *
     * 要求时间复杂度为O(n)。
     *
     *<p>
     * 示例：
     * 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
     * 输出: 6
     * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
     *
     * <p>
     * 数据范围：
     * 1 <= arr.length <= 10^5
     * -100 <= arr[i] <= 100
     */
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        // 初始化状态
        dp[0] = nums[0];
        int max = dp[0];
        // 由状态转移方程得到dp数组
        for (int i = 1; i < dp.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            max = Math.max(max, dp[i]);
        }

        return max;
    }

    @Test
    public void test() {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray(nums));
    }
}
