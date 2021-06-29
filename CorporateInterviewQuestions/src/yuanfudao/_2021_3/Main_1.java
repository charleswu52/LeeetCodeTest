package yuanfudao._2021_3;

import java.util.Scanner;

/**
 * @author WuChao
 * @create 2021/6/29 11:10
 */
public class Main_1 {
    /**
     * [编程题]小猿的扑克牌魔术
     * 小猿会表演扑克牌魔术：已知一副牌的初始顺序，经过多轮洗牌，仍然能看破任意一张牌的牌面。
     * 对于N张的一副牌，小猿的一次洗牌操作如下：将牌分为两叠，分别为前 N/2 张和后 N - N/2 张；接下来使两叠纸牌一张叉一张地交错叠在一起，原先第一张的纸牌洗牌后处于第二张。
     * 你能破解小猿魔术的秘密吗？
     * <p>
     * 输入描述:
     * 第一行输入两个正整数 N M，2 <= N <= 100, 1 <= M <= 200
     * 第二行输入N个正整数，表示初始牌序列
     * <p>
     * 输出描述:
     * 输出经过M次洗牌后的序列
     * <p>
     * 输入例子1:
     * 6 2
     * 3 1 4 2 5 6
     * <p>
     * 输出例子1:
     * 1 2 6 3 4 5
     * <p>
     * 例子说明1:
     * 3 1 4 2 5 6 -> 2 3 5 1 6 4 -> 1 2 6 3 4 5
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String first = scanner.nextLine();
        String[] nums = first.split(" ");
        int n = Integer.parseInt(nums[0]);
        int m = Integer.parseInt(nums[1]);
        String str = scanner.nextLine();
        String[] sequence = str.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            sb.setLength(0);
            int left, right;
            for (left = 0, right = n / 2; left < n / 2; left++, right++) {
                sb.append(sequence[right]+" ");
                sb.append(sequence[left]+" ");
            }
            if (right < n) {
                sb.append(sequence[right]+" ");
            }
            sequence = sb.toString().split(" ");
        }
        String res = sb.toString().substring(0,sb.toString().length()-1);
        System.out.println(res);

    }
}
