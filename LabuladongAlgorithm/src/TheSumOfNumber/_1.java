package TheSumOfNumber;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WuChao
 * @create 2021/7/1 20:41
 */
public class _1 {
    /**
     * 数字之和系列
     * 1.两数之和
     * 难度：easy
     *
     * 描述
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
     *
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
     *
     * 你可以按任意顺序返回答案。
     *
     * 输入：nums = [2,7,11,15], target = 9
     * 输出：[0,1]
     * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
     *
     * 数据范围
     * 2 <= nums.length <= 104
     * -109 <= nums[i] <= 109
     * -109 <= target <= 109
     * 只会存在一个有效答案
     */

    /*
    思路分析
    最简单的思路1: 暴力搜索
    对每个数遍历其他的数，对能组成target的进行输出
    这种思路最坏情况下的时间复杂度是O(n^2)

    改进思路：哈希表
    每个数字及其对应的下标进行存储，但是也不必单独存储一遍，搜索一遍，边遍历存储边搜索即可，因为两数之和是对称的

     */

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> store = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (store.containsKey(target - nums[i])) {
                return new int[]{i, store.get(target - nums[i])};
            }
            store.put(nums[i], i);
        }
        return new int[]{};


    }
}
