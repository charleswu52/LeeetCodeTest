package chp16;

/**
 * @author WuChao
 * @since 2021/5/31 下午1:39
 */
public class _17 {
    /**
     * 程序员面试金典(version 6) -  面试题 16.17. 连续数列
     * 难度: easy
     * <p>
     * 给定一个整数数组，找出总和最大的连续数列，并返回总和。
     * <p>
     * 示例:
     * 输入： [-2,1,-3,4,-1,2,1,-5,4]
     * 输出： 6
     * 解释： 连续子数组 [4,-1,2,1] 的和最大，为 6。
     * <p>
     * 数据范围：
     *
     * <p>
     * 进阶：
     * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
     */

    /*
    思路1；一次遍历动态规划
     */
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return nums[0];
        }
        int[] dp = new int[n];
        dp[0] = nums[0];
        int res = dp[0];
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
            res = Math.max(dp[i], res);
        }
        return res;
    }

    /*
    思路2：参考题解 分治法
    https://leetcode-cn.com/problems/contiguous-sequence-lcci/solution/lian-xu-shu-lie-by-leetcode-solution-be4z/
     */
}
