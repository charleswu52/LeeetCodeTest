package leetcode_everyday._2022.Apr;

/**
 * @author WuChao
 * @create 2022/4/22 8:30
 */
public class _22_396 {
    /**
     * 每日一题：2022/4/22
     * <p>
     * 396. 旋转函数
     * <p>
     * 难度：medium
     * <p>
     * 给定一个长度为 n 的整数数组 nums 。
     * <p>
     * 假设 arrk 是数组 nums 顺时针旋转 k 个位置后的数组，我们定义 nums 的 旋转函数  F 为：
     * <p>
     * F(k) = 0 * arrk[0] + 1 * arrk[1] + ... + (n - 1) * arrk[n - 1]
     * 返回 F(0), F(1), ..., F(n-1)中的最大值 。
     * <p>
     * 生成的测试用例让答案符合 32 位 整数。
     * <p>
     * 示例
     * <p>
     * 输入: nums = [4,3,2,6]
     * <p>
     * 输出: 26
     * <p>
     * 解释:
     * F(0) = (0 * 4) + (1 * 3) + (2 * 2) + (3 * 6) = 0 + 3 + 4 + 18 = 25
     * F(1) = (0 * 6) + (1 * 4) + (2 * 3) + (3 * 2) = 0 + 4 + 6 + 6 = 16
     * F(2) = (0 * 2) + (1 * 6) + (2 * 4) + (3 * 3) = 0 + 6 + 8 + 9 = 23
     * F(3) = (0 * 3) + (1 * 2) + (2 * 6) + (3 * 4) = 0 + 2 + 12 + 12 = 26
     * 所以 F(0), F(1), F(2), F(3) 中的最大值是 F(3) = 26 。
     * <p>
     * 范围
     * <p>
     * n == nums.length
     * 1 <= n <= 105
     * -100 <= nums[i] <= 100
     */

    /*
    思路：数学规律
    * 把数组逆转跟把乘数逆转是一样的，可以看出有如下规律
    4     3     2     6

   0*4   1*3   2*2   3*6   F(0)

   3*4   0*3   1*2   2*6   F(1) = F(0) - SUM(data) + N * data[0];

   2*4   3*3   0*2   1*6   F(2) = F(1) - SUM(data) + N * data[1];

   1*4   2*3   3*2   0*6   F(3) = F(2) - SUM(data) + N * data[2];

     */
    public int maxRotateFunction(int[] nums) {
        int ans = 0;
        int n = nums.length;
        int sum = 0,all=0;
        for (int i = 0; i < n; i++) {
            all += i * nums[i];
            sum += nums[i];
        }
        ans = all;
        for (int i = 1; i < n; i++) {
            int cur = all - sum + n * nums[i-1];
            ans = Math.max(ans, cur);
            all = cur;
        }
        return ans;

    }
}
