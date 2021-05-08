package chp5;

import org.junit.Test;

/**
 * @author WuChao
 * @since 2021/5/8 上午11:20
 */
public class _3 {
    /**
     * 程序员面试金典(version 6) - 面试题 05.03. 翻转数位
     * 难度: easy
     * <p>
     * 给定一个32位整数 num，你可以将一个数位从0变为1。请编写一个程序，找出你能够获得的最长的一串1的长度。
     *
     * <p>
     * 示例:
     * 输入: num = 1775(11011101111)
     * 输出: 8
     * <p>
     * 输入: num = 7(01112)
     * 输出: 4
     *
     * <p>
     * 数据范围：
     */

    /*
    思路：使用滑动窗口的思路
    先将数字转换为二进制String字符串，然后使用滑动窗口保证窗口内0的个数<=1,然后求得满足要求的窗口的最大值
     */
    public int reverseBits(int num) {

        int left = 0, right = 0;
        int count = 0;
        while (right < 32) {
            if (((1 << right) & num) == 0) count++;
            if (count > 1) {
                if (((1 << left) & num) == 0) count--;
                left++;
            }
            right++;
        }
        return right - left;


    }

    @Test
    public void test() {
        System.out.println(reverseBits(2147483647));
    }
}
