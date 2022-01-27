package leetcode_everyday._2022.Jan;

import org.junit.Test;

import java.beans.Transient;
import java.util.Arrays;

/**
 * @author WuChao
 * @create 2022/1/27 9:54
 */
public class _27_2047 {
    /**
     * 每日一题：2022/1/27
     * <p>
     * 2047. 句子中的有效单词数
     * <p>
     * 难度：easy
     * <p>
     * 句子仅由小写字母（'a' 到 'z'）、数字（'0' 到 '9'）、连字符（'-'）、标点符号（'!'、'.' 和 ','）以及空格（' '）组成。
     * 每个句子可以根据空格分解成 一个或者多个 token ，这些 token 之间由一个或者多个空格 ' ' 分隔。
     * <p>
     * 如果一个 token 同时满足下述条件，则认为这个 token 是一个有效单词：
     * <p>
     * 仅由小写字母、连字符和/或标点（不含数字）。
     * 至多一个 连字符 '-' 。如果存在，连字符两侧应当都存在小写字母（"a-b" 是一个有效单词，但 "-ab" 和 "ab-" 不是有效单词）。
     * 至多一个 标点符号。如果存在，标点符号应当位于 token 的 末尾 。
     * 这里给出几个有效单词的例子："a-b."、"afad"、"ba-c"、"a!" 和 "!" 。
     * <p>
     * 给你一个字符串 sentence ，请你找出并返回 sentence 中 有效单词的数目 。
     * <p>
     * 输入：sentence = "alice and  bob are playing stone-game10"
     * <p>
     * 输出：5
     * <p>
     * 解释：句子中的有效单词是 "alice"、"and"、"bob"、"are" 和 "playing"
     * "stone-game10" 不是有效单词，因为它含有数字
     * <p>
     * 范围
     * <p>
     * 1 <= sentence.length <= 1000
     * sentence 由小写英文字母、数字（0-9）、以及字符（' '、'-'、'!'、'.' 和 ','）组成
     * 句子中至少有 1 个 token
     */

    public int countValidWords(String sentence) {
        int res = 0;
        String[] strings = sentence.split(" ");
        for (String str : strings) {
            int len = str.length();
            boolean isToken = len > 0;
            boolean has_ = false;
            for (int i = 0; i < len; i++) {
                char ch = str.charAt(i);
                if ((ch >= '0' && ch <= '9')) {
                    isToken = false;
                    break;
                }
                if (ch == '-'){
                    if (has_ == false) {
                        has_ = true;
                        if (i == 0 || i == len - 1) {
                            isToken = false;
                            break;
                        } else if (!(str.charAt(i - 1) >= 'a' && str.charAt(i - 1) <= 'z' && str.charAt(i + 1) >= 'a' && str.charAt(i + 1) <= 'z')) {
                            isToken = false;
                            break;
                        }
                    }else {
                        isToken = false;
                        break;
                    }
                }
                if ((ch == '!' || ch == '.' || ch == ',') && i != len - 1) {
                    isToken = false;
                    break;
                }
            }
            if (isToken) {
                res++;
            }
        }
        return res;


    }

    @Test
    public void test() {
        String sentence = "he bought 2 pencils, 3 erasers, and 1  pencil-sharpener.";
        System.out.println(countValidWords(sentence));
    }

}
