package pdd._2021;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author WuChao
 * @create 2021/6/18 13:10
 */
public class Main_4 {
    /**
     * 拼多多2021笔试真题集
     * [编程题]多多的骰子组合
     * <p>
     * 多多君拼团购买了N个骰子，为了方便后面进行活动，多多君需要将这些骰子进行分类。
     * <p>
     * 两个骰子为同类的定义是：
     * <p>
     * 将其中一个骰子通过若干次上下、左右或前后翻转后，其与另一个骰子对应的6面数字均相等。
     * <p>
     * 现在多多君想知道不同种类的骰子的数量分别有多少。
     * <p>
     * 输入描述:
     * 第一行1个整数N，表示骰子的数量。
     * （1 <= N <= 1,000）
     * 接下来N行，每行6个数字（1～6，且各不相同）
     * 其中第i行表示第i个骰子当前上、下、左、右、前、后这6面的数字。
     * <p>
     * 输出描述:
     * 共2行:
     * 第一行1个整数M，表示不同种类的骰子的个数
     * 第二行M个整数，由大到小排序，表示每个种类的骰子的数量
     * <p>
     * 输入例子1:
     * 2
     * 1 2 3 4 5 6
     * 1 2 6 5 3 4
     * <p>
     * 输出例子1:
     * 1
     * 2
     * <p>
     * 例子说明1:
     * 第二个骰子相当于是第一个骰子从左向右旋转了一面得到，属于同类。
     * <p>
     * 输入例子2:
     * 3
     * 1 2 3 4 5 6
     * 1 2 6 5 3 4
     * 1 2 3 4 6 5
     * <p>
     * 输出例子2:
     * 2
     * 2 1
     * <p>
     * 例子说明2:
     * 第三个骰子无法通过任何旋转变换成第一个或第二个骰子。
     * <p>
     * 输入例子3:
     * 10
     * 2 5 1 3 4 6
     * 5 4 3 2 1 6
     * 1 4 6 2 3 5
     * 1 5 6 3 4 2
     * 6 4 2 1 5 3
     * 3 6 4 5 2 1
     * 1 6 3 4 2 5
     * 5 1 4 2 6 3
     * 6 2 3 1 5 4
     * 5 3 6 1 4 2
     * <p>
     * 输出例子3:
     * 9
     * 2 1 1 1 1 1 1 1 1
     * <p>
     * 例子说明3:
     * 只有第4个骰子(1 5 6 3 4 2)与第8个骰子(5 1 4 2 6 3)属于同一类。
     * <p>
     * 一种可能的变换方式:
     * 1) 首先从右向左翻转1次
     * (1 5 6 3 4 2) -> (1 5 4 2 3 6)
     * 2) 然后从上向下翻转2次
     * (1 5 4 2 3 6) -> (6 3 4 2 1 5) -> (5 1 4 2 6 3)
     */

    static int[][] type = new int[30][5];
    static int[] typenum = new int[30];
    static int typenumsum = 0;


    /*
    题目难度较大

     */
    public static void main(String[] args) {
        int n;
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        int[] temp = new int[6];
        while (n-- > 0) {
            for (int i = 0; i < 6; i++) {
                temp[i] = scanner.nextInt();
            }
            slove(temp);
        }
        Arrays.sort(typenum);
        System.out.println(typenumsum);
        for (int i = 29; i > -1 && typenum[i] > 0; i--) {
            System.out.print(typenum[i] + " ");
        }
    }

    public static void slove(int[] temp) {
        int i, j, b1 = 0, b2 = 0, rn = 0;
        int[] ring = new int[5];
        for (i = 0; i < 6; i += 2) {
            j = i + 1;
            if (temp[i] == 1) {
                b1 = i;
                b2 = j;
            } else if (temp[j] == 1) {
                b1 = j;
                b2 = i;
            } else {
                ring[rn++] = temp[i];
                ring[rn++] = temp[j];
            }
        }
        int t = ring[1];
        ring[1] = ring[2];
        ring[2] = t;

        if ((b1 < b2) ^ (b1 / 2 == 1)) {
            int t2 = ring[1];
            ring[1] = ring[3];
            ring[3] = t2;
        }

        for (i = 1; i < 4; i++) {
            if (ring[i] < ring[ring[4]]) {
                ring[4] = i;
            }
        }
        i = (temp[b2]-2) * 6;
        for (j = 0; j < 6; j++) {
            if (typenum[i + j] == 0) {
                for (int k = 0; k < 5; k++) {
                    type[i + j][k] = ring[k];
                }
                typenum[i + j]++;
                typenumsum++;
                break;
            } else {
                int flag = 1;
                for (int k = 0; k < 4; k++)
                {
                    int k1 = (type[i+j][4] + k) % 4;
                    int k2 = (ring[4] + k) % 4;
                    if (type[i+j][k1] != ring[k2])
                    {
                        flag = 0;
                        break;
                    }
                }
                if (flag==1)
                {
                    typenum[i+j]++;
                    break;
                }
            }
        }

    }

}
