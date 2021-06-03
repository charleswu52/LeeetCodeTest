package leetcodetest.Jun;

import org.junit.Test;

import java.util.HashMap;

/**
 * @author WuChao
 * @since 2021/6/3 上午8:28
 */
public class _3 {
    /**
     * 每日一题：2021/6/3
     * 525. 连续数组
     * 难度: medium
     * <p>
     * 给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。
     *
     * <p>
     * 示例:
     * 输入: nums = [0,1]
     * 输出: 2
     * 说明: [0, 1] 是具有相同数量0和1的最长连续子数组。
     *
     * 输入: nums = [0,1,0]
     * 输出: 2
     * 说明: [0, 1] (或 [1, 0]) 是具有相同数量0和1的最长连续子数组。
     * <p>
     * 数据范围：
     * 1 <= nums.length <= 105
     * nums[i] 不是 0 就是 1
     */

    /*
    思路： 与昨天题目类似，同样使用 前缀和 + 哈希表
    这里使用到的一个技巧就是 对1 仍然是加1 对0 变为-1
    然后遍历将前缀和以及对应的下标存到哈希表中
     */

    public int findMaxLength(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return 0;
        }
        int res = 0;
        int sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0; i < len; i++) {
            if (nums[i] == 1) {
                sum++;
            } else {
                sum--;
            }
            if (map.containsKey(sum)) {
                res = Math.max(res, i - map.get(sum));
            } else {
                map.put(sum, i);
            }
        }
        return res;
    }

    @Test
    public void test() {
        int[] nums = {0, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1};
        System.out.println(findMaxLength(nums));
    }
}
