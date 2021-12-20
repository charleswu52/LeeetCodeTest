package logparser;

import com.csvreader.CsvReader;
import logdelta.core.DeltaCompress;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author WuChao
 * @create 2021/12/13 下午9:13
 */
public class VerifyResult {
    static String method = "MoLFI_result";
    static String filePath = "/InfluxDB";
    static String fileName = "/influxdb2_test";

    public static void main(String[] args) throws Exception {

        // 聚类 csv 文件
        String resultPath = "/home/charles/WorkSpace/logparser/myresult/" +
                method + fileName +
                ".log_structured.csv";
        // 验证输出文件夹
        String verifyPath = "/home/charles/WorkSpace/logparser/myresult/" +
                method +
                "/clusterFiles/";

        // 统计出basicStr
        String basicFilePath = "/home/charles/WorkSpace/logparser/myresult/" +
                method +
                "/basicStrFiles/";

        // 压缩后的文件存放的路径
        String compressedFilePath = "/home/charles/WorkSpace/logparser/myresult/" +
                method +
                "/compressedFiles/";


        // 原始文件
        String logFilePath = "/media/charles/My Passport/Work/CompressData/exampleData/" +
                "influxdb-2021-03-30T11-06-23.613.log";

        System.out.println("Start Clustering...");
        verifyCluster(resultPath, verifyPath, logFilePath);
        System.out.println("Clustering Successful！");

        System.out.println("Start count basic string...");
        countBasicStr(verifyPath, basicFilePath);
        System.out.println("Counting Successful！");


        System.out.println("Start Compressed...");
        generateCompressedFilePath(compressedFilePath);
        callDelta();
        System.out.println("Compressing Successful！");

        // 计算压缩后的文件大小


    }

    /*
    聚类结果进行验证,将原文件分发到指定文件夹
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
            System.out.println(id);
            String eventId = map.get(id);
            File folder = new File(outputPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }
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

    /*
    聚类后的文件中，统计出basic串
     */
    public static void countBasicStr(String clusterFilePath, String basicStrPath) throws Exception {
        File file = new File(clusterFilePath);

        File[] fileList = file.listFiles();
        BufferedReader reader = null;
        String temp = null;
        for (File file1 : fileList) {
            reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(file1), StandardCharsets.UTF_8));
            Map<String, Integer> map = new HashMap<>();
            int maxCount = Integer.MIN_VALUE;
            String maxStr = null;
            while ((temp = reader.readLine()) != null) {
                map.put(temp, map.getOrDefault(temp, 0) + 1);
                if (map.get(temp) > maxCount) {
                    maxCount = map.get(temp);
                    maxStr = temp;
                }
            }
            reader.close();
            File folder = new File(basicStrPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            File newFile = new File(basicStrPath + file1.getName() + ".raw");
            if (!newFile.exists()) {
                newFile.createNewFile();
            }
            // 获取该文件的缓冲输出流
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(newFile, true), StandardCharsets.UTF_8));
            // 写入信息
            bufferedWriter.write(maxStr + "\n");
            bufferedWriter.flush();// 清空缓冲区
            bufferedWriter.close();// 关闭输出流

        }
    }

    /*
    创建出压缩后的文件夹
     */
    public static void generateCompressedFilePath(String compressed) {
        File folder = new File(compressed);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    /*
    列举出当前文件夹所有文件的名称
     */
    public static void printFileName(String basicStrFilePath, String clusterFilePath, String compressFilePath) {
        System.out.println("Origin FileNames:");
        File file1 = new File(clusterFilePath);
        File[] files = file1.listFiles();
        for (File f : files) {
            System.out.println("\"" + f.getPath() + "\",");
        }

        System.out.println("BasicStr FileNames:");
        File file2 = new File(basicStrFilePath);
        for (File f : files) {
            System.out.println("\"" + file2.getPath() + "/" + f.getName() + ".raw\",");

        }

        System.out.println("Compress FileNames:");
        File file3 = new File(compressFilePath);
        for (File f : files) {
            System.out.println("\"" + file3.getPath() + "/" + f.getName() + ".dlt\",");
        }
    }


    @Test
    public void test() {
        String a = "/home/charles/WorkSpace/logparser/myresult/" +
                method +
                "/basicStrFiles";
        String b = "/home/charles/WorkSpace/logparser/myresult/" +
                method +
                "/clusterFiles";
        String c = "/home/charles/WorkSpace/logparser/myresult/" +
                method +
                "/compressedFiles";
        System.out.println(method);
//        printFileName(a, b, c);
        calculateSize(b, a, c);
    }


    /*
    调用delta方法进行压缩
     */
    public static void callDelta()throws Exception {
        String basicStrFiles = "/home/charles/WorkSpace/logparser/myresult/" +
                method +
                "/basicStrFiles";
        String clusterFiles = "/home/charles/WorkSpace/logparser/myresult/" +
                method +
                "/clusterFiles";
        String compressedFiles = "/home/charles/WorkSpace/logparser/myresult/" +
                method +
                "/compressedFiles";

        File file1 = new File(clusterFiles);
        File file2 = new File(basicStrFiles);
        File file3 = new File(compressedFiles);
        File[] files = file1.listFiles();
        for (File f : files) {
            String logFile = f.getPath();
            String basicFile = file2.getPath() + "/" + f.getName() + ".raw";
            String compressFile = file3.getPath() + "/" + f.getName() + ".dlt";
            DeltaCompress.processDelta(logFile,basicFile,compressFile);
        }

    }

    /*
    计算文件夹大小
     */
    public static void calculateSize(String clusterFiles, String basicStrFiles, String compressedFiles) {
        long clusterLen = getFileSize(clusterFiles);
        int clusters = new File(clusterFiles).listFiles().length;

        long basicLen = getFileSize(basicStrFiles);
        long compressLen = getFileSize(compressedFiles);
        int compress = new File(compressedFiles).listFiles().length;
        long totalSize = compressLen + basicLen;
        System.out.println("聚类文件大小：" + clusterLen + ",聚类文件数量：" + clusters);
        System.out.println("压缩后文件大小：" + compressLen + ",压缩文件数量：" + compress);
        System.out.println("压缩后文件大小(加basic)：" + totalSize);

    }

    //创建方法获取指定文件夹下的文件大小并将其打印出来,参数为文件夹绝对路径,返回值文件的大小
    public static long getFileSize(String file) {
        //创建文件对象
        File f = new File(file);
        if (f.exists() && f.isDirectory()) {//文件夹存在
            //获取文件夹的文件的集合
            File[] files = f.listFiles();
            long count = 0;//用来保存文件的长度
            for (File file1 : files) {//遍历文件集合
                if (file1.isFile()) {//如果是文件
                    count += file1.length();//计算文件的长度
                } else {
                    count += getFileSize(file1.toString());//递归调用
                }

            }
            return count;
        } else {
            System.out.println("您查询的文件夹有误");
            return 0;
        }
    }
}
