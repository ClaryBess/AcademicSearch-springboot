package com.example.demo.bean;

public class User {
    Integer id;
    String name;
    String trueName;
    String organization;
    String email;
    Integer paperCount; //论文数目
    Integer index;  //为n_index指数
    String pwd;
    String info;
    Integer role;   //0为管理员，1为普通用户，2为学者
    String avatar;
    private String field1;
    private String field2;
    private String field3;

    public User(Integer id, String name,String trueName, String organization, String email, Integer paperCount, Integer index, String pwd, String info, Integer role, String avatar) {
        this.id = id;
        this.name = name;
        this.trueName=trueName;
        this.organization = organization;
        this.email = email;
        this.paperCount = paperCount;
        this.index = index;
        this.pwd = pwd;
        this.info = info;
        this.role = role;
        this.avatar = avatar;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getPaperCount() {
        return paperCount;
    }

    public void setPaperCount(Integer paperCount) {
        this.paperCount = paperCount;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getAvatar() { return avatar; }

    public void setAvatar(String avatar) { this.avatar = avatar; }

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
}
