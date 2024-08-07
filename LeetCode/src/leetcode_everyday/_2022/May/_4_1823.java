package leetcode_everyday._2022.May;

import org.junit.Test;

/**
 * @author WuChao
 * @create 2022/5/4 9:54
 */
public class _4_1823 {
    /**
     * 每日一题：2022/5/4
     * <p>
     * 1823. 找出游戏的获胜者
     * <p>
     * 难度：medium
     * <p>
     * 共有 n 名小伙伴一起做游戏。小伙伴们围成一圈，按 顺时针顺序 从 1 到 n 编号。确切地说，
     * 从第 i 名小伙伴顺时针移动一位会到达第 (i+1) 名小伙伴的位置，其中 1 <= i < n ，从第 n 名小伙伴顺时针移动一位会回到第 1 名小伙伴的位置。
     * <p>
     * 游戏遵循如下规则：
     * <p>
     * 从第 1 名小伙伴所在位置 开始 。
     * 沿着顺时针方向数 k 名小伙伴，计数时需要 包含 起始时的那位小伙伴。逐个绕圈进行计数，一些小伙伴可能会被数过不止一次。
     * 你数到的最后一名小伙伴需要离开圈子，并视作输掉游戏。
     * 如果圈子中仍然有不止一名小伙伴，从刚刚输掉的小伙伴的 顺时针下一位 小伙伴 开始，回到步骤 2 继续执行。
     * 否则，圈子中最后一名小伙伴赢得游戏。
     * 给你参与游戏的小伙伴总数 n ，和一个整数 k ，返回游戏的获胜者。
     * <p>
     * 示例
     * <p>
     * 输入：n = 5, k = 2
     * <p>
     * 输出：3
     * <p>
     * 解释：游戏运行步骤如下：
     * 1) 从小伙伴 1 开始。
     * 2) 顺时针数 2 名小伙伴，也就是小伙伴 1 和 2 。
     * 3) 小伙伴 2 离开圈子。下一次从小伙伴 3 开始。
     * 4) 顺时针数 2 名小伙伴，也就是小伙伴 3 和 4 。
     * 5) 小伙伴 4 离开圈子。下一次从小伙伴 5 开始。
     * 6) 顺时针数 2 名小伙伴，也就是小伙伴 5 和 1 。
     * 7) 小伙伴 1 离开圈子。下一次从小伙伴 3 开始。
     * 8) 顺时针数 2 名小伙伴，也就是小伙伴 3 和 5 。
     * 9) 小伙伴 5 离开圈子。只剩下小伙伴 3 。所以小伙伴 3 是游戏的获胜者。
     * <p>
     * 范围
     * <p>
     * 1 <= k <= n <= 500
     */

    /*
    思路：模拟 解决 约瑟夫环问题
     */
    public int findTheWinner1(int n, int k) {
        boolean[] check = new boolean[n];
        int remove = 0;
        int count = 0;
        while (true) {
            for (int i = 0; i < n; i++) {
                count++;
                if (!check[i] && count <= k) {
                    if (remove == n - 1) {
                        return i + 1;
                    }
                    if (count == k) {
                        check[i] = true;
                        remove++;
                        count = 0;
                    }
                } else if (count > k) {
                    count = 0;
                    break;
                } else {
                    count--;
                    continue;
                }
            }
        }
    }

    /*
    思路2： 递归

     */

    public int findTheWinner(int n, int k) {
        if (n <= 1) {
            return n;
        }
        int ans = (findTheWinner(n - 1, k) + k) % n;
        return ans == 0 ? n : ans;
    }


        @Test
    public void test() {
        int n = 6, k = 5;
        System.out.println(findTheWinner(n, k));
    }
}
