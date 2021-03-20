import org.junit.Test;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * @author WuChao
 * @since 2021/3/20 下午1:04
 */
public class _40 {
    /**
     * 剑指 Offer 40. 最小的k个数
     * 难度: easy
     * <p>
     * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
     *
     * <p>
     * 示例：
     * 输入：arr = [3,2,1], k = 2
     * 输出：[1,2] 或者 [2,1]
     *
     * <p>
     * 数据范围：
     * 0 <= k <= arr.length <= 10000
     * 0 <= arr[i] <= 10000
     */

    // 先排序然后直接选
    public int[] getLeastNumbers(int[] arr, int k) {
        if (k < 1) {
            return new int[]{};
        }
        Arrays.sort(arr);
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // 存储到map中
    public int[] getLeastNumbers2(int[] arr, int k) {
        if (k < 1) {
            return new int[]{};
        }
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int[] res = new int[k];
        int i = 0;
        for (int key : map.keySet()) {
            int count = map.get(key);
            while (i < k && count > 0) {
                res[i++] = key;
                count--;
            }
        }
        return res;
    }


    @Test
    public void test() {
        int[] arr = {0, 0, 1, 2, 4, 2, 2, 3, 1, 4};
        int k = 8;
        System.out.println(Arrays.toString(getLeastNumbers(arr, k)));
        System.out.println(Arrays.toString(getLeastNumbers2(arr, k)));
    }

}
