package chp1;

/**
 * @author WuChao
 * @since 2021/4/6 上午10:00
 */
public class _6 {
    /**
     * 程序员面试金典(version 6) - 面试题 01.06. 字符串压缩
     * 难度: easy
     * <p>
     * 字符串压缩。利用字符重复出现的次数，编写一种方法，实现基本的字符串压缩功能。
     * 比如，字符串aabcccccaaa会变为a2b1c5a3。若“压缩”后的字符串没有变短，则返回原先的字符串。你可以假设字符串中只包含大小写英文字母（a至z）。
     *
     * <p>
     * 例如：
     * 输入："abbccd"
     * 输出："abbccd"
     * 解释："abbccd"压缩后为"a1b2c2d1"，比原字符串长度更长。
     *
     * <p>
     * 数据范围：
     * 字符串长度在[0, 50000]范围内。
     */
    public String compressString(String S) {
        if (S.length() < 2) {
            return S;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(S.charAt(0));
        int temp = 1;
        for (int i = 1; i < S.length(); i++) {
            if (S.charAt(i) == S.charAt(i - 1)) {
                temp++;
            } else {
                stringBuilder.append(temp);
                stringBuilder.append(S.charAt(i));
                temp = 1;
            }
        }
        stringBuilder.append(temp);
        return stringBuilder.toString().length() < S.length() ? stringBuilder.toString() : S;
    }

}
