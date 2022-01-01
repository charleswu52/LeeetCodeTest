package leetcode_everyday._2021.Nov;

import java.util.HashSet;
import java.util.Set;

/**
 * @author WuChao
 * @create 2021/11/23 下午8:40
 */
public class _23_859 {
    /**
     * 每日一题：2021/11/23
     * <p>
     * 859. 亲密字符串
     * <p>
     * 难度：easy
     * <p>
     * 给你两个字符串 s 和 goal ，只要我们可以通过交换 s 中的两个字母得到与 goal 相等的结果，就返回 true ；否则返回 false 。
     * <p>
     * 交换字母的定义是：取两个下标 i 和 j （下标从 0 开始）且满足 i != j ，接着交换 s[i] 和 s[j] 处的字符。
     * <p>
     * 例如，在 "abcd" 中交换下标 0 和下标 2 的元素可以生成 "cbad" 。
     *
     * <p>
     * 示例 1：
     * <p>
     * 输入：s = "ab", goal = "ba"
     * 输出：true
     * 解释：你可以交换 s[0] = 'a' 和 s[1] = 'b' 生成 "ba"，此时 s 和 goal 相等。
     *
     * <p>
     * 示例 2：
     * <p>
     * 输入：s = "ab", goal = "ab"
     * 输出：false
     * 解释：你只能交换 s[0] = 'a' 和 s[1] = 'b' 生成 "ba"，此时 s 和 goal 不相等。
     * <p>
     * 范围
     * <p>
     * 1 <= s.length, goal.length <= 2 * 104
     * s 和 goal 由小写英文字母组成
     */

    public boolean buddyStrings(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        }
        if (s.equals(goal)) {
            Set<Character> set = new HashSet<>();
            for (char c : s.toCharArray()) {
                if (!set.add(c)) {
                    return true;
                }
            }
            return false;
        }
        int len = s.length();
        int count = 0;
        int a = -1, b = -1;
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) != goal.charAt(i)) {
                count++;
                if (count > 2) {
                    return false;
                }
                if (a == -1) {
                    a = i;
                } else if (b == -1) {
                    b = i;
                }
            }
        }
        if (count != 2) {
            return false;
        }
        return s.charAt(a) == goal.charAt(b) && s.charAt(b) == goal.charAt(a);

    }
}
