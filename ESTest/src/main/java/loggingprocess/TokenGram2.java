package loggingprocess;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author WuChao
 * @create 2021/10/29 8:19
 */
public class TokenGram2 {
    // 统计 N-token gram  每个出现几次算几次
    public static void main(String[] args) throws Exception {
        String filePath = "E:\\研究生学习\\华为压缩索引检索项目\\ESRally数据\\logging\\解压数据\\documents-191998.json\\output" +
                "\\request.txt";
        String outputPath = "E:\\研究生学习\\华为压缩索引检索项目\\ESRally数据\\logging\\解压数据\\documents-191998.json\\output" +
                "\\grams\\tokengram2\\";
        File file = new File(filePath);
        BufferedReader reader = null;
        String temp = null;
        HashMap<String,Long> invertList = null;
        int N;
        for (N = 1; N < 7; N++) {
            reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(file), StandardCharsets.UTF_8));
            invertList = new HashMap<>();
            int line = 0;
            while ((temp = reader.readLine()) != null) {
                line++;
                String[] value = temp.split(" ");
                int len = value.length;
                for (int i = 0; i <= len - N; i++) {
                    StringBuffer sb = new StringBuffer();
                    for (int j = i; j < i + N; j++) {
                        if (j != i) {
                            sb.append(" ");
                        }
                        sb.append(value[j]);
                    }
                    String key = sb.toString();
                    if (invertList.containsKey(key)) {
                        Long aLong = invertList.get(key);
                        if (aLong  == Long.MAX_VALUE) {
                            System.out.println("ERROR");
                        }
                    }
                    invertList.put(key, invertList.getOrDefault(key, 0L) + 1);

                }
//                break;
            }
            reader.close();

            // 按照value降序排序
            //这里将map.entrySet()转换成list
            List<Map.Entry<String,Long>> list = new ArrayList<Map.Entry<String,Long>>(invertList.entrySet());
            //然后通过比较器来实现排序
            Collections.sort(list, new Comparator<Map.Entry<String, Long>>() {
                @Override
                public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {
                    // 按照set大小降序排序
                    return o2.getValue().compareTo(o1.getValue());
                }
            });

            // 遍历写入文件
            File newFile1 = new File(outputPath + N + "-gram.txt");
            File newFile2 = new File(outputPath + N + "-token gram.txt");
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

            for(Map.Entry<String,Long> mapping:list){
                // 写入信息
                bufferedWriter1.write(mapping.getValue() + "\n");
                bufferedWriter1.flush();// 清空缓冲区
                bufferedWriter2.write(mapping.getKey() + ":" + mapping.getValue() + "\n");
                bufferedWriter2.flush();// 清空缓冲区
            }
            bufferedWriter1.close();// 关闭输出流
            bufferedWriter2.close();// 关闭输出流
            System.out.println(N + "-token gram 统计完成");


        }
    }
}
