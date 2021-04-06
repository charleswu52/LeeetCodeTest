package chp1;

/**
 * @author WuChao
 * @since 2021/4/6 下午6:22
 */
public class _9 {
    /**
     * 程序员面试金典(version 6) - 面试题 01.09. 字符串轮转
     * 难度: easy
     * <p>
     * 字符串轮转。给定两个字符串s1和s2，请编写代码检查s2是否为s1旋转而成（比如，waterbottle 是erbottlewat旋转后的字符串）。
     *
     * <p>
     * 例如：
     *  输入：s1 = "aa", s2 = "aba"
     *  输出：False
     *
     * <p>
     * 数据范围：
     * 字符串长度在[0, 100000]范围内。
     */

    /**
     * 暴力解法：
     * 让s1循环拼接子字符串看是否有与s2相等的情况
     * @param s1
     * @param s2
     * @return
     */
    public boolean isFlipedString(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        int len = s1.length();
        if (len < 2) {
            return true;
        }


        if (s1.equals(s2)) {
            return true;
        }

        for (int i = 1; i < len; i++) {
            String temp = s1.substring(i) + s1.substring(0, i);
            if (temp.equals(s2)) {
                return true;
            }
        }
        return false;

    }

    /**
     * 取巧的方法：让s2+s2,如果是s1轮转得到的，那么s2+s2结果中一定会包含s1
     * @param s1
     * @param s2
     * @return
     */
    public boolean isFlipedString2(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        String temp = s2 + s2;
        return temp.contains(s1);
    }

}
