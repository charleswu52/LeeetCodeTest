package leetcode_everyday.Apr;

import org.junit.Test;

/**
 * @author WuChao
 * @since 2021/4/9 上午7:56
 */
public class _9 {
    /**
     * 每日一题：2021/4/9
     * 154. 寻找旋转排序数组中的最小值 II
     * 难度: hard
     * <p>
     * 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,4,4,5,6,7] 在变化后可能得到：
     * 若旋转 4 次，则可以得到 [4,5,6,7,0,1,4]
     * 若旋转 7 次，则可以得到 [0,1,4,4,5,6,7]
     * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
     * <p>
     * 给你一个可能存在 重复 元素值的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
     *
     *
     * <p>
     * 示例：
     * 输入：nums = [2,2,2,0,1]
     * 输出：0
     *
     * <p>
     * 数据范围：
     * n == nums.length
     * 1 <= n <= 5000
     * -5000 <= nums[i] <= 5000
     * nums 原来是一个升序排序的数组，并进行了 1 至 n 次旋转
     */

    /*
    二分法
     */
    public int findMin(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int left = 0, right = nums.length - 1;
        while (left <= right) {

            int mid = left + ((right - left) >> 1);
            if (nums[mid] >= nums[left]) {
                left = mid + 1;
            } else if (nums[mid] < nums[left]) {
                right = mid;
            }
        }

        return -1;

    }

    @Test
    public void test() {
        int[] nums = {3, 1, 3};
        System.out.println(findMin(nums));
    }




}
