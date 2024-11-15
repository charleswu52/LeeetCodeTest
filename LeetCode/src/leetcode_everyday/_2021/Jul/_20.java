package leetcode_everyday._2021.Jul;

import java.util.Arrays;

/**
 * @author WuChao
 * @create 2021/7/20 8:10
 */
public class _20 {
    /**
     * 每日一题：2021/7/20
     * 1877.数组中最大数对和的最小值
     * 难度: medium
     * <p>
     * 一个数对 (a,b) 的 数对和 等于 a + b 。最大数对和 是一个数对数组中最大的 数对和 。
     * <p>
     * 比方说，如果我们有数对 (1,5) ，(2,3) 和 (4,4)，最大数对和 为 max(1+5, 2+3, 4+4) = max(6, 5, 8) = 8 。
     * 给你一个长度为 偶数 n 的数组 nums ，请你将 nums 中的元素分成 n / 2 个数对，使得：
     * <p>
     * nums 中每个元素 恰好 在 一个 数对中，且
     * 最大数对和 的值 最小 。
     * 请你在最优数对划分的方案下，返回最小的 最大数对和 。
     * <p>
     * 提示：
     * <p>
     * n == nums.length
     * 2 <= n <= 10^5
     * n 是 偶数 。
     * 1 <= nums[i] <= 10^5
     * 示例
     * <p>
     * 示例 1：
     * 输入：nums = [3,5,2,3]
     * 输出：7
     * 解释：数组中的元素可以分为数对 (3,3) 和 (5,2) 。
     * 最大数对和为 max(3+3, 5+2) = max(6, 7) = 7 。
     * <p>
     * 示例 2：
     * 输入：nums = [3,5,4,2,4,6]
     * 输出：8
     * 解释：数组中的元素可以分为数对 (3,5)，(4,4) 和 (6,2) 。
     * 最大数对和为 max(3+5, 4+4, 6+2) = max(8, 8, 8) = 8 。
     */

    /*
    题目解析：
    刚看半天没懂是什么意思，求最小的最大数对和
    思路：排序 + 双指针
    数组排序，将最大值和最小值相加，然后逐步向中间靠拢，使用双指针即可
     */

    public int minPairSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int res = 0;
        int left = 0, right = n - 1;
        while (left < right) {
            res = Math.max(nums[left] + nums[right], res);
            left++;
            right--;
        }
        return res;

    }
}
