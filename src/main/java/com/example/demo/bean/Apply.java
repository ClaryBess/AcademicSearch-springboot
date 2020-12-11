package com.example.demo.bean;

public class Apply {
    private long id;

    private long user;
    private String state;
    private String feedback;

    public Apply(long id, long user, String state, String feedback) {
        this.id = id;
        this.user = user;
        this.state = state;
        this.feedback = feedback;
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
}
