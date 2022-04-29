package leetcode_everyday._2022.Apr;

/**
 * @author WuChao
 * @create 2022/4/29 11:15
 */
public class _29_427 {
    /**
     * 每日一题：2022/4/29
     * <p>
     * 427. 建立四叉树
     * <p>
     * 难度：medium
     * <p>
     * 给你一个 n * n 矩阵 grid ，矩阵由若干 0 和 1 组成。请你用四叉树表示该矩阵 grid 。
     * <p>
     * 你需要返回能表示矩阵的 四叉树 的根结点。
     * <p>
     * 注意，当 isLeaf 为 False 时，你可以把 True 或者 False 赋值给节点，两种值都会被判题机制 接受 。
     * <p>
     * 四叉树数据结构中，每个内部节点只有四个子节点。此外，每个节点都有两个属性：
     * <p>
     * val：储存叶子结点所代表的区域的值。1 对应 True，0 对应 False；
     * isLeaf: 当这个节点是一个叶子结点时为 True，如果它有 4 个子节点则为 False 。
     * <p>
     * 我们可以按以下步骤为二维区域构建四叉树：
     * <p>
     * 如果当前网格的值相同（即，全为 0 或者全为 1），将 isLeaf 设为 True ，将 val 设为网格相应的值，并将四个子节点都设为 Null 然后停止。
     * 如果当前网格的值不同，将 isLeaf 设为 False， 将 val 设为任意值，然后如下图所示，将当前网格划分为四个子网格。
     * 使用适当的子网格递归每个子节点。
     * <p>
     * 四叉树格式：
     * <p>
     * 输出为使用层序遍历后四叉树的序列化形式，其中 null 表示路径终止符，其下面不存在节点。
     * <p>
     * 它与二叉树的序列化非常相似。唯一的区别是节点以列表形式表示 [isLeaf, val] 。
     * <p>
     * 如果 isLeaf 或者 val 的值为 True ，则表示它在列表 [isLeaf, val] 中的值为 1 ；如果 isLeaf 或者 val 的值为 False ，
     * 则表示值为 0 。
     * <p>
     * 示例
     * <p>
     * 输入：grid = [[0,1],[1,0]]
     * <p>
     * 输出：[[0,1],[1,0],[1,1],[1,1],[1,0]]
     * <p>
     * 解释：此示例的解释如下：
     * 请注意，在下面四叉树的图示中，0 表示 false，1 表示 True 。
     * <p>
     * 范围
     * <p>
     * n == grid.length == grid[i].length
     * n == 2^x 其中 0 <= x <= 6
     */

    /*
    四叉树的定义
     */
    class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;


        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    }

    int[][] g;

    public Node construct(int[][] grid) {
        this.g = grid;
        int n = g.length;
        return dfs(0, 0, n - 1, n - 1);
    }

    private Node dfs(int a, int b, int c, int d) {
        boolean ok = true;
        int t = g[a][b];
        for (int i = a; i <= c && ok; i++) {
            for (int j = b; j <= d && ok; j++) {
                if (g[i][j] != t) {
                    ok = false;
                }
            }
        }
        if (ok) {
            return new Node(t == 1, true);
        }
        Node root = new Node(t == 1, false);
        int dx = c - a + 1, dy = d - b + 1;
        root.topLeft = dfs(a, b, a + dx / 2 - 1, b + dy / 2 - 1);
        root.topRight = dfs(a, b + dy / 2, a + dx / 2 - 1, d);
        root.bottomLeft = dfs(a + dx / 2, b, c, b + dy / 2 - 1);
        root.bottomRight = dfs(a + dx / 2, b + dy / 2, c, d);
        return root;
    }

}
