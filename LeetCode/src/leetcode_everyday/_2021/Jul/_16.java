package leetcode_everyday._2021.Jul;

/**
 * @author WuChao
 * @create 2021/7/16 8:12
 */
public class _16 {
    /**
     * 每日一题：2021/7/16
     * 剑指 Offer 53 - I. 在排序数组中查找数字 I
     * 难度: easy
     * <p>
     * 统计一个数字在排序数组中出现的次数。
     *
     * <p>
     * 示例
     * 输入: nums = [5,7,7,8,8,10], target = 8
     * 输出: 2
     *
     * 输入: nums = [5,7,7,8,8,10], target = 6
     * 输出: 0
     * <p>
     * 限制：
     * 0 <= 数组长度 <= 50000
     */

    /*
    与 LeetCode34 题目一致

    思路1：简单直接的一次遍历
    平均时间复杂度 O(n)

    思路2：二分查找
    找到目标值出现在数组中的最小下标和最大下标，然后计算两个的差值就是

     */
    public int search(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int left = 0, right = n - 1;
        int leftRes = 0, rightRes = 0;
        // 先计算下边界
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        leftRes = left;

        // 再计算上边界
        left = 0;
        right = n - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        rightRes = right;
        if (leftRes > rightRes) {
            return 0;
        }
        return rightRes - leftRes + 1;

    }
}
