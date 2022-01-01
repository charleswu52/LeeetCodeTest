package leetcode_everyday._2021.Feb;

public class _15 {
    /**
     * 每日一题：2021/2/15
     * 485. 最大连续1的个数
     * 难度: easy
     * 给定一个二进制数组， 计算其中最大连续1的个数。
     * <p>
     * 示例：
     *输入: [1,1,0,1,1,1]
     * 输出: 3
     * 解释: 开头的两位和最后的三位都是连续1，所以最大连续1的个数是 3.
     * <p>
     * 数据范围：
     * 输入的数组只包含 0 和1。
     * 输入数组的长度是正整数，且不超过 10,000。
     */

    /*
    思路：一次遍历
    遍历一遍数组，遇到是1的二进制数就累加，不是啦就重置重新累加
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        int maxCount = 0, count = 0;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (nums[i] == 1) {
                count++;
            } else {
                maxCount = Math.max(maxCount, count);
                count = 0;
            }
        }
        maxCount = Math.max(maxCount, count);
        return maxCount;

    }

    public void _21_2_15() {
        int[] nums = {1, 1, 0, 1, 1, 1};
        System.out.println(findMaxConsecutiveOnes(nums));

    }
}
