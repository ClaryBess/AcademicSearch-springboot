package com.example.demo.service;

import com.alibaba.fastjson.JSON;
import com.example.demo.bean.Paper;
import com.example.demo.bean.Researcher;
import com.example.demo.mapper.ResearcherMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ResearcherService {

    @Qualifier("elasticsearchClient")
    @Autowired
    RestHighLevelClient client = new RestHighLevelClient(
            RestClient.builder(
                    new HttpHost("10.251.253.212", 9200, "http")));

    public Researcher searchById(long id) throws IOException {
        GetRequest getRequest = new GetRequest("researcher", String.valueOf(id));
        GetResponse response = client.get(getRequest, RequestOptions.DEFAULT);
        String sourceAsString = response.getSourceAsString();
        return JSON.parseObject(sourceAsString, Researcher.class);
    }
}
