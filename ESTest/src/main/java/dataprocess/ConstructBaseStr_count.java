package dataprocess;

import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;

/**
 * @author WuChao
 * @create 2021/6/17 23:07
 */
public class ConstructBaseStr_count {
    public static void main(String[] args) throws Exception {




        String path = "E:\\研究生学习\\ES测试\\ES实验\\logs_trans\\all16LeveCut\\";
        String outPath = "E:\\研究生学习\\ES测试\\ES实验\\logs_trans\\all16BasicStr\\";

        File dir = new File(path);
        File[] files = dir.listFiles();
        BufferedReader reader = null;
        LinkedHashMap<String, String> fileStr = new LinkedHashMap<>();
        for (int i = 0; i < files.length; i++) {
            try {
                /*
                遍历所有串 找到出现次数最多的串作为基准串
                 */
                String fileName = files[i].getName();
                int maxLen2 = Integer.MIN_VALUE;
                String maxStr="";
                LinkedHashMap<String, Integer> stringCount = new LinkedHashMap<>();
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(files[i]), StandardCharsets.UTF_8));//new BufferedReader(new FileReader());
                String temp = null;
                while ((temp = reader.readLine()) != null) {
                    JSONObject jsonObject = JSONObject.parseObject(temp);

                    String t = (String) jsonObject.get("caller");

                    stringCount.put(t, stringCount.getOrDefault(t, 0) + 1);
                    if (stringCount.get(t) > maxLen2) {
                        maxStr = temp;
                        maxLen2 = stringCount.get(t);
                    }
                }
                reader.close();

                System.out.println(fileName);
                System.out.println(" "+maxLen2+":"+ maxStr);


                File newFile = new File(outPath + fileName + "_count.raw");
                if (!newFile.exists()) {
                    newFile.createNewFile();
                }
                // 获取该文件的缓冲输出流
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile), StandardCharsets.UTF_8));
                // 写入信息
                bufferedWriter.write(maxStr);
                bufferedWriter.flush();// 清空缓冲区
                bufferedWriter.close();// 关闭输出流

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

//        Iterator<Map.Entry<String, String>> iterator = fileStr.entrySet().iterator();
//        while (iterator.hasNext()) {
//            Map.Entry<String, String> next = iterator.next();
//            File newFile = new File(outPath +"-379033988出现次数最多的串.txt");
//            if (!newFile.exists()) {
//                newFile.createNewFile();
//            }
//            // 获取该文件的缓冲输出流
//            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile, true), StandardCharsets.UTF_8));
//            // 写入信息
//            bufferedWriter.write("fileName: "+next.getKey()+"\nBaseStr: " + next.getValue()+"\n");
//            bufferedWriter.flush();// 清空缓冲区
//            bufferedWriter.close();// 关闭输出流
//        }







    }
}
