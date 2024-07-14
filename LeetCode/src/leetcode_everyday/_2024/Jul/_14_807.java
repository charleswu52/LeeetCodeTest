package leetcode_everyday._2024.Jul;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author charles
 * @create 2024/7/14 8:58
 */
public class _14_807 {
    /**
     * 每日一题：2024/7/14
     * 807. 保持城市天际线
     * https://leetcode.cn/problems/find-if-array-can-be-sorted/
     */
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        if (grid.length == 0) {
            return 0;
        }
        // 1.从四个方向统计，每个的最大值
        Map<Integer, List<Integer>> dirctionMap = new HashMap<>();
        getMaxLength(grid, dirctionMap);
        // 2.便利每个方块，找到与四个方向最大值的差值，并累计
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int i1 = 0; i1 < grid[i].length; i1++) {
                int current = grid[i][i1];
                res += getDiff(current, i, i1, dirctionMap);
            }
        }
        return res;
    }

    private void getMaxLength(int[][] grid, Map<Integer, List<Integer>> diractionMap) {
        // 先统计每一行的最大值
        // west方向看到的
        List<Integer> westNums = new ArrayList<>();
        int length = grid.length;
        for (int i = 0; i < length; i++) {
            int maxNum = grid[i][0];
            for (int i1 = 0; i1 < length; i1++) {
                if (grid[i][i1] > maxNum) {
                    maxNum = grid[i][i1];
                }
            }
            westNums.add(maxNum);
        }
        // 翻转即为east方向看到的
        List<Integer> eastNums = new ArrayList<>();
        for (int i = westNums.size() - 1; i >= 0; i--) {
            eastNums.add(westNums.get(i));
        }
        // 再统计每一列的最大值
        // south方向看到的
        List<Integer> southNums = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            int maxNum = grid[0][i];
            for (int i1 = 0; i1 < length; i1++) {
                if (grid[i1][i] > maxNum) {
                    maxNum = grid[i1][i];
                }
            }
            southNums.add(maxNum);
        }
        // 翻转即为north方向看到的
        List<Integer> northNums = new ArrayList<>();
        for (int i = southNums.size() - 1; i >= 0; i--) {
            northNums.add(southNums.get(i));
        }
        // 将四个方向看到的最大值填写到map中
        diractionMap.put(1, westNums);
        diractionMap.put(2, eastNums);
        diractionMap.put(3, southNums);
        diractionMap.put(4, northNums);
    }

    private int getDiff(int num, int rowIndex,int colIndex, Map<Integer, List<Integer>> diractionMap) {
        int diffNum = 0;
        Integer west = diractionMap.get(1).get(rowIndex);
        Integer south = diractionMap.get(3).get(colIndex);
        return diffNum + Math.min((west - num) , (south - num));
    }
}
