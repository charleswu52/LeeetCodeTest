package leetcode_everyday._2021.Aug;

import java.util.Arrays;

/**
 * @author WuChao
 * @create 2021/8/10 15:05
 */
public class _10_413 {
    /**
     * 每日一题：2021/8/10
     * 413. 等差数列划分
     * 难度：medium
     * <p>
     * 如果一个数列 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该数列为等差数列。
     * <p>
     * 例如，[1,3,5,7,9]、[7,7,7,7] 和 [3,-1,-5,-9] 都是等差数列。
     * 给你一个整数数组 nums ，返回数组 nums 中所有为等差数组的 子数组 个数。
     * <p>
     * 子数组 是数组中的一个连续序列。
     *
     * <p>
     * 示例 1：
     * 输入：nums = [1,2,3,4]
     * 输出：3
     * 解释：nums 中有三个子等差数组：[1, 2, 3]、[2, 3, 4] 和 [1,2,3,4] 自身。
     * <p>
     * 示例 2：
     * 输入：nums = [1]
     * 输出：0
     * <p>
     * 注意:
     * 1 <= nums.length <= 5000
     * -1000 <= nums[i] <= 1000
     */

    /*
    思路：动态规划
    定义 dp[i] 是以nums[i]为终点的等差数列的个数
        当 nums[i] - nums[i-1] == nums[i-1] -nums[i-2] 时，说明增加的 nums[i] 能 和前面构成等差数列，那么 dp[i] = dp[i-1]+1
        当 nums[i] - nums[i-1] != nums[i-1] -nums[i-2] 时, 说明增加的 nums[i] 不能 和前面构成等差数列，那么dp[i] = 0
        初始状态 dp[0] = 0, dp[1] = 0

    最后要求的是整个数组中等差数列的数目，所以需要把 0 <= i <= len(nums)-1 的所有结果相加

     */
    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return 0;
        }
        int[] dp = new int[n];
        for (int i = 1; i < n - 1; i++) {
            if (nums[i - 1] + nums[i + 1] == nums[i] * 2) {
                dp[i] = dp[i - 1] + 1;
            }
        }
        return Arrays.stream(dp).sum();

    }

    /*
    思路：动态规划 改进
    由于 dp[i] 只和 dp[i-1] 有关，所以可以进行状态压缩，只用一个变量k来表示即可
     */
    public int numberOfArithmeticSlices2(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return 0;
        }
        int pre = 0;
        int res = 0;
        for (int i = 0; i < n - 2; i++) {
            if (nums[i + 1] - nums[i] == nums[i + 2] - nums[i + 1]) {
                pre++;
                res += pre;
            } else {
                pre = 0;
            }
        }
        return res;
    }
}
