package leetcode_hot100.top20;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author WuChao
 * @create 2021/7/4 13:24
 */
public class _17 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 17. 电话号码的字母组合
     * 难度：medium
     * <p>
     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
     * <p>
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     * <p>
     * 示例
     * <p>
     * 输入：nums = [-1,0,1,2,-1,-4]
     * 输出：[[-1,-1,2],[-1,0,1]]
     * <p>
     * 输入：nums = []
     * 输出：[]
     * <p>
     * 范围
     * 0 <= digits.length <= 4
     * digits[i] 是范围 ['2', '9'] 的一个数字。
     */

    /*
    思路 ： 哈希 + 回溯
     */
    Map<Integer, List<String>> store;
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits.length() < 1) {
            return res;
        }
        init();
        for (char ch : digits.toCharArray()) {
            int cur = ch - '0';
            if (res.size() == 0) {
                res.addAll(store.get(cur));
            } else {
                List<String> strings = store.get(cur);
                List<String> temp = new ArrayList<>(res);
                res.clear();
                for (int i = 0; i < temp.size(); i++) {
                    String a = temp.get(i);
                    for (String b : strings) {
                        res.add(a + b);
                    }
                }
            }
        }
        return res;


    }

    public void  init() {
        store = new HashMap<>();
        store.put(2,new ArrayList<String>(){{
            add("a");
            add("b");
            add("c");
        }});
        store.put(3,new ArrayList<String>(){{
            add("d");
            add("e");
            add("f");
        }});
        store.put(4,new ArrayList<String>(){{
            add("g");
            add("h");
            add("i");
        }});store.put(5,new ArrayList<String>(){{
            add("j");
            add("k");
            add("l");
        }});store.put(6,new ArrayList<String>(){{
            add("m");
            add("n");
            add("o");
        }});store.put(7,new ArrayList<String>(){{
            add("p");
            add("q");
            add("r");
            add("s");
        }});store.put(8,new ArrayList<String>(){{
            add("t");
            add("u");
            add("v");
        }});store.put(9,new ArrayList<String>(){{
            add("w");
            add("x");
            add("y");
            add("z");
        }});
    }

    @Test
    public void test() {
        String digits = "223";
        System.out.println(letterCombinations(digits));
    }
}
