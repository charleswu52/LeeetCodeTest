package leetcode_everyday._2022.Apr;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WuChao
 * @create 2022/4/23 9:00
 */
public class _23_587 {
    /**
     * 每日一题：2022/4/23
     * <p>
     * 587. 安装栅栏
     * <p>
     * 难度：hard
     * <p>
     * 在一个二维的花园中，有一些用 (x, y) 坐标表示的树。由于安装费用十分昂贵，你的任务是先用最短的绳子围起所有的树。
     * 只有当所有的树都被绳子包围时，花园才能围好栅栏。你需要找到正好位于栅栏边界上的树的坐标。
     * <p>
     * 生成的测试用例让答案符合 32 位 整数。
     * <p>
     * 示例
     * <p>
     * 输入: [[1,1],[2,2],[2,0],[2,4],[3,3],[4,2]]
     * <p>
     * 输出: [[1,1],[2,0],[4,2],[3,3],[2,4]]
     * <p>
     * 范围
     * <p>
     * 所有的树应当被围在一起。你不能剪断绳子来包围树或者把树分成一组以上。
     * 输入的整数在 0 到 100 之间。
     * 花园至少有一棵树。
     * 所有树的坐标都是不同的。
     * 输入的点没有顺序。输出顺序也没有要求。
     */
    /*
    思路：计算机图形学中的凸包问题
    https://leetcode-cn.com/problems/erect-the-fence/solution/an-zhuang-zha-lan-by-leetcode-solution-75s3/
     */
    public int[][] outerTrees(int[][] trees) {
        int n = trees.length;
        if (n < 4) {
            return trees;
        }
        int leftMost = 0;
        for (int i = 0; i < n; i++) {
            if (trees[i][0] < trees[leftMost][0]) {
                leftMost = i;
            }
        }

        List<int[]> res = new ArrayList<int[]>();
        boolean[] visit = new boolean[n];
        int p = leftMost;
        do {
            int q = (p + 1) % n;
            for (int r = 0; r < n; r++) {
                /* 如果 r 在 pq 的右侧，则 q = r */
                if (cross(trees[p], trees[q], trees[r]) < 0) {
                    q = r;
                }
            }
            /* 是否存在点 i, 使得 p 、q 、i 在同一条直线上 */
            for (int i = 0; i < n; i++) {
                if (visit[i] || i == p || i == q) {
                    continue;
                }
                if (cross(trees[p], trees[q], trees[i]) == 0) {
                    res.add(trees[i]);
                    visit[i] = true;
                }
            }
            if (!visit[q]) {
                res.add(trees[q]);
                visit[q] = true;
            }
            p = q;
        } while (p != leftMost);
        return res.toArray(new int[][]{});
    }

    public int cross(int[] p, int[] q, int[] r) {
        return (q[0] - p[0]) * (r[1] - q[1]) - (q[1] - p[1]) * (r[0] - q[0]);
    }

}
