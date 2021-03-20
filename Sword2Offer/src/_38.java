import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * @author WuChao
 * @since 2021/3/20 上午9:38
 */
public class _38 {
    /**
     * 剑指 Offer 38. 字符串的排列
     * 难度: medium
     * <p>
     * 输入一个字符串，打印出该字符串中字符的所有排列。
     * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
     *
     * <p>
     * 示例：
     * 输入：s = "abc"
     * 输出：["abc","acb","bac","bca","cab","cba"]
     *
     * <p>
     * 数据范围：
     * 1 <= s 的长度 <= 8
     */

//    List<List<Character>> res;
//    public String[] permutation(String s) {
//        res = new LinkedList<>();
//        char[] chars = s.toCharArray();
//        LinkedList<Character> path = new LinkedList<>();
//        backtrack(path, chars);
//        String[] ans = new String[res.size()];
//        int i = 0;
//        for (List<Character> characters : res) {
//            StringBuilder sb = new StringBuilder();
//            for (int j = 0; j < characters.size();j++) {
//                sb.append(characters.get(j));
//            }
//            ans[i++] = sb.toString();
//        }
//        return ans;
//
//
//    }
//
//    public void backtrack(LinkedList<Character> path, char[] chars) {
//        // 触发结束条件
//        if (path.size() == chars.length) {
//            res.add(new LinkedList(path));
//            return;
//        }
//        for (int i = 0; i < chars.length; i++) {
//            // 排除不合法的选择
//            if (path.contains(chars[i])) {
//                continue;
//            }
//            // 做选择
//            path.add(chars[i]);
//            // 进入下一层决策
//            backtrack(path, chars);
//            // 撤销选择
//            path.removeLast();
//        }
//
//    }


    List<String> res = new LinkedList<>();
    char[] c;

    public String[] permutation(String s) {
        c = s.toCharArray();
        dfs(0);
        return res.toArray(new String[res.size()]);
    }

    void dfs(int x) {
        if (x == c.length - 1) {
            res.add(String.valueOf(c));      // 添加排列方案
            return;
        }
        HashSet<Character> set = new HashSet<>();
        for (int i = x; i < c.length; i++) {
            if (set.contains(c[i])) continue; // 重复，因此剪枝
            set.add(c[i]);
            swap(i, x);                      // 交换，将 c[i] 固定在第 x 位
            dfs(x + 1);                      // 开启固定第 x + 1 位字符
            swap(i, x);                      // 恢复交换
        }
    }

    void swap(int a, int b) {
        char tmp = c[a];
        c[a] = c[b];
        c[b] = tmp;
    }


    @Test
    public void test() {
        String s = "aab";
        System.out.println(Arrays.toString(permutation(s)));

    }
}
