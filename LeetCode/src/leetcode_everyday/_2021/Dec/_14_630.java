package leetcode_everyday._2021.Dec;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author WuChao
 * @create 2021/12/14 上午8:55
 */
public class _14_630 {
    /**
     * 每日一题：2021/12/14
     * <p>
     * 630. 课程表 III
     * <p>
     * 难度：hard
     * <p>
     * 这里有 n 门不同的在线课程，按从 1 到 n 编号。给你一个数组 courses ，其中 courses[i] = [duration_i, lastDay_i]
     * 表示第 i 门课将会 持续 上 duration_i 天课，并且必须在不晚于 lastDay_i 的时候完成。
     *
     * 你的学期从第 1 天开始。且不能同时修读两门及两门以上的课程。
     *
     * 返回你最多可以修读的课程数目。
     *
     * <p>
     * 示例 1：
     * <p>
     * 例子：
     * 输入：courses = [[100, 200], [200, 1300], [1000, 1250], [2000, 3200]]
     * 输出：3
     * 解释：
     * 这里一共有 4 门课程，但是你最多可以修 3 门：
     * 首先，修第 1 门课，耗费 100 天，在第 100 天完成，在第 101 天开始下门课。
     * 第二，修第 3 门课，耗费 1000 天，在第 1100 天完成，在第 1101 天开始下门课程。
     * 第三，修第 2 门课，耗时 200 天，在第 1300 天完成。
     * 第 4 门课现在不能修，因为将会在第 3300 天完成它，这已经超出了关闭日期。
     *
     * <p>
     * 范围
     * <p>
     * 1 <= courses.length <= 10^4
     * 1 <= duration_i, lastDay_i <= 10^4
     */

    /*
    思路：贪心 + 优先队列

    对关闭时间 d 进行升序排序，再依次挑选课程并按照顺序进行学习。

    */
    public int scheduleCourse(int[][] courses) {
        // 先对课程的截止时间进行排序
        Arrays.sort(courses, (o1, o2) -> o1[1] - o2[1]);

        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        // 优先队列中所有课程的时间总和
        int total = 0;

        for (int[] course : courses) {
            int ti = course[0], di = course[1];
            if (total + ti <= di) {
                total += ti;
                queue.offer(ti);
            } else if (!queue.isEmpty() && queue.peek() > ti) {
                total -= queue.poll() - ti;
                queue.offer(ti);
            }
        }
        return queue.size();

    }
}
