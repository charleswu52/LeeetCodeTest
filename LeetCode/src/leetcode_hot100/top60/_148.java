package leetcode_hot100.top60;

/**
 * @author WuChao
 * @create 2021/7/16 8:39
 */
public class _148 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 148. 排序链表
     * 难度：medium
     * <p>
     * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
     * <p>
     * 进阶：
     * 你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
     *
     *
     * <p>
     * 输入：head = [4,2,1,3]
     * 输出：[1,2,3,4]
     * <p>
     * 输入：head = [-1,5,3,4,0]
     * 输出：[-1,0,3,4,5]
     *
     *
     * <p>
     * 数据范围:
     * 链表中节点的数目在范围 [0, 5 * 104] 内
     * -105 <= Node.val <= 105
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

    /*
    题目解析：
    对链表排序 题目要求 时间复杂度 O(nlogn) 和空间复杂度 O(1)

    思路：归并排序  双指针
    数组归并排序需要O(n)的空间，但是链表不需要额外的空间。

    利用归并的思想，递归地将当前链表分为两段，然后merge，分两段的方法是使用 fast-slow 法，用两个指针，一个每次走两步，一个走一步，
    知道快的走到了末尾，然后慢的所在位置就是中间位置，这样就分成了两段。

    merge时，把两段头部节点值比较，用一个 p 指向较小的，且记录第一个节点，然后 两段的头一步一步向后走，p也一直向后走，总是指向较小节点，
    直至其中一个头为NULL，处理剩下的元素。最后返回记录的头即可。

     */


    public ListNode sortList(ListNode head) {
         if (head == null) {
                return head;
        }
        return mergeSort(head);
    }

    private ListNode mergeSort(ListNode head) {
        if (head.next == null) {
            return head;
        }

        ListNode slow = head, fast = head, pre = null;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        pre.next = null;
        ListNode left = mergeSort(head);
        ListNode right = mergeSort(slow);
        return merge(left, right);
    }

    private ListNode merge(ListNode left, ListNode right) {
        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;
        while (left != null && right != null) {
            if (left.val <= right.val) {
                cur.next = left;
                cur = cur.next;
                left = left.next;
            } else {
                cur.next = right;
                cur = cur.next;
                right = right.next;
            }
        }
        if (left != null) {
            cur.next = left;
        }
        if (right != null) {
            cur.next = right;
        }
        return dummyHead.next;
    }
}
