package leetcode_everyday._2022.Feb;

/**
 * @author WuChao
 * @create 2022/2/13 10:12
 */
public class _13_1189 {
    /**
     * 每日一题：2022/2/13
     * <p>
     * 1189. “气球” 的最大数量
     * <p>
     * 难度：easy
     * <p>
     * 给你一个字符串 text，你需要使用 text 中的字母来拼凑尽可能多的单词 "balloon"（气球）。
     * <p>
     * 字符串 text 中的每个字母最多只能被使用一次。请你返回最多可以拼凑出多少个单词 "balloon"。
     * <p>
     * 示例
     * <p>
     * 输入：text = "loonbalxballpoon"
     * <p>
     * 输出：2
     * <p>
     * 范围
     * <p>
     * 1 <= text.length <= 10^4
     * text 全部由小写英文字母组成
     */

    /*
    思路：普通字符串模拟
     */
    public int maxNumberOfBalloons(String text) {
        int res;
        int[] count = new int[5];
        for (char ch : text.toCharArray()) {
            switch (ch) {
                case 'a':
                    count[0]++;
                    break;
                case 'b':
                    count[1]++;
                    break;
                case 'n':
                    count[2]++;
                    break;
                case 'l':
                    count[3]++;
                    break;
                case 'o':
                    count[4]++;
                    break;
            }
        }
        int temp1 = Math.min(Math.min(count[0], count[1]), count[2]);
        int temp2 = Math.min(count[3], count[4]);
        res = Math.min(temp1, temp2 / 2);
        return res;

    }
}
