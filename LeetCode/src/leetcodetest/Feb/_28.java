package leetcodetest.Feb;

/**
 * @author WuChao
 * @since 2021/3/1 下午10:41
 */
public class _28 {
    /**
     * 每日一题：2021/2/28
     * 896. 单调数列
     * 难度: easy
     * 如果数组是单调递增或单调递减的，那么它是单调的。
     * 如果对于所有 i <= j，A[i] <= A[j]，那么数组 A 是单调递增的。 如果对于所有 i <= j，A[i]> = A[j]，那么数组 A 是单调递减的。
     * 当给定的数组 A 是单调数组时返回 true，否则返回 false。
     * <p>
     * 输入：[1,2,2,3]
     * 输出：true
     * 数据范围：
     * 1 <= A.length <= 50000
     * -100000 <= A[i] <= 100000
     */
    public boolean isMonotonic(int[] A) {
        int len = A.length;
        if(len<2){
            return true;
        }
        int flag =0;
        if(A[0] < A[1]){
            flag = 1;
        }else if(A[0]>A[1]){
            flag = -1;
        }

        for(int i=1;i<len-1;i++){
            if(flag ==0 ){
                if(A[i]<A[i+1]){
                    flag = 1;
                }else if(A[i]>A[i+1]){
                    flag = -1;
                }
            }else if(flag==-1 && A[i]<A[i+1]){
                return false;
            }else if(flag ==1 && A[i]>A[i+1]){
                return false;
            }
        }
        return true;
    }

    public void _21_2_28() {
        int[] A = {1, 2, 2, 3};
        System.out.println(isMonotonic(A));
    }
}
