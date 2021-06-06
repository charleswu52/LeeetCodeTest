package chp17;

import org.junit.Test;

/**
 * @author WuChao
 * @since 2021/6/6 上午11:06
 */
public class _9 {
    /**
     * 程序员面试金典(version 6) -  面试题 17.09. 第 k 个数
     * 难度: medium
     * <p>
     * 有些数的素因子只有 3，5，7，请设计一个算法找出第 k 个数。注意，不是必须有这些素因子，而是必须不包含其他的素因子。
     * 例如，前几个数按顺序应该是 1，3，5，7，9，15，21。
     *
     * <p>
     * 示例:
     * 输入: k = 5
     *
     * 输出: 9
     * <p>
     *
     * <p>
     * 数据范围：
     */


    /**
     * 思路：动态规划 参考 剑指Offer49  丑数
     * @param k
     * @return
     */
    public int getKthMagicNumber(int k) {
        int a = 0, b = 0, c = 0;
        int[] dp = new int[k];
        dp[0] = 1;
        for (int i = 1; i < k; i++) {
            int n3 = dp[a] * 3, n5 = dp[b] * 5, n7 = dp[c] * 7;
            dp[i] = Math.min(Math.min(n3, n5), n7);
            if (dp[i]==n3) a++;
            if (dp[i]==n5) b++;
            if (dp[i]==n7) c++;
        }
        return dp[k - 1];


    }
    @Test
    public void test() {
        System.out.println(getKthMagicNumber(251));
    }
}
