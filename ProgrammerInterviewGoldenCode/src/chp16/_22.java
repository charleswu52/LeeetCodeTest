package chp16;

import java.util.*;

/**
 * @author WuChao
 * @since 2021/6/2 下午1:40
 */
public class _22 {
    /**
     * 程序员面试金典(version 6) -  面试题 16.22. 兰顿蚂蚁
     * 难度: medium
     * <p>
     * 一只蚂蚁坐在由白色和黑色方格构成的无限网格上。开始时，网格全白，蚂蚁面向右侧。每行走一步，蚂蚁执行以下操作。
     *
     * (1) 如果在白色方格上，则翻转方格的颜色，向右(顺时针)转 90 度，并向前移动一个单位。
     * (2) 如果在黑色方格上，则翻转方格的颜色，向左(逆时针方向)转 90 度，并向前移动一个单位。
     *
     * 编写程序来模拟蚂蚁执行的前 K 个动作，并返回最终的网格。
     *
     * 网格由数组表示，每个元素是一个字符串，代表网格中的一行，黑色方格由 'X' 表示，白色方格由 '_' 表示，
     * 蚂蚁所在的位置由 'L', 'U', 'R', 'D' 表示，分别表示蚂蚁 左、上、右、下 的朝向。只需要返回能够包含蚂蚁走过的所有方格的最小矩形。
     *
     *
     * <p>
     * 示例:
     * 输入: 0
     * 输出: ["R"]
     *
     * 输入: 2
     * 输出:
     * [
     *   "_X",
     *   "LX"
     * ]
     *
     * 输入: 5
     * 输出:
     * [
     *   "_U",
     *   "X_",
     *   "XX"
     * ]
     *
     * <p>
     * 数据范围：
     * K <= 100000
     */

    /*
    题目解析：模拟题
    按照题目理解走即可
    题目不了解的可以参考下面的示例
    0步：棋盘所有的位置都是白色，蚂蚁在棋盘内，R代表当前位置下它的头是朝右
_ _ _ _ _
_ _ _ _ _
_ _ R _ _
_ _ _ _ _
_ _ _ _ _
1步：由于蚂蚁第0步的时候脚下是白色，所以将之前位置反转黑色，右拐前进一格，D代表当前位置下它的头是朝下的
_ _ _ _ _
_ _ _ _ _
_ _ X _ _
_ _ D _ _
_ _ _ _ _
2步：由于蚂蚁第1步的时候脚下是白色，所以将之前位置反转黑色，右拐前进一格，L代表当前位置下它的头是朝左的
_ _ _ _ _
_ _ _ _ _
_ _ X _ _
_ L X _ _
_ _ _ _ _
3步：由于蚂蚁第2步的时候脚下是白色，所以将之前位置反转黑色，右拐前进一格，U代表当前位置下它的头是朝上的
_ _ _ _ _
_ _ _ _ _
_ U X _ _
_ X X _ _
_ _ _ _ _
4步：由于蚂蚁第3步的时候脚下是白色，所以将之前位置反转黑色，右拐前进一格，R代表当前位置下它的头是朝右的
_ _ _ _ _
_ _ _ _ _
_ X R _ _
_ X X _ _
_ _ _ _ _
5步：由于蚂蚁第4步的时候脚下是黑色，所以将之前位置反转白色，左拐前进一格，U代表当前位置下它的头是朝上的
_ _ _ _ _
_ _ U _ _
_ X _ _ _
_ X X _ _
_ _ _ _ _
结果要求的是返回能够包含蚂蚁走过的所有方格的最小矩形。
     */


    /*
    思路：
    实际上并不需要一个真正的网格棋盘，蚂蚁的下一步坐标可以由当前状态计算出，所以保存并时刻更新蚂蚁的状态，用集合保存块的位置，
    最后再通过集合生成字符串列表。
     */

    /**
     * 自定义 内部类 表示网格上的点
     */
    private class Position {
        // 横纵坐标
        int x, y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Position() {
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return x == position.x && y == position.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public List<String> printKMoves(int K) {
        char[] direction = {'L', 'U', 'R', 'D'};
        // 用“向量”记录方向，顺序与上一行方向的字符顺序保持一致，每个元素的后一个元素都是可以90°向右变换得到的
        int[][] offset = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        // 蚂蚁的位置
        Position antPos = new Position(0, 0);
        // 蚂蚁方向的向量序号
        int antDir = 2; // 初始面向右
        // 用集合存储所有黑块的坐标，一开始想再定义一个路径的坐标集合，发现可以直接用黑块+蚂蚁位置也能过
        Set<Position> blackSet = new HashSet<>();
        while (K > 0) {
            // 新的坐标对象用于放入集合
            Position t = new Position(antPos.x, antPos.y);
            // 如果黑块集合能存入，说明脚下的块不在集合中，也就意味着是白色，方向序号循环自增1
            if (blackSet.add(t)) antDir = (antDir + 1) % 4;
            else {
                // 否则说明脚下的块已经在集合中，也就意味着是黑色，方向序号循环自增3，相当于自减1，但是Math.floorMod取模可能消耗大？用+3替代
                antDir = (antDir + 3) % 4;
                // 别忘了删除，即将黑块变白
                blackSet.remove(t);
            }
            // 蚂蚁移动位置
            antPos.x += offset[antDir][0];
            antPos.y += offset[antDir][1];
            K--;
        }
        // 计算边界，即输出网格的行数和列数
        int left = antPos.x, top = antPos.y, right = antPos.x, bottom = antPos.y;
        for (Position pos : blackSet) {
            left = pos.x < left ? pos.x : left;
            top = pos.y < top ? pos.y : top;
            right = pos.x > right ? pos.x : right;
            bottom = pos.y > bottom ? pos.y : bottom;
        }
        char[][] grid = new char[bottom - top + 1][right - left + 1];
        // 填充白块
        for (char[] row : grid)
            Arrays.fill(row, '_');
        // 替换黑块
        for (Position pos : blackSet)
            grid[pos.y - top][pos.x - left] = 'X';
        // 替换蚂蚁
        grid[antPos.y - top][antPos.x - left] = direction[antDir];
        // 利用网格生成字符串列表
        List<String> result = new ArrayList<>();
        for (char[] row : grid)
            result.add(String.valueOf(row));
        return result;


    }
}
