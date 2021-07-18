package leetcode_hot100.top80;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WuChao
 * @create 2021/7/18 8:48
 */
public class _207 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 207. 课程表
     * 难度：medium
     * <p>
     * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
     * <p>
     * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，
     * 表示如果要学习课程 ai 则 必须 先学习课程  bi 。
     * <p>
     * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
     * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
     *
     * <p>
     * 示例：
     * <p>
     * 输入：numCourses = 2, prerequisites = [[1,0]]
     * 输出：true
     * 解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
     * <p>
     * 输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
     * 输出：false
     * 解释：总共有 2 门课程。学习课程 1 之前，你需要先完成 课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
     * <p>
     * 数据范围:
     * 1 <= numCourses <= 105
     * 0 <= prerequisites.length <= 5000
     * prerequisites[i].length == 2
     * 0 <= ai, bi < numCourses
     * prerequisites[i] 中的所有课程对 互不相同
     */

    /*
    题目解析：
    将题目抽象成判断一个图是否存在环的问题
    每门课程表示一个节点，课程学习的先后顺序指定了两个节点间的有向边，这样判断是否可以选修完所有课程的问题就转换成了判断图是否存在环的问题

    思路：拓扑排序 (图的深度有限遍历 或者 广度优先遍历)

     */

    List<List<Integer>> edges; // 邻接表
    int[] visited;
    boolean res;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        this.edges = new ArrayList<>();
        this.visited = new int[numCourses];
        this.res = true;

        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]); // edges[i]:List 表示 完成课程 i 先需要完成的课程列表
        }
        for (int i = 0; i < numCourses && res; i++) {
            if (visited[i]==0) {
                dfs(i);
            }
        }
        return res;

    }

    public void dfs(int courseId) {
        visited[courseId] = 1;

        for (int preCourse : edges.get(courseId)) {  // 遍历完成courseId 先需要完成哪些课程
            if (visited[preCourse]==0) {
                dfs(preCourse);
                if (!res) {
                    return;
                }
            } else if (visited[preCourse]==1){ // 已被访问过，即递归过程中重复出现
                res = false;
                return;
            }
        }
        visited[courseId] = 2; // 当前节点访问过
    }

}
