package chp1;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author WuChao
 * @since 2021/4/4 上午9:47
 */
public class _1 {
    /**
     * 程序员面试金典(version 6) - 面试题 01.01. 判定字符是否唯一
     * 难度: easy
     * <p>
     * 实现一个算法，确定一个字符串 s 的所有字符是否全都不同。
     * <p>
     * 例如：
     * 输入: s = "leetcode"
     * 输出: false
     *
     * <p>
     * 数据范围：
     * 0 <= len(s) <= 100
     * 如果你不使用额外的数据结构，会很加分。
     */

    public boolean isUnique(String astr) {
        if (astr.length() < 2) {
            return true;
        }
        char[] chars = astr.toCharArray();
        Arrays.sort(chars);
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == chars[i - 1]) {
                return false;
            }
        }
        return true;
    }

    /*
    题意解析：
    题目比较简单，但是隐含表达了不能使用其他的数据结构的难点
    所以考察点就值得思考。直接双重循环的暴力解法的时间复杂度是O(n^2),是不可取的
     */
    /*
    考虑使用位运算的方式来做：
    不过这题说的是判断字符是否唯一，但没说包含哪些字符，如果是只包含英文字母的话，直接使用一个long类型的数字即可，因为long类型是64位，
    大写字母'A'的ASCII码是65，小写字母'z'的ASCII码是122，两者相差58（122-65+1=58，加1是因为这里要包含大写字母'A'），是小于64的。
    我们用每一位存储每一个字符，比如右边第1位存储大写字符'A'，右边第2位存储大写字符'B'……，以此类推。
    然后再遍历字符串中的字符，先判断对应的位置是否是1，如果是1，就表示已经存在了，直接返回false，如果对应的位置不是1，
    说明这个字符还没出现过，然后把对应的位置置为1
     */

    public boolean isUnique2(String astr) {
        long bits = 0;
        int size = astr.length();
        for (int i = 0; i < size; i++) {
            int move = astr.charAt(i) - 'A';
            if ((bits & (1L << move)) != 0) {
                // 有重复的直接返回false
                return false;
            } else {
                // 标记当前位置有这个字符
                bits |= (1L << move);
            }
        }
        return true;
    }

    @Test
    public void test() {

    }

}
