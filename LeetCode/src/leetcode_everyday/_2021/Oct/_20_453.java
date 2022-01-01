package leetcode_everyday._2021.Oct;

/**
 * @author WuChao
 * @create 2021/10/20 8:28
 */
public class _20_453 {
    /**
     * 每日一题：2021/10/20
     * <p>
     * 211. 添加与搜索单词 - 数据结构设计453. 最小操作次数使数组元素相等
     * <p>
     * 难度：medium
     * <p>
     * 给你一个长度为 n 的整数数组，每次操作将会使 n - 1 个元素增加 1 。返回让数组所有元素相等的最小操作次数。
     * <p>
     * 示例：
     * <p>
     * n == nums.length
     * 1 <= nums.length <= 105
     * -109 <= nums[i] <= 109
     * 答案保证符合 32-bit 整数
     *
     */


    /*
    思路：数学题
    推导思路 将原数组记作A, 其长度记作n。
            将数组的元素总和记作sum0，我们需要移动次数为k，最后数组所有元素都相等为x，那么整体数组总和将会增加 k⋅(n−1)，于是有:
                sum0 + k⋅(n−1) = x⋅n (1)
            我们将数组中最小的元素记作 A_min, 那么最小元素移动的次数必然为k, 最小元素从 A_min 变为x, 故 k = x - A_min，即
                x = k + n⋅A_min (2)
    将方程(2)带入方程(1),化简得: k = sum0 - n⋅A_min
     */
    public int minMoves(int[] nums) {
        int n = nums.length;
        int sum = 0, min = Integer.MAX_VALUE;
        for (int num : nums) {
            sum += num;
            min = Math.min(num, min);
        }
        return sum - n * min;

    }
}
