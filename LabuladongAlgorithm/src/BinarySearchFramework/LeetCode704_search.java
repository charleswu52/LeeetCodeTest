package BinarySearchFramework;

/**
 * @author WuChao
 * @since 2021/3/17 上午11:02
 */
public class LeetCode704_search {
    /**
     * 二分搜索学习框架
     * 案例
     * 704. 二分查找
     * 难度：easy
     * <p>
     * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，
     * 写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
     *
     * <p>
     * 示例：
     * 输入: nums = [-1,0,3,5,9,12], target = 9
     * 输出: 4
     * 解释: 9 出现在 nums 中并且下标为 4
     * <p>
     * 数据范围：
     * 你可以假设 nums 中的所有元素是不重复的。
     * n 将在 [1, 10000]之间。
     * nums 的每个元素都将在 [-9999, 9999]之间。
     */

    public int search(int[] nums, int target) {
        int n = nums.length;
        if (n == 1) {
            if (nums[0] == target) {
                return 0;
            } else return -1;
        }
        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;

    }
}
