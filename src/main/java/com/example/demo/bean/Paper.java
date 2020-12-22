package com.example.demo.bean;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.sql.Timestamp;

@Data
@Document(indexName = "paper")
public class Paper implements Comparable<Paper> {
    @Id
    private String id;
    private String title;  //题目
    private Integer citation; //被引用量
    private Integer year; //发布年份
    //private String field;   //领域

    private String  author[];  //作者名字
    private String keywords[]; //关键字
    private String url;   //网址
    private String Abstract;  //摘要
    private String authorShow;//作者显示
    private String keyWordShow;//关键词显示


    public Paper(String id, String title, Integer citation, Integer year, String[] author, String[] keywords, String url, String Abstract) {
        this.id = id;
        this.title = title;
        this.citation = citation;
        this.year = year;
        //this.field = keyWord[0];
        this.keywords = keywords;
        this.author = author;

        this.url = url;
        this.Abstract = Abstract;
    }
    public Paper(String title, Integer citation, Integer year, String[] author, String[] keywords, String url, String Abstract) {
        this.title = title;
        this.citation = citation;
        this.year = year;
        //this.field = field;
        this.author = author;
        this.keywords = keywords;
        this.url = url;
        this.Abstract = Abstract;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

//    public String getField() {
//        return field;
//    }
//
//    public void setField(String field) {
//        this.field = field;
//    }


    public String[] getAuthor() {
        return author;
    }

    public void setAuthor(String[] author) {
        this.author = author;
    }

    public String[] getKeywords() {
        return keywords;
    }

    public void setKeywords(String[] keywords) {
        this.keywords = keywords;
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
