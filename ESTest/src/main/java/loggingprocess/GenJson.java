package loggingprocess;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author WuChao
 * @create 2021/11/15 22:01
 */
public class GenJson {
    // 将logging数据生成json导入到ES
    public static void main(String[] args) throws Exception {
        String path = "/media/charles/6E247A97247A61CF/林天成/数据/";
        File file = new File(path);
        File[] tempList = file.listFiles();
        BufferedReader reader = null;
        String outPath = "/media/charles/6E247A97247A61CF/林天成/数据/output/";
        String type = "logging_token_";
        for (int i = 0; i < tempList.length; i++) {
            long line = 1L;
            String fileName = type + tempList[i].getName().split("\\.")[0];
            String content = "logging_token_5000000";
            System.out.println(fileName);
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(tempList[i]), StandardCharsets.UTF_8));//new BufferedReader(new FileReader());
            String temp = null;
            while ((temp = reader.readLine()) != null) {
                File newFile = new File(outPath + fileName + ".json");
                if (!newFile.exists()) {
                    newFile.createNewFile();
                }
                // 获取该文件的缓冲输出流
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile, true), StandardCharsets.UTF_8));
                // 写入信息
                bufferedWriter.write("{\"index\":{\"_index\":\"" + content + "\",\"_type\":\"_doc\",\"_id\":" + (line++) + "}}\n");
                bufferedWriter.write("{\"request\":\"" + temp + "\"}\n");
                bufferedWriter.flush();// 清空缓冲区
                bufferedWriter.close();// 关闭输出流
            }
            reader.close();


        }


    }
}
