package com.example.demo.service;

import com.alibaba.fastjson.JSON;
import com.example.demo.mapper.ArticleSearchRepository;
import com.example.demo.bean.Article;
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
public class ArticleService implements EsArticleService{
    @Qualifier("elasticsearchClient")
    @Autowired
    RestHighLevelClient client;
    @Autowired
    ArticleSearchRepository articleSearchRepository;

    @Override
    public void save(Article article){
        articleSearchRepository.save(article);
    }
    @Override
    public void delete(long id){
        articleSearchRepository.deleteById(id);
    }
    @Override
    //成功更新返回1
    //无更新返回2
    //更新失败返回0
    public int update(long id, Article article) throws IOException {
        //构建改的hashmap
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("id",id);
        if(article.getTitle() != null) jsonMap.put("title", article.getTitle());
        if(article.getAbstracts() != null) jsonMap.put("abstracts", article.getAbstracts());
        if(article.getContent() != null) jsonMap.put("content", article.getContent());
        if(article.getPostTime() != null) jsonMap.put("postTime", article.getPostTime());
        if(article.getClickCount() != null) jsonMap.put("clickCount", article.getClickCount());
        if(article.getAuthor() != null) jsonMap.put("author", article.getAuthor());
        if(article.getTutorial() != null) jsonMap.put("tutorial", article.getTutorial());
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

    @Override
    public Article search(long id) throws IOException {
        GetRequest getRequest = new GetRequest("paper", String.valueOf(id));
        GetResponse response =  client.get(getRequest, RequestOptions.DEFAULT);
        String sourceAsString = response.getSourceAsString();

        return JSON.parseObject(sourceAsString, Article.class);
    }
}
