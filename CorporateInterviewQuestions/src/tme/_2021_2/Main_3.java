package tme._2021_2;

import java.util.Scanner;

/**
 * @author WuChao
 * @create 2021/6/20 13:51
 */
public class Main_3 {
    /**
     * 腾讯音乐娱乐（TME）2021暑期实习生招聘技术类笔试题
     * [编程题]三国鼎立
     * 在 n*m 的棋盘格状土地上盘踞着三个国家的若干股势力，上下左右相邻的属于同一个国家的土地被认为是同一股势力。
     * 现在想知道，土地上总共有多少股势力？
     * <p>
     * 输入描述:
     * 第一行两个正整数，土地宽 n ，长 m ；
     * 接下来一个 n*m 矩阵，今包含 '1', '2' ,'3' 表示土地上的国家分布。
     * <p>
     * 输出描述:
     * 一个正整数，势力股数。
     * <p>
     * 输入例子1:
     * 4 4
     * 1122
     * 1222
     * 3111
     * 3333
     * <p>
     * 输出例子1:
     * 4
     * <p>
     * 例子说明1:
     * 11
     * 1
     * 是1国的一块势力
     * 22
     * 222
     * 是2国一块势力
     * 3
     * 3333
     * 是3国一块势力
     * 111
     * 是1国的另一块势力
     * 总共4块势力
     */

    /*
    DFS 搜索
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n, m;
        String s = scanner.nextLine();
        String[] s1 = s.split(" ");
        n = Integer.parseInt(s1[0]);
        m = Integer.parseInt(s1[1]);
        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            String str = scanner.nextLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(String.valueOf(str.charAt(j)));
            }
        }
        boolean[][] visited = new boolean[n][m];
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j]) {
                    dfs(map, i, j, map[i][j], visited);
                    res++;
                }
            }
        }
        System.out.println(res);

    }

    public static void dfs(int[][] map, int i, int j, int target, boolean[][] visited) {
        if (i < 0 || j < 0 || i >= map.length || j >= map[0].length || visited[i][j] || map[i][j] != target) {
            return;
        }
        visited[i][j] = true;
        dfs(map, i + 1, j, target, visited);
        dfs(map, i - 1, j, target, visited);
        dfs(map, i, j + 1, target, visited);
        dfs(map, i, j - 1, target, visited);
    }
}
