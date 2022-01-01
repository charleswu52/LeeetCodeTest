package leetcode_everyday._2021.Jan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class _22 {
    /**
     * 每日一题：2021/1/22
     * 989. 数组形式的整数加法
     * 难度： easy
     * 对于非负整数 X 而言，X 的数组形式是每位数字按从左到右的顺序形成的数组。例如，如果 X = 1231，那么其数组形式为 [1,2,3,1]。
     * 给定非负整数 X 的数组形式 A，返回整数 X+K 的数组形式。
     * <p>
     * 示例：
     * 输入：A = [1,2,0,0], K = 34
     * 输出：[1,2,3,4]
     * 解释：1200 + 34 = 1234
     * <p>
     * 数据范围：
     * 1. 1 <= A.length <= 10000
     * 2. 0 <= A[i] <= 9
     * 3. 0 <= K <= 10000
     * 4. 如果 A.length > 1，那么 A[0] != 0
     */

    /**
     * 思路：简单题，逐位相加即可
     * @param A
     * @param K
     * @return
     */
    public List<Integer> addToArrayForm(int[] A, int K) {
        int n = A.length;
        int sum = 0;
        List<Integer> res = new ArrayList<>();
        for (int i = n - 1; i >= 0; i--) {
            int add = 0;
            if (K != 0) {
                add = K % 10;
                K /= 10;
            }
            sum += A[i] + add;
            res.add(sum % 10);
            sum /= 10;
        }
        while (K != 0) {
            sum += K % 10;
            K /= 10;
            res.add(sum % 10);
            sum /= 10;
        }

        if (sum != 0) {
            res.add(sum);
        }

        Collections.reverse(res);
        return res;
    }

    public void _21_1_22() {
        int[] A = {2, 7, 5};
        int k = 1811;
        System.out.println(addToArrayForm(A, k));

    }
}
