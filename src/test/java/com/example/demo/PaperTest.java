package com.example.demo;

import com.example.demo.DTO.HotDTO;
import com.example.demo.bean.Paper;
import com.example.demo.bean.Researcher;
import com.example.demo.service.PaperService;

import com.example.demo.service.ResearcherService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class PaperTest {
    @Autowired
    PaperService paperService;
    @Autowired
    ResearcherService researcherService;


    @Test
    public void SaveTest() throws Exception{
        String[] author={"Author1","Author2"};
        String[] keywords={"keyword1","Test1"};
        Paper paper=new Paper("18L","title",13,2000,author,keywords,"url","Abstract");
        paperService.save(paper);
        System.out.println(paper);
    }

    @Test
    public void DeleteTest() throws Exception{
        String id="18L";
        paperService.delete(id);
    }

    @Test
    public void UpdateTest() throws Exception{
        String id="1L";
        String[] Author={"Author1","Author2"};
        //Paper paper=new Paper(null,"titleupdate","Abstractupdate",null,null,null,"fieldupdate",12,null);
        Paper paper=new Paper((String) null,null,null,null,null,null,null,null);

        paperService.update(id,paper);
    }

    @Test
    public void SearchTest() throws Exception{
        String id="2L";
        Paper paper =paperService.search(id);
        System.out.println(paper);
    }
    @Test
    public void SearchByAuthorTest() throws Exception{
        String userId="1";
        List<Paper> pp= paperService.searchByAuthorId(userId);
        System.out.println(pp);
    }

    @Test
    public void SearchByFiledTest() throws Exception{
        String filed="keyword";
        List<Paper> pp= paperService.getPaperByField(filed);
        System.out.println(pp);
    }


    //数组测试
    @Test
    public void ByAuthorTest() throws Exception{
        List<Paper> pp= paperService.searchByAuthorName("Author2");
        System.out.println(pp);
    }

    //数组测试
//    @Test
//    public void ByKeyWordTest() throws Exception{
//        List<Paper> pp= paperService.getPaperByKeyWord("Class Rank");
//        System.out.println(pp);
//    }


    //被引量降序
    @Test
    public void OrderByCitation() throws Exception{
        List<HotDTO> pp= paperService.OrderByCitation();
        System.out.println(pp);
    }


    @Test
    public void ALLPaper() throws Exception{
        List<Paper> pp= paperService.searchALLPaper();
        System.out.println(pp);
    }

    @Test
    public void searchByAuthoridTest() throws IOException {
        String id="1L";
        Researcher researcher=researcherService.searchByAuthorid(id);
        System.out.println(researcher);
    }








}
