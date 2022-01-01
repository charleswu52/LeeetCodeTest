package leetcode_everyday._2021.May;

import java.util.Arrays;

/**
 * @author WuChao
 * @since 2021/5/8 上午8:21
 */
public class _8 {
    /**
     * 每日一题：2021/5/8
     * 1723. 完成所有工作的最短时间
     * 难度: hard
     * 给你一个整数数组 jobs ，其中 jobs[i] 是完成第 i 项工作要花费的时间。
     * <p>
     * 请你将这些工作分配给 k 位工人。所有工作都应该分配给工人，且每项工作只能分配给一位工人。
     * 工人的 工作时间 是完成分配给他们的所有工作花费时间的总和。请你设计一套最佳的工作分配方案，使工人的 最大工作时间 得以 最小化 。
     * <p>
     * 返回分配方案中尽可能 最小 的 最大工作时间 。
     *
     * <p>
     * 示例：
     * 输入：jobs = [1,2,4,7,8], k = 2
     * 输出：11
     * 解释：按下述方式分配工作：
     * 1 号工人：1、2、8（工作时间 = 1 + 2 + 8 = 11）
     * 2 号工人：4、7（工作时间 = 4 + 7 = 11）
     * 最大工作时间是 11 。
     *
     * <p>
     * 数据范围：
     * 1 <= k <= jobs.length <= 12
     * 1 <= jobs[i] <= 107
     */


    /*
    思路1：二分查找+回溯+剪枝

    完成所有工作的最短时间已经确定为 limit 时，如果存在可行的方案，那么对于任意长于 limit 的最短时间，都一定也存在可行的方案。
    因此我们可以考虑使用二分查找的方法寻找最小的存在可行方案的  limit 值。

    当完成所有工作的最短时间已经确定为 limit 时，我们可以利用回溯的方式来寻找方案。

    一个朴素的方案是，开辟一个大小为 k 的数组 workloads，workloads[i] 表示第 i 个工人的当前已经被分配的工作量，
    然后我们利用一个递归函数 backtrack(i) 递归地枚举第 i 个任务的分配方案，过程中实时地更新 workloads 数组。
    具体地，函数中我们检查每一个工人 j 当前已经被分配的工作量，如果被分配的工作量 workloads[j] 与当前工作的工作量 jobs[i] 之和不超过
    limit 的限制，我们即可以将该工作分配给工人 j，然后计算下一个工作 jobs[i+1] 的分配方案。
    过程中一旦我们找到了一个可行方案，我们即可以返回 true，而无需枚举完所有的方案。

    朴素的方案中，backtrack 函数的效率可能十分低下，有可能需要枚举完所有的分配方案才能得到答案，因此我们提出几个优化措施：

    1.缩小二分查找的上下限，下限为所有工作中的最大工作量，上限为所有工作的工作量之和。
        每一个工作都必须被分配，因此必然有一个工人承接了工作量最大的工作；
        在最坏情况下，只有一个工人，他必须承接所有工作。
    2.优先分配工作量大的工作。
        感性地理解，如果要求将小石子和大石块放入玻璃瓶中，优先放入大石块更容易使得工作变得简单。
        在搜索过程中，优先分配工作量小的工作会使得工作量大的工作更有可能最后无法被分配。
    3.当工人 i 还没被分配工作时，我们不给工人 i+1 分配工作。
        如果当前工人 i 和 i+1 都没有被分配工作，那么我们将工作先分配给任何一个人都没有区别，
        如果分配给工人 i 不能成功完成分配任务，那么分配给工人 i+1 也一样无法完成。
    4.当我们将工作 i 分配给工人 j，使得工人 j 的工作量恰好达到 limit，且计算分配下一个工作的递归函数返回了 false，
    此时即无需尝试将工作 ii 分配给其他工人，直接返回 false 即可。
        常规逻辑下，递归函数返回了 false，那么我们需要尝试将工作 i 分配给其他工人，假设分配给了工人 j'，那么此时工人 j'的工作量必定不多于工人 j 的工作量；
        如果存在一个方案使得分配给工人 j' 能够成功完成分配任务，那么此时必然有一个或一组工作 i'取代了工作 i 被分配给工人 j，
           否则我们可以直接将工作 i 移交给工人 j，仍然能成功完成分配任务。而我们知道工作 i'的总工作量不会超过工作 i，
           因此我们可以直接交换工作 i 与工作 i'，仍然能成功完成分配任务。这与假设不符，可知不存在这样一个满足条件的工人 j'。
     */
    /*

     */
    public int minimumTimeRequired(int[] jobs, int k) {
        Arrays.sort(jobs);
        int low = 0, high = jobs.length - 1;
        while (low < high) {
            int temp = jobs[low];
            jobs[low] = jobs[high];
            jobs[high] = temp;
            low++;
            high--;
        }
        int l = jobs[0], r = Arrays.stream(jobs).sum(); // 二分，下界是所有工作中的最大量，上界是所有工作量总和
        while (l < r) {
            int mid = (l + r) >> 1;
            if (check(jobs, k, mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    public boolean check(int[] jobs, int k, int limit) {
        int[] workloads = new int[k];
        return backtrack(jobs, workloads, 0, limit);
    }

    public boolean backtrack(int[] jobs, int[] workloads, int i, int limit) {
        if (i >= jobs.length) {
            return true;
        }
        int cur = jobs[i];
        for (int j = 0; j < workloads.length; ++j) {
            if (workloads[j] + cur <= limit) {
                workloads[j] += cur;
                if (backtrack(jobs, workloads, i + 1, limit)) {
                    return true;
                }
                workloads[j] -= cur;
            }
            // 如果当前工人未被分配工作，那么下一个工人也必然未被分配工作
            // 或者当前工作恰能使该工人的工作量达到了上限
            // 这两种情况下我们无需尝试继续分配工作
            if (workloads[j] == 0 || workloads[j] + cur == limit) {
                break;
            }
        }
        return false;
    }




}
