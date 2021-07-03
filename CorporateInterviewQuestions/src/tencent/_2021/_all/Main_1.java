package tencent._2021._all;

import java.util.*;

/**
 * @author WuChao
 * @create 2021/6/26 10:53
 */
public class Main_1 {
    /**
     * 腾讯2021校园招聘技术类编程题汇总
     *[编程题]朋友圈
     *
     * 现在有10^7个用户，编号为1- 10^7，现在已知有m对关系，每一对关系给你两个数x和y，代表编号为x的用户和编号为y的用户是在一个圈子中，
     * 例如：A和B在一个圈子中，B和C在一个圈子中，那么A,B,C就在一个圈子中。现在想知道最多的一个圈子内有多少个用户。
     * 输入描述:
     * 第一行输入一个整数T，接下来有T组测试数据。
     * 对于每一组测试数据：第一行输入1个整数n，代表有n对关系。
     * 接下来n行，每一行输入两个数x和y，代表编号为x和编号为y的用户在同一个圈子里。
     * 1 ≤ T ≤ 10
     * 1 ≤ n ≤ 105
     * 1 ≤ x, y ≤ 107
     * 输出描述:
     * 对于每组数据，输出一个答案代表一个圈子内的最多人数
     *
     * 输入例子1:
     * 2
     * 4
     * 1 2
     * 3 4
     * 5 6
     * 1 6
     * 4
     * 1 2
     * 3 4
     * 5 6
     * 7 8
     *
     * 输出例子1:
     * 4
     * 2
     */

    /*
    思路：使用并查集
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        while (t-- > 0) {
            int n = scanner.nextInt();
            int size = Integer.MIN_VALUE;
            int[][] store = new int[n][2];
            for (int i = 0; i < n; i++) {
                store[i][0] = scanner.nextInt();
                if (store[i][0] > size) {
                    size = store[i][0];
                }
                store[i][1] = scanner.nextInt();
                if (store[i][1] > size) {
                    size = store[i][1];
                }
                if (store[i][0] > store[i][1]) {
                    int tmp = store[i][0];
                    store[i][0] = store[i][1];
                    store[i][1] = tmp;
                }
            }
            UnionFind unionFind = new UnionFind(size+1);
            for (int[] row : store) {
                unionFind.unionElements(row[0], row[1]);
            }
            System.out.println(unionFind.getSetSize());
        }
    }

    /*
    并查集实现
     */
    static class UnionFind {
        private int[] parent;
        private int[] res;
        private int size;

        public UnionFind(int size) {
            this.size = size;
            parent = new int[size];
            res = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
                res[i] = 1;
            }
        }

        /**
         * 查看元素所属集合
         * @param element
         * @return
         */
        private int find(int element) {
            while (element != parent[element]) {
                parent[element] = parent[parent[element]];
                element = parent[element];
            }
            return element;
        }


        /**
         * 合并两个元素所在的集合，也就是连接两个元素
         *
         * @param firstElement  第一个元素
         * @param secondElement 第二个元素
         */
        public void unionElements(int firstElement, int secondElement) {
            int firstRoot = find(firstElement);
            int secondRoot = find(secondElement);
            if (firstRoot != secondRoot) {
                parent[firstRoot] = secondRoot;
                res[secondRoot] += res[firstRoot];
            }
        }

        /**
         * 返回集合中元素最多的数量
         * @return
         */
        public int getSetSize() {
            int ans = 0;
            for (int i = 0; i < size; i++) {
                if (res[i] > ans) {
                    ans = res[i];
                }
            }
            return ans;
        }





    }
}
