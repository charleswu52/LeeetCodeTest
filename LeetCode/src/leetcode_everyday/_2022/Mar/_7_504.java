package leetcode_everyday._2022.Mar;

import org.junit.Test;

/**
 * @author WuChao
 * @create 2022/3/7 8:54
 */
public class _7_504 {
    /**
     * 每日一题：2022/3/7
     * <p>
     * 504. 七进制数
     * <p>
     * 难度：easy
     * <p>
     * 给定一个整数 num，将其转化为 7 进制，并以字符串形式输出。
     * <p>
     * 示例1
     * <p>
     * 输入: num = 100
     *
     * 输出: "202"
     *
     * 示例2
     *
     * 输入: num = -7
     *
     * 输出: "-10"
     * <p>
     * 解释：
     * 第 2 天，我们有 security[0] >= security[1] >= security[2] <= security[3] <= security[4] 。
     * 第 3 天，我们有 security[1] >= security[2] >= security[3] <= security[4] <= security[5] 。
     * 没有其他日子符合这个条件，所以日子 2 和 3 是适合打劫银行的日子。
     *
     * <p>
     * 范围
     * <p>
     * -10^7 <= num <= 10^7
     */

    /*
    思路：短除法
     */
    public String convertToBase7(int num) {
        StringBuilder res = new StringBuilder();
        boolean isNeg = false;
        if (num < 0) {
            isNeg = true;
            num = -num;
        }
        while (true) {
            int mod = num % 7;
            res.append(mod);
            num /= 7;
            if (num == 0) {
                break;
            }
        }
        res.reverse();
        if (isNeg) {
            res.insert(0, '-');
        }
        return res.toString();
    }
    @Test
    public void test() {
        System.out.println(convertToBase7(9));
    }
}
