package leetcode_everyday._2022.Feb;

/**
 * @author WuChao
 * @create 2022/2/23 8:48
 */
public class _23_917 {
    /**
     * 每日一题：2022/2/23
     * <p>
     * 917. 仅仅反转字母
     * <p>
     * 难度：easy
     * <p>
     * 给你一个字符串 s ，根据下述规则反转字符串：
     * <p>
     * 所有非英文字母保留在原有位置。
     * 所有英文字母（小写或大写）位置反转。
     * 返回反转后的 s 。
     * <p>
     * 示例
     * <p>
     * 输入：s = "ab-cd"
     * <p>
     * 输出："dc-ba"
     * <p>
     * 范围
     * <p>
     * 1 <= s.length <= 100
     * s 仅由 ASCII 值在范围 [33, 122] 的字符组成
     * s 不含 '\"' 或 '\\'
     */

    /*
    思路1：直接一遍模拟 将可以
     */
    public String reverseOnlyLetters(String s) {
        char[] chars = s.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : chars) {
            if ((c >= 'a' & c <= 'z') || (c >= 'A' && c <= 'Z')) {
                stringBuilder.append(c);
            }
        }
        stringBuilder.reverse();
        for (int i = 0, j = 0; i < chars.length; i++) {
            char c = chars[i];
            if ((c >= 'a' & c <= 'z') || (c >= 'A' && c <= 'Z')) {
                chars[i] = stringBuilder.charAt(j);
                j++;
            }
        }
        return new String(chars);
    }

    /*
    思路2：双指针
     */
    public String reverseOnlyLetters2(String s) {
        char[] chars = s.toCharArray();
        int len = chars.length;
        for (int i = 0, j = len - 1; i < j; ) {
            while (i < j && !((chars[i] >= 'a' & chars[i] <= 'z') || (chars[i] >= 'A' && chars[i] <= 'Z'))) i++;
            while (i < j && !((chars[j] >= 'a' & chars[j] <= 'z') || (chars[j] >= 'A' && chars[j] <= 'Z'))) j--;
            if (i < j) {
                char temp = chars[i];
                chars[i++] = chars[j];
                chars[j--] = temp;
            }

        }
        return new String(chars);

    }
}
