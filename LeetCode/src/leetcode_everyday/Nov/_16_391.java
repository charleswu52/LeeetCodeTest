package leetcode_everyday.Nov;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author WuChao
 * @create 2021/11/17 10:03
 */
public class _16_391 {
    /**
     * 每日一题：2021/11/16
     * <p>
     * 391. 完美矩形
     * <p>
     * 难度：hard
     * <p>
     * 给你一个数组 rectangles ，其中 rectangles[i] = [xi, yi, ai, bi] 表示一个坐标轴平行的矩形。
     * 这个矩形的左下顶点是 (xi, yi) ，右上顶点是 (ai, bi) 。
     *
     * 如果所有矩形一起精确覆盖了某个矩形区域，则返回 true ；否则，返回 false 。
     * <p>
     * 示例 1：
     * <p>
     * 输入：rectangles = [[1,1,3,3],[3,1,4,2],[3,2,4,4],[1,3,2,4],[2,3,3,4]]
     *
     * 输出：true
     *
     * 解释：5 个矩形一起可以精确地覆盖一个矩形区域。
     * <p>
     *
     * <p>
     * 范围
     * <p>
     * 1 <= rectangles.length <= 2 * 10^4
     * rectangles[i].length == 4
     * -10^5 <= xi, yi, ai, bi <= 10^5
     */

    /*
    思路：哈希表
    精确覆盖意味着：
    矩形区域中不能有空缺，即矩形区域的面积等于所有矩形的面积之和；
    矩形区域中不能有相交区域。
    我们需要一个统计量来判定是否存在相交区域。由于精确覆盖意味着矩形的边和顶点会重合在一起，我们不妨统计每个矩形顶点的出现次数。
    同一个位置至多只能存在四个顶点，在满足该条件的前提下，如果矩形区域中有相交区域，这要么导致矩形区域四角的顶点出现不止一次，
    要么导致非四角的顶点存在出现一次或三次的顶点；

    因此要满足精确覆盖，除了要满足矩形区域的面积等于所有矩形的面积之和，还要满足矩形区域四角的顶点只能出现一次，且其余顶点的出现次数只能是两次或四次。

    在代码实现时，我们可以遍历矩形数组，计算矩形区域四个顶点的位置，以及矩形面积之和，并用哈希表统计每个矩形的顶点的出现次数。
    遍历完成后，检查矩形区域的面积是否等于所有矩形的面积之和，以及每个顶点的出现次数是否满足上述要求。
     */

    public boolean isRectangleCover(int[][] rectangles) {
        long allArea = 0;
        int minX = rectangles[0][0], minY = rectangles[0][1], maxX = rectangles[0][2], maxY = rectangles[0][3];
        Map<Point, Integer> count = new HashMap<>();
        for (int[] rectangle : rectangles) {
            int x = rectangle[0], y = rectangle[1], a = rectangle[2], b = rectangle[3];
            allArea += (long) (a - x) * (b - y);

            minX = Math.min(minX, x);
            minY = Math.min(minY, y);
            maxX = Math.max(maxX, a);
            maxY = Math.max(maxY, b);

            Point p1 = new Point(x, y);
            Point p2 = new Point(x, b);
            Point p3 = new Point(a, y);
            Point p4 = new Point(a, b);

            count.put(p1, count.getOrDefault(p1, 0) + 1);
            count.put(p2, count.getOrDefault(p2, 0) + 1);
            count.put(p3, count.getOrDefault(p3, 0) + 1);
            count.put(p4, count.getOrDefault(p4, 0) + 1);
        }

        Point pointMinMin = new Point(minX, minY);
        Point pointMinMax = new Point(minX, maxY);
        Point pointMaxMin = new Point(maxX, minY);
        Point pointMaxMax = new Point(maxX, maxY);

        if (allArea != (long) (maxX - minX) * (maxY - minY) ||
                count.getOrDefault(pointMinMin, 0) != 1 ||
                count.getOrDefault(pointMinMax, 0) != 1 ||
                count.getOrDefault(pointMaxMin, 0) != 1 ||
                count.getOrDefault(pointMaxMax, 0) != 1) {
            return false;
        }

        count.remove(pointMinMin);
        count.remove(pointMinMax);
        count.remove(pointMaxMin);
        count.remove(pointMaxMax);

        for (Map.Entry<Point, Integer> entry : count.entrySet()) {
            Integer value = entry.getValue();
            if (value != 2 && value != 4) {
                return false;
            }
        }
        return true;


    }

    class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
