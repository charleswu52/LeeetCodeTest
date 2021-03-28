import org.junit.Test;

import java.util.Arrays;

/**
 * @author WuChao
 * @since 2021/3/28 上午11:52
 */
public class _57_1 {
    /**
     * 剑指 Offer 57. 和为s的两个数字
     * 难度: easy
     * <p>
     * 输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。
     *
     * <p>
     * 例如：
     * 输入：nums = [10,26,30,31,47,60], target = 40
     * 输出：[10,30] 或者 [30,10]
     *
     * <p>
     * 数据范围：
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^6
     */

    /**
     * 使用双指针的解法
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        if (target < nums[0] || nums.length < 2) {
            return new int[]{};
        }
        int i = 0, j = nums.length - 1;
        while (i < j) {
            if (nums[i] + nums[j] > target) {
                j--;
            } else if (nums[i] + nums[j] < target) {
                i++;
            } else {
                return new int[]{nums[i], nums[j]};
            }
        }
        return new int[]{};

    }

    @Test
    public void test() {
        int[] nums = {10, 26, 30, 31, 47, 60};
        int target = 40;
        System.out.println(Arrays.toString(twoSum(nums, target)));

    }

}
