package leetcode_everyday.Mar;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WuChao
 * @since 2021/3/27 上午7:58
 */
public class _27 {
    /**
     * 每日一题：2021/3/27
     * 61. 旋转链表
     * 难度: medium
     * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
     * <p>
     * 示例：
     * 输入：head = [1,2,3,4,5], k = 2
     * 输出：[4,5,1,2,3]
     *
     *
     * <p>
     * 数据范围：
     * 链表中节点的数目在范围 [0, 500] 内
     * -100 <= Node.val <= 100
     * 0 <= k <= 2 * 109
     */

    /*
    题意解析：将链表向右循环移动k个位置
     */

    /**
     * 链表节点的定义
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

    /**
     * 方法1：开辟O(n)的空间存储链表节点
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0) {
            return head;
        }
        List<ListNode> store = new ArrayList<>();
        ListNode node = head;
        while (node != null) {
            store.add(node);
            node = node.next;
        }
        int len = store.size();
        int move = k % len;
        if (move == 0) {
            return head;
        }
        ListNode newHead = store.get(len - move);
        ListNode pre = newHead;
        for (int i = len - move + 1; i < len; i++) {
            pre.next = store.get(i);
            pre = store.get(i);
        }
        for (int i = 0; i < len - move; i++) {
            pre.next = store.get(i);
            pre = store.get(i);
        }
        pre.next = null;
        return newHead;
    }

    /**
     * 方法2：与方法1类似，只是这里只是用指针而不再使用额外的存储空间
     */
    public ListNode rotateRight2(ListNode head, int k) {
        if (head == null || k == 0) {
            return head;
        }
        int count = 0;
        ListNode node = head;
        ListNode oldRear = null;
        while (node != null) {
            count++;
            if (node.next == null) {
                oldRear = node;
            }
            node = node.next;
        }
        int move = k % count;
        if (move == 0) {
            return head;
        }
        ListNode newHead = head;
        int newnHeadIdx = count - move;
        ListNode newRear = null;
        while (newnHeadIdx != 0) {
            newnHeadIdx--;
            if (newnHeadIdx == 0) {
                newRear = newHead;
            }
            newHead = newHead.next;
        }
        oldRear.next = head;
        newRear.next = null;
        return newHead;

    }



        @Test
    public void test() {
        ListNode a = new ListNode(1);
        int k = 1;
        ListNode res = rotateRight(a, k);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }

}
