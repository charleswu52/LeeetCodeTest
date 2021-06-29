package yuanfudao._2021_3;

import java.util.Scanner;

/**
 * @author WuChao
 * @create 2021/6/29 12:53
 */
public class Main_2 {
    /**
     * [编程题]小猿的纸条
     * 小猿有两张分别写着字符串s1、s2的纸条，字符串由大小写字母组成。小猿会进行n次操作，每次操作时小猿会选择其中一张纸条，
     * 把它从左侧撕下一段或把它全部交给你。你按收到纸条的顺序，从左到右将收到的n张纸条拼接成一张新的纸条。
     * 已知字符串s1、s2，求是否存在一种方案使新纸条上的字符串与s3相同、且满足n<=K。
     *
     * 输入描述:
     * 第一行输入T（T ≤ 20），表示输入T组数据。
     * 接下来T行，每行按顺序输入字符串s1、s2、s3和正整数K（K ≤ 50），用空格分开。
     * 字符串s1、s2长度不超过200，s3长度不超过400。
     *
     * 输出描述:
     * 输出T行，每行输出对应组数据方案是否存在。存在输出1，不存在输出0。
     *
     * 输入例子1:
     * 1
     * ac bb abbc 3
     *
     * 输出例子1:
     * 1
     *
     * 例子说明1:
     * 方案为：1.小猿从第一张纸条撕下a给你。2.小猿将第二张纸条bb给你。3.小猿将第一张纸条剩下的c给你。你收到3张纸条，按顺序拼成abbc，符合条件。
     */

    /*
    题目解析:
    给定两个字符串和一个目标串，问是否存在方案将两个串拼接成目标串，且分成k个子串

     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String firstLine = scanner.nextLine();
        int t = Integer.parseInt(firstLine);
        while (t-- > 0) {
            String next = scanner.nextLine();
            String[] fields = next.split(" ");
            System.out.println(dfs(fields[0], fields[1], fields[2], Integer.parseInt(fields[3]), 0, 0, 0, 0));
        }
    }

    public static int dfs(String a, String b, String target, int K, int i, int j, int k, int flag) {
        if (K >= 0 && k >= target.length()) {
            return 1;
        } else if (K < 0) {
            return 0;
        }
        if (i < a.length() && a.charAt(i) == target.charAt(k)) {
            int res = dfs(a, b, target, flag == 0 ? K : K - 1, i + 1, j, k + 1, 0);
            if (res == 1) {
                return res;
            }
        }
        if (j < b.length() && b.charAt(j) == target.charAt(k)) {
            int res = dfs(a, b, target, flag == 1 ? K : K - 1, i, j + 1, k + 1, 1);
            if (res == 1) {
                return res;
            }
        }
        return 0;

    }

}
