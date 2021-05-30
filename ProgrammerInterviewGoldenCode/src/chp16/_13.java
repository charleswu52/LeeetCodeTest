package chp16;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author WuChao
 * @since 2021/5/30 上午9:58
 */
public class _13 {
    /**
     * 程序员面试金典(version 6) -  面试题 16.13. 平分正方形
     * 难度: medium
     * <p>
     * 给定两个正方形及一个二维平面。请找出将这两个正方形分割成两半的一条直线。假设正方形顶边和底边与 x 轴平行。
     *
     * 每个正方形的数据square包含3个数值，正方形的左下顶点坐标[X,Y] = [square[0],square[1]]，以及正方形的边长square[2]。
     * 所求直线穿过两个正方形会形成4个交点，请返回4个交点形成线段的两端点坐标（两个端点即为4个交点中距离最远的2个点，
     * 这2个点所连成的线段一定会穿过另外2个交点）。2个端点坐标[X1,Y1]和[X2,Y2]的返回格式为{X1,Y1,X2,Y2}，
     * 要求若X1 != X2，需保证X1 < X2，否则需保证Y1 <= Y2。
     *
     * 若同时有多条直线满足要求，则选择斜率最大的一条计算并返回（与Y轴平行的直线视为斜率无穷大）。
     *
     * <p>
     * 示例:
     * 输入：
     * square1 = {-1, -1, 2}
     * square2 = {0, -1, 2}
     * 输出： {-1,0,2,0}
     * 解释： 直线 y = 0 能将两个正方形同时分为等面积的两部分，返回的两线段端点为[-1,0]和[2,0]
     * <p>
     * 数据范围：
     * square.length == 3
     * square[2] > 0
     */

    /*
    题目分析：本题属于模拟题
    将一个正方形面积分成两半的方法就四种，这里要求把两个正方形都要进行均分；求直线方程，emm 与 16.03面试题考察点类似
    如果一条直线可以把一个正方形分成面经相等的两半那么该直线必过正方形的中心， 所以本题找的直线就是过两个正方形的中心形成的直线，
    分为两种情况讨论，
        如果斜率不存在可以直接得出答案，
        否则计算该直线与四条竖直线和四条水平线的交点，找到四个交点后求出距离最远的两个点即可。
     */

    public double[] cutSquares(int[] square1, int[] square2) {
        // 求出两个正方形的中心
        double centerx1 = square1[0] + (double) square1[2] / 2;
        double centery1 = square1[1] + (double) square1[2] / 2;
        double centerx2 = square2[0] + (double) square2[2] / 2;
        double centery2 = square2[1] + (double) square2[2] / 2;

        // 过两个中心点的直线与四条竖直线与四条水平线的交点
        List<double[]> doubleList = new ArrayList<>();
        double[][] h = new double[4][2];
        double dis = 0;
        int p1 = -1, p2 = -1; //dis为四个点中最远的两个点的距离， p1,p2记录对应下标
        if (centerx1 == centerx2) {
            return new double[]{centerx1, (double) Math.min(square1[1], square2[1]), centerx2, (double) Math.max(square1[1] + square1[2], square2[1] + square2[2])};
        } else {

            double k = (centery1 - centery2) / (centerx1 - centerx2);
            double b = centery1 - k * centerx1;
            //四条竖直 h[i][0]存放横坐标 h[i][1]存放纵坐标 已知横坐标计算纵坐标
            h[0][0] = square1[0];
            h[1][0] = square1[0] + square1[2];
            h[2][0] = square2[0];
            h[3][0] = square2[0] + square2[2];
            for (int i = 0; i < 4; i++) {
                h[i][1] = k * h[i][0] + b;
                if (i < 2 && h[i][1] >= square1[1] && h[i][1] <= square1[1] + square1[2]) {
                    doubleList.add(new double[]{h[i][0], h[i][1]});
                } else if (i >= 2 && h[i][1] >= square2[1] && h[i][1] <= square2[1] + square2[2]) {
                    doubleList.add(new double[]{h[i][0], h[i][1]});
                }
            }
            //四条水平 h[i][0]存放横坐标 h[i][1]存放纵坐标 已知纵坐标计算横坐标
            h[0][1] = square1[1];
            h[1][1] = square1[1] + square1[2];
            h[2][1] = square2[1];
            h[3][1] = square2[1] + square2[2];
            for (int i = 0; i < 4; i++) {
                h[i][0] = (h[i][1] - b) / k;
                if (i < 2 && h[i][0] >= square1[0] && h[i][0] <= square1[0] + square1[2]) {
                    doubleList.add(new double[]{h[i][0], h[i][1]});
                } else if (i >= 2 && h[i][0] >= square2[0] && h[i][0] <= square2[0] + square2[2]) {
                    doubleList.add(new double[]{h[i][0], h[i][1]});
                }
            }

            for (int i = 0; i < 3; i++) {
                for (int j = i + 1; j < 4; j++) {
                    long t = (long) dist(doubleList.get(i)[0], doubleList.get(i)[1], doubleList.get(j)[0], doubleList.get(j)[1]);
                    if (dis < t) {
                        dis = t;
                        p1 = i;
                        p2 = j;
                    }
                }
            }
        }
        List<double[]> res = new ArrayList<>();
        res.add(new double[]{doubleList.get(p1)[0], doubleList.get(p1)[1]});
        res.add(new double[]{doubleList.get(p2)[0], doubleList.get(p2)[1]});
        Collections.sort(res, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return (int) (o1[1] - o2[1]);
            } else {
                return (int) (o1[0] - o2[0]);
            }
        });
        return new double[]{res.get(0)[0], res.get(0)[1], res.get(1)[0], res.get(1)[1]};
    }

    public double dist(double x,double y,double x1,double y1){
        return (x - x1) * (x - x1) + (y - y1) * (y - y1);
    }


}
