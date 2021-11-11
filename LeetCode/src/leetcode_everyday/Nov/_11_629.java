package leetcode_everyday.Nov;

/**
 * @author WuChao
 * @create 2021/11/11 9:02
 */
public class _11_629 {
    /**
     * 每日一题：2021/11/11
     * <p>
     * 629. K个逆序对数组
     * <p>
     * 难度：hard
     * <p>
     * 给出两个整数 n 和 k，找出所有包含从 1 到 n 的数字，且恰好拥有 k 个逆序对的不同的数组的个数。
     * <p>
     * 逆序对的定义如下：对于数组的第i个和第 j个元素，如果满i < j且 a[i] > a[j]，则其为一个逆序对；否则不是。
     * <p>
     * 由于答案可能很大，只需要返回 答案 mod 10^9 + 7 的值。
     *
     * <p>
     * 示例1：
     * <p>
     * 输入: n = 3, k = 0
     * <p>
     * 输出: 1
     * <p>
     * 解释:
     * 只有数组 [1,2,3] 包含了从1到3的整数并且正好拥有 0 个逆序对。
     * <p>
     * 范围
     * <p>
     * n 的范围是 [1, 1000] 并且 k 的范围是 [0, 1000]。
     */

    /*
    思路：动态规划
    难度较大 详细看题解：
     */
    public int kInversePairs(int n, int k) {
        int MOD = 1000000007;
        int[][] f = new int[2][k + 1];
        f[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                int cur = i & 1, prev = cur ^ 1;
                f[cur][j] = (j - 1 >= 0 ? f[cur][j - 1] : 0) - (j - i >= 0 ? f[prev][j - i] : 0) + f[prev][j];
                if (f[cur][j] >= MOD) {
                    f[cur][j] -= MOD;
                } else if (f[cur][j] < 0) {
                    f[cur][j] += MOD;
                }
            }
        }
        return f[n & 1][k];


    }
}
