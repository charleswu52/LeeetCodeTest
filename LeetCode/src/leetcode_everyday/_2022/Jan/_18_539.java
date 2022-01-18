package leetcode_everyday._2022.Jan;

import java.util.Arrays;
import java.util.List;

/**
 * @author WuChao
 * @create 2022/1/18 9:26
 */
public class _18_539 {
    /**
     * 每日一题：2022/1/18
     * <p>
     * 539. 最小时间差
     * <p>
     * 难度：hard
     * <p>
     * 给你一个整数 n，请你帮忙统计一下我们可以按下述规则形成多少个长度为 n 的字符串：
     * <p>
     * 给定一个 24 小时制（小时:分钟 "HH:MM"）的时间列表，找出列表中任意两个时间的最小时间差并以分钟数表示。
     * <p>
     * 示例 1：
     * <p>
     * 输入：timePoints = ["00:00","23:59","00:00"]
     * <p>
     * 输出：0
     * <p>
     * 范围
     * 2 <= timePoints <= 2 * 104
     * timePoints[i] 格式为 "HH:MM"
     */

    /*
    思路1: 排序
    将每个时间转换成距离0点多少分钟的格式，然后将所有数进行排序
    两个时间的最小值就一定出现在中间或者头尾两个时间，遍历计算最小差值即可
     */
    public int findMinDifference(List<String> timePoints) {
        int len = timePoints.size();
        int[] store = new int[len];
        for (int i = 0; i < len; i++) {
            store[i] = calculate(timePoints.get(i));
        }
        Arrays.sort(store);
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i < len; i++) {
            int i1 = store[i] - store[i - 1];
            if (i1 < ans) {
                ans = i1;
            }
        }
        int ans1 = 24 * 60 - store[len - 1] + store[0];
        if (ans1 <ans){
            ans = ans1;
        }
        return ans;
    }

    /*
    辅助函数：将 HH:MM 格式的时间转换成距离0点的分钟格式
     */
    public int calculate(String time) {
        String[] split = time.split(":");
        int ans = 0;
        ans += Integer.parseInt(split[0]) * 60;
        ans += Integer.parseInt(split[1]);
        return ans;
    }

}
