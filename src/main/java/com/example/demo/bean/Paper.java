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
    private String title;  //题目
    private String Abstract;  //摘要
    private Timestamp paperTime; //发布时间
    private String  Author;  //作者名字
    private String url;   //网址
    private String field;   //领域
    private Integer citation; //被引用量
    private String keyWord; //关键字


    public Paper(){

    }

    public Paper(Long id, String title, String anAbstract, Timestamp paperTime, String author, String url, String field, Integer citation, String keyWord) {
        this.id = id;
        this.title = title;
        Abstract = anAbstract;
        this.paperTime = paperTime;
        Author = author;
        this.url = url;
        this.field = field;
        this.citation = citation;
        this.keyWord = keyWord;
    }
}
