package leetcode_everyday._2021.Nov;

/**
 * @author WuChao
 * @create 2021/11/6 22:02
 */
public class _6_268 {
    /**
     * 每日一题：2021/11/6
     * <p>
     * 268. 丢失的数字
     * <p>
     * 难度：easy
     * <p>
     * 给定一个包含 [0, n] 中 n 个数的数组 nums ，找出 [0, n] 这个范围内没有出现在数组中的那个数。
     * <p>
     * 示例1：
     * <p>
     * 输入：nums = [3,0,1]
     * <p>
     * 输出：2
     * <p>
     * 解释：n = 3，因为有 3 个数字，所以所有的数字都在范围 [0,3] 内。2 是丢失的数字，因为它没有出现在 nums 中。
     * <p>
     * 范围
     * <p>
     * n == nums.length
     * 1 <= n <= 104
     * 0 <= nums[i] <= n
     * nums 中的所有数字都 独一无二
     */


    /*
    思路： 作差法
     */
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int sum = n * (n + 1) / 2;
        int total = 0;
        for (int num : nums) {
            total += num;
        }
        return sum - total;
    }
}
