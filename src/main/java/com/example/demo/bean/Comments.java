package com.example.demo.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;
@Data
@TableName(value="Comments")  //表名
public class Comments{
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String content;
    private String paperId;
    private Integer Commentator;
    private String commentatorName;
    private Timestamp commentTime;

    public Comments(Integer id, String content, String paperId, Integer commentator, String commentatorName, Timestamp commentTime) {
        this.id = id;
        this.content = content;
        this.paperId = paperId;
        Commentator = commentator;
        this.commentatorName = commentatorName;
        this.commentTime = commentTime;
    }
    public Comments(String content, String paperId) {
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

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
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
