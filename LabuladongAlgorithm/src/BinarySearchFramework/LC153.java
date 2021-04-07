package BinarySearchFramework;

/**
 * @author WuChao
 * @since 2021/4/7 下午7:35
 */
public class LC153 {
    /**
     * 二分搜索专题：LeetCode 153. 寻找旋转排序数组中的最小值
     * 难度: medium
     * <p>
     * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] 。
     * <p>
     * 请找出其中最小的元素。
     *
     *
     * <p>
     * 示例：
     * 输入：nums = [4,5,6,7,0,1,2]
     * 输出：0
     *
     * <p>
     * 数据范围：
     * 1 <= nums.length <= 5000
     * -5000 <= nums[i] <= 5000
     * nums 中的所有整数都是 唯一 的
     * nums 原来是一个升序排序的数组，但在预先未知的某个点上进行了旋转
     */

    public int findMin(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            if (nums[left] <= nums[right]) {
                return nums[left];
            }
            int mid = left + ((right - left) >> 1);
            if (nums[mid] >= nums[left]) {
                left = mid + 1;
            } else if (nums[mid] < nums[left]) {
                right = mid;
            }
        }
        return -1;

    }
}
