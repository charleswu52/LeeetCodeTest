package leetcode_everyday.Aug;

/**
 * @author WuChao
 * @create 2021/8/7 21:03
 */
public class _7_457 {
    /**
     * 每日一题：2021/8/7
     * 457. 环形数组是否存在循环
     * 难度：medium
     * <p>
     * 存在一个不含 0 的 环形 数组 nums ，每个 nums[i] 都表示位于下标 i 的角色应该向前或向后移动的下标个数：
     * <p>
     * 如果 nums[i] 是正数，向前 移动 nums[i] 步
     * 如果 nums[i] 是负数，向后 移动 nums[i] 步
     * 因为数组是 环形 的，所以可以假设从最后一个元素向前移动一步会到达第一个元素，而第一个元素向后移动一步会到达最后一个元素。
     * <p>
     * 数组中的 循环 由长度为 k 的下标序列 seq ：
     * <p>
     * 遵循上述移动规则将导致重复下标序列 seq[0] -> seq[1] -> ... -> seq[k - 1] -> seq[0] -> ...
     * 所有 nums[seq[j]] 应当不是 全正 就是 全负
     * k > 1
     * 如果 nums 中存在循环，返回 true ；否则，返回 false 。
     *
     *
     * <p>
     * 示例 1：
     * 输入：nums = [2,-1,1,2,2]
     * 输出：true
     * 解释：存在循环，按下标 0 -> 2 -> 3 -> 0 。循环长度为 3 。
     * <p>
     * 示例 2：
     * 输入：nums = [-1,2]
     * 输出：false
     * 解释：按下标 1 -> 1 -> 1 ... 的运动无法构成循环，因为循环的长度为 1 。根据定义，循环的长度必须大于 1 。
     * <p>
     * 示例 3:
     * 输入：nums = [-2,1,-1,-2,-2]
     * 输出：false
     * 解释：按下标 1 -> 2 -> 1 -> ... 的运动无法构成循环，因为 nums[1] 是正数，而 nums[2] 是负数。
     * 所有 nums[seq[j]] 应当不是全正就是全负。
     * <p>
     * 注意:
     * 1 <= nums.length <= 5000
     * -1000 <= nums[i] <= 1000
     * nums[i] != 0
     * <p>
     * 进阶：你能设计一个时间复杂度为 O(n) 且额外空间复杂度为 O(1) 的算法吗？
     */

    /*
    思路：快慢指针
    具体地，我们检查每一个节点，令快慢指针从当前点出发，快指针每次移动两步，慢指针每次移动一步，期间每移动一次，
    我们都需要检查当前单向边的方向是否与初始方向是否一致，如果不一致，我们即可停止遍历，因为当前路径必然不满足条件。
    为了降低时间复杂度，我们可以标记每一个点是否访问过，过程中如果我们的下一个节点为已经访问过的节点，则可以停止遍历。

    在实际代码中，我们无需新建一个数组记录每个点的访问情况，而只需要将原数组的对应元素置零即可（题目保证原数组中元素不为零）。
    遍历过程中，如果快慢指针相遇，或者移动方向改变，那么我们就停止遍历，并将快慢指针经过的点均置零即可。

    特别地，当 nums[i] 为 n 的整倍数时，i 的后继节点即为 i 本身，此时循环长度 k=1，不符合题目要求，因此我们需要跳过这种情况。
     */

    public boolean circularArrayLoop(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                continue;
            }
            int slow = i, fast = next(nums, i);

            // 判断非零且方向相同
            while (nums[slow] * nums[fast] > 0 && nums[slow] * nums[next(nums, fast)] > 0) {
                if (slow == fast) {
                    if (slow != next(nums, slow)) {
                        return true;
                    } else {
                        break;
                    }
                }
                slow = next(nums, slow);
                fast = next(nums, next(nums, fast));
            }

            // 置为访问过
            int add = i;
            while (nums[add] * nums[next(nums, add)] > 0) {
                int temp = add;
                add = next(nums, add);
                nums[temp] = 0;
            }
        }
        return false;

    }

    public int next(int[] nums, int cur) {
        int n = nums.length;
        return ((cur + nums[cur]) % n + n) % n;// 保证返回值在 [0,n) 中
    }

}
