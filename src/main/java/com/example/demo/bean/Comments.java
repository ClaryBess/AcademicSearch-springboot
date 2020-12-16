package com.example.demo.bean;

import lombok.Data;

import java.sql.Timestamp;

public class Comments{
    private Integer id;
    private String content;
    private Long paperId;
    private Integer Commentator;
    private String commentatorName;
    private Timestamp commentTime;

    public Comments(Integer id, String content, Long paperId, Integer commentator, String commentatorName, Timestamp commentTime) {
        this.id = id;
        this.content = content;
        this.paperId = paperId;
        Commentator = commentator;
        this.commentatorName = commentatorName;
        this.commentTime = commentTime;
    }
    public Comments(String content, Long paperId) {
        this.content = content;
        this.paperId = paperId;
    }

    public Comments() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getPaperId() {
        return paperId;
    }

    public void setPaperId(Long paperId) {
        this.paperId = paperId;
    }

    public Integer getCommentator() {
        return Commentator;
    }

    public void setCommentator(Integer commentator) {
        this.Commentator = commentator;
    }

    public String getCommentatorName() {
        return commentatorName;
    }

    public void setCommentatorName(String commentatorName) {
        this.commentatorName = commentatorName;
    }

    public Timestamp getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Timestamp commentTime) {
        this.commentTime = commentTime;
    }
}
