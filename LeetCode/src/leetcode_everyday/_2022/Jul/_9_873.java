package leetcode_everyday._2022.Jul;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WuChao
 * @create 2022/7/9 21:32
 */
public class _9_873 {
    /**
     * 每日一题：2022/7/9
     * <p>
     * 873. 最长的斐波那契子序列的长度
     * <p>
     * 难度：medium
     * <p>
     * 如果序列 X_1, X_2, ..., X_n 满足下列条件，就说它是 斐波那契式 的：
     * <p>
     * n >= 3
     * 对于所有 i + 2 <= n，都有 X_i + X_{i+1} = X_{i+2}
     * 给定一个严格递增的正整数数组形成序列 arr ，找到 arr 中最长的斐波那契式的子序列的长度。如果一个不存在，返回  0 。
     * <p>
     * （回想一下，子序列是从原序列 arr 中派生出来的，它从 arr 中删掉任意数量的元素（也可以不删），而不改变其余元素的顺序。例如， [3, 5, 8] 是 [3, 4, 5, 6, 7, 8] 的一个子序列）
     * <p>
     * 示例
     * <p>
     * 输入: arr = [1,2,3,4,5,6,7,8]
     * <p>
     * 输出: 5
     * <p>
     * 解释: 最长的斐波那契式子序列为 [1,2,3,5,8] 。
     * <p>
     * 范围
     * <p>
     * 3 <= arr.length <= 1000
     * 1 <= arr[i] < arr[i + 1] <= 10^9
     */

    /*
    思路： 动态规划

     */
    public int lenLongestFibSubseq(int[] arr) {
        Map<Integer, Integer> indices = new HashMap<>();
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            indices.put(arr[i], i);
        }
        int[][] dp = new int[n][n];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i-1; j >=0 && arr[j]*2>arr[i]; j--) {
                int k = indices.getOrDefault(arr[i] - arr[j], -1);
                if (k >= 0) {
                    dp[j][i] = Math.max(dp[k][j] + 1, 3);
                }
                ans = Math.max(ans, dp[j][i]);

            }
        }
        return ans;
    }
}
