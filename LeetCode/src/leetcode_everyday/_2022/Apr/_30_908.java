package leetcode_everyday._2022.Apr;

import java.util.Arrays;

/**
 * @author WuChao
 * @create 2022/4/30 9:19
 */
public class _30_908 {
    /**
     * 每日一题：2022/4/30
     * <p>
     * 908. 最小差值 I
     * <p>
     * 难度：easy
     * <p>
     * 给你一个整数数组 nums，和一个整数 k 。
     *
     * 在一个操作中，您可以选择 0 <= i < nums.length 的任何索引 i 。将 nums[i] 改为 nums[i] + x ，
     * 其中 x 是一个范围为 [-k, k] 的整数。对于每个索引 i ，最多 只能 应用 一次 此操作。
     *
     * nums 的 分数 是 nums 中最大和最小元素的差值。
     *
     * 在对  nums 中的每个索引最多应用一次上述操作后，返回 nums 的最低 分数 。
     * <p>
     * 示例
     * <p>
     * 输入：nums = [0,10], k = 2
     *
     * 输出：6
     *
     * 解释：将 nums 改为 [2,8]。分数是 max(nums) - min(nums) = 8 - 2 = 6。
     * <p>
     * 范围
     * <p>
     * 1 <= nums.length <= 10^4
     * 0 <= nums[i] <= 10^4
     * 0 <= k <= 10^4
     */

    /*
    思路：数学分析
     */
    public int smallestRangeI(int[] nums, int k) {
        int max = Arrays.stream(nums).max().getAsInt();
        int min = Arrays.stream(nums).min().getAsInt();
        if (max - min > 2 * k) {
            return max - min - 2 * k;
        } else {
            return 0;
        }
    }
}
