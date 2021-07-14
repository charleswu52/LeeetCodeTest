package weilai._210703;

import com.sun.org.apache.bcel.internal.generic.SWAP;

import java.util.jar.JarEntry;

public class Test {
    /*
     */
    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }


    public ListNode ReverseList(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode pre = null;
        while (head != null) {
            ListNode nextNode = head.next;
            if (pre == null) {
                pre = head;
                head.next = null;
            } else {
                head.next = pre;
                pre = head;
            }
            head = nextNode;
        }
        return pre;

    }
    // 手写排序 --> 直接选择排序
    public int[] MySort (int[] arr) {
        // write code here
        int len = arr.length;
        int minIndex;
        for (int i = 0; i < len; i++) {
            minIndex = i;
            for (int j = i; j < len; j++) {
                if (arr[minIndex] > arr[j]) {
                    arr[minIndex] = arr[j];
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);

        }
        return arr;

    }

    public void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }


    // 手写快排

    public int[] quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int p = partition(arr, left, right);
            quickSort(arr, left, p - 1);
            quickSort(arr, p + 1, right);

        }
        return arr;
    }

    public int partition(int[] arr, int left, int right) {
        int temp = left;
        while (left < right) {
            while (left < right && arr[right] >= arr[temp]) {
                right--;
            }
            while (left < right && arr[left] <= arr[temp]) {
                left++;
            }
            swap(arr, left, right);
        }
        swap(arr, temp, right);
        return right;
    }
}
