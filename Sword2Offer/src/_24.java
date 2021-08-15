import java.util.LinkedList;
import java.util.List;

/**
 * @author WuChao
 * @since 2021/3/16 上午8:35
 */
public class _24 {
    /**
     * 剑指 Offer 24. 反转链表
     * 难度: easy
     * 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
     * <p>
     * 示例：
     * 输入: 1->2->3->4->5->NULL
     * 输出: 5->4->3->2->1->NULL
     * <p>
     * 数据范围：
     * 0 <= 节点个数 <= 5000
     */
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode reverseList(ListNode head) {
        // 用空间换时间的方式
        List<ListNode> listNodeList = new LinkedList<>();
        while (head != null) {
            listNodeList.add(head);
            head = head.next;
        }
        if (listNodeList.size() == 0) {
            return null;
        }
        listNodeList.get(0).next = null;
        for (int i = listNodeList.size() - 1; i > 0; i--) {
            listNodeList.get(i).next = listNodeList.get(i - 1);
        }
        return listNodeList.get(listNodeList.size() - 1);
    }

}
