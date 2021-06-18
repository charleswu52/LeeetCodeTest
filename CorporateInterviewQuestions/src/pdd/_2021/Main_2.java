package pdd._2021;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author WuChao
 * @create 2021/6/18 11:14
 */
public class Main_2 {
    /**
     * 拼多多2021笔试真题集
     * [编程题]多多的字符变换
     * <p>
     * 多多君最近在研究字符串之间的变换，可以对字符串进行若干次变换操作:
     *
     * 交换任意两个相邻的字符，代价为0。
     * 将任意一个字符a修改成字符b，代价为 |a - b|（绝对值）。
     * 现在有两个长度相同的字符串X和Y，多多君想知道，如果要将X和Y变成两个一样的字符串，需要的最少的代价之和是多少。
     *
     *
     *
     * 输入描述:
     * 共三行，第一行，一个整数N，表示字符串的长度。
     * （1 <= N <= 2,000）
     * 接下来两行，每行分别是一个字符串，表示字符串X和Y。
     * （字符串中仅包含小写字母）
     *
     * 输出描述:
     * 共一行，一个整数，表示将X和Y变换成一样的字符串需要的最小的总代价。
     *
     * 输入例子1:
     * 4
     * abca
     * abcd
     *
     * 输出例子1:
     * 3
     *
     * 例子说明1:
     * 其中一种代价最小的变换方案：
     * 都修改为abcd，那么将第一个字符串X最后一个字符a修改为d，代价为|a - d| = 3。
     *
     * 输入例子2:
     * 4
     * baaa
     * aabb
     *
     * 输出例子2:
     * 1
     *
     * 例子说明2:
     * 其中一种代价最小的变换方案：
     * 首先将第一个字符串通过交换相邻的字符：baaa -> abaa -> aaba，代价为0。
     * 然后将第二个字符串修改最后一个字符b：|b - a| = 1。
     * 两个字符都修改为aaba，所以最小的总代价为1。
     */
    public static void main(String[] args) {
        int n;
        String a, b;
        int res = 0;
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        a = scanner.next();
        b = scanner.next();


        char[] arrayA = a.toCharArray();
        char[] arrayB = b.toCharArray();
        Arrays.sort(arrayA);
        Arrays.sort(arrayB);
        a = Arrays.toString(arrayA);
        b = Arrays.toString(arrayB);
        System.out.println(a);
        System.out.println(b);
        for (int i = 0; i < a.length(); i++) {
            res += Math.abs(a.charAt(i) - b.charAt(i));
        }
        System.out.println(res);
        /*
        10
fmjdnylvfj
yxbpiplbso
         */



    }
}
