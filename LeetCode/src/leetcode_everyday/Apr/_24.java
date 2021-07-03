package leetcode_everyday.Apr;

import org.junit.Test;

/**
 * @author WuChao
 * @since 2021/4/24 上午8:44
 */
public class _24 {
    /**
     * 每日一题：2021/4/24
     * 377. 组合总和 Ⅳ
     * 难度: medium
     * <p>
     * 给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。请你从 nums 中找出并返回总和为 target 的元素组合的个数。
     *
     * 题目数据保证答案符合 32 位整数范围。
     *
     * <p>
     * 示例：
     * 输入：nums = [1,2,3], target = 4
     * 输出：7
     * 解释：
     * 所有可能的组合为：
     * (1, 1, 1, 1)
     * (1, 1, 2)
     * (1, 2, 1)
     * (1, 3)
     * (2, 1, 1)
     * (2, 2)
     * (3, 1)
     * 请注意，顺序不同的序列被视作不同的组合。
     *
     * <p>
     * 数据范围：
     * 1 <= nums.length <= 200
     * 1 <= nums[i] <= 1000
     * nums 中的所有元素 互不相同
     * 1 <= target <= 1000
     *
     * 进阶：如果给定的数组中含有负数会发生什么？问题会产生何种变化？如果允许负数出现，需要向题目中添加哪些限制条件？
     */

    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;// target == 0时，不添加任何元素，因此只有一种
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (num <= i) {
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }

    @Test
    public void test() {
        int[] nums = {1, 2, 3};
        System.out.println(combinationSum4(nums, 4));
    }
}
