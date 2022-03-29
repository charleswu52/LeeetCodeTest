package leetcode_everyday._2022.Mar;

/**
 * @author WuChao
 * @create 2022/3/29 9:11
 */
public class _29_2024 {
    /**
     * 每日一题：2022/3/29
     * <p>
     * 2024. 考试的最大困扰度
     * <p>
     * 难度：medium
     * <p>
     * 一位老师正在出一场由 n 道判断题构成的考试，每道题的答案为 true （用 'T' 表示）或者 false （用 'F' 表示）。
     * 老师想增加学生对自己做出答案的不确定性，方法是 最大化 有 连续相同 结果的题数。（也就是连续出现 true 或者连续出现 false）。
     * <p>
     * 给你一个字符串 answerKey ，其中 answerKey[i] 是第 i 个问题的正确结果。除此以外，还给你一个整数 k ，表示你能进行以下操作的最多次数：
     * <p>
     * 每次操作中，将问题的正确答案改为 'T' 或者 'F' （也就是将 answerKey[i] 改为 'T' 或者 'F' ）。
     * 请你返回在不超过 k 次操作的情况下，最大 连续 'T' 或者 'F' 的数目。
     * <p>
     * 示例1
     * <p>
     * 输入：answerKey = "TTFTTFTT", k = 1
     * <p>
     * 输出：5
     * <p>
     * 解释：我们可以将第一个 'F' 换成 'T' ，得到 answerKey = "TTTTTFTT" 。
     * 或者我们可以将第二个 'F' 换成 'T' ，得到 answerKey = "TTFTTTTT" 。
     * 两种情况下，都有五个连续的 'T' 。
     * <p>
     * 范围
     * <p>
     * n == answerKey.length
     * 1 <= n <= 5 * 104
     * answerKey[i] 要么是 'T' ，要么是 'F'
     * 1 <= k <= n
     */

    /*
    思路：滑动窗口
    题目等价于求一个包含'F'或者'T'的个数不超过可修改次数k的最大窗口长度
     */
    String s;
    int n, edit;
    public int maxConsecutiveAnswers(String answerKey, int k) {
        s = answerKey;
        n = answerKey.length();
        edit = k;
        return Math.max(getChar('T'),getChar('F'));

    }

    int getChar(char ch) {
        int ans = 0;
        for (int i = 0, j = 0, cnt = 0; i < n; i++) {
            if (s.charAt(i) == ch) {
                cnt++;
            }
            while (cnt > edit) {
                if (s.charAt(j) == ch) {
                    cnt--;
                }
                j++;
            }
            ans = Math.max(ans, i - j + 1);
        }
        return ans;
    }
}
