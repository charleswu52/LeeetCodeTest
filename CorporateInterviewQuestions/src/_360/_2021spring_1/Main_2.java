package _360._2021spring_1;

import java.util.Scanner;

/**
 * @author WuChao
 * @create 2021/6/25 12:43
 */
public class Main_2 {
    /**
     * 【2021】360春招编程题（第一批）
     * <p>
     * [编程题]【2021】360编程-火星人的宝藏
     * X星人发现了一个藏宝图，在藏宝图中标注了N个宝库的位置。这N个宝库连成了一条直线，每个宝库都有若干枚金币。
     * <p>
     * X星人决定乘坐热气球去收集金币，热气球每次最多只能飞行M千米（假设热气球在飞行过程中并不会发生故障）
     * 此外，由于设计上的缺陷，热气球最多只能启动K次。
     * <p>
     * X星人带着热气球来到了第1个宝库（达到第1个宝库时热气球尚未启动），收集完第1个宝库的金币后将启动热气球前往下一个宝库。
     * 如果他决定收集某一个宝库的金币，必须停下热气球，收集完之后再重新启动热气球。当然，X星人每到一个宝库是一定会拿走这个宝库所有金币的。
     * <p>
     * 已知每一个宝库距离第1个宝库的距离（单位：千米）和宝库的金币数量。
     * <p>
     * 请问X星人最多可以收集到多少枚金币？
     * <p>
     * 输入描述:
     * 单组输入。
     * <p>
     * 第1行输入三个正整数N、M和K，分别表示宝库的数量、热气球每次最多能够飞行的距离（千米）和热气球最多可以启动的次数，
     * 三个正整数均不超过100，相邻两个正整数之间用空格隔开。
     * <p>
     * 接下来N行每行包含两个整数，分别表示第1个宝库到某一个宝库的距离（千米）和这个宝库的金币枚数。（因为初始位置为第1个宝库，因此第1个宝库所对应行的第1个值为0。）
     * <p>
     * 输入保证所有的宝库按照到第1个宝库的距离从近到远排列，初始位置为第1个宝库。
     * <p>
     * <p>
     * 输出描述:
     * 输出一个正整数，表示最多可以收集的金币数。
     * <p>
     * <p>
     * 输入例子1:
     * 5 10 2
     * 0 5
     * 8 6
     * 10 8
     * 18 12
     * 22 15
     * <p>
     * 输出例子1:
     * 25
     * <p>
     * 例子说明1:
     * X星人启动热气球两次，分别收集第1个、第3个和第4个宝库的金币，一共可以得到的金币总数为5+8+12=25枚。
     */

    /*
    思路： 参考题解
    使用动态规划
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int k = scanner.nextInt();
        int[][] store = new int[n][2];

        int sum = 0;

        for (int i = 0; i < n; i++) {
            store[i][0] = scanner.nextInt();
            store[i][1] = scanner.nextInt();
            sum += store[i][1];
        }

        if (k >= n) {
            System.out.println(sum);
            System.exit(0);
        }
        int[][] dp = new int[n][k + 1];
        dp[0][0] = store[0][1];
        int max = 0;
        for (int i = 1; i <= k; i++) {
            for (int j = 0; j < n; j++) {
                if (dp[j][i - 1] > 0) {
                    for (int l = j + 1; l < n; l++) {
                        if (store[l][0] - store[j][0] <= m) {
                            dp[l][i] = Math.max(dp[l][i], dp[j][i - 1] + store[l][1]);
                            max = Math.max(dp[l][i], max);
                        }
                    }
                }
            }
        }
        System.out.println(max);
    }
}
