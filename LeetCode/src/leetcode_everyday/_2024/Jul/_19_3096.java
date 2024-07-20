package leetcode_everyday._2024.Jul;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * @author charles
 * @create 2024/7/20
 */
public class _19_3096 {
    /**
     * 每日一题：2024/7/19
     * 23096. 得到更多分数的最少关卡数目
     * https://leetcode.cn/problems/minimum-levels-to-gain-more-points/description/?envType=daily-question&envId=2024-07-19
     */
    public static int minimumLevels(int[] possible) {
        int sum = 0;
        List<Integer> preSum = new ArrayList<>();
        for (int i = 0; i < possible.length; i++) {
            int curScore = 0;
            if (possible[i] == 0) {
                curScore = -1;
            } else {
                curScore = 1;
            }
            sum += curScore;
            if (i == 0) {
                preSum.add(curScore);
            } else if (i != possible.length - 1) {
                preSum.add(preSum.get(i - 1) + curScore);
            }
        }
        Pair<Integer, Integer> score_count = null;
        for (int i = 0; i < preSum.size(); i++) {
            int alice = preSum.get(i);
            int bob = sum - alice;
            if (alice > bob) {
                if (score_count == null) {
                    score_count = new Pair<>(alice, i + 1);
                }else {
                    Integer scoreCount = score_count.getKey();
                    Integer countValue = score_count.getValue();
                    if (alice > scoreCount && (i + 1 < countValue)) {
                        score_count = new Pair<>(alice, i + 1);
                    }
                }
            }
        }
        return score_count != null ? score_count.getValue() : -1;
    }

    public static void main(String[] args) {
        int[] possible = {1, 1};
        System.out.println(minimumLevels(possible));
    }
}
