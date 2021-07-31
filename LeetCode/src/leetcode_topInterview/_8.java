package leetcode_topInterview;

/**
 * @author WuChao
 * @create 2021/7/31 9:26
 */
public class _8 {
    /**
     * codeTop 热题 面试常考
     * <p>
     * 8. 字符串转换整数 (atoi)
     * <p>
     * 难度：medium
     * <p>
     * 请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。
     * <p>
     * 函数 myAtoi(string s) 的算法如下：
     * 1.读入字符串并丢弃无用的前导空格
     * 2.检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
     * 3.读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
     * 4.将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 2 开始）。
     * <p>
     * 如果整数数超过 32 位有符号整数范围 [−231,  231 − 1] ，需要截断这个整数，使其保持在这个范围内。
     * 具体来说，小于 −231 的整数应该被固定为 −231 ，大于 231 − 1 的整数应该被固定为 231 − 1 。
     * 返回整数作为最终结果。
     * 注意：
     * <p>
     * 本题中的空白字符只包括空格字符 ' ' 。
     * 除前导空格或数字后的其余字符串外，请勿忽略 任何其他字符。
     * <p>
     * <p>
     * 0 <= s.length <= 200
     * s 由英文字母（大写和小写）、数字（0-9）、' '、'+'、'-' 和 '.' 组成
     */


    /*
    思路： 模拟题
    注意题目中的要求，各种情况尽量都考虑到
     */
    public int myAtoi(String s) {
        int len = s.length();
        char[] charArray = s.toCharArray();

        // 去除前导空格
        int idx = 0;
        while (idx < s.length() && charArray[idx] == ' ') {
            idx++;
        }
        if (idx == len) {
            return 0;
        }

        // 判断符号
        int sign = 1;
        char charFirst = charArray[idx];
        if (charFirst == '+') {
            idx++;
        } else if (charFirst == '-') {
            sign = -1;
            idx++;
        }

        // 转换数字，注意判断越界
        int res = 0;
        while (idx < len) {
            char currChar = charArray[idx];
            // 先判断不合理的情况
            if (currChar > '9' || currChar < '0') {
                break;
            }

            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && (currChar - '0') > Integer.MAX_VALUE % 10)) {
                return Integer.MAX_VALUE;
            }
            if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && (currChar - '0') > -(Integer.MIN_VALUE % 10))) {
                return Integer.MIN_VALUE;
            }
            res = res * 10 + sign * (currChar - '0');
            idx++;
        }
        return res;

    }
}
