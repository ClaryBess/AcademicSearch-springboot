package com.example.demo.bean;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.sql.Timestamp;

@Data
@Document(indexName = "paper")
public class Paper {
    @Id
    private Long id;
    private String title;
    private Timestamp paperTime;
    private String field;
    private String url;   //网址
    private String keyWord; //关键字
    private String Abstract;  //摘要
    private Integer AuthorId;  //作者id


    public Paper(){

    }

    public Paper(Long id, String title, Timestamp paperTime, String field, String url, String keyWord, String anAbstract) {
        this.id = id;
        this.title = title;
        this.paperTime = paperTime;
        this.field = field;
        this.url = url;
        this.keyWord = keyWord;
        Abstract = anAbstract;
    }
}
