package leetcode_everyday.Dec;

/**
 * @author WuChao
 * @create 2021/12/27 上午10:28
 */
public class _21_1154 {
    /**
     * 每日一题：2021/12/21
     * <p>
     * 1154. 一年中的第几天
     * <p>
     * 难度：easy
     * <p>
     * 给你一个字符串 date ，按 YYYY-MM-DD 格式表示一个 现行公元纪年法 日期。请你计算并返回该日期是当年的第几天。
     *
     * 通常情况下，我们认为 1 月 1 日是每年的第 1 天，1 月 2 日是每年的第 2 天，依此类推。每个月的天数与现行公元纪年法（格里高利历）一致。
     * <p>
     * 示例 1：
     * <p>
     * 输入：text = "alice is a good girl she is a good student", first = "a", second = "good"
     *
     * 输出：["girl","student"]
     * <p>
     * 范围
     * <p>
     * 1 <= text.length <= 1000
     * text 由小写英文字母和空格组成
     * text 中的所有单词之间都由 单个空格字符 分隔
     * 1 <= first.length, second.length <= 10
     * first 和 second 由小写英文字母组成
     **/


    int[] months = {
            31,
            28,
            31,
            30,
            31,
            30,
            31,
            31,
            30,
            31,
            30,
            31
    };

    public int dayOfYear(String date) {
        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(5, 7));
        int day = Integer.parseInt(date.substring(8));
        int res = 0;
        for (int i = 1; i < month; i++) {
            res += months[i - 1];
        }
        res += day;
        if (month > 2 && ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0))
        {
            res++;
        }
        return res;
    }
}
