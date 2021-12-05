package leetcode_everyday.Dec;

/**
 * @author WuChao
 * @create 2021/12/5 10:44
 */
public class _5_372 {
    /**
     * 每日一题：2021/12/5
     * 372. 超级次方
     * 难度：medium
     * <p>
     * 你的任务是计算 a^b 对 1337 取模，a 是一个正整数，b 是一个非常大的正整数且会以数组形式给出。
     *
     * <p>
     * 示例1
     * <p>
     * 输入：a = 2, b = [1,0]
     * <p>
     * 输出：1024
     * <p>
     * 示例2
     * <p>
     * 输入：a = 2147483647, b = [2,0,0]
     * <p>
     * 输出：1198
     *
     * <p>
     * 范围
     * <p>
     * 1 <= a <= 2^31 - 1
     * 1 <= b.length <= 2000
     * 0 <= b[i] <= 9
     * b 不含前导 0
     */


    /*
    思路：递归+快速幂
    题解：https://leetcode-cn.com/problems/super-pow/solution/gong-shui-san-xie-di-gui-kuai-su-mi-ying-yx1j/
     */

    int MOD = 1337;
    // solution
    public int superPow(int a, int[] b) {
        return dfs(a, b, b.length - 1);
    }

    // 递归计算
    private int dfs(int a, int[] b, int idx) {
        if (idx == -1) {
            return 1;
        }
        return qpow(dfs(a, b, idx - 1), 10) * qpow(a, b[idx]) % MOD;
    }

    // 快速幂
    private int qpow(int a, int b) {
        int ans = 1;
        a %= MOD;
        while (b != 0) {
            if ((b & 1) != 0) {
                ans = ans * a % MOD;
            }
            a = a * a % MOD;
            b >>= 1;
        }
        return ans;
    }
}
