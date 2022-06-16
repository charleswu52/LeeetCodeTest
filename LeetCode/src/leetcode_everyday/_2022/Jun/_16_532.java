package leetcode_everyday._2022.Jun;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WuChao
 * @create 2022/6/16 21:36
 */
public class _16_532 {
    /**
     * 每日一题：2022/6/16
     * <p>
     * 532. 数组中的 k-diff 数对
     * <p>
     * 难度：medium
     * <p>
     * 给你一个整数数组 nums 和一个整数 k，请你在数组中找出 不同的 k-diff 数对，并返回不同的 k-diff 数对 的数目。
     * <p>
     * k-diff 数对定义为一个整数对 (nums[i], nums[j]) ，并满足下述全部条件：
     * <p>
     * 0 <= i, j < nums.length
     * i != j
     * nums[i] - nums[j] == k
     * 注意，|val| 表示 val 的绝对值。
     * <p>
     * 示例
     * <p>
     * 输入：nums = [3, 1, 4, 1, 5], k = 2
     * 输出：2
     * 解释：数组中有两个 2-diff 数对, (1, 3) 和 (3, 5)。
     * 尽管数组中有两个 1 ，但我们只应返回不同的数对的数量。
     * <p>
     * 范围
     * <p>
     * 1 <= nums.length <= 10^4
     * -10^7 <= nums[i] <= 10^7
     * 0 <= k <= 10^7
     */

    /*
    思路；使用 Map
     */
    public int findPairs(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        if (k < 0) {
            return res;
        }
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            map.putIfAbsent(nums[i], 0);
            map.put(nums[i], map.get(nums[i]) + 1);
        }
        for (int key : map.keySet()) {
            if (k == 0) {
                if (map.get(key) > 1) {
                    res++;
                }
            } else if (map.containsKey(key + k)) {
                res++;
            }
        }
        return res;

    }
}
