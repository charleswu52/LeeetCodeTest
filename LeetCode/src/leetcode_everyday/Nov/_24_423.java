package leetcode_everyday.Nov;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author WuChao
 * @create 2021/11/24 上午8:59
 */
public class _24_423 {
    /**
     * 每日一题：2021/11/24
     * <p>
     * 423. 从英文中重建数字
     * <p>
     * 难度：medium
     * <p>
     * 给你一个字符串 s ，其中包含字母顺序打乱的用英文单词表示的若干数字（0-9）。按 升序 返回原始的数字。
     *
     * <p>
     * 示例 1：
     * <p>
     * 输入：s = "owoztneoer"
     * <p>
     * 输出："012"
     *
     * <p>
     * 示例 2：
     * <p>
     * 输入：s = "fviefuro"
     * <p>
     * 输出："45"
     * <p>
     * 范围
     * <p>
     * 1 <= s.length <= 105
     * s[i] 为 ["e","g","f","i","h","o","n","s","r","u","t","w","v","x","z"] 这些字符之一
     * s 保证是一个符合题目要求的字符串
     */


    /*
    思路：字符串模拟题
    依次确定每个数字出现的次数
     */
    public String originalDigits(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        int[] cnt = new int[10];
        cnt[0] = map.getOrDefault('z', 0);
        cnt[2] = map.getOrDefault('w', 0);
        cnt[4] = map.getOrDefault('u', 0);
        cnt[6] = map.getOrDefault('x', 0);
        cnt[8] = map.getOrDefault('g', 0);

        cnt[5] = map.getOrDefault('f', 0) - cnt[4];
        cnt[3] = map.getOrDefault('h', 0) - cnt[8];
        cnt[7] = map.getOrDefault('s', 0) - cnt[6];

        cnt[9] = map.getOrDefault('i', 0) - cnt[5] - cnt[6] - cnt[8];
        cnt[1] = map.getOrDefault('o', 0) - cnt[0] - cnt[2] - cnt[4];

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < cnt[i]; j++) {
                stringBuilder.append((char)(i+'0'));
            }
        }




        return stringBuilder.toString();
    }

    public static String getSortString(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

}
