package leetcodetest.Jun;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WuChao
 * @create 2021/6/24 8:33
 */
public class _24 {
    /**
     * 每日一题：2021/6/24
     * 149. 直线上最多的点数
     * 难度: hard
     * <p>
     * 给你一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点。求最多有多少个点在同一条直线上。
     *
     * <p>
     * 示例
     * 输入：points = [[1,1],[2,2],[3,3]]
     * 输出：3
     *
     * 输入：points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
     * 输出：4
     * <p>
     * 数据范围：
     * 1 <= points.length <= 300
     * points[i].length == 2
     * -104 <= xi, yi <= 104
     * points 中的所有点 互不相同
     */

    /*
    题目解析：
    题意很好懂，一个朴素的求解想法就是算出任意两个的构成的直线方程，把表示一条直线的参数存储起来，根据出现方程次数最多的数再反推出有几个点都是在
    此直线上。但是这样做的时间复杂度在O(n^2)。同时有个问题是，直线斜率如何存储，考虑使用Map，而且表达上使用化简的分数形式，避免使用double
    会存在精度丢失的问题。
     */

    /*
    思路：参考官方题解
    https://leetcode-cn.com/problems/max-points-on-a-line/solution/zhi-xian-shang-zui-duo-de-dian-shu-by-le-tq8f/
     */

    public int maxPoints(int[][] points) {
        int len = points.length;
        if (len < 3) {
            return len;
        }
        int res = 0;
        for (int i = 0; i < len; i++) {
            if (res >= len - i || res > len / 2) {
                break;
            }
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = i + 1; j < len; j++) {
                int x = points[i][0] - points[j][0];
                int y = points[i][1] - points[j][1];
                if (x == 0) {
                    y = 1;
                } else if (y == 0) {
                    x = 1;
                } else {
                    if (y < 0) {
                        x = -x;
                        y = -y;
                    }
                    int gcdXY = gcd(Math.abs(x), Math.abs(y));
                    x /= gcdXY;
                    y /= gcdXY;
                }
                int key = y + x * 20001;
                map.put(key, map.getOrDefault(key, 0) + 1);
            }
            int maxn = 0;
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                int num = entry.getValue();
                maxn = Math.max(maxn, num + 1);
            }
            res = Math.max(res, maxn);

        }
        return res;


    }

    /**
     * 求两个数的最大公因数
     * @param a
     * @param b
     * @return
     */
    public static int gcd(int a, int b) {
        return b != 0 ? gcd(b, a % b) : a;
    }
}
