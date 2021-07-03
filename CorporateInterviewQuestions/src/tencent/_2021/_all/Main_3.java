package tencent._2021._all;

import java.util.Scanner;

/**
 * @author WuChao
 * @create 2021/7/2 14:27
 */
public class Main_3 {
    /**
     * 腾讯2021校园招聘技术类编程题汇总
     * 求抛物线 y^2 = 2Ax  与直线 y = Bx + C 所围成的封闭图形面积.若图形不存在,则输出 0
     * <p>
     * 输入描述:
     * 第一行输入一个正整数T.表示测试数据组数.
     * <p>
     * 接下来每行输入三个整数A , B和 C.
     * <p>
     * 1<=T<=1000
     * 1<=A,B<=100
     * -100<=C<=100
     * <p>
     * <p>
     * <p>
     * <p>
     * 输出描述:
     * 每组测试数据输出一个答案.在<1e-4范围内都视为正确输出.
     * <p>
     * 输入例子1:
     * 1
     * 1 1 -6
     * <p>
     * 输出例子1:
     * 31.2481110540
     */

    /*
    思路：数学题
    积分求面积

     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = Integer.parseInt(scanner.nextLine());
        while (t-- > 0) {
            String[] fields = scanner.nextLine().split(" ");
            int A = Integer.parseInt(fields[0]);
            int B = Integer.parseInt(fields[1]);
            int C = Integer.parseInt(fields[2]);
            double a, b, c;
            a = B * B;
            b = 2 * B * C - 2 * A;
            c = C * C;
            double dt = b * b - 4 * a * c;
            if (compare(dt) <= 0)
                System.out.println(0);
            else {
                double x1 = (-b + Math.sqrt(dt)) / (2 * a);
                double x2 = (-b - Math.sqrt(dt)) / (2 * a);
                double y1 = B * x1 + C;
                double y2 = B * x2 + C;
                if (y1 < y2){
                    double temp = y1;
                    y1 = y2;
                    y2 = temp;
                }
                double ans = ((y1 * y1) / (2 * B) - (y2 * y2) / (2 * B)) - (C * y1 / B - C * y2 / B) - ((y1 * y1 * y1) / (6 * A) - (y2 * y2 * y2) / (6 * A));
                System.out.println(ans);
            }
        }
    }

    public static int compare(double x)
    {
        if(Math.abs(x)<1e-8)
            return 0;
        if(x>0)
            return 1;
        return -1;
    }


}

