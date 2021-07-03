package leetcode_everyday.Feb;

public class _5 {
    /**
     * 每日一题：2021/2/5
     * 1208. 尽可能使字符串相等
     * 难度: medium
     * 给你两个长度相同的字符串，s 和 t。
     * 将 s 中的第 i 个字符变到 t 中的第 i 个字符需要 |s[i] - t[i]| 的开销（开销可能为 0），也就是两个字符的 ASCII 码值的差的绝对值。
     * 用于变更字符串的最大预算是 maxCost。在转化字符串时，总开销应当小于等于该预算，这也意味着字符串的转化可能是不完全的。
     * 如果你可以将 s 的子字符串转化为它在 t 中对应的子字符串，则返回可以转化的最大长度。
     * 如果 s 中没有子字符串可以转化成 t 中对应的子字符串，则返回 0。
     * <p>
     * 示例：
     * 输入：s = "abcd", t = "bcdf", cost = 3
     * 输出：3
     * 解释：s 中的 "abc" 可以变为 "bcd"。开销为 3，所以最大长度为 3。
     *
     * <p>
     * 数据范围：
     * 1 <= s.length, t.length <= 10^5
     * 0 <= maxCost <= 10^6
     * s 和 t 都只含小写英文字母。
     */

    public int equalSubstring(String s, String t, int maxCost) {
        int res = 0;
        int len = s.length();
        int[] store = new int[len];
        for (int i = 0; i < len; i++) {
            store[i]   = Math.abs(s.charAt(i) - t.charAt(i));
        }
//        System.out.println(Arrays.toString(store));
        // 双指针的方法
        int start = 0, end = 0;
        int cost =0;
        while (end < len) {
            cost += store[end];
            while (cost > maxCost) {
                cost -= store[start++];
            }
            res = Math.max(res, end - start + 1);
            end++;
        }
        return res;
    }




    public void _21_2_5() {
        String s = "krrgw", t = "zjxss";
        int cost = 19;
        System.out.println(equalSubstring(s, t, cost));

    }
}
