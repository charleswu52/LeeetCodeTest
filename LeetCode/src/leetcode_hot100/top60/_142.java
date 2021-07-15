package leetcode_hot100.top60;

/**
 * @author WuChao
 * @create 2021/7/15 8:51
 */
public class _142 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 142. 环形链表 II
     * 难度：medium
     * <p>
     * 给定一个链表，判断链表中是否有环。
     * <p>
     * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
     *
     * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
     * 注意，pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。
     *
     * 说明：不允许修改给定的链表。
     *
     * 进阶：
     * 你是否可以使用 O(1) 空间解决此题？
     * <p>
     * 示例 1：
     * 输入：head = [3,2,0,-4], pos = 1
     * 输出：true
     * 解释：链表中有一个环，其尾部连接到第二个节点。
     * <p>
     * 示例 2：
     * 输入：head = [1,2], pos = 0
     * 输出：true
     * 解释：链表中有一个环，其尾部连接到第一个节点。
     * <p>
     * 示例 3：
     * 输入：head = [1], pos = -1
     * 输出：false
     * 解释：链表中没有环。
     * <p>
     * 数据范围:
     */

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /*
    思路：快慢指针
    快慢两个指针起初均位于head
    然后 快指针一次移动两个位置，慢指针一次移动一个位置
    没有环时快指针会先走到null;
    有环的时候快指针会追上慢指针，这时候新定义一个指针从头开始走，慢指针也走 当他们相遇的时候就是环出现的位置
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null) {
            slow = slow.next;
            if (fast.next != null) {
                fast = fast.next.next;

            } else {
                return null;
            }
            if (fast == slow) {
                ListNode node = head;
                while (node != slow) {
                    node = node.next;
                    slow = slow.next;
                }
                return node;
            }
        }
        return null;
    }
}
