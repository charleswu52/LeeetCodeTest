package chp2;

/**
 * @author WuChao
 * @since 2021/4/9 上午11:14
 */
public class _8 {
    /**
     * 程序员面试金典(version 6) - 面试题 02.08. 环路检测
     * 难度: medium
     * <p>
     * 给定一个链表，如果它是有环链表，实现一个算法返回环路的开头节点。
     * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
     * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
     * 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
     * <p>
     * 示例：
     * 输入：head = [3,2,0,-4], pos = 1
     * 输出：tail connects to node index 1
     * 解释：链表中有一个环，其尾部连接到第二个节点。
     *
     * <p>
     * 进阶：
     * 你是否可以不用额外空间解决此题？
     * <p>
     * 数据范围：
     */

    // 链表节点定义
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /*
    不使用额外的存储空间，那就只能用双指针的思考来考虑
    不好想用哪种类型的双指针

     */
    /*
    思路：使用快慢两个双指针，通过自己举例：
    假设有 1 2 3 4 5 3 4 5 ...在5 到3 形成环
    有两个指针 一快一慢，快的一次移动两个位置，慢的一次移动一个位置
    慢： 1 2 3 4 5 3 4 5 3 4 5 ...
    快： 1 3 5 4 3 5 4 3 5 4 3 ...
    可以发现在4处两者相遇，然后3部一遇，因此在他们第一次相遇的时候再设置一个指针从头开始走 再下一次两者相遇的时候就是环的入口点
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode slow = head, fast = head;
        while (fast!=null) {
            slow = slow.next;
            if (fast.next != null) {
                fast = fast.next.next;
            } else {
                return null;
            }
            if (fast == slow) {// 第一次相遇
                ListNode ans = head; // 开始从头设一个新的指针，让它追慢指针
                while (ans != slow) {
                    ans = ans.next;
                    slow = slow.next;
                }
                return ans; // 追上的时候就是环形入口点
            }
        }
        return null;
    }

}
