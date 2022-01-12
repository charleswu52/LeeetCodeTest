package leetcode_everyday._2021.Nov;

/**
 * @author WuChao
 * @create 2021/11/12 9:33
 */
public class _12_375 {
    /**
     * 每日一题：2021/11/12
     * <p>
     * 375. 猜数字大小 II
     * <p>
     * 难度：medium
     * <p>
     * 我们正在玩一个猜数游戏，游戏规则如下：
     *
     * 我从 1 到 n 之间选择一个数字。
     * 你来猜我选了哪个数字。
     * 如果你猜到正确的数字，就会 赢得游戏 。
     * 如果你猜错了，那么我会告诉你，我选的数字比你的 更大或者更小 ，并且你需要继续猜数。
     * 每当你猜了数字 x 并且猜错了的时候，你需要支付金额为 x 的现金。如果你花光了钱，就会 输掉游戏 。
     * 给你一个特定的数字 n ，返回能够 确保你获胜 的最小现金数，不管我选择那个数字 。
     * <p>
     * 示例1：
     * 输入：n = 10
     * 输出：16
     * 解释：制胜策略如下：
     * - 数字范围是 [1,10] 。你先猜测数字为 7 。
     *     - 如果这是我选中的数字，你的总费用为 $0 。否则，你需要支付 $7 。
     *     - 如果我的数字更大，则下一步需要猜测的数字范围是 [8,10] 。你可以猜测数字为 9 。
     *         - 如果这是我选中的数字，你的总费用为 $7 。否则，你需要支付 $9 。
     *         - 如果我的数字更大，那么这个数字一定是 10 。你猜测数字为 10 并赢得游戏，总费用为 $7 + $9 = $16 。
     *         - 如果我的数字更小，那么这个数字一定是 8 。你猜测数字为 8 并赢得游戏，总费用为 $7 + $9 = $16 。
     *     - 如果我的数字更小，则下一步需要猜测的数字范围是 [1,6] 。你可以猜测数字为 3 。
     *         - 如果这是我选中的数字，你的总费用为 $7 。否则，你需要支付 $3 。
     *         - 如果我的数字更大，则下一步需要猜测的数字范围是 [4,6] 。你可以猜测数字为 5 。
     *             - 如果这是我选中的数字，你的总费用为 $7 + $3 = $10 。否则，你需要支付 $5 。
     *             - 如果我的数字更大，那么这个数字一定是 6 。你猜测数字为 6 并赢得游戏，总费用为 $7 + $3 + $5 = $15 。
     *             - 如果我的数字更小，那么这个数字一定是 4 。你猜测数字为 4 并赢得游戏，总费用为 $7 + $3 + $5 = $15 。
     *         - 如果我的数字更小，则下一步需要猜测的数字范围是 [1,2] 。你可以猜测数字为 1 。
     *             - 如果这是我选中的数字，你的总费用为 $7 + $3 = $10 。否则，你需要支付 $1 。
     *             - 如果我的数字更大，那么这个数字一定是 2 。你猜测数字为 2 并赢得游戏，总费用为 $7 + $3 + $1 = $11 。
     * 在最糟糕的情况下，你需要支付 $16 。因此，你只需要 $16 就可以确保自己赢得游戏。
     * <p>
     * 范围
     * <p>
     * 1 <= n <= 200
     */

    /*
    思路：递归二分
     */
    public int getMoneyAmount(int n) {
        return dfs(1, n);
    }

    /*
    s
    设计递归函数为 int dfs(int l, int r) 传入参数 l 和 r 代表在范围 [l, r] 内进行猜数，返回值为在 [l, r] 内猜中数字至少需要多少钱。
    我们可决策的部分为「选择猜哪个数 x」，而不可决策的是「选择某个数 x 之后（假设没有猜中），真实值在落在哪边」。
    因而为求得「最坏情况下最好」的结果，我们应当取所有的 x 中的最小值。
    最后，为减少重复计算，我们需要在「递归」基础上加入记忆化搜索。
     */

    static int[][] cache = new int[201][201];
    private int dfs(int left, int right) {
        if (left >= right) {
            return 0;
        }
        if (cache[left][right] != 0) {
            return cache[left][right];
        }
        int ans = Integer.MAX_VALUE;
        for (int i = left; i <= right; i++) {
            int cur = Math.max(dfs(left, i - 1), dfs(i + 1, right)) + i;
            ans = Math.min(cur, ans);
        }
        cache[left][right] = ans;
        return ans;
    }
}