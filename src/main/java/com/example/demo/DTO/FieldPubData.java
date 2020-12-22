package com.example.demo.DTO;

public class FieldPubData {
    private String field;
    private Integer pubCount;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Integer getPubCount() {
        return pubCount;
    }

    public void setPubCount(Integer pubCount) {
        this.pubCount = pubCount;
    }

    public FieldPubData(String field, Integer pubCount) {
        this.field = field;
        this.pubCount = pubCount;
    }
}
