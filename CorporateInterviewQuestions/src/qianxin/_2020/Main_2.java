package qianxin._2020;

import java.util.Scanner;

/**
 * @author WuChao
 * @create 2021/6/30 16:42
 */
public class Main_2 {
    /**
     * [编程题]三角形三边数字求和
     * 时间限制：C/C++ 5秒，其他语言10秒
     *
     * 空间限制：C/C++ 256M，其他语言512M
     *
     * 1
     * 2
     * 3
     * 如下图所示的三角形，有三个边s1,s2,s3,边s1有四个圆圈⭕️1，2，3，4，边s2有四个圆圈⭕️4，5，6，7，边s3有四个圆圈⭕️7，8，9，1
     * 把1-9这9个数字，分别填写到下图所示的9个圆圈圆圈里， 使每条边上的4个圆圈⭕️的和相等（9个圆圈里的数字不能重复)
     * 例如：s1=[1=>9,2=>8,3=>1,4=>3]=9+8+1+3=21,s2=[4=>3,5=>5,6=>7,7=>6]=3+5+7+6=21,s3=[7=>6,8=>2,9=>4, 1=>9]=6+2+4+9=21
     *
     *
     * 输入描述:
     * 输入内容格式:
     * 3,4,8,5,2,6,7,1,9
     * 其中
     * 3，4， 8，5 是s1 1-4填写的数字，数字之和20
     * 5，2，6，7 是s2 4-7填写的数字，数字之和20
     * 7，1，9，3 是s4 7-1填写的数字，数字之和20
     *
     *
     * 输出描述:
     * 当输入的数字满足三边之和相等的时候，输出yes
     * 当输入的数字不满足三边之和相等的时候，输出no
     *
     * 输入例子1:
     * 3,4,8,5,2,6,7,1,9
     *
     * 输出例子1:
     * yes
     *
     * 输入例子2:
     * 3,4,8,5,2,6,9,7,1
     *
     * 输出例子2:
     * no
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] s = scanner.nextLine().split(" ");
        int size = s.length;
        int[] ints = new int[size];
        for (int i = 0; i < size; i++) {
            ints[i] = Integer.parseInt(s[i]);
        }
        int a = ints[0] + ints[1] + ints[2] + ints[3];
        int b = ints[3] + ints[4] + ints[5] + ints[6];
        int c = ints[6] + ints[7] + ints[8] + ints[0];
        if (a != b || b != c || a != c) {
            System.out.println("no");
        } else {
            System.out.println("yes");
        }
    }
}
