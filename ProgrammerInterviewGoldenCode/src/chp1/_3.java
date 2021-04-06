package chp1;

import org.junit.Test;

/**
 * @author WuChao
 * @since 2021/4/4 上午11:03
 */
public class _3 {
    /**
     * 程序员面试金典(version 6) - 面试题 01.03. URL化
     * 难度: easy
     * <p>
     * URL化。编写一种方法，将字符串中的空格全部替换为%20。假定该字符串尾部有足够的空间存放新增字符，并且知道字符串的“真实”长度。
     * （注：用Java实现的话，请使用字符数组实现，以便直接在数组上操作。）
     *
     * <p>
     * 例如：
     * 输入："Mr John Smith    ", 13
     * 输出："Mr%20John%20Smith"
     *
     * <p>
     * 数据范围：
     * 字符串长度在 [0, 500000] 范围内。
     */

    /**
     * 直接调用String API的方式，简单但并不是题目要考察的东西
     */
    public String replaceSpaces(String S, int length) {
        String substring = S.substring(0, length);
        return substring.replace(" ", "%20");
    }


    public String replaceSpaces2(String S, int length) {
        if (S.length() < 1) {
            return S;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (S.charAt(i) != ' ') {
                stringBuilder.append(S.charAt(i));
            } else {
                stringBuilder.append("%20");
            }
        }
        return stringBuilder.toString();
    }


    /**
     * 方法3：使用字符数组
     * 根据题目的提示，应该使用字符数组来做
     * 根据S的实际长度和提供的不含多余空格的length 设置前后两个指针i与index 向前移动遇到空格就用%20替换 否则就把前面的字符移动到后面
     * @param S
     * @param length
     * @return
     */
    public String replaceSpaces3(String S, int length) {
        if (S.length() < 1) {
            return S;
        }
        char[] chars = S.toCharArray();
        int index = chars.length - 1;
        for (int i = length - 1; i >= 0; i--) {
            if (chars[i] == ' ') {
                chars[index--] = '0';
                chars[index--] = '2';
                chars[index--] = '%';
            } else {
                chars[index--] = chars[i];
            }
        }
        return new String(chars, index + 1, chars.length - index - 1);
    }


    @Test
    public void test() {
        String s = "Mr John Smith    ";
        int length = 13;
        System.out.println(replaceSpaces(s, length));
    }
}
