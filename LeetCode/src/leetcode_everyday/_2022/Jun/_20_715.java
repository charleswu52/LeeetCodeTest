package leetcode_everyday._2022.Jun;

/**
 * @author WuChao
 * @create 2022/6/20 15:05
 */
public class _20_715 {
    /**
     * 每日一题：2022/6/20
     * <p>
     * 715. Range 模块
     * <p>
     * 难度：hard
     * <p>
     * Range模块是跟踪数字范围的模块。设计一个数据结构来跟踪表示为 半开区间 的范围并查询它们。
     * <p>
     * 半开区间 [left, right) 表示所有 left <= x < right 的实数 x 。
     * <p>
     * 实现 RangeModule 类:
     * <p>
     * RangeModule() 初始化数据结构的对象。
     * void addRange(int left, int right) 添加 半开区间 [left, right)，跟踪该区间中的每个实数。添加与当前跟踪的数字部分重叠的区间时，
     * 应当添加在区间 [left, right) 中尚未跟踪的任何数字到该区间中。
     * <p>
     * boolean queryRange(int left, int right) 只有在当前正在跟踪区间 [left, right) 中的每一个实数时，才返回 true ，否则返回 false 。
     * void removeRange(int left, int right) 停止跟踪 半开区间 [left, right) 中当前正在跟踪的每个实数。
     * <p>
     * 示例
     * <p>
     * 输入: root = [5,2,-3]
     * <p>
     * 输出: [2,-3,4]
     * <p>
     * 范围
     * <p>
     * 节点数在 [1, 10^4] 范围内
     * -10^5 <= Node.val <= 10^5
     */

    class RangeModule {

        public RangeModule() {

        }

        public void addRange(int left, int right) {
            update(root, 1, N, left, right - 1, 1);

        }

        public boolean queryRange(int left, int right) {
            return query(root, 1, N, left, right - 1);

        }

        public void removeRange(int left, int right) {
            update(root, 1, N, left, right - 1, -1);

        }


        // 线段树模板
        class Node {
            Node left, right;
            // 表示当前区间是否被覆盖
            boolean cover;
            int add;
        }

        private int N = (int) 1e9;
        private Node root = new Node();

        public void update(Node node, int start, int end, int l, int r, int val) {
            if (l <= start && end <= r) {
                // 1表示覆盖，-1表示取消覆盖
                node.cover = val == 1;
                node.add = val;
                return;
            }
            int mid = (start + end) >> 1;
            pushDown(node, mid - start + 1, end - mid);
            if (l <= mid) {
                update(node.left, start, mid, l, r, val);
            }
            if (r > mid) {
                update(node.right, mid + 1, end, l, r, val);
            }
            pushUp(node);

        }

        public boolean query(Node node, int start, int end, int l, int r) {
            if (l <= start && end <= r) {
                return node.cover;
            }
            int mid = (start + end) >> 1;
            pushDown(node, mid - start + 1, end - mid);
            // 查询左右子树是否被覆盖
            boolean ans = true;
            if (l <= mid) {
                ans = ans && query(node.left, start, mid, l, r);
            }
            if (r > mid) {
                ans = ans && query(node.right, mid + 1, end, l, r);
            }
            return ans;
        }


        private void pushUp(Node node) {
            node.cover = node.left.cover && node.right.cover;
        }

        // 向下更新
        private void pushDown(Node node, int leftNum, int rightNum) {
            if (node.left == null) {
                node.left = new Node();
            }
            if (node.right == null) {
                node.right = new Node();
            }
            if (node.add == 0) {
                return;
            }
            node.left.cover = node.add == 1;
            node.right.cover = node.add == 1;
            node.left.add = node.add;
            node.right.add = node.add;
            node.add = 0;
        }
    }
}
