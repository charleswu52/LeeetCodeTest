package leetcode_everyday._2022.Jul;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author WuChao
 * @create 2022/7/13 11:11
 */
public class _13_735 {
    /**
     * 每日一题：2022/7/13
     * <p>
     * 735. 行星碰撞
     * <p>
     * 难度：medium
     * <p>
     * 给定一个整数数组 asteroids，表示在同一行的行星。
     *
     * 对于数组中的每一个元素，其绝对值表示行星的大小，正负表示行星的移动方向（正表示向右移动，负表示向左移动）。每一颗行星以相同的速度移动。
     *
     * 找出碰撞后剩下的所有行星。碰撞规则：两个行星相互碰撞，较小的行星会爆炸。如果两颗行星大小相同，则两颗行星都会爆炸。两颗移动方向相同的行星，永远不会发生碰撞。
     * <p>
     * 示例
     * <p>
     * 输入：asteroids = [5,10,-5]
     *
     * 输出：[5,10]
     *
     * 解释：10 和 -5 碰撞后只剩下 10 。 5 和 10 永远不会发生碰撞。
     * <p>
     * 范围
     * <p>
     * 2 <= asteroids.length <= 10%4
     * -1000 <= asteroids[i] <= 1000
     * asteroids[i] != 0
     */

    /*
    思路： 栈模拟
     */
    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (int asteroid : asteroids) {
            boolean ok = true;
            while (ok && !stack.isEmpty() && stack.peekLast() > 0 && asteroid < 0) {
                int a = stack.peekLast(), b = -asteroid;
                if (a <= b) {
                    stack.pollLast();
                }
                if (a >= b) {
                    ok = false;
                }
            }
            if (ok) {
                stack.addLast(asteroid);

            }
        }
        int size = stack.size();
        int[] res = new int[size];
        while (!stack.isEmpty()) {
            res[--size] = stack.pollLast();
        }
        return res;


    }
}
