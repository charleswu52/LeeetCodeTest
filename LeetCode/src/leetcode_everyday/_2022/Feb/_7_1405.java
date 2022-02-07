package leetcode_everyday._2022.Feb;

import javafx.util.Pair;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author WuChao
 * @create 2022/2/7 10:28
 */
public class _7_1405 {
    /**
     * 每日一题：2022/2/7
     * <p>
     * 1405. 最长快乐字符串
     * <p>
     * 难度：medium
     * <p>
     * 如果字符串中不含有任何 'aaa'，'bbb' 或 'ccc' 这样的字符串作为子串，那么该字符串就是一个「快乐字符串」。
     * <p>
     * 给你三个整数 a，b ，c，请你返回 任意一个 满足下列全部条件的字符串 s：
     * <p>
     * s 是一个尽可能长的快乐字符串。
     * s 中 最多 有a 个字母 'a'、b 个字母 'b'、c 个字母 'c' 。
     * s 中只含有 'a'、'b' 、'c' 三种字母。
     * 如果不存在这样的字符串 s ，请返回一个空字符串 ""。
     * <p>
     * 输入：a = 1, b = 1, c = 7
     * <p>
     * 输出："ccaccbcc"
     * <p>
     * 解释："ccbccacc" 也是一种正确答案。
     * <p>
     * 范围
     * <p>
     * 0 <= a, b, c <= 100
     * a + b + c > 0
     */

    /*
    思路：贪心

     */
    public String longestDiverseString(int a, int b, int c) {
        StringBuffer stringBuffer = new StringBuffer();
        List<Pair<Integer, Character>> list = new ArrayList<>();
        list.add(new Pair<>(a, 'a'));
        list.add(new Pair<>(b, 'b'));
        list.add(new Pair<>(c, 'c'));
        while (true) {
            Collections.sort(list, (o1, o2) -> o2.getKey() - o1.getKey());
            int preSize = stringBuffer.length();
            for (int i = 0; i < 3; i++) {
                Pair<Integer, Character> pair = list.get(i);
                if (pair.getKey() == 0 ||
                        stringBuffer.length() >= 2 && pair.getValue() == stringBuffer.charAt(stringBuffer.length() - 1)
                                && pair.getValue() == stringBuffer.charAt(stringBuffer.length() - 2)) {
                    continue;
                }
                stringBuffer.append(pair.getValue());
                list.set(i, new Pair<>(pair.getKey() - 1, pair.getValue()));
                break;
            }
            if (preSize == stringBuffer.length()) {
                break;
            }
        }
        return stringBuffer.toString();
    }

    @Test
    public void test() {
        List<Pair<Integer, Character>> list = new ArrayList<>();
        list.add(new Pair<>(2, 'a'));
        list.add(new Pair<>(4, 'b'));
        list.add(new Pair<>(1, 'c'));
        Collections.sort(list, (o1, o2) -> o2.getKey() - o1.getKey());
        for (int i = 0; i < list.size(); i++) {
            list.set(i, new Pair<>(list.get(i).getKey() - 1, list.get(i).getValue()));
        }

        System.out.println(list);
    }
}
