package chp17;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WuChao
 * @since 2021/6/4 上午10:02
 */
public class _5 {
    /**
     * 程序员面试金典(version 6) -  面试题 17.05.  字母与数字
     * 难度: medium
     * <p>
     * 给定一个放有字符和数字的数组，找到最长的子数组，且包含的字符和数字的个数相同。
     *
     * 返回该子数组，若存在多个最长子数组，返回左端点下标值最小的子数组。若不存在这样的数组，返回一个空数组。
     *
     * <p>
     * 示例:
     * 输入: ["A","1","B","C","D","2","3","4","E","5","F","G","6","7","H","I","J","K","L","M"]
     *
     * 输出: ["A","1","B","C","D","2","3","4","E","5","F","G","6","7"]
     *
     * 输入: ["A","A"]
     *
     * 输出: []
     * <p>
     * 数据范围：
     */

    /*
    思路：前缀和 + 哈希
    计算出从开头当前位置的字母和数字的个数，怎么得到以该位置为结束点的合法长度呢，显然有以下结论：
        如果当前位置数字个数与字母个数差diff=x, 如果在当前位置之前的位置数字个数与字母个数差出现过x，那么这一段长度一定是合法的。
     */
    public String[] findLongestSubarray(String[] array) {
        int resLen = 0, begin = 0, cnum = 0;
        Map<Integer, Integer> pos = new HashMap<>();
        pos.put(0, 0);
        for (int i = 1; i <= array.length; i++) {
            cnum += Character.isDigit(array[i - 1].charAt(0)) ? 1 : 0;

            int dif = 2 * cnum - i;
            if (!pos.containsKey(dif)) {
                pos.put(dif, i);
            } else if (resLen < i - pos.get(dif)) {
                resLen = i - pos.get(dif);
                begin = i - resLen;
            }
        }
        String[] res = new String[resLen];
        for (int i = 0; i < resLen; i++) {
            res[i] = array[i + begin];
        }
        return res;

    }

}
