import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author WuChao
 * @since 2021/3/25 上午9:35
 */
public class _50 {
    /**
     * 剑指 Offer 50. 第一个只出现一次的字符
     * 难度: easy
     * <p>
     * 在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。
     * <p>
     * 示例：
     * s = "abaccdeff"
     * 返回 "b"
     * <p>
     * s = ""
     * 返回 " "
     * <p>
     * 数据范围：
     * 0 <= s 的长度 <= 50000
     */

    /*
    思路：使用HashMap 存储 每个字符对应出现的次数和第一次出现的下标
     */
    public char firstUniqChar(String s) {
        if (s.length() < 1) {
            return ' ';
        }
        HashMap<Character, int[]> store = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (!store.containsKey(s.charAt(i))) {
                store.put(s.charAt(i), new int[]{1, i});
            } else {
                store.get(s.charAt(i))[0] += 1;
            }
        }
        Iterator<Map.Entry<Character, int[]>> iterator = store.entrySet().iterator();
        int min = Integer.MAX_VALUE;
        while (iterator.hasNext()) {
            Map.Entry<Character, int[]> entry = iterator.next();
            if (entry.getValue()[0] == 1) {
                min = Math.min(min, entry.getValue()[1]);
            }
        }
        return min != Integer.MAX_VALUE ? s.charAt(min) : ' ';
    }

    @Test
    public void test() {
        String s = "";
        System.out.println(firstUniqChar(s));
    }
}
