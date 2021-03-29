import org.junit.Test;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author WuChao
 * @since 2021/3/29 下午12:30
 */
public class _59_1 {
    /**
     * 剑指 Offer 59 - I. 滑动窗口的最大值
     * 难度: easy
     * <p>
     * 给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
     *
     * <p>
     * 例如：
     * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
     * 输出: [3,3,5,5,6,7]
     * 解释:
     * 滑动窗口的位置                最大值
     * ---------------               -----
     * [1  3  -1] -3  5  3  6  7       3
     * 1 [3  -1  -3] 5  3  6  7       3
     * 1  3 [-1  -3  5] 3  6  7       5
     * 1  3  -1 [-3  5  3] 6  7       5
     * 1  3  -1  -3 [5  3  6] 7       6
     * 1  3  -1  -3  5 [3  6  7]      7
     *
     * <p>
     * 数据范围：
     * 你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。
     */

    /**
     * 滑动窗口的最大值
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums.length == 0 || k == 0) return new int[0];
        Deque<Integer> deque = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];
        for(int j = 0, i = 1 - k; j < nums.length; i++, j++) {
            // 删除 deque 中对应的 nums[i-1]
            if(i > 0 && deque.peekFirst() == nums[i - 1])
                deque.removeFirst();
            // 保持 deque 递减
            while(!deque.isEmpty() && deque.peekLast() < nums[j])
                deque.removeLast();
            deque.addLast(nums[j]);
            // 记录窗口最大值
            if(i >= 0)
                res[i] = deque.peekFirst();
        }
        return res;
    }

    public int[] maxSlidingWindow2(int[] nums, int k) {
        if(nums.length == 0 || k == 0) return new int[0];
        //窗口个数
        int[] res = new int[nums.length - k + 1];
        LinkedList<Integer> queue = new LinkedList<>();

        // 遍历数组中的元素，right表示滑动窗口右边界
        for (int right = 0; right < nums.length; right++) {
            // 如果队列不为空且当前元素大于等于队尾元素，则将队尾元素移除，直到队列为或当前考察元素小于新的队尾元素
            while (!queue.isEmpty() && nums[right] >= nums[queue.peekLast()]) {
                queue.removeLast();
            }
            // 存储元素的下标
            queue.add(right);
            // 计算窗口左侧边界
            int left = right - k + 1;
            // 当队首元素下标小于滑动窗口左侧边界left时，表示队首元素已经不在滑动窗口内，因此将其从队首移除
            if (queue.peekFirst() < left) {
                queue.removeFirst();
            }

            // 由于数组下标从0开始，因此当窗口右侧边界right+1大于等于窗口大小k时，，意味着窗口形成，此时，队首元素就是该窗口内的最大值
            if (right + 1 >= k) {
                res[left] = nums[queue.peekFirst()];
            }

        }
        return res;
    }


        @Test
    public void test() {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        System.out.println(Arrays.toString(maxSlidingWindow(nums, k)));
    }
}
