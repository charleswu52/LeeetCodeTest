package esapi;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.WrapperQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;

/**
 * @author WuChao
 * @since 2021/4/16 下午3:20
 */
public class SearchTest {

    public static void main(String[] args) throws Exception {
        Settings settings = Settings.builder()
                .put("cluster.name", "my-esTest").build();
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));

        StringBuffer dsl = new StringBuffer();
        String keyWord = "SELECT";
        dsl.append("{\n" +
                "    \"bool\": {\n" +
                "      \"must\": [\n" +
                "        {\n" +
                "          \"match\": {\n" +
                "            \"query\": \"" +
                keyWord +
                "\"\n" +
                "          }\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  }");
//        System.out.println(dsl.toString());
        WrapperQueryBuilder wqb = QueryBuilders.wrapperQuery(dsl.toString());
        SearchResponse searchResponse = client.prepareSearch("log_plain")
                .setTypes("doc").setQuery(wqb).setSize(10).get();
        System.out.println(searchResponse.toString());
        SearchHit[] hits = searchResponse.getHits().getHits();
        for(SearchHit hit : hits){
            String content = hit.getSourceAsString();
            System.out.println(content);
        }


        client.close();
    }
}
