package leetcode_everyday._2022.Feb;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WuChao
 * @create 2022/2/9 9:36
 */
public class _9_2006 {
    /**
     * 每日一题：2022/2/9
     * <p>
     * 2006. 差的绝对值为 K 的数对数目
     * <p>
     * 难度：easy
     * <p>
     * 给你一个整数数组 nums 和一个整数 k ，请你返回数对 (i, j) 的数目，满足 i < j 且 |nums[i] - nums[j]| == k 。
     *
     * |x|的值定义为：
     *
     * 如果 x >= 0 ，那么值为 x 。
     * 如果 x < 0 ，那么值为 -x 。
     * <p>
     * 输入：nums = [1,2,2,1], k = 1
     * 输出：4
     * 解释：差的绝对值为 1 的数对为：
     * - [1,2,2,1]
     * - [1,2,2,1]
     * - [1,2,2,1]
     * - [1,2,2,1]
     * <p>
     * 范围
     * <p>
     * 1 <= nums.length <= 200
     * 1 <= nums[i] <= 100
     * 1 <= k <= 99
     */

    /*
    思路：
     */
    public int countKDifference(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int res = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int key = entry.getKey();
            if (map.containsKey(key + k)) {
                res += map.get(key + k) * map.get(key);
            }
            if (map.containsKey(key - k)) {
                res += map.get(key - k) * map.get(key);
            }
            map.put(key, 0);
        }
        return res;
    }

    @Test
    public void test() {
        int[] nums = {3, 2, 1, 5, 4};
        int k = 2;
        System.out.println(countKDifference(nums, k));
    }

}
