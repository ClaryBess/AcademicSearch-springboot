package com.example.demo.DTO;

public class FieldPubData {
    private String field;
    private Integer paperCount;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Integer getPaperCount() {
        return paperCount;
    }

    public void setPaperCount(Integer paperCount) {
        this.paperCount = paperCount;
    }

    public FieldPubData(String field, Integer paperCount) {
        this.field = field;
        this.paperCount = paperCount;
    }
}
