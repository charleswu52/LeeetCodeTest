package loggingprocess;

import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author WuChao
 * @create 2021/10/29 8:19
 */
public class NGram {
    // 统计 N-token gram 建立倒排索引
    public static void main(String[] args) throws Exception {
        String filePath = "E:\\研究生学习\\华为压缩索引检索项目\\ESRally数据\\logging\\解压数据\\documents-191998.json\\output" +
                "\\request2.txt";
        String outputPath = "E:\\研究生学习\\华为压缩索引检索项目\\ESRally数据\\logging\\解压数据\\documents-191998.json\\output" +
                "\\grams\\vgram\\";
        File file = new File(filePath);
        BufferedReader reader = null;
        String temp = null;
        int N = 3;
        HashMap<String, BitSet> invertList = new HashMap<>();
        int line = 0;
        reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(file), StandardCharsets.UTF_8));
        while ((temp = reader.readLine()) != null) {
            line++;
            int len = temp.length();
            for (int i = 0; i <= len - N; i++) {
                String key = temp.substring(i, i + N);
                if (invertList.containsKey(key)) {
                    BitSet bitSet = invertList.get(key);
                    bitSet.set(line);
                    invertList.put(key, bitSet);
                } else {
                    BitSet bitSet = new BitSet();
                    bitSet.set(line);
                    invertList.put(key, bitSet);
                }
            }
            System.out.println("line "+line+" map大小："+invertList.size());
//            break;
        }
        reader.close();


        /* 遍历写入文件
        File newFile1 = new File(outputPath + N + "-vGram.txt");
        File newFile2 = new File(outputPath + N + "-v gram.txt");
        if (!newFile1.exists()) {
            newFile1.createNewFile();
        }
        if (!newFile2.exists()) {
            newFile2.createNewFile();
        }
        // 获取该文件的缓冲输出流
        BufferedWriter bufferedWriter1 = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(newFile1, true), StandardCharsets.UTF_8));

        BufferedWriter bufferedWriter2 = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(newFile2, true), StandardCharsets.UTF_8));

        // 按照value降序排序
        //这里将map.entrySet()转换成list
        List<Map.Entry<String, BitSet>> list = new ArrayList<Map.Entry<String, BitSet>>(invertList.entrySet());
        //然后通过比较器来实现排序
        Collections.sort(list, new Comparator<Map.Entry<String, BitSet>>() {
            @Override
            public int compare(Map.Entry<String, BitSet> o1, Map.Entry<String, BitSet> o2) {
                // 按照set大小降序排序
                return o2.getValue().cardinality() - o1.getValue().cardinality();
            }
        });

        for (Map.Entry<String, BitSet> mapping : list) {
            // 写入信息
            bufferedWriter1.write(mapping.getValue().cardinality() + "\n");
            bufferedWriter1.flush();// 清空缓冲区
            bufferedWriter2.write(mapping.getKey() + ":" + mapping.getValue().cardinality() + "\n");
            bufferedWriter2.flush();// 清空缓冲区
        }
        bufferedWriter1.close();// 关闭输出流
        bufferedWriter2.close();// 关闭输出流
        System.out.println(N + "-gram 统计完成");

         */


    }
//    }


    @Test
    public void test() {
        BitSet set = new BitSet();
        for (int i = 0; i < 1000000000; i++) {
            set.set(i);

        }


        System.out.println(set.cardinality());
    }
}
