package com.example.demo.bean;

public class Follow {
    private Integer id;
    private Integer user;
    private Integer researcher;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public Integer getResearcher() {
        return researcher;
    }

    public void setResearcher(Integer researcher) {
        this.researcher = researcher;
    }

    public Follow(Integer id, Integer user, Integer researcher) {
        this.id = id;
        this.user = user;
        this.researcher = researcher;
    }
}
