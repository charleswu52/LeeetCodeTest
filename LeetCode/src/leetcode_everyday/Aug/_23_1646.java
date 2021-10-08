package leetcode_everyday.Aug;

/**
 * @author WuChao
 * @create 2021/8/23 9:59
 */
public class _23_1646 {
    /**
     * 每日一题：2021/8/23
     * 1646. 获取生成数组中的最大值
     * 难度：easy
     * <p>
     * 给你一个整数 n 。按下述规则生成一个长度为 n + 1 的数组 nums ：
     * <p>
     * nums[0] = 0
     * nums[1] = 1
     * 当 2 <= 2 * i <= n 时，nums[2 * i] = nums[i]
     * 当 2 <= 2 * i + 1 <= n 时，nums[2 * i + 1] = nums[i] + nums[i + 1]
     * 返回生成数组 nums 中的 最大 值。
     *
     * <p>
     * 示例 1：
     * 输入：n = 7
     * 输出：3
     * 解释：根据规则：
     * nums[0] = 0
     * nums[1] = 1
     * nums[(1 * 2) = 2] = nums[1] = 1
     * nums[(1 * 2) + 1 = 3] = nums[1] + nums[2] = 1 + 1 = 2
     * nums[(2 * 2) = 4] = nums[2] = 1
     * nums[(2 * 2) + 1 = 5] = nums[2] + nums[3] = 1 + 2 = 3
     * nums[(3 * 2) = 6] = nums[3] = 2
     * nums[(3 * 2) + 1 = 7] = nums[3] + nums[4] = 2 + 1 = 3
     * 因此，nums = [0,1,1,2,1,3,2,3]，最大值 3
     *
     * <p>
     * 注意:
     * 0 <= n <= 100
     */

    /*
    思路：模拟

     */
    public int getMaximumGenerated(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[] nums = new int[n + 1];
        nums[0] = 0;
        nums[1] = 1;
        int res = 1;
        for (int i = 1; 2 * i + 1 <= n; i++) {
            if (2 <= 2 * i && 2 * i <= n) {
                nums[2 * i] = nums[i];
                res = Math.max(res, nums[2 * i]);
            }
            if (2 <= 2 * i + 1 && 2 * i + 1 <= n) {
                nums[2 * i + 1] = nums[i] + nums[i + 1];
                res = Math.max(res, nums[2 * i + 1]);
            }
        }
        return res;


    }
}
