package leetcode_everyday.Oct;

/**
 * @author WuChao
 * @create 2021/10/11 8:19
 */
public class _11_273 {
    /**
     * 每日一题：2021/10/11
     * <p>
     * 273. 整数转换英文表示
     * <p>
     * 难度：hard
     * <p>
     * 将非负整数 num 转换为其对应的英文表示。
     * <p>
     * 示例 1：
     * <p>
     * 输入：num = 123
     * <p>
     * 输出："One Hundred Twenty Three"
     * <p>
     * 示例 2：
     * <p>
     * 输入：num = 1234567891
     * <p>
     * 输出："One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
     * <p>
     * 提示
     * <p>
     * 0 <= num <= 231 - 1
     */

    /*
    思路：递归
    最多有10位数，将整数转换成英文表示中，将数字按照 33 位一组划分，将每一组的英文表示拼接之后即可得到整数 num 的英文表示。
    每一组最多有 3 位数，可以使用递归的方式得到每一组的英文表示。根据数字所在的范围，具体做法如下：
        小于 20 的数可以直接得到其英文表示；
        大于等于 20 且小于 100 的数首先将十位转换成英文表示，然后对个位递归地转换成英文表示；
        大于等于 100 的数首先将百位转换成英文表示，然后对其余部分（十位和个位）递归地转换成英文表示。


     */

    String[] singles = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"}; // 单数 0-9
    String[] teens = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen",
            "Eighteen", "Nineteen"};    // 双数，10-19
    String[] tens = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"}; // 十
    String[] thousands = {"", "Thousand", "Million", "Billion"};

    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }

        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 3, unit = 1000000000; i >= 0; i--, unit /= 1000) {
            int curNum = num / unit;
            if (curNum != 0) {
                num -= curNum * unit;
                StringBuffer curr = new StringBuffer();
                helper(curr, curNum);
                curr.append(thousands[i]).append(" ");
                stringBuffer.append(curr);
            }

        }
        return stringBuffer.toString().trim();
    }

    public void helper(StringBuffer curr, int num) {
        if (num == 0) {
            return;
        } else if (num < 10) {
            curr.append(singles[num]).append(" ");
        } else if (num < 20) {
            curr.append(teens[num - 10]).append(" ");
        } else if (num < 100) {
            curr.append(tens[num / 10]).append(" ");
            helper(curr, num % 10);
        } else {
            curr.append(singles[num / 100]).append(" Hundred ");
            helper(curr, num % 100);
        }
    }

}
