package leetcode_topInterview;

/**
 * @author WuChao
 * @create 2021/7/28 21:18
 */
public class _415 {
    /**
     * codeTop 热题 面试常考
     * <p>
     * 415. 字符串相加
     * <p>
     * 难度：easy
     * <p>
     * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
     * <p>
     * 提示：
     * <p>
     * num1 和num2 的长度都小于 5100
     * num1 和num2 都只包含数字 0-9
     * num1 和num2 都不包含任何前导零
     * 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式
     */

    /*
    思路： 模拟题

     */

    public String addStrings(String num1, String num2) {
        int idx1 = num1.length() - 1, idx2 = num2.length() - 1;
        int flag = 0;
        StringBuilder res = new StringBuilder();
        while (idx1 >= 0 || idx2 >= 0 || flag != 0) {
            int x = idx1 >= 0 ? num1.charAt(idx1) - '0' : 0;
            int y = idx2 >= 0 ? num2.charAt(idx2) - '0' : 0;
            int sum = x + y + flag;
            res.append(sum % 10);
            flag =  sum / 10 ;
            idx1--;
            idx2--;

        }
        return res.reverse().toString();

    }

}
