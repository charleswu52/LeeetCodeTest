package leetcodetest.Feb;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class _1 {
    /**
     * 每日一题：2021/2/1
     * 888. 公平的糖果棒交换
     * 难度: easy
     * 爱丽丝和鲍勃有不同大小的糖果棒：A[i] 是爱丽丝拥有的第 i 根糖果棒的大小，B[j] 是鲍勃拥有的第 j 根糖果棒的大小。
     * 因为他们是朋友，所以他们想交换一根糖果棒，这样交换后，他们都有相同的糖果总量。（一个人拥有的糖果总量是他们拥有的糖果棒大小的总和。）
     * 返回一个整数数组 ans，其中 ans[0] 是爱丽丝必须交换的糖果棒的大小，ans[1]是 Bob 必须交换的糖果棒的大小。
     * 如果有多个答案，你可以返回其中任何一个。保证答案存在。
     * <p>
     * 示例：
     * 输入：A = [1,1], B = [2,2]
     * 输出：[1,2]
     * <p>
     * 数据范围：
     * 1 <= A.length <= 10000
     * 1 <= B.length <= 10000
     * 1 <= A[i] <= 100000
     * 1 <= B[i] <= 100000
     * 保证爱丽丝与鲍勃的糖果总量不同。
     * 答案肯定存在。
     */

    /*
    题目解析
    问题比较易懂，两个数组，要求交换两个数组中的一对数，使得交换后的两个数组的数值总和相等
    将符合条件的数值对作为结果返回
     */
    public int[] fairCandySwap(int[] A, int[] B) {
        int sumA = Arrays.stream(A).sum();
        int sumB = Arrays.stream(B).sum();
        int minus = Math.abs(sumA - sumB) / 2;
        Arrays.sort(A);
        Arrays.sort(B);
        int[] res = new int[2];
        if (sumA < sumB) {
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < B.length; j++) {
                    if (B[j] - A[i] == minus) {
                        res[0] = A[i];
                        res[1] = B[j];
                        return res;
                    }
                }
            }
        } else if (sumA > sumB) {
            for (int i = 0; i < B.length; i++) {
                for (int j = 0; j < A.length; j++) {
                    if (A[j] - B[i] == minus) {
                        res[0] = A[j];
                        res[1] = B[i];
                        return res;
                    }
                }
            }
        } else {
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < B.length; j++) {
                    if (B[j] == A[i]) {
                        res[0] = A[i];
                        res[1] = B[j];
                        return res;
                    }
                }
            }
        }
        return res;
    }

    /*
    改进方法
    总体思路与上面类似，不过使用哈希表的方法
    对于 B 中的任意一个数 y'，只要 A 中存在一个数 x' ，满足 x' = y' + (sumA-sumB)/2 ,那么 {x',y'} 即为一组可行解。
    因此为了快速查询 A 中是否存在某个数，我们可以先将 A 中的数字存入哈希表中。然后遍历 B 序列中的数 y'，
    在哈希表中查询是否有对应的 x'。
     */
    public int[] fairCandySwap2(int[] A, int[] B) {
        int sumA = Arrays.stream(A).sum();
        int sumB = Arrays.stream(B).sum();
        int delta = (sumA - sumB) / 2;
        Set<Integer> store = new HashSet<Integer>();
        for (int num : A) {
            store.add(num);
        }
        int[] ans = new int[2];
        for (int y : B) {
            int x = y + delta;
            if (store.contains(x)) {
                ans[0] = x;
                ans[1] = y;
                break;
            }
        }
        return ans;

    }

    public void _21_2_1() {
        int[] A = {1, 1}, B = {2, 2};
        System.out.println(Arrays.toString(fairCandySwap(A, B)));
        System.out.println(Arrays.toString(fairCandySwap2(A, B)));
    }

}
