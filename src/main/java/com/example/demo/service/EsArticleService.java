package com.example.demo.service;

import com.example.demo.bean.Article;

import java.io.IOException;

public interface EsArticleService {

    //存储
    void save(Article article);

    //删除
    void delete(long id);

    //更新
    int update(long id, Article article) throws IOException;

    //搜索
    Article search(long id) throws IOException;



}
