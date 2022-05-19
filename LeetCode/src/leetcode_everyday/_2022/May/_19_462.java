package leetcode_everyday._2022.May;

import java.util.Arrays;

/**
 * @author WuChao
 * @create 2022/5/19 8:37
 */
public class _19_462 {
    /**
     * 每日一题：2022/5/19
     * <p>
     * 462. 最少移动次数使数组元素相等 II
     * <p>
     * 难度：medium
     * <p>
     * 给你一个长度为 n 的整数数组 nums ，返回使所有数组元素相等需要的最少移动数。
     *
     * 在一步操作中，你可以使数组中的一个元素加 1 或者减 1 。
     * <p>
     * 示例:
     * <p>
     * 输入：nums = [1,2,3]
     *
     * 输出：2
     *
     * 解释：
     * 只需要两步操作（每步操作指南使一个元素加 1 或减 1）：
     * [1,2,3]  =>  [2,2,3]  =>  [2,2,2]
     * <p>
     * n == nums.length
     * 1 <= nums.length <= 105
     * -109 <= nums[i] <= 109
     */

    /*
    思路：找中位数
     */
    public int minMoves2(int[] nums) {
        int n = nums.length;
        int mid = n / 2;
        Arrays.sort(nums);
        int temp = nums[mid];
        int res = 0;
        for (int i = 0; i < n; i++) {
            res += Math.abs(nums[i] - temp);
        }
        return res;
    }
}
