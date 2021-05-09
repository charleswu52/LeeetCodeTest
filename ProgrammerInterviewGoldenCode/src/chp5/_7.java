package chp5;

import org.junit.Test;

/**
 * @author WuChao
 * @since 2021/5/9 上午11:18
 */
public class _7 {
    /**
     * 程序员面试金典(version 6) - 面试题 05.07. 配对交换
     * 难度: easy
     * <p>
     * 配对交换。编写程序，交换某个整数的奇数位和偶数位，尽量使用较少的指令（也就是说，位0与位1交换，位2与位3交换，以此类推）。
     *
     * <p>
     * 示例:
     * 输入：num = 2（或者0b10）
     * 输出 1 (或者 0b01)
     * <p>
     * 输入：num = 3
     * 输出：3
     *
     * <p>
     * 数据范围：
     * num的范围在[0, 2^30 - 1]之间，不会发生整数溢出。
     */

    /*
    使用位运算：
    对num提取奇数位和偶数位后，对两个的结果做异或
    int -> 32位
    奇数位全1 -> 0101.... 表示为 0x55555555
    偶数位全1 -> 1010.... 表示为 0xaaaaaaaa
    ans = (提取奇数位 << 1) + (提取偶数位 >> 1)
     */
    public int exchangeBits(int num) {
        return (((num & 0x55555555) << 1) | ((num & 0xaaaaaaaa) >> 1));
    }


    /*
    传统做法
     */
    public int exchangeBits2(int num) {
        String s = Integer.toBinaryString(num);
        if (s.length() % 2 == 1) {
            s = "0" + s;
        }
        char[] chars = s.toCharArray();
        for (int i = chars.length-1; i >0 ; i-=2) {
            char temp = chars[i];
            chars[i] = chars[i - 1];
            chars[i - 1] = temp;
        }
        String trans = new String(chars);
        return Integer.parseInt(trans, 2);

    }

    @Test
    public void test() {
        System.out.println(exchangeBits2(1));
    }

    }
