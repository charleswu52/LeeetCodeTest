package leetcode_hot100.top40;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author WuChao
 * @create 2021/7/10 10:14
 */
public class _56 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 56. 合并区间
     * 难度：medium
     * <p>
     * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
     * 请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
     * <p>
     * 示例
     * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
     * 输出：[[1,6],[8,10],[15,18]]
     * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
     *
     * 输入：intervals = [[1,4],[4,5]]
     * 输出：[[1,5]]
     * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
     * <p>
     * 范围
     * 1 <= intervals.length <= 104
     * intervals[i].length == 2
     * 0 <= starti <= endi <= 104
     */

    public int[][] merge(int[][] intervals) {
        int len = intervals.length;
        if (len < 2) {
            return intervals;
        }
        // 先排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                }
                return o1[0]-o2[0];
            }
        });
        List<int[]> store = new ArrayList<>();
        store.add(intervals[0]);
        for (int i = 1; i < len; i++) {
            int a = intervals[i][0], b = intervals[i][1];
            int preA = store.get(store.size() - 1)[0];
            int preB = store.get(store.size() - 1)[1];
            if (a >= preA && a <= preB) {
                a = preA;
            }
            if (b <= preB) {
                b = preB;
            }
            if (a > preB) {
                store.add(new int[]{a, b});
            } else {
                store.remove(store.size() - 1);
                store.add(new int[]{a, b});
            }
        }
        int size = store.size();
        int[][] res = new int[size][];
        for (int i = 0; i < size; i++) {
            res[i] = store.get(i);
        }
        return res;

    }

    @Test
    public void test() {
        int[][] nums = {{1, 4}, {0, 2}, {3, 5}};
        System.out.println(Arrays.deepToString(merge(nums)));
    }

}
