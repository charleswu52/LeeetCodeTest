package leetcode_everyday._2021.Feb;

public class _4 {
    /**
     * 每日一题：2021/2/4
     * 643. 子数组最大平均数 I
     * 难度: easy
     * 给定 n 个整数，找出平均数最大且长度为 k 的连续子数组，并输出该最大平均数。
     * <p>
     * 示例：
     * 输入：[1,12,-5,-6,50,3], k = 4
     * 输出：12.75
     * 解释：最大平均数 (12-5-6+50)/4 = 51/4 = 12.75
     * <p>
     * 数据范围：
     * 1 <= k <= n <= 30,000。
     * 所给数据范围 [-10,000，10,000]。
     */

    public double findMaxAverage(int[] nums, int k) {
        double res = -Double.MAX_VALUE;
        int head = 0;
        double count = 0;

        for (int i = 0; i < nums.length; i++) {
            count += nums[i];
            if (i >= k - 1) {
                res = Math.max(res, count / k);
                count -= nums[head++];
            }
        }
        return res;
    }

    // 改进版
    public double findMaxAverage2(int[] nums, int k) {
        int sum = 0;
        int n = nums.length;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        int maxSum = sum;
        for (int i = k; i < n; i++) {
            sum = sum - nums[i - k] + nums[i];
            maxSum = Math.max(maxSum, sum);
        }
        return 1.0 * maxSum / k;
    }

    public void _21_2_4() {
        int[] nums = {-1};
        int k = 1;
        System.out.println(findMaxAverage2(nums, k));

    }
}
