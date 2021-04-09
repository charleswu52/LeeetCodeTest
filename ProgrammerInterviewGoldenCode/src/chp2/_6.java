package chp2;

/**
 * @author WuChao
 * @since 2021/4/9 上午8:55
 */
public class _6 {
    /**
     * 程序员面试金典(version 6) - 面试题 02.06. 回文链表
     * 难度: easy
     * <p>
     * 编写一个函数，检查输入的链表是否是回文的。
     * <p>
     * 示例：
     * 输入： 1->2->2->1
     * 输出： true
     * <p>
     * 进阶：
     * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
     *
     *
     * <p>
     * 数据范围：
     */

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /*
    题意解析
    题目难点在于，要求使用时间复杂度O(n)与空间复杂度O(1)
    因此对于遍历链表保存值再判断的方式就先不再说明
    只考虑如何一遍遍历来判断
     */
    /*
    O(n)空间复杂度的解决方案： 使用快慢指针
    将链表的后半部分反转（修改链表结构），然后将前半部分和后半部分进行比较。比较完成后我们应该将链表恢复原样。
    该方法的缺点就是在并发环境下函数运行时需要锁定其他线程或进程对链表的访问。

    处理流程分为：
    1. 找到前半部分链表的尾节点。
    2. 反转后半部分链表。
    3. 判断是否回文。
    4. 恢复链表。
    5. 返回结果。
     */
    public boolean isPalindrome(ListNode head) {

        if (head == null) {
            return true;
        }
        // 1.找到前半部分的尾节点并反转后半部分的链表
        ListNode firstHalfEnd = endOfFirstHalf(head); // 找到链表的前半部分链表的尾节点
        ListNode secondHalfStart = reverseList(firstHalfEnd.next); // 翻转后半部分链表

        // 判断重构后的链表是否是回文
        ListNode node1 = head;
        ListNode node2 = secondHalfStart;
        boolean ans = true;
        while (ans && node2 != null) {
            if (node1.val != node2.val) {
                ans = false;
            }
            node1 = node1.next;
            node2 = node2.next;
        }

        // 以上就完成了题目要求，但是处于考虑，还是将链表结构进行还原
        firstHalfEnd = reverseList(secondHalfStart);
        return ans;


    }

    // 将过程中使用到的 查找中间节点 和 反转部分链表 的功能抽象出来

    /**
     * 反转链表
     * @param head
     * @return
     */
    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    /**
     * 找到前半部分的尾节点
     * 使用快慢两个指针，快指针一次走两步，慢指针一次走一步，这样当快指针走到头的时候满指针正好走链表的中间部分
     * @param head
     * @return
     */
    private ListNode endOfFirstHalf(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

}
