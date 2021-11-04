package loggingprocess;

import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author WuChao
 * @create 2021/10/29 8:19
 */
public class NGram2 {
    // 统计 v gram 有几次算几次
    public static void main(String[] args) throws Exception {
        String filePath = "E:\\研究生学习\\华为压缩索引检索项目\\ESRally数据\\logging\\解压数据\\documents-191998.json\\output" +
                "\\request2.txt";
        String outputPath = "E:\\研究生学习\\华为压缩索引检索项目\\ESRally数据\\logging\\解压数据\\documents-191998.json\\output" +
                "\\grams\\vgram2\\";
        File file = new File(filePath);
        BufferedReader reader = null;
        String temp = null;
        HashMap<String, Long> invertList = null;
        int N;
        for (N = 1; N < 7; N++) {
            int line = 0;
            invertList = new HashMap<>();
            reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(file), StandardCharsets.UTF_8));
            while ((temp = reader.readLine()) != null) {
                line++;
                int len = temp.length();
                for (int i = 0; i <= len - N; i++) {
                    String key = temp.substring(i, i + N);
                    if (invertList.containsKey(key)) {
                        if (invertList.get(key) == Long.MAX_VALUE) {
                            System.out.println("ERROR");
                        }
                    }
                    invertList.put(key, invertList.getOrDefault(key, 0L) + 1);
                }
            }
            reader.close();


            // 遍历写入文件
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
            List<Map.Entry<String, Long>> list = new ArrayList<Map.Entry<String, Long>>(invertList.entrySet());
            //然后通过比较器来实现排序
            Collections.sort(list, new Comparator<Map.Entry<String, Long>>() {
                @Override
                public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {
                    // 按照set大小降序排序
                    return o2.getValue().compareTo(o1.getValue());
                }
            });

            for (Map.Entry<String, Long> mapping : list) {
                // 写入信息
                bufferedWriter1.write(mapping.getValue() + "\n");
                bufferedWriter1.flush();// 清空缓冲区
                bufferedWriter2.write(mapping.getKey() + ":" + mapping.getValue() + "\n");
                bufferedWriter2.flush();// 清空缓冲区
            }
            bufferedWriter1.close();// 关闭输出流
            bufferedWriter2.close();// 关闭输出流
            System.out.println(N + "-gram 统计完成");


        }
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
