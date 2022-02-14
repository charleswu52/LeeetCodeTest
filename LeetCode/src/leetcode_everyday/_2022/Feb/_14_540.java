package leetcode_everyday._2022.Feb;

/**
 * @author WuChao
 * @create 2022/2/14 9:56
 */
public class _14_540 {
    /**
     * 每日一题：2022/2/14
     * <p>
     * 540. 有序数组中的单一元素
     * <p>
     * 难度：easy
     * <p>
     * 给你一个仅由整数组成的有序数组，其中每个元素都会出现两次，唯有一个数只会出现一次。
     * <p>
     * 请你找出并返回只出现一次的那个数。
     * <p>
     * 你设计的解决方案必须满足 O(log n) 时间复杂度和 O(1) 空间复杂度。
     *
     * <p>
     * 字符串 text 中的每个字母最多只能被使用一次。请你返回最多可以拼凑出多少个单词 "balloon"。
     * <p>
     * 示例
     * <p>
     * 输入: nums =  [3,3,7,7,10,11,11]
     * <p>
     * 输出: 10
     * <p>
     * 范围
     * <p>
     * 1 <= nums.length <= 10^5
     * 0 <= nums[i] <= 10^5
     */

    /*
    思路：二分搜素
    根据mid  奇偶性分析 插入的单一元素是在左还是右
     */
    public int singleNonDuplicate(int[] nums) {
        int len = nums.length;
        int left = 0, right = len - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (mid % 2 == 0) {
                if (mid + 1 < len && nums[mid] == nums[mid + 1]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            } else {
                if (mid - 1 >= 0 && nums[mid] == nums[mid - 1]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
        }
        return nums[right];
    }
}
