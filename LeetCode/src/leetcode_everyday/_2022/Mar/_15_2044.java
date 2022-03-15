package leetcode_everyday._2022.Mar;

/**
 * @author WuChao
 * @create 2022/3/15 上午8:56
 */
public class _15_2044 {
    /**
     * 每日一题：2022/3/15
     * <p>
     * 2044. 统计按位或能得到最大值的子集数目
     * <p>
     * 难度：medium
     * <p>
     * 给你一个整数数组 nums ，请你找出 nums 子集 按位或 可能得到的 最大值 ，并返回按位或能得到最大值的 不同非空子集的数目 。
     *
     * 如果数组 a 可以由数组 b 删除一些元素（或不删除）得到，则认为数组 a 是数组 b 的一个 子集 。如果选中的元素下标位置不一样，则认为两个子集 不同 。
     *
     * 对数组 a 执行 按位或 ，结果等于 a[0] OR a[1] OR ... OR a[a.length - 1]（下标从 0 开始）。
     * <p>
     * 示例1
     * <p>
     * 输入：nums = [3,2,1,5]
     *
     * 输出：6
     *
     * 解释：子集按位或可能的最大值是 7 。有 6 个子集按位或可以得到 7 ：
     * - [3,5]
     * - [3,1,5]
     * - [3,2,5]
     * - [3,2,1,5]
     * - [2,5]
     * - [2,1,5]
     * <p>
     * 范围
     * <p>
     * 1 <= nums.length <= 16
     * 1 <= nums[i] <= 105
     */

    /*
    思路：二进制枚举
    令 n 为 nums 的长度，利用 n 不超过 16，我们可以使用一个 int 数值来代指 nums 的使用情况（子集状态）。

    假设当前子集状态为 state ，state 为一个仅考虑低 n 位的二进制数，当第 k 位为 1，代表 nums[k] 参与到当前的按位或运算，
    当第 k 位为 0，代表 nums[i] 不参与到当前的按位或运算。

    在枚举这 2^n  个状态过程中，我们使用变量 max 记录最大的按位或得分，使用 ans 记录能够取得最大得分的状态数量。
     */
    public int countMaxOrSubsets(int[] nums) {
        int n = nums.length;
        int mask = 1 << n;
        int max = 0, res = 0;
        for (int i = 0; i < mask; i++) {
            int cur = 0;
            for (int j = 0; j < n; j++) {
                if (((i >> j) & 1) == 1) {
                    cur |= nums[j];
                }
            }
            if (cur > max) {
                max = cur;
                res = 1;
            } else if (cur == max) {
                res++;
            }
        }
        return res;

    }
}
