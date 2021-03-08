package leetcodetest.Feb;

import java.util.ArrayList;
import java.util.List;

public class _12 {
    /**
     * 每日一题：2021/2/12
     * 119. 杨辉三角 II
     * 难度: easy
     * 定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
     * 在杨辉三角中，每个数是它左上方和右上方的数的和。
     * <p>
     * 示例：
     * 输入: 3
     * 输出: [1,3,3,1]
     * <p>
     * 数据范围：
     */

    public List<Integer> getRow(int rowIndex) {
        int[][] store= new int[rowIndex+1][rowIndex+1];
        for (int i = 0; i <= rowIndex; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    store[i][j] = 1;
                } else {
                    store[i][j] = store[i - 1][j - 1] + store[i - 1][j];
                }
            }
        }
//        System.out.println(Arrays.deepToString(store));
        List<Integer> res = new ArrayList<Integer>();
        for (int num : store[rowIndex]) {
            res.add(num);
        }
//        System.out.println(res);
        return res;

     }

    public void _21_2_12() {
        int k = 33;
        System.out.println(getRow(k));

    }
}
