package com.example.demo.service;

import com.alibaba.fastjson.JSON;
import com.example.demo.DTO.HotDTO;
import com.example.demo.bean.Paper;
import com.example.demo.bean.Researcher;
import com.example.demo.bean.User;
import com.example.demo.mapper.PaperMapper;
import com.example.demo.mapper.ResearcherMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import org.elasticsearch.common.unit.Fuzziness;
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


    public void delete(long id) {
        paperMapper.deleteById(id);
    }

    //成功更新返回1
    //无更新返回2
    //更新失败返回0
    public int update(long id, Paper paper) throws IOException {
        //构建改的hashmap
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("id", id);
        if (paper.getTitle() != null) jsonMap.put("title", paper.getTitle());
        if (paper.getYear() != null) jsonMap.put("year", paper.getYear());
        if (paper.getCitation() != null) jsonMap.put("citation", paper.getCitation());
        if (paper.getField() != null) jsonMap.put("field", paper.getField());
        if (paper.getUrl() != null) jsonMap.put("url", paper.getUrl());
        if (paper.getKeyWord() != null) jsonMap.put("keywords", paper.getKeyWord());
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

    public Paper search(long id) throws IOException {
        GetRequest getRequest = new GetRequest("paper", String.valueOf(id));
        GetResponse response = client.get(getRequest, RequestOptions.DEFAULT);
        String sourceAsString = response.getSourceAsString();
        return JSON.parseObject(sourceAsString, Paper.class);
    }

    //按作者id查询
    public List<Paper> searchByAuthorId(long AuthorId) throws IOException {
        User user = researcherMapper.getResearcherById(AuthorId);
        String Author = user.getName();
        return searchByAuthorName(Author);
    }

    //按作者名字查询
    public List<Paper> searchByAuthorName(String name) throws IOException {
        List<Paper> paperList = new ArrayList<>();
        SearchRequest searchRequest = new SearchRequest("paper");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("author",name);
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
    public List<Paper> getPaperByKeyWord(String KeyWord) throws IOException {
        List<Paper> paperList = new ArrayList<>();
        SearchRequest searchRequest = new SearchRequest("paper");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("keyWord",KeyWord)
                .fuzziness(Fuzziness.AUTO);
        searchSourceBuilder.query(matchQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest,RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();
        for (SearchHit hit : hits) {
            String sourceAsString = hit.getSourceAsString();
            paperList.add(JSON.parseObject(sourceAsString, Paper.class));
        }
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
            HotDTO hot=new HotDTO(paper.getTitle(),paper.getAuthor(),paper.getYear(),paper.getCitation());
            paperList.add(hot);
        }
        //排序
        Collections.sort(paperList);
        return paperList;
    }

    // 按学科领域查询
    public List<Paper> getPaperByField(String field) throws IOException {
        List<Paper> paperList = new ArrayList<>();
        SearchRequest searchRequest = new SearchRequest("field");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("field",field)
                .fuzziness(Fuzziness.AUTO);
        searchSourceBuilder.query(matchQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest,RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();
        for (SearchHit hit : hits) {
            String sourceAsString = hit.getSourceAsString();
            paperList.add(JSON.parseObject(sourceAsString, Paper.class));
        }
        return paperList;
    }

    // 按发表年份范围查询
    public List<Paper> getPaperByYear(int start, int end) throws IOException {
        List<Paper> paperList = new ArrayList<>();
        SearchRequest searchRequest = new SearchRequest("year");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.rangeQuery("year").lte(end).gte(start));
        searchRequest.source(searchSourceBuilder);
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        for (SearchHit hit : hits) {
            String sourceAsString = hit.getSourceAsString();
            paperList.add(JSON.parseObject(sourceAsString, Paper.class));
        }
        return paperList;
    }
}
