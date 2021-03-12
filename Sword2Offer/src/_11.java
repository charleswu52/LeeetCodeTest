/**
 * @author WuChao
 * @since 2021/3/11 下午12:31
 */
public class _11 {
    /**
     * 剑指 Offer 11. 旋转数组的最小数字
     * 难度: easy
     * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。
     * 例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。
     * <p>
     * <p>
     * 示例：
     * 输入：[3,4,5,1,2]
     * 输出：1
     * <p>
     * 数据范围：
     * 0 <= n <= 100
     */

    // 一趟遍历就能通过的方法
    public int minArray(int[] numbers) {
        int n = numbers.length;
        if (n < 2) {
            return numbers[0];
        }
        for (int i = 0; i < n - 1; i++) {
            if (numbers[i] > numbers[i + 1]) {
                return numbers[i + 1];
            }
        }
        return numbers[0];
    }

    /**
     * 改进思路2：二分法查找.
     * 1. 声明双指针i，j分别指向数组的两端
     * 2. 循环二分：
     * 中点设置为 m = （i+j）/ 2
     * 当nums[m]>nums[j]时，m一定在左边递增的排序数组中，旋转点则在右边[m+1,j]内,再设置 i=m+1
     * 当numbers[m] < numbers[j]时，m一定在右排序数组中，旋转点则在[i,m]内，再设置 j = m
     * 当numbers[m] = numbers[j]时，无法判断旋转点在哪个区间内,解决方案是执行j=j-1缩小判断范围
     * 3.当i=j时跳出二分循环，返回旋转点的值numbers[i]
     *
     * @param numbers
     * @return
     */
    public int minArray2(int[] numbers) {
        int i = 0, j = numbers.length - 1;
        while (i < j) {
            int m = (i + j) / 2;
            if (numbers[m] > numbers[j]) i = m + 1;
            else if (numbers[m] < numbers[j]) j = m;
            else j--;
        }
        return numbers[i];


    }


    public void sword2Offer_11() {
        int[] numbers = {3, 4, 5, 1, 2};
        System.out.println(minArray(numbers));
        System.out.println(minArray2(numbers));
    }

}
