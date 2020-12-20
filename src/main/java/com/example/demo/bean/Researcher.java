package com.example.demo.bean;

import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

@Document(indexName = "author")
public class Researcher extends User{
    private Integer citation;
    private List<String> field;
    private List<String> pubs;

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

    public Researcher(Integer id, String name, String trueName, String organization, String email, Integer paperCount, Integer index, String pwd, String info, Integer role, String avatar, Integer researcherId, Integer citation, List<String> field, List<String> pubs) {
        super(id, name, trueName, organization, email, paperCount, index, pwd, info, role, avatar, researcherId);
        this.citation = citation;
        this.field = field;
        this.pubs = pubs;
    }
}
