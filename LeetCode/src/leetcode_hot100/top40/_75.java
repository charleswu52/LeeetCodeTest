package leetcode_hot100.top40;

/**
 * @author WuChao
 * @create 2021/7/11 9:30
 */
public class _75 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 75. 颜色分类
     * 难度：medium
     * <p>
     * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
     * <p>
     * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
     * <p>
     * 示例
     * <p>
     * 输入：nums = [2,0,2,1,1,0]
     * 输出：[0,0,1,1,2,2]
     * <p>
     * 输入：nums = [2,0,1]
     * 输出：[0,1,2]
     * <p>
     * 输入：nums = [0]
     * 输出：[0]
     * <p>
     * 范围
     * n == nums.length
     * 1 <= n <= 300
     * nums[i] 为 0、1 或 2
     */

    /*
    题目解析：
    实际上就是考察排序，不用开辟新的内存空间的原地排序数组

    时间复杂度：
        快些（希）以nlogn速度归队：即快速、希尔、归并、堆排序 这四种排序的时间复杂度是 O(nlogn)
        其他的都是 O(n^2) :选择、插入、冒泡
    空间复杂度：
        除了 快速排序是O(nlogn) 归并是O(n)之外 其他排序的空间复杂度都是 O(1)
    排序稳定性：
        不稳定：情绪不稳定，快些选一堆朋友聊天吧： 即 快速、希尔、选择、堆排序
        其他均为稳定的
     */
    public void sortColors(int[] nums) {
        // 直接使用下面的 快 任意一个排序即可

    }

    /*
    排序算法 复习实现
     */

    public void swap(int[] arr, int x, int y) {
        int t = arr[x];
        arr[x] = arr[y];
        arr[y] = t;
    }

    /**
     * 冒泡排序
     * 时间复杂度：O(n^2)
     * 口那个件复杂度：O(1)
     * 稳定
     *
     * @param array
     */
    public void bubbleSort(int[] array) {
        // 外层循环，遍历次数
        for (int i = 0; i < array.length; i++) {
            // 内层循环，升序，如果前一个数比后一个数大就交换
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                }

            }
        }
    }

    /**
     * 选择排序
     * 时间复杂度：O(n^2)
     * 口那个件复杂度：O(1)
     * 不稳定
     *
     * @param array
     */
    public void selectSort(int[] array) {

        for (int i = 0; i < array.length; i++) {
            // 默认第一个是最小的
            int min = array[i];
            // 记录最小值的下标
            int idx = i;
            // 通过与最后面对数据进行比较得出最小值和下标
            for (int j = i + 1; j < array.length; j++) {
                if (min > array[j]) {
                    min = array[j];
                    idx = j;
                }
            }
            swap(array, i, idx);
        }
    }

    /**
     * 插入排序
     * 时间复杂度：O(n^2)
     * 口那个件复杂度：O(1)
     * 稳定
     *
     * @param array
     */
    public void insertSort(int[] array) {
        if (array.length < 2) {
            return;
        }
        // 外层循环，从第二个开始比较
        for (int i = 1; i < array.length; i++) {
            // 内层循环，与前面排好序的数据比较，如果后面的数据小雨前面的则交换
            for (int j = i; j > 0; j--) {
                if (array[j] < array[j - 1]) {
                    swap(array, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }

    /**
     * 希尔排序
     * 时间复杂度：O(nlog n)
     * 口那个件复杂度：O(1)
     * 不稳定
     *
     * @param array
     */
    public void xierSort(int[] array) {
        if (array.length < 2) {
            return;
        }
        // 第i层控制循环步长
        for (int i = array.length / 2; i > 0; i /= 2) {
            // j控制无序端的起始位置
            for (int j = i; j < array.length; j++) {
                for (int k = j; k > 0 && k - i >= 0; k -= i) {
                    if (array[k] < array[k - i]) {
                        swap(array, k, k - i);
                    } else {
                        break;
                    }
                }
            }
        }
    }

    /**
     * 归并排序
     * 时间复杂度：O(nlog n)
     * 口那个件复杂度：O(n)
     * 稳定
     *
     * @param array
     */
    public void mergeSort(int[] array, int start, int end) {
        // 判断是否切分为最小单元
        if (end - start > 0) {
            // 再拆分一次，直到拆分成一个一个的数据
            mergeSort(array, start, (start + end) / 2);
            mergeSort(array, (start + end) / 2 + 1, end);
            // 记录开始和结束位置
            int left = start;
            int right = (start + end) / 2 + 1;
            // 记录每个小单位的排序结果
            int index = 0;
            int[] result = new int[end - start + 1];
            // 如果拆分后的两块数据都还存在
            while (left <= (start + end) / 2 && right <= end) {
                // 比较两块数据的大小，然后赋值，并且移动下标
                if (array[left] <= array[right]) {
                    result[index] = array[left];
                    left++;
                } else {
                    result[index] = array[right];
                    right++;
                }
                // 移动单位的记录下标
                index++;
            }
            // 当某一数据不存在的时候
            while (left <= (start + end) / 2 || right <= end) {
                // 直接赋值到记录下标
                if (left <= (start + end) / 2) {
                    result[index] = array[left];
                    left++;
                } else {
                    result[index] = array[right];
                    right++;
                }
            }
            //最后将新的数据赋值给原来的列表，并且是对应分块后的下标。
            for (int i = start; i <= end; i++) {
                array[i] = result[i - start];
            }
        }
    }

    /**
     * 快速排序
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(nlogn)
     * 不稳定
     *
     * @param arr
     * @param low
     * @param high
     */
    public void quickSort(int[] arr, int low, int high) {
        //如果指针在同一位置(只有一个数据时)，退出
        if (high - low < 1) {
            return;
        }
        //标记，从高指针开始，还是低指针（默认高指针）
        boolean flag = true;
        //记录指针的其实位置
        int start = low;
        int end = high;
        //默认中间值为低指针的第一个值
        int midValue = arr[low];
        while (true) {
            //高指针移动
            if (flag) {
                //如果列表右方的数据大于中间值，则向左移动
                if (arr[high] > midValue) {
                    high--;
                } else if (arr[high] < midValue) {
                    //如果小于，则覆盖最开始的低指针值，并且移动低指针，标志位改成从低指针开始移动
                    arr[low] = arr[high];
                    low++;
                    flag = false;
                }
            } else {
                //如果低指针数据小于中间值，则低指针向右移动
                if (arr[low] < midValue) {
                    low++;
                } else if (arr[low] > midValue) {
                    //如果低指针的值大于中间值，则覆盖高指针停留时的数据，并向左移动高指针。切换为高指针移动
                    arr[high] = arr[low];
                    high--;
                    flag = true;
                }
            }
            //当两个指针的位置相同时，则找到了中间值的位置，并退出循环
            if (low == high) {
                arr[low] = midValue;
                break;
            }
        }
        //然后出现有，中间值左边的小于中间值。右边的大于中间值。
        //然后在对左右两边的列表在进行快速排序
        quickSort(arr, start, low -1);
        quickSort(arr, low + 1, end);
    }


}
