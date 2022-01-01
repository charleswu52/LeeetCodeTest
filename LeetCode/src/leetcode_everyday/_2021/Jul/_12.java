package leetcode_everyday._2021.Jul;

/**
 * @author WuChao
 * @create 2021/7/12 8:17
 */
public class _12 {
    /**
     * 每日一题：2021/7/12
     * 275. H 指数 II
     * 难度: medium
     * <p>
     * 给定一位研究者论文被引用次数的数组（被引用次数是非负整数），数组已经按照 升序排列 。编写一个方法，计算出研究者的 h 指数。
     * <p>
     * h 指数的定义：h 代表“高引用次数”（high citations），一名科研人员的 h 指数是指他（她）的 （N 篇论文中）
     * 总共有 h 篇论文分别被引用了至少 h 次。且其余的 N - h 篇论文每篇被引用次数 不超过 h 次。
     * <p>
     * 例如：某人的 h 指数是 20，这表示他已发表的论文中，每篇被引用了至少 20 次的论文总共有 20 篇。
     *
     * 说明:
     * 如果 h 有多有种可能的值 ，h 指数是其中最大的那个。
     *
     * 进阶：
     * 这是 H 指数 的延伸题目，本题中的 citations 数组是保证有序的。
     * 你可以优化你的算法到对数时间复杂度吗？
     * <p>
     * 示例
     * 输入: citations = [0,1,3,5,6]
     * 输出: 3
     * 解释: 给定数组表示研究者总共有 5 篇论文，每篇论文相应的被引用了 0, 1, 3, 5, 6 次。
     *      由于研究者有 3 篇论文每篇至少被引用了 3 次，其余两篇论文每篇被引用不多于 3 次，所以她的 h 指数是 3。
     * <p>
     * 限制：
     */

    /*
    与昨天 H 指数 的相似，只不过这里已经将数组 升序排序。
    因此
     */

    public int hIndex(int[] citations) {
        int len = citations.length;
        int left = 0, right = len - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (citations[mid] >= len - mid) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return len - left;
    }
}
