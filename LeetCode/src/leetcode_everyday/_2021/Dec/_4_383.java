package leetcode_everyday._2021.Dec;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WuChao
 * @create 2021/12/4 9:47
 */
public class _4_383 {
    /**
     * 每日一题：2021/12/4
     * 383. 赎金信
     * 难度：easy
     * <p>
     * 为了不在赎金信中暴露字迹，从杂志上搜索各个需要的字母，组成单词来表达意思。
     * <p>
     * 给你一个赎金信 (ransomNote) 字符串和一个杂志(magazine)字符串，判断 ransomNote 能不能由 magazines 里面的字符构成。
     * <p>
     * 如果可以构成，返回 true ；否则返回 false 。
     * <p>
     * magazine 中的每个字符只能在 ransomNote 中使用一次。
     *
     * <p>
     * 示例
     * <p>
     * 输入：ransomNote = "aa", magazine = "ab"
     * 输出：false
     *
     *
     * <p>
     * 范围
     * <p>
     * 1 <= ransomNote.length, magazine.length <= 10^5
     * ransomNote 和 magazine 由小写英文字母组成
     */

    /*
    思路1：哈希表
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> store = new HashMap<>();
        for (char c : magazine.toCharArray()) {
            store.put(c, store.getOrDefault(c, 0) + 1);
        }
        for (char c : ransomNote.toCharArray()) {
            if (!store.containsKey(c) || (store.containsKey(c) && store.get(c) == 0)) {
                return false;
            } else {
                store.put(c, store.get(c) - 1);
            }
        }
        return true;
    }

    /*
    思路2： 字符统计
     */
    public boolean canConstruct2(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) {
            return false;
        }
        int[] cnt = new int[26];
        for (char ch : magazine.toCharArray()) {
            cnt[ch - 'a']++;
        }
        for (char ch : ransomNote.toCharArray()) {
            cnt[ch - 'a']--;
            if (cnt[ch - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }



    }
