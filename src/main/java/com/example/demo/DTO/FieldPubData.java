package com.example.demo.DTO;

public class FieldPubData {
    private String field;
<<<<<<< HEAD
    private Integer paperCount;
=======
    private Integer pubCount;
>>>>>>> 74e0a9a2a86d60a370658c60bbc6056b77939dd8

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

<<<<<<< HEAD
    public Integer getPaperCount() {
        return paperCount;
    }

    public void setPaperCount(Integer paperCount) {
        this.paperCount = paperCount;
    }

    public FieldPubData(String field, Integer paperCount) {
        this.field = field;
        this.paperCount = paperCount;
=======
    public Integer getPubCount() {
        return pubCount;
    }

    public void setPubCount(Integer pubCount) {
        this.pubCount = pubCount;
    }

    public FieldPubData(String field, Integer pubCount) {
        this.field = field;
        this.pubCount = pubCount;
>>>>>>> 74e0a9a2a86d60a370658c60bbc6056b77939dd8
    }
}
