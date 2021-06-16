package chp17;

/**
 * @author WuChao
 * @create 2021/6/16 9:57
 */
public class _24 {
    /**
     * 程序员面试金典(version 6) -  面试题 17.24. 最大子矩阵
     * 难度: hard
     * <p>
     * 给定一个正整数、负整数和 0 组成的 N × M 矩阵，编写代码找出元素总和最大的子矩阵。
     *
     * 返回一个数组 [r1, c1, r2, c2]，其中 r1, c1 分别代表子矩阵左上角的行号和列号，r2, c2 分别代表右下角的行号和列号。若有多个满足条件的子矩阵，返回任意一个均可。
     *
     * 注意：本题相对书上原题稍作改动
     * 给定一个方阵，其中每个单元(像素)非黑即白。设计一个算法，找出 4 条边皆为黑色像素的最大子方阵。
     * <p>
     * 返回一个数组 [r, c, size] ，其中 r, c 分别代表子方阵左上角的行号和列号，size 是子方阵的边长。
     * 若有多个满足条件的子方阵，返回 r 最小的，若 r 相同，返回 c 最小的子方阵。若无满足条件的子方阵，返回空数组。
     * <p>
     * 示例:
     * 输入：
     * [
     *    [-1,0],
     *    [0,-1]
     * ]
     * 输出：[0,1,0,1]
     * 解释：输入中标粗的元素即为输出所表示的矩阵
     *
     * <p>
     * 数据范围：
     * 1 <= matrix.length, matrix[0].length <= 200
     */


    /*
    在解决该题之前，先要复习一下之前的一个题目 LeetCode53. 最大子序和
    给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
    这是一个简单的dp问题
        1、状态定义：dp[i]为以nums[i]结尾的最大子序和
        2、状态转移方程：对于nums[i]有两种情况:
            一种是和前一个位置的子序列连着dp[i]=dp[i-1]+nums[i]
            第二种是以自己独立门户，从自己开始dp[i]=nums[i]
                 取其中最大值,可得状态转移方程为dp[i]=max( dp[i-1] + nums[i] , nums[i] )
        3、basecase:dp[0]=nums[0]很好理解
     */

    public int maxSubArray(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = nums[0];
        int ans = dp[0];
        for (int i = 0; i < len; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            ans = Math.max(dp[i], ans);

        }
        return ans;
    }

    /*
    在该题目的基础上，增加一个条件要求返回最大子序列和的起始和终止坐标
    在状态转换的时候记录一下即可
     */
    public int[] maxSubArray2(int[] nums){
        int maxSum = Integer.MIN_VALUE;
        int dp_i = nums[0];
        int[] ans = new int[2];
        int begin = 0;
        for (int i = 1; i < nums.length; i++) {
            if (dp_i > 0) {
                dp_i += nums[i];
            } else {
                dp_i = nums[i];
                begin = i;
            }
            if (dp_i > maxSum) {
                maxSum = dp_i;
                ans[0] = begin;
                ans[1] = i;
            }
        }
        return ans;
    }

    /*
    前序题目看完后，再看本题目

    问题从一维变成了二维，但实质是一样的，同样是再求最大子序和，我们需要将二维转化为一维，
    对于矩阵的每一列，我们将其加在一起，成为了一维上的一个数，二维矩阵的和转化为了一维数组的和
    设数组b表示数组a的i-j(0<=i<=j<=n-1)行，对应列元素的和，对数组b计算最大字段和就将二维动态规划问题转换为一维动态规划问题

    这样我们就将二维问题转化为了一维问题，现在另一个问题就是怎么把所有情况都遍历到呢？
    我们以第i行为第一行，向下延申，设最后一行为第j行，我们就i在这么一个范围内，将二维问题转化为一维问题，再求解最大子序列和
    我们将当前i~j行组成的矩阵的每一列的和存放在数组b中，其余的工作就是在求最大子序列和，并且保存其左上角和右下角
     */

    /*
    题目解析：
    显然
     */
    public int[] getMaxMatrix(int[][] matrix) {
        int N = matrix.length, M = matrix[0].length;
        int[] ans = new int[4]; // 返回结果
        int[] b = new int[M]; //记录当前i~j行组成大矩阵的每一列的和，将二维转化为一维
        int sum;    // 记录子矩阵和
        int maxSum = Integer.MIN_VALUE; // 子矩阵最大值
        int bestr1=0, bestc1=0; // 暂时记录左上角

        for (int i = 0; i < N; i++) { //以i为上边，从上而下扫描
            for (int t = 0; t < M; t++) {
                b[t] = 0;//每次更换子矩形上边，就要清空b，重新计算每列的和
            }
            for (int j = i; j < N; j++) { //子矩阵的下边，从i到N-1，不断增加子矩阵的高
                //一下就相当于求一次最大子序列和
                sum = 0;//从头开始求dp
                for (int k = 0; k < M; k++) {
                    b[k] += matrix[j][k];
                    //我们只是不断增加其高，也就是下移矩阵下边，所有这个矩阵每列的和只需要加上新加的哪一行的元素
                    //因为我们求dp[i]的时候只需要dp[i-1]和nums[i],所有在我们不断更新b数组时就可以求出当前位置的dp_i
                    if (sum > 0) {
                        sum += b[k];
                    } else {
                        sum = b[k];
                        bestr1 = i;//自立门户，暂时保存其左上角
                        bestc1 = k;
                    }
                    if (sum > maxSum) {
                        maxSum = sum;
                        ///更新答案
                        ans[0] = bestr1;
                        ans[1] = bestc1;
                        ans[2] = j;
                        ans[3] = k;
                    }
                }
            }
        }
        return ans;
    }


}
