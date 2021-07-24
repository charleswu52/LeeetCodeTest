package leetcode_hot100.top80;

/**
 * @author WuChao
 * @create 2021/7/24 9:17
 */
public class _312 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 312. 戳气球
     * 难度：hard
     * <p>
     * 有 n 个气球，编号为0 到 n - 1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
     *
     * 现在要求你戳破所有的气球。戳破第 i 个气球，你可以获得 nums[i - 1] * nums[i] * nums[i + 1] 枚硬币。
     * 这里的 i - 1 和 i + 1 代表和 i 相邻的两个气球的序号。如果 i - 1或 i + 1 超出了数组的边界，那么就当它是一个数字为 1 的气球。
     *
     * 求所能获得硬币的最大数量。
     *
     * <p>
     * 输入：nums = [3,1,5,8]
     * 输出：167
     * 解释：
     * nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
     * coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
     *
     */

    /*
   思路：动态规划
   假设 dp[i][j] 表示开区间 (i,j) 内你能拿到的最多金币
    那么这个情况下
    你在 (i,j) 开区间得到的金币可以由 dp[i][k] 和 dp[k][j] 进行转移
    如果你此刻选择戳爆气球 k，那么你得到的金币数量就是：
    total = dp[i][k] + val[i] * val[k] * val[j] + dp[k][j]
    注：val[i] 表示 i 位置气球的数字
    然后 (i,k) 和 (k,j) 也都是开区间

     */

    public int maxCoins(int[] nums) {
        int n = nums.length;
        // 创建一个辅助数组，在首尾各添加1，方便处理边界情况
        int[] temp = new int[n + 2];
        temp[0] = 1;
        temp[n + 1] = 1;
        for (int i = 0; i < n; i++) {
            temp[i + 1] = nums[i];
        }

        int[][] dp = new int[n + 2][n + 2];
        // len 代表开区间长度
        for (int len = 3; len <= n + 2; len++) {
            // i代表开区间左端点
            for (int i = 0; i <= n + 2 - len; i++) {
                int res = 0;
                // K 为区间内的索引
                for (int k = i + 1; k < i + len - 1; k++) {
                    int left = dp[i][k];
                    int right = dp[k][i + len - 1];
                    res = Math.max(res, left + temp[i] * temp[k] * temp[i + len - 1] + right);
                }
                dp[i][i + len - 1] = res;
            }
        }
        return dp[0][n + 1];
    }
}
