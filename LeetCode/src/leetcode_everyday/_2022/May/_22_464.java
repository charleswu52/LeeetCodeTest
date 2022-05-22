package leetcode_everyday._2022.May;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WuChao
 * @create 2022/5/22 8:57
 */
public class _22_464 {
    /**
     * 每日一题：2022/5/22
     * <p>
     * 464. 我能赢吗
     * <p>
     * 难度：medium
     * <p>
     * 在 "100 game" 这个游戏中，两名玩家轮流选择从 1 到 10 的任意整数，累计整数和，先使得累计整数和 达到或超过 100 的玩家，即为胜者。
     * <p>
     * 如果我们将游戏规则改为 “玩家 不能 重复使用整数” 呢？
     * <p>
     * 例如，两个玩家可以轮流从公共整数池中抽取从 1 到 15 的整数（不放回），直到累计整数和 >= 100。
     * <p>
     * 给定两个整数 maxChoosableInteger （整数池中可选择的最大数）和 desiredTotal（累计和），
     * 若先出手的玩家是否能稳赢则返回 true ，否则返回 false 。假设两位玩家游戏时都表现 最佳 。
     *
     * <p>
     * 示例:
     * <p>
     * 输入：maxChoosableInteger = 10, desiredTotal = 11
     * <p>
     * 输出：false
     * <p>
     * 解释：
     * <p>
     * 无论第一个玩家选择哪个整数，他都会失败。
     * 第一个玩家可以选择从 1 到 10 的整数。
     * 如果第一个玩家选择 1，那么第二个玩家只能选择从 2 到 10 的整数。
     * 第二个玩家可以通过选择整数 10（那么累积和为 11 >= desiredTotal），从而取得胜利.
     * 同样地，第一个玩家选择任意其他整数，第二个玩家都会赢。
     * <p>
     * 范围
     * 1 <= maxChoosableInteger <= 20
     * 0 <= desiredTotal <= 300
     */

    /*
    思路：记忆化搜索+状态压缩
    先虑边界情况，当所有数字选完仍无法到达 desiredTotal 时，两人都无法获胜，返回 false。
    当所有数字的和大于等于 desiredTotal 时，其中一方能获得胜利，需要通过搜索来判断获胜方。

    在游戏中途，假设已经被使用的数字的集合为 usedNumbers，这些数字的和为 currentTotal。
    当某方行动时，如果他能在未选择的数字中选出一个 i，使得 i+currentTotal≥desiredTotal，则他能获胜。否则，需要继续通过搜索来判断获胜方。

    在剩下的数字中，如果他能选择一个 i，使得对方在接下来的局面中无法获胜，则他会获胜。否则，他会失败。

    根据这个思想设计搜索函数 dfs，其中 usedNumbers 可以用一个整数来表示，从低位到高位，
    第 i 位为 1 则表示数字 i 已经被使用，为 0 则表示数字 ii 未被使用。

    如果当前玩家获胜，则返回 true，否则返回 false。为了避免重复计算，需要使用记忆化的操作来降低时间复杂度。

     */
    Map<Integer, Boolean> memo = new HashMap<>();

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if ((1 + maxChoosableInteger) * maxChoosableInteger / 2 < desiredTotal) {
            return false;
        }
        return dfs(maxChoosableInteger, 0, desiredTotal, 0);

    }

    public boolean dfs(int maxChoosableInteger, int usedNumbers, int desiredTotal, int currentTotal) {
        if (!memo.containsKey(usedNumbers)) {
            boolean res = false;
            for (int i = 0; i < maxChoosableInteger; i++) {
                if (((usedNumbers >> i) & 1) == 0) {
                    if (i + 1 + currentTotal >= desiredTotal) {
                        res = true;
                        break;
                    }
                    if (!dfs(maxChoosableInteger, usedNumbers | (1 << i), desiredTotal, currentTotal + i + 1)) {
                        res = true;
                        break;
                    }
                }
            }
            memo.put(usedNumbers, res);
        }
        return memo.get(usedNumbers);
    }

    @Test
    public void test() {
        int a = 4, b = 6;
        System.out.println(canIWin(a, b));
    }
}
