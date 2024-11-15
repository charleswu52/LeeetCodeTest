package leetcode_everyday._2024.Jul;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author charles
 * @create 2024/7/21
 */
public class _23_3098 {
    /**
     * 每日一题：2024/7/27
     * 3098. 求出所有子序列的能量和
     * https://leetcode.cn/problems/find-the-sum-of-subsequence-powers/description/?envType=daily-question&envId=2024-07-22
     */

    private Map<Long, Integer> f = new HashMap<>();
    private final int mod = (int) 1e9 + 7;
    private int[] nums;

    public int sumOfPowers(int[] nums, int k) {
        Arrays.sort(nums);
        this.nums = nums;
        return dfs(0, nums.length, k, Integer.MAX_VALUE);
    }

    private int dfs(int i, int j, int k, int mi) {
        if (i >= nums.length) {
            return k == 0 ? mi : 0;
        }
        if (nums.length - i < k) {
            return 0;
        }
        long key = (1L * mi) << 18 | (i << 12) | (j << 6) | k;
        if (f.containsKey(key)) {
            return f.get(key);
        }
        int ans = dfs(i + 1, j, k, mi);
        if (j == nums.length) {
            ans += dfs(i + 1, i, k - 1, mi);
        } else {
            ans += dfs(i + 1, i, k - 1, Math.min(mi, nums[i] - nums[j]));
        }
        ans %= mod;
        f.put(key, ans);
        return ans;
    }
}