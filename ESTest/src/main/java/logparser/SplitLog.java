package logparser;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author WuChao
 * @create 2021/12/14 下午2:03
 */
public class SplitLog {
    public static void main(String[] args)throws Exception {
        String filePath = "/media/charles/My Passport/Work/CompressData/exampleData/" +
                "influxdb-2021-03-30T11-06-23.613.log";
        String outputPath = "/media/charles/My Passport/Work/CompressData/exampleData/influxdbOut/";

        split1(filePath, outputPath, 160001, 165200);

    }

    /*
     测试第一种划分 方式： 前1k 条 与前2k条（2k包含 1k）
     */
    public static void split1(String filePath, String outputPath, int fromLineId, int toLineId)throws Exception {
        File file = new File(filePath);
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(file), StandardCharsets.UTF_8));

        int id = 0;
        String temp = null;
        while ((temp = reader.readLine()) != null) {
            id++;
            if (fromLineId <= id && id <= toLineId) {
                String values = SplitJson.splitJson(temp);

                // 写文件
                File newFile = new File(outputPath + fromLineId + "_" + toLineId + ".log");
                if (!newFile.exists()) {
                    newFile.createNewFile();
                }
                // 获取该文件的缓冲输出流
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(newFile, true), StandardCharsets.UTF_8));
                // 写入信息
                bufferedWriter.write(values + "\n");
                bufferedWriter.flush();// 清空缓冲区
                bufferedWriter.close();// 关闭输出流
            } else if (id > toLineId) {
                break;
            }
        }
        reader.close();
    }
}
