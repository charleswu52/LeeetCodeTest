package leetcodetest.May;

import java.util.*;

/**
 * @author WuChao
 * @since 2021/5/5 下午4:19
 */
public class _1 {
    /**
     * 每日一题：2021/5/1
     * 2690. 员工的重要性
     * 难度: easy
     *
     * 给定一个保存员工信息的数据结构，它包含了员工 唯一的 id ，重要度 和 直系下属的 id 。
     *
     * 比如，员工 1 是员工 2 的领导，员工 2 是员工 3 的领导。他们相应的重要度为 15 , 10 , 5 。
     * 那么员工 1 的数据结构是 [1, 15, [2]] ，员工 2的 数据结构是 [2, 10, [3]] ，员工 3 的数据结构是 [3, 5, []] 。
     * 注意虽然员工 3 也是员工 1 的一个下属，但是由于 并不是直系 下属，因此没有体现在员工 1 的数据结构中。
     *
     * 现在输入一个公司的所有员工信息，以及单个员工 id ，返回这个员工和他所有下属的重要度之和。
     *
     * <p>
     * 输入：[[1, 5, [2, 3]], [2, 3, []], [3, 3, []]], 1
     * 输出：11
     * 解释：
     * 员工 1 自身的重要度是 5 ，他有两个直系下属 2 和 3 ，而且 2 和 3 的重要度均为 3 。因此员工 1 的总重要度是 5 + 3 + 3 = 11 。
     *
     *
     * <p>
     * 数据范围：
     * 一个员工最多有一个 直系 领导，但是可以有多个 直系 下属
     * 员工数量不超过 2000 。
     */

    class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;
    };

    /*
    思路1：最简单直接的方式，使用队列来保存需要查找的员工的ID，然后依次遍历找到对应的员工获取其 重要性 并把其子员工信息添加到队列中
     */
    public int getImportance(List<Employee> employees, int id) {
        int res = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(id);
        while (!queue.isEmpty()) {
            int employeeId = queue.poll();
            for (Employee employee : employees) {
                if (employee.id == employeeId) {
                    res += employee.importance;
                    for (Integer eId : employee.subordinates) {
                        queue.offer(eId);
                    }
                    break;
                }

            }
        }
        return res;
    }

    /*
    思路2：先遍历一遍员工列表，将每个员工对应的ID和员工添加到哈希表中方便查找
     */
    Map<Integer, Employee> map = new HashMap<>();

    public int getImportance2(List<Employee> employees, int id) {
        for (Employee employee : employees) {
            map.put(employee.id, employee);
        }
        return dfs(id);

    }

    // 使用深搜递归遍历
    public int dfs(int id) {
        Employee employee = map.get(id);
        int total = employee.importance;
        List<Integer> subordinates = employee.subordinates;
        for (int subId : subordinates) {
            total += dfs(subId);
        }
        return total;
    }
}