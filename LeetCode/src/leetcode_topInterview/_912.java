package leetcode_topInterview;

import java.util.Random;

/**
 * @author WuChao
 * @create 2021/7/30 9:17
 */
public class _912 {
    /**
     * codeTop 热题 面试常考
     * <p>
     * 912. 排序数组
     * <p>
     * 难度：medium
     * <p>
     * 给你一个整数数组 nums，请你将该数组升序排列。
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [5,2,3,1]
     * 输出：[1,2,3,5]
     * <p>
     * 示例 2：
     * <p>
     * 输入：nums = [5,1,1,2,0,0]
     * 输出：[0,0,1,1,2,5]
     * <p>
     * 提示：
     * <p>
     * 1 <= nums.length <= 50000
     * -50000 <= nums[i] <= 50000
     */

    /*
    思路：快速排序
    题目考察手撕快速排序
     */
    public int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;

    }


    public void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int pos = randomizedPartition(arr, left, right);
            quickSort(arr, left, pos - 1);
            quickSort(arr, pos + 1, right);
        }

    }

    public int randomizedPartition(int[] arr, int left, int right) {
        int idx = new Random().nextInt(right - left + 1) + left;// 随机选一个作为我们的主元
        swap(arr, idx, right);
        return partition(arr, left, right);


    }

    public int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left - 1;
        for (int j =left ; j <= right - 1; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, right);
        return i + 1;
    }


    public void swap(int[] arr, int x, int y) {
        int t = arr[x];
        arr[x] = arr[y];
        arr[y] = t;
    }
}
