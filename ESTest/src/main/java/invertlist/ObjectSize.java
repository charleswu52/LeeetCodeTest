package invertlist;

import org.apache.lucene.util.RamUsageEstimator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WuChao
 * @create 2021/11/4 13:30
 */
public class ObjectSize {
    public static void main(String[] args) {
        //计算一个对象在内存占用的空间
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            stringList.add(String.valueOf(i));
        }
        String a = "2";
        // 计算一个对象在内存中的大小
        System.out.println(RamUsageEstimator.humanReadableUnits(RamUsageEstimator.sizeOfObject(a)));

    }
}
