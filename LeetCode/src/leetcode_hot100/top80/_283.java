package leetcode_hot100.top80;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author WuChao
 * @create 2021/7/21 10:44
 */
public class _283 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 283. 移动零
     * 难度：easy
     * <p>
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     *
     * 示例:
     *
     * 输入: [0,1,0,3,12]
     * 输出: [1,3,12,0,0]
     * 说明:
     *
     * 必须在原数组上操作，不能拷贝额外的数组。
     * 尽量减少操作次数。
     */

    /*
    双指针
     */
    public void moveZeroes(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return;
        }
        int left= 0, right = 0;
        while (right < n) {
            if (nums[right] != 0) {
                swap(nums, left, right);
                left++;
            }
            right++;
        }
    }

    public void swap(int[] nums, int x, int y) {
        int t = nums[x];
        nums[x] = nums[y];
        nums[y] = t;
    }

    @Test
    public void test() {
        int[] nums = {0, 1, 0, 3, 12};
        moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }
}
