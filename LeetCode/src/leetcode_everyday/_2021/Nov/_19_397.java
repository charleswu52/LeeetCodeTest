package leetcode_everyday._2021.Nov;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WuChao
 * @create 2021/11/19 12:38
 */
public class _19_397 {
    /**
     * 每日一题：2021/11/19
     * <p>
     * 397. 整数替换
     * <p>
     * 难度：medium
     * <p>
     * 给定一个正整数 n ，你可以做如下操作：
     * <p>
     * 如果 n 是偶数，则用 n / 2替换 n 。
     * 如果 n 是奇数，则可以用 n + 1或n - 1替换 n 。
     * n 变为 1 所需的最小替换次数是多少？
     * <p>
     * 示例 1：
     * <p>
     * 输入：n = 8
     * <p>
     * 输出：3
     * <p>
     * 解释：8 -> 4 -> 2 -> 1
     * <p>
     * 示例 2：
     * <p>
     * 输入：n = 7
     * <p>
     * 输出：4
     * <p>
     * 解释：7 -> 8 -> 4 -> 2 -> 1
     * 或 7 -> 6 -> 3 -> 2 -> 1
     * <p>
     * 示例 3：
     * <p>
     * 输入：n = 4
     * <p>
     * 输出：2
     * <p>
     *
     * <p>
     * 范围
     * <p>
     * 1 <= n <= 2^31 - 1
     */

    /*
    思路：DFS
     */
    Map<Long, Integer> map = new HashMap<>();
    public int integerReplacement(int n) {
        return dfs(n);
    }

    public int dfs(long n) {
        if (n == 1) {
            return 0;
        }
        if (map.containsKey(n)) {
            return map.get(n);
        }
        int ans = n % 2 == 0 ? dfs(n / 2) : Math.min(dfs(n + 1), dfs(n - 1));
        map.put(n, ++ans);
        return ans;

    }
}
