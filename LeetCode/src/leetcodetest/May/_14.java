package leetcodetest.May;

/**
 * @author WuChao
 * @since 2021/5/14 上午8:14
 */
public class _14 {
    /**
     * 每日一题：2021/5/14
     * 12. 整数转罗马数字
     * 难度: medium
     * <p>
     * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
     * 字符          数值
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     *
     * <p>
     * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
     * <p>
     * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
     * <p>
     * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
     * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
     * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
     * <p>
     * 给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。
     * <p>
     * 示例：
     * 输入: 1994
     * 输出: "MCMXCIV"
     * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
     * <p>
     * 输入: 58
     * 输出: "LVIII"
     * 解释: L = 50, V = 5, III = 3.
     * <p>
     *
     * <p>
     * 数据范围：
     * 1 <= num <= 3999
     */

    /*
    思路1:模拟
    根据罗马数字的唯一表示法，为了表示一个给定的整数 num，我们寻找不超过 num 的最大符号值，将 num 减去该符号值，然后继续寻找不超过 num
    的最大符号值，将该符号拼接在上一个找到的符号之后，循环直至 num 为 0。最后得到的字符串即为 num 的罗马数字表示。

     */
    public String intToRoman(int num) {
        String res = "";
        int[] aArray = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] rArray = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X",
                "IX", "V", "IV", "I"};
        if (num < 1 || num > 3999) {
            res = "-1";
        } else {
            for (int i = 0; i < aArray.length; i++) {
                while (num >= aArray[i]) {
                    res += rArray[i];
                    num -= aArray[i];
                }
            }
        }
        return res;
    }

    /*
    思路：编码
    千位数字只能由  M 表示；
    百位数字只能由  C， CD， D 和  CM 表示；
    十位数字只能由  X， XL， L 和  XC 表示；
    个位数字只能由  I， IV， V 和  IX 表示。

    由于数字范围是[1,3999]
    因此:
    千位数上只能是["","M","MM","MMM"]
    百位数上只能是["","C","CC","CCC","CD","D","DC","DCC","DCCC","CM"]
    十位数上只能是["","X","XX","XXX","XL","L","LX","LXX","LXXX","XC"]
    个位数上只能是["","I","II","III","IV","V","VI","VII","VIII","IX"]
     */
    String[] thousands = {"", "M", "MM", "MMM"};
    String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
    String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
    String[] ones = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

    public String intToRoman2(int num) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(thousands[num / 1000]);
        stringBuffer.append(hundreds[(num / 100) % 10]);
        stringBuffer.append(tens[(num / 10 )% 10]);
        stringBuffer.append(ones[num % 10]);
        return stringBuffer.toString();
    }


}
