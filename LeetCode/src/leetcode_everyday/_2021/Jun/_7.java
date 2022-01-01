package leetcode_everyday._2021.Jun;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author WuChao
 * @create 2021/6/7 上午8:09
 */
public class _7 {
    /**
     * 每日一题：2021/6/7
     * 494. 目标和
     * 难度: medium
     * <p>
     * 你一个整数数组 nums 和一个整数 target 。
     *
     * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
     *
     * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
     * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
     * <p>
     * 示例:
     * 输入：nums = [1,1,1,1,1], target = 3
     * 输出：5
     * 解释：一共有 5 种方法让最终目标和为 3 。
     * -1 + 1 + 1 + 1 + 1 = 3
     * +1 - 1 + 1 + 1 + 1 = 3
     * +1 + 1 - 1 + 1 + 1 = 3
     * +1 + 1 + 1 - 1 + 1 = 3
     * +1 + 1 + 1 + 1 - 1 = 3
     *
     * 输入：nums = [1], target = 1
     * 输出：1
     * <p>
     * 数据范围：
     * 1 <= nums.length <= 20
     * 0 <= nums[i] <= 1000
     * 0 <= sum(nums[i]) <= 1000
     * -1000 <= target <= 100
     */

    /*
    题目解析 && 思路：
    数组和是sum，要将一部分数字改为负号后的和是neg,则可以推出 neg = (sum-target)/2
    由于nums[i] 是非负数。因此求出neg 为负数可直接返回0
    这样原问题就转换成了在原数组 nums 中选取若干元素，使得这些元素的和是neg。计算存在的方案数，这样可以使用动态规划的思路来解决。
    dp[i][j]  表示在 nums 的前 i 个数中选取元素，使得所有元素之和为j，最终结果为 dp[n][neg]
    边界条件：dp[0][0] = 1; dp[0][j] = 0,j>= 1
    其他情况:
            对数组中的第i个元素 num(i从1 开始)，遍历 0<= j <= neg,计算dp[i][j]的值
                如果 j < num,则不能选num，此时dp[i][j] = dp[i-1][j]
                如果 j >= num,不选num,是 dp[i-1][j],选的话是 dp[i-1][j-num] 此时 dp[i][j] = dp[i-1][j]+dp[i-1][j-num]
     */


    public int findTargetSumWays(int[] nums, int target) {
        int sum = Arrays.stream(nums).sum();
        int diff = sum - target;
        if (diff < 0 || diff % 2 != 0) {
            return 0;
        }
        int n = nums.length, neg = diff / 2;
        int[][] dp = new int[n + 1][neg + 1];
        dp[0][0] = 1;
        for (int i = 1; i < n+1; i++) {
            int num = nums[i - 1];
            for (int j = 0; j <= neg; j++) {
                if (num > j) {
                    dp[i][j] = dp[i - 1][j];
                } else if (num <= j) {
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - num];
                }
            }
        }
        return dp[n][neg];

    }



    @Test
    public void test() {
        Integer a = 1545646458;
        System.out.println(a.hashCode());
    }
}
