package leetcode_everyday.Dec;

import java.util.HashSet;
import java.util.Set;

/**
 * @author WuChao
 * @create 2021/12/23 上午9:15
 */
public class _23_1044 {
    /**
     * 每日一题：2021/12/23
     * <p>
     * 1044. 最长重复子串
     * <p>
     * 难度：hard
     * <p>
     * 给你一个字符串 s ，考虑其所有 重复子串 ：即，s 的连续子串，在 s 中出现 2 次或更多次。这些出现之间可能存在重叠。
     * <p>
     * 返回 任意一个 可能具有最长长度的重复子串。如果 s 不含重复子串，那么答案为 "" 。
     * <p>
     * 示例 1：
     * <p>
     * 输入：s = "banana"
     * <p>
     * 输出："ana"
     *
     * <p>
     * 示例 2：
     * <p>
     * 输入：s = "abcd"
     * <p>
     * 输出：""
     * <p>
     * 范围
     * <p>
     * 2 <= s.length <= 3 * 10^4
     * s 由小写英文字母组成
     **/
    long[] h, p;
    public String longestDupSubstring(String s) {
        int P = 1313131, n = s.length();
        h = new long[n + 10];
        p = new long[n + 10];
        p[0] = 1;
        for (int i = 0; i < n; i++) {
            p[i + 1] = p[i] * P;
            h[i + 1] = h[i] * P + s.charAt(i);
        }
        String ans = "";
        int l = 0, r = n;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            String t = check(s, mid);
            if (t.length() != 0) {
                l = mid;
            } else {
                r = mid - 1;
            }
            ans = t.length() > ans.length() ? t : ans;
        }
        return ans;

    }

    String check(String s, int len) {
        int n = s.length();
        Set<Long> set = new HashSet<>();
        for (int i = 1; i + len - 1 <= n; i++) {
            int j = i + len - 1;
            long cur = h[j] - h[i - 1] * p[j - i + 1];
            if (set.contains(cur)) {
                return s.substring(i - 1, j);
            }
            set.add(cur);
        }
        return "";
    }
}
