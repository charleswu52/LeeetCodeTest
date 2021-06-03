package leetcodetest.Jun;

import org.junit.Test;

import java.util.HashMap;

/**
 * @author WuChao
 * @since 2021/6/2 上午8:20
 */
public class _2 {
    /**
     * 每日一题：2021/6/2
     * 523. 连续的子数组和
     * 难度: medium
     * <p>
     * 给你一个整数数组 nums 和一个整数 k ，编写一个函数来判断该数组是否含有同时满足下述条件的连续子数组：
     * <p>
     * 子数组大小 至少为 2 ，且
     * 子数组元素总和为 k 的倍数。
     * 如果存在，返回 true ；否则，返回 false 。
     * <p>
     * 如果存在一个整数 n ，令整数 x 符合 x = n * k ，则称 x 是 k 的一个倍数。
     *
     * <p>
     * 示例:
     * 输入：nums = [23,2,4,6,7], k = 6
     * 输出：true
     * 解释：[2,4] 是一个大小为 2 的子数组，并且和为 6 。
     * <p>
     * 输入：nums = [23,2,6,4,7], k = 6
     * 输出：true
     * 解释：[23, 2, 6, 4, 7] 是大小为 5 的子数组，并且和为 42 。
     * 42 是 6 的倍数，因为 42 = 7 * 6 且 7 是一个整数。
     * <p>
     * 输入：nums = [23,2,6,4,7], k = 13
     * 输出：false
     * <p>
     * 数据范围：
     * 1 <= nums.length <= 105
     * 0 <= nums[i] <= 109
     * 0 <= sum(nums[i]) <= 231 - 1
     * 1 <= k <= 231 - 1
     */

    /*
    思路：
    朴素思路--肯定会超时
    进而思考 重复性的操作
    使用前缀和 求得任意区间的元素和
     */
    public boolean checkSubarraySum(int[] nums, int k) {

        for (int i = 0; i < nums.length; i++) {
            double sum = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                sum += nums[j];
                double d = sum / (double) k;
                if (d != 0 && d == (int) d) {
                    return true;
                }
            }
        }
        return false;
    }

    /*
    思路2：使用前缀和，同时使用哈希表存储每个前缀和对k的余数值以及该前缀和的下标
     */
    public boolean checkSubarraySum2(int[] nums, int k) {
        int len = nums.length;
        if (len < 2) {
            return false;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int t = 0;
        for (int i = 0; i < len; i++) {
            t = (t + nums[i]) % k;
            if (map.containsKey(t)) {
                if (i - map.get(t) >= 2) {
                    return true;
                }
            } else {
                map.put(t, i);
            }
        }
        return false;

    }

    @Test
    public void test() {
        int[] nums = {23, 2, 4, 6, 7};
        int k = 6;
        System.out.println(checkSubarraySum2(nums, k));

    }

}
