package pdd._2021;

import java.util.Scanner;

/**
 * @author WuChao
 * @create 2021/6/18 10:38
 */
public class Main_1 {
    /**
     * 拼多多2021笔试真题集
     * [编程题]多多的数字组合
     * <p>
     * 多多君最近在研究某种数字组合：
     * 定义为：每个数字的十进制表示中(0~9)，每个数位各不相同且各个数位之和等于N。
     * 满足条件的数字可能很多，找到其中的最小值即可。
     * 多多君还有很多研究课题，于是多多君找到了你--未来的计算机科学家寻求帮助。
     *
     *
     * 输入描述:
     * 共一行，一个正整数N，如题意所示，表示组合中数字不同数位之和。
     * （1 <= N <= 1,000）
     *
     * 输出描述:
     * 共一行，一个整数，表示该组合中的最小值。
     * 如果组合中没有任何符合条件的数字，那么输出-1即可。
     *
     * 输入例子1:
     * 5
     *
     * 输出例子1:
     * 5
     *
     * 例子说明1:
     * 符合条件的数字有：5，14，23，32，41
     * 其中最小值为5
     */


    /*
    题目解析： 数位之和 为 N
    思路：从最低位开始累加，且取值从大到小 9-1
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        int ans = 0, max = 9, level = 1;
        if (num > 45) {
            System.out.println(-1);
        } else {
            while (num > 0) {
                if (num > max) {
                    ans += max * level;
                    level *= 10;
                } else {
                    ans += num * level;
                }
                num -= max;
                max--;
            }
            System.out.println(ans);
        }
    }
}
