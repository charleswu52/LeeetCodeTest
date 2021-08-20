package leetcode_everyday.Oct;

/**
 * @author WuChao
 * @create 2021/8/20 15:33
 */
public class _20_541 {
    /**
     * 每日一题：2021/8/20
     * 345. 反转字符串中的元音字母
     * 难度：easy
     * <p>
     * 给定一个字符串 s 和一个整数 k，从字符串开头算起，每 2k 个字符反转前 k 个字符。
     *
     * 如果剩余字符少于 k 个，则将剩余字符全部反转。
     * 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
     *
     * <p>
     * 示例 1：
     * 输入：s = "abcdefg", k = 2
     * 输出："bacdfeg"
     *
     * 示例 2：
     * 输入：s = "abcd", k = 2
     * 输出："bacd"
     * <p>
     * 示例 2：
     * 输入："leetcode"
     * 输出："leotcede"
     * <p>
     * 注意:
     * 1 <= s.length <= 104
     * s 仅由小写英文组成
     * 1 <= k <= 104
     */

    /*
    思路：模拟
     */
    public String reverseStr(String s, int k) {
        int n = s.length();
        char[] arr = s.toCharArray();
        for (int i = 0; i < n; i += 2 * k) {
            reverse(arr, i, Math.min(i + k, n) - 1);
        }
        return new String(arr);
    }

    public void reverse(char[] arr, int left, int right) {
        while (left < right) {
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }


}
