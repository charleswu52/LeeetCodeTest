package leetcode_everyday.Jun;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author WuChao
 * @create 2021/6/17 8:22
 */
public class _17 {
    /**
     * 每日一题：2021/6/17
     * 65. 有效数字
     * 难度: medium
     * <p>
     * 有效数字（按顺序）可以分成以下几个部分：
     * <p>
     * 1.一个 小数 或者 整数
     * 2.（可选）一个 'e' 或 'E' ，后面跟着一个 整数
     * <p>
     * 小数（按顺序）可以分成以下几个部分：
     * 1.（可选）一个符号字符（'+' 或 '-'）
     * 2.下述格式之一：
     * 2.1至少一位数字，后面跟着一个点 '.'
     * 2.2至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字
     * 2.3一个点 '.' ，后面跟着至少一位数字
     * <p>
     * 整数（按顺序）可以分成以下几个部分：
     * 1.（可选）一个符号字符（'+' 或 '-'）
     * 2. 至少一位数字
     * <p>
     * 部分有效数字列举如下：
     * ["2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"]
     * <p>
     * 部分无效数字列举如下：
     * ["abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"]
     * <p>
     * 给你一个字符串 s ，如果 s 是一个 有效数字 ，请返回 true 。
     *
     * <p>
     * 示例:
     * 输入：s = "0"
     * 输出：true
     * <p>
     * 输入：s = "e"
     * 输出：false
     * <p>
     * 输入：s = "."
     * 输出：false
     * <p>
     * 输入：s = ".1"
     * 输出：true
     * <p>
     * 数据范围：
     * 1 <= s.length <= 20
     * s 仅含英文字母（大写和小写），数字（0-9），加号 '+' ，减号 '-' ，或者点 '.' 。
     */

    /*
    题目解析：判断一个数是否是整数或者是科学计数法表示

    思路1： 正则表达式匹配
    根据题目要求写出不同条件下的正则表达式模式
     */
    public boolean isNumber(String s) {
        if (s == null || "".equals(s)) {
            return false;
        }

        // 1. 整数
        String regex1 = "[+-]?\\d+";

        // 2. 一个小数
        String regex2 = "[+-]?((\\d+\\.)|(\\d+\\.\\d+)|(\\.\\d+))";

        // 3. 科学计数法：一个整数|小数 + 科学计数法表示后缀
        String regex3 = "([eE][+-]?\\d+)";

        // 化简最终的表达式
        String regex = "([+-]?(\\d+(\\.\\d*)?|(\\.\\d+)))([eE][+-]?\\d+)?";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(s).matches();

    }

    /*
    但是上述也就运用了一个正则表达式的技巧，真正判断两者是如何匹配的过程还是调用的java库的函数
    其内部核心是使用了自动机来实现这个匹配过程的，因此 可以手写这个自动机匹配的过程来实现这个匹配函数

    思路2：自动机实现

    列出所有的状态：
        0.初始状态
        1.符号位
        2.整数部分
        3.左侧有整数的小数点
        4.左侧无整数的小数点（根据前面的第二条额外规则，需要对左侧有无整数的两种小数点做区分）
        5.小数部分
        6.字符 e
        7.指数部分的符号位
        8.指数部分的整数部分
    所有接收状态： 2 3 5 8
    转移规则： 见图 https://assets.leetcode-cn.com/solution-static/65/1.png

     */
    public CharType toCharType(char ch) {
        if (ch >= '0' && ch <= '9') {
            return CharType.CHAR_NUMBER;
        } else if (ch == 'e' || ch == 'E') {
            return CharType.CHAR_EXP;
        } else if (ch == '.') {
            return CharType.CHAR_POINT;
        } else if (ch == '+' || ch == '-') {
            return CharType.CHAR_SIGN;
        } else {
            return CharType.CHAR_ILLEGAL;
        }
    }

    // 枚举所有字符类型
    enum CharType {
        CHAR_NUMBER,
        CHAR_EXP,
        CHAR_POINT,
        CHAR_SIGN,
        CHAR_ILLEGAL
    }

    // 枚举所有状态
    enum State {
        STATE_INITIAL,
        STATE_INT_SIGN,
        STATE_INTEGER,
        STATE_POINT,
        STATE_POINT_WITHOUT_INT,
        STATE_FRACTION,
        STATE_EXP,
        STATE_EXP_SIGN,
        STATE_EXP_NUMBER,
        STATE_END
    }


    /*
    自动机实现 状态转移来匹配
     */
    public boolean isNumber2(String s) {
        Map<State, Map<CharType, State>> transfer = new HashMap<>();

        Map<CharType, State> initialMap = new HashMap<CharType,State>(){{
            put(CharType.CHAR_NUMBER, State.STATE_INTEGER);
            put(CharType.CHAR_POINT, State.STATE_POINT_WITHOUT_INT);
            put(CharType.CHAR_SIGN, State.STATE_INT_SIGN);}
        };
        transfer.put(State.STATE_INITIAL, initialMap);

        Map<CharType,State> intSignMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUMBER, State.STATE_INTEGER);
            put(CharType.CHAR_POINT, State.STATE_POINT_WITHOUT_INT);}
        };
        transfer.put(State.STATE_INT_SIGN, intSignMap);

        Map<CharType, State> integerMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUMBER, State.STATE_INTEGER);
            put(CharType.CHAR_EXP, State.STATE_EXP);
            put(CharType.CHAR_POINT, State.STATE_POINT);
        }};
        transfer.put(State.STATE_INTEGER, integerMap);

        Map<CharType, State> pointMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUMBER, State.STATE_FRACTION);
            put(CharType.CHAR_EXP, State.STATE_EXP);
        }};
        transfer.put(State.STATE_POINT, pointMap);

        Map<CharType, State> pointWithoutIntMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUMBER, State.STATE_FRACTION);
        }};
        transfer.put(State.STATE_POINT_WITHOUT_INT, pointWithoutIntMap);

        Map<CharType, State> fractionMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUMBER, State.STATE_FRACTION);
            put(CharType.CHAR_EXP, State.STATE_EXP);
        }};
        transfer.put(State.STATE_FRACTION, fractionMap);

        Map<CharType, State> expMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUMBER, State.STATE_EXP_NUMBER);
            put(CharType.CHAR_SIGN, State.STATE_EXP_SIGN);
        }};
        transfer.put(State.STATE_EXP, expMap);

        Map<CharType, State> expSignMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUMBER, State.STATE_EXP_NUMBER);
        }};
        transfer.put(State.STATE_EXP_SIGN, expSignMap);

        Map<CharType, State> expNumberMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUMBER, State.STATE_EXP_NUMBER);
        }};
        transfer.put(State.STATE_EXP_NUMBER, expNumberMap);

        int len = s.length();
        State state = State.STATE_INITIAL;
        for (int i = 0; i < len; i++) {
            CharType type = toCharType(s.charAt(i));
            if (!transfer.get(state).containsKey(type)) {
                return false;
            } else {
                state = transfer.get(state).get(type);
            }
        }
        return state == State.STATE_INTEGER ||
               state == State.STATE_POINT ||
               state == State.STATE_FRACTION ||
               state == State.STATE_EXP_NUMBER ||
               state == State.STATE_END;
    }

}
