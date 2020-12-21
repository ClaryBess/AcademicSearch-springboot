package com.example.demo.bean;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

@Data
@Document(indexName = "researcher")
public class Researcher{
    private Long id;
    private String name;
    private Integer index;
    private String organization;
    private Integer paperCount;
    private Integer citation;
    private String info;
    private List<String> pubs;
    private List<String> field;
    private String trueName;
    private String email;
    private String pwd;
    private String avatar;




    public Integer getCitation() {
        return citation;
    }

    public void setCitation(Integer citation) {
        this.citation = citation;
    }

    public List<String> getField() {
        return field;
    }

    public void setField(List<String> field) {
        this.field = field;
    }

    public List<String> getPubs() {
        return pubs;
    }

    public void setPubs(List<String> pubs) {
        this.pubs = pubs;
    }



    public Researcher(Long id, String name, Integer index, String organization, Integer paperCount, Integer citation, String info, List<String> pubs, List<String> field) {
        this.id = id;
        this.name = name;
        this.index = index;
        this.organization = organization;
        this.paperCount = paperCount;
        this.citation = citation;
        this.info = info;
        this.pubs = pubs;
        this.field = field;
    }

    public Researcher(Integer citation, List<String> field, List<String> pubs) {
        this.citation = citation;
        this.field = field;
        this.pubs = pubs;
    }
}
