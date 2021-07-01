package qianxin._2020;

import java.util.Scanner;

/**
 * @author WuChao
 * @create 2021/6/30 16:40
 */
public class Main_1 {
    /**
     * 时间限制：C/C++ 1秒，其他语言2秒
     * <p>
     * 空间限制：C/C++ 256M，其他语言512M
     * <p>
     * 小明有N元钱去药店买口罩，药店里有6个品牌的口罩，
     * A品牌2个装（2元），
     * B品牌3个装（2元）、
     * C品牌1个装（3元）、
     * D品牌5个装（1元），
     * E品牌4个装（5元），
     * F品牌3个装（2元），
     * <p>
     * 由于限购每个品牌最多只能买一个，小明最多能买多少口罩？
     * <p>
     * 输入描述:
     * N元钱（1<N<15）
     * <p>
     * <p>
     * 输出描述:
     * N元钱能买到的口罩数目
     * <p>
     * 输入例子1:
     * 9
     * <p>
     * 输出例子1:
     * 13
     */

    /*
    经典0-1 背包问题

     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] count = {0,2,3,1,5,4,3};
        int[] money = {0,2,2,3,1,5,2};

        int[][] dp = new int[7][n + 1];

        dp[0][0] = 0;
        for (int i = 1; i <=6 ; i++) {
            for (int j = 1; j <= n; j++) {
                if (j < money[i]) {
                    dp[i][j] = dp[i - 1][j]; // 钱不够用
                } else {
                    // 不装第i个，和装了第i个比较
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - money[i]] + count[i]);
                }
            }
        }
        System.out.println(dp[6][n]);



    }
}
