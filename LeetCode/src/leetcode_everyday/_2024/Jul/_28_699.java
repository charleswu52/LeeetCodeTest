
package leetcode_everyday._2024.Jul;

import javafx.util.Pair;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author charles
 * @create 2024/7/28
 */
public class _28_699 {
    /**
     * 每日一题：2024/7/27
     * 699. 掉落的方块
     * https://leetcode.cn/problems/falling-squares/description/
     * */


    public List<Integer> fallingSquares(int[][] positions) {
        List<Integer> res = new ArrayList<>();
        Map<Pair<Integer, Integer>,Integer> pairMap = new HashMap<>();
        pairMap.put(new Pair<>(0, 101000000), 0);
        int max = 0;
        for (int[] position : positions) {
            int curL = position[0], curR = curL + position[1];
            int h = 0;
            for (Map.Entry<Pair<Integer, Integer>, Integer> pairIntegerEntry : pairMap.entrySet()) {
                Pair<Integer, Integer> key = pairIntegerEntry.getKey();
                int left = key.getKey(), right = key.getValue();
                if (curL < right && curR > left) {
                    h = Math.max(h, pairIntegerEntry.getValue());
                }
            }
            pairMap.put(new Pair<>(curL, curR), h + position[1]);
            max = Math.max(max, h + position[1]);
            res.add(max);
        }
        return res;
    }

    @Test
    public  void test() {
        String a = "asssszzeeaahhgg";
    }
}
