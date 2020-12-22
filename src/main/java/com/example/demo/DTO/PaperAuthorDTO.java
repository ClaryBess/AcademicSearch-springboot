package com.example.demo.DTO;

import lombok.Data;

@Data
public class PaperAuthorDTO {
    String id;
    String name;
    String field;
    String work;

    @Override
    public String toString() {
        return "PaperAuthorDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", field='" + field + '\'' +
                ", work='" + work + '\'' +
                '}';
    }

}
