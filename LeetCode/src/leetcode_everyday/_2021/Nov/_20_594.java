package leetcode_everyday._2021.Nov;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WuChao
 * @create 2021/11/20 9:46
 */
public class _20_594 {
    /**
     * 每日一题：2021/11/20
     * <p>
     * 594. 最长和谐子序列
     * <p>
     * 难度：medium
     * <p>
     * 和谐数组是指一个数组里元素的最大值和最小值之间的差别 正好是 1 。
     *
     * 现在，给你一个整数数组 nums ，请你在所有可能的子序列中找到最长的和谐子序列的长度。
     *
     * 数组的子序列是一个由数组派生出来的序列，它可以通过删除一些元素或不删除元素、且不改变其余元素的顺序而得到。
     *
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [1,3,2,2,5,2,3,7]
     *
     * 输出：5
     *
     * 解释：最长的和谐子序列是 [3,2,2,2,3]
     * <p>
     * 范围
     * <p>
     * 1 <= nums.length <= 2 * 10^4
     * -10^9 <= nums[i] <= 10^9
     */

    /*
    思路：使用哈希表统计每个数字出现的次数
     */
    public int findLHS(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int ans = 0;
        for (int key : map.keySet()) {
            int value = map.get(key);
            int t1 = 0, t2 = 0;
            if (map.containsKey(key - 1)) {
                t1= value + map.get(key - 1);
            }
            if (map.containsKey(key + 1)) {
                t2 = value + map.get(key + 1);
            }
            ans = Math.max(ans, Math.max(t1, t2));
        }
        return ans;

    }
}
