package esapi;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.query.MatchPhraseQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;

import java.net.InetAddress;

/**
 * @author WuChao
 * @since 2021/5/7 下午4:04
 */
public class MatchPhraseTest {


    @Test
    public void test()throws Exception {

        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")));


        String key = "this is a";
        MatchPhraseQueryBuilder matchPhraseQueryBuilder = QueryBuilders.matchPhraseQuery("title",key);

        matchPhraseQueryBuilder.boost(10);
        matchPhraseQueryBuilder.analyzer("standard");
        matchPhraseQueryBuilder.slop(2);

//        SearchResponse searchResponse = client.prepareSearch()
//                .setIndices("my_index")
//                .setTypes("my_type")
//                .setQuery(matchPhraseQueryBuilder)
//                .execute()
//                .actionGet();
//        System.out.println(ResponseUtil.parse(searchResponse));
        client.close();
    }
}
