package leetcode_everyday._2021.Jul;

import java.util.*;

/**
 * @author WuChao
 * @create 2021/7/3 8:36
 */
public class _3 {
    /**
     * 每日一题：2021/7/3
     * 451. 根据字符出现频率排序
     * 难度: medium
     * <p>
     * 给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
     *
     * <p>
     * 输入:
     * "tree"
     *
     * 输出:
     * "eert"
     *
     * 解释:
     * 'e'出现两次，'r'和't'都只出现一次。
     * 因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
     * <p>
     * 限制：
     * <p>
     */

    /*
    思路： 哈希表 + 排序
    把 String 中出现的每个 字符以及对应的出现次数放到map中，
     */
    public String frequencySort(String s) {
        if (s.length() < 2) {
            return s;
        }
        Map<Character, Integer> store = new HashMap<>();
        for (Character ch : s.toCharArray()) {
            store.put(ch, store.getOrDefault(ch, 0) + 1);
        }
        List<Map.Entry<Character, Integer>> list = new ArrayList<>(store.entrySet());
        Collections.sort(list, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        StringBuilder sb = new StringBuilder();
        for (Map.Entry entry : list) {
            Integer value = (Integer) entry.getValue();
            Character key = (Character) entry.getKey();
            for (int i = 0; i < value; i++) {
                sb.append(key);
            }
        }
        return sb.toString();


    }
}
