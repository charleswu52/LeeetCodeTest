import org.junit.Test;

/**
 * @author WuChao
 * @since 2021/3/22 下午1:25
 */
public class _44 {
    /**
     * 剑指 Offer 44. 数字序列中某一位的数字
     * 难度: medium
     * <p>
     * 数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。
     * <p>
     * 请写一个函数，求任意第n位对应的数字。
     *
     *
     *
     * <p>
     * 示例：
     * 输入：n = 3
     * 输出：3
     *
     * <p>
     * 数据范围：
     * 0 <= n < 2^31
     */

    /*
    初次分析题目，感觉是数学上的找规律题，先按找规律的方法来做
    在数据范围内 [0,2^31)，最多是有9位数
    0-9 ：               1位， 9*1=9
    10-99：              2位， 90*2=180
    100-999：            3位， 900*3=2700
    1000-9999：          4位， 9000*4=36000
    10000-99999：        5位， 90000*5=450000
    100000-999999：      6位， 900000*6=5400000
    1000000-9999999：    7位， 9000000*7=63000000
    10000000-99999999：  8位， 90000000*8=720000000
    100000000-999999999：9位， 900000000*9=8100000000

     */

    // 此种暴力求解的方法已经通过
    public int findNthDigit(int n) {
        if (n < 10) {
            return n;
        } else if (n - 9 <= 180) {
            int temp = n - 9;
            int a = temp / 2, b = temp % 2;
            if (b == 0) {
                return (10 + a - 1) % 10;
            } else {
                return ((10 + a) / (int) Math.pow(10, (2 - b)))%10;
            }
        } else if (n - 9 - 180 <= 2700) {
            int temp = n - 9 - 180;
            int a = temp / 3, b = temp % 3;
            if (b == 0) {
                return (100 + a - 1) % 10;
            } else {
                return ((100 + a) / (int) Math.pow(10, (3 - b)))%10;
            }

        } else if (n - 9 - 180 - 2700 <= 36000) {
            int temp = n - 9 - 180 - 2700;
            int a = temp / 4, b = temp % 4;
            if (b == 0) {
                return (1000 + a - 1) % 10;
            } else {
                return ((1000 + a) / (int) Math.pow(10, (4 - b)))%10;
            }

        } else if (n - 9 - 180 - 2700 - 36000 <= 450000) {
            int temp = n - 9 - 180 - 2700 - 36000;
            int a = temp / 5, b = temp % 5;
            if (b == 0) {
                return (10000 + a - 1) % 10;
            } else {
                return ((10000 + a) / (int) Math.pow(10, (5 - b)))%10;
            }

        } else if (n - 9 - 180 - 2700 - 36000 - 450000 <= 5400000) {
            int temp = n - 9 - 180 - 2700 - 36000 - 450000;
            int a = temp / 6, b = temp % 6;
            if (b == 0) {
                return (100000 + a - 1) % 10;
            } else {
                return ((100000 + a) / (int) Math.pow(10, (6 - b)))%10;
            }

        } else if (n - 9 - 180 - 2700 - 36000 - 450000 - 5400000 <= 63000000) {
            int temp = n - 9 - 180 - 2700 - 36000 - 450000 - 5400000;
            int a = temp / 7, b = temp % 7;
            if (b == 0) {
                return (1000000 + a - 1) % 10;
            } else {
                return ((1000000 + a) / (int) Math.pow(10, (7 - b)))%10;
            }

        } else if (n - 9 - 180 - 2700 - 36000 - 450000 - 5400000 - 63000000 <= 720000000) {
            int temp = n - 9 - 180 - 2700 - 36000 - 450000 - 5400000 - 63000000;
            int a = temp / 8, b = temp % 8;
            if (b == 0) {
                return (10000000 + a - 1) % 10;
            } else {
                return ((10000000 + a) / (int) Math.pow(10, (8 - b)))%10;
            }

        } else {
            int temp = n - 9 - 180 - 2700 - 36000 - 450000 - 5400000 - 63000000 - 720000000;
            int a = temp / 9, b = temp % 9;
            if (b == 0) {
                return (100000000 + a - 1) % 10;
            } else {
                return ((100000000 + a) / (int) Math.pow(10, (9 - b)))%10;
            }
        }

    }

    @Test
    public void test() {
        int n = 10000;
        System.out.println(findNthDigit(n));
    }
}
