package com.example.demo.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.sql.Timestamp;

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


    public Paper(Long id, String title, Timestamp paperTime, String field, String url, String keyWord, String anAbstract) {
        this.id = id;
        this.title = title;
        this.paperTime = paperTime;
        this.field = field;
        this.url = url;
        this.keyWord = keyWord;
        Abstract = anAbstract;
    }

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

    public void setAbstract(String Abstract) {
        this.Abstract = Abstract;
    }
}
