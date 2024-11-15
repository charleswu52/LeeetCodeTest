package leetcode_hot100.top60;

/**
 * @author WuChao
 * @create 2021/7/17 13:21
 */
public class _198 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 198. 打家劫舍
     * 难度：medium
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
     * <p>
     * 输入：[2,7,9,3,1]
     * 输出：12
     * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
     *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
     * <p>
     * 进阶：
     * <p>
     * 数据范围
     * 1 <= nums.length <= 100
     * 0 <= nums[i] <= 400
     */

    /*
    题目：打家劫舍题目系列
    动态规划来解决

    思路：动态规划
    dp[i] 表示偷到该房子为止所获到的最大金额
    dp[0] = nums[0] ,只有该房子可选
    dp[1] = max(nums[0],nums[1]) 可以选当前房子或者之前的房子
    其他：dp[i] = max(dp[i-2]+nums[i],dp[i-1] ) // 偷当前房子或者不偷
     */
    public int rob(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return nums[0];
        }
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            // 偷 ， 不偷 分别的钱数求最大值
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[n - 1];

    }
}
