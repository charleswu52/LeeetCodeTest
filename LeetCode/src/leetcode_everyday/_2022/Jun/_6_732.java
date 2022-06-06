package leetcode_everyday._2022.Jun;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Author CharlesWu
 * @Create 2022/6/6 9:18
 * @Version 1.0
 * @Description
 * @Note
 */
public class _6_732 {
    /**
     * 每日一题：2022/6/6
     * 732. 我的日程安排表 III
     * 难度: hard
     * <p>
     * 当 k 个日程安排有一些时间上的交叉时（例如 k 个日程安排都在同一时间内），就会产生 k 次预订。
     * <p>
     * 给你一些日程安排 [start, end) ，请你在每个日程安排添加后，返回一个整数 k ，表示所有先前日程安排会产生的最大 k 次预订。
     * <p>
     * 实现一个 MyCalendarThree 类来存放你的日程安排，你可以一直添加新的日程安排。
     * <p>
     * MyCalendarThree() 初始化对象。
     * int book(int start, int end) 返回一个整数 k ，表示日历中存在的 k 次预订的最大值。
     * <p>
     * 示例:
     * <p>
     * 输入：
     * ["MyCalendarThree", "book", "book", "book", "book", "book", "book"]
     * [[], [10, 20], [50, 60], [10, 40], [5, 15], [5, 10], [25, 55]]
     * 输出：
     * [null, 1, 1, 2, 3, 3, 3]
     * <p>
     * 解释：
     * MyCalendarThree myCalendarThree = new MyCalendarThree();
     * myCalendarThree.book(10, 20); // 返回 1 ，第一个日程安排可以预订并且不存在相交，所以最大 k 次预订是 1 次预订。
     * myCalendarThree.book(50, 60); // 返回 1 ，第二个日程安排可以预订并且不存在相交，所以最大 k 次预订是 1 次预订。
     * myCalendarThree.book(10, 40); // 返回 2 ，第三个日程安排 [10, 40) 与第一个日程安排相交，所以最大 k 次预订是 2 次预订。
     * myCalendarThree.book(5, 15); // 返回 3 ，剩下的日程安排的最大 k 次预订是 3 次预订。
     * myCalendarThree.book(5, 10); // 返回 3
     * myCalendarThree.book(25, 55); // 返回 3
     * <p>
     * 数据范围：
     * <p>
     * 0 <= start < end <= 10^9
     * 每个测试用例，调用 book 函数最多不超过 400次
     */

    /*
    思路1：差分数组
     */
    class MyCalendarThree {
        TreeMap<Integer, Integer> cnt;

        public MyCalendarThree() {
            cnt = new TreeMap<>();
        }

        public int book(int start, int end) {
            int ans = 0;
            int maxBook = 0;
            cnt.put(start, cnt.getOrDefault(start, 0) + 1);
            cnt.put(end, cnt.getOrDefault(end, 0) - 1);
            for (Map.Entry<Integer, Integer> entry : cnt.entrySet()) {
                int freq = entry.getValue();
                maxBook += freq;
                ans = Math.max(maxBook, ans);
            }
            return ans;

        }
    }

    /*
    思路2：线段树
     */

    class MyCalendarThree2 {
        Map<Integer, Integer> tree;
        Map<Integer, Integer> lazy;

        public MyCalendarThree2() {
            tree = new HashMap<>();
            lazy = new HashMap<>();
        }

        public int book(int start, int end) {
            update(start, end - 1, 0, 1000000000, 1);
            return tree.getOrDefault(1, 0);
        }

        public void update(int start, int end, int l, int r, int idx) {
            if (r < start || end < l) {
                return;
            }
            if (start <= l && r <= end) {
                tree.put(idx, tree.getOrDefault(idx, 0) + 1);
                lazy.put(idx, lazy.getOrDefault(idx, 0) + 1);
            } else {
                int mid = (l + r) >> 1;
                update(start, end, l, mid, 2 * idx);
                update(start, end, mid + 1, r, 2 * idx + 1);
                tree.put(idx, lazy.getOrDefault(idx, 0) + Math.max(tree.getOrDefault(2 * idx, 0), tree.getOrDefault(2 * idx + 1, 0)));
            }
        }
    }

}
