package leetcode_everyday.Oct;

import org.junit.Test;

/**
 * @author WuChao
 * @create 2021/10/21 9:10
 */
public class _21_66 {
    /**
     * 每日一题：2021/10/21
     * <p>
     * 66. 加一
     * <p>
     * 难度：easy
     * <p>
     * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
     * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
     * 你可以假设除了整数 0 之外，这个整数不会以零开头。
     * <p>
     * 示例：
     * 输入：digits = [1,2,3]
     * <p>
     * 输出：[1,2,4]
     * <p>
     * 解释：输入数组表示数字 123。
     * <p>
     * 提示
     * <p>
     * 1 <= digits.length <= 100
     * <p>
     * 0 <= digits[i] <= 9
     */


    /*
    思路2：按位加法
     */
    public int[] plusOne(int[] digits) {
        int len = digits.length;
        int flag = 0,add=1;
        for (int i = len - 1; i >= 0; i--) {
            if (i != len - 1) {
                add = 0;
                if (flag == 0) {

                    break;
                }
            }
            int cur = digits[i] + flag + add;
            if (cur < 10) {
                digits[i] = cur;
            } else {
                digits[i] = cur % 10;
            }
            flag = cur / 10;
        }
        if (flag != 0) {
            int[] res = new int[len + 1];
            res[0] = flag;
            System.arraycopy(digits, 0, res, 1, len);
            return res;
        }
        return digits;

    }

    @Test
    public void test() {
        int[] digits = {9};


    }
}
