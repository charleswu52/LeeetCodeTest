package leetcode_everyday._2022.Jun;

/**
 * @Author CharlesWu
 * @Create 2022/6/3 9:08
 * @Version 1.0
 * @Description
 * @Note
 */
public class _3_829 {
    /**
     * 每日一题：2022/6/3
     * 829. 连续整数求和
     * 难度: hard
     * <p>
     * 给定一个正整数 n，返回 连续正整数满足所有数字之和为 n 的组数 。
     * <p>
     * 示例:
     *
     * 输入: n = 15
     *
     * 输出: 4
     *
     * 解释: 15 = 8 + 7 = 4 + 5 + 6 = 1 + 2 + 3 + 4 + 5
     * <p>
     * 数据范围：
     * 1 <= n <= 10^9
     */


    /*
    思路：数学 求和公式 变形推导

     */
    public int consecutiveNumbersSum(int n) {
        n *= 2;
        int len = (int) Math.sqrt(n), ans = 0;
        for (int i = 1; i <= len; i++) {
            if (n % i == 0 && ((n / i - i + 1) & 1) == 0) {
                ans++;
            }
        }
        return ans;

    }
}
