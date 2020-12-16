package com.example.demo;

import com.example.demo.bean.Paper;
import com.example.demo.service.PaperService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.sql.Timestamp;
import java.util.Date;

@SpringBootTest
public class PaperTest {
    @Autowired
    PaperService paperService;

    @Test
    public void SaveTest() throws Exception{
        Timestamp t = new Timestamp(new Date().getTime());

        Paper paper=new Paper(1L,"title",t,"field","url","keyWord","Abstract");
        paperService.save(paper);
        System.out.println(paper);
    }

    @Test
    public void DeleteTest() throws Exception{
        long id=0L;
        paperService.delete(id);
    }

    @Test
    public void UpdateTest() throws Exception{
        long id=1L;
        Paper paper=new Paper(null,"titleupdate",null,"fieldupdate",null,null,"Abstractupdate");
        paperService.update(id,paper);
    }

    @Test
    public void SearchTest() throws Exception{
        long id=1L;
        Paper paper =paperService.search(id);
        System.out.println(paper);
    }




}
