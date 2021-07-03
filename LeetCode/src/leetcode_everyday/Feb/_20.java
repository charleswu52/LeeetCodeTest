package leetcode_everyday.Feb;

import java.util.HashMap;

public class _20 {
    /**
     * 每日一题：2021/2/20
     * 697. 数组的度
     * 难度: easy
     * 给定一个非空且只包含非负数的整数数组nums，数组的度的定义是指数组里任一元素出现频数的最大值。
     * 你的任务是在 nums 中找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。
     * <p>
     * 示例：
     * 输入：[1, 2, 2, 3, 1]
     * 输出：2
     * 解释：
     * 输入数组的度是2，因为元素1和2的出现频数最大，均为2.
     * 连续子数组里面拥有相同度的有如下所示:
     * [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
     * 最短连续子数组[2, 2]的长度为2，所以返回2.
     * <p>
     * 数据范围：
     * nums.length 在1到 50,000 区间范围内。
     * nums[i] 是一个在 0 到 49,999 范围内的整数。
     */


    /*
    思路1：哈希表
    对数组中的每个数字建立一个哈希表，对应的统计3个信息，出现的开始位置和结束位置和出现的次数
    然后遍历这个哈希表，找到出现次数最多的数字以及区间大小最小的数字，该区间大小的最小值就是题目要求的最小子区间
     */

    public int findShortestSubArray(int[] nums) {
        HashMap<Integer, int[]> store = new HashMap<Integer,int[]>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (!store.containsKey(nums[i])) {
                store.put(nums[i], new int[]{i, i, 1});
            } else {
                store.get(nums[i])[1] = i;
                store.get(nums[i])[2] += 1;
            }
        }
        int count =Integer.MIN_VALUE; // 数组的度
        int res = Integer.MAX_VALUE;    // 区间大小
        for (int[] ints : store.values()) {
            if (count < ints[2]) {
                count = ints[2];
                res = ints[1] - ints[0] + 1;
            } else if (count == ints[2]) {
                res = Math.min(res, ints[1] - ints[0] + 1);
            }
        }
        return res;
    }

    public void _21_2_20() {
        int[] nums = {1,2,2,3,1,4,2};
        System.out.println(findShortestSubArray(nums));
    }


}

