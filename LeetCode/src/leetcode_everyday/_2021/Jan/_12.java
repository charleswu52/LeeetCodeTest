package leetcode_everyday._2021.Jan;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _12 {
    /**
     * 每日一题：2021/1/12
     *
     * 难度： hard
     * 公司共有 n 个项目和 m 个小组，每个项目要不无人接手，要不就由 m 个小组之一负责。
     * group[i] 表示第 i 个项目所属的小组，如果这个项目目前无人接手，那么 group[i] 就等于 -1。
     * （项目和小组都是从零开始编号的）小组可能存在没有接手任何项目的情况。
     * 请你帮忙按要求安排这些项目的进度，并返回排序后的项目列表：
     *
     * 同一小组的项目，排序后在列表中彼此相邻。
     * 项目之间存在一定的依赖关系，我们用一个列表 beforeItems 来表示，其中 beforeItems[i] 表示在进行第 i 个项目前（位于第 i 个项目左侧）应该完成的所有项目。
     * 如果存在多个解决方案，只需要返回其中任意一个即可。如果没有合适的解决方案，就请返回一个 空列表 。
     *
     */

    /*
    题目解析：
    指明了有n个项目，m个小组，每个项目分给对应的小组来解决，或者-1表示没有小组来接手
    要解决的问题是安排这些项目的进度，按照两个规则安排：
    1. before数组，指明了在进行该项目前应该先完成哪些项目
    2. 指定给同一小组的项目，在安排的时候应该是相邻的

    要求给出满足这些要求的项目执行方案，没有适合的解决方案，返回空
     */


    /* 思路：拓扑排序
     * 只看规则1不难看出就是一个拓扑排序的问题：
     * 将项目抽象成点，项目间依赖关系的抽象成边，即如果进行项目 i 前需要完成项目 j，那么就存在一条 j→i 的边。然后判断图中是否可以拓扑排序。
     * 但是还有一个规则就是 同一小组的项目，排序后在列表中彼此相邻 ，这意味着 组与组之间也存在依赖关系 ，
     * 故还要解决组之间的拓扑排序。基于此，解决这道题其实可以分成两步：
     * 1. 首先解决组与组的依赖关系。我们将组抽象成点，组与组的关系抽象成边，建图后判断是否存在一个拓扑排序。
     * 2. 如果存在拓扑顺序 groupTopSort，我们只要再确定组内的依赖关系。遍历组间的拓扑序 groupTopSort，
     *    对于任意的组 g，对所有属于组 g 的点再进行拓扑排序。
     *    如果能够拓扑排序，则将组 g 内部的拓扑序按顺序放入答案数组即可。
     */

    /*
    实现细节：
    注意到某些项目存在无人接手的情况，由于这些 groupId 都为 −1，为了编码方便，我们重新将其编号。
    由于已有的小组编号不会超过 m−1，因此可以将这些项目从 m 开始正序编号，这样能保证不会与已存在的小组编号冲突。
    为了减少编码的复杂度，我们可以将拓扑排序抽成一个函数进行复用，定义 topSort(deg, graph, items) 表示当前待拓扑排序的点集为 items，
    点的入度数组为 deg，点的连边关系为 graph，graph[i] 表示点 i 连出点组成的集合，
    如果不存在冲突，返回拓扑排序后的数组，否则返回一个空数组。
    在建图的过程中，如果发现两个项目属于不同的项目组，则在组间的关系图中添加对应的边，否则在组内的关系图中添加对应的边。
    编码细节请看下面的代码。
     */

    /**
     * 思路：拓扑排序
     * @param n 项目数
     * @param m 小组数
     * @param group 项目分配情况
     * @param beforeItems 项目依赖关系
     * @return
     */
    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        List<List<Integer>> groupItem = new ArrayList<List<Integer>>();
        for (int i = 0; i < n + m; ++i) {
            groupItem.add(new ArrayList<Integer>());
        }

        // 组间和组内依赖图
        List<List<Integer>> groupGraph = new ArrayList<List<Integer>>();
        for (int i = 0; i < n + m; ++i) {
            groupGraph.add(new ArrayList<Integer>());
        }
        List<List<Integer>> itemGraph = new ArrayList<List<Integer>>();
        for (int i = 0; i < n; ++i) {
            itemGraph.add(new ArrayList<Integer>());
        }

        // 组间和组内入度数组
        int[] groupDegree = new int[n + m];
        int[] itemDegree = new int[n];

        List<Integer> id = new ArrayList<Integer>();
        for (int i = 0; i < n + m; ++i) {
            id.add(i);
        }

        int leftId = m;
        // 给未分配的 item 分配一个 groupId
        for (int i = 0; i < n; ++i) {
            if (group[i] == -1) {
                group[i] = leftId;
                leftId += 1;
            }
            groupItem.get(group[i]).add(i);
        }
        // 依赖关系建图
        for (int i = 0; i < n; ++i) {
            int curGroupId = group[i];
            for (int item : beforeItems.get(i)) {
                int beforeGroupId = group[item];
                if (beforeGroupId == curGroupId) {
                    itemDegree[i] += 1;
                    itemGraph.get(item).add(i);
                } else {
                    groupDegree[curGroupId] += 1;
                    groupGraph.get(beforeGroupId).add(curGroupId);
                }
            }
        }

        // 组间拓扑关系排序
        List<Integer> groupTopSort = topSort(groupDegree, groupGraph, id);
        if (groupTopSort.size() == 0) {
            return new int[0];
        }
        int[] ans = new int[n];
        int index = 0;
        // 组内拓扑关系排序
        for (int curGroupId : groupTopSort) {
            int size = groupItem.get(curGroupId).size();
            if (size == 0) {
                continue;
            }
            List<Integer> res = topSort(itemDegree, itemGraph, groupItem.get(curGroupId));
            if (res.size() == 0) {
                return new int[0];
            }
            for (int item : res) {
                ans[index++] = item;
            }
        }
        return ans;
    }

    public List<Integer> topSort(int[] deg, List<List<Integer>> graph, List<Integer> items) {
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int item : items) {
            if (deg[item] == 0) {
                queue.offer(item);
            }
        }
        List<Integer> res = new ArrayList<Integer>();
        while (!queue.isEmpty()) {
            int u = queue.poll();
            res.add(u);
            for (int v : graph.get(u)) {
                if (--deg[v] == 0) {
                    queue.offer(v);
                }
            }
        }
        return res.size() == items.size() ? res : new ArrayList<Integer>();
    }


}
