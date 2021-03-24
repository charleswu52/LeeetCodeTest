package leetcodetest.Mar;

import org.junit.Test;

import java.util.*;

/**
 * @author WuChao
 * @since 2021/3/24 上午8:03
 */
public class _24 {
    /**
     * 每日一题：2021/3/24
     * 456. 132模式
     * 难度: medium
     * 给定一个整数序列：a1, a2, ..., an，一个132模式的子序列 ai, aj, ak 被定义为：当 i < j < k 时，ai < ak < aj。
     * 设计一个算法，当给定有 n 个数字的序列时，验证这个序列中是否含有132模式的子序列。
     * <p>
     * 示例：
     * 输入: [-1, 3, 2, 0]
     * 输出: True
     * 解释: 序列中有 3 个132模式的的子序列: [-1, 3, 2], [-1, 3, 0] 和 [-1, 2, 0].
     *
     *
     * <p>
     * 数据范围：
     * n 的值小于15000。
     */

    /*
    对于一个满足 132 模式的三元组下标 (i,j,k),枚举其中的两个下标的时间复杂度为O(n^2),极易超时
    因此可以考虑枚举其中的一个下标，并使用合适的数据结构维护另外2个下标的可能值。
    因此，对三个不同的下标有3种枚举方式。
     */

    /*
    方法1：枚举3
    3是 132 模式中的最大值，并且其出现在1 和2的中间，因此只需要从左到右枚举3的下标j，那么：
    1.由于 1 是模式中的最小值，因此我们在枚举 j 的同时，维护数组 a 中左侧元素 a[0..j-1] 的最小值，即为 1 对应的元素 a[i]。
        需要注意的是，只有 a[i] < a[j] 时，a[i] 才能作为 1 对应的元素；
    2.由于 2 是模式中的次小值，因此我们可以使用一个有序集合（例如平衡树）维护数组 a 中右侧元素 a[j+1..n−1] 中的所有值。
      当我们确定了 a[i] 和 a[j] 之后，只需要在有序集合中查询严格比 a[i] 大的那个最小的元素，即为 a[k]。
        需要注意的是，只有 a[k] < a[j] 时，a[k] 才能作为 3 对应的元素。

     */

