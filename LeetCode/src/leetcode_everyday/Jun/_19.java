package leetcode_everyday.Jun;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author WuChao
 * @create 2021/6/19 10:25
 */
public class _19 {
    /**
     * 每日一题：2021/6/19
     * 1239. 串联字符串的最大长度
     * 难度: medium
     * <p>
     * 给定一个字符串数组 arr，字符串 s 是将 arr 某一子序列字符串连接所得的字符串，如果 s 中的每一个字符都只出现过一次，那么它就是一个可行解。
     * <p>
     * 请返回所有可行解 s 中最长长度。
     *
     * <p>
     * 示例:
     * 输入：arr = ["un","iq","ue"]
     * 输出：4
     * 解释：所有可能的串联组合是 "","un","iq","ue","uniq" 和 "ique"，最大长度为 4。
     * <p>
     * 输入：arr = ["cha","r","act","ers"]
     * 输出：6
     * 解释：可能的解答有 "chaers" 和 "acters"。
     * <p>
     * 数据范围：
     * 1 <= arr.length <= 16
     * 1 <= arr[i].length <= 26
     * arr[i] 中只含有小写英文字母
     */

    /*
    思路1：DFS 回溯

     */

    private int ans;    // 返回结果

    public int maxLength(List<String> arr) {
        dfs(arr, 0, new StringBuilder());

        return ans;
    }

    private void dfs(List<String> arr, int start, StringBuilder builder) {
        if (!match(builder)) {
            return;
        }
        ans = Math.max(ans, builder.length());
        int size = arr.size();
        for (int i = start; i < size; i++) {
            String s = arr.get(i);
            builder.append(s);
            dfs(arr, i + 1, builder);
            builder.delete(builder.length() - s.length(), builder.length()); // 撤销选择
        }
    }

    /**
     * 检查一个单词中是否有重复单词
     * @param builder
     * @return
     */
    public boolean match(StringBuilder builder) {
        String s = builder.toString();
        Set<Character> set = new HashSet<>();
        for (char ch : s.toCharArray()) {
            if (set.contains(ch)) {
                return false;
            } else {
                set.add(ch);
            }

        }
        return true;
    }


}
