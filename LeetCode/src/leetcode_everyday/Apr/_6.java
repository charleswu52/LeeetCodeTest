package leetcode_everyday.Apr;

import org.junit.Test;

/**
 * @author WuChao
 * @since 2021/4/6 上午8:40
 */
public class _6 {
    /**
     * 每日一题：2021/4/6
     * 80. 删除有序数组中的重复项 II
     * 难度: medium
     * <p>
     * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 最多出现两次 ，返回删除后数组的新长度。
     * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     *
     *
     * <p>
     * 示例：
     * 输入：nums = [1,1,1,2,2,3]
     * 输出：5, nums = [1,1,2,2,3]
     * 解释：函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。 不需要考虑数组中超出新长度后面的元素。
     *
     * <p>
     * 数据范围：
     * 0 <= nums.length <= 3 * 104
     * -104 <= nums[i] <= 104
     * nums 已按升序排列
     */


    /**
     * 思路：双指针解法
     */
    public int removeDuplicates(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return len;
        }
        int slow = 2, fast = 2;//上面已经对数组长度进行判别，快慢指针直接从2开始
        while (fast < len) {
            if (nums[slow - 2] != nums[fast]) { //因为题目要求最多两个数相同，所以这么判断
                // 如果nums[slow - 2] == nums[fast]，代表已经有两个数相等，此时nums[fast]
                // 对应的数值不能放进结果之中。反之，如果nums[slow - 2] != nums[fast]，
                // 那么nums[fast]可以放进nums[slow]中，并且slow++，记录结果的长度。
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;//不管怎样，快指针都是要向前遍历各个元素的
        }
        return slow; // 返回结果的长度，即slow
    }

    @Test
    public void test() {
        int[] nums = {0,0,1,1,1,1,2,3,3};
        System.out.println(removeDuplicates(nums));

    }
}
