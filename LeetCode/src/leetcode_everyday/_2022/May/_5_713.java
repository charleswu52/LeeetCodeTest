package leetcode_everyday._2022.May;

/**
 * @author WuChao
 * @create 2022/5/5 19:07
 */
public class _5_713 {
    /**
     * 每日一题：2022/5/5
     * <p>
     * 713. 乘积小于 K 的子数组
     * <p>
     * 难度：medium
     * <p>
     * 给你一个整数数组 nums 和一个整数 k ，请你返回子数组内所有元素的乘积严格小于 k 的连续子数组的数目。
     * <p>
     * 示例
     * <p>
     * 输入：nums = [10,5,2,6], k = 100
     *
     * 输出：8
     *
     * 解释：8 个乘积小于 100 的子数组分别为：[10]、[5]、[2],、[6]、[10,5]、[5,2]、[2,6]、[5,2,6]。
     * 需要注意的是 [10,5,2] 并不是乘积小于 100 的子数组。
     * <p>
     * 范围
     * <p>
     * 1 <= nums.length <= 3 * 104
     * 1 <= nums[i] <= 1000
     * 0 <= k <= 106
     */

    /*
    思路：滑动窗口
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int n = nums.length, ans = 0;
        if (k <= 1) {
            return 0;
        }
        for (int i = 0, j = 0, cur = 1; i < n; i++) {
            cur *= nums[i];
            while (cur >= k) {
                cur /= nums[j];
                j++;
            }
            ans += i - j + 1;
        }
        return ans;

    }
}
