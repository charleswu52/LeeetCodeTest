package chp1;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WuChao
 * @since 2021/4/4 上午11:43
 */
public class _4 {
    /**
     * 程序员面试金典(version 6) - 面试题 01.04. 回文排列
     * 难度: easy
     * <p>
     * 给定一个字符串，编写一个函数判定其是否为某个回文串的排列之一。
     * 回文串是指正反两个方向都一样的单词或短语。排列是指字母的重新排列。
     * 回文串不一定是字典当中的单词。
     *
     * <p>
     * 例如：
     * 输入："tactcoa"
     * 输出：true（排列有"tacocat"、"atcocta"，等等）
     *
     * <p>
     * 数据范围：
     */

    public boolean canPermutePalindrome(String s) {
        if (s.length() < 2) {
            return true;
        }
        int len = s.length();
        int sign = len % 2;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() % 2 == 1) {
                if (sign == 1) {
                    sign--;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    @Test
    public void test() {
        String s = "tactcoa";
    }
}
