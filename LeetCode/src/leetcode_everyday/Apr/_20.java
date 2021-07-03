package leetcode_everyday.Apr;

import org.junit.Test;

/**
 * @author WuChao
 * @since 2021/4/20 上午7:56
 */
public class _20 {
    /**
     * 每日一题：2021/4/20
     * 28. 实现 strStr()
     * 难度: easy
     * <p>
     * 实现 strStr() 函数。
     * 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。
     * 如果不存在，则返回  -1 。
     * <p>
     * <p>
     * 说明：
     * <p>
     * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
     * <p>
     * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与 C 语言的 strstr() 以及 Java 的 indexOf() 定义相符。
     *
     * <p>
     * 示例：
     * 输入：haystack = "hello", needle = "ll"
     * 输出：2
     *
     *
     * <p>
     * 数据范围：
     * 0 <= haystack.length, needle.length <= 5 * 104
     * haystack 和 needle 仅由小写英文字符组成
     */

    public int strStr(String haystack, String needle) {
        if (needle.length() == 0) {
            return 0;
        }
        if (needle.length() > haystack.length()) {
            return -1;
        }

        // 模式匹配算法
//        return BF(haystack,needle);
        return KMP(haystack, needle);

    }

    /**
     * 模式匹配算法--暴力BF算法
     * 无措，但超时
     * @param s
     * @param t
     * @return
     */
    public int BF(String s, String t) {
        for (int i = 0; i < s.length(); i++) {
            int j;
            int tempI = i;
            for (j = 0; j < t.length() && tempI<s.length(); j++) {
                if (s.charAt(tempI) == t.charAt(j)) {
                    tempI++;
                } else {
                    break;
                }
            }
            if ( j == t.length()) {
                return i;
            }
        }
        return -1;
    }

    /**
     *  模式匹配算法--KMP算法
     * @param s
     * @param t
     * @return
     */
    public int KMP(String s, String t) {
        int[] next = new int[t.length()];
        next[0] = 0;
        for (int i = 1, j = 0; i < t.length(); i++) {
            while (j > 0 && t.charAt(j) != t.charAt(i)) {
                j = next[j - 1];
            }
            if (t.charAt(i) == t.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        // 遍历 s
        for (int i = 0, j = 0; i < s.length(); i++) {
            while (j > 0 && s.charAt(i) != t.charAt(j)) {
                j = next[j - 1];
            }
            if (s.charAt(i) == t.charAt(j)) {
                j++;
            }
            if (j == t.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }



    @Test
    public void test() {

        String haystack = "mississippi";
        String needle = "issipi";
        System.out.println(strStr(haystack, needle));
    }

}
