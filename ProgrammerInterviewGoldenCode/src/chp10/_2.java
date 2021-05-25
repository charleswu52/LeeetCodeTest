package chp10;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author WuChao
 * @since 2021/5/23 上午10:30
 */
public class _2 {
    /**
     * 程序员面试金典(version 6) - 面试题 10.02. 变位词组
     * 难度: medium
     * <p>
     * 编写一种方法，对字符串数组进行排序，将所有变位词组合在一起。变位词是指字母相同，但排列不同的字符串。
     * <p>
     * 注意：本题相对原题稍作修改
     *
     * <p>
     * 示例:
     * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
     * 输出:
     * [
     * ["ate","eat","tea"],
     * ["nat","tan"],
     * ["bat"]
     * ]
     * <p>
     * 输出: [1,2,2,3,5,6]
     *
     *
     * <p>
     * 数据范围：
     * 所有输入均为小写字母。
     * 不考虑答案输出的顺序。
     */

    /*

     */
    public List<List<String>> groupAnagrams2(String[] strs) {
        HashMap<String, List<Integer>> store = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            char[] temp = strs[i].toCharArray();
            Arrays.sort(temp);
            String s = new String(temp);
            if (store.containsKey(s)) {
                store.get(s).add(i);

            } else {
                int finalI = i;
                store.put(s, new ArrayList<Integer>() {{
                    add(finalI);
                }});
            }
        }
        List<List<String>> res = new ArrayList<>();
        for (List<Integer> list : store.values()) {
            List<String> strings = new ArrayList<>();
            for (int i : list) {
                strings.add(strs[i]);
            }
            res.add(new ArrayList<>(strings));

        }

        return res;
    }

    /*

     */
    public List<List<String>> groupAnagrams(String[] strs) {
        return null;
    }


        @Test
    public void test() {

        String s = "sdfgyadew";
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        System.out.println(chars);
    }
}
