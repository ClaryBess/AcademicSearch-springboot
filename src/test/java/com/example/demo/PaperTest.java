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
import java.util.List;

@SpringBootTest
public class PaperTest {
    @Autowired
    PaperService paperService;

    @Test
    public void SaveTest() throws Exception{

        Timestamp t = new Timestamp(new Date().getTime());
        String[] Author={"Author1","Author2"};
//        String[] Keyword={"keyword"};
        Paper paper=new Paper(3L,"title","Abstractt",t,Author,"url","field",13,"Keyword");
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
        Paper paper=new Paper(null,"titleupdate","Abstractupdate",null,null,null,"fieldupdate",15,null);
        paperService.update(id,paper);
    }

    @Test
    public void SearchTest() throws Exception{
        long id=1L;
        Paper paper =paperService.search(id);
        System.out.println(paper);
    }
    @Test
    public void SearchByAuthorTest() throws Exception{
        int userId=1;
        List<Paper> pp= paperService.searchByAuthorId(userId);
        System.out.println(pp);
    }


    //数组测试
    @Test
    public void ByAuthorTest() throws Exception{
        List<Paper> pp= paperService.searchByAuthorName("Author2");
        System.out.println(pp);
    }







}
