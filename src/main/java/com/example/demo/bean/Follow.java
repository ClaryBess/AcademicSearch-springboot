package com.example.demo.bean;

public class Follow {
    private Integer id;
    private Integer user;
    private Long researcher;

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

    public Long getResearcher() {
        return researcher;
    }

    public void setResearcher(Long researcher) {
        this.researcher = researcher;
    }

    public Follow(Integer id, Integer user, Long researcher) {
        this.id = id;
        this.user = user;
        this.researcher = researcher;
    }
}
