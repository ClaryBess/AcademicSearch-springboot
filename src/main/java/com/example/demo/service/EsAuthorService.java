package com.example.demo.service;
import com.example.demo.bean.Author;

import java.io.IOException;

public interface EsAuthorService {

    //存储
    void save(Author author);

    //删除
    void delete(long id);

    //更新
    int update(long id, Author author) throws IOException;

    //搜索
    Author search(long id) throws IOException;



}
