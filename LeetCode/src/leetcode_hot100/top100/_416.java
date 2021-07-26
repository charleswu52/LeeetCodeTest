package leetcode_hot100.top100;

/**
 * @author WuChao
 * @create 2021/7/26 11:10
 */
public class _416 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 416. 分割等和子集
     * 难度：medium
     * <p>
     * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
     *
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [1,5,11,5]
     * 输出：true
     * 解释：数组可以分割成 [1, 5, 5] 和 [11] 。
     * 示例 2：
     * <p>
     * 输入：nums = [1,2,3,5]
     * 输出：false
     * 解释：数组不能分割成两个元素和相等的子集。
     *
     *
     * <p>
     * 1 <= nums.length <= 200
     * 1 <= nums[i] <= 100
     */

    /*
    思路： 转化成 0 - 1背包问题
    找一个子集，使得子集和为 总和的一半
    定义动态规划方程 dp[i][j] 表示从下标[0,i]的范围内选取若干个正整数，是否存在一种选取方案使得呗选取的数字的和恰好等于j
    初始化 dp[0][nums[0]] = true;
          dp[i][0] = true;
    状态转移方程：如果 j>=nums[i] ,[dp[i][j] = dp[i-1][j] | dp[i-1][j-nums[i]] // 选或者不选
                如果 j<nums[i] ,dp[i][j] = dp[i-1][f]
    */
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return false;
        }
        int sum = 0, maxNum = 0;
        for (int num : nums) {
            sum += num;
            maxNum = Math.max(num, maxNum);
        }
        if (sum % 2 != 0) {
            return false;
        }

        int target = sum / 2;
        if (maxNum > target) {
            return false;
        }

        // dp方程. dp[i][j] 表示从数组的 [0,i] 下标范围内选取若干个正整数（可以是 0 个），是否存在一种选取方案使得被选取的正整数的和等于j。
        boolean[][] dp = new boolean[n][target + 1];
        // 初始化
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }
        dp[0][nums[0]] = true;
        for (int i = 1; i < n; i++) {
            int num = nums[i];
            for (int j = 1; j <= target; j++) {
                if (j >= num) {
                    dp[i][j] = dp[i - 1][j] | dp[i - 1][j - num];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n - 1][target];



    }
}
