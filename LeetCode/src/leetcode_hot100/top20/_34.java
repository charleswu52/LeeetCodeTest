package leetcode_hot100.top20;

/**
 * @author WuChao
 * @create 2021/7/6 13:03
 */
public class _34 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 34. 在排序数组中查找元素的第一个和最后一个位置
     * 难度：medium
     * <p>
     * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
     *
     * 如果数组中不存在目标值 target，返回 [-1, -1]。
     *
     * 进阶：
     *
     * 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
     * <p>
     * 示例
     * <p>
     * 输入：nums = [5,7,7,8,8,10], target = 8
     * 输出：[3,4]
     *
     * 输入：nums = [5,7,7,8,8,10], target = 6
     * 输出：[-1,-1]
     * <p>
     * 范围
     * 0 <= nums.length <= 105
     * -109 <= nums[i] <= 109
     * nums 是一个非递减数组
     * -109 <= target <= 109
     */
    /*
    思路：二分搜索  复习

     */

    public int[] searchRange(int[] nums, int target) {
        if (nums.length < 1) {
            return new int[]{-1, -1};
        }
        int left = 0, right = nums.length - 1;
        int[] ans = new int[2];
        // 计算下边界
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        ans[0] = left;

        // 计算上边界
        left = 0;
        right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        ans[1] = right;
        if (ans[0] > ans[1]) {
            return new int[]{-1, -1};
        }
        return ans;


    }
}
