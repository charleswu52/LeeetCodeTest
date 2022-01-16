package leetcode_everyday._2022.Jan;

import java.util.Random;

/**
 * @author WuChao
 * @create 2022/1/16 10:16
 */
public class _16_382 {
    /**
     * 每日一题：2022/1/16
     * <p>
     * 382. 链表随机节点
     * <p>
     * 难度：medium
     * <p>
     * 给你一个单链表，随机选择链表的一个节点，并返回相应的节点值。每个节点 被选中的概率一样 。
     * <p>
     * 实现 Solution 类：
     * <p>
     * Solution(ListNode head) 使用整数数组初始化对象。
     * int getRandom() 从链表中随机选择一个节点并返回该节点的值。链表中所有节点被选中的概率相等。
     *
     * <p>
     * 示例 1：
     * <p>
     * 输入
     * ["Solution", "getRandom", "getRandom", "getRandom", "getRandom", "getRandom"]
     * [[[1, 2, 3]], [], [], [], [], []]
     * 输出
     * [null, 1, 3, 2, 2, 3]
     * <p>
     * 解释
     * Solution solution = new Solution([1, 2, 3]);
     * solution.getRandom(); // 返回 1
     * solution.getRandom(); // 返回 3
     * solution.getRandom(); // 返回 2
     * solution.getRandom(); // 返回 2
     * solution.getRandom(); // 返回 3
     * // getRandom() 方法应随机返回 1、2、3中的一个，每个元素被返回的概率相等。
     * <p>
     * 范围
     * <p>
     * 链表中的节点数在范围 [1, 10^4] 内
     * -10^4 <= Node.val <= 10^4
     * 至多调用 getRandom 方法 10^4 次
     *
     * 进阶：
     *
     * 如果链表非常大且长度未知，该怎么处理？
     * 你能否在不使用额外空间的情况下解决此问题？
     *
     */
    /**
     * 链表节点定义
     */
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
            this.next = null;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /*
    思路：蓄水池采样算法
    考虑不使用额外的存储空间存储每个节点的信息
    采样思路：
        从链表头开始，遍历整个链表，对遍历到的第i个节点，随机选择区间[0,i)内的一个整数，如果其等于0，则将答案置为该节点的值，否则答案不变
        默认选中第i个节点，i=0,则每次当随机数取0时就更新这个值
        每次只保留一个数，当遇到第 i 个数时，以 1/i的概率保留它，(i-1)/i的概率保留原来的数。
     */

    class Solution {
        ListNode node;
        Random random;

        public Solution(ListNode head) {
            this.node = head;
            random = new Random();

        }

        public int getRandom() {
            int i = 1, ans = 0;
            for (ListNode node = this.node; node != null; node = node.next) {
                if (random.nextInt(i) == 0) {
                    ans = node.val;
                }
                i++;
            }

            return ans;
        }
    }
}
