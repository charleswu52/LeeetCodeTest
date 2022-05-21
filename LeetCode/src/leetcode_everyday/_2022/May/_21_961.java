package leetcode_everyday._2022.May;

/**
 * @author WuChao
 * @create 2022/5/21 8:40
 */
public class _21_961 {
    /**
     * 每日一题：2022/5/21
     * <p>
     * 961. 在长度 2N 的数组中找出重复 N 次的元素
     * <p>
     * 难度：easy
     * <p>
     * 给你一个整数数组 nums ，该数组具有以下属性：
     * <p>
     * nums.length == 2 * n.
     * nums 包含 n + 1 个 不同的 元素
     * nums 中恰有一个元素重复 n 次
     * 找出并返回重复了 n 次的那个元素。
     * <p>
     * 示例:
     * <p>
     * 输入：nums = [5,1,5,2,5,3,5,4]
     * <p>
     * 输出：5
     * <p>
     * 范围
     * 2 <= n <= 5000
     * nums.length == 2 * n
     * 0 <= nums[i] <= 10^4
     * nums 由 n + 1 个 不同的 元素组成，且其中一个元素恰好重复 n 次
     */

    /*
    思路1: 数学分析
     */
    public int repeatedNTimes(int[] nums) {
        int n = nums.length;
        for (int gap = 1; gap <= 3; gap++) {
            for (int i = 0; i + gap < n; i++) {
                if (nums[i] == nums[i + gap]) {
                    return nums[i];
                }
            }
        }
        return -1;

    }
}
