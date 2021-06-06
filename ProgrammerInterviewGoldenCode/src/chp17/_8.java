package chp17;

import org.junit.Test;

import java.util.*;

/**
 * @author WuChao
 * @since 2021/6/5 上午11:57
 */
public class _8 {
    /**
     * 程序员面试金典(version 6) -  面试题 17.08. 马戏团人塔
     * 难度: medium
     * <p>
     * 有个马戏团正在设计叠罗汉的表演节目，一个人要站在另一人的肩膀上。出于实际和美观的考虑，在上面的人要比下面的人矮一点且轻一点。
     * 已知马戏团每个人的身高和体重，请编写代码计算叠罗汉最多能叠几个人。
     *
     *
     * <p>
     * 示例:
     * 输入：height = [65,70,56,75,60,68] weight = [100,150,90,190,95,110]
     * 输出：6
     * 解释：从上往下数，叠罗汉最多能叠 6 层：(56,90), (60,95), (65,100), (68,110), (70,150), (75,190)
     * <p>
     *
     * <p>
     * 数据范围：
     * height.length == weight.length <= 10000
     */

    public int bestSeqAtIndex(int[] height, int[] weight) {
        int len = height.length;
        if (len <= 1) {
            return len;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            map.put(i, height[i]);
        }
        List<Map.Entry<Integer, Integer>> list = new ArrayList<Map.Entry<Integer, Integer>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o1.getValue()-o2.getValue();
            }
        });
        int[] dp = new int[len + 1];
        Arrays.fill(dp, 1);
        dp[0] = 0;
        Iterator<Map.Entry<Integer, Integer>> iterator = list.iterator();
        int pre = iterator.next().getKey();
        int idx = 2, res = Integer.MIN_VALUE;
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> next = iterator.next();
            int cur = next.getKey();
            if (weight[cur] > weight[pre] && height[cur]>height[pre]) {
                dp[idx] = Math.max(dp[idx], dp[idx - 1] + 1);
            }
            res = Math.max(dp[idx], res);
            idx++;
        }


        return res;
    }

    public int bestSeqAtIndex2(int[] height, int[] weight) {
        int len = height.length;
        int[][] person = new int[len][2];

        for (int i = 0; i < len; i++) {
            person[i] = new int[]{height[i], weight[i]};
        }
        // 按身高升序排列，升高相同的体重按逆序排列
        // lambda 写法
        Arrays.sort(person, (o1, o2) -> o1[0] == o2[0] ? o2[1] - o1[1] : o1[0] - o2[0]);

        // 转换成最长递增子序列的问题
        int[] dp = new int[len];
        int res = 0;
        for (int[] pair : person) {
            int i = Arrays.binarySearch(dp, 0, res, pair[1]);
            if (i < 0)
                i = -(i + 1);
            dp[i] = pair[1];
            if (i == res)
                ++res;
        }
        return res;
    }


        @Test
    public void test() {
        int[] height = {2868,5485,1356,1306,6017,8941,7535,4941,6331,6181};
        int[] weight = {5042,3995,7985,1651,5991,7036,9391,428,7561,8594};
        System.out.println(bestSeqAtIndex(height, weight));
    }

}
