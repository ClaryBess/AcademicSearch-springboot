package com.example.demo.bean;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.sql.Timestamp;

@Data
@Document(indexName = "paper")
public class Paper implements Comparable<Paper> {
    @Id
    private Long id;
    private String title;  //题目
    private Integer citation; //被引用量
    private Integer year; //发布年份
    private String field;   //领域
    private String  Author[];  //作者名字
    private String keyWord[]; //关键字
    private String url;   //网址
    private String Abstract;  //摘要
    private String authorShow;//作者显示
    private String keyWordShow;//关键词显示


    public Paper(Long id, String title, Integer citation, Integer year, String field, String[] author, String[] keyWord, String url, String anAbstract) {
        this.id = id;
        this.title = title;
        this.citation = citation;
        this.year = year;
        this.field = field;
        Author = author;
        this.keyWord = keyWord;
        this.url = url;
        Abstract = anAbstract;
    }
    public Paper(String title, Integer citation, Integer year, String field, String[] author, String[] keyWord, String url, String anAbstract) {
        this.title = title;
        this.citation = citation;
        this.year = year;
        this.field = field;
        Author = author;
        this.keyWord = keyWord;
        this.url = url;
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

    public Integer getCitation() {
        return citation;
    }

    public void setCitation(Integer citation) {
        this.citation = citation;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String[] getAuthor() {
        return Author;
    }

    public void setAuthor(String[] author) {
        Author = author;
    }

    public String[] getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String[] keyWord) {
        this.keyWord = keyWord;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAbstract() {
        return Abstract;
    }

    public void setAbstract(String anAbstract) {
        Abstract = anAbstract;
    }

    public String getAuthorShow() {
        return authorShow;
    }

    public void setAuthorShow(String authorShow) {
        this.authorShow = authorShow;
    }

    public String getKeyWordShow() {
        return keyWordShow;
    }

    public void setKeyWordShow(String keyWordShow) {
        this.keyWordShow = keyWordShow;
    }

    @Override
    public int compareTo(Paper o) {
        return o.getCitation()-this.citation; //升序
        //return o.id-this.id;  降序
    }
}
