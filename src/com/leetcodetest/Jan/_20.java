package com.leetcodetest.Jan;

import java.util.Arrays;

public class _20 {
    /**
     * 每日一题：2021/1/20
     * 628. 三个数的最大乘积
     * 难度： easy
     * 给定一个整型数组，在数组中找出由三个数组成的最大乘积，并输出这个乘积。
     *
     * 示例：
     * 输入: [1,2,3,4]
     * 输出: 24
     * 范围说明：
     * 1. 给定的整型数组长度范围是[3,10^4]，数组中所有的元素范围是[-1000, 1000]。
     * 2. 输入的数组中任意三个数的乘积不会超出32位有符号整数的范围。
     */

    /*
    比较水 ，分析了下先排序，然后剩下就两种可能的大小
     */
    public int maximumProduct(int[] nums) {
        int res = 1;
        int n = nums.length;
        if (n == 3) {
            return nums[0] * nums[1] * nums[2];
        }
        Arrays.sort(nums);
        int res1 = nums[0] * nums[1] * nums[n - 1];
        int res2 = nums[n - 1] * nums[n - 2] * nums[n - 3];
        return Math.max(res1, res2);


    }

    public void _21_1_20() {
        int[] nums = {-4, -3,-2,-1};
        System.out.println(maximumProduct(nums));

    }
}
