package com.example.demo.bean;

public class ApplyItem {
    private long id;

    private long user;
    private String state;
    private String feedback;
    private String researcher;
    private String name;

    public ApplyItem(long id, long user, String state, String feedback, String researcher, String name) {
        this.id = id;
        this.user = user;
        this.state = state;
        this.feedback = feedback;
        this.researcher = researcher;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUser() {
        return user;
    }

    public void setUser(long user) {
        this.user = user;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getResearcher() {
        return researcher;
    }

    public void setResearcher(String researcher) {
        this.researcher = researcher;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
