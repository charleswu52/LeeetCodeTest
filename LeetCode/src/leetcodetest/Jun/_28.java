package leetcodetest.Jun;

import java.util.*;

/**
 * @author WuChao
 * @create 2021/6/28 8:41
 */
public class _28 {
    /**
     * 每日一题：2021/6/28
     * 815. 公交路线
     * 难度: hard
     * <p>
     * 给你一个数组 routes ，表示一系列公交线路，其中每个 routes[i] 表示一条公交线路，第 i 辆公交车将会在上面循环行驶。
     *
     * 例如，路线 routes[0] = [1, 5, 7] 表示第 0 辆公交车会一直按序列 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... 这样的车站路线行驶。
     * 现在从 source 车站出发（初始时不在公交车上），要前往 target 车站。 期间仅可乘坐公交车。
     *
     * 求出 最少乘坐的公交车数量 。如果不可能到达终点车站，返回 -1 。
     *
     *
     *
     * <p>
     * 示例
     * 输入：routes = [[1,2,7],[3,6,7]], source = 1, target = 6
     * 输出：2
     * 解释：最优策略是先乘坐第一辆公交车到达车站 7 , 然后换乘第二辆公交车到车站 6 。
     *
     * 输入：routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
     * 输出：-1
     * <p>
     * 数据范围：
     * 1 <= routes.length <= 500.
     * 1 <= routes[i].length <= 105
     * routes[i] 中的所有值 互不相同
     * sum(routes[i].length) <= 105
     * 0 <= routes[i][j] < 106
     * 0 <= source, target < 106
     */

    /*
    思路：还是BFS
     */
    public int numBusesToDestination(int[][] routes, int source, int target) {
        int routeLens = routes.length;
        // 出发地和目的地相同的 不用坐车
        if (source == target) {
            return 0;
        }
        Map<Integer, List<Integer>> stationToRoute = new HashMap<>();
        Map<Integer, List<Integer>> routeToStation = new HashMap<>();
        for (int i = 0; i < routeLens; i++) {
            List<Integer> stationList = new ArrayList<>();
            for (int station : routes[i]) {
                stationList.add(station);
                if (!stationToRoute.containsKey(station)) {
                    int finalI = i;
                    stationToRoute.put(station, new ArrayList<Integer>() {{
                        add(finalI);
                    }});
                } else {
                    List<Integer> list = stationToRoute.get(station);
                    list.add(i);
                    stationToRoute.put(station, list);
                }
            }
            routeToStation.put(i, stationList);
        }
        Map<Integer, List<Integer>> routeToRoute = new HashMap<>();
        for (int i = 0; i < routeLens; i++) {
            List<Integer> routeList = new ArrayList<>();
            for (int station : routes[i]) {
                for (int r : stationToRoute.get(station)) {
                    if (r != i) {
                        routeList.add(r);
                    }
                }
            }
            routeToRoute.put(i, routeList);
        }

        int res = 0;
        Queue<Integer> routesQueue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        for (int candidateRoute : stationToRoute.get(source)) {
            routesQueue.offer(candidateRoute);
        }
        while (!routesQueue.isEmpty()) {
            res++;
            int size = routesQueue.size();
            for (int i = 0; i < size; i++) {
                int curRoute = routesQueue.poll();
                if (!visited.contains(curRoute)) {
                    visited.add(curRoute);
                    if (routeToStation.get(curRoute).contains(target)) {
                        return res;
                    }
                    for (int nextRoute : routeToRoute.get(curRoute)) {
                        if (!visited.contains(nextRoute)) {
                            routesQueue.offer(nextRoute);
                        }
                    }
                }
            }
        }

        return -1;
    }


}
