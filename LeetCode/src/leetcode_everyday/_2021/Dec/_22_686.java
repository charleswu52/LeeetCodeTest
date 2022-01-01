package leetcode_everyday._2021.Dec;

/**
 * @author WuChao
 * @create 2021/12/22 上午9:36
 */
public class _22_686 {
    /**
     * 每日一题：2021/12/22
     * <p>
     * 686. 重复叠加字符串匹配
     * <p>
     * 难度：medium
     * <p>
     * 给定两个字符串 a 和 b，寻找重复叠加字符串 a 的最小次数，使得字符串 b 成为叠加后的字符串 a 的子串，如果不存在则返回 -1。
     *
     * 注意：字符串 "abc" 重复叠加 0 次是 ""，重复叠加 1 次是 "abc"，重复叠加 2 次是 "abcabc"。
     * <p>
     * 示例 1：
     * 输入：a = "abcd", b = "cdabcdab"
     *
     * 输出：3
     *
     * 解释：a 重复叠加三遍后为 "abcdabcdabcd", 此时 b 是其子串。
     * <p>
     * 解释: 仅在位置2上有一个供暖器。如果我们将加热半径设为1，那么所有房屋就都能得到供暖。
     * <p>
     * 示例 2：
     * <p>
     * 输入: houses = [1,2,3,4], heaters = [1,4]
     * <p>
     * 输出: 1
     * <p>
     * 解释: 在位置1, 4上有两个供暖器。我们需要将加热半径设为1，这样所有房屋就都能得到供暖。
     *
     * <p>
     * 范围
     * <p>
     * 1 <= houses.length, heaters.length <= 3 * 10^4
     * 1 <= houses[i], heaters[i] <= 10^9
     **/

    public int repeatedStringMatch(String a, String b) {
        if (a.equals(b)) {
            return 1;
        }
        int[] charsA = new int[26];
        for (char ch : a.toCharArray()) {
            charsA[ch - 'a']++;
        }
        for (char ch : b.toCharArray()) {
            if (charsA[ch - 'a'] == 0) {
                return -1;
            }
        }
        int res = 0;
        StringBuffer stringBuffer = new StringBuffer();
        while (stringBuffer.length()<b.length() && ++res>0) {
            stringBuffer.append(a);
        }
        stringBuffer.append(a);
        int idx = stringBuffer.indexOf(b);
        if (idx == -1) {
            return -1;
        }
        return idx + b.length() > a.length() * res ? res + 1 : res;

    }

}
