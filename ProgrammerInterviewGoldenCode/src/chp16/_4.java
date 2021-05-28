package chp16;

/**
 * @author WuChao
 * @since 2021/5/28 上午9:00
 */
public class _4 {
    /**
     * 程序员面试金典(version 6) - 面试题 16.04. 井字游戏
     * 难度: medium
     * <p>
     * 设计一个算法，判断玩家是否赢了井字游戏。输入是一个 N x N 的数组棋盘，由字符" "，"X"和"O"组成，其中字符" "代表一个空位。
     * <p>
     * 以下是井字游戏的规则：
     * <p>
     * 玩家轮流将字符放入空位（" "）中。
     * 第一个玩家总是放字符"O"，且第二个玩家总是放字符"X"。
     * "X"和"O"只允许放置在空位中，不允许对已放有字符的位置进行填充。
     * 当有N个相同（且非空）的字符填充任何行、列或对角线时，游戏结束，对应该字符的玩家获胜。
     * 当所有位置非空时，也算为游戏结束。
     * 如果游戏结束，玩家不允许再放置字符。
     * 如果游戏存在获胜者，就返回该游戏的获胜者使用的字符（"X"或"O"）；如果游戏以平局结束，则返回 "Draw"；如果仍会有行动（游戏未结束），则返回 "Pending"。
     *
     * <p>
     * 示例:
     * 输入： board = ["O X"," XO","X O"]
     * 输出： "X"
     * <p>
     * 输入： board = ["OOX","XXO","OXO"]
     * 输出： "Draw"
     * 解释： 没有玩家获胜且不存在空位
     * <p>
     * 数据范围：
     * 1 <= board.length == board[i].length <= 100
     * 输入一定遵循井字棋规则
     */
    public String tictactoe(String[] board) {
        int len = board.length;
        char[][] store = new char[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < board[i].length(); j++) {
                store[i][j] = board[i].charAt(j);
            }
        }
        boolean isBlackSpace = false;
        for (int i = 0; i < len; i++) {
            int temp1 = 0, temp2 = 0;
            for (int j = 0; j < len; j++) {
                switch (store[i][j]) {
                    case 'X':
                        temp1 += 1;
                        break;
                    case 'O':
                        temp1 -= 1;
                        break;
                    default:
                        isBlackSpace = true;
                        break;
                }
                switch (store[j][i]) {
                    case 'X':
                        temp2 += 1;
                        break;
                    case 'O':
                        temp2 -= 1;
                        break;
                    default:
                        isBlackSpace = true;
                        break;
                }
            }
            if (temp1 == -len) {
                return "O";
            } else if (temp1 == len) {
                return "X";
            } else if (temp2 == -len) {
                return "O";
            } else if (temp2 == len) {
                return "X";
            }

        }
        int temp3 = 0, temp4 = 0;
        for (int i = 0, j = len - 1; i < len; i++, j--) {
            switch (store[i][i]) {
                case 'X':
                    temp3 += 1;
                    break;
                case 'O':
                    temp3 -= 1;
                    break;
                default:
                    isBlackSpace = true;
                    break;
            }
            switch (store[i][j]) {
                case 'X':
                    temp4 += 1;
                    break;
                case 'O':
                    temp4 -= 1;
                    break;
                default:
                    isBlackSpace = true;
                    break;
            }

        }
        if (temp3 == -len) {
            return "O";
        } else if (temp3 == len) {
            return "X";
        } else if (temp4 == -len) {
            return "O";
        } else if (temp4 == len) {
            return "X";
        }


        if (isBlackSpace) {
            return "Pending";
        } else {
            return "Draw";
        }

    }
}
