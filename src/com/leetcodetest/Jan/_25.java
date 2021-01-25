package com.leetcodetest.Jan;

public class _25 {
    /**
     * 每日一题：2021/1/25
     * 959. 由斜杠划分区域
     * 在由 1 x 1 方格组成的 N x N 网格 grid 中，每个 1 x 1 方块由 /、\ 或空格构成。这些字符会将方块划分为一些共边的区域。
     * （请注意，反斜杠字符是转义的，因此 \ 用 "\\" 表示。）。
     * 返回区域的数目。
     *
     * 示例：
     * 输入：
     * [
     *   " /",
     *   "/ "
     * ]
     * 输出：2
     *
     * 数据范围：
     * 1 <= grid.length == grid[0].length <= 30
     * grid[i][j] 是 '/'、'\'、或 ' '。
     */

    /**
     * 题目解析：
     * 「斜杠」、「反斜杠」把单元格拆分成的 2 个三角形的形态，在做合并的时候需要分类讨论。
     * 根据「斜杠」、「反斜杠」分割的特点，我们把一个单元格分割成逻辑上的 4 个部分。
     * 将单元格按对角线分开，顺时针标记为: 0,1,2,3 四个区间；
     *  \ 0 /
     * 3 \ /1
     *   \/
     *  / 2\
     * 我们须要遍历一次输入的二维网格 grid，在 单元格内 和 单元格间 进行合并。
     * 单元格内：
     *  如果是空格：合并 0、1、2、3；
     *  如果是斜杠：合并 0、3，合并 1、2；
     *  如果是反斜杠：合并 0、1，合并 2、3。
     * 单元格间：
     *  我们选择在遍历 grid 的每一个单元格的时候，分别向右、向下尝试合并。
     *  向右：合并 1 （当前单元格）和 3（当前单元格右边 1 列的单元格）
     *  向下：合并 2 （当前单元格）和 0（当前单元格下边 1 列的单元格)
     *
     * 解题思路：
     *  并查集里连通分量的个数就是题目要求的区域的个数。
     */

    // 21_Jan 这个月份是并查集的月份，大部分题目都是并查集

    // 并查集模板
    class UnionFind{
        int[] parent;
        int count; // 连通分量数

        public UnionFind(int count) {
            this.count = count;
            this.parent = new int[count];
            for (int i = 0; i < count; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            while (x != parent[x]) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return;
            }
            parent[rootX] = rootY;
            count--;
        }

        public int getCount() {
            return count;
        }
    }


    /**
     * 使用并查集的思路求解
     * @param grid
     * @return
     */
    public int regionsBySlashes(String[] grid) {
        int N = grid.length;
        int size = 4 * N * N;
        UnionFind unionFind = new UnionFind(size);
        for (int i = 0; i < N; i++) {
            char[] row = grid[i].toCharArray();
            for (int j = 0; j < N;j++) {
                // 将二维网格转换为一维表格
                int index = 4 * (i * N + j);
                char c = row[j];
                // 单元格内合并
                if (c == '/') {
                    // 合并0、3，合并1、2
                    unionFind.union(index, index + 3);
                    unionFind.union(index + 1, index + 2);
                } else if (c == '\\') {
                    // 合并0、1,合并2、3
                    unionFind.union(index, index + 1);
                    unionFind.union(index + 2, index + 3);
                }else{
                    // 空格，全合并
                    unionFind.union(index, index + 1);
                    unionFind.union(index + 1, index + 2);
                    unionFind.union(index + 2, index + 3);
                }

                // 单元格间合并
                // 向右合并：1（当前）、3（右边相邻单元格）
                if (j + 1 < N) {
                    unionFind.union(index + 1, 4 * (i * N + j + 1) + 3);
                }
                // 向下合并:2(当前)、0（下边相邻单元格）
                if (i + 1 < N) {
                    unionFind.union(index + 2, 4 * ((i + 1) * N + j));
                }
            }
        }
        return unionFind.getCount();
    }

    public void _21_1_25(){
        /*
        输入： _/
              /_
         */
        String[] grid = {" /", "/ "};
        System.out.println(regionsBySlashes(grid));
    }
}
