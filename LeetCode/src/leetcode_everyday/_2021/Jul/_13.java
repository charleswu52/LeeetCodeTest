package leetcode_everyday._2021.Jul;

import java.util.*;

/**
 * @author WuChao
 * @create 2021/7/13 7:44
 */
public class _13 {
    /**
     * 每日一题：2021/7/13
     * 218. 天际线问题
     * 难度: hard
     * https://leetcode-cn.com/problems/the-skyline-problem/
     * <p>
     * 城市的天际线是从远处观看该城市中所有建筑物形成的轮廓的外部轮廓。给你所有建筑物的位置和高度，请返回由这些建筑物形成的 天际线 。
     *
     * 每个建筑物的几何信息由数组 buildings 表示，其中三元组 buildings[i] = [lefti, righti, heighti] 表示：
     *
     * lefti 是第 i 座建筑物左边缘的 x 坐标。
     * righti 是第 i 座建筑物右边缘的 x 坐标。
     * heighti 是第 i 座建筑物的高度。
     *
     * 天际线 应该表示为由 “关键点” 组成的列表，格式 [[x1,y1],[x2,y2],...] ，并按 x 坐标 进行 排序 。关键点是水平线段的左端点。
     * 列表中最后一个点是最右侧建筑物的终点，y 坐标始终为 0 ，仅用于标记天际线的终点。
     * 此外，任何两个相邻建筑物之间的地面都应被视为天际线轮廓的一部分。
     *
     * 注意：输出天际线中不得有连续的相同高度的水平线。例如 [...[2 3], [4 5], [7 5], [11 5], [12 7]...] 是不正确的答案；
     * 三条高度为 5 的线应该在最终输出中合并为一个：[...[2 3], [4 5], [12 7], ...]
     *
     * <p>
     * 示例
     * 输入：buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
     * 输出：[[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
     * 解释：
     * 图 A 显示输入的所有建筑物的位置和高度，
     * 图 B 显示由这些建筑物形成的天际线。图 B 中的红点表示输出列表中的关键点。
     *
     * <p>
     * 限制：
     */


    /*
    题目解析：看图比较好懂 但是之前没有做过这一类的题目
    难度比较大 题解写的不好理解

    思路：扫描线 + 优先队列
    https://leetcode-cn.com/problems/the-skyline-problem/solution/tian-ji-xian-wen-ti-by-leetcode-solution-ozse/

     */

    public List<List<Integer>> getSkyline(int[][] buildings) {
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((o1, o2) -> o2[1] - o1[1]);
        // 保存每个建筑物的横坐标
        List<Integer> boundaries = new ArrayList<>();
        for (int[] building : buildings) {
            boundaries.add(building[0]);
            boundaries.add(building[1]);
        }
        // 排序
        Collections.sort(boundaries);

        List<List<Integer>> res = new ArrayList<>();
        int n = buildings.length, idx = 0;
        for (int boundary : boundaries) {
            while (idx < n && buildings[idx][0] <= boundary) {
                pq.offer(new int[]{buildings[idx][1], buildings[idx][2]});
                idx++;
            }
            while (!pq.isEmpty() && pq.peek()[0] <= boundary) {
                pq.poll();
            }

            int maxn = pq.isEmpty() ? 0 : pq.peek()[1];
            if (res.size() == 0 || maxn != res.get(res.size() - 1).get(1)) {
                res.add(Arrays.asList(boundary, maxn));
            }
        }
        return res;
    }
}
