package leetcodetest.Jun;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * @author WuChao
 * @create 2021/6/22 22:03
 */
public class _22 {
    /**
     * 每日一题：2021/6/22
     * 剑指 Offer 38. 字符串的排列
     * 难度: medium
     * <p>
     * 输入一个字符串，打印出该字符串中字符的所有排列。
     * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
     * <p>
     *
     * <p>
     * 示例:输入：s = "abc"
     * 输出：["abc","acb","bac","bca","cab","cba"]
     * <p>
     * 数据范围：
     * 1 <= s 的长度 <= 8
     */

    List<String> res = new LinkedList<>();
    char[] chars;
    public String[] permutation(String s) {
        this.chars = s.toCharArray();
        dfs(0);
        return res.toArray(new String[res.size()]);

    }

    public void dfs(int x) {
        if (x == chars.length - 1) {
            res.add(String.valueOf(chars));
            return;
        }
        HashSet<Character> set = new HashSet<>();
        for (int i = x; i < chars.length; i++) {
            if (set.contains(chars[i])) {
                continue;
            }
            set.add(chars[i]);
            swap(i, x);
            dfs(x + 1);
            swap(i, x);

        }



    }

    public void swap(int a, int b) {
        char temp = chars[a];
        chars[a] = chars[b];
        chars[b] = temp;
    }
}
