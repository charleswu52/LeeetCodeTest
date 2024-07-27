
package leetcode_everyday._2024.Jul;

import javafx.util.Pair;
import org.junit.Test;

import java.util.Objects;

/**
 * @author charles
 * @create 2024/7/27
 */
public class _27_3106 {
    /**
     * 每日一题：2024/7/27
     * 3106. 满足距离约束且字典序最小的字符串
     * https://leetcode.cn/problems/lexicographically-smallest-string-after-operations-with-constraint/description/
     */

    public String getSmallestString(String s, int k) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            Pair<String, Integer> minChar = getMinChar(c, k);
            if (Objects.nonNull(minChar)) {
                stringBuilder.append(minChar.getKey());
                k -= minChar.getValue();
            }
        }


        return stringBuilder.toString();
    }

    public int getDis(char a, char b) {
        int aIdx = (int) a - 96;
        int bIdx = (int) b - 96;
        int maxidx = Math.max(aIdx, bIdx);
        return Math.min(Math.abs(aIdx - bIdx), 26 - Math.max(aIdx,bIdx)+ Math.min(aIdx , bIdx));
    }

    public Pair<String,Integer> getMinChar(char x, int k) {
        for (int i = 97; i < 97 + 26; i++) {
            char i1 = (char) i;
            int dis = getDis(i1, x);
            if (dis <= k) {
                return new Pair<>(String.valueOf(i1), dis);
            }
        }
        return null;
    }

    @Test
    public  void test() {
        String a = "asssszzeeaahhgg";
        System.out.println(getSmallestString(a, 1));
    }
}
