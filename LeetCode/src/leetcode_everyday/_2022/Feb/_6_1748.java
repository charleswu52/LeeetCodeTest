package leetcode_everyday._2022.Feb;

import com.oracle.deploy.update.UpdateInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WuChao
 * @create 2022/2/6 9:02
 */
public class _6_1748 {
    /**
     * 每日一题：2022/2/6
     * <p>
     * 1748. 唯一元素的和
     * <p>
     * 难度：easy
     * <p>
     * 给你一个整数数组 nums 。数组中唯一元素是那些只出现 恰好一次 的元素。
     * <p>
     * 请你返回 nums 中唯一元素的 和 。
     * <p>
     * 输入：nums = [1,2,3,2]
     * 输出：4
     * 解释：唯一元素为 [1,3] ，和为 4 。
     * <p>
     * 范围
     * <p>
     * 1 <= nums.length <= 100
     * 1 <= nums[i] <= 100
     */


    /*
    思路：哈希表
     */
    public int sumOfUnique(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int res = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                res += entry.getKey();
            }
        }
        return res;
    }
}
