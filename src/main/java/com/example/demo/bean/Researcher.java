package com.example.demo.bean;

public class Researcher {
    private Long id;
    private String name;
    private String organization;
    private Integer paperCount;
    private String edu;
    private String email;
    private String pwd;
    private String info;

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

    public String getEdu() {
        return edu;
    }

    public void setEdu(String edu) {
        this.edu = edu;
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

    public Researcher(Long id, String name, String organization, Integer paperCount, String edu, String email, String pwd, String info) {
        this.id = id;
        this.name = name;
        this.organization = organization;
        this.paperCount = paperCount;
        this.edu = edu;
        this.email = email;
        this.pwd = pwd;
        this.info = info;
    }
}
