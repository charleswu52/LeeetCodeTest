package BinarySearchFramework;

import org.junit.Test;

/**
 * @author WuChao
 * @since 2021/4/7 上午10:37
 */
public class LC35 {
    /**
     * 二分搜索专题：LeetCode 35. 搜索插入位置
     * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     * <p>
     * 你可以假设数组中无重复元素。
     * <p>
     * 示例：
     * 输入: [1,3,5,6], 0
     * 输出: 0
     * <p>
     * 数据范围：
     */

    public int searchInsert(int[] nums, int target) {
        if (nums.length < 1) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0] <= target ? 0 : 1;
        }
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;

    }

    @Test
    public void test() {
        int[] nums = {1, 3, 5, 6};
        int target = 2;
        System.out.println(searchInsert(nums, target));
    }
}
