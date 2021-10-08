package leetcode_everyday.Aug;

/**
 * @author WuChao
 * @create 2021/8/17 8:52
 */
public class _17_551 {
    /**
     * 每日一题：2021/8/17
     * 551. 学生出勤记录 I
     * 难度：medium
     * <p>
     * 给你一个字符串 s 表示一个学生的出勤记录，其中的每个字符用来标记当天的出勤情况（缺勤、迟到、到场）。记录中只含下面三种字符：
     * <p>
     * 'A'：Absent，缺勤
     * 'L'：Late，迟到
     * 'P'：Present，到场
     * <p>
     * 如果学生能够 同时 满足下面两个条件，则可以获得出勤奖励：
     * <p>
     * 按 总出勤 计，学生缺勤（'A'）严格 少于两天。
     * 学生 不会 存在 连续 3 天或 3 天以上的迟到（'L'）记录。
     * 如果学生可以获得出勤奖励，返回 true ；否则，返回 false 。
     *
     * <p>
     * 示例 1：
     * 输入：s = "PPALLP"
     * 输出：true
     * 解释：学生缺勤次数少于 2 次，且不存在 3 天或以上的连续迟到记录。
     * <p>
     * 示例 2：
     * 输入：s = "PPALLL"
     * 输出：false
     * 解释：学生最后三天连续迟到，所以不满足出勤奖励的条件。
     *
     * <p>
     * 注意:
     * N 是一个正整数，并且不会超过15。
     */


    /*
    思路1： 使用String 自带函数

     */
    public boolean checkRecord(String s) {
        if (s.contains("LLL")) {
            return false;
        }
        String s1 = s.replace("A", "");
        if (s.length() - s1.length() >= 2) {
            return false;
        }
        return true;
    }

    /*
    思路2：一次遍历

     */
    public boolean checkRecord2(String s) {
        int absentCnt = 0, lateCnt = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == 'A') {
                absentCnt++;
                if (absentCnt >= 2) {
                    return false;
                }
            }
            if (ch == 'L') {
                lateCnt++;
                if (lateCnt >= 3) {
                    return false;
                }
            } else {
                lateCnt = 0;
            }
        }
        return true;
    }


}
