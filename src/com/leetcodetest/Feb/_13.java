package com.leetcodetest.Feb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class _13 {
    /**
     * 每日一题：2021/2/13
     * 448. 找到所有数组中消失的数字
     * 难度: easy
     * 给定一个范围在 1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。
     * 找到所有在 [1, n] 范围之间没有出现在数组中的数字。
     * 您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。
     * <p>
     * 示例：
     * 输入:
     * [4,3,2,7,8,2,3,1]
     * 输出:
     * [5,6]
     * <p>
     * 数据范围：
     */

    public List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        int[] store = new int[n + 1];
//        System.out.println(Arrays.toString(store));
        List<Integer> res = new ArrayList<Integer>();
//        for (int i = 1; i <= n; i++) {
//            res.add(i);
//        }

        for (int num : nums) {
            store[num] = 1;
//                res.remove((Integer) num);
        }
        for (int i = 1; i < n + 1; i++) {
            if (store[i] == 0) {
                res.add(i);
            }
        }

        return res;

    }

    // 不消耗额外空间的做法
    /*
    思路2： 原地修改
    我们可以用一个哈希表记录数组 nums 中的数字，由于数字范围均在 [1,n] 中，记录数字后我们再利用哈希表检查 [1,n] 中的每一个数是否出现，从而找到缺失的数字。
    由于数字范围均在 [1,n] 中，我们也可以用一个长度为 n 的数组来代替哈希表。这一做法的空间复杂度是 O(n) 的。我们的目标是优化空间复杂度到 O(1)。
    注意到 nums 的长度恰好也为 n，能否让 nums 充当哈希表呢？
    由于 nums 的数字范围均在 [1,n] 中，我们可以利用这一范围之外的数字，来表达「是否存在」的含义。
    具体来说，遍历 nums，每遇到一个数 x，就让 nums[x−1] 增加 n。由于 nums 中所有数均在 [1,n] 中，增加以后，这些数必然大于 n。
    最后我们遍历 nums，若 nums[i] 未大于 n，就说明没有遇到过数 i+1。这样我们就找到了缺失的数字。
    注意，当我们遍历到某个位置时，其中的数可能已经被增加过，因此需要对 n 取模来还原出它本来的值。
     */
    public List<Integer> findDisappearedNumbers2(int[] nums) {
        int n = nums.length;
        List<Integer> res = new ArrayList<>();
        for (int num : nums) {
            nums[(num - 1) % n] += n;
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] <= n) {
                res.add(i + 1);
            }
        }
        return res;
    }

    public void _21_2_13() {
        int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};
        int[] nums2 = {2, 2};
        System.out.println(findDisappearedNumbers(nums));
        System.out.println(findDisappearedNumbers2(nums));

    }
}
