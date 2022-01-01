package leetcode_everyday._2021.Nov;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author WuChao
 * @create 2021/11/9 9:26
 */
public class _9_488 {
    /**
     * 每日一题：2021/11/9
     * <p>
     * 488. 祖玛游戏
     * <p>
     * 难度：hard
     * <p>
     * 你正在参与祖玛游戏的一个变种。
     * <p>
     * 在这个祖玛游戏变体中，桌面上有 一排 彩球，每个球的颜色可能是：红色 'R'、黄色 'Y'、蓝色 'B'、绿色 'G' 或白色 'W' 。
     * 你的手中也有一些彩球。
     * <p>
     * 你的目标是 清空 桌面上所有的球。每一回合：
     * <p>
     * 从你手上的彩球中选出 任意一颗 ，然后将其插入桌面上那一排球中：两球之间或这一排球的任一端。
     * 接着，如果有出现 三个或者三个以上 且 颜色相同 的球相连的话，就把它们移除掉。
     * 如果这种移除操作同样导致出现三个或者三个以上且颜色相同的球相连，则可以继续移除这些球，直到不再满足移除条件。
     * 如果桌面上所有球都被移除，则认为你赢得本场游戏。
     * 重复这个过程，直到你赢了游戏或者手中没有更多的球。
     * 给你一个字符串 board ，表示桌面上最开始的那排球。另给你一个字符串 hand ，表示手里的彩球。
     * 请你按上述操作步骤移除掉桌上所有球，计算并返回所需的 最少 球数。如果不能移除桌上所有的球，返回 -1 。
     *
     *
     * <p>
     * 示例1：
     * <p>
     * 输入：board = "WRRBBW", hand = "RB"
     * <p>
     * 输出：-1
     * <p>
     * 解释：无法移除桌面上的所有球。可以得到的最好局面是：
     * - 插入一个 'R' ，使桌面变为 WRRRBBW 。WRRRBBW -> WBBW
     * - 插入一个 'B' ，使桌面变为 WBBBW 。WBBBW -> WW
     * 桌面上还剩着球，没有其他球可以插入。
     *
     * <p>
     * 范围
     * <p>
     * 1 <= board.length <= 16
     * 1 <= hand.length <= 5
     * board 和 hand 由字符 'R'、'Y'、'B'、'G' 和 'W' 组成
     * 桌面上一开始的球中，不会有三个及三个以上颜色相同且连着的球
     */

    /*
    参考题解：https://leetcode-cn.com/problems/zuma-game/solution/bao-li-dfstong-guo-hui-su-jie-jue-can-ka-25yn/
     */

    HashMap<String, Integer> cache;

    public int findMinStep(String board, String hand) {
        int[] hands = new int[5];
        cache = new HashMap<>();
        for (int i = 0; i < hand.length(); i++) {
            hands[getId(hand.charAt(i))]++;
        }
        return slove(board, hands);

    }

    private int slove(String board, int[] hands) {
        //
        board = xiaoxiaole(board);
        if (board.length() == 0) {
            return 0;
        }
        String node = board + Arrays.toString(hands);
        if (cache.containsKey(node)) {
            return cache.get(node);
        }
        int res = -1;
        for (int i = 0; i < hands.length; i++) {
            if (hands[i] == 0) {
                continue;
            }
            hands[i]--;
            for (int j = 0; j < board.length(); j++) {
                int ans = slove(board.substring(0, j) + getColor(i) + board.substring(j), hands);
                if (ans != -1) {
                    res = res == -1 ? ans + 1 : Math.min(res, ans + 1);
                }
            }
            hands[i]++;// 回溯

        }
        cache.put(node, res);
        return res;
    }

    private String xiaoxiaole(String board) {
        int s = 0;
        int len = board.length();
        for (int i = 1; i <= len; i++) {
            if (i == len || board.charAt(i) != board.charAt(i - 1)) {
                if (i - s >= 3) {
                    return xiaoxiaole(board.substring(0, s) + board.substring(i));
                } else {
                    s = i;
                }
            }
        }
        return board;
    }

    // 定义每种球颜色的id
    private int getId(char c) {
        switch (c) {
            case 'W':
                return 0;
            case 'B':
                return 1;
            case 'Y':
                return 2;
            case 'G':
                return 3;
            default:
                return 4;
        }
    }

    /**
     * 获取颜色
     * @param id
     * @return
     */
    private char getColor(int id) {
        switch (id) {
            case 0:
                return 'W';
            case 1:
                return 'B';
            case 2:
                return 'Y';
            case 3:
                return 'G';
            default:
                return 'R';
        }

    }
}
