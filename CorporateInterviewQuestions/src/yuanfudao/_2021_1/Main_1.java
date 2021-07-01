package yuanfudao._2021_1;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author WuChao
 * @create 2021/7/1 13:43
 */
public class Main_1 {
    /**
     * [编程题]小猿的时间管理
     * 小猿非常热爱学习，所以他在猿辅导上购买了N节课来提升自己，每节课有一个开始时间S和结束时间E（S和E均用正整数表示）。
     * 买完课程后，粗心的小猿发现这些课程之间有些时间冲突，幸好小猿有一种“一心多用”的超能力，能同时兼顾K节课上课。
     * 当然是K越大，使用这种能力就越累。请问小猿最少需要一心几用，才能上完所有他买的课程呢？
     *
     * 输入描述:
     * 第一行输入为N（N ≤ 200000），表示购买课程数。
     * 接下来N行，每行输入两个数Si Ei（0 < Si < Ei < 1e9），为第i节课的起止时间。
     *
     * 输出描述:
     * 请输出最小满足条件的K。
     *
     * 输入例子1:
     * 4
     * 1 4
     * 1 2
     * 2 3
     * 3 4
     *
     * 输出例子1:
     * 2
     */

    public static int[] record;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int time = Integer.parseInt(in.nextLine());
        int[] arrive=new int[time];
        int[] leave = new int[time];
        for(int i=0;i<time;i++){
            arrive[i]=in.nextInt();
            leave[i]=in.nextInt();
        }
        Arrays.sort(arrive);
        Arrays.sort(leave);
        int p_ar=0,le=0; int max_len=0;
        while(p_ar<arrive.length&& le<leave.length){
            if(arrive[p_ar]<leave[le]){
                max_len=Math.max(max_len, p_ar-le+1);
                p_ar++;
            }
            else{
                le++;
            }
        }
        System.out.println(max_len);
    }
}
