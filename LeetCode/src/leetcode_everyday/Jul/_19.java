package leetcode_everyday.Jul;

import java.util.Arrays;

/**
 * @author WuChao
 * @create 2021/7/19 16:55
 */
public class _19 {
    /**
     * 每日一题：2021/7/19
     * 1838. 最高频元素的频数
     * 难度: medium
     * <p>
     * 元素的 频数 是该元素在一个数组中出现的次数。
     * <p>
     * 给你一个整数数组 nums 和一个整数 k 。在一步操作中，你可以选择 nums 的一个下标，并将该下标对应元素的值增加 1 。
     * <p>
     * 执行最多 k 次操作后，返回数组中最高频元素的 最大可能频数 。
     * <p>
     * 示例:
     * <p>
     * 输入：nums = [1,2,4], k = 5
     * 输出：3
     * 解释：对第一个元素执行 3 次递增操作，对第二个元素执 2 次递增操作，此时 nums = [4,4,4] 。
     * 4 是数组中最高频元素，频数是 3 。
     * <p>
     * 输入：nums = [1,4,8,13], k = 5
     * 输出：2
     * 解释：存在多种最优解决方案：
     * - 对第一个元素执行 3 次递增操作，此时 nums = [4,4,8,13] 。4 是数组中最高频元素，频数是 2 。
     * - 对第二个元素执行 4 次递增操作，此时 nums = [1,8,8,13] 。8 是数组中最高频元素，频数是 2 。
     * - 对第三个元素执行 5 次递增操作，此时 nums = [1,4,13,13] 。13 是数组中最高频元素，频数是 2 。
     * <p>
     * 范围
     * 1 <= nums.length <= 105
     * 1 <= nums[i] <= 105
     * 1 <= k <= 105
     */

    /*
    思路：排序 + 滑动窗口
    先排序；
    只要求最大频数，那可以用数学的思路考虑，假设一个窗口内所有元素的和等于窗口最右边的数乘以窗口元素个数那么这个窗口中所有元素的都是相等的，
    频数即为窗口元素个数
    但是两者不一定是正好的，而且窗口和要加上k再判断，大于等于的话是没关系的，小于就需要缩减左端窗口，
     */
    public int maxFrequency(int[] nums, int k) {

        Arrays.sort(nums);
        int n = nums.length;
        int left = 0, right = 0;
        int res = 0;
        long sum = 0;// 防止溢出
        while (right < n) {
            sum += nums[right];
            while ((sum + k) < (long) nums[right] * (right - left + 1)) {
                sum -= nums[left];
                left++;
            }
            res = Math.max(res, right - left + 1);
            right++;
        }
        return res;
    }

}
