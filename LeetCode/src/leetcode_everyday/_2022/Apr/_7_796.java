package leetcode_everyday._2022.Apr;

/**
 * @author WuChao
 * @create 2022/4/9 10:43
 */
public class _7_796 {
    /**
     * 每日一题：2022/4/7
     * <p>
     * 796. 旋转字符串
     * <p>
     * 难度：easy
     * <p>
     * 给定两个字符串, s 和 goal。如果在若干次旋转操作之后，s 能变成 goal ，那么返回 true 。
     * <p>
     * s 的 旋转操作 就是将 s 最左边的字符移动到最右边。
     * <p>
     * 例如, 若 s = 'abcde'，在旋转一次之后结果就是'bcdea' 。
     * <p>
     * 示例
     * <p>
     * 输入: s = "abcde", goal = "cdeab"
     * <p>
     * 输出: true
     * <p>
     * 范围
     * <p>
     * 1 <= s.length, goal.length <= 100
     * s 和 goal 由小写英文字母组成
     */

    /*
    思路：简单字符串
     */
    public boolean rotateString(String s, String goal) {
        return s.length() == goal.length() && (s + s).contains(goal);

    }
}
