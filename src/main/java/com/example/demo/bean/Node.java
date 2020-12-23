package com.example.demo.bean;

public class Node {
    private String name;
    private Integer paperCount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPaperCount() {
        return paperCount;
    }

    public void setPaperCount(Integer paperCount) {
        this.paperCount = paperCount;
    }

    public Node(String name, Integer paperCount) {
        this.name = name;
        this.paperCount = paperCount;
    }
}
