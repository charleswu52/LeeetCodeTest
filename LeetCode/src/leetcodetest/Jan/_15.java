package leetcodetest.Jan;

import java.util.HashMap;
import java.util.Map;

public class _15 {
    /**
     * 每日一题：2021/1/15
     * 947. 移除最多的同行或同列石头
     * 难度： medium
     * n 块石头放置在二维平面中的一些整数坐标点上。每个坐标点上最多只能有一块石头。
     * 如果一块石头的 同行或者同列 上有其他石头存在，那么就可以移除这块石头。
     * 给你一个长度为 n 的数组 stones ，其中 stones[i] = [xi, yi] 表示第 i 块石头的位置，返回 可以移除的石子 的最大数量。
     */

    /*
    题目解析：
    把二维坐标平面上的石头想象成图的顶点，如果两个石头横坐标相同、或者纵坐标相同，在它们之间形成一条边
    将二维平面上的石头点连接起来，建成图的形式。

    根据可以移除石头的规则：如果一块石头的 同行或者同列 上有其他石头存在，那么就可以移除这块石头。
    可以发现转化为图上的规则就是：一定可以把一个连通图里的所有顶点根据这个规则删到只剩下一个顶点。
    所以原问题就转换成了：求图的连通分量的个数的问题

    既然这些顶点在一个连通图里，可以通过遍历的方式（深度优先遍历或者广度优先遍历）遍历到这个连通图的所有顶点。
    那么就可以按照遍历的方式 逆向 移除石头，最后只剩下一块石头。
    所以：最多可以移除的石头的个数 = 所有石头的个数 - 连通分量的个数。（每个连通分量最后只剩下一块石头）

    思路：由于题目只关心结果，不关系过程；因此可以使用并查集的方式来做，求出连通分量的个数然后用石头数减去连通分量的个数即可
     */

    /*
    code细节说明：
    1. 两个石头的横纵坐标有一个是相同的时候，就认为它们是连在一起的
    2. 最终这些石头一定会位于不同行和不同的列；（由于横纵坐标的范围是[0,10000]，因此将任意坐标的值加10000可以避免横纵坐标相同的情况）
    3. 并查集的底层数组表示：不同的[行]坐标和不同的[列]坐标
    4. 对于每一块石头，将横坐标数值和纵坐标数值进行合并
    */
    public int removeStones(int[][] stones) {
        UnionFind unionFind = new UnionFind();

        for (int[] stone : stones) {    // 将石头的坐标依次输入并查集
            unionFind.union(stone[0] + 10000, stone[1]);    // 为了避免横纵坐标相同，将横坐标加10000
        }
        return stones.length - unionFind.getCount();    //返回结果：所有石头的个数 - 连通分量的个数
    }

    public class UnionFind{
        private Map<Integer, Integer> parent;// 对应横纵坐标顶点的父节点
        private int count;  // 连通分量的个数

        public UnionFind() {
            this.parent = new HashMap<>();
            this.count = 0;
        }

        public int find(int x) {
            if (!parent.containsKey(x)) {    //如果x并不存在于哈希表中，将它添加进去并增加连通分量的个数
                parent.put(x, x);
                count++;
            }

            // 路径压缩 （固定写法）
            if (x != parent.get(x)) {
                parent.put(x, find(parent.get(x)));
            }
            return parent.get(x);
        }

        public void union(int x, int y) {
            //先查询x和y对应的根节点
            int rootX = find(x);
            int rootY = find(y);

            if (rootX == rootY) {   // x和y属于同一个连通分量
                return;
            }
            parent.put(rootX, rootY);
            count--;    // 每进行一次合并，那么连通分量的个数-1
        }

        public int getCount() {
            return count;
        }
    }


    public void _21_1_15() {
        int[][] stones = {{0, 0}, {0, 1}, {1, 0}, {1, 2}, {2, 1}, {2, 2}};
        System.out.println(removeStones(stones));
    }


}
