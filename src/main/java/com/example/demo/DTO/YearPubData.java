package com.example.demo.DTO;

public class YearPubData {
    private Integer year;
    private Integer pubCount;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getPubCount() {
        return pubCount;
    }

    public void setPubCount(Integer pubCount) {
        this.pubCount = pubCount;
    }

    public YearPubData(Integer year, Integer pubCount) {
        this.year = year;
        this.pubCount = pubCount;
    }
}
