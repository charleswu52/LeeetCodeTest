/**
 * @author WuChao
 * @since 2021/3/15 上午10:09
 */
public class _20 {
    /**
     * 剑指 Offer 20. 表示数值的字符串
     * 难度: medium
     * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
     * 例如，字符串"+100"、"5e2"、"-123"、"3.1416"、"-1E-16"、"0123"都表示数值，
     * 但"12e"、"1a3.14"、"1.2.3"、"+-5"及"12e+5.4"都不是。
     */

    public boolean isNumber(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        s = s.trim();  // 去掉首位空格
        boolean numFlag = false;  //是否是数字
        boolean dotFlag = false;    //是否出现小数点
        boolean eFlag = false;  //是否出现e
        for (int i = 0; i < s.length(); i++) {
            //判定为数字，则标记numFlag
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                numFlag = true;
                // 判定为'.' ,需要之前没出现过，而且e也没出现
            } else if (s.charAt(i) == '.' && !dotFlag && !eFlag) {
                dotFlag = true;
                // 判定为'e'，需要之前没出出现过而且，出现过数字
            } else if ((s.charAt(i) == 'e' || s.charAt(i) == 'E') && numFlag && !eFlag) {
                eFlag = true;
                //e出现后必须还得再有数字，因此需要把numFlag置为false
                numFlag = false;
            } else if ((s.charAt(i) == '+' || s.charAt(i) == '-') && (i == 0 || s.charAt(i - 1) == 'e' || s.charAt(i - 1) == 'E')) {

                // 其他情况都是非法
            } else {
                return false;
            }
        }
        return numFlag;

    }

    public void sword2Offer_20() {
        System.out.println(isNumber("12e+5.4"));

    }


}
