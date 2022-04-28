package leetcode_everyday._2022.Apr;

/**
 * @author WuChao
 * @create 2022/4/28 9:48
 */
public class _28_905 {
    /**
     * 每日一题：2022/4/28
     * <p>
     * 905. 按奇偶排序数组
     * <p>
     * 难度：easy
     * <p>
     * 给你一个整数数组 nums，将 nums 中的的所有偶数元素移动到数组的前面，后跟所有奇数元素。
     * <p>
     * 返回满足此条件的 任一数组 作为答案。
     * <p>
     * 示例
     * <p>
     * 输入：nums = [3,1,2,4]
     * <p>
     * 输出：[2,4,3,1]
     * <p>
     * 解释：[4,2,3,1]、[2,4,1,3] 和 [4,2,1,3] 也会被视作正确答案。
     * <p>
     * 范围
     * <p>
     * 1 <= nums.length <= 5000
     * 0 <= nums[i] <= 5000
     */

    /*
    思路：双指针+原地遍历
     */
    public int[] sortArrayByParity(int[] nums) {
        int n = nums.length;
        int odd = 0, even = 0;
        while (even < n) {
            if (nums[even] % 2 == 0) {
                while (odd < even && odd < n) {
                    if (nums[odd] % 2 != 0) {
                        break;
                    }
                    odd++;
                }
                if (odd < even) {
                    int temp = nums[odd];
                    nums[odd] = nums[even];
                    nums[even] = temp;
                }
            }
            even++;
        }
        return nums;

    }
}
