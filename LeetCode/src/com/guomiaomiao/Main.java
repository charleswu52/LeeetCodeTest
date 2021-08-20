package com.guomiaomiao;

import java.util.Arrays;

/**
 * @author WuChao
 */
public class Main {
    public static void main(String[] args) {
//        int[] nums = {1, 3, 5, 2, 4, 6};
//        int n = nums.length;
//        int[] tmp = new int[n];
//        long ans = mergeSort(nums, 0, nums.length - 1, tmp);
//        System.out.println(Arrays.toString(nums));

//        ListNode node1 = new ListNode(1);
    }

    private static long mergeSort(int[] nums, int l, int r, int[] tmp) {
        if (l >= r) {
            return 0;
        }
        int m = (l + r) >>> 1;
        long ans = mergeSort(nums, l, m, tmp) + mergeSort(nums, m + 1, r, tmp);
        System.arraycopy(nums, l, tmp, l, r - l + 1);
        int i = l, j = m + 1;
        for (int k = l; k <= r; k++) {
            if (i == m + 1) {
                nums[k] = tmp[j];
                j++;
            } else if (j == r + 1) {
                nums[k] = tmp[i];
                i++;
            } else if (tmp[i] <= tmp[j]) {
                nums[k] = tmp[i];
                i++;
            } else {
                nums[k] = tmp[j];
                j++;
            }
        }
        return ans;
    }


    /**
     * 链表节点定义
     */
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
            this.next = null;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode leftHead = new ListNode(-1);
        ListNode leftNode = leftHead;
        ListNode rightHead = new ListNode(-1);
        ListNode rightNode = rightHead;
        ListNode pivotHead = head;
        ListNode pivotNode = pivotHead;
        ListNode node = head.next;
        while (node != null) {
            if (node.val < pivotNode.val) {
                leftNode.next = node;
                leftNode = leftNode.next;
            } else if (node.val == pivotNode.val) {
                pivotNode.next = node;
                pivotNode = pivotNode.next;
            } else {
                rightNode.next = node;
                rightNode = rightNode.next;
            }
            node = node.next;
        }
        leftNode.next = null;
        rightNode.next = null;
        pivotNode.next = null;
        ListNode left = sortList(leftHead.next);
        ListNode right = sortList(rightHead.next);
        pivotNode.next = right;
        if (left == null) {
            return pivotHead;
        }
        leftNode = left;
        while (leftNode.next != null) {
            leftNode = leftNode.next;
        }
        leftNode.next = pivotHead;
        return left;
    }

}
