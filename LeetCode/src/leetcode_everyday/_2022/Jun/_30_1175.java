package leetcode_everyday._2022.Jun;

/**
 * @author WuChao
 * @create 2022/6/30 15:10
 */
public class _30_1175 {
    /**
     * 每日一题：2022/6/29
     * <p>
     * 1175. 质数排列
     * <p>
     * 难度：easy
     * <p>
     * 请你帮忙给从 1 到 n 的数设计排列方案，使得所有的「质数」都应该被放在「质数索引」（索引从 1 开始）上；你需要返回可能的方案总数。
     * <p>
     * 让我们一起来回顾一下「质数」：质数一定是大于 1 的，并且不能用两个小于它的正整数的乘积来表示。
     * <p>
     * 由于答案可能会很大，所以请你返回答案 模 mod 10^9 + 7 之后的结果即可。
     * <p>
     * 示例
     * <p>
     * 输入：n = 5
     * <p>
     * 输出：12
     * <p>
     * 解释：举个例子，[1,2,5,4,3] 是一个有效的排列，但 [5,2,3,4,1] 不是，因为在第二种情况里质数 5 被错误地放在索引为 1 的位置上。
     * <p>
     * 范围
     * <p>
     * 1 <= n <= 100
     */


    /*
    思路： 求范围内质数的个数
     */
    int mod = 1000000007;
    public int numPrimeArrangements(int n) {
        int numPrimes = 0;
        for (int i = 1; i <= n; i++) {
            if (isPrime(i)) {
                numPrimes++;
            }
        }
        return (int) (fun(numPrimes) * fun(n - numPrimes) % mod);
    }

    private boolean isPrime(int num) {
        if (num == 1) {
            return false;
        }
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    private long fun(int num) {
        long res = 1;
        for (int i = 2; i <= num; i++) {
            res *= i;
            res %= mod;

        }
        return res;
    }
}
