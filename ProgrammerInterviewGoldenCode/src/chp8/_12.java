package chp8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WuChao
 * @since 2021/5/22 上午9:15
 */
public class _12 {
    /**
     * 程序员面试金典(version 6) - 面试题 08.12. 八皇后
     * 难度: hard
     * <p>
     * 设计一种算法，打印 N 皇后在 N × N 棋盘上的各种摆法，其中每个皇后都不同行、不同列，也不在对角线上。
     * 这里的“对角线”指的是所有的对角线，不只是平分整个棋盘的那两条对角线。
     * <p>
     * 注意：本题相对原题做了扩展
     * <p>
     * 示例:
     * 输入：4
     * 输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
     * 解释: 4 皇后问题存在如下两个不同的解法。
     * [
     * [".Q..",  // 解法 1
     * "...Q",
     * "Q...",
     * "..Q."],
     * <p>
     * ["..Q.",  // 解法 2
     * "Q...",
     * "...Q",
     * ".Q.."]
     * ]
     * <p>
     * 数据范围：
     */

    /*
    思路：回溯模板
     */
    List<List<String>> res;
    public List<List<String>> solveNQueens(int n) {
        // 定义棋盘并初始化
        char[][] chess = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                chess[i][j] = '.';
            }
        }
        res = new ArrayList<>();
        backtrack(chess, 0);
        return res;

    }


    public void backtrack(char[][] chess, int row) {
        if (row == chess.length) {
            res.add(generate(chess));
            return;
        }
        int n = chess.length;
        for (int col = 0; col < n; col++) {
            if (!isValid(chess, row, col)) {
                continue;
            }
            chess[row][col] = 'Q';
            backtrack(chess, row + 1);
            chess[row][col] = '.';
        }
    }

    /**
     * 判断在[row,col]位置是否可放置皇后
     * @param chess
     * @param row
     * @param col
     * @return
     */
    public boolean isValid(char[][] chess, int row, int col) {
        //判断当前列有没有皇后,因为他是一行一行往下走的，我们只需要检查走过的行数即可，通俗一点就是判断当前坐标位置的 上面 有没有皇后

        // 检查坐标在一列上的上面的元素是否有皇后
        for (int i = 0; i < row; i++) {
            if (chess[i][col] == 'Q') {
                return false;
            }
        }

        // 检查当前坐标右上角有没有皇后
        for (int i = row - 1, j = col + 1; i >= 0 && j < chess.length; i--, j++) {
            if (chess[i][j] == 'Q') {
                return false;
            }
        }

        //检查当前坐标左上角有没有皇后
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (chess[i][j] == 'Q') {
                return false;
            }
        }

        return true;

    }

    /**
     * 将棋盘转化为List<String> 的形式
     * @param chess
     * @return
     */
    public List<String> generate(char[][] chess) {
        List<String> path = new ArrayList<>();
        for (int i = 0; i < chess.length; i++) {
            path.add(new String(chess[i]));
        }
        return path;
    }

    @Test
    public void test() {
        int n = 30;
        System.out.println(solveNQueens(n));
    }



}

