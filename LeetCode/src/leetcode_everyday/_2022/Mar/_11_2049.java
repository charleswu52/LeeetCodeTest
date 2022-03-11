package leetcode_everyday._2022.Mar;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WuChao
 * @create 2022/3/11 8:55
 */
public class _11_2049 {
    /**
     * 每日一题：2022/3/11
     * <p>
     * 2049. 统计最高分的节点数目
     * <p>
     * 难度：medium
     * <p>
     * 给你一棵根节点为 0 的 二叉树 ，它总共有 n 个节点，节点编号为 0 到 n - 1。
     * 同时给你一个下标从 0 开始的整数数组 parents 表示这棵树，其中 parents[i] 是节点 i 的父节点。
     * 由于节点 0 是根，所以 parents[0] == -1 。
     * <p>
     * 一个子树的 大小 为这个子树内节点的数目。每个节点都有一个与之关联的 分数 。
     * 求出某个节点分数的方法是，将这个节点和与它相连的边全部 删除 ，剩余部分是若干个 非空 子树，这个节点的 分数 为所有这些子树 大小的乘积 。
     * <p>
     * 请你返回有 最高得分 节点的 数目 。
     * <p>
     * 示例1
     * <p>
     * 输入：parents = [-1,2,0,2,0]
     * <p>
     * 输出：3
     * <p>
     * 解释：
     * - 节点 0 的分数为：3 * 1 = 3
     * - 节点 1 的分数为：4 = 4
     * - 节点 2 的分数为：1 * 1 * 2 = 2
     * - 节点 3 的分数为：4 = 4
     * - 节点 4 的分数为：4 = 4
     * 最高得分为 4 ，有三个节点得分为 4 （分别是节点 1，3 和 4 ）。
     * <p>
     * 范围
     * <p>
     * 节点总数在范围 [0, 10^4]内
     * 0 <= Node.val <= 10^4
     * n 叉树的高度小于或等于 1000
     * <p>
     * 进阶：递归法很简单，你可以使用迭代法完成此题吗?
     */

    /*
    思路：深度优先搜索
    在一棵树中，当把一个节点和与它相连的所有边删除，剩余部分最多为三棵非空子树，即原节点的左子树（如果有），右子树（如果有），
    以及把以这个节点为根结点的子树移除所形成的子树（除根结点外均有）。而这个节点的分数为这些子树的节点个数的乘积。
    我们可以先用 parents 数组统计出每个节点的子节点，然后使用深度优先搜索来计算以每个节点为根结点的子树的大小，同时计算每个节点的大小，
    作为深度优先搜索的返回值，最后统计最大分数出现的次数。在实现上，统计最大分数出现的次数可以放到深度优先搜索中完成，从而节省一部分空间。
     */
    long maxScore = 0;
    int cnt = 0;
    int n;
    List<Integer>[] children;
    public int countHighestScoreNodes(int[] parents) {
        n = parents.length;
        children = new List[n];
        for (int i = 0; i < n; i++) {
            children[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            int p = parents[i];
            if (p != -1) {
                children[p].add(i);
            }
        }
        dfs(0);
        return cnt;

    }

    public int dfs(int node) {
        long score = 1;
        int size = n - 1;
        for (int child : children[node]) {
            int temp = dfs(child);
            score *= temp;
            size -= temp;
        }
        if (node != 0) {
            score *= size;
        }
        if (score == maxScore) {
            cnt++;
        } else if (score > maxScore) {
            maxScore = score;
            cnt = 1;
        }
        return n - size;
    }

}
