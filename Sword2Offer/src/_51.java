import org.junit.Test;

import java.util.Arrays;

/**
 * @author WuChao
 * @since 2021/3/25 上午10:01
 */
public class _51 {
    /**
     * 剑指 Offer 51. 数组中的逆序对
     * 难度: hard
     * <p>
     * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
     * <p>
     * 示例：
     * 输入: [7,5,6,4]
     * 输出: 5
     * <p>
     * 数据范围：
     * 0 <= 数组长度 <= 50000
     */


    /*
    暴力算法比较简单，时间复杂度是O(n^2) 不再写
    参考其他题解提到了归并排序的思想，这里先复习下归并排序的思想，再根据排序过程中的思路来整理出该题目的思路
     */

    /**
     * 对一个数组执行合并排序
     * @param sourceArray 原始待排序数组
     * @return
     */
    public int[] mergeSort(int[] sourceArray) {
        // 先对原始待排序数组进行拷贝，不改变参数内容
        int[] res = Arrays.copyOf(sourceArray, sourceArray.length);
        if (res.length < 2) {
            return res;
        }
        int middle = (int) Math.floor(res.length / 2);// 对排序数组大小一分为二，结果取整

        int[] left = Arrays.copyOfRange(res, 0, middle);
        int[] right = Arrays.copyOfRange(res, middle, res.length);

        return merge(mergeSort(left), mergeSort(right));


    }

    /**
     * 递归分治法  --合并排序
     * @param left
     * @param right
     * @return
     */
    public int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0;
        while (left.length > 0 && right.length > 0) {
            if (left[0] <= right[0]) {
                result[i++] = left[0];
                left = Arrays.copyOfRange(left, 1, left.length);
            } else {
                result[i++] = right[0];
                right = Arrays.copyOfRange(right, 1, right.length);
            }
        }
        while (left.length > 0) {
            result[i++] = left[0];
            left = Arrays.copyOfRange(left, 1, left.length);
        }
        while (right.length > 0) {
            result[i++] = right[0];
            right = Arrays.copyOfRange(right, 1, right.length);
        }
        return result;
    }



    /*
    复习完成归并排序算法的思路，回到本题上，统计逆序数的个数
    在归并排序“合并两个有序数组步骤上，利用数组的部分有序性，一下子计算出一个数之前或者之后元素的逆序的个数；
    前面“分”的时候什么都不做，“合”的过程中计算“逆序对”的个数；
    「排序」的工作是必要的，正是因为「排序」才能在下一轮利用顺序关系加快逆序数的计算，也能避免重复计算；
    所有的「逆序对」来源于 3 个部分：
        左边区间的逆序对；
        右边区间的逆序对；
        横跨两个区间的逆序对。

    将原始数组拷贝到辅助数组，再使用双指针，每次将较小元素归并回去
     */
    public int reversePairs(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return 0;
        }
        int[] copy = Arrays.copyOf(nums, len);
        int[] temp = new int[len];
        return reversePairs(copy, 0, len - 1, temp);

    }

    /**
     * nums[left..right] 计算逆序对的个数并排序
     * @param nums 原始未排序的数组
     * @param left 左边界
     * @param right 右边界
     * @param temp 归并过程中使用的临时数组
     * @return
     */
    public int reversePairs(int[] nums, int left, int right, int[] temp) {
        if (left == right) {
            return 0;
        }
        int mid = left + (right - left) / 2;
        // 左右两个子区间的逆序对
        int leftPairs = reversePairs(nums, left, mid, temp);
        int rightPairs = reversePairs(nums, mid + 1, right, temp);

        // 如果整个数组已经有序，无需合并
        if (nums[mid] <= nums[mid + 1]) {
            return leftPairs + rightPairs;
        }

        // 横跨两个区间的逆序对
        int crossPairs = mergeAndCount(nums, left, mid, right, temp);
        return leftPairs + rightPairs + crossPairs;
    }

    /**
     * 归并的过程中计算逆序数：nums[left..mid] 与 nums[mid+1...right]均有序
     * @param nums 排序数组
     * @param left 左边界
     * @param mid  中间值
     * @param right 右边界
     * @param temp 临时数组
     * @return
     */
    public int mergeAndCount(int[] nums, int left, int mid, int right, int[] temp) {
        for (int i = left; i <= right  ; i++) {
            temp[i] = nums[i];
        }
        int i = left;
        int j = mid + 1;

        int count = 0;
        for (int k = left; k <= right; k++) {
            // 先判断是否越界
            if (i == mid + 1) {
                nums[k] = temp[j];
                j++;
            } else if (j == right + 1) {
                nums[k] = temp[i];
                i++;
            } else if (temp[i] <= temp[j]) {
                nums[k] = temp[i];
                i++;
            } else {
                nums[k] = temp[j];
                j++;
                // 在j指向元素归并回去的时候计算逆序对的个数
                count += (mid - i + 1);
            }
        }
        return count;
    }

    @Test
    public void test() {
        int[] nums = {7, 5, 6, 4};
        System.out.println(reversePairs(nums));
    }
}
