

/**
 * @author WuChao
 * @since 2021/3/12 上午10:54
 */
public class _13 {
    /**
     * 剑指 Offer 13. 机器人的运动范围
     * 难度: medium
     * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。
     * 一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），
     * 也不能进入行坐标和列坐标的数位之和大于k的格子。
     *
     * 例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。
     *
     * 请问该机器人能够到达多少个格子？
     *
     * <p>
     * 示例：
     * 输入：m = 2, n = 3, k = 1
     * 输出：3
     *
     * <p>
     * 数据范围：
     * 1 <= n,m <= 100
     * 0 <= k <= 20
     */

    /**
     * DFS 深搜
     * 1. 首先创建visited数组用于记录已被访问的节点，已被访问设为1，还没被访问设为0，另外不能被访问的位置也设为1
     * 2. 因为从(0, 0)位置开始遍历数组，因此只会从往右和往下两个方向走
     * 3. 递归的返回值为该机器人能够到达的格子数量，每次递归1个格子，因此每次返回值 + 1：1 + dfs(x + 1, y) + dfs(x, y + 1)
     * 4. 递归中，如果当前位置(x, y)不在方格范围内或者已被访问过，返回0
     *
     */
    int m,n;
    int[][] visited;


    public int movingCount(int m, int n, int k) {
        this.m = m;
        this.n = n;
        this.visited = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i / 10 + i % 10 + j / 10 + j % 10 > k) {
                    visited[i][j] = 1; // 不能访问的位置设置为1
                }
            }
        }
        return dfs(0, 0);
    }
    public int dfs(int x,int y) {
        if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y] == 1) {
            return 0;
        }
        visited[x][y] = 1;
        return 1 + dfs(x + 1, y) + dfs(x, y + 1);   // 只能往右和下两个方向走
    }


    public void sword2Offer_13() {
        int m = 3, n = 2, k = 17;
        System.out.println(movingCount(m, n, k));
    }
}
