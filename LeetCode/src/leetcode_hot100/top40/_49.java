package leetcode_hot100.top40;

import java.util.*;

/**
 * @author WuChao
 * @create 2021/7/10 8:58
 */
public class _49 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 49. 字母异位词分组
     * 难度：medium
     * <p>
     * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
     *
     * <p>
     * 示例
     * <p>
     * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
     * 输出:
     * [
     *   ["ate","eat","tea"],
     *   ["nat","tan"],
     *   ["bat"]
     * ]
     * <p>
     * 范围
     * 所有输入均为小写字母。
     * 不考虑答案输出的顺序。
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if (strs.length < 1) {
            return res;
        }
        Map<String, List<String>> stringListMap = new HashMap<>();
        for (String str : strs) {
            String key = getOrderStr(str);
            if (stringListMap.containsKey(key)) {
                List<String> strings = stringListMap.get(key);
                strings.add(str);
                stringListMap.put(key, strings);
            } else {
                stringListMap.put(key, new ArrayList<String>() {{
                    add(str);
                }});
            }
        }
        for (List<String> strings : stringListMap.values()) {
            res.add(strings);
        }
        return res;

    }

    public String getOrderStr(String orign) {
        char[] chars = orign.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}
