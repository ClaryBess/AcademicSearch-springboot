package com.example.demo.bean;

public class Collection {
    private Integer id;
    private Integer directory;
    private Long paper;
    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDirectory() {
        return directory;
    }

    public void setDirectory(Integer directory) {
        this.directory = directory;
    }

    public Long getPaper() {
        return paper;
    }

    public void setPaper(Long paper) {
        this.paper = paper;
    }
}
