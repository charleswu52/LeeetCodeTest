package firest;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author WuChao
 * @since 2021/4/25 上午10:53
 */
public class _2 {
    public int maxFrequency(int[] nums, int k) {
        int n = nums.length;
        if (n == 1) {
            return n;
        }
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < dp.length; i++) {
            if (nums[i] == nums[i - 1]) {
                dp[i] += dp[i - 1];
            } else if (k>0){
                int t = k / (nums[i] - nums[i - 1]);
                int min = Math.min(t, dp[i - 1]);
                dp[i] += min;
                k -= min * (nums[i] - nums[i - 1]);
            }
        }
        System.out.println(Arrays.toString(dp));
        Arrays.sort(dp);
        return dp[n - 1];
    }

    /**
     * 滑动窗口的解法
     * 先对原数组进行排序，然后使用 l 与 r 作为执行操作的左右边界（闭区间），同时用 total 来维护将 [l,r] 区间全部变为末尾元素的操作次数。
     * 在顺序枚举目标值（右边界）的同时，我们更新对应的左边界，并用 res 来维护满足限制的最大区间元素数量即可。
     * @param nums
     * @param k
     * @return
     */
    public int maxFrequency2(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        long sum = 0;
        int l = 0;
        int ans = 0;
        for (int r = 0; r < n; r++) {
            sum += nums[r];
            while ((sum + k) < (long) nums[r] * (r - l + 1)) {
                sum -= nums[l];
                l++;
            }
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }


        @Test
    public void test() {
        int[] nums = {9930, 9923, 9983, 9997, 9934, 9952, 9945, 9914, 9985, 9982, 9970, 9932, 9985, 9902, 9975, 9990,
                9922, 9990, 9994, 9937, 9996, 9964, 9943, 9963, 9911, 9925, 9935, 9945, 9933, 9916, 9930, 9938, 10000,
                9916, 9911, 9959, 9957, 9907, 9913, 9916, 9993, 9930, 9975, 9924, 9988, 9923, 9910, 9925, 9977, 9981,
                9927, 9930, 9927, 9925, 9923, 9904, 9928, 9928, 9986, 9903, 9985, 9954, 9938, 9911, 9952, 9974, 9926,
                9920, 9972, 9983, 9973, 9917, 9995, 9973, 9977, 9947, 9936, 9975, 9954, 9932, 9964, 9972, 9935, 9946,
                9966};
        int k = 3056;
        System.out.println(maxFrequency(nums, k));
        System.out.println(maxFrequency2(nums, k));

    }
}
