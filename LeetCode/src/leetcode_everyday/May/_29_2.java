package leetcode_everyday.May;

import java.util.HashMap;

/**
 * @author WuChao
 * @since 2021/5/29 上午9:06
 */
public class _29_2 {
    /**
     * 每日一题：2021/5/29
     * 560. 和为K的子数组
     * 难度: hard
     * <p>
     * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
     * <p>
     * 示例:
     * 输入:nums = [1,1,1], k = 2
     * 输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
     * <p>
     * 数据范围：
     * 数组的长度为 [1, 20,000]。
     * 数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。
     */

    /*
    思路1：枚举
    考虑以 i 结尾和为 k 的连续子数组个数，我们需要统计符合条件的下标 j 的个数，其中 0≤j≤i 且 [j..i] 这个子数组的和恰好为 k 。
    我们可以枚举 [0..i] 里所有的下标 jj 来判断是否符合条
     */
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j >= 0; j--) {
                sum += nums[j];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }
    /*
    思路2： 前缀和+哈希表优化
    对思路1 朴素求解方法进行优化

    定义pre[i] 为[0..i]里所有数的和，则pre[i]可以由pre[i-1]得来
    pre[i] = pre[i-1]+nums[i]
    子数组 [j..i]和为k可以转换为 pre[i] -pre[j-1] = k ==> pre[j-1] = pre[i] - k
    因此可以直接建立一个map，存放前缀和pre[i] 以及 对应出现的次数即可
     */
    public int subarraySum2(int[] nums, int k) {
        int sum = 0;
        int res = 0;
        HashMap<Integer, Integer> store = new HashMap<>();
        store.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (store.containsKey(sum - k)) {
                res += store.get(sum - k);
            }
            store.put(sum, store.getOrDefault(sum, 0) + 1);
        }
        return res;
    }

    }

