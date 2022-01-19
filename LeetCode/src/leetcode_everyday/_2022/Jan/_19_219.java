package leetcode_everyday._2022.Jan;

import java.util.HashSet;
import java.util.Set;

/**
 * @author WuChao
 * @create 2022/1/19 9:23
 */
public class _19_219 {
    /**
     * 每日一题：2022/1/19
     * <p>
     * 219. 存在重复元素 II
     * <p>
     * 难度：hard
     * <p>
     * 给你一个整数数组 nums 和一个整数 k ，判断数组中是否存在两个 不同的索引 i 和 j ，满足 nums[i] == nums[j] 且 abs(i - j) <= k 。
     * 如果存在，返回 true ；否则，返回 false 。
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [1,2,3,1], k = 3
     * <p>
     * 输出：true
     * <p>
     * 范围
     * <p>
     * 1 <= nums.length <= 10^5
     * -109 <= nums[i] <= 10^9
     * 0 <= k <= 105
     */

    /*
    思路： 滑动窗口
    以 k 大小为单位滑动窗口，判断窗口内是否有相同的数出现，使用 set 集合进行存储
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        int len = nums.length;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < len; i++) {
            if (i > k) {
                set.remove(nums[i - k - 1]);
            }
            if (set.contains(nums[i])) {
                return true;
            }
            set.add(nums[i]);
        }
        return false;
    }
}
