package leetcode_everyday._2021.Jun;

import org.junit.Test;

/**
 * @author WuChao
 * @create 2021/6/29 10:08
 */
public class _29 {
    /**
     * 每日一题：2021/6/29
     * 168. Excel表列名称
     * 难度: easy
     * <p>
     * 给定一个正整数，返回它在 Excel 表中相对应的列名称。
     * <p>
     * 例如，
     * <p>
     * 1 -> A
     * 2 -> B
     * 3 -> C
     * ...
     * 26 -> Z
     * 27 -> AA
     * 28 -> AB
     * ...
     * <p>
     * 示例
     * 输入: 1
     * 输出: "A"
     * 示例 2:
     * <p>
     * 输入: 28
     * 输出: "AB"
     * 示例 3:
     * <p>
     * 输入: 701
     * 输出: "ZY"
     * <p>
     * 数据范围：
     */


    public String convertToTitle(int columnNumber) {
        StringBuffer sb = new StringBuffer();
        while (columnNumber > 0) {
            columnNumber--;     // 对26取模，从1开始，需要先减一实现整体偏移
            sb.append((char)(columnNumber % 26 + 'A'));
            columnNumber /= 26;
        }
        return sb.reverse().toString();


    }

    @Test
    public void test() {
        System.out.println(convertToTitle(52));

    }

}
