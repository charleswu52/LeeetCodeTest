package yuanfudao._2021_1;

import java.util.*;

/**
 * @author WuChao
 * @create 2021/7/1 15:17
 */
public class Main_2 {
    /**
     * [编程题]小猿的抽奖
     * 猿辅导组织一次抽奖活动，奖券的发放方式是：某个同学拿到全部的奖券，然后自己留一张，其他的分发给他周边的同学；其他同学收到奖券后，
     * 自己留一张，再分发给周边还未收到过奖券的其他同学，以此类推，直到每个同学都收到一张奖券为止。
     * <p>
     * 开奖时，每张奖券会得到一个奖励值，每个同学最终奖励值除了要包含自己奖券的奖励值外，
     * 还可以额外加上从经由自己发出去的奖券中选择出一部分奖券的奖励值。
     * 但是如果不选择某张奖券，那么经由持有这张没被选择奖券的同学发出去的所有奖券都不能再选了。
     * 比如A把BCD的奖券发给了B，B再把CD的奖券分发给了CD，A可以只选择自己的奖券，可以选择ABCD的奖券，也可以选择AB或ABC或ABD的奖券，
     * 但是不能只选择AC或者AD的奖券。
     * <p>
     * 奖励值当然是越大越好，大家一定也想知道最终大奖是多少，请你帮大家算一下吧。
     * <p>
     * 输入描述:
     * 第一行输入N，表示N个同学，N <= 100000；
     * 第二行到第N+1行输入两个整数A B，其中A表示某同学持有奖券的奖励值，-1e9 <= A <= 1e9，B表示该奖券是第B行的同学发给他的；
     * B=0表示他是第一个发奖券的同学。
     * <p>
     * 输出描述:
     * 输出整数M，为所有同学中获得的最大的奖励值除1000000003的模。
     * <p>
     * 输入例子1:
     * 3
     * 2 0
     * 1 2
     * -1 2
     * <p>
     * 输出例子1:
     * 3
     */

    public static final int mod = 1000000003;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        Map<Integer, Integer> lineToVal = new HashMap<>();
        lineToVal.put(0, 0);
        Map<Integer, List<Integer>> lineToLine = new HashMap<>();
        for (int i = 2; i < 2 + n; i++) {
            String[] fields = scanner.nextLine().split(" ");
            lineToVal.put(i, Integer.parseInt(fields[0]));
            int fromLine = Integer.parseInt(fields[1]);
            if (!lineToLine.containsKey(fromLine)) {
                int finalI = i;
                lineToLine.put(fromLine, new ArrayList<Integer>() {{
                    add(finalI);
                }});
            } else {
                List<Integer> list = lineToLine.get(fromLine);
                list.add(i);
                lineToLine.put(fromLine, list);
            }
        }
        long res = 0L;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        long sum = 0L;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                sum += lineToVal.get(cur);
            }
        }



    }
}
