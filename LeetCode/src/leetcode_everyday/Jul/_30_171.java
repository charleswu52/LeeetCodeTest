package leetcode_everyday.Jul;

import org.junit.Test;

/**
 * @author WuChao
 * @create 2021/7/30 8:21
 */
public class _30_171 {
    /**
     * 每日一题：2021/7/29
     * 171. Excel表列序号
     * 难度: easy
     * <p>
     * 给定一个Excel表格中的列名称，返回其相应的列序号。
     * <p>
     * 例如，
     * <p>
     * A -> 1
     * B -> 2
     * C -> 3
     * ...
     * Z -> 26
     * AA -> 27
     * AB -> 28
     * ...
     * 示例 1:
     * <p>
     * 输入: "A"
     * 输出: 1
     * 示例 2:
     * <p>
     * 输入: "AB"
     * 输出: 28
     * 示例 3:
     * <p>
     * 输入: "ZY"
     * 输出: 701
     */

    /*
    思路：数学进制转换
     */

    public int titleToNumber(String columnTitle) {
        int n = columnTitle.length();
        int res = 0;
        for (int i = 0, j = n - 1; i < n; i++, j--) {
            res += (columnTitle.charAt(i) - 'A' + 1) * Math.pow(26, j);

        }
        return res;
    }

    @Test
    public void test() {
        String str = "FXSHRXW";
        System.out.println(titleToNumber(str));
    }

}
