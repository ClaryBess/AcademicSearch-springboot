package com.example.demo.bean;

public class Edge {
    private Integer source;
    private Integer target;
    private String value;

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Integer getTarget() {
        return target;
    }

    public void setTarget(Integer target) {
        this.target = target;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Edge(Integer source, Integer target, String value) {
        this.source = source;
        this.target = target;
        this.value = value;
    }
}
