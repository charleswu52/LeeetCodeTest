package chp8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author WuChao
 * @since 2021/5/19 上午9:58
 */
public class _7 {
    /**
     * 程序员面试金典(version 6) - 面试题 08.07. 无重复字符串的排列组合
     * 难度: medium
     * <p>
     * 无重复字符串的排列组合。编写一种方法，计算某字符串的所有排列组合，字符串每个字符均不相同。
     *
     * <p>
     * 示例:
     * 输入：S = "qwe"
     * 输出：["qwe", "qew", "wqe", "weq", "ewq", "eqw"]
     * <p>
     * 输入：S = "ab"
     * 输出：["ab", "ba"]
     * <p>
     * 数据范围：
     * 字符都是英文字母。
     * 字符串长度在[1, 9]之间。
     */
    List<String> strings;
    public String[] permutation(String S) {
        char[] chars = S.toCharArray();
        strings = new ArrayList<>();
        perm(chars, new Stack<>());
        return strings.toArray(new String[strings.size()]);
    }

    public void perm(char[] array, Stack<Character> stack) {
        StringBuffer stringBuffer = new StringBuffer();
        if (array.length <= 0) {
            //进入了叶子节点，输出栈中内容
            for (Character character : stack) {
                stringBuffer.append(character);
            }
            strings.add(stringBuffer.toString());
        } else {
            for (int i = 0; i < array.length; i++) {
                //tmepArray是一个临时数组，用于就是Ri
                //eg：1，2，3的全排列，先取出1，那么这时tempArray中就是2，3
                char[] tempArray = new char[array.length - 1];
                System.arraycopy(array, 0, tempArray, 0, i);
                System.arraycopy(array, i + 1, tempArray, i, array.length - i - 1);
                stack.push(array[i]);
                perm(tempArray, stack);
                stack.pop();
            }
        }
    }
    @Test
    public void test() {
        String s = "qqe";
        System.out.println(Arrays.deepToString(permutation(s)));
    }

}
