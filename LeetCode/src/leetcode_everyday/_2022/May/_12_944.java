package leetcode_everyday._2022.May;

/**
 * @author WuChao
 * @create 2022/5/12 12:48
 */
public class _12_944 {
    /**
     * 每日一题：2022/5/12
     * <p>
     * 944. 删列造序
     * <p>
     * 难度：easy
     * <p>
     * 给你由 n 个小写字母字符串组成的数组 strs，其中每个字符串长度相等。
     * <p>
     * 这些字符串可以每个一行，排成一个网格。例如，strs = ["abc", "bce", "cae"] 可以排列为：
     * <p>
     * abc
     * bce
     * cae
     * 你需要找出并删除 不是按字典序升序排列的 列。在上面的例子（下标从 0 开始）中，列 0（'a', 'b', 'c'）和列 2（'c', 'e', 'e'）都是按升序排列的，而列 1（'b', 'c', 'a'）不是，所以要删除列 1 。
     * <p>
     * 返回你需要删除的列数。
     *
     * <p>
     * 示例
     * <p>
     * 输入：strs = ["zyx","wvu","tsr"]
     * <p>
     * 输出：3
     * <p>
     * 解释：网格示意如下：
     * zyx
     * wvu
     * tsr
     * 所有 3 列都是非升序排列的，所以都要删除。
     * <p>
     * 范围
     * <p>
     * 提示：
     * n == strs.length
     * 1 <= n <= 100
     * 1 <= strs[i].length <= 1000
     * strs[i] 由小写英文字母组成
     */

    /*
    思路：简单字符串模拟
     */
    public int minDeletionSize(String[] strs) {
        int n = strs.length;
        if (n < 2) {
            return 0;
        }
        int len = strs[0].length();
        int res = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < n-1; j++) {
                if (strs[j].charAt(i) > strs[j + 1].charAt(i)) {
                    res++;
                    break;
                }
            }
        }
        return res;


    }
}
