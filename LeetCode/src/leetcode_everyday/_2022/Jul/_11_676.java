package leetcode_everyday._2022.Jul;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author WuChao
 * @create 2022/7/12 11:38
 */
public class _11_676 {
    /**
     * 每日一题：2022/7/11
     * <p>
     * 676. 实现一个魔法字典
     * <p>
     * 难度：medium
     * <p>
     * 设计一个使用单词列表进行初始化的数据结构，单词列表中的单词 互不相同 。
     * 如果给出一个单词，请判定能否只将这个单词中一个字母换成另一个字母，使得所形成的新单词存在于你构建的字典中。
     * <p>
     * 实现 MagicDictionary 类：
     * <p>
     * MagicDictionary() 初始化对象
     * void buildDict(String[] dictionary) 使用字符串数组 dictionary 设定该数据结构，dictionary 中的字符串互不相同
     * bool search(String searchWord) 给定一个字符串 searchWord ，判定能否只将字符串中 一个 字母换成另一个字母，
     * 使得所形成的新字符串能够与字典中的任一字符串匹配。如果可以，返回 true ；否则，返回 false 。
     * <p>
     * 示例
     * <p>
     * 输入
     * ["MagicDictionary", "buildDict", "search", "search", "search", "search"]
     * [[], [["hello", "leetcode"]], ["hello"], ["hhllo"], ["hell"], ["leetcoded"]]
     * <p>
     * 输出
     * [null, null, false, true, false, false]
     * <p>
     * 解释
     * MagicDictionary magicDictionary = new MagicDictionary();
     * magicDictionary.buildDict(["hello", "leetcode"]);
     * magicDictionary.search("hello"); // 返回 False
     * magicDictionary.search("hhllo"); // 将第二个 'h' 替换为 'e' 可以匹配 "hello" ，所以返回 True
     * magicDictionary.search("hell"); // 返回 False
     * magicDictionary.search("leetcoded"); // 返回 False
     * <p>
     * 范围
     * <p>
     * 11 <= dictionary.length <= 100
     * 1 <= dictionary[i].length <= 100
     * dictionary[i] 仅由小写英文字母组成
     * dictionary 中的所有字符串 互不相同
     * 1 <= searchWord.length <= 100
     * searchWord 仅由小写英文字母组成
     * buildDict 仅在 search 之前调用一次
     * 最多调用 100 次 search
     */

    /*
    思路1：枚举优化
     */
    class MagicDictionary {
        Map<Integer, List<String>> map;

        public MagicDictionary() {
            this.map = new HashMap<>();
        }

        public void buildDict(String[] dictionary) {
            for (String s : dictionary) {
                int len = s.length();
                if (map.containsKey(len)) {
                    map.get(len).add(s);
                } else {
                    List<String> temp = new ArrayList<>();
                    temp.add(s);
                    map.put(len, temp);
                }
            }

        }

        public boolean search(String searchWord) {
            int len = searchWord.length();
            if (!map.containsKey(len)) {
                return false;
            }
            for (String s : map.get(len)) {
                if (isSub(s, searchWord)) {
                    return true;
                }
            }
            return false;

        }

        private boolean isSub(String a, String b) {
            int len = a.length();
            int sum = 0;
            for (int i = 0; i < len; i++) {
                if (a.charAt(i) != b.charAt(i)) {
                    sum++;
                }
                if (sum > 1) {
                    return false;
                }
            }
            return sum==1;
        }

    }
}
