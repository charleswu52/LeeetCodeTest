package leetcode_everyday._2021.Feb;

public class _8 {
    /**
     * 每日一题：2021/2/8
     * 978. 最长湍流子数组
     * 难度: medium
     * 当 A 的子数组 A[i], A[i+1], ..., A[j] 满足下列条件时，我们称其为湍流子数组：
     * 若 i <= k < j，当 k 为奇数时， A[k] > A[k+1]，且当 k 为偶数时，A[k] < A[k+1]；
     * 或 若 i <= k < j，当 k 为偶数时，A[k] > A[k+1] ，且当 k 为奇数时， A[k] < A[k+1]。
     * 也就是说，如果比较符号在子数组中的每个相邻元素对之间翻转，则该子数组是湍流子数组。
     * 返回 A 的最大湍流子数组的长度。
     * <p>
     * 示例：
     * 输入：[9,4,2,10,7,8,8,1,9]
     * 输出：5
     * 解释：(A[1] > A[2] < A[3] > A[4] < A[5])
     * <p>
     * 数据范围：
     * 1 <= A.length <= 40000
     * 0 <= A[i] <= 10^9
     */

    /*
    题目解析：
    题目描述的有点复杂，简单来说就是给一个数组，任意两个数字之间有大小比较关系，
    如果比较符号在子数组中的每个相邻元素对之间翻转，则该子数组是湍流子数组。
     */
    public int maxTurbulenceSize(int[] arr) {
        int len = arr.length;
        if (len < 2) {
            return len;
        }

        int[] compare = new int[len - 1];
        for (int i = 0; i < len - 1; i++) {
            if (arr[i] < arr[i + 1]) {
                compare[i] = 0;
            } else if (arr[i] > arr[i + 1]) {
                compare[i] = 1;
            } else {
                compare[i] = 3;
            }
        }
//        System.out.println(Arrays.toString(compare));
        int res = 0;
        int head = 0;
        while (head < compare.length) {
            if (compare[head] != 3) {
                break;
            }
            head++;
        }
        if (head == compare.length) {
            return 1;
        }
        int i;
        for (i = head+1; i < compare.length; i++) {
            if (Math.abs(compare[i] - compare[i - 1]) == 0) {
                res = Math.max(res, i-head);
                head=i;
            } else if (Math.abs(compare[i] - compare[i - 1]) > 1) {
                res = Math.max(res, i-head);
                head=++i;
            }
        }
        res = Math.max(res, i-head);
        return res+1;

    }

    /*
    官方题解： 滑动窗口
    设数组 arr 的长度为 n，窗口 [left,right](0 ≤ left ≤ right ≤ n−1) 为当前的窗口，窗口内构成了一个「湍流子数组」。
    随后，我们要考虑下一个窗口的位置。
    根据「湍流子数组」的定义，当 0 < right < n−1 时：
    如果 arr[right−1] < arr[right] 且 arr[right] > arr[right+1]，则 [left,right+1] 也构成「湍流子数组」，因此需要将 right 右移一个单位；
    如果 arr[right−1] > arr[right] 且 arr[right] < arr[right+1]，同理，也需要将 right 右移一个单位；
    否则，[right−1,right+1] 无法构成「湍流子数组」，当 left < right 时，[left,right+1] 也无法构成「湍流子数组」，
    因此需要将 left 移到 right，即令 left=right。
    此外，我们还需要特殊考虑窗口长度为 1 (即 left 和 right 相等的情况)：
    只要 arr[right] != arr[right+1]，就可以将 right 右移一个单位；否则，left 和 right 都要同时右移。
     */
    public int maxTurbulenceSize2(int[] arr) {
        int n = arr.length;
        int ret = 1;
        int left = 0, right = 0;

        while (right < n - 1) {
            if (left == right) {
                if (arr[left] == arr[left + 1]) {
                    left++;
                }
                right++;
            } else {
                if (arr[right - 1] < arr[right] && arr[right] > arr[right + 1]) {
                    right++;
                } else if (arr[right - 1] > arr[right] && arr[right] < arr[right + 1]) {
                    right++;
                } else {
                    left = right;
                }
            }
            ret = Math.max(ret, right - left + 1);
        }
        return ret;
    }

    public void _21_2_8() {
        int[] arr = {9,9};
        System.out.println(maxTurbulenceSize(arr));
        System.out.println(maxTurbulenceSize2(arr));

    }
}
