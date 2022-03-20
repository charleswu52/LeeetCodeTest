package graduate.util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author WuChao
 * @create 2022/3/18 14:11
 */
public class ResultSplit {
    // 对分类后的文件 按照每个字符串的长度再进行划分
    public static void main(String[] args) {
        String method = "Apache";
        String clusterFilePath = "H:\\Work\\LogCompress\\logparser\\allresult\\LogDelta\\"+method+"\\clusterFiles";
        String splitOutPath = "H:\\Work\\LogCompress\\logparser\\allresult\\LogDelta\\"+method+"\\split\\";
        split(clusterFilePath, splitOutPath);
    }

    public static void split(String clusterFilePath, String splitOutPath) {
        File file = new File(clusterFilePath);
        File[] tempList = file.listFiles();
        BufferedReader reader = null;
        for (int i = 0; i < tempList.length; i++) {
            try {
                String fileName = tempList[i].getName().substring(0, tempList[i].getName().lastIndexOf("."));
                System.out.println("当前处理文件：" + fileName);
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(tempList[i]), StandardCharsets.UTF_8));//new BufferedReader(new FileReader());
                String temp = null;
                HashMap<Integer, List<String>> map = new HashMap<>();
                while ((temp = reader.readLine()) != null) {
                    int length = temp.length();
                    if (!map.containsKey(length)) {
                        List<String> strings = new ArrayList<>();
                        strings.add(temp);
                        map.put(length, strings);
                    } else {
                        map.get(length).add(temp);
                    }
                }
                reader.close();

                for (Map.Entry<Integer, List<String>> entry : map.entrySet()) {
                    int length = entry.getKey();
                    File newFile = new File(splitOutPath +fileName+"_"+length+ ".log");
                    if (!newFile.exists()) {
                        newFile.createNewFile();
                    }
                    // 获取该文件的缓冲输出流
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile, true), StandardCharsets.UTF_8));
                    // 写入信息
                    for (String s : entry.getValue()) {
                        bufferedWriter.write(s + "\n");
                        bufferedWriter.flush();// 清空缓冲区
                    }
                    bufferedWriter.close();// 关闭输出流
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
