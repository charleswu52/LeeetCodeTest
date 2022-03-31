package leetcode_everyday._2022.Mar;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WuChao
 * @create 2022/3/31 8:55
 */
public class _31_728 {
    /**
     * 每日一题：2022/3/31
     * <p>
     * 728. 自除数
     * <p>
     * 难度：easy
     * <p>
     * 自除数 是指可以被它包含的每一位数整除的数。
     *
     * 例如，128 是一个 自除数 ，因为 128 % 1 == 0，128 % 2 == 0，128 % 8 == 0。
     * 自除数 不允许包含 0 。
     *
     * 给定两个整数 left 和 right ，返回一个列表，列表的元素是范围 [left, right] 内所有的 自除数 。
     * <p>
     * 示例1
     * <p>
     * 输入：left = 1, right = 22
     *
     * 输出：[1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15, 22]
     * <p>
     * 范围
     * <p>
     * 1 <= left <= right <= 10^4
     */

    /*
    思路：
     */
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> res = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            char[] chars = String.valueOf(i).toCharArray();
            boolean flag = true;
            for (char ch : chars) {
                if (ch == '0') {
                    flag = false;
                    break;
                }
                int i1 = Integer.parseInt(String.valueOf(ch));
                if (i % i1 != 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                res.add(i);
            }
        }
        return res;
    }

    @Test
    public void test() {
        int left = 1, right = 22;
        System.out.println(selfDividingNumbers(left, right));
    }
}
