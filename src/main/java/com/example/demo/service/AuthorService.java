package com.example.demo.service;

import com.alibaba.fastjson.JSON;
import com.example.demo.mapper.AuthorEsRepository;
import com.example.demo.bean.Author;
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
public class AuthorService implements EsAuthorService{
    @Qualifier("elasticsearchClient")
    @Autowired
    RestHighLevelClient client;

    @Autowired
    AuthorEsRepository authorEsRepository;
    @Override
    public void save(Author author){  authorEsRepository.save(author); }
    @Override
    public void delete(long id){
        authorEsRepository.deleteById(id);
    }
    @Override
    //成功更新返回1
    //无更新返回2
    //更新失败返回0
    public int update(long id, Author author) throws IOException {
        //构建改的hashmap
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("id",id);
        if(author.getName() != null) jsonMap.put("name", author.getName());
        if(author.getRemark() != null) jsonMap.put("remark", author.getRemark());
        //构建updateRequest
        UpdateRequest updateRequest = new UpdateRequest("author",String.valueOf(id));
        updateRequest.doc(jsonMap);
        UpdateResponse updateResponse = client.update(updateRequest,RequestOptions.DEFAULT);
        if (updateResponse.getResult() == DocWriteResponse.Result.UPDATED)
            return 1;
        else if (updateResponse.getResult() == DocWriteResponse.Result.NOOP)
            return 2;
        else return 0;
    }

    @Override
    public Author search(long id) throws IOException {
        GetRequest getRequest = new GetRequest("author", String.valueOf(id));
        GetResponse response =  client.get(getRequest, RequestOptions.DEFAULT);
        String sourceAsString = response.getSourceAsString();

        return JSON.parseObject(sourceAsString, Author.class);
    }
}
