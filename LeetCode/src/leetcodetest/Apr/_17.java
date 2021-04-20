package leetcodetest.Apr;

import java.util.TreeSet;

/**
 * @author WuChao
 * @since 2021/4/19 上午8:06
 */
public class _17 {
    /**
     * 每日一题：2021/4/17
     * 220. 存在重复元素 III
     * 难度: medium
     * <p>
     * 给你一个整数数组 nums 和两个整数 k 和 t 。请你判断是否存在 两个不同下标 i 和 j，使得 abs(nums[i] - nums[j]) <= t ，
     * 同时又满足 abs(i - j) <= k 。
     * <p>
     * 如果存在则返回 true，不存在返回 false。
     *
     * <p>
     * 示例：
     * 输入：nums = [1,5,9,1,5,9], k = 2, t = 3
     * 输出：false
     *
     * <p>
     * 数据范围：
     * 0 <= nums.length <= 2 * 10^4
     * -2^31 <= nums[i] <= 2^31 - 1
     * 0 <= k <= 10^4
     * 0 <= t <= 2^31 - 1
     */

    /*
    题意解析:
    数组中是否存在一个大小不超过k的子数组，该子数组内的最大值和最小值的差不超过t

    思路：滑动窗口
    设置滑动窗口的固定长度为k，而本题难点在于快速寻找滑动窗口内的最大值和最小值，以及删除指定元素。
    每次都遍历窗口的话时间复制度为O(k)，会超时，解决思路就是增加空间复杂度，使用更好的数据结构。
    Java中使用TreeSet 是一个有序的哈希表
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        //1. 使用 有序集合来维护大小为k 的滑动窗口
        int n = nums.length;
        TreeSet<Long> set = new TreeSet<>(); // Long为了防止溢出

        // 2. 处理逻辑：遍历每个元素，滑动窗口包含了nums[i]的前k个元素，检查是否落在了[nums[i]-t,nums[i]+t]的区间内
        for (int i = 0; i < n; i++) {
            // 2.1 判断有序集合中是否存在符号条件的元素
            // ceiling()：方法返回在这个集合中大于或者等于给定元素的最小元素，如果不存在这样的元素,返回null.
            Long ceiling = set.ceiling((long) nums[i] - (long) t);
            if (ceiling != null && ceiling <= (long) nums[i] + (long) t) { // 存在这样的一对元素就直接返回
                return true;
            }

            // 2.2 不存在的话就添加到有序集合中
            set.add((long) nums[i]);

            // 2.3 维护大小为k的滑窗，删除超出范围的最早的元素
            if (i >= k) {
                set.remove((long) nums[i - k]);
            }
        }

        // 3.都不满足返回false

        return false;
    }
}
