package leetcode_everyday._2021.Jul;

import java.util.*;

/**
 * @author WuChao
 * @create 2021/7/18 8:31
 */
public class _18 {
    /**
     * 每日一题：2021/7/18
     * 面试题 10.02. 变位词组
     * 难度: medium
     * <p>
     * 编写一种方法，对字符串数组进行排序，将所有变位词组合在一起。变位词是指字母相同，但排列不同的字符串。
     *
     * 注意：本题相对原题稍作修改
     *
     * 示例:
     *
     * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
     * 输出:
     * [
     *   ["ate","eat","tea"],
     *   ["nat","tan"],
     *   ["bat"]
     * ]
     * 说明：
     *
     * 所有输入均为小写字母。
     * 不考虑答案输出的顺序。
     *
     */

    /*
    思路：哈希表 + 字符串排序

     */

    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if (strs.length==0) {
            return res;
        }
        Map<String, List<String>> store = new HashMap<>();
        for (String s : strs) {
            String t = sortStr(s);
            if (store.containsKey(t)) {
                List<String> strings = store.get(t);
                strings.add(s);
                store.put(t, strings);
            } else {
                store.put(t, new ArrayList<String>() {{
                    add(s);
                }});
            }
        }
        for (List<String> list : store.values()) {
            res.add(list);
        }
        return res;

    }

    public String sortStr(String str) {
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}
