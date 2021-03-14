/**
 * @author WuChao
 * @since 2021/3/14 上午10:34
 */
public class _18 {
    /**
     * 剑指 Offer 18. 删除链表的节点
     * 难度: easy
     * 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。
     * <p>
     * 返回删除后的链表的头节点。
     * <p>
     * 示例：
     * 输入: head = [4,5,1,9], val = 5
     * 输出: [4,1,9]
     * 解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
     *
     * <p>
     * 数据范围：
     * 题目保证链表中节点的值互不相同
     * 若使用 C 或 C++ 语言，你不需要 free 或 delete 被删除的节点
     */

    // 链表的定义
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode deleteNode(ListNode head, int val) {
        if (head.val == val) {
            return head.next;
        }
        ListNode preNode = head, p = head;
        while (p != null) {
            if (p.val != val) {
                preNode = p;
                p = p.next;
            } else {
                preNode.next = p.next;
                break;
            }
        }
        return head;

    }

    public void sword2Offer_18(){

    }


}
