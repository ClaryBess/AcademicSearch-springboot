package com.example.demo.bean;

import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

@Document(indexName = "author")
public class Researcher extends User{
    private Integer citation;
    private List<String> pubs;

    public Integer getCitation() {
        return citation;
    }

    public void setCitation(Integer citation) {
        this.citation = citation;
    }

    public List<String> getPubs() {
        return pubs;
    }

    public void setPubs(List<String> pubs) {
        this.pubs = pubs;
    }

    public Researcher(Integer id, String name, String trueName, String organization, String email, Integer paperCount, Integer index, String pwd, String info, Integer role, String avatar, Integer citation, List<String> pubs) {
        super(id, name, trueName, organization, email, paperCount, index, pwd, info, role, avatar);
        this.citation = citation;
        this.pubs = pubs;
    }

}
