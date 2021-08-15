import leetcode_everyday.Mar.*;

import java.awt.*;
import java.lang.reflect.Field;

public class Main {

    private static int j = 0;

    private static boolean mb(int k) {
        j += k;
        return true;
    }

    public static void ma(int i) {
        boolean b;
        b = i < 10 | mb(4);
        b = i < 10 || mb(8);
    }
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Class classs = Class.class.getClass();
        Object o = Object.class.getClass();
        System.out.println(classs==o);
        System.out.println(classs.getClass()==o.getClass());

    }
//        String s = new String("abc");
//        System.out.println(s);
//        Field value = s.getClass().getDeclaredField("value");
////        value.setAccessible(true);
////        value.set(s, "abcd".toCharArray());
////
////
////        System.out.println(s);
//        int[] nums = {1, 3, 5, 5, 5, 5, 5, 5, 7, 2, 0};
//        System.out.println(maxnum(nums));
//    }
//
//    public  static int maxnum(int[] nums){
//        int i =0 ;
//        int j = nums.length - 1;
//        int res;
//        while (i <= j) {
//            int mid = (i + j) / 2;
//            int left = mid;
//            while (nums[left] == nums[mid] && left > 0) {
//                left--;
//            }
//            int right = mid;
//            while (nums[right] == nums[mid] && right < nums.length - 1) {
//                right++;
//            }
//            if (nums[mid] >= nums[left] && nums[mid] <= nums[right]) {
//                i = right;
//            } else if (nums[mid] <= nums[left] && nums[mid] >= nums[right]) {
//                j = left;
//            } else {
//                res = nums[mid];
//                return res;
//            }
//        }
//        return -1;
//    }




}
