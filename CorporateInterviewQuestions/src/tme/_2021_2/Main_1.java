package tme._2021_2;

import java.util.Scanner;
import java.util.Vector;

/**
 * @author WuChao
 * @create 2021/6/20 11:20
 */
public class Main_1 {
    /**
     * 腾讯音乐娱乐（TME）2021暑期实习生招聘技术类笔试题
     * [编程题]军营选择
     * 牛牛是一名新晋营长，需要选择军营建造地点，已知一共有n个候选地，编号为 1,2, ... ,n ，有n-1条道路，使得这 n 个候选地之间两两可以到达。
     * <p>
     * 军营选择规则如下：
     * <p>
     * 对于其中一个候选地 i 而言，如果将该地以及其直接相连的道路全都删除，就可以得到若干个连通块，用 w_i 记录下其中的最大连通块中的候选地数量。
     * <p>
     * 对于所有的满足 w_k = min{w_1,w_2,...,w_n}的候选地 k 而言，都是最佳军营建造地。
     * <p>
     * 由于最佳地点可能不止一个，所以牛牛想要通过一些操作将该地点唯一化：
     * <p>
     * 首先，牛牛会封闭一条已经存在的道路，接着，构建一条新道路，在这两个操作之后，这 n -1 条道路依然可以使 n 个地点两两相通，
     * 同时，最佳地点只有一个。
     * <p>
     * 但是，牛牛只擅长指挥军队，并不精通此法，所以，请你给出任意一种可以达成要求的合法方案。
     * <p>
     * 输入描述:
     * 本题为多组测试数据，第一行输入一个正整数 T(1<=T<=10^5) ，代表测试数据组数。
     * <p>
     * 对于每组测试数据，第一行输入一个正整数 n(3<=n<=10^5) ，代表军营候选地的数量。
     * 接下去 n-1 行，每行两个正整数 u,v(1<=u,v<=n;u!=v) ，代表候选地 u,v 之间存在一条道路 (无向边)。
     * <p>
     * 数据保证，每组测试数据给出的道路一定可以使 n 个候选地两两相通，同时，所有测试数据的 n 之和不会超过 10^6 .
     * <p>
     * 输出描述:
     * 对于每组测试数据，输出两行，第一行输出两个正整数 u1,v1，代表封闭原道路 u1,v1，这条道路必须存在。
     * 第二行输出两个正整数 u2,v2，代表增加一条道路 ，这条道路必须在原道路中不存在或者已经被封闭。
     * 由于是无向边，所以输出 u,v 等价于 v,u.
     * <p>
     * 如果存在多种解，任意输出一种即可，只需要保证满足题意。
     * <p>
     * 输入例子1:
     * 2
     * 3
     * 1 2
     * 1 3
     * 4
     * 1 2
     * 1 3
     * 4 3
     * <p>
     * 输出例子1:
     * 1 2
     * 2 1
     * 3 4
     * 4 1
     * <p>
     * 例子说明1:
     * 第一组测试数据中，最佳选择本就只有一个，所以任选一条道路，封闭后再建造即可。
     * 第二组测试数据中，最佳选择为候选地 \text 1,\ \text 3，而在封闭 \text {3 4} 之间的道路，再添加 \text {4 1} 之间的道路之后，最佳选择就只有候选地 \text 1.
     */


    /*
     题目解析 ：连通树
     看了半天没看懂题意，找题解

     */

    public static int[] w = new int[100005];
    public static int minnode = (int) 1e9;
    public static Vector<Integer>[] e = new Vector[100005];

    public static int n;

    // 类加载的时候初始化 e
    static {
        for (int i = 0; i < e.length; i++) {
            e[i] = new Vector<>();
        }
    }


    public static void main(String[] args) {
        int t;
        Scanner scanner = new Scanner(System.in);
        t = scanner.nextInt();
        while (t-- > 0) {
            n = scanner.nextInt();
            for (int i = 1; i <= n; i++) {
                e[i].clear();
                w[i] = 0;
            }
            for (int i = 1; i < n; i++) {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                e[x].add(y);
                e[y].add(x);
            }

            // dfs
            dfs(1, 0);

            int s1 = 0, s2 = 0;
            for (int i = 1; i <= n; i++) {
                if (w[i] == minnode) {
                    if (s1 == 0) { // 找到两个最小值的点
                        s1 = i;
                    } else {
                        s2 = i;
                    }
                }
            }
            if (s2 == 0) {
                System.out.println(1 + " " + e[1].get(0));
                System.out.println(1 + " " + e[1].get(0));
            } else {
                int i;
                for (i = 0; i <e[s2].size() ; i++) {
                    if (e[s2].get(i) != s1) {
                        break;
                    }
                }
                System.out.println(s2+" "+e[s2].get(i));
                System.out.println(s1+" "+e[s2].get(i));
            }
        }
    }

    // dfs 返回值 是以root为根的子树的结点数量
    public static int dfs(int root,int f) {
        int sum = 1, maxv = 0;
        for (int i = 0; i < e[root].size(); i++) {
            int y = e[root].get(i);
            if (y == f) {
                continue;
            }
            int temp = dfs(y, root);
            sum += temp;
            maxv = Math.max(maxv, temp);
        }
        w[root] = Math.max(n - sum, maxv);
        minnode = Math.min(minnode, w[root]);
        return sum;

    }


}
