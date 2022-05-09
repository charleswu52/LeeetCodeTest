package leetcode_everyday._2022.May;

/**
 * @author WuChao
 * @create 2022/5/9 8:47
 */
public class _9_942 {
    /**
     * 每日一题：2022/5/8
     * <p>
     * 942. 增减字符串匹配
     * <p>
     * 难度：easy
     * <p>
     * 由范围 [0,n] 内所有整数组成的 n + 1 个整数的排列序列可以表示为长度为 n 的字符串 s ，其中:
     * <p>
     * 如果 perm[i] < perm[i + 1] ，那么 s[i] == 'I'
     * 如果 perm[i] > perm[i + 1]，那么 s[i] == 'D'
     * 给定一个字符串 s ，重构排列 perm 并返回它。如果有多个有效排列perm，则返回其中 任何一个 。
     * <p>
     * 示例
     * <p>
     * 输入：s = "IDID"
     * <p>
     * 输出：[0,4,1,3,2]
     * <p>
     * 范围
     * <p>
     * 提示：
     * 1 <= s.length <= 105
     * s 只包含字符 "I" 或 "D"
     */

    public int[] diStringMatch(String s) {
        int n = s.length();
        int[] res = new int[n + 1];
        int left = 0, right = n, idx = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'I') {
                res[idx++] = left++;
            } else {
                res[idx++] = right--;
            }
        }
        res[idx] = left;
        return res;


    }
}
