package com.example.demo.controller;

import com.example.demo.bean.CommonResult;
import com.example.demo.bean.Researcher;
import com.example.demo.service.PaperService;
import com.example.demo.service.ResearcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SearchController {
    @Autowired
    PaperService paperService;

    @Autowired
    ResearcherService researcherService;

//    @RequestMapping("/search/paper/{keyword}")
//    public CommonResult SearchPaperByTitle(@PathVariable("keyword") String keyword){
//        return new CommonResult(200,"success",paperService.getPaperByKeyWord(keyword));
//    }

    @RequestMapping("/search/researcher/{keyword}")
    public CommonResult SearchResearchByName(@PathVariable("keyword") String keyword){
        return new CommonResult(200,"success",researcherService.getRearcherByKeyword(keyword));
    }

}
