package chp8;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author WuChao
 * @since 2021/5/10 上午9:03
 */
public class _2 {
    /**
     * 程序员面试金典(version 6) - 面试题 08.02. 迷路的机器人
     * 难度: medium
     * <p>
     * 设想有个机器人坐在一个网格的左上角，网格 r 行 c 列。机器人只能向下或向右移动，但不能走到一些被禁止的网格（有障碍物）。
     * 设计一种算法，寻找机器人从左上角移动到右下角的路径。
     * <p>
     * 网格中的障碍物和空位置分别用 1 和 0 来表示。
     * 返回一条可行的路径，路径由经过的网格的行号和列号组成。左上角为 0 行 0 列。如果没有可行的路径，返回空数组。
     *
     * <p>
     * 示例:
     * 输入:
     * [
     * [0,0,0],
     * [0,1,0],
     * [0,0,0]
     * ]
     * 输出: [[0,0],[0,1],[0,2],[1,2],[2,2]]
     * 解释:
     * 输入中标粗的位置即为输出表示的路径，即
     * 0行0列（左上角） -> 0行1列 -> 0行2列 -> 1行2列 -> 2行2列（右下角）
     *
     * <p>
     * 数据范围：
     * r 和 c 的值均不超过 100。
     */

    /*
    思路：简单的深搜，使用回溯模板
     */

    List<List<Integer>> res = new LinkedList<>(); // 记录路径
    int r = 0; // 行数
    int c = 0; // 列数

    public List<List<Integer>> pathWithObstacles(int[][] obstacleGrid) {
        r = obstacleGrid.length;
        if (r == 0) {
            return res;
        }
        c = obstacleGrid[0].length;
        if (obstacleGrid[r - 1][c - 1] == 1) { // 终点有障碍无法到达，则直接返回空路径即可
            return res;
        }
        boolean[][] visited = new boolean[r][c];   // 记录访问过程数组
        backtrack(obstacleGrid, 0, 0, visited);// 从（0，0）格子开始走
        return res;
    }

    /**
     * 回溯算法
     * @param obstacleGrid 输入地图
     * @param x            当前格子行坐标
     * @param y            当前格子列坐标
     * @param visit        访问数组
     */
    public boolean backtrack(int[][] obstacleGrid, int x, int y, boolean[][] visit) {
        // 终止条件：越界，有障碍，已访问
        if (x >= r || y >= c || obstacleGrid[x][y] == 1 || visit[x][y]) {
            return false; // 无法到达当前格子
        }
        res.add(Arrays.asList(x, y));
        visit[x][y] = true;// 标记已经访问过
        // 选择后是否到达终点
        if (x == r - 1 && y == c - 1) {
            return true;
        }
        // 没到终点就选择往下走或者往右走
        if (backtrack(obstacleGrid, x + 1, y, visit) || backtrack(obstacleGrid, x, y + 1, visit)) {
            return true;
        }
        // 不能往下走或往右走了说明当前选择的格子不合适，撤销当前选择
        res.remove(res.size() - 1);
        return false;
    }
}
