package loggingprocess;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author WuChao
 * @create 2021/10/29 8:19
 */
public class TokenGram {
    // 统计 N-token gram
    public static void main(String[] args) throws Exception {
        String filePath = "E:\\研究生学习\\华为压缩索引检索项目\\ESRally数据\\logging\\解压数据\\documents-191998.json\\output" +
                "\\request.txt";
        String outputPath = "E:\\研究生学习\\华为压缩索引检索项目\\ESRally数据\\logging\\解压数据\\documents-191998.json\\output" +
                "\\grams\\";
        File file = new File(filePath);
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(file), StandardCharsets.UTF_8));
        String temp = null;
        HashMap<String, Set<Integer>> invertList = new HashMap<>();
        int N = 6;
        int line = 0;
        while ((temp = reader.readLine()) != null) {
            line++;
            String[] split = temp.split(":");
            int id = Integer.parseInt(split[0]);
            String[] value = split[1].split(" ");
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
                    invertList.get(key).add(id);
                } else {
                    HashSet<Integer> set = new HashSet<>();
                    set.add(id);
                    invertList.put(key, set);
                }
            }
//            break;
        }
        /*
        Iterator<Map.Entry<String, Set<Integer>>> iterator = invertList.entrySet().iterator();
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
        while (iterator.hasNext()) {
            Map.Entry<String, Set<Integer>> next = iterator.next();
            // 写入信息
            bufferedWriter1.write(next.getValue().size() + "\n");
            bufferedWriter1.flush();// 清空缓冲区
            bufferedWriter2.write(next.getKey() + ":" + next.getValue().size() + "\n");
            bufferedWriter2.flush();// 清空缓冲区
        }
        bufferedWriter1.close();// 关闭输出流
        bufferedWriter2.close();// 关闭输出流
        System.out.println("统计完成");

         */
        System.out.println(line);
    }
}
