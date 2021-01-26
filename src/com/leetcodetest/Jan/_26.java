package com.leetcodetest.Jan;

import java.util.Arrays;
import java.util.Comparator;

public class _26 {
    /**
     * 每日一题：2021/1/26
     * 1128. 等价多米诺骨牌对的数量
     * 给你一个由一些多米诺骨牌组成的列表 dominoes。
     * 如果其中某一张多米诺骨牌可以通过旋转 0 度或 180 度得到另一张多米诺骨牌，我们就认为这两张牌是等价的。
     * 形式上，dominoes[i] = [a, b] 和 dominoes[j] = [c, d] 等价的前提是 a==c 且 b==d，或是 a==d 且 b==c。
     * 在 0 <= i < j < dominoes.length 的前提下，找出满足 dominoes[i] 和 dominoes[j] 等价的骨牌对 (i, j) 的数量。
     * 示例：
     * 输入：dominoes = [[1,2],[2,1],[3,4],[5,6]]
     * 输出：1
     * <p>
     * 数据范围：
     * 1 <= dominoes.length <= 40000
     * 1 <= dominoes[i][j] <= 9
     */
    public int numEquivDominoPairs(int[][] dominoes) {
        int len = dominoes.length;
        int res = 0;
        if (len < 2) {
            return 0;
        }
        // 先将二维数组元素交换
        for (int[] num : dominoes) {
            if (num[0] > num[1]) {
                int temp = num[0];
                num[0] = num[1];
                num[1] = temp;
            }
        }
        // 再将原二维数组进行排序
        Arrays.sort(dominoes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                } else {
                    return o1[0] - o2[0];
                }
            }
        });
        System.out.println(Arrays.deepToString(dominoes));

        int temp = 1;
        for (int i = 1; i < len; i++) {
            if (dominoes[i][0] == dominoes[i - 1][0] && dominoes[i][1] == dominoes[i - 1][1]) {
                temp++;
            } else {
                res += temp * (temp - 1) / 2;
                temp = 1;
            }
        }
        res += temp * (temp - 1) / 2;
        return res;
    }

    public void _21_1_26() {
        int[][] dominoes = {{2, 2}, {1, 2}, {1, 2}, {1, 1}, {1, 2}, {1, 1}, {2, 2}};
        System.out.println(numEquivDominoPairs(dominoes));

    }

}
