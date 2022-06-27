package leetcode_everyday._2022.Jun;

/**
 * @author WuChao
 * @create 2022/6/27 9:49
 */
public class _27_522 {

    /**
     * 每日一题：2022/6/27
     * <p>
     * 522. 最长特殊序列 II
     * <p>
     * 难度：medium
     * <p>
     * 给定字符串列表 strs ，返回其中 最长的特殊序列 。如果最长特殊序列不存在，返回 -1 。
     *
     * 特殊序列 定义如下：该序列为某字符串 独有的子序列（即不能是其他字符串的子序列）。
     *
     *  s 的 子序列可以通过删去字符串 s 中的某些字符实现。
     *
     * 例如，"abc" 是 "aebdc" 的子序列，因为您可以删除"aebdc"中的下划线字符来得到 "abc"。
     * "aebdc"的子序列还包括"aebdc"、 "aeb" 和 "" (空字符串)。
     * <p>
     * 示例
     * <p>
     * 输入: strs = ["aba","cdc","eae"]
     *
     * 输出: 3
     * <p>
     * 范围
     * <p>
     * 2 <= strs.length <= 50
     * 1 <= strs[i].length <= 10
     * strs[i] 只包含小写英文字母
     */

    /*
    题目解释给出一个字符串数组，在里面找出字符串满足当前字符串不是字符串数组中其他字符串的子序列，返回满足条件的字符串中 最长的字符串的长度
    思路：暴力判断
     */
    public int findLUSlength(String[] strs) {
        int res = -1;
        int n = strs.length;
        boolean flag = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && isSubStr(strs[i], strs[j])) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                res = Math.max(res, strs[i].length());
            }
            flag = true;
        }
        return res;



    }

    private boolean isSubStr(String a, String b) {
        int idx1 = 0, idx2 = 0;
        while (idx1 < a.length() && idx2 < b.length()) {
            if (a.charAt(idx1) == b.charAt(idx2)) {
                idx1++;
            }
            idx2++;
        }
        return idx1 == a.length();
    }

}
