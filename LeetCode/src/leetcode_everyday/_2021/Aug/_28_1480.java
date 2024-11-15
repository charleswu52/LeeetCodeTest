package leetcode_everyday._2021.Aug;

/**
 * @author WuChao
 * @create 2021/8/28 9:25
 */
public class _28_1480 {
    /**
     * 每日一题：2021/8/28
     * 1480. 一维数组的动态和
     * 难度：easy
     * <p>
     * 给你一个数组 nums 。数组「动态和」的计算公式为：runningSum[i] = sum(nums[0]…nums[i]) 。
     *
     * 请返回 nums 的动态和。
     *
     * <p>
     * 示例 1：
     * 输入：nums = [1,2,3,4]
     * 输出：[1,3,6,10]
     * 解释：动态和计算过程为 [1, 1+2, 1+2+3, 1+2+3+4] 。
     *
     * 示例 2：
     * 输入：nums = [1,1,1,1,1]
     * 输出：[1,2,3,4,5]
     * 解释：动态和计算过程为 [1, 1+1, 1+1+1, 1+1+1+1, 1+1+1+1+1] 。
     *
     * 示例 3：
     * 输入：nums = [3,1,2,10,1]
     * 输出：[3,4,6,16,17]
     * <p>
     * 1 <= nums.length <= 1000
     * -10^6 <= nums[i] <= 10^6
     */

    public int[] runningSum(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[]{};
        }
        int n = nums.length;
        int[] res = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            res[i] = sum;
        }
        return res;

    }
}
