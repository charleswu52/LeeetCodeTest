package logparser;

import com.csvreader.CsvReader;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author WuChao
 * @create 2021/12/13 下午9:13
 */
public class VerifyResult {
    public static void main(String[] args) throws Exception {

        String method = "Spell_result";
        // 聚类 csv 文件
        String resultPath = "/home/charles/WorkSpace/logparser/myresult/" +
                method +
                "/influxdb2_test.log_structured.csv";
        // 验证输出文件夹
        String verifyPath = "/home/charles/WorkSpace/logparser/myresult/" +
                method +
                "/verify1/";

        // 原始文件
        String logFilePath = "/media/charles/My Passport/Work/CompressData/exampleData/" +
                "influxdb-2021-03-30T11-06-23.613.log";

        verifyCluster(resultPath, verifyPath, logFilePath);

        System.out.println("Process Successful！");


    }

    /*
    聚类结果进行验证
     */
    public static void verifyCluster(String resultPath, String outputPath, String originFilePath) throws Exception {
        // 读取结果文件 并存到 Map 中
        CsvReader csvReader = new CsvReader(resultPath);
        Map<Integer, String> map = new HashMap<>();
        // 读取表头
        csvReader.readHeaders();
        while (csvReader.readRecord()) {
            // 读取一整行数据
            Integer lineId = Integer.parseInt(csvReader.get("LineId"));
            String eventId = csvReader.get("EventId");
            map.put(lineId, eventId);
        }

        // 读取原始日志文件并按照聚类id重新写文件
        File file = new File(originFilePath);

        BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(file), StandardCharsets.UTF_8));

        String temp = null;
        int id = 0; // 标记是几行数据
        while ((temp = reader.readLine()) != null) {
            id++;
            String eventId = map.get(id);
            File newFile = new File(outputPath + eventId + ".log");
            if (!newFile.exists()) {
                newFile.createNewFile();
            }
            // 获取该文件的缓冲输出流
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(newFile, true), StandardCharsets.UTF_8));
            // 写入信息
            bufferedWriter.write(temp + "\n");
            bufferedWriter.flush();// 清空缓冲区
            bufferedWriter.close();// 关闭输出流
        }
        reader.close();

    }
}
