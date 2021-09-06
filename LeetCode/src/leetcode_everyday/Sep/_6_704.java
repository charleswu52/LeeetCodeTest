package leetcode_everyday.Sep;

/**
 * @author WuChao
 * @create 2021/9/6 9:16
 */
public class _6_704 {
    /**
     * 每日一题：2021/9/6
     * 704. 二分查找
     * 难度：easy
     * <p>
     * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，
     * 如果目标值存在返回下标，否则返回 -1。
     *
     * <p>
     * 示例 1:
     * 输入: nums = [-1,0,3,5,9,12], target = 9
     * 输出: 4
     *
     *
     * <p>
     * 提示:
     * 你可以假设 nums 中的所有元素是不重复的。
     * n 将在 [1, 10000]之间。
     * nums 的每个元素都将在 [-9999, 9999]之间。
     */

    /*
    经典二分查找
     */
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
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
        return -1;

    }
}
