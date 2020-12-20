package com.example.demo.controller;

import com.example.demo.bean.CommonResult;
import com.example.demo.bean.Paper;
import com.example.demo.bean.Researcher;
import com.example.demo.service.PaperService;
import com.example.demo.service.ResearcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ResearcherController {
    @Autowired
    ResearcherService researcherService;
    @Autowired
    PaperService paperService;

    @RequestMapping("/researcher/info/{researcherId}")
    public CommonResult getResearcherById(@PathVariable("researcherId") Long researcherId) throws IOException {
        Researcher researcher = researcherService.searchById(researcherId);
        return new CommonResult(200,"success",researcher);
    }

    @RequestMapping("/researcher/field/{researcherId}")
    public CommonResult getField(@PathVariable("researcherId") Long researcherId) throws IOException {
        List<String> field = researcherService.getFieldByResearcher(researcherId);
        if(field == null)
            return new CommonResult(400,"researcher not exist",null);
        else
            return new CommonResult(200,"success",field);
    }

    @RequestMapping("/researcher/citation/{researcherId}")
    public CommonResult getCitation(@PathVariable("researcherId") Long researcherId) throws IOException {
        Researcher researcher = researcherService.searchById(researcherId);
        if(researcher != null)
            return new CommonResult(200,"success",researcher.getCitation());
        else
            return new CommonResult(400,"researcher not exist",null);
    }

    @RequestMapping("/researcher/paper/{researcherId}")
    public CommonResult getPapersByResearcher(@PathVariable("researcherId") Long researcherId) throws IOException{
        List<Paper> papers = paperService.searchByAuthorId(researcherId);
        return new CommonResult(200,"success",papers);
    }

    @RequestMapping("/researcher/relation/{researcherId}")
    public CommonResult getRelationByResearcher(@PathVariable("researcherId") Long researcherId) throws IOException{
        List<Paper> papers = paperService.searchByAuthorId(researcherId);
        List<Researcher> researchers= new ArrayList<>();
        for(Paper paper : papers){
            String[] names = paper.getAuthor();
            for(String name : names){
                Researcher researcher = researcherService.getResearcherByName(name);
                researchers.add(researcher);
            }
        }
        return new CommonResult(200,"success",researchers);
    }
}
