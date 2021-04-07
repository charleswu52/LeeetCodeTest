package BinarySearchFramework;

/**
 * @author WuChao
 * @since 2021/4/7 下午7:24
 */
public class LC81 {
    /**
     * 二分搜索专题：LeetCode 81. 搜索旋转排序数组 II
     * 难度: medium
     * <p>
     * <p>
     * 已知存在一个按非降序排列的整数数组 nums ，数组中的值不必互不相同。
     * <p>
     * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转 ，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,4,4,5,6,6,7] 在下标 5 处经旋转后可能变为 [4,5,6,6,7,0,1,2,4,4] 。
     * <p>
     * 给你 旋转后 的数组 nums 和一个整数 target ，请你编写一个函数来判断给定的目标值是否存在于数组中。如果 nums 中存在这个目标值 target ，则返回 true ，否则返回 false
     *
     *
     * <p>
     * 示例：
     * 输入：nums = [2,5,6,0,0,1,2], target = 0
     * 输出：true
     *
     * <p>
     * 数据范围：
     * 1 <= nums.length <= 5000
     * -104 <= nums[i] <= 104
     * 题目数据保证 nums 在预先未知的某个下标上进行了旋转
     * -104 <= target <= 104
     */

    public boolean search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target) {
                return true;
            }
            if (nums[left] == nums[mid]) {
                left ++;
                continue;
            }
            if (nums[mid] > nums[left]) {
                if (nums[mid] > target & target >= nums[left]) {
                    right = mid - 1;
                } else if (target > nums[mid] || target < nums[left]) {
                    left = mid + 1;
                }
            } else if (nums[mid] < nums[left]) {
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else if (target < nums[mid] || target > nums[right]) {
                    right = mid - 1;
                }
            }
        }
        return false;

    }
}