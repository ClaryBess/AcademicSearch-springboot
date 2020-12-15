package com.example.demo.service;

import com.alibaba.fastjson.JSON;
import com.example.demo.bean.Paper;
import com.example.demo.mapper.PaperMaper;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class PaperService{
    @Qualifier("elasticsearchClient")
    @Autowired
    RestHighLevelClient client;

    @Autowired
    PaperMaper paperMaper;

    public void save(Paper paper){
        paperMaper.save(paper);
    }

    public void delete(long id){
        paperMaper.deleteById(id);
    }

    //成功更新返回1
    //无更新返回2
    //更新失败返回0
    public int update(long id,Paper paper) throws IOException {
        //构建改的hashmap
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("id",id);
        if(paper.getTitle() != null) jsonMap.put("title", paper.getTitle());
        if(paper.getPaperTime() != null) jsonMap.put("paperTime", paper.getPaperTime());
        if(paper.getField() != null) jsonMap.put("field", paper.getField());
        if(paper.getUrl() != null) jsonMap.put("url", paper.getUrl());
        if(paper.getKeyWord() != null) jsonMap.put("keyWord", paper.getKeyWord());
        if(paper.getAbstract() != null) jsonMap.put("Abstract", paper.getAbstract());
        //构建updateRequest
        UpdateRequest updateRequest = new UpdateRequest("paper", "_doc", String.valueOf(id));
        updateRequest.doc(jsonMap);
        UpdateResponse updateResponse = client.update(updateRequest,RequestOptions.DEFAULT);
        if (updateResponse.getResult() == DocWriteResponse.Result.UPDATED)
            return 1;
        else if (updateResponse.getResult() == DocWriteResponse.Result.NOOP)
            return 2;
        else return 0;
    }

    public Paper search(long id) throws IOException {
        GetRequest getRequest = new GetRequest("paper", String.valueOf(id));
        GetResponse response =  client.get(getRequest, RequestOptions.DEFAULT);
        String sourceAsString = response.getSourceAsString();
        return JSON.parseObject(sourceAsString, Paper.class);
    }
}
