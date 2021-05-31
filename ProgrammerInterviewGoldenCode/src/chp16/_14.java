package chp16;

/**
 * @author WuChao
 * @since 2021/5/31 上午9:12
 */
public class _14 {
    /**
     * 程序员面试金典(version 6) -  面试题 16.14. 最佳直线
     * 难度: medium
     * <p>
     * 给定一个二维平面及平面上的 N 个点列表Points，其中第i个点的坐标为Points[i]=[Xi,Yi]。请找出一条直线，其通过的点的数目最多。
     *
     * 设穿过最多点的直线所穿过的全部点编号从小到大排序的列表为S，你仅需返回[S[0],S[1]]作为答案，若有多条直线穿过了相同数量的点，
     * 则选择S[0]值较小的直线返回，S[0]相同则选择S[1]值较小的直线返回。
     * <p>
     * 示例:
     * 输入： [[0,0],[1,1],[1,0],[2,0]]
     * 输出： [0,2]
     * 解释： 所求直线穿过的3个点的编号为[0,2,3]
     * <p>
     * 数据范围：
     * 2 <= len(Points) <= 300
     * len(Points[i]) = 2
     */

    /*
    题目解析：最近连续的几道题目都是与数学 直线方程有关
    本题是求穿过点最多的直线方程
     */
    /*
    思路：纯数学  向量共线
    利用向量共线有两大好处：
    1.避免开double而进行相对复杂的精度判断
    2.不用求直线方程，简化代码量的同时更好理解

    大致思路：
        首先先确定两个点，再加入第三个点，如果这三个点共线，则num++，不断更新maxn（一条直线穿过点的数量的最大值）
        更新时要求解的点也随之更新
     */

    public int[] bestLine(int[][] points) {
        int[] res = new int[2]; // 保存满足条件的两个点
        int num;    // 记录直线穿过点的数量
        int maxn = 0;   // 记录num的最大值
        int n = points.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                // 先确定两个点
                num = 2;
                //n个点，已经遍历了j+1点，剩下n-j-1个点。若剩下的点与当前两个点的数量n-j-1+2不大于maxn，则提前终止。
                if (n - 1 - j + 1 + 1 <= maxn) {
                    break;
                }
                long x1 = points[j][0] - points[i][0];
                long y1 = points[j][1] - points[i][1];
                // 记录前两个点的向量 (x1,y1)
                for (int k = j + 1; k < n; k++) { // 不断更新第三个点
                    long x2 = points[k][0] - points[i][0];
                    long y2 = points[k][1] - points[i][1];
                    // 第三个点与第一个点构成向量(x2,y2)
                    if (x1 * y2 == x2 * y1) {
                        num++;
                    }
                }
                if (num > maxn) {
                    maxn = num;
                    res[0] = i;
                    res[1] = j;
                }
            }
        }
        return res;


    }
}
