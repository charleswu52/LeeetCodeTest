package leetcode_everyday.Jul;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WuChao
 * @create 2021/7/8 19:09
 */
public class _8 {
    /**
     * 每日一题：2021/7/8
     * 930. 和相同的二元子数组
     * 难度: medium
     * <p>
     * 给你一个二元数组 nums ，和一个整数 goal ，请你统计并返回有多少个和为 goal 的 非空 子数组。
     * <p>
     * 子数组 是数组的一段连续部分。
     * <p>
     * 示例
     * 输入：nums = [1,0,1,0,1], goal = 2
     * 输出：4
     * 解释：
     * 如下面黑体所示，有 4 个满足题目要求的子数组：
     * [1,0,1]
     * [1,0,1,0]
     * [0,1,0,1]
     * [1,0,1]
     * <p>
     * 输入：nums = [0,0,0,0,0], goal = 0
     * 输出：15
     *
     * <p>
     * 限制：
     * 1 <= nums.length <= 3 * 104
     * nums[i] 不是 0 就是 1
     * 0 <= goal <= nums.length
     */

    /*
    思路：前缀和 + 哈希表
    类似于两数之和，这里用不同子数组的前缀和来表示两数 即遍历nums数组，计算前缀和，用哈希表统计不同前缀和的次数
    然后 前缀和 - goal的结果存在的就累加次数
     */
    public int numSubarraysWithSum2(int[] nums, int goal) {
        Map<Integer, Integer> store = new HashMap<>();
        int sum = 0;
        int res = 0;
        for (int num : nums) {
            store.put(sum, store.getOrDefault(sum, 0) + 1);
            sum += num;
            res += store.getOrDefault(sum - goal, 0);
        }
        return res;

    }



    /*
    思路： 滑动窗口
     */
    public int numSubarraysWithSum(int[] nums, int goal) {
        int res = 0;
        int len = nums.length;
        int left1 = 0, left2 = 0, right = 0;
        int sum1 = 0, sum2 = 0;
        while (right < len) {
            sum1 += nums[right];
            while (left1 <= right && sum1 > goal) {
                sum1 -= nums[left1];
                left1++;
            }
            sum2 += nums[right];
            while (left2 <= right && sum2 >= goal) {
                sum2 -= nums[left2];
                left2++;
            }
            res += left2 - left1;
            right++;
        }

        return res;
    }

    @Test
    public void test() {
        int[] nums = {0, 0, 0, 0, 0};
        int goal = 0;
        System.out.println(numSubarraysWithSum(nums, goal));


    }


}
