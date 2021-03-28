import org.junit.Test;

/**
 * @author WuChao
 * @since 2021/3/28 下午2:49
 */
public class _58_1 {
    /**
     * 剑指 Offer 58 - I. 翻转单词顺序
     * 难度: easy
     * <p>
     * 输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。
     * 为简单起见，标点符号和普通字母一样处理。例如输入字符串"I am a student. "，则输出"student. a am I"。
     * <p>
     * 例如：
     * 输入: " hello world! "
     * 输出: "world! hello"
     * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
     * <p>
     * 数据范围：
     * 无空格字符构成一个单词。
     * 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
     * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
     */

    /**
     * 先对字符串进行切割然后逆序追加输出
     * 注意这里 在循环拼接字符串的时候，使用 StingBuilder的效率是高于 String的+=方式的
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        s = s.trim();
        String[] strings = s.split(" ");
        String res = "";
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = strings.length - 1; i >= 0; i--) {
            if (strings[i].equals("")) {
                continue;
            }
            res += strings[i];
            stringBuilder.append(strings[i]);
            if (i != 0) {
                res += " ";
                stringBuilder.append(" ");
            }
        }
        return stringBuilder.toString();
    }

    @Test
    public void test() {
        String s = "a good   example";
        System.out.println(reverseWords(s));
    }
}
