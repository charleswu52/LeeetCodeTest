package leetcode_everyday.May;

/**
 * @author WuChao
 * @since 2021/5/7 上午8:26
 */
public class _7 {
    /**
     * 每日一题：2021/5/7
     * 1486. 数组异或操作
     * 难度: easy
     * 给你两个整数，n 和 start 。
     *
     * 数组 nums 定义为：nums[i] = start + 2*i（下标从 0 开始）且 n == nums.length 。
     *
     * 请返回 nums 中所有元素按位异或（XOR）后得到的结果。
     *
     * <p>
     * 示例：
     * 输入：n = 5, start = 0
     * 输出：8
     * 解释：数组 nums 为 [0, 2, 4, 6, 8]，其中 (0 ^ 2 ^ 4 ^ 6 ^ 8) = 8 。
     *      "^" 为按位异或 XOR 运算符。
     *
     * <p>
     * 数据范围：
     * 1 <= n <= 1000
     * 0 <= start <= 1000
     * n == nums.length
     */

    public int xorOperation(int n, int start) {
        if (n == 1) {
            return start;
        }
        int res = start;
        for (int i = 1; i < n; i++) {
            res = res ^(start + 2 * i);
        }
        return res;

    }
}
