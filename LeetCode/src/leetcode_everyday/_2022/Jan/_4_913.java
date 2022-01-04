package leetcode_everyday._2022.Jan;

import java.util.Arrays;

/**
 * @author WuChao
 * @create 2022/1/4 15:21
 */
public class _4_913 {
    /**
     * 每日一题：2022/1/4
     * <p>
     * 913. 猫和老鼠
     * <p>
     * 难度：hard
     * <p>
     * 两位玩家分别扮演猫和老鼠，在一张 无向 图上进行游戏，两人轮流行动。
     * <p>
     * 图的形式是：graph[a] 是一个列表，由满足 ab 是图中的一条边的所有节点 b 组成。
     * <p>
     * 老鼠从节点 1 开始，第一个出发；猫从节点 2 开始，第二个出发。在节点 0 处有一个洞。
     * <p>
     * 在每个玩家的行动中，他们 必须 沿着图中与所在当前位置连通的一条边移动。例如，如果老鼠在节点 1 ，那么它必须移动到 graph[1] 中的任一节点。
     * <p>
     * 此外，猫无法移动到洞中（节点 0）。
     * <p>
     * 然后，游戏在出现以下三种情形之一时结束：
     * <p>
     * 如果猫和老鼠出现在同一个节点，猫获胜。
     * 如果老鼠到达洞中，老鼠获胜。
     * 如果某一位置重复出现（即，玩家的位置和移动顺序都与上一次行动相同），游戏平局。
     * 给你一张图 graph ，并假设两位玩家都都以最佳状态参与游戏：
     * <p>
     * 如果老鼠获胜，则返回 1；
     * 如果猫获胜，则返回 2；
     * 如果平局，则返回 0 。
     * <p>
     * 示例 1：
     * <p>
     * 输入：graph = [[2,5],[3],[0,4,5],[1,4,5],[2,3],[0,2,3]]
     * <p>
     * 输出：0
     * <p>
     * 输入：graph = [[1,3],[0],[3],[0,2]]
     * <p>
     * 输出：1
     * <p>
     * 范围
     * <p>
     * 3 <= graph.length <= 50
     * 1 <= graph[i].length < graph.length
     * 0 <= graph[i][j] < graph.length
     * graph[i][j] != i
     * graph[i] 互不相同
     * 猫和老鼠在游戏中总是移动
     **/

    /*
    思路：分析
    博弈问题 使用 动态规划 进行解决
    详细题解：https://leetcode-cn.com/problems/cat-and-mouse/solution/mao-he-lao-shu-by-leetcode-solution-444x/
     */
    // 定义三种状态
    static final int MOUSE_WIN = 1;
    static final int CAT_WIN = 2;
    static final int DRAW = 0;

    int n;
    int[][] graph;
    int[][][] dp;

    public int catMouseGame(int[][] graph) {
        this.n = graph.length;
        this.graph = graph;
        this.dp = new int[n][n][n * 2];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        return getResult(1, 2, 0);

    }

    public int getResult(int mouse, int cat, int turns) {
        if (turns == n * 2) {
            return DRAW;
        }
        if (dp[mouse][cat][turns] < 0) {
            if (mouse == 0) {
                dp[mouse][cat][turns] = MOUSE_WIN;
            } else if (cat == mouse) {
                dp[mouse][cat][turns] = CAT_WIN;
            } else {
                getNextResult(mouse, cat, turns);
            }
        }
        return dp[mouse][cat][turns];
    }

    public void getNextResult(int mouse, int cat, int turns) {
        int curMove = turns % 2 == 0 ? mouse : cat;
        int defaultResult = curMove == mouse ? CAT_WIN : MOUSE_WIN;
        int result = defaultResult;
        int[] nextNodes = graph[curMove];
        for (int next : nextNodes) {
            if (curMove == cat && next == 0) {
                continue;
            }
            int nextMouse = curMove == mouse ? next : mouse;
            int nextCat = curMove == cat ? next : cat;
            int nextResult = getResult(nextMouse, nextCat, turns + 1);
            if (nextResult != defaultResult) {
                result = nextResult;
                if (result != DRAW) {
                    break;
                }
            }
        }
        dp[mouse][cat][turns] = result;
    }
}
