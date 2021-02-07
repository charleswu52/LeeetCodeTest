package com.leetcodetest.Feb;

public class _7 {
    /**
     * 每日一题：2021/2/7
     * 665. 非递减数列
     * 难度: medium
     * 给你一个长度为 n 的整数数组，请你判断在 最多 改变 1 个元素的情况下，该数组能否变成一个非递减数列。
     * 我们是这样定义一个非递减数列的： 对于数组中所有的 i (0 <= i <= n-2)，总满足 nums[i] <= nums[i + 1]。
     * <p>
     * 示例：
     * 输入: nums = [4,2,3]
     * 输出: true
     * 解释: 你可以通过把第一个4变成1来使得它成为一个非递减数列。
     * <p>
     * 数据范围：
     * 1 <= n <= 10 ^ 4
     * - 10 ^ 5 <= nums[i] <= 10 ^ 5
     */

    public boolean checkPossibility(int[] nums) {
        int len = nums.length;
        boolean res = true;
        if (len < 2) {
            return res;
        }
        int count = 1;
        for (int i = 1; i < len; i++) {
            if (nums[i] < nums[i - 1]) {
                if (count > 0) {
                    count--;
                    if (i == 1) {
                        nums[0] = nums[i];
                    } else if (nums[i - 2] > nums[i]) {
                        nums[i] = nums[i - 1];
                    } else {
                        nums[i - 1] = nums[i];
                    }
                } else {
                    res = false;
                    break;
                }
            }
        }
        return res;

    }

    public void _21_2_7() {
        int[] nums = {-1, 4, 2, 3};
        System.out.println(checkPossibility(nums));

    }
}
