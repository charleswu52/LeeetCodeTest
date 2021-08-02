import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

    public static int maxProfit(int[] prices) {
        int res = 0;
        int len = prices.length;
        int minPrice = prices[0];
        for (int i = 1; i < len; i++) {
            res= Math.max(prices[i] - minPrice,res);
            minPrice = Math.min(prices[i], minPrice);
        }
        return res;
    }


    public static void main(String[] args)throws Exception {
        int[] prices = {1, 2, 3, 6, 7};
        System.out.println(maxProfit(prices));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        Date date = simpleDateFormat.parse("01:00");
        System.out.println(date.getTime());
    }
}
