package leetcode_everyday._2022.Mar;

/**
 * @author WuChao
 * @create 2022/3/23 9:38
 */
public class _23_440 {
    /**
     * 每日一题：2022/3/23
     * <p>
     * 440. 字典序的第K小数字
     * <p>
     * 难度：hard
     * <p>
     * 给定整数 n 和 k，返回  [1, n] 中字典序第 k 小的数字。
     * <p>
     * 示例1
     * <p>
     * 输入: n = 13, k = 2
     * 输出: 10
     * 解释: 字典序的排列是 [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9]，所以第二小的数字是 10。
     * <p>
     * 范围
     * <p>
     * 1 <= k <= n <= 109
     */

    /*
    思路：https://leetcode-cn.com/problems/k-th-smallest-in-lexicographical-order/solution/by-ac_oier-m3zl/
     */
    public int findKthNumber(int n, int k) {
        int ans = 1;
        while (k > 1) {
            int cnt = getCnt(ans, n);
            if (cnt < k) {
                k -= cnt; ans++;
            } else {
                k--; ans *= 10;
            }
        }
        return ans;
    }
    int getCnt(int x, int limit) {
        String a = String.valueOf(x), b = String.valueOf(limit);
        int n = a.length(), m = b.length(), k = m - n;
        int ans = 0, u = Integer.parseInt(b.substring(0, n));
        for (int i = 0; i < k; i++) ans += Math.pow(10, i);
        if (u > x) ans += Math.pow(10, k);
        else if (u == x) ans += limit - x * Math.pow(10, k) + 1;
        return ans;
    }

}
