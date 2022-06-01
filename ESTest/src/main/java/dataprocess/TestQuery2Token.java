package dataprocess;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author WuChao
 * @since 2021/4/29 下午4:51
 */
public class TestQuery2Token {
    // 将之前的query进行分词处理
    public static void main(String[] args) throws Exception {

        String[] fields = {
                "DB1.DBW2",
                " (C1_B1_G1_Tag3)",
                "SHOW TAG VALUES ON",
                "pointswrittenok",
                "database",
                "SL_1_Tag114",
                " count",
                "difference",
                "hostname",
                "autogen",
                "AND time>='2021-03-29T08:36:32Z' AND time <='2021-03-30T08:36:32Z\n"
        };
        for (String field : fields) {
            String trans = "";
//            StandardAnalyzer analyzer = new StandardAnalyzer();
//            TokenStream tokenStream = analyzer.tokenStream("", field);
//            CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
//            tokenStream.reset();
//            while (tokenStream.incrementToken()) {
//                trans += charTermAttribute.toString() + " ";
////                                System.out.print(charTermAttribute.toString()+" ");
//            }
//            tokenStream.close();
            System.out.println(trans);
        }
    }

    public void cutQuery(String field) throws Exception {
        String trans = "";
        int tokenLen = 0;
        Set<String> set = new HashSet<>();
        StandardAnalyzer analyzer = new StandardAnalyzer();
        TokenStream tokenStream = analyzer.tokenStream("", field);
        CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
        tokenStream.reset();
        while (tokenStream.incrementToken()) {
            trans += charTermAttribute.toString() + " ";
            set.add(charTermAttribute.toString());
            tokenLen++;
        }
        tokenStream.close();
        System.out.println("分词结果：\n" + trans + "\ntokenLength:" + tokenLen + ";tokenSetLen:" + set.size());
    }

    @Test
    public void test() throws Exception {
        String query =
                "as sl_1_tag108 from szsslkjyxgs_1ou7 autogen 68885b4c32ff4708a8a649c9b4baae59 where gatewayid 2c938083783650af01786535768406ac and time 2021 03 29t08 44\n";
//        String s = "[client 219.236.211.74] File does not exist: /var/www/html/scripts/..\\xc1\\x1c..,55c99ad0,[client <*>] File does not exist: <*>,\"['219.236.211.74', '/var/www/html/scripts/..\\\\xc1\\\\x1c..'][client 219.236.211.74] File does not exist: /var/www/html/scripts/..\\xc1\\x1c..,55c99ad0,[client <*>] File does not exist: <*>,\"['219.236.211.74', '/var/www/html/scripts/..\\\\xc1\\\\x1c..']";
        String[] sd =
                {
                        "GET /images/11288.jpg HTTP/1.0",
                        "GET /images/11288.jpg HTTP/1.0",
                        "GET /images/11185.gif HTTP/1.0",
                        "GET /english/news/11415.htm HTTP/1.0",
                        "GET /english/news/11415.htm HTTP/1.0",
                        "GET /images/11288.jpg HTTP/1.1",
                };
        for (String s : sd) {
            System.out.println(s);
            cutQuery(s);
        }

    }
}
