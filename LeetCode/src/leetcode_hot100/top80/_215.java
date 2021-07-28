package leetcode_hot100.top80;

import java.util.Arrays;
import java.util.Random;

/**
 * @author WuChao
 * @create 2021/7/18 10:14
 */
public class _215 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 215. 数组中的第K个最大元素
     * 难度：medium
     * <p>
     * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
     * <p>
     * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
     * <p>
     * 示例：
     * 输入: [3,2,1,5,6,4] 和 k = 2
     * 输出: 5
     * <p>
     * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
     * 输出: 4
     * <p>
     * 数据范围:
     * 1 <= k <= nums.length <= 104
     * -104 <= nums[i] <= 104
     */

    /*
    思路1： 直接排序
    但是面试中是不让用这种算法的，变相考察手写排序
     */
    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        return nums[n - k];
    }

    /*
    思路2： 使用快排中的切分思想
    切分函数可以找到比当前值小的元素与大于当前值的元素的分界线，即第k个元素

    在划分的时候分割点为倒数第k个下标的时候就找到了该答案

    因此我们可以改进快速排序算法来解决这个问题：在分解的过程当中，我们会对子数组进行划分，如果划分得到的 q 正好就是我们需要的下标，
    就直接返回 a[q]；否则，如果 q 比目标下标小，就递归右子区间，否则递归左子区间。
    这样就可以把原来递归两个区间变成只递归一个区间，提高了时间效率。这就是「快速选择」算法。

    我们知道快速排序的性能和「划分」出的子数组的长度密切相关。直观地理解如果每次规模为 n 的问题我们都划分成 1 和 n−1，
    每次递归的时候又向 n−1 的集合中递归，这种情况是最坏的，时间代价是 O(n^2)。
    我们可以引入随机化来加速这个过程，它的时间代价的期望是 O(n)，证明过程可以参考「《算法导论》9.2：期望为线性的选择算法」。
     */
    Random random = new Random(); // 随机化选择下标

    public int findKthLargest2(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);

    }

    public int quickSelect(int[] arr, int left, int right, int index) {
        int q = randomPartition(arr, left, right);
        if (q == index) {
            return arr[q];
        } else {
            return q < index ? quickSelect(arr, q + 1, right, index) : quickSelect(arr, left, q - 1, index);
        }
    }

    public int randomPartition(int[] arr, int left, int right) {
        int i = random.nextInt(right - left + 1) + left;
        swap(arr, i, left);
        return partition(arr, left, right);
    }

    public int partition(int[] arr, int left, int right) {
        int x = arr[right];
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (arr[j] <= x) {
                swap(arr, ++i, j);
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



    /*
    思路3：基于堆排序的思想
    建立一个大根堆，做 k - 1k−1 次删除操作后堆顶元素就是我们要找的答案。
    在很多语言中，都有优先队列或者堆的的容器可以直接使用，但是在面试中，面试官更倾向于让更面试者自己实现一个堆。
     */

    public int findKthLargest3(int[] nums, int k) {
        int heapSize = nums.length;
        buildMaxHeap(nums, heapSize);
        for (int i = nums.length - 1; i >= nums.length - k + 1; i--) {
            swap(nums, 0, i);
            --heapSize;
            maxHeapify(nums, 0, heapSize);
        }
        return nums[0];


    }

    /**
     * 建一个大顶堆
     *
     * @param arr
     * @param heapSize
     */
    public void buildMaxHeap(int[] arr, int heapSize) {
        for (int i = heapSize / 2; i >= 0; i--) {
            maxHeapify(arr, i, heapSize);
        }

    }

    /**
     * 调整堆
     *
     * @param arr
     * @param i
     * @param heapSize
     */
    public void maxHeapify(int[] arr, int i, int heapSize) {
        int left = i * 2 + 1, right = i * 2 + 2, largest = i;
        if (left < heapSize && arr[left] > arr[largest]) {
            largest = left;
        }
        if (right < heapSize && arr[right] > arr[largest]) {
            largest = right;
        }
        if (largest != i) {
            swap(arr, i, largest);
            maxHeapify(arr, largest, heapSize);
        }
    }

}
