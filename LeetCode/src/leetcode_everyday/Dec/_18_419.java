package leetcode_everyday.Dec;

/**
 * @author WuChao
 * @create 2021/12/18 10:11
 */
public class _18_419 {
    /**
     * 每日一题：2021/12/18
     * <p>
     * 419. 甲板上的战舰
     * <p>
     * 难度：medium
     * <p>
     * 给你一个大小为 m x n 的矩阵 board 表示甲板，其中，每个单元格可以是一艘战舰 'X' 或者是一个空位 '.' ，
     * 返回在甲板 board 上放置的 战舰 的数量。
     * <p>
     * 战舰 只能水平或者垂直放置在 board 上。换句话说，战舰只能按 1 x k（1 行，k 列）或 k x 1（k 行，1 列）的形状建造，
     * 其中 k 可以是任意大小。两艘战舰之间至少有一个水平或垂直的空位分隔 （即没有相邻的战舰）。
     *
     * <p>
     * 示例 1：
     * <p>
     * 输入：board = [["X",".",".","X"],[".",".",".","X"],[".",".",".","X"]]
     * 输出：2
     *
     * <p>
     * 范围
     * <p>
     * m == board.length
     * n == board[i].length
     * 1 <= m, n <= 200
     * board[i][j] 是 '.' 或 'X'
     * <p>
     * 进阶
     * <p>
     * 你可以实现一次扫描算法，并只使用 O(1) 额外空间，并且不修改 board 的值来解决这个问题吗？
     **/

    /*
    思路：一次扫描 空间O(1)
    脑筋急转弯：遇到标记为'X'的格子，判断其左边格子或者上边格子是否为'X'如果不是则说明其是战舰的头，可以奇数，否则不计数
     */
    public int countBattleships(char[][] board) {
        int ans = 0;
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'X') {
                    if (i > 0 && board[i - 1][j] == 'X') {
                        continue;
                    }
                    if (j > 0 && board[i][j - 1] == 'X') {
                        continue;
                    }
                    ans++;
                }
            }
        }
        return ans;


    }
}
