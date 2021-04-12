package chp4;

import org.junit.Test;

/**
 * @author WuChao
 * @since 2021/4/10 下午8:04
 */
public class PatternMatch_KMP {
    /*
    数据结构算法实现：模式匹配算法——KMP算法

     */

    /**
     * KMP 算法实现
     * 查找模式串在主串的位置，查找成功返回主串的下标，失败返回-1
     *
     * @param str     主串
     * @param pattern 模式串
     * @param next
     * @return
     */
    public int kmp(String str, String pattern, int[] next) {
        for (int i = 0, j = 0; i < str.length(); i++) {
            while (j > 0 && str.charAt(i) != pattern.charAt(j)) {
                j = next[j - 1];
            }
            if (str.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            if (j == pattern.length()) {
                return i - j + 1;
            }
        }

        return -1;
    }

    /**
     * 根据模式串生成next数组
     *
     * @param pattern
     * @return
     */
    public int[] kmpNext(String pattern) {
        int[] next = new int[pattern.length()];
        next[0] = 0;
        for (int i = 1, j = 0; i < pattern.length(); i++) {
            while (j > 0 && pattern.charAt(j) != pattern.charAt(i)) {
                j = next[j - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;

    }

    @Test
    public void test() {
        // kmp 算法测试实现
        String str = "abdjfuhwifghkjuwljiw";
        String pattern = "abbcab";
        int[] next = kmpNext(pattern);
        System.out.println(kmp(str, pattern, next));

    }
}
