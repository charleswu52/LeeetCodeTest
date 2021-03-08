package leetcodetest.Feb;

import java.util.TreeMap;

public class _21 {
    /**
     * 每日一题：2021/2/21
     * 1438. 绝对差不超过限制的最长连续子数组
     * 难度: medium
     * 给你一个整数数组 nums ，和一个表示限制的整数 limit，请你返回最长连续子数组的长度，该子数组中的任意两个元素之间的绝对差必须小于或者等于 limit 。
     * 如果不存在满足条件的子数组，则返回 0 。
     * <p>
     * 示例：
     * 输入：nums = [8,2,4,7], limit = 4
     * 输出：2
     * 解释：所有子数组如下：
     * [8] 最大绝对差 |8-8| = 0 <= 4.
     * [8,2] 最大绝对差 |8-2| = 6 > 4.
     * [8,2,4] 最大绝对差 |8-2| = 6 > 4.
     * [8,2,4,7] 最大绝对差 |8-2| = 6 > 4.
     * [2] 最大绝对差 |2-2| = 0 <= 4.
     * [2,4] 最大绝对差 |2-4| = 2 <= 4.
     * [2,4,7] 最大绝对差 |2-7| = 5 > 4.
     * [4] 最大绝对差 |4-4| = 0 <= 4.
     * [4,7] 最大绝对差 |4-7| = 3 <= 4.
     * [7] 最大绝对差 |7-7| = 0 <= 4.
     * 因此，满足题意的最长子数组的长度为 2 。
     * <p>
     * 数据范围：
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * 0 <= limit <= 10^9
     */


    /*
    题目解析：
    题意：求一个最长的子数组，该子数组内的最大值和最小值的差不超过 limit。
    显然还是用滑动窗口的方法。滑动窗口的限制条件是：窗口内最大值和最小值的差不超过 limit。

    难点分析：
    最大的难点在于快速地求滑动窗口内的最大值和最小值。
    如果遍历求滑动窗口内的最大值和最小值，时间复杂度是 O(k)，肯定会超时。
    降低时间复杂度的一个绝招就是增加空间复杂度：利用更好的数据结构。是的，我们的目的是快速让一组数据有序，那就寻找一个内部是有序的数据结构呗！
    在 Java 中 TreeSet 是有序的去重集合，TreeMap 是 key 有序的哈希表，它们也是基于红黑树实现的。

    本题要点：
    本题需要保存滑动窗口内的所有元素（可能含有重复元素），可以使用的 C++ 的 multiset/map 与 Java 中的 TreeMap。
    当频繁的插入和删除元素时，multiset/map 和 TreeMap 等有序的数据结构能够在在 O(log(k)) 的时间复杂度内调整 BST，从而维护结构的有序性。
    multiset 和 TreeMap 都提供了获取第一个元素和最后一个元素的函数，也就能在 O(1) 的时间内获取滑动窗口内最小值和最大值。

    代码实现：
    1.使用 left 和 right 两个指针，分别指向滑动窗口的左右边界；定义 multiset 保存滑动窗口的所有元素；
    2.right 主动右移：right 指针每次移动一步，把 A[right] 放入滑动窗口；
    3.left 被动右移：判断此时窗口内最大值和最小值的差，如果大于 limit，则 left 指针被迫右移，直至窗口内最大值和最小值的差小于等于 limit 为止；
      left 每次右移之前，需要把 A[left] 从 multiset 中减去一次。
    4.滑动窗口长度的最大值就是所求。
     */
    public int longestSubarray(int[] nums, int limit) {
        TreeMap<Integer, Integer> store = new TreeMap<>();  //  存储滑动窗口中每个元素和对应的出现次数
        int n = nums.length, left = 0, right = 0;
        int res = 0;
        int count = 0;
        while (right < n) {
            store.put(nums[right], store.getOrDefault(nums[right], 0) + 1);
            while (store.lastKey() - store.firstKey() > limit) {
                store.put(nums[left], store.get(nums[left]) - 1);
                if (store.get(nums[left]) == 0) {
                    store.remove(nums[left]);
                }
                left++;
            }

            res = Math.max(res, right - left + 1);
            right++;
        }
        return res;

    }

    public void _21_2_21() {
        int[] nums = {8, 2, 4, 7};
        int limit = 4;
        System.out.println(longestSubarray(nums, limit));
    }
}
