package leetcode_everyday.Apr;

import org.junit.Test;

/**
 * @author WuChao
 * @since 2021/4/30 上午8:28
 */
public class _30 {
    /**
     * 每日一题：2021/4/30
     * 137. 只出现一次的数字 II
     * 难度: medium
     * <p>
     * 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
     *
     * <p>
     * 示例：
     * 输入：nums = [0,1,0,1,0,1,99]
     * 输出：99
     * <p>
     * 数据范围：
     * 1 <= nums.length <= 3 * 104
     * -231 <= nums[i] <= 231 - 1
     * nums 中，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次
     */

    /*
    本题使用哈希表的方法来做的话会很简单，但是题目进阶要求是使用O(n)的时间复杂度与O(1)的空间复杂度
     */

    /*
    参考题解：依次确定每个二进制位
    数组中的元素都在 int（即 32 位整数）范围内，因此我们可以依次计算答案的每一个二进制位是 0 还是 1。
    具体地，考虑答案的第 i 个二进制位（i 从 0 开始编号），它可能为 0 或 1。
    对于数组中非答案的元素，每一个元素都出现了 3 次，对应着第 i 个二进制位的 3 个 0 或 3 个 1，无
    论是哪一种情况，它们的和都是 3 的倍数（即和为 0 或 3）。因此：
    答案的第 i 个二进制位就是数组中所有元素的第 i 个二进制位之和除以 3 的余数。
    这样一来，对于数组中的每一个元素 x，我们使用位运算 (x >>i) & 1 得到 x 的第 i 个二进制位，
    并将它们相加再对 3 取余，得到的结果一定为 0 或 1，即为答案的第 i 个二进制位。
     */
    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int total = 0;
            for (int num : nums) {
                total += ((num >> i) & 1);// 得到i的第i个二进制位 并累加求和
            }
            if (total % 3 != 0) {
                ans |= (1 << i);
            }
        }
        return ans;
    }

    /*
    参考题解：
    参考电路设计方法 ：与或门门
     */


    @Test
    public void test() {
        int[] nums = {0, 1, 0, 1, 0, 1, 99};
        System.out.println(singleNumber(nums));

    }
}
