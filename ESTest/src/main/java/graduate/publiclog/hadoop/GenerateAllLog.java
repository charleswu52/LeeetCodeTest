package graduate.publiclog.hadoop;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author WuChao
 * @create 2022/3/17 21:01
 */
public class GenerateAllLog {
    // 将所有的Hadoop 日志合成一个
    public static void main(String[] args) {
        // InfluxDB 日志所在的文件夹
        String path = "H:\\Work\\LogCompress\\logparser\\allLogs\\Hadoop";
        String outPath = "H:\\Work\\LogCompress\\logparser\\allLogs\\";
        allLog2Json(path, outPath);

    }

    public static void allLog2Json(String path, String outPath) {
        // InfluxDB 日志所在的文件夹
        File file = new File(path);
        File[] tempList = file.listFiles();
        BufferedReader reader = null;
        long line = 0;
        for (int i = 0; i < tempList.length; i++) {
            try {
                String fileName = tempList[i].getName();
                System.out.println("当前处理文件：" + fileName);
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(tempList[i]), StandardCharsets.UTF_8));//new BufferedReader(new FileReader());
                String temp = null;
                while ((temp = reader.readLine()) != null) {
                    ++line;
                    File newFile = new File(outPath +  "Hadoop.lop");
                    if (!newFile.exists()) {
                        newFile.createNewFile();
                    }
                    // 获取该文件的缓冲输出流
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile, true), StandardCharsets.UTF_8));
                    // 写入信息
                    bufferedWriter.write(temp + "\n");
                    bufferedWriter.flush();// 清空缓冲区
                    bufferedWriter.close();// 关闭输出流
                }
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("日志条目总数：" + line); //全部：2719173 小:165200
    }
}
