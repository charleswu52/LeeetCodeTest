package chp2;

import java.util.HashSet;
import java.util.Set;

/**
 * @author WuChao
 * @since 2021/4/8 上午7:57
 */
public class _1 {
    /**
     * 程序员面试金典(version 6) - 面试题 02.01. 移除重复节点
     * 难度: easy
     * <p>
     * <p>
     * 编写代码，移除未排序链表中的重复节点。保留最开始出现的节点。
     * <p>
     * 例如：
     * 输入：[1, 2, 3, 3, 2, 1]
     * 输出：[1, 2, 3]
     *
     * <p>
     * 数据范围：
     * 链表长度在[0, 20000]范围内。
     * 链表元素在[0, 20000]范围内。
     * <p>
     * 如果不得使用临时缓冲区，该怎么解决？
     */

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /*
    题目解析：
    题目难点在于不使用临时缓冲区，即不使用额外的存储空间。要求空间复杂度是O(1)
     */

    /*
    先实现第一种使用额外存储空间的一种解法，使用Set来存储已经存在的节点的值.
    时间复杂度：O(n) 一次遍历
    空间复杂度：O(n) 存储节点
     */
    public ListNode removeDuplicateNodes(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode node = head;
        Set<Integer> store = new HashSet<>();
        store.add(node.val);
        while (node.next != null) {
            ListNode cur = node.next;
            if (store.add(cur.val)) {
                node = node.next;
            } else {
                node.next = node.next.next;
            }
        }
        node.next = null;
        return head;
    }


    /*
    方法2：改进时间复杂度
    两次遍历，外层遍历链表中的所有元素，内层遍历对每一个当前的节点，向后遍历一遍，看是否与当前遍历的节点的值相同，将值相同的节点移除即可
    属于用时间换空间，并不推荐
    时间复杂度：O(n^2)
    空间复杂度：O(1)
     */
    public ListNode removeDuplicateNodes2(ListNode head) {
        ListNode node = head;
        while (node != null) {
            ListNode cur = node;
            while (cur.next != null) {
                if (cur.next.val == node.val) {
                    cur.next = cur.next.next;
                } else {
                    cur = cur.next;
                }
            }
            node = node.next;
        }
        return head;

    }


    }
