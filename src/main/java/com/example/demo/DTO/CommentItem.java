package com.example.demo.DTO;

import java.sql.Timestamp;

public class CommentItem {
    private String profileUrl;
    private String userName;
    private String content;
    private Timestamp dateTime;

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    public CommentItem(String profileUrl, String userName, String content, Timestamp dateTime) {
        this.profileUrl = profileUrl;
        this.userName = userName;
        this.content = content;
        this.dateTime = dateTime;
    }
}
