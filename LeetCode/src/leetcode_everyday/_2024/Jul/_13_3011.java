package leetcode_everyday._2024.Jul;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author WuChao
 * @create 2024/7/13
 */
public class _13_3011 {
    /**
     * 每日一题：2024/7/14
     * https://leetcode.cn/problems/find-if-array-can-be-sorted/
     */

    public static boolean canSortArray(int[] nums) {
        if (nums.length == 0) {
            return true;
        }
        // 1.将数组中的每个数字计算得到二进制中1的个数
        List<Integer> binaryList = new ArrayList<>();
        for (int num : nums) {
            binaryList.add(getBinaryOneCount(num));
        }
        // 2.对二进制中1的个数进行分段，分割点的下标记录到数组中
        List<Integer> indexList = new ArrayList<>();
        for (int i = 0; i < binaryList.size()-1; i++) {
            if (!Objects.equals(binaryList.get(i), binaryList.get(i + 1))) {
                indexList.add(i);
            }
        }
        if (indexList.isEmpty()) {
            return true;
        }
        // 3.对分段后的数组统计最大值，分割点的下标记录到数组中
        List<Pair<Integer,Integer>> segmentNum = new ArrayList<>();
        for (int i = 0; i <= indexList.size(); i++) {
            int minIndex;
            int maxIndex;

            if (i == 0) {
                minIndex = 0;
            } else {
                minIndex = indexList.get(i - 1) + 1;
            }

            if (i == indexList.size()) {
                maxIndex = nums.length - 1;
            } else {
                maxIndex = indexList.get(i);
            }
            segmentNum.add(getMaxNum(nums, minIndex, maxIndex));
        }
        // 4.验证分段中每段的最大值是否小于前面后面段中的最小值
        for (int i = 0; i < segmentNum.size()-1; i++) {
            Pair<Integer, Integer> integerIntegerPair = segmentNum.get(i);
            Pair<Integer, Integer> integerIntegerPair1 = segmentNum.get(i + 1);
            if (integerIntegerPair.getValue() > integerIntegerPair1.getKey()) {
                return false;
            }

        }
        // 打印下每阶段的结果
//        System.out.println(Arrays.toString(nums));
//        System.out.println(binaryList);
//        System.out.println(indexList);
//        System.out.println(segmentNum);
        return true;
    }

    private static int getBinaryOneCount(int num) {
        int count = 0;
        while (num != 0) {
            if ((num & 1) == 1) {
                count++;
            }
            num = num >> 1;
        }
        return count;
    }

    private static Pair<Integer,Integer> getMaxNum(int[] nums, int minIndex, int maxIndex) {
        int maxNum = nums[minIndex];
        int minNum = nums[minIndex];
        for (int i = minIndex; i <= maxIndex; i++) {
            if (nums[i] > maxNum) {
                maxNum = nums[i];
            }
            if (nums[i] < minNum) {
                minNum = nums[i];
            }
        }
        return new Pair<>(minNum, maxNum);
    }

    public static void main(String[] args) {
        int[] nums = {3,16,8,4,2};
        System.out.println(canSortArray(nums));
    }

}
