package leetcode_everyday._2022.Jan;

import leetcode_everyday._2021.Jan._15;

/**
 * @author WuChao
 * @create 2022/1/10 21:34
 */
public class _10_306 {
    /**
     * 每日一题：2022/1/10
     * <p>
     * 306. 累加数
     * <p>
     * 难度：medium
     * <p>
     * 累加数 是一个字符串，组成它的数字可以形成累加序列。
     * <p>
     * 一个有效的 累加序列 必须 至少 包含 3 个数。除了最开始的两个数以外，字符串中的其他数都等于它之前两个数相加的和。
     * <p>
     * 给你一个只包含数字 '0'-'9' 的字符串，编写一个算法来判断给定输入是否是 累加数 。如果是，返回 true ；否则，返回 false 。
     * <p>
     * 说明：累加序列里的数 不会 以 0 开头，所以不会出现 1, 2, 03 或者 1, 02, 3 的情况。
     *
     * <p>
     * 示例 1：
     * <p>
     * 输入："112358"
     * <p>
     * 输出：true
     * <p>
     * 解释：累加序列为: 1, 1, 2, 3, 5, 8 。1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
     * <p>
     * 输入："199100199"
     * <p>
     * 输出：true
     * <p>
     * 解释：累加序列为: 1, 99, 100, 199。1 + 99 = 100, 99 + 100 = 199
     * <p>
     * 范围
     * <p>
     * 1 <= num.length <= 35
     * num 仅由数字（0 - 9）组成
     **/

    /*
    思路：穷巨额累加序列的第一个数字和第二个数字的所有可能性思路及解法
    记字符串 num 的长度为 n，序列最新确定的两个数中，位于前面的数字为 first，first 的最高位在 num 中的下标为 firstStart，
    first 的最低位在 num 中的下标为 firstEnd。记序列最新确定的两个数中，位于后面的数字为 second，second 的最高位在 num 中的下标为
    secondStart，second 的最低位在 num 中的下标为 secondEnd。在穷举第一个数字和第二个数字的过程中，容易得到以下两个结论：
        firstStart=0，firstEnd+1=secondStart。因此，我们只需要用两个循环来遍历 secondStart 和 secondEnd 所有可能性的组合即可。

    在判断累加序列的合法性时，用字符串的加法来算出 first 与 second 之和 third。将 third 与 num 接下来紧邻的相同长度的字符串进行比较。
    当 third 过长或者与接下来的字符串不相同时，则说明这不是一个合法的累加序列。当相同时，则我们为这个序列新确定了一个数字。
    如果 third 刚好抵达 num 的末尾时，则说明这是一个合法的序列。当 num 还有多余的字符时，则需要更新 firstStart，firstEnd，
    secondStart，secondEnd， 继续进行合法性的判断。

    当输入规模较小时，这题可以直接使用整形或者长整型的数字的相加。而我们这里使用了字符串的加法，因此也能处理溢出的过大的整数输入。

    仍需要注意的是，当某个数字长度大于等于 2 时，这个数字不能以 0 开头，这部分的判断可以在两层循环体的开头完成。

     */
    public boolean isAdditiveNumber(String num) {
        int n = num.length();
        for (int secondStart = 1; secondStart < n - 1; secondStart++) {
            if (num.charAt(0) == '0' && secondStart != 1) {
                break;
            }
            for (int secondEnd = secondStart; secondEnd < n - 1; secondEnd++) {
                if (num.charAt(secondStart) == '0' && secondStart != secondEnd) {
                    break;
                }
                if (check(secondStart, secondEnd, num)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean check(int secondStart, int secondEnd, String num) {
        int n = num.length();
        int firstStart = 0, firstEnd = secondStart - 1;
        while (secondEnd <= n - 1) {
            String third = stringAdd(num, firstStart, firstEnd, secondStart, secondEnd);
            int thirdStart = secondEnd + 1;
            int thirdEnd = secondEnd + third.length();
            if (thirdEnd >= n || !num.substring(thirdStart, thirdEnd + 1).equals(third)) {
                break;
            }
            if (thirdEnd == n - 1) {
                return true;
            }
            firstStart = secondStart;
            firstEnd = secondEnd;
            secondStart = thirdStart;
            secondEnd = thirdEnd;
        }
        return false;
    }

    public String stringAdd(String s, int firstStart, int firstEnd, int secondStart, int secondEnd) {
        StringBuilder third = new StringBuilder();
        int carry = 0, cur = 0;
        while (firstEnd >= firstStart || secondEnd >= secondStart||carry!=0) {
            cur = carry;
            if (firstEnd >= firstStart) {
                cur += s.charAt(firstEnd) - '0';
                --firstEnd;
            }
            if (secondEnd >= secondStart) {
                cur += s.charAt(secondEnd) - '0';
                --secondEnd;
            }
            carry = cur / 10;
            cur = cur % 10;
            third.append((char) (cur + '0'));
        }
        return third.reverse().toString();
    }
}
