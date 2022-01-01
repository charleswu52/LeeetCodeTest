package leetcode_everyday._2021.Dec;

import org.junit.Test;

/**
 * @author WuChao
 * @create 2021/12/10 上午9:40
 */
public class _10_748 {
    /**
     * 每日一题：2021/12/10
     * <p>
     * 748. 最短补全词
     * <p>
     * 难度：easy
     * <p>
     * 给你一个字符串 licensePlate 和一个字符串数组 words ，请你找出并返回 words 中的 最短补全词 。
     *
     * 补全词 是一个包含 licensePlate 中所有的字母的单词。在所有补全词中，最短的那个就是 最短补全词 。
     *
     * 在匹配 licensePlate 中的字母时：
     * 忽略 licensePlate 中的 数字和空格 。
     * 不区分大小写。
     *
     * 如果某个字母在 licensePlate 中出现不止一次，那么该字母在补全词中的出现次数应当一致或者更多。
     * 例如：licensePlate = "aBc 12c"，那么它的补全词应当包含字母 'a'、'b' （忽略大写）和两个 'c' 。可能的 补全词 有 "abccdef"、"caaacab" 以及 "cbca" 。
     *
     * 请你找出并返回 words 中的 最短补全词 。题目数据保证一定存在一个最短补全词。当有多个单词都符合最短补全词的匹配条件时取 words 中 最靠前的 那个。
     * <p>
     * 示例 1：
     * <p>
     * 输入：licensePlate = "1s3 PSt", words = ["step", "steps", "stripe", "stepple"]
     * 输出："steps"
     * 解释：最短补全词应该包括 "s"、"p"、"s"（忽略大小写） 以及 "t"。
     * "step" 包含 "t"、"p"，但只包含一个 "s"，所以它不符合条件。
     * "steps" 包含 "t"、"p" 和两个 "s"。
     * "stripe" 缺一个 "s"。
     * "stepple" 缺一个 "s"。
     * 因此，"steps" 是唯一一个包含所有字母的单词，也是本例的答案。
     *
     * <p>
     * 范围
     * <p>
     * 1 <= licensePlate.length <= 7
     * licensePlate 由数字、大小写字母或空格 ' ' 组成
     * 1 <= words.length <= 1000
     * 1 <= words[i].length <= 15
     * words[i] 由小写英文字母组成
     */

    /*
    思路1： 使用数组统计
     */
    public String shortestCompletingWord(String licensePlate, String[] words) {
        licensePlate = licensePlate.toLowerCase();
        int[] cnt = new int[26];
        int count = 0;
        for (char c : licensePlate.toCharArray()) {
            if ('a' <= c && c <= 'z') {
                cnt[c - 'a']++;
                count++;
            }
        }
        int minLen = Integer.MAX_VALUE;
        int resIdx = 0;
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() >= count) {
                int[] cnt2 = new int[26];
                for (char c : words[i].toCharArray()) {
                    cnt2[c - 'a']++;
                }
                if (check(cnt, cnt2) && minLen > words[i].length()) {
                    resIdx = i;
                    minLen = words[i].length();
                }
            }
        }
        return words[resIdx];
    }

    public boolean check(int[] cnt1, int[] cnt2) {
        for (int i = 0; i < cnt1.length; i++) {
            if (cnt1[i] != 0 && cnt1[i]>cnt2[i]) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void test() {
        String s ="1s3 PSt";
        String[] words = {"step", "steps", "stripe", "stepple"};
        System.out.println(shortestCompletingWord(s, words));
    }
}
