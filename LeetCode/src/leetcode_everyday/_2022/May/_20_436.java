package leetcode_everyday._2022.May;

import java.util.TreeMap;

/**
 * @author WuChao
 * @create 2022/5/20 8:14
 */
public class _20_436 {
    /**
     * 每日一题：2022/5/20
     * <p>
     * 436. 寻找右区间
     * <p>
     * 难度：medium
     * <p>
     * 给你一个区间数组 intervals ，其中 intervals[i] = [starti, endi] ，且每个 starti 都 不同 。
     * <p>
     * 区间 i 的 右侧区间 可以记作区间 j ，并满足 startj >= endi ，且 startj 最小化 。
     * <p>
     * 返回一个由每个区间 i 的 右侧区间 的最小起始位置组成的数组。如果某个区间 i 不存在对应的 右侧区间 ，则下标 i 处的值设为 -1 。
     *
     * <p>
     * 示例:
     * <p>
     * 输入：intervals = [[3,4],[2,3],[1,2]]
     * <p>
     * 输出：[-1,0,1]
     * <p>
     * 解释：对于 [3,4] ，没有满足条件的“右侧”区间。
     * 对于 [2,3] ，区间[3,4]具有最小的“右”起点;
     * 对于 [1,2] ，区间[2,3]具有最小的“右”起点。
     * <p>
     * 1 <= intervals.length <= 2 * 104
     * intervals[i].length == 2
     * -106 <= starti <= endi <= 106
     * 每个间隔的起点都 不相同
     */

    public int[] findRightInterval(int[][] intervals) {
        int n = intervals.length;
        int[] res = new int[n];
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            map.put(intervals[i][0], i);
        }
        for (int i = 0; i < n; i++) {
            Integer idx = map.ceilingKey(intervals[i][1]);
            if (idx == null) {
                res[i] = -1;
            } else {
                res[i] = map.get(idx);
            }
        }
        return res;

    }
}
