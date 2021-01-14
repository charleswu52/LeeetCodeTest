package com.leetcodetest.Jan;

import java.util.ArrayList;
import java.util.List;

public class _14 {
    /**
     * 每日一题：2021/1/14
     * 可被 5 整除的二进制前缀
     * 难度： easy
     * 给定由若干 0 和 1 组成的数组 A。我们定义 N_i：从 A[0] 到 A[i] 的第 i 个子数组被解释为一个二进制数（从最高有效位到最低有效位）。
     * <p>
     * 返回布尔值列表 answer，只有当 N_i 可以被 5 整除时，答案 answer[i] 为 true，否则为 false。
     */


    public List<Boolean> prefixesDivBy5(int[] A) {
        List<Boolean> res = new ArrayList<Boolean>();
        int cur = 0;
        for (int i = 0; i < A.length; i++) {
            cur = (cur * 2 + A[i]) % 5;// 为了防止溢出，每次计算只保留余数即可
            res.add(cur == 0 ? true : false);
        }
        return res;

    }

    public void _21_1_14() {
        int[] A = {0, 1, 1, 1, 1, 1};

    }


}
