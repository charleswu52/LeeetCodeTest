import org.junit.Test;

import java.util.Arrays;

/**
 * @author WuChao
 * @since 2021/3/30 上午9:35
 */
public class _60 {
    /**
     * 剑指 Offer 60. n个骰子的点数
     * 难度: medium
     * <p>
     * 把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。
     * 你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。
     *
     * <p>
     * 例如：
     * 输入: 2
     * 输出: [0.02778,0.05556,0.08333,0.11111,0.13889,0.16667,0.13889,0.11111,0.08333,0.05556,0.02778]
     * <p>
     * 数据范围：
     * 1 <= n <= 11
     */

    /*
    非常糟糕的解法，只考虑了数字累加出现的顺序，没有考虑到组合出现的情况 是错误的解法
     */
    public double[] dicesProbability(int n) {
        int cases = 5 * n + 1;
        double[] res = new double[cases];
        // 使用滑动窗口的方法，统计每个数出现的次数
        // 窗口大小：6 窗口数量：5*n-4
        int left = 0, right = 0;
        int windowCount = 5 * n - 4;
        int windowSize = 6;
        for (int i = 0; i < windowCount; i++) {
            for (int j = i; j < i+windowSize; j++) {
                res[j] += 1;
            }
        }
        System.out.println(Arrays.toString(res));
        double sum = 0;
        for (double num : res) {
            sum += num;
        }
        System.out.println(sum);
        for (int i = 0; i < res.length; i++) {
            res[i] = res[i] / sum;
        }

        return res;

    }

    /**
     * 应该考虑排列组合的情况 使用动态规划的做法实现
     * 动态规划数组dp[n][s]：表示投掷n个骰子，n个面朝上的点数之和为s的事件出现的次数。
     * 动态规划方程就是 ：dp[n][s] += dp[n-1][s-k],k∈[1,6]
     * 动态转移方程的前提条件是要保证 s - k > 0，因为没有骰子能投掷出小于等于 0 的点数。
     * <p>
     * 为了方便表达，我们将 dp 数组的行的数量设为 n + 1，列的数量设为 6n + 1。行表示 n，列表示 s，且 s 的最大值是 6n。
     * 假如只有一个骰子（n = 1），s 的范围是 [n, 6n]，所以 s 的范围是 [1, 6]，且 s 的每个值可能出现的次数都为 1
     */
    public double[] dicesProbability2(int n) {
        double[] res = new double[5 * n + 1];
        int[][] dp = new int[n + 1][6 * n + 1];// dp数组
        int row = dp.length, col = dp[0].length;
        for (int i = 1; i <= 6; i++) {
            dp[1][i] = 1;//初始化dp数组
        }
        for (int i = 2; i <= n; i++) {
            for (int j = i; j <= 6*n; j++) {
                // s从n开始，因为最小值是n，最大值是6n
                for (int k = 1; k <= 6; k++) {
                    if (j - k > 0) {
                        dp[i][j] += dp[i - 1][j - k];
                    } else {
                        break;
                    }
                }
            }
        }
        double sum = Math.pow(6.0, n);
        for (int s = n; s <= 6 * n; s++) {
            res[s - n] = dp[n][s] / sum;
        }
        return res;
    }


        @Test
    public void test() {
        int n = 3;
        System.out.println(Arrays.toString(dicesProbability2(n)));
    }
}
