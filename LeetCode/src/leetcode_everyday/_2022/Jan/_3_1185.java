package leetcode_everyday._2022.Jan;

/**
 * @author WuChao
 * @create 2022/1/3 10:32
 */
public class _3_1185 {
    /**
     * 每日一题：2022/1/3
     * <p>
     * 1185. 一周中的第几天
     * <p>
     * 难度：easy
     * <p>
     * 给你一个日期，请你设计一个算法来判断它是对应一周中的哪一天。
     * <p>
     * 输入为三个整数：day、month 和 year，分别表示日、月、年。
     * <p>
     * 您返回的结果必须是这几个值中的一个 {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}。
     * <p>
     * 示例 1：
     * <p>
     * 输入：day = 31, month = 8, year = 2019
     * <p>
     * 输出："Saturday"
     * <p>
     * 解释：
     * arr = [1, 2, 3, 4, 5, 6, 7, 8, 9]
     * arr = [2, 4, 6, 8]
     * arr = [2, 6]
     * arr = [6]
     * <p>
     * 示例 2：
     * <p>
     * 输入：n = 1
     * <p>
     * 输出：1
     * <p>
     * 范围
     * <p>
     * 给出的日期一定是在 1971 到 2100 年之间的有效日期。
     **/

    /*
    思路：数学计算
    先查日历发现起始1971年1月1日是星期五，所以源头从这天开始
    然后计算从给定日期到1971年1月1日过了多少天,然后mod7取星期几即可
     */
    public String dayOfTheWeek(int day, int month, int year) {
        String[] weeks = {"Thursday", "Friday", "Saturday","Sunday", "Monday", "Tuesday", "Wednesday", };
        int[] months = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int days = 0;
        int mod = 7;
        for (int i = 1971; i < year; i++) {
            if (isRunYear(i)) {
                days = (days % mod + 366 % mod) % mod;
            } else {
                days = (days % mod + 365 % mod) % mod;
            }
        }
        for (int i = 1; i < month; i++) {
            days = (days % mod + months[i-1] % mod) % mod;
            if (i == 2 && isRunYear(year)) {
                days = (days % mod + 1 % mod) % mod;
            }
        }
        days = (days % mod + day % mod) % mod;
        return weeks[days];

    }

    public boolean isRunYear(int year) {
        return (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0));
    }
}
