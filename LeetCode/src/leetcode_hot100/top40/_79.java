package leetcode_hot100.top40;

import org.junit.Test;

/**
 * @author WuChao
 * @create 2021/7/12 10:26
 */
public class _79 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 79. 单词搜索
     * 难度：medium
     * <p>
     * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
     * <p>
     * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
     * 同一个单元格内的字母不允许被重复使用。
     * <p>
     * 示例
     * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
     * 输出：true
     * <p>
     * <p>
     * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
     * 输出：false
     * <p>
     * 范围：
     * m == board.length
     * n = board[i].length
     * 1 <= m, n <= 6
     * 1 <= word.length <= 15
     * board 和 word 仅由大小写英文字母组成
     * <p>
     * 进阶：你可以使用搜索剪枝的技术来优化解决方案，使其在 board 更大的情况下可以更快解决问题？
     */

    char[][] board;
    String word;
    boolean[][] visited;
    boolean res;

    /*
    思路：DFS回溯
    问题 ； 剪枝策略不正确 超时
     */
    public boolean exist(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        this.board = board;
        this.visited = new boolean[m][n];
        this.word = word;
        this.res = false;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (res) {
                    break;
                }
                dfs(i, j, m, n, new StringBuilder());
            }
        }

        return res;

    }

    public void dfs(int fromX, int fromY, int toX, int toY, StringBuilder stringBuilder) {
        if (res) {
            return;
        }
        if (fromX < 0 || fromX >= toX || fromY < 0 || fromY >= toY) {
            return;
        }
        if (visited[fromX][fromY]) {
            return;
        }
        stringBuilder.append(board[fromX][fromY]);
        visited[fromX][fromY] = true;
        if (stringBuilder.length() == word.length() && stringBuilder.toString().equals(word)) {
            res = true;
            return;
        }
        dfs(fromX - 1, fromY, toX, toY, stringBuilder);
        dfs(fromX + 1, fromY, toX, toY, stringBuilder);
        dfs(fromX, fromY - 1, toX, toY, stringBuilder);
        dfs(fromX, fromY + 1, toX, toY, stringBuilder);
        stringBuilder.setLength(stringBuilder.length() - 1);
        visited[fromX][fromY] = false;
    }

    @Test
    public void test() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("hello world");
        System.out.println(stringBuilder.toString());
        stringBuilder.setLength(stringBuilder.length() - 1);
        System.out.println(stringBuilder);
    }


    /*
    参考思路：回溯
    优化搜索与剪枝策略

    设函数 check(i,j,k) 表示判断以网格的 (i,j) 位置出发，能否搜索到单词 word[k..]，其中 word[k..] 表示字符串 word
    从第 k 个字符开始的后缀子串。如果能搜索到，则返回 true，反之返回 false。函数 check(i,j,k) 的执行步骤如下：
        如果 board[i][j]！=s[k]，当前字符不匹配，直接返回 false。
        如果当前已经访问到字符串的末尾，且对应字符依然匹配，此时直接返回 true。
        否则，遍历当前位置的所有相邻位置。如果从某个相邻位置出发，能够搜索到子串 word[k+1..]，则返回 true，否则返回 false。
    这样，我们对每一个位置 (i,j) 都调用函数 check(i,j,0) 进行检查：只要有一处返回 true，就说明网格中能够找到相应的单词，否则说明不能找到。
    为了防止重复遍历相同的位置，需要额外维护一个与 board 等大的 visited 数组，用于标识每个位置是否被访问过。
    每次遍历相邻位置时，需要跳过已经被访问的位置。
     */

    public boolean exist2(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        this.board = board;
        this.visited = new boolean[m][n];
        this.word = word;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                boolean flag = check(i, j, 0);
                if (flag) {
                    return true;
                }
            }
        }
        return false;

    }

    public boolean check(int i, int j, int k) {
        if (board[i][j] != word.charAt(k)) {
            return false;
        } else if (k == word.length() - 1) {
            return true;
        }
        visited[i][j] = true;
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        boolean ans = false;
        for (int[] dir : directions) {
            int newI = i + dir[0], newJ = j + dir[1];
            if (newI >= 0 && newI < board.length && newJ >= 0 && newJ < board[0].length) {
                if (!visited[newI][newJ]) {
                    boolean flag = check(newI, newJ, k + 1);
                    if (flag) {
                        ans = true;
                        break;
                    }
                }
            }
        }
        visited[i][j] = false;
        return ans;
    }
}
