package com.example.demo.bean;

import java.util.Date;

public class Message {
    private long id;
    private long from;
    private long to;
    private String text;
    private boolean read;
    private Date time;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getFrom() {
        return from;
    }

    public void setFrom(long from) {
        this.from = from;
    }

    public long getTo() {
        return to;
    }

    public void setTo(long to) {
        this.to = to;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Message(long id, long from, long to, String text, boolean read, Date time) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.text = text;
        this.read = read;
        this.time = time;
    }
}
