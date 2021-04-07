package BinarySearchFramework;

/**
 * @author WuChao
 * @since 2021/4/7 上午11:27
 */
public class LastXiaoyuTarget {

    /**
     * 查找有序数组中最后一个小于目标值的元素所在的下标
     */
    public int upperBoundnum(int[] nums, int target, int left, int right) {
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] < target) {
                // 看看是不是当前区间的最后一位，如果当前小于，后面一位大于，返回当前即可
                if (mid == right || nums[mid + 1] >= target) {
                    return mid;
                } else {
                    left = mid + 1;
                }
            } else if (nums[mid] >= target) {
                right = mid - 1;
            }
        }
        // 所有元素都大于目标
        return -1;
    }

}
