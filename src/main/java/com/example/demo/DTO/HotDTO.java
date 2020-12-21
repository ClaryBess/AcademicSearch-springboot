package com.example.demo.DTO;


import com.example.demo.bean.Paper;
import lombok.Data;

@Data
public class HotDTO implements Comparable<HotDTO> {
    Long paperId;
    String title;
    String Author;
    Integer date;
    Integer heat;

    public HotDTO(Long paperId, String title, String author, Integer date, Integer heat) {
        this.paperId = paperId;
        this.title = title;
        Author = author;
        this.date = date;
        this.heat = heat;
    }

    @Override
    public int compareTo(HotDTO o) {
        return o.getHeat()-this.heat; //升序
        //return o.id-this.id;  降序
    }

}
