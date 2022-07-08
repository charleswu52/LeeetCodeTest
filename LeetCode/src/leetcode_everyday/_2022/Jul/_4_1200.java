package leetcode_everyday._2022.Jul;

import java.util.*;

/**
 * @author WuChao
 * @create 2022/7/8 14:47
 */
public class _4_1200 {
    /**
     * 每日一题：2022/7/4
     * <p>
     * 1200. 最小绝对差
     * <p>
     * 难度：easy
     * <p>
     * 给你个整数数组 arr，其中每个元素都 不相同。
     * <p>
     * 请你找到所有具有最小绝对差的元素对，并且按升序的顺序返回。
     * <p>
     * 示例
     * <p>
     * 输入：arr = [3,8,-10,23,19,-4,-14,27]
     * <p>
     * 输出：[[-14,-10],[19,23],[23,27]]
     * <p>
     * 范围
     * <p>
     * 2 <= arr.length <= 10^5
     * -10^6 <= arr[i] <= 10^6
     */


    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);
        Map<Integer, List<List<Integer>>> table = new HashMap<>();
        int min = Integer.MAX_VALUE, tmp;
        List<Integer> tmpList;
        for (int i = 0, j = 1; i < arr.length - 1; i++, j++) {
            tmp = Math.abs(arr[i] - arr[j]);
            if (tmp <= min) {
                min = tmp;
                tmpList = new ArrayList<>(2);
                tmpList.add(arr[i]);
                tmpList.add(arr[j]);
                if (table.containsKey(min)) {
                    table.get(min).add(tmpList);
                } else {
                    List<List<Integer>> result = new ArrayList<>();
                    result.add(tmpList);
                    table.put(min, result);
                }
            }
        }
        return table.get(min);

    }
}
