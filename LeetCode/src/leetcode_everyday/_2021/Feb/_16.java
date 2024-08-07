package leetcode_everyday._2021.Feb;

import java.util.Arrays;

public class _16 {
    /**
     * 每日一题：2021/2/16
     * 561. 数组拆分 I
     * 难度: easy
     * 给定长度为 2n 的整数数组 nums ，你的任务是将这些数分成 n 对, 例如 (a1, b1), (a2, b2), ..., (an, bn) ，
     * 使得从 1 到 n 的 min(ai, bi) 总和最大。
     * 返回该 最大总和 。
     * <p>
     * 示例：
     *输入：nums = [1,4,3,2]
     * 输出：4
     * 解释：所有可能的分法（忽略元素顺序）为：
     * 1. (1, 4), (2, 3) -> min(1, 4) + min(2, 3) = 1 + 2 = 3
     * 2. (1, 3), (2, 4) -> min(1, 3) + min(2, 4) = 1 + 2 = 3
     * 3. (1, 2), (3, 4) -> min(1, 2) + min(3, 4) = 1 + 3 = 4
     * 所以最大总和为 4
     * <p>
     * 数据范围：
     * 1 <= n <= 104
     * nums.length == 2 * n
     * -104 <= nums[i] <= 104
     */

    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int res = 0;
        for (int i = 0; i < nums.length; i+=2) {
            res += nums[i];
        }
        return res;
    }

    public void _21_2_16() {
        int[] nums = {6,2,6,5,1,2};
        System.out.println(arrayPairSum(nums));

    }
}
