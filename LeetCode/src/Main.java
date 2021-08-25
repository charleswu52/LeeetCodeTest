import leetcode_everyday.Mar.*;
import sun.rmi.runtime.Log;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] strings = scanner.nextLine().split(" ");
        int n = strings.length;
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(strings[i]);
        }
        int target = Integer.parseInt(scanner.nextLine());
        int[] res = searchRange(nums, target);
        System.out.println(res[0] + " " + res[1]);
    }

    public static int[] searchRange(int[] nums, int target) {
        int leftIdx = binarySearch(nums, target, true);
        int rightIdx = binarySearch(nums, target, false) - 1;
        if (leftIdx <= rightIdx && rightIdx < nums.length && nums[leftIdx] == target && nums[rightIdx] == target) {
            return new int[]{leftIdx, rightIdx};
        }
        return new int[]{-1, -1};
    }

    public static int binarySearch(int[] nums, int target, boolean lower) {
        int left = 0, right = nums.length - 1, ans = nums.length;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] > target || (lower && nums[mid] >= target)) {
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    /// <summary>
    /// 登录日志
    /// </summary>
    public class MyLog {
        /// <summary>
        /// 登录时间
        /// </summary>
        private int LoginTime;
        /// <summary>
        /// 登出时间
        /// </summary>
        private int LogoutTime;

        public int getLoginTime() {
            return LoginTime;
        }

        public void setLoginTime(int loginTime) {
            LoginTime = loginTime;
        }

        public int getLogoutTime() {
            return LogoutTime;
        }

        public void setLogoutTime(int logoutTime) {
            LogoutTime = logoutTime;
        }
    }


    public void slove() {

        //
        List<MyLog> logs = new ArrayList<>();

        //定义一个数组盛放每个小时的在线人数
        int[] logHigh = new int[24];
        int time = 0;
        while (time < 24) {
            logHigh[time] = 0;
            for (MyLog log : logs) {
                if (time >= log.LoginTime && time < log.LogoutTime) {
                    logHigh[time]++;
                }
            }
            time++;
        }

        //获取最大在线人数
        int max = logHigh[0];
        int index = 0;
        for (int j = 1; j < logHigh.length; j++) {
            if (max < logHigh[j]) {
                max = logHigh[j];
                index = j;
            }
        }
        //获取最大在线人数持续时间
        int maxIndex = 0;
        for (int maxI = index + 1; maxI < logHigh.length; maxI++) {
            if (logHigh[maxI] == max) {
                maxIndex = maxI;
            } else {
                continue;
            }

        }

        System.out.println("最大在线人数是：" + max);
        System.out.println("起始时间是：" + index + "，结束时间是：" + (maxIndex + 1) + "，持续时间：" + (maxIndex + 1 - index));

        for (int i = 0; i < 24; i++) {
            System.out.println("时间在 " + i + " 到 " + (i + 1) + " 点之间在线人数是：" + logHigh[i]);
        }
    }


}
