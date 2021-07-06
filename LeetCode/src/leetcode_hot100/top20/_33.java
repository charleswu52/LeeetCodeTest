package leetcode_hot100.top20;

/**
 * @author WuChao
 * @create 2021/7/6 11:15
 */
public class _33 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 33. 搜索旋转排序数组
     * 难度：medium
     * <p>
     * 整数数组 nums 按升序排列，数组中的值 互不相同 。
     *
     * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，
     * 使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
     *
     * 例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
     *
     * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
     *
     * <p>
     * 示例
     * <p>
     * 输入：nums = [4,5,6,7,0,1,2], target = 0
     * 输出：4
     *
     * 输入：nums = [4,5,6,7,0,1,2], target = 3
     * 输出：-1
     *
     * <p>
     * 范围
     * 1 <= nums.length <= 5000
     * -10^4 <= nums[i] <= 10^4
     * nums 中的每个值都 独一无二
     * 题目数据保证 nums 在预先未知的某个下标上进行了旋转
     * -10^4 <= target <= 10^4
     */

    /**
    思路：
    无脑想法：直接暴力遍历比较  时间复杂度 O(n)
    实际考察: 二分搜索
    复习：二分搜索专题
     @see BinarySearchFramework
     */

    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] >= nums[left]) { // mid和目标值同时落在一个升序数组里
                // target 落在left和mid之间
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                    // target 落在mid与right之间
                } else if (nums[mid] < target || target < nums[left]) {
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
        return -1;// 未找到
    }
}
