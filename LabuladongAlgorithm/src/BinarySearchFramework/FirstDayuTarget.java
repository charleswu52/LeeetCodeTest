package BinarySearchFramework;

/**
 * @author WuChao
 * @since 2021/4/7 上午11:23
 */
public class FirstDayuTarget {

    /**
     * 查找有序数组中第一个大于目标值的元素所在的下标
     */

    public  int lowBoundnum(int[] nums,int target,int left, int right) {
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] > target) {
                if (mid == 0 || nums[mid - 1] <= target) {
                    return mid;
                } else {
                    right = mid - 1;
                }
            } else if (nums[mid] <= target) {
                left = left + 1;
            }
        }
        // 所有元素都小于目标
        return -1;
    }

}
