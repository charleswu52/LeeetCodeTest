import org.junit.Test;

/**
 * @author WuChao
 * @since 2021/3/23 下午12:30
 */
public class _46 {
    /**
     * 剑指 Offer 46. 把数字翻译成字符串
     * 难度: medium
     * <p>
     * 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。
     * 一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
     *
     * <p>
     * 示例：
     * 输入: 12258
     * 输出: 5
     * 解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
     *
     * <p>
     * 数据范围：
     * 0 <= num < 2^31
     */

    public int translateNum(int num) {
        String src = String.valueOf(num);

        int a = 1, b = 1;
        for (int i = 2; i <= src.length(); i++) {
            String temp = src.substring(i - 2, i);
            int c = temp.compareTo("10") >= 0 && temp.compareTo("25") <= 0 ? a + b : a;
            b = a;
            a = c;


        }

        return a;

    }

    //

    @Test
    public void test() {
        int num = 12258;
        System.out.println(translateNum(num));

    }
}
