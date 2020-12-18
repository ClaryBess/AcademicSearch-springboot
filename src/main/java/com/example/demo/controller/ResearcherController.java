package com.example.demo.controller;

import com.example.demo.bean.CommonResult;
import com.example.demo.bean.Paper;
import com.example.demo.bean.Researcher;
import com.example.demo.service.PaperService;
import com.example.demo.service.ResearcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class ResearcherController {
    @Autowired
    ResearcherService researcherService;
    @Autowired
    PaperService paperService;

    @GetMapping("/researcher/info/{id}")
    public CommonResult getResearcherById(@PathVariable("id") Long id){
        Researcher researcher = researcherService.getResearcherById(id);
        return new CommonResult(200,"success",researcher);
    }

    @GetMapping("/researcher/field/{id}")
    public List<String> getFieldByResearcher(@PathVariable("id") Long id){
        return researcherService.getFieldById(id);
    }

    @GetMapping("/researcher/paper/{id}")
    public List<Paper> getPaperByResearcher(@PathVariable("id") Long id){
        try {
            return paperService.searchByAuthorId(id);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping ("/researcher/papersum/{id}")
    public CommonResult getPaperSumByResearcher(@PathVariable("id") Long id){
        int sum = 0;
        try {
            List<Paper> papers = paperService.searchByAuthorId(id);
            for(Paper paper : papers){
                int citation = paper.getCitation();
                sum += citation;
            }
        } catch (IOException e) {
            return new CommonResult(400,"failed to calculat total citation",0);
        }
        return new CommonResult(200,"success",sum);
    }
}
