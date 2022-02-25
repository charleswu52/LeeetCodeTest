package leetcode_everyday._2022.Feb;

/**
 * @author WuChao
 * @create 2022/2/25 8:30
 */
public class _25_537 {
    /**
     * 每日一题：2022/2/25
     * <p>
     * 537. 复数乘法
     * <p>
     * 难度：medium
     * <p>
     * 复数 可以用字符串表示，遵循 "实部+虚部i" 的形式，并满足下述条件：
     * <p>
     * 实部 是一个整数，取值范围是 [-100, 100]
     * 虚部 也是一个整数，取值范围是 [-100, 100]
     * i2 == -1
     * 给你两个字符串表示的复数 num1 和 num2 ，请你遵循复数表示形式，返回表示它们乘积的字符串。
     *
     * <p>
     * 示例
     * <p>
     * 输入：num1 = "1+1i", num2 = "1+1i"
     * <p>
     * 输出："0+2i"
     * <p>
     * 解释：(1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i ，你需要将它转换为 0+2i 的形式。
     *
     * <p>
     * 范围
     * <p>
     * num1 和 num2 都是有效的复数表示。
     */

    /*
    思路：简单模拟题
     */
    public String complexNumberMultiply(String num1, String num2) {
        String[] num1s = num1.substring(0, num1.length() - 1).split("[+]");
        int a = Integer.parseInt(num1s[0]), b = Integer.parseInt(num1s[1]);
        String[] num2s = num2.substring(0, num2.length() - 1).split("[+]");
        int c = Integer.parseInt(num2s[0]), d = Integer.parseInt(num2s[1]);
        int t1 = a * c - (b * d);
        int t2 = a * d + b * c;
        StringBuilder ans = new StringBuilder();
        ans.append(t1);
        ans.append("+");
        ans.append(t2);
        ans.append("i");
        return ans.toString();
    }
}
