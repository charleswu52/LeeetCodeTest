package leetcode_everyday._2022.Apr;

/**
 * @author WuChao
 * @create 2022/4/4 9:13
 */
public class _4_307 {
    /**
     * 每日一题：2022/4/4
     * <p>
     * 307. 区域和检索 - 数组可修改
     * <p>
     * 难度：medium
     * <p>
     * 给你一个数组 nums ，请你完成两类查询。
     * <p>
     * 其中一类查询要求 更新 数组 nums 下标对应的值
     * 另一类查询要求返回数组 nums 中索引 left 和索引 right 之间（ 包含 ）的nums元素的 和 ，其中 left <= right
     * 实现 NumArray 类：
     * <p>
     * NumArray(int[] nums) 用整数数组 nums 初始化对象
     * void update(int index, int val) 将 nums[index] 的值 更新 为 val
     * int sumRange(int left, int right) 返回数组 nums 中索引 left 和索引 right 之间（ 包含 ）的nums元素的 和
     * （即，nums[left] + nums[left + 1], ..., nums[right]）
     * <p>
     * 在比较时，字母是依序循环出现的。举个例子：
     * <p>
     * 如果目标字母 target = 'z' 并且字符列表为 letters = ['a', 'b']，则答案返回 'a'
     * <p>
     * 示例
     * <p>
     * 输入：
     * <p>
     * ["NumArray", "sumRange", "update", "sumRange"]
     * [[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
     * <p>
     * 输出：
     * <p>
     * [null, 9, null, 8]
     * <p>
     * 解释：
     * <p>
     * NumArray numArray = new NumArray([1, 3, 5]);
     * numArray.sumRange(0, 2); // 返回 1 + 3 + 5 = 9
     * numArray.update(1, 2);   // nums = [1,2,5]
     * numArray.sumRange(0, 2); // 返回 1 + 2 + 5 = 8
     * <p>
     * 范围
     * <p>
     * 1 <= nums.length <= 3 * 10^4
     * -100 <= nums[i] <= 100
     * 0 <= index < nums.length
     * -100 <= val <= 100
     * 0 <= left <= right < nums.length
     * 调用 pdate 和 sumRange 方法次数不大于 3 * 104
     */

    /*
    思路：树状数组
     */
    class NumArray {
        int[] tree;

        int lowbit(int x) {
            return x & -x;
        }

        int query(int x) {
            int ans = 0;
            for (int i = x; i > 0; i -= lowbit(i)) {
                ans += tree[i];
            }
            return ans;
        }

        void add(int x, int u) {
            for (int i = x; i <= n; i += lowbit(i)) {
                tree[i] += u;
            }
        }

        int[] nums;
        int n;

        public NumArray(int[] _nums) {
            nums = _nums;
            n = nums.length;
            tree = new int[n + 1];
            for (int i = 0; i < n; i++) add(i + 1, nums[i]);
        }

        public void update(int i, int val) {
            add(i + 1, val - nums[i]);
            nums[i] = val;
        }

        public int sumRange(int l, int r) {
            return query(r + 1) - query(l);
        }

    }
}
