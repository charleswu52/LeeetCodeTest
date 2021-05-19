package chp8;

import java.util.List;

/**
 * @author WuChao
 * @since 2021/5/19 上午9:07
 */
public class _6 {
    /**
     * 程序员面试金典(version 6) - 面试题 08.06. 汉诺塔问题
     * 难度: easy
     * <p>
     * 在经典汉诺塔问题中，有 3 根柱子及 N 个不同大小的穿孔圆盘，盘子可以滑入任意一根柱子。一开始，所有盘子自上而下按升序依次套在第一根柱子上(即每一个盘子只能放在更大的盘子上面)。移动圆盘时受到以下限制:
     * (1) 每次只能移动一个盘子;
     * (2) 盘子只能从柱子顶端滑出移到下一根柱子;
     * (3) 盘子只能叠在比它大的盘子上。
     *
     * 请编写程序，用栈将所有盘子从第一根柱子移到最后一根柱子。
     *
     * 你需要原地修改栈。
     *
     * <p>
     * 示例:
     * 输入：A = [2, 1, 0], B = [], C = []
     * 输出：C = [2, 1, 0]
     * <p>
     * 输入：A = [1, 0], B = [], C = []
     * 输出：C = [1, 0]
     * <p>
     * 数据范围：
     * A中盘子的数目不大于14个。
     */

    /*
    题目解析：经典的递归与分治的题目
    当n=1的时候，直接将盘子从A移动到C；
    当n>1的的时候，
        先把最上面的n-1个盘子从A移动到B（子问题，递归）；
        再将最大的盘子从A移动到C；
        再将B上n-1个盘子从B移动到C（子问题，递归）。
     */
    public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        move(A.size(), A, B, C);

    }

    public void move(int n, List<Integer> A, List<Integer> B, List<Integer> C) {
        if (n == 1) {
            C.add(A.remove(A.size() - 1));
            return;
        } else {
            move(n - 1, A, C, B);//递归： 将A上面n-1个通过C移到B
            C.add(A.remove(A.size() - 1));// 将A的最后一个移动到C
            move(n - 1, B, A, C); // 将B上面n-1通过空的A移动到C
        }

    }
}
