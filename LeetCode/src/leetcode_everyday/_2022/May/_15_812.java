package leetcode_everyday._2022.May;

/**
 * @author WuChao
 * @create 2022/5/15 9:24
 */
public class _15_812 {
    /**
     * 每日一题：2022/5/15
     * <p>
     * 812. 最大三角形面积
     * <p>
     * 难度：easy
     * <p>
     * 给定包含多个点的集合，从其中取三个点组成三角形，返回能组成的最大三角形的面积。
     * <p>
     * 示例:
     * <p>
     * 输入: points = [[0,0],[0,1],[1,0],[0,2],[2,0]]
     * <p>
     * 输出: 2
     * <p>
     * 解释:
     * 这五个点如下图所示。组成的橙色三角形是最大的，面积为2。
     * <p>
     * 3 <= points.length <= 50.
     * 不存在重复的点。
     * -50 <= points[i][j] <= 50.
     * 结果误差值在 10^-6以内都认为是正确答案。
     */

    /*
    思路1：几何数学
    使用 鞋带公式 计算
     */
    public double largestTriangleArea1(int[][] points) {
        double res = 0;
        for (int i = 0; i < points.length - 2; i++) {
            for (int j = i + 1; j < points.length - 1; j++) {
                for (int k = j + 1; k < points.length; k++) {
                    double a = points[i][0] * points[j][1] + points[j][0] * points[k][1] + points[k][0] * points[i][1];
                    double b = points[i][1] * points[j][0] + points[j][1] * points[k][0] + points[k][1] * points[i][0];
                    double cur = 0.5 * Math.abs(a - b);
                    res = Math.max(res, cur);
                }
            }
        }
        return res;
    }

    /*
    思路2：几何数学
    使用 海伦公式 计算
     */
    public double largestTriangleArea2(int[][] points) {
        double res = 0;
        for (int i = 0; i < points.length - 2; i++) {
            for (int j = i + 1; j < points.length - 1; j++) {
                for (int k = j + 1; k < points.length; k++) {
                    double a = Math.sqrt(Math.pow(Math.abs(points[j][0] - points[i][0]), 2) + Math.pow(Math.abs(points[j][1] - points[i][1]), 2));
                    double b = Math.sqrt(Math.pow(Math.abs(points[k][0] - points[j][0]), 2) + Math.pow(Math.abs(points[k][1] - points[j][1]), 2));
                    double c = Math.sqrt(Math.pow(Math.abs(points[i][0] - points[k][0]), 2) + Math.pow(Math.abs(points[i][1] - points[k][1]), 2));
                    double l = 0.5 * (a + b + c);
                    // Java 有异常 注意取绝对值
                    double cur = Math.sqrt(l * Math.abs(l - a) * Math.abs(l - b) * Math.abs(l - c));
                    res = Math.max(res, cur);
                }
            }
        }
        return res;
    }

    /*
   思路3：几何数学
   使用 三角形面积公式 计算
    */
    public double largestTriangleArea3(int[][] points) {
        double res = 0;
        for (int i = 0; i < points.length - 2; i++) {
            for (int j = i + 1; j < points.length - 1; j++) {
                for (int k = j + 1; k < points.length; k++) {
                    double a = Math.sqrt(Math.pow(Math.abs(points[j][0] - points[i][0]), 2) + Math.pow(Math.abs(points[j][1] - points[i][1]), 2));
                    double b = Math.sqrt(Math.pow(Math.abs(points[k][0] - points[j][0]), 2) + Math.pow(Math.abs(points[k][1] - points[j][1]), 2));
                    double c = Math.sqrt(Math.pow(Math.abs(points[i][0] - points[k][0]), 2) + Math.pow(Math.abs(points[i][1] - points[k][1]), 2));
                    double cos = (a * a + b * b - c * c) / (2 * a * b);
                    double sin = Math.sqrt(Math.abs(1.0 - cos * cos));
                    // Java 有异常 注意取绝对值
                    double cur = 0.5 * a * b * Math.abs(sin);
                    res = Math.max(res, cur);
                }
            }
        }
        return res;
    }
}

