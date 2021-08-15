package leetcode_everyday.Oct;

/**
 * @author WuChao
 * @create 2021/8/15 15:07
 */
public class _14_1583 {
    /**
     * 每日一题：2021/8/14
     * 1583. 统计不开心的朋友
     * 难度：medium
     * <p>
     * 给你一份 n 位朋友的亲近程度列表，其中 n 总是 偶数 。
     * <p>
     * 对每位朋友 i，preferences[i] 包含一份 按亲近程度从高到低排列 的朋友列表。换句话说，排在列表前面的朋友与 i 的亲近程度比排在列表后面的朋友更高。每个列表中的朋友均以 0 到 n-1 之间的整数表示。
     * <p>
     * 所有的朋友被分成几对，配对情况以列表 pairs 给出，其中 pairs[i] = [xi, yi] 表示 xi 与 yi 配对，且 yi 与 xi 配对。
     * <p>
     * 但是，这样的配对情况可能会是其中部分朋友感到不开心。在 x 与 y 配对且 u 与 v 配对的情况下，如果同时满足下述两个条件，x 就会不开心：
     * <p>
     * x 与 u 的亲近程度胜过 x 与 y，且
     * u 与 x 的亲近程度胜过 u 与 v
     * 返回 不开心的朋友的数目 。
     *
     * <p>
     * 示例
     * 输入：n = 4, preferences = [[1, 2, 3], [3, 2, 0], [3, 1, 0], [1, 2, 0]], pairs = [[0, 1], [2, 3]]
     * 输出：2
     * 解释：
     * 朋友 1 不开心，因为：
     * - 1 与 0 配对，但 1 与 3 的亲近程度比 1 与 0 高，且
     * - 3 与 1 的亲近程度比 3 与 2 高。
     * 朋友 3 不开心，因为：
     * - 3 与 2 配对，但 3 与 1 的亲近程度比 3 与 2 高，且
     * - 1 与 3 的亲近程度比 1 与 0 高。
     * 朋友 0 和 2 都是开心的。
     *
     * <p>
     * 注意:
     * 2 <= n <= 500
     * n 是偶数
     * preferences.length == n
     * preferences[i].length == n - 1
     * 0 <= preferences[i][j] <= n - 1
     * preferences[i] 不包含 i
     * preferences[i] 中的所有值都是独一无二的
     * pairs.length == n/2
     * pairs[i].length == 2
     * xi != yi
     * 0 <= xi, yi <= n - 1
     * 每位朋友都 恰好 被包含在一对中
     */

    /*
    思路： 模拟题


     */
    public int unhappyFriends(int n, int[][] preferences, int[][] pairs) {
        int[][] order = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                order[i][preferences[i][j]] = j;
            }
        }

        int[] match = new int[n];
        for (int[] pair : pairs) {
            int person0 = pair[0];
            int person1 = pair[1];
            match[person0] = person1;
            match[person1] = person0;
        }

        int res = 0;
        for (int x = 0; x < n; x++) {
            int y = match[x];
            int idx = order[x][y];
            for (int i = 0; i < idx; i++) {
                int u = preferences[x][i];
                int v = match[u];
                if (order[u][x] < order[u][v]) {
                    res++;
                    break;
                }
            }
        }
        return res;

    }
}
