package com.example.demo.bean;

import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "author")
public class Researcher {
    private Long id;
    private String name;
    private String trueName;
    private String organization;
    private Integer paperCount;
    private String index;
    private String email;
    private String pwd;
    private String info;
    private Integer role = 2;
    private String field1;
    private String field2;
    private String field3;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public Integer getPaperCount() {
        return paperCount;
    }

    public void setPaperCount(Integer paperCount) {
        this.paperCount = paperCount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    public String getField3() {
        return field3;
    }

    public void setField3(String field3) {
        this.field3 = field3;
    }

    public Researcher(Long id, String name, String trueName, String organization, Integer paperCount, String index, String email, String pwd, String info, Integer role, String field1, String field2, String field3) {
        this.id = id;
        this.name = name;
        this.trueName = trueName;
        this.organization = organization;
        this.paperCount = paperCount;
        this.index = index;
        this.email = email;
        this.pwd = pwd;
        this.info = info;
        this.role = role;
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
    }
}
