package chp16;

import java.util.Arrays;

/**
 * @author WuChao
 * @since 2021/5/30 上午9:26
 */
public class _10 {
    /**
     * 程序员面试金典(version 6) -  面试题 16.10. 生存人数
     * 难度: medium
     * <p>
     * 给定 N 个人的出生年份和死亡年份，第 i 个人的出生年份为 birth[i]，死亡年份为 death[i]，实现一个方法以计算生存人数最多的年份。
     * <p>
     * 你可以假设所有人都出生于 1900 年至 2000 年（含 1900 和 2000 ）之间。如果一个人在某一年的任意时期处于生存状态，
     * 那么他应该被纳入那一年的统计中。例如，生于 1908 年、死于 1909 年的人应当被列入 1908 年和 1909 年的计数。
     * <p>
     * 如果有多个年份生存人数相同且均为最大值，输出其中最小的年份。
     * <p>
     * 示例:
     * 输入：
     * birth = {1900, 1901, 1950}
     * death = {1948, 1951, 2000}
     * 输出： 1901
     * <p>
     * 数据范围：
     * 0 < birth.length == death.length <= 10000
     * birth[i] <= death[i]
     */

    /*
    题目解析：
    差分数组
    问题可以看成[1900,2000]范围内哪个点被覆盖的次数最多，覆盖是指题目给出的区间[birth,death]
    这样直观得想法就是给出一个区间[x,y]，就把区间[x,y]里面每个数字都加上1，最后扫描一遍[1900,2000]看看哪个点得值最大.
    使用差分数组，可以把一个区间得每一个数加1得操作，变成2个端点得操作，时间复杂度是O(1)。最终得复杂度是O(n)。
     */
    public int maxAliveYear(int[] birth, int[] death) {
        int n = birth.length;
        int[] store = new int[2003];
        Arrays.fill(store, 0);
        for (int i = 0; i < n; i++) {
            int x = birth[i], y = death[i];
            store[x] += 1;
            store[y+1] -= 1;
        }
        int mx = 0, idx = 0, sum = 0;
        for (int i = 1900; i < 2001; i++) {
            sum += store[i];
            if (mx < sum) {
                mx = sum;
                idx = i;
            }
        }
        return idx;
    }
}
