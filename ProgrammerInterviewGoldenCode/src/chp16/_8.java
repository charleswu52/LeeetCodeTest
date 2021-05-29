package chp16;

import org.junit.Test;

/**
 * @author WuChao
 * @since 2021/5/29 上午10:19
 */
public class _8 {
    /**
     * 程序员面试金典(version 6) - 面试题 16.08. 整数的英语表示
     * 难度: hard
     * <p>
     * 给定一个整数，打印该整数的英文描述。
     *
     * <p>
     * 示例:
     * 输入: 123
     * 输出: "One Hundred Twenty Three"
     *
     * 输入: 12345
     * 输出: "Twelve Thousand Three Hundred Forty Five"
     *
     * 输入: 1234567
     * 输出: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
     *
     * 输入: 1234567891
     * 输出: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
     * <p>
     * 数据范围：
     */

    //  预先构建一些数组 0-19
    String[] special = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
        "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    // 十位数
    String[] tens = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    // 千位数，百万位，十亿
    String[] throusands = {"", " Thousand", " Million", " Billion"};

    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }
        StringBuffer stringBuffer = new StringBuffer("");
        String res="";
        int index = 0;
        while (num > 0) {
            if (num % 1000 > 0) {
                res = helper(num % 1000) + throusands[index] + (res != "" ? (" " + res) : "");
            }
            num /= 1000;
            ++index;
        }
        return res;

    }

    /**
     * 解决三位以上的数字
     * @param num
     * @return
     */
    public String helper(int num) {
        if (num < 20) {
            return special[num];
        } else if (num >= 20 && num <= 99) {
            return tens[num / 10] + (num % 10 != 0 ? (" " + special[num % 10]) : "");

        } else {
            String one = helper(num % 100);
            return special[num / 100] + " Hundred" + (one != "" ? (" " + one) : "");
        }
    }

    @Test
    public void test() {
        int num = 1234;
        System.out.println(numberToWords(num));
    }
}
