package com.example.demo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.DTO.HotDTO;
import com.example.demo.bean.Paper;
import com.example.demo.bean.Researcher;
import com.example.demo.bean.User;
import com.example.demo.mapper.PaperMapper;
import com.example.demo.mapper.ResearcherMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;

import org.elasticsearch.action.search.*;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

@Service
public class PaperService {
    @Qualifier("elasticsearchClient")
    @Autowired

    RestHighLevelClient client = new RestHighLevelClient(
            RestClient.builder(
            new HttpHost("10.251.253.212", 9200, "http")));

    @Autowired
    PaperMapper paperMapper;

    @Autowired
    ResearcherMapper researcherMapper;

    public void save(Paper paper) { paperMapper.save(paper);}


    public void delete(String id) {
        paperMapper.deleteById(id);
    }

    //成功更新返回1
    //无更新返回2
    //更新失败返回0
    public int update(String id, Paper paper) throws IOException {
        //构建改的hashmap
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("id", id);
        if (paper.getTitle() != null) jsonMap.put("title", paper.getTitle());
        if (paper.getYear() != null) jsonMap.put("year", paper.getYear());
        if (paper.getCitation() != null) jsonMap.put("citation", paper.getCitation());
        //if (paper.getField() != null) jsonMap.put("field", paper.getField());
        if (paper.getUrl() != null) jsonMap.put("url", paper.getUrl());
        if (paper.getKeywords() != null) jsonMap.put("keywords", paper.getKeywords());
        if (paper.getAbstract() != null) jsonMap.put("abstract", paper.getAbstract());
        if (paper.getAuthor() != null) jsonMap.put("author", paper.getAuthor());

        //构建updateRequest
        UpdateRequest updateRequest = new UpdateRequest("paper", "_doc", String.valueOf(id));
        updateRequest.doc(jsonMap);
        UpdateResponse updateResponse = client.update(updateRequest, RequestOptions.DEFAULT);
        if (updateResponse.getResult() == DocWriteResponse.Result.UPDATED)
            return 1;
        else if (updateResponse.getResult() == DocWriteResponse.Result.NOOP)
            return 2;
        else return 0;
    }

    public Paper search(String id) throws IOException {
        GetRequest getRequest = new GetRequest("paper", String.valueOf(id));
        GetResponse response = client.get(getRequest, RequestOptions.DEFAULT);
        String sourceAsString = response.getSourceAsString();
        return JSON.parseObject(sourceAsString, Paper.class);
    }

    //按作者id查询
    public List<Paper> searchByAuthorId(String AuthorId) throws IOException {
        User user = researcherMapper.getResearcherById(AuthorId);
        String Author = user.getName();
        return searchByAuthorName(Author);
    }

