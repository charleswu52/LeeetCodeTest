package chp1;

/**
 * @author WuChao
 * @since 2021/4/5 下午7:38
 */
public class _5 {
    /**
     * 程序员面试金典(version 6) - 面试题 01.05. 一次编辑
     * 难度: medium
     * <p>
     * 字符串有三种编辑操作:插入一个字符、删除一个字符或者替换一个字符。 给定两个字符串，编写一个函数判定它们是否只需要一次(或者零次)编辑。
     *
     * <p>
     * 例如：
     * 输入:
     * first = "pale"
     * second = "ple"
     * 输出: True
     *
     * <p>
     * 数据范围：
     */
    public boolean oneEditAway(String first, String second) {
        int sizeA = first.length(), sizeB = second.length();
        if (Math.abs(sizeA - sizeB) > 1) {
            return false;
        }
        if (Math.abs(sizeA - sizeB) == 0) {
            int sum = 0;
            for (int i = 0; i < first.length(); i++) {
                if (first.charAt(i) != second.charAt(i)) {
                    sum++;
                    if (sum > 1) {
                        break;
                    }
                }
            }
            return sum <= 1;
        }
        if (Math.abs(sizeA - sizeB) == 1) {
            if (first.length() > second.length()) {
                String temp = first;
                first = second;
                second = temp;
            }
            for (int i = 0; i < second.length(); i++) {
                String newSec;
                if (i == 0) {
                    newSec = second.substring(1);
                }else {
                    newSec = second.substring(0, i) + second.substring(i + 1);
                }
                if (first.equals(newSec)) {
                    return true;
                }
            }
        }
        return false;
    }

}
