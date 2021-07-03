package leetcode_everyday.Apr;

import org.junit.Test;

/**
 * @author WuChao
 * @since 2021/4/7 上午7:54
 */
public class _7_1 {
    /**
     * 每日一题：2021/4/7
     * 81. 搜索旋转排序数组 II
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

    /*
    题意解析：
    与 _7_2 不同的是，这里的旋转数组中的数不是唯一的，因此在二分的时候就会出现无法判断左右部分哪个是有序的情况
    因此可能会出现左右边界值都等于mid位置的值的情况，这时候无法划分左右边界，因此需要left++,right--
    在缩小的新的区间内再判断
     */
    public boolean search(int[] nums, int target) {
        int len = nums.length;
        if (len == 1) {
            return nums[0] == target;
        }
        int left = 0, right = len - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return true;
            }
            // 如果出现左右边界与mid的置相等的情况时，就将左边界+1，右边界-1，在新的区间内再判断
            if (nums[left] == nums[mid] && nums[mid] == nums[right]) {
                left++;
                right--;
            } else if (nums[left] <= nums[mid]) { // 左边是个有序数组
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else { // 右边是有序的
                if (nums[mid] < target && target <= nums[len - 1]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return false;

    }

    @Test
    public void test() {
        int[] nums = {1, 0, 1, 1, 1};
        int target = 0;
        System.out.println(search(nums, target));

    }
}
