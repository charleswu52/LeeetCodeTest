package ali._2021_.star4;

import java.util.Scanner;

/**
 * @author WuChao
 * @create 2021/7/4 15:23
 */
public class Main_2 {
    /**
     * 【2021】阿里巴巴编程题（4星）
     * [编程题]小强爱数学
     *
     * 小强发现当已知 xy = B 以及  x+y= A时,能很轻易的算出 x^2+ y^2 的值.但小强想请你在已知 A 和 B 的情况下,计算出 x^n+ y^n 的值.
     * 因为这个结果可能很大,所以所有的运算都在模 1e9+7 下进行.
     *
     * 输入描述:
     * 第一行输入一个正整数 T .表示有 T 组数据
     * 接下来 T 行,每行输入三个整数A,B和n.
     * 1 <= T <=100
     * 0<=A,B<1e9+7
     * 1<=n<1e5
     *
     *
     *
     * 输出描述:
     * 输出行,每一行表示每组数据的结果.
     *
     * 输入例子1:
     * 3
     * 4 4 3
     * 2 3 4
     * 5 2 6
     *
     * 输出例子1:
     * 16
     * 999999993
     * 9009
     *
     */

    /*
    题目解析：
    数学题 --> 已知 xy 和 x+y 的值 求 x^n +y^n 的值; 数比较大，对结果取模 1000000007

     */
    static int mod = 1000000007;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = Integer.parseInt(scanner.nextLine());
        while (T-- > 0) {
            String[] fields = scanner.nextLine().split(" ");
            long A = Integer.parseInt(fields[0]);
            long B = Integer.parseInt(fields[1]);
            int n = Integer.parseInt(fields[2]);
            A %= mod;
            B %= mod;
            System.out.println(slove(A, B, n));
        }

    }

    public static long slove(long a, long b, int n) {
        long[] dp = new long[n + 1];
        dp[0] = 2;
        dp[1] = a;
        dp[2] = a * a % mod - 2 * b % mod;
        for (int i = 3; i <= n ; i++) {
            dp[i] = (a * (dp[i - 1]) % mod - b * (dp[i - 2]) % mod + mod) % mod;
        }
        return dp[n];

    }
}
