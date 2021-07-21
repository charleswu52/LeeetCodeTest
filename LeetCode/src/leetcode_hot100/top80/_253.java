package leetcode_hot100.top80;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author WuChao
 * @create 2021/7/21 8:35
 */
public class _253 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 253. 会议室 II
     * 难度：medium
     * <p>
     * 给你一个会议时间安排的数组 intervals ，每个会议时间都会包括开始和结束的时间 intervals[i] = [starti, endi] ，
     * 为避免会议冲突，同时要考虑充分利用会议室资源，请你计算至少需要多少间会议室，才能满足这些会议安排。
     *
     * <p>
     * 示例 1：
     * 输入：intervals = [[0,30],[5,10],[15,20]]
     * 输出：2
     * <p>
     * 示例 2：
     * 输入：intervals = [[7,10],[2,4]]
     * 输出：1
     * <p>
     * 数据范围
     * 1 <= intervals.length <= 104
     * 0 <= starti < endi <= 106
     */

    /*
    思路：优先队列（最小堆）
    1. 按照 开始时间 对会议进行排序。
    2. 初始化一个新的 最小堆，将第一个会议的结束时间加入到堆中。我们只需要记录会议的结束时间，告诉我们什么时候房间会空。
    3. 对每个会议，检查堆的最小元素（即堆顶部的房间）是否空闲。
        若房间空闲，则从堆顶拿出该元素，将其改为我们处理的会议的结束时间，加回到堆中。
        若房间不空闲。开新房间，并加入到堆中。
    4. 处理完所有会议后，堆的大小即为开的房间数量。这就是容纳这些会议需要的最小房
     */
    public int minMeetingRooms(int[][] intervals) {
        int n = intervals.length;
        if (n < 2) {
            return n;
        }
        // 按开始时间排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return  o1[0] - o2[0];
            }
        });
        // 定义最小堆
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(Integer::compareTo);
        minHeap.add(intervals[0][1]);

        for (int i = 1; i < intervals.length; i++) {
            // 有空闲会议室
            if (intervals[i][0] >= minHeap.peek()) {
                minHeap.poll();
            }
            minHeap.add(intervals[i][1]);

        }
        return minHeap.size();
    }

    @Test
    public void test() {
        int[][] ints = {{9,10},{4,9},{4,17}};
        System.out.println(minMeetingRooms(ints));
    }
}
