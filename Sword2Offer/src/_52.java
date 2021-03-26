import java.util.Stack;

/**
 * @author WuChao
 * @since 2021/3/26 上午8:10
 */
public class _52 {
    /**
     * 剑指 Offer 52. 两个链表的第一个公共节点
     * 难度: easy
     * <p>
     * 输入两个链表，找出它们的第一个公共节点。
     * <p>
     * 示例：
     * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
     * 输出：Reference of the node with value = 8
     * 输入解释：相交节点的值为 8 （注意，如果两个列表相交则不能为 0）。
     * 从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。
     * 在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
     *
     * <p>
     * 数据范围：
     * 如果两个链表没有交点，返回 null.
     * 在返回结果后，两个链表仍须保持原有的结构。
     * 可假定整个链表结构中没有循环。
     * 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存
     */

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /*
    第一种能够瞬间想到的思路：使用栈来存储,栈顶元素代表后面的节点
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        Stack<ListNode> stackA = new Stack<>(), stackB = new Stack<>();
        ListNode nodeA = headA, nodeB = headB;
        while (nodeA != null) {
            stackA.push(nodeA);
            nodeA = nodeA.next;
        }
        while (nodeB != null) {
            stackB.push(nodeB);
            nodeB = nodeB.next;
        }
        if (stackA.peek() != stackB.peek()) {
            return null;
        }
        ListNode res = null;
        while (!stackA.empty() &&!stackB.empty()&& stackA.peek() == stackB.peek() ) {
            res = stackA.peek();
            stackA.pop();
            stackB.pop();
        }
        return res;
    }

    /**
     * 第二种方法，先将两个链表各自遍历一遍统计个数
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode nodeA = headA, nodeB = headB;
        int countA = 0, countB = 0;
        while (nodeA != null) {
            countA++;
            nodeA = nodeA.next;
        }
        while (nodeB != null) {
            countB++;
            nodeB = nodeB.next;
        }
        nodeA = headA;
        nodeB = headB;
        if (countA > countB) {
            int diff = countA - countB;
            while (diff != 0) {
                diff--;
                nodeA = nodeA.next;
            }
        }else if (countA < countB) {
            int diff = countB - countA;
            while (diff != 0) {
                diff--;
                nodeB = nodeB.next;
            }
        }
        ListNode res = null;
        while (nodeA != null && nodeB != null) {
            if (nodeA == nodeB) {
                res = nodeA;
                break;
            }
            nodeA = nodeA.next;
            nodeB = nodeB.next;
        }
        return res;
    }


    }
