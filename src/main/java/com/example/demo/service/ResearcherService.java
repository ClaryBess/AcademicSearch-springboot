package com.example.demo.service;

import com.alibaba.fastjson.JSON;
import com.example.demo.DTO.PaperAuthorDTO;
import com.example.demo.bean.Paper;
import com.example.demo.bean.Researcher;
import com.example.demo.bean.User;
import com.example.demo.mapper.EsResearcherMapper;
import com.example.demo.mapper.ResearcherMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ResearcherService {

    @Autowired
    ResearcherMapper researcherMapper;
    @Autowired
    EsResearcherMapper esresearcherMapper;


    @Qualifier("elasticsearchClient")
    @Autowired
    RestHighLevelClient client = new RestHighLevelClient(
            RestClient.builder(
                    new HttpHost("10.251.253.212", 9200, "http")));



    public void save(Researcher researcher) { esresearcherMapper.save(researcher);}

    public Researcher searchById(String id) throws IOException {
        GetRequest getRequest = new GetRequest("researcher", String.valueOf(id));
        GetResponse response = client.get(getRequest, RequestOptions.DEFAULT);
        String sourceAsString = response.getSourceAsString();
        Researcher researcher = JSON.parseObject(sourceAsString, Researcher.class);

        User user = researcherMapper.getResearcherById(id);
        if(user != null){
            researcher.setEmail(user.getEmail());
            researcher.setPwd(user.getPwd());
            researcher.setTrueName(user.getTrueName());
            researcher.setAvatar(user.getAvatar());
        }

        return researcher;
    }

    public List<String> getFieldByResearcher(String id) throws IOException {
        GetRequest getRequest = new GetRequest("researcher", String.valueOf(id));
        GetResponse response = client.get(getRequest, RequestOptions.DEFAULT);
        String sourceAsString = response.getSourceAsString();
        Researcher researcher = JSON.parseObject(sourceAsString, Researcher.class);
        List<String> field = new ArrayList<String>();
        if(researcher != null){
            field = researcher.getField();
            return field;
        }
        return null;
    }

    public List<Researcher> searchALLResearcher() throws IOException {
        List<Researcher> researchers = new ArrayList<>();
        SearchRequest searchRequest = new SearchRequest("researcher");

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest,RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();
        for (SearchHit hit : hits) {
            String sourceAsString = hit.getSourceAsString();
            Researcher researcher = JSON.parseObject(sourceAsString, Researcher.class);
            researchers.add(researcher);
        }
        return researchers;
    }

    public Researcher searchResearcherByName(String name) throws IOException {
        List<Researcher> researchers = new ArrayList<>();
        SearchRequest searchRequest = new SearchRequest("researcher");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("name",name).fuzziness(Fuzziness.AUTO);
        searchSourceBuilder.query(matchQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest,RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();
        for (SearchHit hit : hits) {
            String sourceAsString = hit.getSourceAsString();
            System.out.println(sourceAsString);
            researchers.add(JSON.parseObject(sourceAsString,Researcher.class));
        }
        if(researchers.size() == 0) return null;
        return researchers.get(0);
    }

//    public List<Researcher> searchResearcherByName(String name) throws IOException {
//        List<Researcher> researchers = new ArrayList<>();
//        SearchRequest searchRequest = new SearchRequest("researcher");
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        QueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("name",name).fuzziness(Fuzziness.AUTO);
//        searchSourceBuilder.query(matchQueryBuilder);
//        searchRequest.source(searchSourceBuilder);
//        SearchResponse searchResponse = client.search(searchRequest,RequestOptions.DEFAULT);
//        SearchHits hits = searchResponse.getHits();
//        for (SearchHit hit : hits) {
//            String sourceAsString = hit.getSourceAsString();
//            System.out.println(sourceAsString);
//            researchers.add(JSON.parseObject(sourceAsString,Researcher.class));
//        }
//        return researchers;
//    }

    public List<Researcher> searchResearcherByField(String field) throws IOException {
        List<Researcher> researchers = new ArrayList<>();
        SearchRequest searchRequest = new SearchRequest("researcher");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("field",field).fuzziness(Fuzziness.AUTO);
        searchSourceBuilder.query(matchQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest,RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();
        for (SearchHit hit : hits) {
            String sourceAsString = hit.getSourceAsString();
            System.out.println(sourceAsString);
            researchers.add(JSON.parseObject(sourceAsString,Researcher.class));
        }
        return researchers;
    }

    public Researcher searchByAuthorid(String id) throws IOException {
        GetRequest getRequest = new GetRequest("researcher", String.valueOf(id));
        GetResponse response = client.get(getRequest, RequestOptions.DEFAULT);
        String sourceAsString = response.getSourceAsString();
        Researcher researcher = JSON.parseObject(sourceAsString, Researcher.class);
        return researcher;
    }
}
