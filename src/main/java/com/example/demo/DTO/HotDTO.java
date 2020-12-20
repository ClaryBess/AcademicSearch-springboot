package com.example.demo.DTO;


import com.example.demo.bean.Paper;
import lombok.Data;

@Data
public class HotDTO implements Comparable<HotDTO> {
    String title;
    String[] Author;
    Integer data;
    Integer heat;

    public HotDTO(String title, String[] author, Integer data, Integer heat) {
        this.title = title;
        Author = author;
        this.data = data;
        this.heat = heat;
    }
    @Override
    public int compareTo(HotDTO o) {
        return o.getHeat()-this.heat; //升序
        //return o.id-this.id;  降序
    }
}
