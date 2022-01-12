package leetcode_everyday._2022.Jan;

/**
 * @author WuChao
 * @create 2022/1/12 22:15
 */
public class _12_334 {
    /**
     * 每日一题：2022/1/12
     * <p>
     * 334. 递增的三元子序列
     * <p>
     * 难度：medium
     * <p>
     * 给你一个整数数组 nums ，判断这个数组中是否存在长度为 3 的递增子序列。
     *
     * 如果存在这样的三元组下标 (i, j, k) 且满足 i < j < k ，使得 nums[i] < nums[j] < nums[k] ，返回 true ；否则，返回 false 。
     *
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [1,2,3,4,5]
     *
     * 输出：true
     *
     * 解释：任何 i < j < k 的三元组都满足题意
     *
     * 示例 2：
     *
     * 输入：nums = [2,1,5,0,4,6]
     *
     * 输出：true
     *
     * 解释：三元组 (3, 4, 5) 满足题意，因为 nums[3] == 0 < nums[4] == 4 < nums[5] == 6
     * <p>
     * 范围
     * <p>
     * 1 <= nums.length <= 5 * 10^5
     * -2^31 <= nums[i] <= 2^31 - 1
     *
     * 进阶
     *
     * 你能实现时间复杂度为 O(n) ，空间复杂度为 O(1) 的解决方案吗？
     **/

    /*
    思路：贪心算法
     */
    public boolean increasingTriplet(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return false;
        }
        int first = nums[0], second = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            int num = nums[i];
            if (num > second) {
                return true;
            } else if (num > first) {
                second = num;
            } else {
                first = num;
            }

        }
        return false;


    }
}
