package chp5;

import org.junit.Test;

/**
 * @author WuChao
 * @since 2021/5/8 上午10:52
 */
public class _2 {
    /**
     * 程序员面试金典(version 6) - 面试题 05.02. 二进制数转字符串
     * 难度: medium
     * <p>
     * 二进制数转字符串。给定一个介于0和1之间的实数（如0.72），类型为double，打印它的二进制表达式。
     * 如果该数字无法精确地用32位以内的二进制表示，则打印“ERROR”。
     *
     * <p>
     * 示例:
     *  输入：0.625
     *  输出："0.101"
     * <p>
     *  输入：0.1
     *  输出："ERROR"
     *  提示：0.1无法被二进制准确表示
     *
     * <p>
     * 数据范围：
     * 32位包括输出中的"0."这两位。
     */

    /*
    思路： 十进制的小数转换为二进制小数，主要是利用小数部分乘2后，取整数部分，直至小数点后为0
     */
    public String printBin(double num) {
        // 小数部分的值
        double r = num;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("0.");

        // 将小数部分转化为二进制
        int count = 32; // 限制小数部分位数最多为32位，如果超过32为则抛出异常
        double nums = 0;
        while (r > 0.0000000001) {
            count--;
            if (count == 0) {
                return "ERROR";
            }
            nums = r * 2;
            if (nums >= 1) {
                stringBuilder.append(1);
                r = nums - 1;
            } else {
                stringBuilder.append(0);
                r = nums;
            }
        }

        return stringBuilder.toString();

    }

    @Test
    public void test() {
        double num = 0.8125;
        System.out.println(printBin(num));

    }
}
