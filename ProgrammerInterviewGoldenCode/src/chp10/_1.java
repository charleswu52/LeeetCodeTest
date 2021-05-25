package chp10;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author WuChao
 * @since 2021/5/23 上午9:59
 */
public class _1 {
    /**
     * 程序员面试金典(version 6) - 面试题 10.01. 合并排序的数组
     * 难度: easy
     * <p>
     * 给定两个排序后的数组 A 和 B，其中 A 的末端有足够的缓冲空间容纳 B。 编写一个方法，将 B 合并入 A 并排序。
     *
     * 初始化 A 和 B 的元素数量分别为 m 和 n。
     *
     * <p>
     * 示例:
     * 输入:
     * A = [1,2,3,0,0,0], m = 3
     * B = [2,5,6],       n = 3
     *
     * 输出: [1,2,2,3,5,6]
     *
     *
     * <p>
     * 数据范围：
     * A.length == n + m
     */

    /*
    思路1：最简单直接的方法
     */
    public void merge(int[] A, int m, int[] B, int n) {
        if (n == 0) {
            return;
        }
        for (int i = 0, j = m; i < n; i++, j++) {
            A[j] = B[i];
        }
        Arrays.sort(A);

    }

    /*
    思路2：逆向双指针，原地修改
     */
    public void merge2(int[] A, int m, int[] B, int n) {
        int pa = m - 1, pb = n - 1;
        int tail = m + n - 1;
        int cur;
        while (pa >= 0 || pb >= 0) {
            if (pa == -1) {
                cur = B[pb--];

            } else if (pb == -1) {
                cur = A[pa--];
            } else if (A[pa] > B[pb]) {
                cur = A[pa--];
            } else {
                cur = B[pb--];
            }
            A[tail--] = cur;
        }
    }
    @Test
    public void test() {
        int[] A = {4,0,0,0,0,0};
        int m = 1;
        int[] B = {1,2,3,5,6};
        int n = 5;
        merge(A, m, B, n);
        System.out.println(Arrays.toString(A));
    }
}
