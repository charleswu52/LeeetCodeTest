package graduate.publiclog.hadoop;

import com.csvreader.CsvReader;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

/**
 * @author WuChao
 * @create 2022/3/17 13:32
 */
public class HadoopLog2Json {
    // 将 OpenSSH log 日志 转化为 导入到 ES中的JSON文件
    static final int count = 100000; // 10w条数据一个文件
    static String name = "Hadoop";

    // 将 ESRally中的 json数据转换为可以导入ES中的json 数据
    public static void main(String[] args) {
        String str = "hadoop";
        String filePath = "/media/charles/My Passport/Work/LogCompress/logparser/allresult/AEL/" + name + "/" + name + ".log_structured.csv";
        String outPath = "/media/charles/My Passport/Work/LogCompress/logparser/allLogs2Json/" + name + "/";
        transform(filePath, outPath, name);
        printImport(outPath, str);
    }

    public static void transform(String filePath, String outPath, String name) {
        File file = new File(filePath);
        long line = 1;
        try {
            // 读取结果文件 并存到 Map 中
            CsvReader csvReader = new CsvReader(filePath);
            // 读取表头
            csvReader.readHeaders();
            while (csvReader.readRecord()) {
                long cnt = line / count;
                // 读取一整行数据
                Integer id = Integer.parseInt(csvReader.get("LineId"));
                String date = csvReader.get("Date");
                String time = csvReader.get("Time");
                String level = csvReader.get("Level");
                String process = csvReader.get("Process");
                String component = csvReader.get("Component");
                String content = tokenize(csvReader.get("Content"));
                String value1 = "{\"index\":{\"_index\":\"" + "hadoop" + "\",\"_type\":\"_doc\",\"_id\":" + id + "}}\n";
                String value2 = "{\"date\":\"" + date +
                        "\",\"time\":\"" + time +
                        "\",\"level\":\"" + level +
                        "\",\"process\":\"" + process +
                        "\",\"component\":\"" + component +
                        "\",\"content\":\"" + content + "\"}\n";
                // 写入文件
                File newFile = new File(outPath + name + "_" + cnt + ".json");
                if (!newFile.exists()) {
                    newFile.createNewFile();
                }
                // 获取该文件的缓冲输出流
                BufferedWriter bufferedWriter = new BufferedWriter(
                        new OutputStreamWriter(new FileOutputStream(newFile, true), StandardCharsets.UTF_8));
                bufferedWriter.write(value1);
                bufferedWriter.write(value2);
                bufferedWriter.flush();
                bufferedWriter.close();
                line++;
//                break;
            }
            csvReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("当前处理文件包含日志数目为：" + (line - 1));
    }

    /*
    打印导入命令
     */
    public static void printImport(String path, String str) {
        File file = new File(path);
        File[] tempList = file.listFiles();
        System.out.println("当前处理的文件夹：" + path);
        for (int i = 0; i < tempList.length; i++) {
            String fileName = tempList[i].getAbsolutePath();
//            System.out.println(fileName);
            String print = "curl -H \"Content-Type: application/json\" -XPOST 'localhost:9200/" +
                    str +
                    "/_bulk?pretty' --data-binary \"@" +
                    fileName +
                    "\"";
            System.out.println(print);
        }
        System.out.println("-------------------------------------");

    }
    public static String tokenize(String str) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        StandardAnalyzer analyzer = new StandardAnalyzer();
        TokenStream tokenStream = analyzer.tokenStream("", str);
        CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
        tokenStream.reset();
        while (tokenStream.incrementToken()) {
            stringBuilder.append(charTermAttribute + " ");
        }
        tokenStream.close();
        return stringBuilder.substring(0, stringBuilder.length() - 1);
    }
}
