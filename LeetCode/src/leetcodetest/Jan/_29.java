package leetcodetest.Jan;

import java.util.*;

public class _29 {
    /**
     * 每日一题：2021/1/29
     * 1631. 最小体力消耗路径
     * 难度: medium
     * 你准备参加一场远足活动。给你一个二维 rows x columns 的地图 heights ，其中 heights[row][col] 表示格子 (row, col) 的高度。
     * 一开始你在最左上角的格子 (0, 0) ，且你希望去最右下角的格子 (rows-1, columns-1)（注意下标从 0 开始编号）。
     * 你每次可以往 上，下，左，右 四个方向之一移动，你想要找到耗费 体力 最小的一条路径。
     *
     * 一条路径耗费的 体力值 是路径上相邻格子之间 高度差绝对值 的 最大值 决定的。
     *
     * 请你返回从左上角走到右下角的 最小体力消耗值 。
     * <p>
     * 示例：
     * 输入：heights = [[1,2,2],[3,8,2],[5,3,5]]
     * 输出：2
     * 解释：路径 [1,3,5,3,5] 连续格子的差值绝对值最大为 2 。
     * 这条路径比路径 [1,2,2,2,5] 更优，因为另一条路径差值最大值为 3 。
     * <p>
     * 数据范围：
     * rows == heights.length
     * columns == heights[i].length
     * 1 <= rows, columns <= 100
     * 1 <= heights[i][j] <= 106
     */

    /*
    题目比较易懂，主要介绍不同的解题方法
     */

    /**
     * 思路1： 二分查找
     * 可以将这个问题转化成一个「判定性」问题，即：
     * 是否存在一条从左上角到右下角的路径，其经过的所有边权的最大值不超过 x？
     *
     * 这个判定性问题解决起来并不复杂，我们只要从左上角开始进行深度优先搜索或者广度优先搜索，在搜索的过程中只允许经过边权不超过 x 的边，
     * 搜索结束后判断是否能到达右下角即可。
     *
     * 随着 x 的增大，原先可以经过的边仍然会被保留，因此如果当 x = x_0 时，我们可以从左上角到达右下角，那么当 x > x_0 时同样也可以可行的。
     * 因此我们可以使用二分查找的方法，找出满足要求的最小的那个 x 值，记为 x_ans，那么：
     * 当 x < x_ans，我们无法从左上角到达右下角；
     * 当 x ≥ x_ans，我们可以从左上角到达右下角。
     *
     * 由于格子的高度范围为 [1, 10^6],因此我们可以 [0, 10^6-1]的范围内对 x 进行二分查找。在每一步查找的过程中，
     * 我们使用进行深度优先搜索或者广度优先搜索判断是否可以从左上角到达右下角，并根据判定结果更新二分查找的左边界或右边界即可。
     */

    /**
     * 二分查找的BFS方法实现
     *
     * @param heights
     * @return
     */
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int minimumEffortPath1(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        int left = 0, right = 999999, ans = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            Queue<int[]> queue = new LinkedList<int[]>();
            queue.offer(new int[]{0, 0});
            boolean[] seen = new boolean[m * n];
            seen[0] = true;
            while (!queue.isEmpty()) {
                int[] cell = queue.poll();
                int x = cell[0], y = cell[1];
                for (int i = 0; i < 4; ++i) {
                    int nx = x + dirs[i][0];
                    int ny = y + dirs[i][1];
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n && !seen[nx * n + ny] && Math.abs(heights[x][y] - heights[nx][ny]) <= mid) {
                        queue.offer(new int[]{nx, ny});
                        seen[nx * n + ny] = true;
                    }
                }
            }
            if (seen[m * n - 1]) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    /**
     * 思路2：并查集
     * 我们将这 m*n 个节点放入并查集中，实时维护它们的连通性。
     * 由于我们需要找到从左上角到右下角的最短路径，因此我们可以将图中的所有边按照权值从小到大进行排序，并依次加入并查集中。
     * 当我们加入一条权值为 x 的边之后，如果左上角和右下角从非连通状态变为连通状态，那么 x 即为答案。
     */

    // 并查集模板
    class UnionFind {
        int[] parent;
        int[] size;
        int n;
        // 当前连通分量数目
        int setCount;

        public UnionFind(int n) {
            this.n = n;
            this.setCount = n;
            this.parent = new int[n];
            this.size = new int[n];
            Arrays.fill(size, 1);
            for (int i = 0; i < n; ++i) {
                parent[i] = i;
            }
        }

        public int findset(int x) {
            return parent[x] == x ? x : (parent[x] = findset(parent[x]));
        }

        public boolean unite(int x, int y) {
            x = findset(x);
            y = findset(y);
            if (x == y) {
                return false;
            }
            if (size[x] < size[y]) {
                int temp = x;
                x = y;
                y = temp;
            }
            parent[y] = x;
            size[x] += size[y];
            --setCount;
            return true;
        }

        public boolean connected(int x, int y) {
            x = findset(x);
            y = findset(y);
            return x == y;
        }
    }
    /**
     * 并查集方法实现
     * @param heights
     * @return
     */
    public int minimumEffortPath2(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        List<int[]> edges = new ArrayList<int[]>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int id = i * n + j;
                if (i > 0) {
                    edges.add(new int[]{id - n, id, Math.abs(heights[i][j] - heights[i - 1][j])});
                }
                if (j > 0) {
                    edges.add(new int[]{id - 1, id, Math.abs(heights[i][j] - heights[i][j - 1])});
                }
            }
        }
        Collections.sort(edges, new Comparator<int[]>() {
            public int compare(int[] edge1, int[] edge2) {
                return edge1[2] - edge2[2];
            }
        });

        UnionFind uf = new UnionFind(m * n);
        int ans = 0;
        for (int[] edge : edges) {
            int x = edge[0], y = edge[1], v = edge[2];
            uf.unite(x, y);
            if (uf.connected(0, m * n - 1)) {
                ans = v;
                break;
            }
        }
        return ans;
    }

        public void _21_1_29() {
        int[][] heights = {{1, 2, 2}, {3, 8, 2}, {5, 3, 5}};

        System.out.println(minimumEffortPath1(heights));
    }
}
