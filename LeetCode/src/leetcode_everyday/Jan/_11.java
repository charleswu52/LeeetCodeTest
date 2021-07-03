package leetcode_everyday.Jan;

import java.util.*;

public class _11 {
    /**
     * 每日一题：2021/1/11
     * 给你一个字符串 s，以及该字符串中的一些「索引对」数组 pairs，其中 pairs[i] = [a, b] 表示字符串中的两个索引（编号从 0 开始）。
     * 你可以 任意多次交换 在 pairs 中任意一对索引处的字符。
     * 返回在经过若干次交换后，s 可以变成的按字典序最小的字符串。
     *
     * 输入：s = "dcab", pairs = [[0,3],[1,2]]
     * 输出："bacd"
     * 解释：
     * 交换 s[0] 和 s[3], s = "bcad"
     * 交换 s[1] 和 s[2], s = "bacd"
     */

    /*
    问题剖析：
    题目中指出可以任意多次交换在pairs中任意一对索引处的字符，因此对pairs中的多个索引对集合，
    具有交集的索引对在交换的时候存在传递性，因此需要找出同属于一个连通分量的所有字符，
    把【连在一起】的索引按照字符的ASCII值升序排序。就是这几个数字的最小字典序的字符串，采用基于比较的原地排序算法均可

    因此可以看出当前问题是一个图论问题，需要找出同属于一个连通分量的所有字符，把连在一起的索引按照ASCII值升序排序。
    交换关系具有传递性、找哪些索引连在一起、数组 pairs 给出的是数对的形式，这三点提示我们可以使用并查集。
     */

    /**
     * 思路：并查集
     * 1.先遍历 pairs 中的索引对，将索引对中成对的索引输入并查集，并查集会帮助我们实现同属于一个连通分量中的元素的合并工作。
     * 2.遍历输入字符串 s，对于每一个索引，找到这个索引在并查集中的代表元，把同属于一个代表元的字符放在一起。这一步需要建立一个映射关系。
     *   键：并查集中的代表元，值：同属于一个代表元的 s 中的字符。可以使用哈希表建立映射关系。
     * 3.分组排序。即对同属于一个连通分量中的字符进行排序.
     *   这一步实现可以这样做：重新生成一个长度和 s 相同的字符串，对于每一个索引，查询索引在并查集中的代表元，
     *   再从哈希表中获得这个代表元对应的字符集列表，从中移除 ASCII 值最小的字符依次拼接起来。
     *   这一步我们每一次需要从一个集合中选出 ASCII 值最小的字符，选出以后不再用它，
     *   带排序功能的集合有「平衡树（二叉搜索树）」和「优先队列（堆）」等，可以使用「优先队列」。
     *
     * @param s
     * @param pairs
     * @return
     */
    public static String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        if (pairs.size() == 0) {
            return s;
        }

        // 第 1 步：将任意交换的结点对输入并查集
        int len = s.length();
        UnionFind unionFind = new UnionFind(len);
        for (List<Integer> pair : pairs) {
            int index1 = pair.get(0);
            int index2 = pair.get(1);
            unionFind.union(index1, index2);
        }

        // 第 2 步：构建映射关系
        char[] charArray = s.toCharArray();
        // key：连通分量的代表元，value：同一个连通分量的字符集合（保存在一个优先队列中）
        Map<Integer, PriorityQueue<Character>> hashMap = new HashMap<>(len);
        for (int i = 0; i < len; i++) {
            int root = unionFind.find(i);
//            if (hashMap.containsKey(root)) {
//                hashMap.get(root).offer(charArray[i]);
//            } else {
//                PriorityQueue<Character> minHeap = new PriorityQueue<>();
//                minHeap.offer(charArray[i]);
//                hashMap.put(root, minHeap);
//            }
            // 上面六行代码等价于下面一行代码，JDK 1.8 以及以后支持下面的写法
            hashMap.computeIfAbsent(root, key -> new PriorityQueue<>()).offer(charArray[i]);
        }

        // 第 3 步：重组字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            int root = unionFind.find(i);
            stringBuilder.append(hashMap.get(root).poll());
        }
        return stringBuilder.toString();
    }

    private static class UnionFind {

        private int[] parent;
        /**
         * 以 i 为根结点的子树的高度（引入了路径压缩以后该定义并不准确）
         */
        private int[] rank;

        public UnionFind(int n) {
            this.parent = new int[n];
            this.rank = new int[n];
            for (int i = 0; i < n; i++) {
                this.parent[i] = i;
                this.rank[i] = 1;
            }
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return;
            }

            if (rank[rootX] == rank[rootY]) {
                parent[rootX] = rootY;
                // 此时以 rootY 为根结点的树的高度仅加了 1
                rank[rootY]++;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
                // 此时以 rootY 为根结点的树的高度不变
            } else {
                // 同理，此时以 rootX 为根结点的树的高度不变
                parent[rootY] = rootX;
            }
        }

        public int find(int x) {
            if (x != parent[x]) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }
    }



    public static void _21_1_11(){
        String s = "dcab";
        List<List<Integer>> pairs = new ArrayList<List<Integer>>();
        List<Integer> temp = new ArrayList<Integer>();
        temp.add(0);
        temp.add(3);
        pairs.add(temp);
        temp.clear();
        temp.add(1);
        temp.add(2);
        pairs.add(temp);
        System.out.println(smallestStringWithSwaps(s, pairs));
    }
}
