package loggingprocess;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author WuChao
 * @create 2021/11/10 18:32
 */
public class CountDayLogging {
    // 统计一天内产生多少logging数据
    public static void main(String[] args) throws Exception {
        String filePath = "E:\\研究生学习\\华为压缩索引检索项目\\ESRally数据\\logging\\解压数据\\documents-191998.json\\input" +
                "\\documents-191998.json";
        File file = new File(filePath);

        BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(file), StandardCharsets.UTF_8));

        String temp = null;
        int id = 0;
        HashMap<String, Long> map = new HashMap<>();
        while ((temp = reader.readLine()) != null) {
            id++;
            JSONObject jsonObject = JSONObject.parseObject(temp);
            Object value = jsonObject.get("@timestamp");
            String timeStamp = stampToTime(value + "000");
//            String key = timeStamp.substring(0, 10);
            map.put(timeStamp, map.getOrDefault(timeStamp, 0L) + 1);
        }
        System.out.println(id+"条数据");
        reader.close();
        Iterator<Map.Entry<String, Long>> iterator = map.entrySet().iterator();
        long res = 0;
        while (iterator.hasNext()) {
            Map.Entry<String, Long> next = iterator.next();
            System.out.println(next.getKey() + ":" + next.getValue());
            res += next.getValue();
        }
        System.out.println(res);

    }

    public static String stampToTime(String s) throws Exception{
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long lt = new Long(s);//将时间戳转换为时间
        Date date = new Date(lt);//将时间调整为yyyy-MM-dd HH:mm:ss时间样式
        res = simpleDateFormat.format(date);
        return res;
    }

    @Test
    public void test() throws Exception {
        String s = "894051017000";
        System.out.println(stampToTime(s));
    }
}
