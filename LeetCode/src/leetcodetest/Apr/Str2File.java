package leetcodetest.Apr;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author WuChao
 * @since 2021/4/6 上午11:31
 */
public class Str2File {
    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i <= 108; i++) {
            stringBuilder.append("curl -H \"Content-Type: application/json\" -XPOST 'localhost:9200/offset/_bulk?pretty'" +
                    " --data-binary \"@/media/charles/Samsung_T5/es-rally/curl/tpcc_general_offset.log_split_5/" +
                    "tpcc_general_offset.log_" + i + ".json\"\n");

        }
        String str=stringBuilder.toString();
        FileWriter writer;
        try {
            writer = new FileWriter("/home/charles/WorkSpace/ES实验/offset索引导入命令.txt");
            writer.write(str);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
