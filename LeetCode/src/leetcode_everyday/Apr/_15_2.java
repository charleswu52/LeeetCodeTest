package leetcode_everyday.Apr;

/**
 * @author WuChao
 * @since 2021/4/15 上午10:39
 */
public class _15_2 {
    /**
     * 每日一题：2021/4/15
     * 198. 打家劫舍
     * 难度: medium
     * <p>
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
     * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     *
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
     * <p>
     * 示例：
     * 输入：[1,2,3,1]
     * 输出：4
     * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     *      偷窃到的最高金额 = 1 + 3 = 4 。
     *
     * <p>
     * 数据范围：
     * 0 <= nums.length <= 100
     * 0 <= nums[i] <= 400
     */

    /*
    思路：使用动态规划
    定义动态规划数组dp[n],表示在第i家所偷盗的钱数
    偷的话，钱数为dp[i] =dp[i-1]+nums[i]
    不偷，保留的钱还是在上一家偷到的钱数 dp[i] = dp[i-1]
    因此动态规划数组为 dp[i] = max(dp[i-2]+nums[i],dp[i-1])
     */
    public int rob(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return nums[0];
        }
        int[] dp = new int[len];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < len; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[len - 1];
    }
}