    //按作者名字查询
    public List<Paper> searchByAuthorName(String name) throws IOException {
        List<Paper> paperList = new ArrayList<>();
        SearchRequest searchRequest = new SearchRequest("paper");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder matchQueryBuilder = matchQuery("author",name);
        searchSourceBuilder.query(matchQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest,RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();
        for (SearchHit hit : hits) {
            String sourceAsString = hit.getSourceAsString();
            paperList.add(JSON.parseObject(sourceAsString,Paper.class));
        }

        return paperList;
    }


    //按关键字查询,模糊查询
    public List<Paper> getPaperByKeyWord(String KeyWord, String field) throws IOException {
        List<Paper> paperList = new ArrayList<>();
        MultiSearchRequest request = new MultiSearchRequest();
        SearchRequest keySearchRequest = new SearchRequest("paper");
        SearchRequest fieldSearchRequest = new SearchRequest("paper");
        SearchSourceBuilder keySearchSourceBuilder = new SearchSourceBuilder();
        SearchSourceBuilder fieldSearchSourceBuilder = new SearchSourceBuilder();
        MatchQueryBuilder keyMatchQueryBuilder = matchQuery("Abstract", KeyWord)
                .fuzziness(Fuzziness.AUTO)
                .prefixLength(1)
                .maxExpansions(10);
        MatchQueryBuilder fieldMatchQueryBuilder = matchQuery("keywords", field);

        keySearchSourceBuilder.query(keyMatchQueryBuilder);
        fieldSearchSourceBuilder.query(fieldMatchQueryBuilder);

        keySearchRequest.source(keySearchSourceBuilder);
        fieldSearchRequest.source(fieldSearchSourceBuilder);
        request.add(keySearchRequest);
        request.add(fieldSearchRequest);

        MultiSearchResponse response = client.msearch(request, RequestOptions.DEFAULT);

        MultiSearchResponse.Item firstResponse = response.getResponses()[0];
        SearchResponse firstSearchResponse = firstResponse.getResponse();
        SearchHits firstSearchHits = firstSearchResponse.getHits();

        MultiSearchResponse.Item secondResponse = response.getResponses()[1];
        SearchResponse secondSearchResponse = secondResponse.getResponse();
        SearchHits secondSearchHits = secondSearchResponse.getHits();

        for (SearchHit hit : firstSearchHits) {
            String sourceAsString = hit.getSourceAsString();
            paperList.add(JSON.parseObject(sourceAsString, Paper.class));
        }
        for (SearchHit hit : secondSearchHits) {
            String sourceAsString = hit.getSourceAsString();
            paperList.add(JSON.parseObject(sourceAsString, Paper.class));
        }
        paperList = paperList.stream().distinct().collect(Collectors.toList());
        return paperList;
    }

    // 模糊查询，在标题、摘要、关键字中查询
    public List<Paper> fuzzySearch(String KeyWord) throws IOException {
        List<Paper> paperList = new ArrayList<>();
        MultiSearchRequest request = new MultiSearchRequest();
        SearchRequest searchRequest1 = new SearchRequest("paper");
        SearchRequest searchRequest2 = new SearchRequest("paper");
        SearchRequest searchRequest3 = new SearchRequest("paper");

        SearchSourceBuilder searchSourceBuilder1 = new SearchSourceBuilder();
        SearchSourceBuilder searchSourceBuilder2 = new SearchSourceBuilder();
        SearchSourceBuilder searchSourceBuilder3 = new SearchSourceBuilder();

        QueryBuilder matchQueryBuilder1 = matchQuery("keywords",KeyWord).fuzziness(Fuzziness.AUTO);
        QueryBuilder matchQueryBuilder2 = matchQuery("title",KeyWord).fuzziness(Fuzziness.AUTO);
        QueryBuilder matchQueryBuilder3 = matchQuery("Abstract",KeyWord).fuzziness(Fuzziness.AUTO);

        searchSourceBuilder1.query(matchQueryBuilder1);
        searchSourceBuilder2.query(matchQueryBuilder2);
        searchSourceBuilder3.query(matchQueryBuilder3);

        searchRequest1.source(searchSourceBuilder1);
        searchRequest2.source(searchSourceBuilder2);
        searchRequest3.source(searchSourceBuilder3);

        request.add(searchRequest1);
        request.add(searchRequest2);
        request.add(searchRequest3);

        MultiSearchResponse response = client.msearch(request, RequestOptions.DEFAULT);

        MultiSearchResponse.Item Response1 = response.getResponses()[0];
        SearchResponse SearchResponse1 = Response1.getResponse();
        SearchHits hits1 = SearchResponse1.getHits();

        MultiSearchResponse.Item Response2 = response.getResponses()[1];
        SearchResponse SearchResponse2 = Response2.getResponse();
        SearchHits hits2 = SearchResponse2.getHits();

        MultiSearchResponse.Item Response3 = response.getResponses()[2];
        SearchResponse SearchResponse3 = Response3.getResponse();
        SearchHits hits3 = SearchResponse3.getHits();

        for (SearchHit hit : hits1) {
            String sourceAsString = hit.getSourceAsString();
            paperList.add(JSON.parseObject(sourceAsString, Paper.class));
        }
        for (SearchHit hit : hits2) {
            String sourceAsString = hit.getSourceAsString();
            paperList.add(JSON.parseObject(sourceAsString, Paper.class));
        }
        for (SearchHit hit : hits3) {
            String sourceAsString = hit.getSourceAsString();
            paperList.add(JSON.parseObject(sourceAsString, Paper.class));
        }
        paperList = paperList.stream().distinct().collect(Collectors.toList());
        return paperList;
    }

    //按被引量排序
    //但由于es通常没有开放排序功能，对list排序
    public List<HotDTO> OrderByCitation() throws IOException {
        List<HotDTO> paperList = new ArrayList<>();
        SearchRequest searchRequest = new SearchRequest("paper");

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);
//        //按被引量降序排列
//        searchSourceBuilder.sort(new FieldSortBuilder("citation").order(SortOrder.DESC));
        SearchResponse searchResponse = client.search(searchRequest,RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();
        for (SearchHit hit : hits) {
            String sourceAsString = hit.getSourceAsString();
            Paper paper=JSON.parseObject(sourceAsString, Paper.class);
            StringBuffer author = new StringBuffer();
            for(int i = 0; i < paper.getAuthor().length-1; i++){
                author. append(paper.getAuthor()[i]+"，");
            }
            author. append(paper.getAuthor()[paper.getAuthor().length-1]);
            String s = author.toString();
            HotDTO hot=new HotDTO(paper.getId(),paper.getTitle(),s,paper.getYear(),paper.getCitation());
            paperList.add(hot);
        }
        //排序
        Collections.sort(paperList);
        return paperList;
    }

    // 按学科领域查询
    public List<Paper> getPaperByField(String field) throws IOException {
        System.out.println("*******"+field+"*******");
        List<Paper> paperList = new ArrayList<>();
        SearchRequest searchRequest = new SearchRequest("paper");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder matchQueryBuilder = matchQuery("keywords",field)
                .fuzziness(Fuzziness.AUTO);
        searchSourceBuilder.query(matchQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest,RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();
        for (SearchHit hit : hits) {
            String sourceAsString = hit.getSourceAsString();
            paperList.add(JSON.parseObject(sourceAsString, Paper.class));
        }
        System.out.println(paperList);
        return paperList;
    }

    // 按发表年份范围查询
    public List<Paper> getPaperByYear(int start, int end) throws IOException {
        List<Paper> paperList = new ArrayList<>();
        SearchRequest searchRequest = new SearchRequest("paper");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.rangeQuery("year").lte(end).gte(start));
        searchRequest.source(searchSourceBuilder);
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        for (SearchHit hit : hits) {
            String sourceAsString = hit.getSourceAsString();
            paperList.add(JSON.parseObject(sourceAsString, Paper.class));
        }
        paperList = paperList.stream().distinct().collect(Collectors.toList());
        return paperList;
    }

 //热门领域
    public List<String> HotField() throws IOException {
        List<String> hotFiled=new ArrayList<>();
        SearchRequest searchRequest = new SearchRequest("paper");
        List<Paper> paperList=searchALLPaper();
        //排序
        Collections.sort(paperList);
        for(int i=0;i<30&&i<paperList.size();i++){
            String filed=paperList.get(i).getKeywords()[0];
            int exist=0;
            for(String s:hotFiled){
                if(s.equals(filed)){
                    exist=1;
                    break;
                }
            }
            if(exist==0) hotFiled.add(filed);
        }
        return hotFiled;
    }

    //查询paper所有项目
    public List<Paper> searchALLPaper() throws IOException {
        List<Paper> paperList = new ArrayList<>();
        SearchRequest searchRequest = new SearchRequest("paper");

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest,RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();
        for (SearchHit hit : hits) {
            String sourceAsString = hit.getSourceAsString();
            Paper paper=JSON.parseObject(sourceAsString, Paper.class);
            paperList.add(paper);
        }
        return paperList;
    }

    // 获取所有领域（关键词）
    public List<String> getFields() throws IOException {
        List<String> fields = new ArrayList<>();
        SearchRequest searchRequest = new SearchRequest("paper");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchSourceBuilder.size(2000);
        searchRequest.source(searchSourceBuilder);
        searchRequest.scroll(TimeValue.timeValueMinutes(1L));
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        String scrollId = searchResponse.getScrollId();
        SearchHits hits = searchResponse.getHits();
        for (SearchHit hit : hits) {
            String sourceAsString = hit.getSourceAsString();
            System.out.println(sourceAsString);
            Paper paper = JSON.parseObject(sourceAsString, Paper.class);
            for (String e : paper.getKeywords()) {
                fields.add(e);
            }
        }
        return fields;
    }
}
