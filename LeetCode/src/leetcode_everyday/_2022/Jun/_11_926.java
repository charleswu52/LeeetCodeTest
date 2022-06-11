package leetcode_everyday._2022.Jun;

/**
 * @Author CharlesWu
 * @Create 2022/6/11 10:50
 * @Version 1.0
 * @Description
 * @Note
 */
public class _11_926 {
    /**
     * 每日一题：2022/6/11
     * 926. 将字符串翻转到单调递增
     * 难度: medium
     * <p>
     * 如果一个二进制字符串，是以一些 0（可能没有 0）后面跟着一些 1（也可能没有 1）的形式组成的，那么该字符串是 单调递增 的。
     * <p>
     * 给你一个二进制字符串 s，你可以将任何 0 翻转为 1 或者将 1 翻转为 0 。
     * <p>
     * 返回使 s 单调递增的最小翻转次数。
     * <p>
     * 示例:
     * <p>
     * 输入：s = "00110"
     * <p>
     * 输出：1
     * <p>
     * 解释：翻转最后一位得到 00111.
     * <p>
     * 数据范围：
     * <p>
     * 1 <= s.length <= 105
     * s[i] 为 '0' 或 '1'
     */

    /*
   思路：一次遍历
   从第一个1开始0的数目和1的数目赛跑，每次0的数目超过1的数目翻转前面的所有1，再找到下一个1从新计数，以此类推。
   最后0的数目不超过1的数目翻转后面剩下的0。
     */
    public int minFlipsMonoIncr(String s) {
        int res = 0, n = s.length();
        int num0 = 0, num1 = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                num1++;
            } else if (num1 != 0) {
                num0++;
            }
            if (num0 > num1) {
                res += num1;
                num0 = 0;
                num1 = 0;
            }
        }
        res += num0;
        return res;

    }
}
