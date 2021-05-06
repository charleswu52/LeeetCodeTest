package leetcodetest.May;

import org.junit.Test;

/**
 * @author WuChao
 * @since 2021/5/5 下午4:20
 */
public class _3 {
    /**
     * 每日一题：2021/5/3
     * 7. 整数反转
     * 难度: easy
     * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
     *
     * 如果反转后整数超过 32 位的有符号整数的范围 [−231,  231− 1] ，就返回 0。
     *
     * 假设环境不允许存储 64 位整数（有符号或无符号）。
     *
     *
     * <p>
     * 示例：
     * 输入：x = 123
     * 输出：321
     *
     * 输入：x = -123
     * 输出：-321
     *
     * <p>
     * 数据范围：
     * -231 <= x <= 231 - 1
     */

    public int reverse(int x) {
        if (x == 0) {
            return x;
        }
        int sign = 1;
        if (x < 0) {
            sign = -1;
        }
        long temp = Math.abs((long) x);
        String s = String.valueOf(temp);
        char[] chars = s.toCharArray();
        int left = 0, right = chars.length - 1;
        while (left < right) {
            char t = chars[left];
            chars[left] = chars[right];
            chars[right] = t;
            left++;
            right--;
        }
        String tran = new String(chars);
         long l = Long.parseLong(tran);
        if (l > Math.pow(2, 31)) {
            return 0;
        }
        return  (int) l *sign;
    }


    public int reverse2(int x) {
        int res = 0;
        while (x != 0) {
            if (res < Integer.MIN_VALUE / 10 || res > Integer.MAX_VALUE / 10) {
                return 0;
            }
            int digit = x % 10;
            x /= 10;
            res = res * 10 + digit;
        }
        return res;

    }



        @Test
    public void test() {
        int x = -2147483648;
        System.out.println(reverse(x));
    }
}
