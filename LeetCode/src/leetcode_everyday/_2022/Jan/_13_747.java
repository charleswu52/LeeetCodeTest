package leetcode_everyday._2022.Jan;

import javax.sound.midi.Track;

/**
 * @author WuChao
 * @create 2022/1/13 22:37
 */
public class _13_747 {
    /**
     * 每日一题：2022/1/13
     * <p>
     * 747. 至少是其他数字两倍的最大数
     * <p>
     * 难度：easy
     * <p>
     * 给你一个整数数组 nums ，其中总是存在 唯一的 一个最大整数 。
     * <p>
     * 请你找出数组中的最大元素并检查它是否 至少是数组中每个其他数字的两倍 。如果是，则返回 最大元素的下标 ，否则返回 -1 。
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [3,6,1,0]
     * 输出：1
     * 解释：6 是最大的整数，对于数组中的其他整数，6 大于数组中其他元素的两倍。6 的下标是 1 ，所以返回 1 。
     * <p>
     * 示例 2：
     * <p>
     * 输入：nums = [1]
     * 输出：0
     * 解释：因为不存在其他数字，所以认为现有数字 1 至少是其他数字的两倍。
     * <p>
     * 范围
     * <p>
     * 1 <= nums.length <= 50
     * 0 <= nums[i] <= 100
     * nums 中的最大元素是唯一的
     **/

    /*
    思路：简单程序模拟
     */
    public int dominantIndex(int[] nums) {
        int n = nums.length;
        int firstMax = 0, secondMax = 0;
        int index = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] > firstMax) {
                secondMax = firstMax;
                firstMax = nums[i];
                index = i;
            } else if (nums[i] > secondMax) {
                secondMax = nums[i];
            }
        }
        if (secondMax * 2 > firstMax) {
            return -1;
        } else {
            return index;
        }

    }

}
