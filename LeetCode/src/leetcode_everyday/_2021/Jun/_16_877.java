package leetcode_everyday._2021.Jun;

/**
 * @author WuChao
 * @create 2021/6/16 8:41
 */
public class _16_877 {
    /**
     * 每日一题：2021/6/16
     * 877. 石子游戏
     * 难度: medium
     * <p>
     * 亚历克斯和李用几堆石子在做游戏。偶数堆石子排成一行，每堆都有正整数颗石子 piles[i] 。
     * <p>
     * 游戏以谁手中的石子最多来决出胜负。石子的总数是奇数，所以没有平局。
     * <p>
     * 亚历克斯和李轮流进行，亚历克斯先开始。 每回合，玩家从行的开始或结束处取走整堆石头。 这种情况一直持续到没有更多的石子堆为止，此时手中石子最多的玩家获胜。
     * <p>
     * 假设亚历克斯和李都发挥出最佳水平，当亚历克斯赢得比赛时返回 true ，当李赢得比赛时返回 false 。
     * <p>
     *
     * <p>
     * 示例:
     * 输入：[5,3,4,5]
     * 输出：true
     * 解释：
     * 亚历克斯先开始，只能拿前 5 颗或后 5 颗石子 。
     * 假设他取了前 5 颗，这一行就变成了 [3,4,5] 。
     * 如果李拿走前 3 颗，那么剩下的是 [4,5]，亚历克斯拿走后 5 颗赢得 10 分。
     * 如果李拿走后 5 颗，那么剩下的是 [3,4]，亚历克斯拿走后 4 颗赢得 9 分。
     * 这表明，取前 5 颗石子对亚历克斯来说是一个胜利的举动，所以我们返回 true 。
     * <p>
     * 数据范围：
     * 2 <= piles.length <= 500
     * piles.length 是偶数。
     * 1 <= piles[i] <= 500
     * sum(piles) 是奇数。
     */

    /*
    题目解析：
    将题目变成两个人轮流从数组的头/尾 取数字，取数之和多者获胜；亚历克斯先手，李后手，判断对给出的数组，亚历克斯是否可以获胜
    题目中说明，数组数量是偶数，所有元素和是奇数，因此不存在平局的情况，因此必须有一人获胜。
     */
    /*
    思路1：数学分析
    根据题目加的限制条件，必须有一人获胜，先数学分析能否有一直获胜的选择
    将数组按奇数和偶数下标分成两组，其实这就对应了一种选取策略，一人可以永远选择奇数/偶数组的数字，而且由于题目保证了和为奇数，因此两组和必有
    一组多一组少，看哪组和多就让亚历克斯永远选那组和多的即可。
    这样就能保证其永远胜利，也就是存在永远胜利的策略，直接返回TRUE即可。
     */
    public boolean stoneGame(int[] piles) {
        return true;
    }

    /*
    思路2：动态规划
    由于每次只能从行的开始或结束处取走整堆石子，因此可以保证剩下的石子堆一定是连续的。
    如果只剩下一堆石子，则当前玩家只能取走这堆石子。如果剩下多堆石子，则当前玩家可以选择从行的开始或结束处取走整堆石子，
    然后轮到另一个玩家在剩下的石子堆中取走石子。

    这是一个递归的过程，因此可以使用递归进行求解，递归过程中维护一个总数，表示 Alex 和 Lee 的石子数量之差，
    当游戏结束时，如果总数大于 0，则 Alex 赢得比赛，否则 Lee 赢得比赛。

    如果有 n 堆石子，则递归的时间复杂度为 O(2^n)，无法通过所有的测试用例。递归的时间复杂度高的原因是存在大量重复计算。
    由于存在重复子问题，因此可以使用动态规划降低时间复杂度。

    定义二维数组 dp，其行数和列数都等于石子的堆数，dp[i][j] 表示当剩下的石子堆为下标 i 到下标 j 时，当前玩家与另一个玩家的石子数量之差的最大值，
    注意当前玩家不一定是先手 Alex。

    只有当 i≤j 时，剩下的石子堆才有意义，因此当 i>j 时，dp[i][j]=0。

    当 i=j 时，只剩下一堆石子，当前玩家只能取走这堆石子，因此对于所有 0≤i<nums.length，都有 dp[i][i]=piles[i]。

    当 i<j 时，当前玩家可以选择取走 piles[i] 或 piles[j]，然后轮到另一个玩家在剩下的石子堆中取走石子。在两种方案中，当前玩家会选择最优的方案，
    使得自己的石子数量最大化。因此可以得到如下状态转移方程：
        dp[i][j]=max(piles[i]−dp[i+1][j],piles[j]−dp[i][j−1])

    最后判断 dp[0][piles.length−1] 的值，如果大于 0，则 Alex 的石子数量大于 Lee 的石子数量，因此 Alex 赢得比赛，否则 Lee 赢得比赛。
     */
    public boolean stoneGame2(int[] piles) {
        int len = piles.length;
        int[][] dp = new int[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = piles[i];
        }
        for (int i = len-2; i >=0 ; i--) {
            for (int j = i+1; j < len; j++) {
                dp[i][j] = Math.max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1]);
            }
        }
        return dp[0][len - 1] > 0;

    }

    /*
    思路3：区间dp
    思路：参考宫水千叶的题解
    https://mp.weixin.qq.com/s?__biz=MzU4NDE3MTEyMA==&mid=2247489400&idx=1&sn=0b629d3669329a6bf4f6ec71c2571ce7&chksm=fd9cbc67caeb357132fe0a1ca6240e2183748d94039100f539193d3eeb1dc223e0ddd4aa9584&token=2094656911&lang=zh_CN#rd
     */
    public boolean stoneGame3(int[] piles) {
        int length = piles.length;
        int[][] f = new int[length + 2][length + 2];
        for (int len = 1; len <= length; len++) { // 枚举区间长度
            for (int left = 1; left + len - 1 <= length; left++) { // 枚举左端点
                int right = left + len - 1;// 计算右端点
                int a = piles[left - 1] - f[left + 1][right];
                int b = piles[right - 1] - f[left][right - 1];
                f[left][right] = Math.max(a, b);
            }
        }
        return f[1][length] > 0;
    }

    }



