package leetcode_everyday.Nov;

import java.util.HashMap;

/**
 * @author WuChao
 * @create 2021/11/5 9:07
 */
public class _5_1218 {
    /**
     * 每日一题：2021/11/5
     * <p>
     * 1218. 最长定差子序列
     * <p>
     * 难度：medium
     * <p>
     * 给你一个整数数组 arr 和一个整数 difference，请你找出并返回 arr 中最长等差子序列的长度，该子序列中相邻元素之间的差等于 difference 。
     * 子序列 是指在不改变其余元素顺序的情况下，通过删除一些元素或不删除任何元素而从 arr 派生出来的序列。
     * <p>
     * 示例1：
     * <p>
     * 输入：arr = [1,5,7,8,5,3,4,2,1], difference = -2
     * <p>
     * 输出：4
     * <p>
     * 解释：最长的等差子序列是 [7,5,3,1]。
     *
     * <p>
     * 范围
     * <p>
     * 1 <= arr.length <= 105
     * -10^4 <= arr[i], difference <= 10^4
     */


    /*
    思路：动态规划
    动态规划数组的定义：dp[i] 表示以 arr[i] 为结尾的最长等差子序列的长度，取所有长度的最大值即为答案。
    状态转移 dp[i] = dp[i-d] +1

     */
    public int longestSubsequence(int[] arr, int difference) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int ans = 1;
        for (int x : arr) {
            map.put(x, map.getOrDefault(x-difference, 0) + 1);
            ans = Math.max(ans, map.get(x));
        }
        return ans;

    }
}
