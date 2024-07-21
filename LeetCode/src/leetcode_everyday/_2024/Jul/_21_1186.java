package leetcode_everyday._2024.Jul;

/**
 * @author charles
 * @create 2024/7/21
 */
public class _21_1186 {
    /**
     * 每日一题：2024/7/21
     * 1186. 删除一次得到子数组最大和
     * https://leetcode.cn/problems/maximum-subarray-sum-with-one-deletion/description/
     */
    public int maximumSum(int[] arr) {
        int dp0 = arr[0], dp1 = 0, res = arr[0];
        for (int i = 1; i < arr.length; i++) {
            dp1 = Math.max(dp0, dp1 + arr[i]);
            dp0 = Math.max(dp0, 0) + arr[i];
            res = Math.max(res, Math.max(dp0, dp1));
        }
        return res;
    }
}
