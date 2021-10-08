package leetcode_everyday.Aug;

/**
 * @author WuChao
 * @create 2021/8/19 9:38
 */
public class _19_345 {
    /**
     * 每日一题：2021/8/19
     * 345. 反转字符串中的元音字母
     * 难度：easy
     * <p>
     * 编写一个函数，以字符串作为输入，反转该字符串中的元音字母。
     *
     * <p>
     * 示例 1：
     * 输入："hello"
     * 输出："holle"
     * <p>
     * 示例 2：
     * 输入："leetcode"
     * 输出："leotcede"
     * <p>
     * 注意:
     * 元音字母不包含字母 "y" 。
     */

    /*
    思路1：双指针
    两个指针都向中间遍历，遇到都是元音字母的就交换
     */
    public String reverseVowels(String s) {
        if (s.length() < 1) {
            return s;
        }
        char[] chars = s.toCharArray();
        int left = 0, right = chars.length - 1;
        while (left <= right) {
            boolean a = check(chars[left]);
            boolean b = check(chars[right]);
            if (a && b) {
                swap(chars, left, right);
                left++;
                right--;
            }
            if (!b) {
                right--;
            }
            if (!a) {
                left++;
            }
        }
        return new String(chars);

    }

    public boolean check(char ch) {
        if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u'
                || ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U') {
            return true;
        }
        return false;
    }

    public void swap(char[] arry, int x, int y) {
        char tmp = arry[x];
        arry[x] = arry[y];
        arry[y] = tmp;
    }


}
