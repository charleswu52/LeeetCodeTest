package leetcode_everyday._2021.Dec;

/**
 * @author WuChao
 * @create 2021/12/9 10:45
 */
public class _9_794 {
    /**
     * 每日一题：2021/12/9
     * 689. 三个无重叠子数组的最大和
     * 难度：hard
     * <p>
     * 给你一个字符串数组 board 表示井字游戏的棋盘。当且仅当在井字游戏过程中，棋盘有可能达到 board 所显示的状态时，才返回 true 。
     * <p>
     * 井字游戏的棋盘是一个 3 x 3 数组，由字符 ' '，'X' 和 'O' 组成。字符 ' ' 代表一个空位。
     * <p>
     * 以下是井字游戏的规则：
     * <p>
     * 玩家轮流将字符放入空位（' '）中。
     * 玩家 1 总是放字符 'X' ，而玩家 2 总是放字符 'O' 。
     * 'X' 和 'O' 只允许放置在空位中，不允许对已放有字符的位置进行填充。
     * 当有 3 个相同（且非空）的字符填充任何行、列或对角线时，游戏结束。
     * 当所有位置非空时，也算为游戏结束。
     * 如果游戏结束，玩家不允许再放置字符。
     *
     * <p>
     * 示例1
     * <p>
     * 输入：board = ["O  ","   ","   "]
     * <p>
     * 输出：false
     * <p>
     * 解释：玩家 1 总是放字符 "X" 。
     * <p>
     * 示例2
     * <p>
     * 输入：board = ["XOX"," X ","   "]
     * <p>
     * 输出：false
     * <p>
     * 解释：玩家应该轮流放字符。
     * <p>
     * 范围
     * <p>
     * board.length == 3
     * board[i].length == 3
     * board[i][j] 为 'X'、'O' 或 ' '
     */

    /*
    题目解析：
    1、X必须比O多1个或者相同，否则false；
    2、X和O不能同时都是赢家，否则false；
    3、X赢的时候必须比O多1个，否则false；
    4、O赢得时候个数与X相同，否则false；
    5、其他的都是true
     */
    public boolean validTicTacToe(String[] board) {
        int xCount = 0, oCount = 0;
        for (String row : board) {
            for (char c : row.toCharArray()) {
                xCount = (c == 'X') ? (xCount + 1) : xCount;
                oCount = (c == 'O') ? (oCount + 1) : oCount;
            }
        }
        if (oCount != xCount && oCount != xCount - 1) {
            return false;
        }
        if (win(board, 'X') && oCount != xCount - 1) {
            return false;
        }
        if (win(board, 'O') && oCount != xCount) {
            return false;
        }
        return true;
    }

    public boolean win(String[] board, char p) {
        for (int i = 0; i < 3; i++) {
            if (p == board[0].charAt(i) && p == board[1].charAt(i) && p == board[2].charAt(i)) {
                return true;
            }
            if (p == board[i].charAt(0) && p == board[i].charAt(1) && p == board[i].charAt(2)) {
                return true;
            }
        }
        if (p == board[0].charAt(0) && p == board[1].charAt(1) && p == board[2].charAt(2)) {
            return true;
        }
        if (p == board[0].charAt(2) && p == board[1].charAt(1) && p == board[2].charAt(0)) {
            return true;
        }
        return false;

    }

}
