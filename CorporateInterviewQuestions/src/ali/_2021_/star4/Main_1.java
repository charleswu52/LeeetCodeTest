package ali._2021_.star4;

import java.util.*;

/**
 * @author WuChao
 * @create 2021/7/4 14:12
 */
public class Main_1 {
    /**
     * 【2021】阿里巴巴编程题（4星）
     * [编程题]子集
     * 小强现在有n个物品,每个物品有两种属性 x_i 和 y_i .他想要从中挑出尽可能多的物品满足以下条件:
     * 对于任意两个物品 i 和 j ,满足 x_i < x_j 且  y_i < y_j 或者 x_i > x_j 且 y_i > y_j.
     * 问最多能挑出多少物品.
     * <p>
     * 输入描述:
     * 第一行输入一个正整数 T .表示有 T 组数据.
     * 对于每组数据,第一行输入一个正整数 n .表示物品个数.
     * 接下来两行,每行有 n 个整数.
     * 第一行表示 n 个节点的x属性.
     * 第二行表示 n 个节点的y属性.
     * 其中:
     * 1<= T <= 10
     * 2 <= n <= 100000
     * 0 <= x,y <= 1000000000
     * <p>
     * 输出描述:
     * 输出 T 行,每一行对应每组数据的输出.
     * <p>
     * 输入例子1:
     * 2
     * 3
     * 1 3 2
     * 0 2 3
     * 4
     * 1 5 4 2
     * 10 32 19 21
     * <p>
     * 输出例子1:
     * 2
     * 3
     */

    static class Thing implements Comparable<Thing> {
        private int x;
        private int y;

        public Thing() {
        }

        public Thing(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Thing o) {
            if (this.x == o.x) {
                return this.y - o.y;
            }
            return this.x - o.x;
        }

        @Override
        public String toString() {
            return "Thing{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    /*
    思路1 ： 自定义类排序 + 最长递增子序列
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = Integer.parseInt(scanner.nextLine());
        while (T-- > 0) {
            int n = Integer.parseInt(scanner.nextLine());
            List<Thing> things = new ArrayList<Thing>() {{
                for (int i = 0; i < n; i++) {
                    add(new Thing());
                }
            }};

            String[] fieldsX = scanner.nextLine().split(" ");
            for (int i = 0; i < fieldsX.length; i++) {
                things.get(i).x = Integer.parseInt(fieldsX[i]);
            }
            String[] fieldsY = scanner.nextLine().split(" ");
            for (int i = 0; i < fieldsY.length; i++) {
                things.get(i).y = Integer.parseInt(fieldsY[i]);
            }
            Collections.sort(things);
            int[] dp = new int[n];
            int res = 0;
            Arrays.fill(dp, 1);
            for (int i = 1; i < n; i++) {
                for (int j = 0; j < i; j++) {
                    if (things.get(i).x > things.get(j).x && things.get(i).y > things.get(j).y && dp[i] < dp[j] + 1) {
                        dp[i] = dp[j] + 1;
                        res = Math.max(res, dp[i]);
                    }

                }
            }
            System.out.println(res);
        }

    }
}
