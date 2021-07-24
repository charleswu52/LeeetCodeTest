package leetcode_everyday.Jul;

/**
 * @author WuChao
 * @create 2021/7/24 8:17
 */
public class _24 {
    /**
     * <p> 每日一题：2021/7/24</p>
     * <p> 1736. 替换隐藏数字得到的最晚时间 </p>
     * <p>难度: easy</p>
     * <p>
     * 给你一个字符串 time ，格式为 hh:mm（小时：分钟），其中某几位数字被隐藏（用 ? 表示）。
     * <p>
     * 有效的时间为 00:00 到 23:59 之间的所有时间，包括 00:00 和 23:59 。
     * <p>
     * 替换 time 中隐藏的数字，返回你可以得到的最晚有效时间。
     *
     *
     * </p>
     * <p>示例</p>
     * <p>
     * 输入：time = "2?:?0"
     * 输出："23:50"
     * 解释：以数字 '2' 开头的最晚一小时是 23 ，以 '0' 结尾的最晚一分钟是 50 。
     * <p>
     * 输入：time = "0?:3?"
     * 输出："09:39"
     * </p>
     *
     * <p>范围</p>
     * <p>
     * time 的格式为 hh:mm
     * 题目数据保证你可以由输入的字符串生成有效的时间
     * </p>
     */

    /*
    思路：模拟
    考虑每一位上的可能情况进行模拟赋值
     */

    public String maximumTime(String time) {
        char[] chars = new char[5];
        chars[2] = ':';

        for (int i = 0; i < time.length(); i++) {
            char cur = time.charAt(i);
            if (i == 0) {
                if (cur == '?') {
                    if (time.charAt(1) != '?' && time.charAt(1) - '0' > 3) {
                        chars[i] = '1';
                    } else {
                        chars[i] = '2';
                    }
                } else {
                    chars[i] = cur;
                }
            } else if (i == 1) {
                if (cur == '?') {
                    if (chars[0] == '2') {
                        chars[1] = '3';
                    } else {
                        chars[1] = '9';
                    }
                } else {
                    chars[1] = cur;
                }
            } else if (i == 3) {
                if (cur == '?') {
                    chars[i] = '5';
                } else {
                    chars[i] = cur;
                }
            } else if (i == 4) {
                if (cur == '?') {
                    chars[4] = '9';
                } else {
                    chars[4] = cur;
                }
            }

        }
        return new String(chars);


    }
}
