package leetcode_hot100.top40;

import org.junit.Test;

/**
 * @author WuChao
 * @create 2021/7/10 9:37
 */
public class _55 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 55. 跳跃游戏
     * 难度：medium
     * <p>
     * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
     *
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     *
     * 判断你是否能够到达最后一个下标
     * <p>
     * 示例
     * <p>
     * 输入：nums = [2,3,1,1,4]
     * 输出：true
     * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
     *
     * 输入：nums = [3,2,1,0,4]
     * 输出：false
     * 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
     * <p>
     * 范围
     * 1 <= nums.length <= 3 * 10^4
     * 0 <= nums[i] <= 10^5
     */

    public boolean canJump(int[] nums) {
        if (nums.length < 2) {
            return true;
        }
        int len = nums.length - 1;
        int[] ints = new int[len];
        for (int i = 0; i < len; i++) {
            ints[i] = nums[i] + i;
        }
        int maxIdx = ints[0];
        for (int i = 1; i < len && i <= maxIdx; i++) {
            maxIdx = Math.max(maxIdx, ints[i]);
        }
        return maxIdx >= len;

    }

    @Test
    public void test() {
        int[] nums = {1, 2, 3};
        System.out.println(canJump(nums));
    }
}