    /**
     * 方法1：枚举3的实现
     *
     * @param nums
     * @return
     */
    public boolean find132pattern(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return false;
        }
        // 左侧最小值
        int leftMin = nums[0];
        // 右侧所有元素
        TreeMap<Integer, Integer> rightAll = new TreeMap<>();
        // 添加右侧所有元素到有序集合中
        for (int k = 2; k < n; k++) {
            rightAll.put(nums[k], rightAll.getOrDefault(nums[k], 0) + 1);
        }
        // 遍历寻找符合条件的 3
        for (int j = 1; j < n - 1; j++) {
            if (leftMin < nums[j]) {
                // ceilingKey 返回最小键大于或等于返回到给定的键，不存在的话返回null
                Integer next = rightAll.ceilingKey(leftMin + 1);
                if (next != null && next < nums[j]) {
                    return true;
                }
            }
            // 更新 1
            leftMin = Math.min(leftMin, nums[j]);
            // j 指针右移，右移前先把下一个数从右侧有序集合中-1
            rightAll.put(nums[j + 1], rightAll.get(nums[j + 1]) - 1);
            if (rightAll.get(nums[j + 1]) == 0) {
                rightAll.remove(nums[j + 1]);
            }
        }
        return false;
    }

    /*
    方法二：枚举1
    该方法需要使用到  单调栈
    下面整理该方法的思路：
    如果我们从左到右枚举 1 的下标 i，那么 j, k 的下标范围都是减少的，这样就不利于对它们进行维护。因此我们可以考虑从右到左枚举 i。
    那么我们应该如何维护 j, k 呢？在 132 模式中，如果 1<2 并且 2<3，那么根据传递性，1<3 也是成立的，那么我们可以使用下面的方法进行维护：
        我们使用一种数据结构维护所有遍历过的元素，它们作为 2 的候选元素。每当我们遍历到一个新的元素时，就将其加入数据结构中；
        在遍历到一个新的元素的同时，我们可以考虑其是否可以作为 3。如果它作为 3，那么数据结构中所有严格小于它的元素都可以作为 2，
            我们将这些元素全部从数据结构中移除，并且使用一个变量维护所有被移除的元素的最大值。
            这些被移除的元素都是可以真正作为 2 的，并且元素的值越大，那么我们之后找到 1 的机会也就越大。
    那么这个「数据结构」是什么样的数据结构呢？我们尝试提取出它进行的操作：
        它需要支持添加一个元素；
        它需要支持移除所有严格小于给定阈值的所有元素；
        上面两步操作是「依次进行」的，即我们先用给定的阈值移除元素，再将该阈值加入数据结构中。
    这就是「单调栈」。在单调栈中，从栈底到栈顶的元素是严格单调递减的。当给定阈值 x 时，我们只需要不断地弹出栈顶的元素，
    直到栈为空或者 x 严格小于栈顶元素。此时我们再将 x 入栈，这样就维护了栈的单调性。

    因此，使用单调栈作为维护 2 的数据结构，并给出下面的算法：
        1.我们用单调栈维护所有可以作为 2 的候选元素。初始时，单调栈中只有唯一的元素 a[n−1]。
            我们还需要使用一个变量 max_k 记录所有可以真正作为 2 的元素的最大值；
        2.随后我们从 n-2 开始从右到左枚举元素 a[i]：
            2.1 首先我们判断 a[i] 是否可以作为 1。如果 a[i]<max_k，那么它就可以作为 1，我们就找到了一组满足 132 模式的三元组；
            2.2 随后我们判断 a[i] 是否可以作为 3，以此找出哪些可以真正作为 2 的元素。我们将 a[i] 不断地与单调栈栈顶的元素进行比较，
                如果 a[i] 较大，那么栈顶元素可以真正作为 2，将其弹出并更新 max_k；
            2.3 最后我们将 a[i] 作为 2 的候选元素放入单调栈中。这里可以进行一个优化，即如果 a[i]≤max_k，那么我们也没有必要将 a[i] 放入栈中，
                因为即使它在未来被弹出，也不会将 max_k 更新为更大的值。
        3.在枚举完所有的元素后，如果仍未找到满足 132132 模式的三元组，那就说明其不存在。
     */

    /**
     * 枚举1的方法实现
     *
     * @param nums
     * @return
     */
    public boolean find132pattern2(int[] nums) {
        int n = nums.length;
        Deque<Integer> candidateK = new LinkedList<>(); // 维护的单调栈
        candidateK.push(nums[n - 1]);
        int maxK = Integer.MIN_VALUE;

        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] < maxK) {
                return true;
            }
            while (!candidateK.isEmpty() && nums[i] > candidateK.peek()) {
                maxK = candidateK.pop();
            }
            if (nums[i] > maxK) {
                candidateK.push(nums[i]);
            }
        }
        return false;
    }


    /*
    方法3：枚举2 (在方法二的基础上，对单调栈进行二分查找)
    我们枚举 22 的下标 kk 时，与方法二相反，从左到右进行枚举的方法是十分合理的：在枚举的过程中，i,j 的下标范围都是增加的。
    由于我们需要保证 1<2 并且 2<3，那么我们需要维护一系列尽可能小的元素作为 1 的候选元素，并且维护一系列尽可能大的元素作为 3 的候选元素。
    我们可以分情况进行讨论，假设当前有一个小元素 x_i 以及一个大元素 x_j 表示一个二元组，而我们当前遍历到了一个新的元素 x=a[k]，那么：
        如果 x > x_j，那么让 x 作为 3 显然是比 x_j 作为 3 更优，因此我们可以用 x 替代 x_j；
        如果 x < x_i，那么让 x 作为 1 显然是比 x_i 作为 3 更优，然而我们必须要满足 132 模式中的顺序，即 1 出现在 3 之前，
            这里如果我们简单地用 x 替代 x_i，那么 x_i=x 作为 1 是出现在 x_j 作为 3 之后的，这并不满足要求。
            因此我们需要为 x 找一个新的元素作为 3。由于我们还没有遍历到后面的元素，因此可以简单地将 x 同时看作一个二元组的 x_i 和 x_j；
        对于其它的情况，x_i≤x≤x_j，x 无论作为 1 还是 3 都没有当前二元组对应的要优，因此我们可以不用考虑 x 作为 1 或者 3 的情况。

    这样一来，与方法二类似，我们使用两个单调递减的单调栈维护一系列二元组 (x_i, x_j),表示一个可以选择的 1−3 区间，并且从栈底到栈顶 x_i和
    x_j分别严格单调递减，因为根据上面的讨论，我们只有在 x < x_i 时才会增加一个新的二元组。
    然而与方法二不同的是，如果我们想让 x 作为 2，那么我们并不知道到底应该选择单调栈中的哪个 1−3 区间，因此我们只能根据单调性进行二分查找：
        对于单调栈中的 x_i，我们需要找出第一个满足 x_i < x 的位置 idxi，这样从该位置到栈顶的所有二元组都满足 x_i < x；
        对于单调栈中的 x_j，我们需要找出最后一个满足 x_j > x的位置 idxj，这样从栈底到该位置的所有二元组都满足 x_j > x；
        如果 idxi 和 idxj 都存在，并且 idxi ≤idxj，那么就存在至少一个二元组 (x_i, x_j) 满足 x_i < x < x_j，x 就可以作为 2，
            我们就找到了一组满足 132 模式的三元组。

    在枚举完所有的元素后，如果仍未找到满足 132132 模式的三元组，那就说明其不存在。

    注意：
    需要注意的是，我们是在单调递减的栈上进行二分查找，因此大部分语言都需要实现一个自定义比较函数，或者将栈中的元素取相反数后再使用默认的比较函数。
     */

    /**
     * 方法3：枚举2 的实现
     * @param nums
     * @return
     */
    public boolean find132pattern3(int[] nums) {
        int n = nums.length;
        List<Integer> candidateI = new ArrayList<Integer>();
        candidateI.add(nums[0]);
        List<Integer> candidateJ = new ArrayList<Integer>();
        candidateJ.add(nums[0]);

        for (int k = 1; k < n; ++k) {
            int idxI = binarySearchFirst(candidateI, nums[k]);
            int idxJ = binarySearchLast(candidateJ, nums[k]);
            if (idxI >= 0 && idxJ >= 0) {
                if (idxI <= idxJ) {
                    return true;
                }
            }

            if (nums[k] < candidateI.get(candidateI.size() - 1)) {
                candidateI.add(nums[k]);
                candidateJ.add(nums[k]);
            } else if (nums[k] > candidateJ.get(candidateJ.size() - 1)) {
                int lastI = candidateI.get(candidateI.size() - 1);
                while (!candidateJ.isEmpty() && nums[k] > candidateJ.get(candidateJ.size() - 1)) {
                    candidateI.remove(candidateI.size() - 1);
                    candidateJ.remove(candidateJ.size() - 1);
                }
                candidateI.add(lastI);
                candidateJ.add(nums[k]);
            }
        }

        return false;
    }

    public int binarySearchFirst(List<Integer> candidate, int target) {
        int low = 0, high = candidate.size() - 1;
        if (candidate.get(high) >= target) {
            return -1;
        }
        while (low < high) {
            int mid = (high - low) / 2 + low;
            int num = candidate.get(mid);
            if (num >= target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public int binarySearchLast(List<Integer> candidate, int target) {
        int low = 0, high = candidate.size() - 1;
        if (candidate.get(low) <= target) {
            return -1;
        }
        while (low < high) {
            int mid = (high - low + 1) / 2 + low;
            int num = candidate.get(mid);
            if (num <= target) {
                high = mid - 1;
            } else {
                low = mid;
            }
        }
        return low;
    }


    @Test
    public void test() {
        int[] nums = {-1, 3, 2, 0};
        System.out.println(find132pattern(nums));
        System.out.println(find132pattern2(nums));
        System.out.println(find132pattern3(nums));

    }
}
