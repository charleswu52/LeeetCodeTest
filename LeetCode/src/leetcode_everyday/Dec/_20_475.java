package leetcode_everyday.Dec;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author WuChao
 * @create 2021/12/20 上午9:19
 */
public class _20_475 {
    /**
     * 每日一题：2021/12/20
     * <p>
     * 475. 供暖器
     * <p>
     * 难度：medium
     * <p>
     * 冬季已经来临。你的任务是设计一个有固定加热半径的供暖器向所有房屋供暖。
     * <p>
     * 在加热器的加热半径范围内的每个房屋都可以获得供暖。
     * <p>
     * 现在，给出位于一条水平线上的房屋 houses 和供暖器 heaters 的位置，请你找出并返回可以覆盖所有房屋的最小加热半径。
     * <p>
     * 说明：所有供暖器都遵循你的半径标准，加热的半径也一样。
     * <p>
     * 示例 1：
     * <p>
     * 输入: houses = [1,2,3], heaters = [2]
     * <p>
     * 输出: 1
     * <p>
     * 解释: 仅在位置2上有一个供暖器。如果我们将加热半径设为1，那么所有房屋就都能得到供暖。
     * <p>
     * 示例 2：
     * <p>
     * 输入: houses = [1,2,3,4], heaters = [1,4]
     * <p>
     * 输出: 1
     * <p>
     * 解释: 在位置1, 4上有两个供暖器。我们需要将加热半径设为1，这样所有房屋就都能得到供暖。
     *
     * <p>
     * 范围
     * <p>
     * 1 <= houses.length, heaters.length <= 3 * 10^4
     * 1 <= houses[i], heaters[i] <= 10^9
     **/

    /*
    思路1:map
        会超时
    思路2:二分查找
     */
    Map<Integer, Integer> map = new HashMap<>();
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int left = 0, right = (int) 1e9;
        while (left < right) {
            int mid = left + right >> 1;
            if (helper(houses, heaters, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;

    }

    public boolean helper(int[] houses, int[] heaters, int x) {
        int n = houses.length, m = heaters.length;
        for (int i = 0, j = 0; i < n; i++) {
            while (j < m && houses[i] > heaters[j] + x) {
                j++;
            }
            if (j < m && heaters[j] - x <= houses[i] && houses[i] <= heaters[j] + x) {
                continue;
            }
            return false;
        }
        return true;
    }



    // 思路1:会超时
    public int findRadius2(int[] houses, int[] heaters) {
        for (int house : houses) {
            map.put(house, 0);
        }
        int res = -1;
        while (!check()) {
            res++;
            reset();
            for (int heater : heaters) {
                for (int i = heater-res; i <=heater+res ; i++) {
                    if (map.containsKey(i)) {
                        Integer integer = map.get(i);
                        map.put(i, integer + 1);
                    }
                }
            }
        }
        return res;

    }



    public void reset() {
        for (int key : map.keySet()) {
            map.put(key, 0);
        }
    }
    public boolean check() {
        for (int val : map.values()) {
            if (val == 0) {
                return false;
            }
        }
        return true;
    }

}
