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
<<<<<<< HEAD
    private String title;  //题目
    private String Abstract;  //摘要
    private Timestamp paperTime; //发布时间
    private String  Author;  //作者名字
=======

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Timestamp getPaperTime() {
        return paperTime;
    }

    public void setPaperTime(Timestamp paperTime) {
        this.paperTime = paperTime;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getAbstract() {
        return Abstract;
    }

    public void setAbstract(String anAbstract) {
        Abstract = anAbstract;
    }

    public Integer getAuthorId() {
        return AuthorId;
    }

    public void setAuthorId(Integer authorId) {
        AuthorId = authorId;
    }

    private String title;
    private Timestamp paperTime;
    private String field;
>>>>>>> edb0869b25284ec0333e2e77b5096a456524802f
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
