package com.leetcodetest.Jan;

public class _30 {
    /**
     * 每日一题：2021/1/30
     * 778. 水位上升的泳池中游泳
     * 难度: hard
     * 在一个 N x N 的坐标方格 grid 中，每一个方格的值 grid[i][j] 表示在位置 (i,j) 的平台高度。
     * 现在开始下雨了。当时间为 t 时，此时雨水导致水池中任意位置的水位为 t 。
     * 你可以从一个平台游向四周相邻的任意一个平台，但是前提是此时水位必须同时淹没这两个平台。
     * 假定你可以瞬间移动无限距离，也就是默认在方格内部游动是不耗时的。当然，在你游泳的时候你必须待在坐标方格里面。
     *
     * 你从坐标方格的左上平台 (0，0) 出发。最少 耗时多久你才能到达坐标方格的右下平台 (N-1, N-1)？
     * <p>
     * 示例：
     * 输入: [[0,2],[1,3]]
     * 输出: 3
     * 解释:
     * 时间为0时，你位于坐标方格的位置为 (0, 0)。
     * 此时你不能游向任意方向，因为四个相邻方向平台的高度都大于当前时间为 0 时的水位。
     * 等时间到达 3 时，你才可以游向平台 (1, 1). 因为此时的水位是 3，坐标方格中的平台没有比水位 3 更高的，所以你可以游向坐标方格中的任意位置
     *
     * 示例2:
     * 输入: [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
     * 输出: 16
     * 解释:
     *  0  1  2  3  4
     * 24 23 22 21  5
     * 12 13 14 15 16
     * 11 17 18 19 20
     * 10  9  8  7  6
     * <p>
     * 数据范围：
     * 2 <= N <= 50.
     * grid[i][j] 是 [0, ..., N*N - 1] 的排列。
     */


    /*
    题目解析：
    与29号的题目非常类似
    注意题目中的重要信息：假定你可以 瞬间移动 无限距离，游动不耗时。
    当前这个问题就转换成为：找一个时刻 t，使得这个二维网格上数值 小于等于 t 的部分，存在一条从左上角到右下角的路径。
    即：经过了时间 t 以后，可以瞬间从左上角（坐标 [0, 0]）游到右下角（坐标 [N - 1, N - 1]）。
     */

    /*
    解题思路1：二分查找+遍历
    根据题目解析可知，如果如果等待的时间 t 越少，网格上可以游泳的部分就越少，存在从左上角到右下角的一条路径的可能性越小；
    反之，如果等待的时间 t 越多，网格上可以游泳的部分就越多，存在从左上角到右下角的一条路径的可能性越大。

    因此可以知道该问题具有单调性，因此可以使用二分查找定位到最短等待时间。
    具体来说：在区间 [0, N * N - 1] 里猜一个整数，针对这个整数从起点（左上角）开始做一次深度优先遍历或者广度优先遍历。
        当小于等于该数值时，如果存在一条从左上角到右下角的路径，说明答案可能是这个数值，也可能更小；
        当小于等于该数值时，如果不存在一条从左上角到右下角的路径，说明答案一定比这个数值更大。
        按照这种方式不断缩小搜索的区间，最终找到最少等待时间。
     */

    /**
     * 思路1：二分查找+深度（广度）遍历
     *
     * @param grid
     * @return
     */
    private int N;
    public static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};    // 搜索方向

    public int swimInWater1(int[][] grid) {
        this.N = grid.length;

        int left = 0, right = N * N - 1;
        while (left < right) {
            // 要保证 left+right 不会溢出
            int mid = (left + right) / 2;
            boolean[][] visited = new boolean[N][N];
            if (grid[0][0] <= mid && dfs(grid, 0, 0, visited, mid)) {
                // mid 可以，尝试 mid 小一点是不是也可以呢？下一轮搜索的区间 [left, mid]
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;

    }

    /**
     * 使用深度优先遍历得到从 (x, y) 开始向四个方向的所有小于等于 threshold 且与 (x, y) 连通的结点
     *
     * @param grid
     * @param x
     * @param y
     * @param visited
     * @param threshold
     * @return
     */
    private boolean dfs(int[][] grid, int x, int y, boolean[][] visited, int threshold) {
        visited[x][y] = true;
        for (int[] direction : DIRECTIONS) {    // 向4个方向遍历
            int newX = x + direction[0];
            int newY = y + direction[1];
            if (inArea(newX, newY) && !visited[newX][newY] && grid[newX][newY] <= threshold) {
                if (newX == N - 1 && newY == N - 1) {
                    return true;
                }

                if (dfs(grid, newX, newY, visited, threshold)) {
                    return true;
                }
            }
        }
        return false;
    }

    // 边界判断 确保是在水池内
    private boolean inArea(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }


    /*
    思路2：并查集
    由于题目要我们找的是最少等待时间，可以模拟下雨的过程，把网格抽象成一个无权图，
    每经过一个时刻，就考虑此时和雨水高度相等的单元格，考虑这个单元格的上、下、左、右、四个方向，
    并且高度更低的单元格，把它们在并查集中进行合并。
     */

    public int swimInWater2(int[][] grid) {
        this.N = grid.length;
        int len = N * N;
        // 索引表示方格的高度，值是对应在方格中的坐标
        int[] index = new int[len];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                index[grid[i][j]] = getIndex(i, j);
            }
        }
        UnionFind unionFind = new UnionFind(len);
        for (int i = 0; i < len; i++) {
            int x = index[i] / N;
            int y = index[i] % N;

            for (int[] direction : DIRECTIONS) {
                int newX = x + direction[0];
                int newY = y + direction[1];
                if (inArea(newX, newY) && grid[newX][newY] <= i) {
                    unionFind.union(index[i], getIndex(newX, newY));
                }
                if (unionFind.isConnected(0, len - 1)) {
                    return i;
                }
            }
        }
        return -1;

    }

    private int getIndex(int x, int y) {
        return x * N + y;
    }


    // 并查集模板
    private class UnionFind {

        private int[] parent;

        public UnionFind(int n) {
            this.parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int root(int x) {
            while (x != parent[x]) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }

        public boolean isConnected(int x, int y) {
            return root(x) == root(y);
        }

        public void union(int p, int q) {
            if (isConnected(p, q)) {
                return;
            }
            parent[root(p)] = root(q);
        }
    }


    public void _21_1_30() {
        int[][] grid = {{0, 1, 2, 3, 4}, {24, 23, 22, 21, 5}, {12, 13, 14, 15, 16}, {11, 17, 18, 19, 20}, {10, 9, 8, 7, 6}};
        System.out.println(swimInWater1(grid));
        System.out.println(swimInWater2(grid));

    }
}
