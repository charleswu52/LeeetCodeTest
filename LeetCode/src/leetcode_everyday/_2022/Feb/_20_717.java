package leetcode_everyday._2022.Feb;

import org.junit.Test;

/**
 * @author WuChao
 * @create 2022/2/20 7:17
 */
public class _20_717 {
    /**
     * 每日一题：2022/2/19
     * <p>
     * 717. 1比特与2比特字符
     * <p>
     * 难度：easy
     * <p>
     * 有两种特殊字符：
     * <p>
     * 第一种字符可以用一个比特 0 来表示
     * 第二种字符可以用两个比特(10 或 11)来表示、
     * 给定一个以 0 结尾的二进制数组 bits ，如果最后一个字符必须是一位字符，则返回 true 。
     * <p>
     * 示例
     * <p>
     * 输入: bits = [1, 0, 0]
     * <p>
     * 输出: true
     * <p>
     * 解释: 唯一的编码方式是一个两比特字符和一个一比特字符。
     * 所以最后一个字符是一比特字符。
     * <p>
     * 范围
     * <p>
     * 1 <= bits.length <= 1000
     * bits[i] == 0 or 1
     */

    /*
    思路：简单模拟
     */
    public boolean isOneBitCharacter(int[] bits) {
        int len = bits.length;
        int idx = 0;
        while (idx < len-1) {
            if (bits[idx] == 0) {
                idx++;
            } else if (bits[idx] == 1) {
                idx += 2;
            }
        }
        return idx == len - 1;

    }

}
