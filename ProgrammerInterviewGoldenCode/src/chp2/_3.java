package chp2;

/**
 * @author WuChao
 * @since 2021/4/8 上午9:53
 */
public class _3 {
    /**
     * 程序员面试金典(version 6) - 面试题 02.03. 删除中间节点
     * 难度: easy
     * <p>
     * 实现一种算法，删除单向链表中间的某个节点（即不是第一个或最后一个节点），假定你只能访问该节点。
     * <p>
     * 例如：
     * 输入：单向链表a->b->c->d->e->f中的节点c
     * 结果：不返回任何数据，但该链表变为a->b->d->e->f
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
    有点迷的题意，这个node就是要删除的节点，那直接让它的值变成下一个节点的值，
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;

    }
}
