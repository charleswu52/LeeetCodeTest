package leetcode_everyday._2021.Dec;

import java.util.PriorityQueue;

/**
 * @author WuChao
 * @create 2021/12/3 12:05
 */
public class _3_1005 {
    /**
     * 每日一题：2021/12/3
     * 1005. K 次取反后最大化的数组和
     * 难度：easy
     * <p>
     * 给你一个整数数组 nums 和一个整数 k ，按以下方法修改该数组：
     *
     * 选择某个下标 i 并将 nums[i] 替换为 -nums[i] 。
     * 重复这个过程恰好 k 次。可以多次选择同一个下标 i 。
     *
     * 以这种方式修改数组后，返回数组 可能的最大和 。
     *
     * <p>
     * 输入：nums = [4,2,3], k = 1
     * 输出：5
     * 解释：选择下标 1 ，nums 变为 [4,-2,3] 。
     *
     * 输入：nums = [2,-3,-1,5,-4], k = 2
     * 输出：13
     * 解释：选择下标 (1, 4) ，nums 变为 [2,3,-1,5,4] 。
     *
     * <p>
     * 范围
     * 1 <= nums.length <= 104
     * -100 <= nums[i] <= 100
     * 1 <= k <= 104
     */


    /*
    思路1： 暴力，优先队列
     */
    public int largestSumAfterKNegations(int[] nums, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>((o1, o2) -> o1 - o2);
        for (int num:nums) {
            priorityQueue.offer(num);
        }
        for (int i = 0; i < k; i++) {
            int t = priorityQueue.poll();
            priorityQueue.offer(-t);
        }

        int res = 0;
        while (!priorityQueue.isEmpty()) {
            res += priorityQueue.poll();
        }
        return res;

    }

    /*
    思路2： 贪心
     */

    public int largestSumAfterKNegations2(int[] nums, int k) {
        int[] number = new int[201];
        for (int num : nums) {
            number[num + 100]++;
        }
        int i = 0;
        while (k > 0) {
            while (number[i] == 0) { // 找到number中最小的数字
                i++;
            }
            number[i]--; // 此数字个数 -1
            number[200 - i]++; // 相反数+1
            if (i > 100) {
                i = 200 - i;
            }
            k--;
        }
        int res = 0;
        for (int j = i; j < number.length; j++) {
            res += (j - 100) * number[j];
        }
        return res;
    }

    }
