package leetcode_everyday._2022.May;

import java.util.*;

/**
 * @author WuChao
 * @create 2022/5/23 9:16
 */
public class _23_675 {
    /**
     * 每日一题：2022/5/23
     * <p>
     * 675. 为高尔夫比赛砍树
     * <p>
     * 难度：hard
     * <p>
     * 你被请来给一个要举办高尔夫比赛的树林砍树。树林由一个 m x n 的矩阵表示， 在这个矩阵中：
     * <p>
     * 0 表示障碍，无法触碰
     * 1 表示地面，可以行走
     * 比 1 大的数 表示有树的单元格，可以行走，数值表示树的高度
     * 每一步，你都可以向上、下、左、右四个方向之一移动一个单位，如果你站的地方有一棵树，那么你可以决定是否要砍倒它。
     * <p>
     * 你需要按照树的高度从低向高砍掉所有的树，每砍过一颗树，该单元格的值变为 1（即变为地面）。
     * <p>
     * 你将从 (0, 0) 点开始工作，返回你砍完所有树需要走的最小步数。 如果你无法砍完所有的树，返回 -1 。
     * <p>
     * 可以保证的是，没有两棵树的高度是相同的，并且你至少需要砍倒一棵树。
     * <p>
     * 示例:
     * <p>
     * 输入：forest = [[1,2,3],[0,0,4],[7,6,5]]
     * <p>
     * 输出：6
     * <p>
     * 解释：沿着上面的路径，你可以用 6 步，按从最矮到最高的顺序砍掉这些树。
     * <p>
     * 范围
     * m == forest.length
     * n == forest[i].length
     * 1 <= m, n <= 50
     * 0 <= forest[i][j] <= 109
     */

    /*
    题目分析：
    题目要求从 (0, 0)(0,0) 开始并按照树的高度大小进行砍树并求出最小步数，假设所有树按照从高度从小到大的排序顺序为
    t_1, t_2, t_3, t_4, ... , t_n。设 d(x, y) 表示从 x 到 y 之间的步数，设 t_0 = (0, 0)，
    则可推出砍树的总的步数为 total=∑ i=0-n−1 d(t_i, t_i+1)，
    若使得 total 最小，只需满足所有的d(i,i+1) 都为最小，即可使得 total 最小，该题即转为求相邻树的两点之间的最短距离。

    思路：广度优先搜索
    首先对矩阵中的树按照树的高度进行排序，我们依次求出相邻的树之间的最短距离。利用广度优先搜索，按照层次遍历，处理队列中的节点（网格位置）。
    visited 记录在某个时间点已经添加到队列中的节点，这些节点已被处理或在等待处理的队列中。
    对于下一个要处理的每个节点，查看他们的四个方向上相邻的点，如果相邻的点没有被遍历过且不是障碍，将其加入到队列中，直到找到终点为止，
    返回当前的步数即可。最终返回所有的步数之和即为最终结果。
     */
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int cutOffTree(List<List<Integer>> forest) {
        List<int[]> trees = new ArrayList<>();
        int row = forest.size(), col = forest.get(0).size();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (forest.get(i).get(j) > 1) {
                    trees.add(new int[]{i, j});
                }
            }
        }
        Collections.sort(trees, (o1, o2) -> forest.get(o1[0]).get(o1[1]) - forest.get(o2[0]).get(o2[1]));

        int cx = 0, cy = 0;
        int ans = 0;
        for (int i = 0; i < trees.size(); i++) {
            int steps = bfs(forest, cx, cy, trees.get(i)[0], trees.get(i)[1]);
            if (steps == -1) {
                return -1;
            }
            ans += steps;
            cx = trees.get(i)[0];
            cy = trees.get(i)[1];
        }
        return ans;
    }

    public int bfs(List<List<Integer>> forest, int sx, int sy, int tx, int ty) {
        if (sx == tx && sy == ty) {
            return 0;
        }
        int row = forest.size();
        int col = forest.get(0).size();
        int step = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[row][col];
        queue.offer(new int[]{sx, sy});
        visited[sx][sy] = true;
        while (!queue.isEmpty()) {
            step++;
            int sz = queue.size();
            for (int i = 0; i < sz; i++) {
                int[] cell = queue.poll();
                int cx = cell[0], cy = cell[1];
                for (int j = 0; j < 4; j++) {
                    int nx = cx + dirs[j][0];
                    int ny = cy + dirs[j][1];
                    if (nx >= 0 && nx < row && ny >= 0 && ny < col) {
                        if (!visited[nx][ny] && forest.get(nx).get(ny) > 0) {
                            if (nx == tx && ny == ty) {
                                return step;
                            }
                            queue.offer(new int[]{nx, ny});
                            visited[nx][ny] = true;
                        }
                    }
                }
            }
        }
        return -1;

    }

}
