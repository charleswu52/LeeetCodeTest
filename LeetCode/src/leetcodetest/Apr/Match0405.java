package leetcodetest.Apr;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author WuChao
 * @since 2021/4/5 下午3:47
 */
public class Match0405 {


    public int purchasePlans(int[] nums, int target) {
        int ans = 0, mod = 1000000007;
        Arrays.sort(nums);
        // 双指针
        int left = 0, right = nums.length - 1;
        while (left < right) {
            if (nums[left] + nums[right] > target) {
                right--;
            } else {
                ans = (ans + (right - left)) % mod;
                left++;
            }
        }
        return ans;

    }

    public int orchestraLayout(int num, int xPos, int yPos) {
        int layer = Math.min(Math.min(xPos, num - xPos - 1), Math.min(yPos, num - yPos));
        int x = Math.min(xPos, num - 1 - xPos);
        int y = Math.min(yPos, num - 1 - yPos);
        long round = Math.min(x, y);
        long res = 0;
        long tmpNum = num-2*round;
        res=(round*(num-1)-round*(round-1))%9*4%9;
        if (xPos == round) {
            res=(res+yPos-round)%9+1;
        } else if(yPos==num-round-1){
            res=(res+tmpNum+xPos-round-1)%9+1;
        }else if(xPos==num-round-1){
            res=(res+2*tmpNum-1+num-round-1-yPos-1)%9+1;
        }else{
            res=(res+3*tmpNum-2+(num-round-1)-xPos-1)%9+1;
        }
        return (int)res;


    }

    @Test
    public void test() {
        int[] nums = {2,5,3,5};
        int target = 6;
        System.out.println(purchasePlans(nums, target));

    }
}
