package leetcode_everyday._2021.Mar;

/**
 * @author WuChao
 * @since 2021/3/9 上午8:15
 */
public class _9 {
    /**
     * 每日一题：2021/3/9
     * 1047. 删除字符串中的所有相邻重复项
     * 难度: easy
     * 给出由小写字母组成的字符串 S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。
     * 在 S 上反复执行重复项删除操作，直到无法继续删除。
     * 在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。
     * <p>
     * 示例:
     * 输入："abbaca"
     * 输出："ca"
     * 解释：
     * 例如，在 "abbaca" 中，我们可以删除 "bb" 由于两字母相邻且相同，这是此时唯一可以执行删除操作的重复项。
     * 之后我们得到字符串 "aaca"，其中又只有 "aa" 可以执行重复项删除操作，所以最后的字符串为 "ca"。
     *
     * <p>
     * 数据范围：
     * 1 <= s.length <= 20000
     * s 仅由小写英文字母组成
     *
     */

    /**
     * 思路1：暴力解法
     *
     * @param S
     * @return
     */
    public String removeDuplicates(String S) {
        if (S.length() < 2) {
            return S;
        }
        for (int i = 1; i < S.length(); i++) {
            if (S.charAt(i) == S.charAt(i - 1)) {
                S = S.replace(S.substring(i - 1, i+1), "");
                i = 0;
            }
        }
        return S;
    }

    /**
     * 思路2：使用栈
     * @param S
     * @return
     */
    public String removeDuplicates2(String S) {
        StringBuffer stack = new StringBuffer();
        int top = -1;
        for (int i = 0; i < S.length(); i++) {
            char ch = S.charAt(i);
            if (top >= 0 && stack.charAt(top) == ch) {
                stack.deleteCharAt(top);
                --top;
            } else {
                stack.append(ch);
                ++top;
            }
        }
        return stack.toString();
    }



    public void _21_3_9() {
        String s = "abbaca";
//        s = s.replace(s.substring(1, 2), "");
//        System.out.println(s);
        System.out.println(removeDuplicates(s));
        System.out.println(removeDuplicates2(s));
    }
}
