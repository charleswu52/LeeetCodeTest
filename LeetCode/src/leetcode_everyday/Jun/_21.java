package leetcode_everyday.Jun;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author WuChao
 * @create 2021/6/21 13:08
 */
public class _21 {
    /**
     * 每日一题：2021/6/21
     * 401. 二进制手表
     * 难度: easy
     * <p>
     * 二进制手表顶部有 4 个 LED 代表 小时（0-11），底部的 6 个 LED 代表 分钟（0-59）。每个 LED 代表一个 0 或 1，最低位在右侧。
     * <p>
     * 例如，下面的二进制手表读取 "3:25" 。
     * 给你一个整数 turnedOn ，表示当前亮着的 LED 的数量，返回二进制手表可以表示的所有可能时间。你可以 按任意顺序 返回答案。
     * <p>
     * 小时不会以零开头：
     * <p>
     * 例如，"01:00" 是无效的时间，正确的写法应该是 "1:00" 。
     * 分钟必须由两位数组成，可能会以零开头：
     * <p>
     * 例如，"10:2" 是无效的时间，正确的写法应该是 "10:02" 。
     * <p>
     *
     * <p>
     * 示例:
     * 输入：turnedOn = 1
     * 输出：["0:01","0:02","0:04","0:08","0:16","0:32","1:00","2:00","4:00","8:00"]
     * <p>
     * 输入：turnedOn = 9
     * 输出：[]
     * <p>
     * 数据范围：
     * 0 <= turnedOn <= 10
     */

    /*
    题目分析：
    手表共有 4 + 6 = 10 个灯，但是合法的灯上面最多亮3个，下面最多亮 5 个，因此 不会超过 8 个灯
    题目定义为easy ，首先可以这样考虑，上面灯亮范围和下面灯亮范围是可枚举的

     */
    public List<String> readBinaryWatch(int turnedOn) {
        List<String> res = new ArrayList<>();
        if (turnedOn > 8) {
            return res;
        }
        Map<Integer, List<String>> hour = new HashMap<>();
        hour.put(0, new ArrayList<String>() {{
            add("0");
        }});
        hour.put(1, new ArrayList<String>() {{
            add("1");
            add("2");
            add("4");
            add("8");
        }});
        hour.put(2, new ArrayList<String>() {{
            add("3");
            add("5");
            add("6");
            add("9");
            add("10");
        }});
        hour.put(3, new ArrayList<String>() {{
            add("7");
            add("11");
        }});
        Map<Integer, List<String>> minutes = new HashMap<>();
        minutes.put(0, new ArrayList<String>() {{
            add("00");
        }});
        minutes.put(1, new ArrayList<String>() {{
            add("01");
            add("02");
            add("04");
            add("08");
            add("16");
            add("32");
        }});
        minutes.put(2, new ArrayList<String>() {{
            add("03");
            add("05");
            add("06");
            add("09");
            add("10");
            add("12");
            add("17");
            add("18");
            add("20");
            add("24");
            add("33");
            add("34");
            add("36");
            add("40");
            add("48");
        }});
        minutes.put(3, new ArrayList<String>() {{
            add("07");
            add("11");
            add("13");
            add("14");
            add("19");
            add("21");
            add("22");
            add("25");
            add("26");
            add("28");
            add("35");
            add("37");
            add("38");
            add("41");
            add("42");
            add("44");
            add("49");
            add("50");
            add("52");
            add("56");
        }});
        minutes.put(4, new ArrayList<String>() {{
            add("15");
            add("23");
            add("27");
            add("29");
            add("30");
            add("39");
            add("43");
            add("45");
            add("46");
            add("51");
            add("53");
            add("54");
            add("57");
            add("58");
        }});
        minutes.put(5, new ArrayList<String>() {{
            add("31");
            add("47");
            add("55");
            add("59");
        }});
        for (int i = 0; i <= Math.min(turnedOn, 3); i++) {
            for (String h : hour.get(i)) {
                String t = h + ":";
                int j = turnedOn - i;
                if (j > 5) {
                    break;
                } else {
                    for (String m : minutes.get(j)) {
                        res.add(t + m);
                    }
                }
            }

        }
        return res;
    }

    @Test
    public void test() {
        System.out.println(readBinaryWatch(6));
    }
}
