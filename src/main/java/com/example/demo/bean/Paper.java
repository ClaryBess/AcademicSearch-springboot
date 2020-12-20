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


    public Paper(){

    }

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

    @Override
    public int compareTo(Paper o) {
        return o.getCitation()-this.citation; //升序
        //return o.id-this.id;  降序
    }
}
