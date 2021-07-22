package leetcode_hot100.top80;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author WuChao
 * @create 2021/7/18 10:14
 */
public class _215 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 215. 数组中的第K个最大元素
     * 难度：medium
     * <p>
     * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
     * <p>
     * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
     * <p>
     * 示例：
     * 输入: [3,2,1,5,6,4] 和 k = 2
     * 输出: 5
     * <p>
     * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
     * 输出: 4
     * <p>
     * 数据范围:
     * 1 <= k <= nums.length <= 104
     * -104 <= nums[i] <= 104
     */

    /*
    思路： 使用快排中的切分思想
    切分函数可以找到比当前值小的元素与大于当前值的元素的分界线，即第k个元素
     */
    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        return nums[n - k];
    }

    @Test
    public void test() {

    }
}
