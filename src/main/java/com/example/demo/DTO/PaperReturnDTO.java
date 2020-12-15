package com.example.demo.DTO;


import com.example.demo.bean.Comments;
import com.example.demo.bean.CommonResult;

import java.sql.Timestamp;
import java.util.List;

public class PaperReturnDTO {
    private Integer id;
    private Timestamp paperTime;//创建时间
    private String field;
    private String url;   //网址
    private String keyWord; //关键字
    private String Abstract;  //摘要
    private Integer creator;
    private String creatorName;
    private Integer collectCount;
    private List<Comments> comments;
    private CommonResult resultDTO;//返回状态

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getCreator() {
        return creator;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
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


    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public Integer getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(Integer collectCount) {
        this.collectCount = collectCount;
    }


    public CommonResult getResultDTO() {
        return resultDTO;
    }

    public void setResultDTO(CommonResult resultDTO) {
        this.resultDTO = resultDTO;
    }
}
