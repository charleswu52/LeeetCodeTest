
package leetcode_everyday._2024.Jul;

import java.util.HashSet;
import java.util.Set;

/**
 * @author charles
 * @create 2024/7/20
 */
public class _16_2956 {
    /**
     * 每日一题：2024/7/16
     * 2956. 找到两个数组中的公共元素
     * https://leetcode.cn/problems/find-common-elements-between-two-arrays/description/?envType=daily-question&envId=2024-07-21
     */

    public int[] findIntersectionValues(int[] nums1, int[] nums2) {
        Set<Integer> nums1Set = new HashSet<>();
        Set<Integer> nums2Set = new HashSet<>();
        for (int k : nums1) {
            nums1Set.add(k);
        }
        for (int j : nums2) {
            nums2Set.add(j);
        }
        int res1 = 0;
        int res2 = 0;
        for (int num : nums1) {
            if (nums2Set.contains(num)) {
                res1++;
            }
        }

        for (int num : nums2) {
            if (nums1Set.contains(num)) {
                res2++;
            }
        }


        return new int[]{res1, res2};

    }
}
