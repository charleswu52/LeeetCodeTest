package leetcode_everyday._2021.Jun;

/**
 * @author WuChao
 * @create 2021/6/15 上午9:14
 */
public class _13 {
    /**
     * 每日一题：2021/6/13
     * 278. 第一个错误的版本
     * 难度: easy
     * <p>
     * 你是产品经理，目前正在带领一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。
     * 由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。
     *
     * 假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。
     *
     * 你可以通过调用 bool isBadVersion(version) 接口来判断版本号 version 是否在单元测试中出错。实现一个函数来查找第一个错误的版本。
     * 你应该尽量减少对调用 API 的次数。
     *
     * <p>
     * 示例:
     * 给定 n = 5，并且 version = 4 是第一个错误的版本。
     *
     * 调用 isBadVersion(3) -> false
     * 调用 isBadVersion(5) -> true
     * 调用 isBadVersion(4) -> true
     *
     * 所以，4 是第一个错误的版本。
     * <p>
     * 数据范围：
     */

    /*
    思路：二分查找
     */
    public int firstBadVersion(int n) {
        int left = 1, right = n;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (isBadVersion(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;

    }


    /* The isBadVersion API is defined in the parent class VersionControl.
    * 没有具体实现，为了编译不报错写的
    * */
      boolean isBadVersion(int version){
          return true;
      }
}
