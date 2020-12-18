package com.example.demo.service;

import com.alibaba.fastjson.JSON;
import com.example.demo.bean.Paper;
import com.example.demo.bean.Researcher;
import com.example.demo.mapper.PaperMapper;
import com.example.demo.mapper.ResearcherMapper;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;

import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaperService {
    @Qualifier("elasticsearchClient")
    @Autowired
    RestHighLevelClient client;

    @Autowired
    PaperMapper paperMapper;

    @Autowired
    ResearcherMapper researcherMapper;



    public void save(Paper paper) {
        paperMapper.save(paper);
    }

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
        if (paper.getPaperTime() != null) jsonMap.put("paperTime", paper.getPaperTime());
        if (paper.getField() != null) jsonMap.put("field", paper.getField());
        if (paper.getUrl() != null) jsonMap.put("url", paper.getUrl());
        if (paper.getKeyWord() != null) jsonMap.put("keyWord", paper.getKeyWord());
        if (paper.getAbstract() != null) jsonMap.put("Abstract", paper.getAbstract());
        if (paper.getAuthor() != null) jsonMap.put("Author", paper.getAuthor());
        if (paper.getCitation() != null) jsonMap.put("citation", paper.getCitation());

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
        Researcher researcher = researcherMapper.getResearcherById(AuthorId);
        String Author=researcher.getName();
        return searchByAuthorName(Author);
    }

    //按作者名字查询
    public List<Paper> searchByAuthorName(String name) throws IOException {
        List<Paper> paperList = new ArrayList<>();
        SearchRequest searchRequest = new SearchRequest("paper");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("Author",name);
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





}
