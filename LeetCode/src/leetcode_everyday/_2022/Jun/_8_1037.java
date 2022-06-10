package leetcode_everyday._2022.Jun;

/**
 * @Author CharlesWu
 * @Create 2022/6/10 12:10
 * @Version 1.0
 * @Description
 * @Note
 */
public class _8_1037 {
    /**
     * 每日一题：2022/6/8
     * 1037. 有效的回旋镖
     * 难度: easy
     * <p>
     * 给定一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点，如果这些点构成一个 回旋镖 则返回 true 。
     * <p>
     * 回旋镖 定义为一组三个点，这些点 各不相同 且 不在一条直线上 。
     * <p>
     * 示例:
     * <p>
     * 输入：points = [[1,1],[2,3],[3,2]]
     * <p>
     * 输出：true
     * <p>
     * 数据范围：
     * <p>
     * points.length == 3
     * points[i].length == 2
     * 0 <= xi, yi <= 100
     */

    /*
    思路：几何数学
     */
    public boolean isBoomerang(int[][] points) {
        int x1 = points[1][0] - points[0][0];
        int y1 = points[1][1] - points[0][1];
        int x2 = points[2][0] - points[1][0];
        int y2 = points[2][1] - points[1][1];
        return x1 * y2 - x2 * y1 != 0;
    }


}
