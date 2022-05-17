package leetcode_everyday._2022.May;

/**
 * @author WuChao
 * @create 2022/5/17 9:23
 */
public class _17_953 {
    /**
     * 每日一题：2022/5/17
     * <p>
     * 953. 验证外星语词典
     * <p>
     * 难度：easy
     * <p>
     * 某种外星语也使用英文小写字母，但可能顺序 order 不同。字母表的顺序（order）是一些小写字母的排列。
     * <p>
     * 给定一组用外星语书写的单词 words，以及其字母表的顺序 order，只有当给定的单词在这种外星语中按字典序排列时，返回 true；否则，返回 false。
     * <p>
     * 示例
     * <p>
     * 输入：words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
     * <p>
     * 输出：true
     * <p>
     * 解释：在该语言的字母表中，'h' 位于 'l' 之前，所以单词序列是按字典序排列的。
     * <p>
     * 范围
     * <p>
     * 1 <= words.length <= 100
     * 1 <= words[i].length <= 20
     * order.length == 26
     * 在 words[i] 和 order 中的所有字符都是英文小写字母。
     */

    /*
    思路：简单字符串模拟
     */
    public boolean isAlienSorted(String[] words, String order) {
        int[] orders = new int[26];
        for (int i = 0; i < 26; i++) {
            orders[order.charAt(i) - 'a'] = i;
        }
        int len = words.length;
        if (len == 1) {
            return true;
        }
        for (int i = 0; i < len - 1; i++) {
            String a = words[i];
            String b = words[i + 1];
            int lena = a.length(), lenb = b.length();
            boolean flag = true;
            int j, size = Math.min(lena, lenb);
            for (j = 0; j < size; j++) {
                if (flag) {
                    if (orders[a.charAt(j) - 'a'] > orders[b.charAt(j) - 'a']) {
                        flag = false;
                        break;
                    } else if (orders[a.charAt(j) - 'a'] < orders[b.charAt(j) - 'a']) {
                        flag = true;
                        break;
                    } else {
                        continue;
                    }
                } else {
                    break;
                }
            }
            if (flag && j == size && lena > lenb) {
                flag = false;
            }
            if (!flag) {
                return false;
            }
        }
        return true;
    }

}
