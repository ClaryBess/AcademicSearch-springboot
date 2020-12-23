package com.example.demo.bean;

public class Collection {
    private Integer id;
    private Integer directory;
    private String paper;
    private Integer user;

    public Collection(){}

    public Integer getUserId() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
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

    public String getPaper() {
        return paper;
    }

    public void setPaper(String paper) {
        this.paper = paper;
    }
}
