import org.junit.Test;

/**
 * @author WuChao
 * @since 2021/3/23 上午9:58
 */
public class _45 {
    /**
     * 剑指 Offer 45. 把数组排成最小的数
     * 难度: medium
     * <p>
     * 输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
     * <p>
     * 示例：
     * 输入: [3,30,34,5,9]
     * 输出: "3033459"
     *
     * <p>
     * 数据范围：
     * 0 < nums.length <= 100
     */


    /*
    题目解析：实际上是一个排序问题
    需要对原数组的数组先转化成字符串型的，然后自定义排序规则，设数组 nums 中任意两数字的字符串为 x 和 y:
        若拼接字符串 x+y > y+x,则 x "大于" y;
        反之，若x+y < y+x,则 x "小于" y;
        这里的 x"小于"y代表排序完成后数组中x应该在y的左边； x "大于" y表示 x应该在y的右边

     */
    public String minNumber(int[] nums) {
        // 1.先将int数组转化为string型
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = String.valueOf(nums[i]);
        };
        // 按照排序规则对String数组进行排序
        quickSort(strs, 0, strs.length - 1);
        StringBuilder res = new StringBuilder();
        for (String s : strs) {
            res.append(s);
        }
        return res.toString();




    }

    // 按照自定义的排序规则对String数组进行排序

    /**
     *
     * @param strs 待排序的String数组
     * @param l 数组的数组的左边界
     * @param r 数组的数组的右边界
     */
    public void quickSort(String[] strs, int l, int r) {
        if (l >= r) {
            return;
        }
        int i = l, j = r;
        String temp = strs[i];
        while (i < j) {
            while ((strs[j] + strs[l]).compareTo(strs[l] + strs[j]) >= 0 && i < j) {
                j--;
            }
            while ((strs[i] + strs[l]).compareTo(strs[l] + strs[i]) <= 0 && i < j) {
                i++;
            }
            temp = strs[i];
            strs[i] = strs[j];
            strs[j] = temp;
        }
        strs[i] = strs[l];
        strs[l] = temp;
        quickSort(strs, l, i - 1);
        quickSort(strs, i + 1, r);
    }

    @Test
    public void test() {
        int[] nums = {3, 30, 34, 5, 9};
//        System.out.println(minNumber(nums));
        assert minNumber(nums).equals("3033452");

    }
}
