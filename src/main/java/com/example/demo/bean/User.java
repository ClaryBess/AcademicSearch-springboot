package com.example.demo.bean;

public class User {
    Integer userid;
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
    Long id; //对应ES中的学者ID

    public User(Integer userid, String name, String trueName, String organization, String email, Integer paperCount, Integer index, String pwd, String info, Integer role, String avatar, Long id) {
        this.userid = userid;
        this.name = name;
        this.trueName = trueName;
        this.organization = organization;
        this.email = email;
        this.paperCount = paperCount;
        this.index = index;
        this.pwd = pwd;
        this.info = info;
        this.role = role;
        this.avatar = avatar;
        this.id = id;
    }

    public User() {
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

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
