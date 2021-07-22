package _210705.shoppe;


import org.junit.Test;

import java.util.ArrayList;

/**
 * @author WuChao
 * @create 2021/7/5 15:26
 */
public class Main_2 {
    int res = Integer.MAX_VALUE;
    int sum = 0;
    int m, n;
    boolean[][] visited;

    public int minimumInitHealth(int[][] rooms, int[] startPoint, int[] endPoint) {
        m = rooms.length;
        n = rooms[0].length;
        visited = new boolean[m][n];
        int fromRow = startPoint[0];
        int fromCol = startPoint[1];
        int toRow = endPoint[0];
        int toCol = endPoint[1];
        dfs(rooms, fromRow, fromCol, toRow, toCol, visited);
        return res < 0  ? Math.abs(res) : 1;


    }


    public void dfs(int[][] rooms, int fromRow, int fromCol, int toRow, int toCol, boolean[][] visited) {
        if (fromRow < 0 || fromRow >= m || fromCol < 0 || fromCol >= n || visited[fromRow][fromCol]) {
            return;
        }
        visited[fromRow][fromCol] = true;
        if (fromRow == toRow && fromCol == toCol) {
            return;
        }
        sum += rooms[fromRow][fromCol];
        res = Math.min(res, sum);
        dfs(rooms, fromRow - 1, fromCol, toRow, toCol, visited);
        dfs(rooms, fromRow + 1, fromCol, toRow, toCol, visited);
        dfs(rooms, fromRow, fromCol - 1, toRow, toCol, visited);
        dfs(rooms, fromRow, fromCol + 1, toRow, toCol, visited);
        sum -= rooms[fromRow][fromCol];
    }

    @Test
    public void test2() {
        int[][] rooms = {{-2, 3, 3}, {-5, -10, 1}, {10, 30, -5}};
        int[] startPoint = {0, 0};
        int[] endPoint = {2, 2};
        int[][] rooms2 = {{100}};
        System.out.println(minimumInitHealth(rooms, startPoint, endPoint));
    }


    public static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public static boolean[][] vis = new boolean[300][300];

    public static int minimumInitHealth2(int[][] rooms, int[] startPoint, int[] endPoint) {
        if(startPoint[0] == endPoint[0] && startPoint[1] == endPoint[1]) {
            return Math.max(1 - rooms[startPoint[0]][startPoint[1]], 1);
        }
        int x0 = startPoint[0];
        int y0 = startPoint[1];
        vis[x0][y0] = true;
        int minHealth = 0x3f3f3f3f;
        for(int d = 0; d < 4; ++d) {
            int x = x0 + dir[d][0];
            int y = y0 + dir[d][1];
            if(!((x >= 0 && x < rooms.length) && (y >= 0 && y < rooms[0].length))) continue;
            if(vis[x][y]) continue;
            int[] point = {x, y};
            int health = minimumInitHealth2(rooms, point, endPoint);
            minHealth = Math.min(health, minHealth);
        }
        vis[x0][y0] = false;
        return Math.max(minHealth-rooms[x0][y0], 1);
    }

    public static void main(String[] args) {
//        int[][] rooms = {{-2, -3, 3}, {-5, -10, 1}, {10, 30, -5}};
        int[][] rooms = {{100}};
        int[] startPoint = {0, 0};
        int[] endPoint = {2, 2};
        int ans = minimumInitHealth2(rooms, startPoint, startPoint);
        System.out.println(ans);
        ArrayList<String> s = new ArrayList<>();

    }
}
