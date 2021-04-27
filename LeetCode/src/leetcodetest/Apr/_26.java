package leetcodetest.Apr;

import java.util.Arrays;

/**
 * @author WuChao
 * @since 2021/4/26 下午4:40
 */
public class _26 {
    /**
     * 每日一题：2021/4/26
     * 1011. 在 D 天内送达包裹的能力
     * 难度: medium
     * <p>
     * 传送带上的包裹必须在 D 天内从一个港口运送到另一个港口。
     *
     * 传送带上的第 i 个包裹的重量为 weights[i]。每一天，我们都会按给出重量的顺序往传送带上装载包裹。我们装载的重量不会超过船的最大运载重量。
     *
     * 返回能在 D 天内将传送带上的所有包裹送达的船的最低运载能力
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/capacity-to-ship-packages-within-d-days
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * <p>
     * 示例：
     * 输入：root = [5,3,6,2,4,null,8,1,null,null,null,7,9]
     * 输出：[1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
     *
     * <p>
     * 数据范围：
     * 树中节点数的取值范围是 [1, 100]
     * 0 <= Node.val <= 1000
     */

    /*
    题目解析：
    题意比较明确，抛开题目背景，给出一个数组，将数组分成D段，求D段子数组和尽量最小情况下的最大值，数组中数据的顺序不能变

    思路：使用二分搜索
    范围是【数组中最大的元素值，数组中所有元素的和】
     */

    public int shipWithinDays(int[] weights, int D) {
        int left = Arrays.stream(weights).max().getAsInt();
        int right = Arrays.stream(weights).sum();
        // 使用二分查找在满足运输天数为D的情况下的运输下限
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            // need 是运送天数，cur是当前这天已经运送的包裹重量之和
            int need = 1, cur = 0;
            for (int weight : weights) {
                if (cur + weight > mid) {
                    need++;
                    cur = 0;
                }
                cur += weight;
            }
            if (need <= D) {
                right = mid;// 降低一天可以运输的能力
            } else {
                left = mid + 1;// 增加一天可以运输的能力
            }
        }
        return left;

    }

}
