package com.example.demo.bean;

public class Researcher {
    private Long id;
    private String name;
    private String organization;
    private Integer paperCount;
    private String index;
    private String email;
    private String pwd;
    private String info;
    private Integer role = 2;

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

    public Researcher(Long id, String name, String organization, Integer paperCount, String index, String email, String pwd, String info, Integer role) {
        this.id = id;
        this.name = name;
        this.organization = organization;
        this.paperCount = paperCount;
        this.index = index;
        this.email = email;
        this.pwd = pwd;
        this.info = info;
        this.role = role;
    }
}
