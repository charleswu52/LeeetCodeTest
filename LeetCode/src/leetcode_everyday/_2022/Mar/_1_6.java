package leetcode_everyday._2022.Mar;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WuChao
 * @create 2022/3/1 8:49
 */
public class _1_6 {
    /**
     * 每日一题：2022/3/1
     * <p>
     * 6. Z 字形变换
     * <p>
     * 难度：medium
     * <p>
     * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
     * <p>
     * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
     * <p>
     * P   A   H   N
     * A P L S I I G
     * Y   I   R
     * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
     * <p>
     * 请你实现这个将字符串进行指定行数变换的函数：
     * <p>
     * string convert(string s, int numRows);
     * <p>
     * 示例
     * <p>
     * 输入：s = "PAYPALISHIRING", numRows = 4
     * 输出："PINALSIGYAHRPI"
     * 解释：
     * P     I    N
     * A   L S  I G
     * Y A   H R
     * P     I
     *
     * <p>
     * 范围
     * <p>
     * 1 <= s.length <= 1000
     * s 由英文字母（小写和大写）、',' 和 '.' 组成
     * 1 <= numRows <= 1000
     */

    public String convert(String s, int numRows) {
        int len = s.length();
        if (len < numRows || numRows < 2) {
            return s;
        }
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            rows.add(new StringBuilder());
        }
        int i = 0, flag = -1;
        for (char ch : s.toCharArray()) {
            rows.get(i).append(ch);
            if (i == 0 || i == numRows - 1) {
                flag = -flag;
            }
            i += flag;
        }
        StringBuilder res = new StringBuilder();
        for (int j = 0; j < numRows; j++) {
            res.append(rows.get(j));
        }
        return res.toString();
    }
}
