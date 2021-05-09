package chp5;

/**
 * @author WuChao
 * @since 2021/5/9 上午10:58
 */
public class _6 {
    /**
     * 程序员面试金典(version 6) - 面试题 05.06. 整数转换
     * 难度: easy
     * <p>
     * 整数转换。编写一个函数，确定需要改变几个位才能将整数A转成整数B。
     *
     * <p>
     * 示例:
     * 输入：A = 29 （或者0b11101）, B = 15（或者0b01111）
     * 输出：2
     * <p>
     * 输入：A = 1，B = 2
     * 输出：2
     *
     * <p>
     * 数据范围：
     * A，B范围在[-2147483648, 2147483647]之间
     */

    /*
    传统做法：将整型数字转换为字符串求解
     */
    public int convertInteger(int A, int B) {
        String a = Integer.toBinaryString(A);
        String b = Integer.toBinaryString(B);
        int minus = Math.abs(a.length() - b.length());
        String temp = "";
        for (int i = 0; i < minus; i++) {
            temp += "0";
        }
        if (a.length() < b.length()) {
            a = temp + a;
        } else if (a.length() > b.length()) {
            b = temp + b;
        }
        int res = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                res++;
            }
        }
        return res;
    }

    /*
    思路2：使用位运算  --异或与运算
     */
    public int convertInteger2(int A, int B) {
        int C = A ^ B;
        int res = 0;
        while (C != 0) { // 统计C 中1的个数
            C = C & (C - 1);
            res ++;
        }
        return res;
    }


    }
