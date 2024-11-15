package leetcode_everyday._2021.Aug;

/**
 * @author WuChao
 * @create 2021/8/23 15:51
 */
public class _22_789 {
    /**
     * 每日一题：2021/8/22
     * 789. 逃脱阻碍者
     * 难度：medium
     * <p>
     * 你在进行一个简化版的吃豆人游戏。你从 [0, 0] 点开始出发，你的目的地是 target = [xtarget, ytarget] 。地图上有一些阻碍者，
     * 以数组 ghosts 给出，第 i 个阻碍者从 ghosts[i] = [xi, yi] 出发。所有输入均为 整数坐标 。
     *
     * 每一回合，你和阻碍者们可以同时向东，西，南，北四个方向移动，每次可以移动到距离原位置 1 个单位 的新位置。
     * 当然，也可以选择 不动 。所有动作 同时 发生。
     *
     * 如果你可以在任何阻碍者抓住你 之前 到达目的地（阻碍者可以采取任意行动方式），则被视为逃脱成功。
     * 如果你和阻碍者同时到达了一个位置（包括目的地）都不算是逃脱成功。
     *
     * 只有在你有可能成功逃脱时，输出 true ；否则，输出 false 。
     *
     * <p>
     * 示例 1：
     * 输入：ghosts = [[1,0],[0,3]], target = [0,1]
     * 输出：true
     * 解释：你可以直接一步到达目的地 (0,1) ，在 (1, 0) 或者 (0, 3) 位置的阻碍者都不可能抓住你。
     *
     * 示例 2：
     * 输入：ghosts = [[1,0]], target = [2,0]
     * 输出：false
     * 解释：你需要走到位于 (2, 0) 的目的地，但是在 (1, 0) 的阻碍者位于你和目的地之间。
     *
     * 示例 3：
     * 输入：ghosts = [[2,0]], target = [1,0]
     * 输出：false
     * 解释：阻碍者可以和你同时达到目的地。
     *
     * 示例 4：
     * 输入：ghosts = [[5,0],[-10,-2],[0,-5],[-2,-2],[-7,1]], target = [7,7]
     * 输出：false
     *
     * 示例 5：
     * 输入：ghosts = [[-1,0],[0,1],[-1,0],[0,1],[-1,0]], target = [0,0]
     * 输出：true
     *
     * <p>
     * 注意:
     * 1 <= ghosts.length <= 100
     * ghosts[i].length == 2
     * -104 <= xi, yi <= 104
     * 同一位置可能有 多个阻碍者 。
     * target.length == 2
     * -10^4 <= xtarget, ytarget <= 10^4
     *
     */

    /*
    题目分析：
    引入 曼哈顿距离
    为了逃脱阻碍者，玩家应按照最短路径向目的地移动。阻碍者为了抓住玩家，也会按照最短路径向目的地移动。
    由于每次移动为向四个方向之一移动一个单位，因此对于玩家和阻碍者而言，到达目的地的最短路径的距离为当前所在位置和目的地的曼哈顿距离。

    用 dist(A,B) 表示 A 点和 B 点的曼哈顿距离，曼哈顿距离的计算方法如下：
        dist(A,B)= | x_A - x_B | + | y_A - y_B |

    如果有一个阻碍者和目的地的曼哈顿距离小于玩家和目的地的曼哈顿距离，则该阻碍者可以在玩家之前到达目的地，然后停在目的地，玩家无法逃脱。
    如果有一个阻碍者和目的地的曼哈顿距离等于玩家和目的地的曼哈顿距离，则该阻碍者可以和玩家同时到达目的地，玩家也无法逃脱。
    如果所有的阻碍者和目的地的曼哈顿距离都大于玩家和目的地的曼哈顿距离，则玩家可以在阻碍者之前到达目的地。

    基于上述分析，问题简化为计算玩家和目的地的曼哈顿距离以及每个阻碍者和目的地的曼哈顿距离，判断玩家是否可以在阻碍者之前到达目的地。
        如果存在至少一个阻碍者和目的地的曼哈顿距离小于或等于玩家和目的地的曼哈顿距离，返回  false；
        如果所有阻碍者和目的地的曼哈顿距离都大于玩家和目的地的曼哈顿距离，返回 true。
     */

    public boolean escapeGhosts(int[][] ghosts, int[] target) {
        int[] source = {0, 0};
        int dist = manhattanDistance(source, target);
        for (int[] points : ghosts) {
            int ghostDist = manhattanDistance(points, target);
            if (ghostDist <= dist) {
                return false;
            }
        }
        return true;

    }

    public int manhattanDistance(int[] pouint1, int[] point2) {
        return Math.abs(pouint1[0] - point2[0]) + Math.abs(pouint1[1] - point2[1]);
    }

}
