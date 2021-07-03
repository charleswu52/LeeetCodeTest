package leetcode_everyday.Jun;

/**
 * @author WuChao
 * @since 2021/6/5 上午8:44
 */
public class _5 {
    /**
     * 每日一题：2021/6/5
     * 203. 移除链表元素
     * 难度: easy
     * <p>
     * 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
     *
     * <p>
     * 示例:
     * 输入：head = [1,2,6,3,4,5,6], val = 6
     * 输出：[1,2,3,4,5]
     * <p>
     * 输入：head = [], val = 1
     * 输出：[]
     * <p>
     * 输入：head = [7,7,7,7], val = 7
     * 输出：[]
     * <p>
     * 数据范围：
     * 列表中的节点在范围 [0, 104] 内
     * 1 <= Node.val <= 50
     * 0 <= k <= 50
     */
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return head;
        }
        ListNode pre = head, cur = head;
        while (cur != null) {
            if (cur.val == val) {
                if (cur == head) {
                    cur = cur.next;
                    head = cur;
                } else {
                    pre.next = cur.next;
                    cur = cur.next;
                }
            } else {
                pre = cur;
                cur = cur.next;
            }
        }
        return head;
    }


}
