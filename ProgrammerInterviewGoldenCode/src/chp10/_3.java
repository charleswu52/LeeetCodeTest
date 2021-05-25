package chp10;

/**
 * @author WuChao
 * @since 2021/5/23 上午11:17
 */
public class _3 {
    /**
     * 程序员面试金典(version 6) - 面试题 10.03. 搜索旋转数组
     * 难度: medium
     * <p>
     * 搜索旋转数组。给定一个排序后的数组，包含n个整数，但这个数组已被旋转过很多次了，次数不详。
     * 请编写代码找出数组中的某个元素，假设数组元素原先是按升序排列的。若有多个相同元素，返回索引值最小的一个。
     *
     *
     * <p>
     * 示例:
     * 输入: arr = [15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14], target = 5
     * 输出: 8（元素5在该数组中的索引）
     * <p>
     * 输入：arr = [15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14], target = 11
     * 输出：-1 （没有找到）
     *
     * <p>
     * 数据范围：
     * arr 长度范围在[1, 1000000]之间
     */

    public int search(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            if (arr[left] == target) {
                return left;
            }
            int mid = left + ((right - left) >> 1);
            if (arr[mid] == target) {
                right = mid;
            } else if (arr[0] < arr[mid]) {
                if (arr[0] <= target && arr[mid] > target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else if (arr[0] > arr[mid]) {

                if (arr[mid] < target && target <= arr[arr.length - 1]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                left += 1;
            }
        }
        return -1;// 未找到

    }
}
