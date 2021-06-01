package chp16;

import org.junit.Test;

import java.util.*;

/**
 * @author WuChao
 * @since 2021/6/1 上午11:04
 */
public class _20 {
    /**
     * 程序员面试金典(version 6) -  面试题 16.20. T9键盘
     * 难度: medium
     * <p>
     * 在老式手机上，用户通过数字键盘输入，手机将提供与这些数字相匹配的单词列表。每个数字映射到0至4个字母。
     * 给定一个数字序列，实现一个算法来返回匹配单词的列表。你会得到一张含有有效单词的列表。映射如下图所示：
     * <p>
     * 示例:
     * 输入：
     * 输入: num = "8733", words = ["tree", "used"]
     * 输出: ["tree", "used"]
     *
     * 输入: num = "2", words = ["a", "b", "c", "d"]
     * 输出: ["a", "b", "c"]
     * 数据范围：
     * num.length <= 1000
     * words.length <= 500
     * words[i].length == num.length
     * num中不会出现 0, 1 这两个数字
     */

    /*
    思路：

     */
    public List<String> getValidT9Words(String num, String[] words) {
        List<String> res = new ArrayList<>();
        if (num.length() == 0) {
            return res;
        }

        HashMap<Character, Integer> t9 = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            char cur = (char) (i + 'a');
            t9.put(cur, (i / 3) + 2);
        }
        t9.put('s', 7);
        t9.put('v', 8);
        t9.put('y', 9);
        t9.put('z', 9);
        for (String word : words) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < word.length(); i++) {
                stringBuffer.append(t9.get(word.charAt(i)));
            }
            if (stringBuffer.toString().equals(num)) {
                res.add(word);
            }
        }
        return res;

    }

    @Test
    public void test() {
        String num = "8733";
        String[] strings = {"tree", "used"};
        System.out.println(getValidT9Words(num, strings));
    }
}
