package leetcode_everyday._2021.Dec;

/**
 * @author WuChao
 * @create 2021/12/21 22:56
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
     * <p>
     * 通常情况下，我们认为 1 月 1 日是每年的第 1 天，1 月 2 日是每年的第 2 天，依此类推。每个月的天数与现行公元纪年法（格里高利历）一致。
     *
     * <p>
     * 示例 1：
     * <p>
     * 输入：date = "2019-01-09"
     * <p>
     * 输出：9
     * <p>
     * 输入：date = "2019-02-10"
     * <p>
     * 输出：41
     * <p>
     * 范围
     * <p>
     * date.length == 10
     * date[4] == date[7] == '-'，其他的 date[i] 都是数字
     * date 表示的范围从 1900 年 1 月 1 日至 2019 年 12 月 31 日
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
        if (month > 2 && ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)){
            res++;
        }
        return res;
    }

}
