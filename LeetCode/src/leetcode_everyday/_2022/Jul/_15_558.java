package leetcode_everyday._2022.Jul;

/**
 * @author WuChao
 * @create 2022/7/15 17:25
 */
public class _15_558 {
    /**
     * 每日一题：2022/7/15
     * <p>
     * 558. 四叉树交集
     * <p>
     * 难度：medium
     * <p>
     * 二进制矩阵中的所有元素不是 0 就是 1 。
     * <p>
     * 给你两个四叉树，quadTree1 和 quadTree2。其中 quadTree1 表示一个 n * n 二进制矩阵，而 quadTree2 表示另一个 n * n 二进制矩阵。
     * <p>
     * 请你返回一个表示 n * n 二进制矩阵的四叉树，它是 quadTree1 和 quadTree2 所表示的两个二进制矩阵进行 按位逻辑或运算 的结果。
     * <p>
     * 注意，当 isLeaf 为 False 时，你可以把 True 或者 False 赋值给节点，两种值都会被判题机制 接受 。
     * <p>
     * 四叉树数据结构中，每个内部节点只有四个子节点。此外，每个节点都有两个属性：
     * <p>
     * val：储存叶子结点所代表的区域的值。1 对应 True，0 对应 False；
     * isLeaf: 当这个节点是一个叶子结点时为 True，如果它有 4 个子节点则为 False 。
     * <p>
     * 示例
     * <p>
     * 输入：quadTree1 = [[0,1],[1,1],[1,1],[1,0],[1,0]]
     * , quadTree2 = [[0,1],[1,1],[0,1],[1,1],[1,0],null,null,null,null,[1,0],[1,0],[1,1],[1,1]]
     * <p>
     * 输出：[[0,0],[1,1],[1,1],[1,1],[1,0]]
     * <p>
     * 解释：quadTree1 和 quadTree2 如上所示。由四叉树所表示的二进制矩阵也已经给出。
     * 如果我们对这两个矩阵进行按位逻辑或运算，则可以得到下面的二进制矩阵，由一个作为结果的四叉树表示。
     * 注意，我们展示的二进制矩阵仅仅是为了更好地说明题意，你无需构造二进制矩阵来获得结果四叉树。
     * <p>
     * 范围
     * <p>
     * quadTree1 和 quadTree2 都是符合题目要求的四叉树，每个都代表一个 n * n 的矩阵。
     * n == 2^x ，其中 0 <= x <= 9.
     */


    // Definition for a QuadTree node.
    // 定义 四叉树 的节点
    class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;

        public Node() {
        }

        public Node(boolean _val, boolean _isLeaf, Node _topLeft, Node _topRight, Node _bottomLeft, Node _bottomRight) {
            val = _val;
            isLeaf = _isLeaf;
            topLeft = _topLeft;
            topRight = _topRight;
            bottomLeft = _bottomLeft;
            bottomRight = _bottomRight;
        }
    }


    public Node intersect(Node quadTree1, Node quadTree2) {
        return build(quadTree1, quadTree2);

    }

    private Node build(Node quadTree1, Node quadTree2) {
        if (quadTree1.isLeaf || quadTree2.isLeaf) {
            if (quadTree1.isLeaf && quadTree2.isLeaf) {
                return new Node(quadTree1.val | quadTree2.val, true, null, null, null, null);
            } else {
                if (quadTree1.isLeaf) {
                    if (quadTree1.val) {
                        return new Node(true, true, null, null, null, null);
                    } else {
                        return quadTree2;
                    }
                } else {
                    if (quadTree2.val) {
                        return new Node(true, true, null, null, null, null);
                    } else {
                        return quadTree1;
                    }
                }
            }
        } else {
            Node topLeft = build(quadTree1.topLeft, quadTree2.topLeft);
            Node topRight = build(quadTree1.topRight, quadTree2.topRight);
            Node bottomLeft = build(quadTree1.bottomLeft, quadTree2.bottomLeft);
            Node bottomRight = build(quadTree1.bottomRight, quadTree2.bottomRight);
            if (topLeft.val && topLeft.isLeaf
                    && topRight.val && topRight.isLeaf
                    && bottomLeft.val && bottomLeft.isLeaf
                    && bottomRight.val && bottomRight.isLeaf
            ) {
                return new Node(true, true, null, null, null, null);
            } else {
                return new Node(false, false, topLeft, topRight, bottomLeft, bottomRight);
            }
        }
    }
}
