package _360._2021spring_2;

import java.util.Scanner;

/**
 * @author WuChao
 * @create 2021/6/24 11:15
 */
public class Main_2 {
    /**
     * 【2021】360春招编程题（第二批）
     * <p>
     * [编程题]【2021】360编程题回文数变换
     * 所谓回文数就是一个数字，从左边读和从右边读的结果都是一样的，例如12321。
     * <p>
     * 现在有一个只包含1、2、3、4的数字，你可以通过在任意位置增加一位数字或者删除一位数字来将其变换成一个回文数。但是增加或删除不同数字所需要的代价是不一样的。
     * <p>
     * 已知增加和删除每个数字的代价如下：
     * <p>
     * 增加一个1，代价：100；删除一个1，代价：120。
     * <p>
     * 增加一个2，代价：200；删除一个2，代价：350。
     * <p>
     * 增加一个3，代价：360；删除一个3，代价：200。
     * <p>
     * 增加一个4，代价：220；删除一个4，代价：320。
     * <p>
     * 请问如何通过最少的代价将一个数字变换为一个回文数。当然，如果一个数字本身已经是一个回文数（包括一位数，例如：2），那么变换的代价为0。
     * <p>
     * 输入描述:
     * 单组输入。
     * <p>
     * 输入一个由1、2、3、4组成的正整数，正整数位数<=100位。【提示：采用字符串输入】
     * <p>
     * <p>
     * 输出描述:
     * 输出一个整数，表示将输入数字变换为一个回文数所需的最少代价。
     * <p>
     * <p>
     * 输入例子1:
     * 12322
     * <p>
     * 输出例子1:
     * 300
     * <p>
     * 例子说明1:
     * 增加一个1并增加一个2，将输入正整数变为1223221或者2123212，所需代价最小，为：100+200=300。
     */

    /*
    思路：参考 动态规划
    定义dp[i][j] 表示从序号i到j变成回文串的最小代价，而dp[i][j]可以由dp[i+1][j]和dp[i][j-1]得到
    如果第i个字符和第j个字符不同，那么dp[i][j]可由dp[i+1][j]删除第i个字符，或者在字符串最末尾添加第i个字符得到，
    也可以由dp[i][j-1]删除第j个字符，或者在最左侧添加第j个字符得到。

     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        int[] delCost = {0, 120, 350, 200, 320};
        int[] addCost = {0, 100, 200, 360, 220};
        if (input.length() < 2) {
            System.out.println(0);
        }
        int res = 0;
        int len = input.length();
        int[][] dp = new int[len][len];
        char[] chars = input.toCharArray();
        for (int j = 1; j < len; j++) {
            for (int i = j - 1; i >= 0; i--) {
                if (chars[i] == chars[j]) {
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    int temp1 = Math.min(dp[i + 1][j] + delCost[chars[i] - '0'],
                            dp[i + 1][j] + addCost[chars[i] - '0']);
                    int temp2 = Math.min(dp[i][j - 1] + delCost[chars[j] - '0'],
                            dp[i][j - 1] + addCost[chars[j] - '0']);
                    dp[i][j] = Math.min(temp1, temp2);
                }

            }

        }
        System.out.println(dp[0][len - 1]);

    }

}
