package ali;

import java.util.Scanner;

/**
 * @author WuChao
 * @create 2021/7/28 9:15
 */
public class Watermark {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        while (t-- > 0) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            int[] cost = new int[n];
            int[] save = new int[n];
            for (int i = 0; i < n; i++) {
                cost[i] = scanner.nextInt();
            }
            for (int i = 1; i < n; i++) {
                save[i] = scanner.nextInt();
            }
            int cur = m;
            int res = -1;
            for (int i = 0; i < n; i++) {
                cur = Math.min(cur + save[i], m);
                cur -= cost[i];
                if (cur <= 0) {
                    res = i+1;
                    break;
                }
            }
            System.out.println(res);

        }
    }
}
