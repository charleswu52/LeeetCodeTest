package leetcode_hot100.top100;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WuChao
 * @create 2021/7/28 13:36
 */
public class _560 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 560. 和为K的子数组
     * 难度：medium
     * <p>
     * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
     * <p>
     * 示例 :
     * 输入:nums = [1,1,1], k = 2
     * 输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
     *
     * <p>
     * 说明 :
     * 数组的长度为 [1, 20,000]。
     * 数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。
     *
     */

    /*
    思路： 前缀和 + 哈希表
    考虑以 i 结尾的和为 k 的连续子数组个数只要统计有多少个前缀和为 pre[i]−k 的 pre[j] 即可。
    用哈希表保存前缀和以及其对应的出现次数
     */

    public int subarraySum(int[] nums, int k) {
        int n = nums.length, pre = 0, count = 0;
        Map<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, 1); // 不要忘记该初始化条件
        for (int i = 0; i < n; i++) {
            pre += nums[i];
            if (preSum.containsKey(pre - k)) {
                count += preSum.get(pre - k);
            }
            preSum.put(pre, preSum.getOrDefault(pre, 0) + 1);
        }
        return count;

    }
}
