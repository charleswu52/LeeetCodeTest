package logparser;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author WuChao
 * @create 2021/12/19 下午12:39
 */
public class SearchTest {

    // 搜索过程代码测试
    public static void main(String[] args) throws Exception {
    }

    @Test
    public void write() throws Exception {
        String fileName = "/media/charles/Data/WorkSpace/Idea/CodingStudy/ESTest/src/main/resources/output/maps.dat";
        GramList gramList = new GramList();
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        int len = 5;
//        gramList.getListMap().put(1, new ArrayList<Pair<Integer, BitSet>>() {{
//            add(new Pair<>(1, new BitSet()));
//        }});
//        gramList.getListMap().put(2, new ArrayList<Pair<Integer, BitSet>>() {{
//            add(new Pair<>(2, new BitSet()));
//        }});
//        gramList.getListMap().put(3, new ArrayList<Pair<Integer, BitSet>>() {{
//            add(new Pair<>(3, new BitSet()));
//        }});
//        gramList.getListMap().put(4, new ArrayList<Pair<Integer, BitSet>>() {{
//            add(new Pair<>(4, new BitSet()));
//        }});
//        gramList.getListMap().put(5, new ArrayList<Pair<Integer, BitSet>>() {{
//            add(new Pair<>(5, new BitSet()));
//        }});

        System.out.println(gramList.getListMap());

        Output output = new Output(new FileOutputStream(file, true));
        Kryo kryo = new Kryo();
//        kryo.register(BitSet.class);
        kryo.register(char[].class);

        kryo.writeObject(output, len);
////        Iterator<Map.Entry<Integer, List<Pair<Integer, BitSet>>>> iterator = gramList.getListMap().entrySet().iterator();
//        while (iterator.hasNext()) {
////            Map.Entry<Integer, List<Pair<Integer, BitSet>>> next = iterator.next();
//            Integer key = next.getKey();
//            kryo.writeObject(output, key);
//            List<Pair<Integer, BitSet>> value = next.getValue();
//            int size = value.size();
//            kryo.writeObject(output, size);
//            for (int i = 0; i < size; i++) {
//                Integer key1 = value.get(i).getKey();
//                BitSet value1 = value.get(i).getValue();
//                kryo.writeObject(output, key1);
//                kryo.writeObject(output,value1);
//            }
//        }
        output.close();

    }

    public static void testCmp(String f) {
        String s = "_123456.";
        String b = "_12345.";

    }
}
