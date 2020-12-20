package com.example.demo.controller;

import com.example.demo.bean.CommonResult;
import com.example.demo.bean.Researcher;
import com.example.demo.service.PaperService;
import com.example.demo.service.ResearcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class SearchController {

    @Autowired
    PaperService paperService;

    @Autowired
    ResearcherService researcherService;

    // 按关键字搜索，模糊搜索论文
    @RequestMapping("/search/paper/{keyword}")
    public CommonResult searchPaperByTitle(@PathVariable("keyword") String keyword) throws IOException {
        return new CommonResult(200,"success",paperService.getPaperByKeyWord(keyword));
    }

    // 按学科领域搜索论文
    @RequestMapping("/search/subject/{subject}")
    public CommonResult searchPaperByField(@PathVariable("subject") String subject) throws IOException {
        return new CommonResult(200, "success", paperService.getPaperByField(subject));
    }

    // 按姓名搜索学者
    @RequestMapping("/search/researcher/{keyword}")
    public CommonResult searchResearcherByName(@PathVariable("keyword") String keyword) throws IOException {
        return new CommonResult(200, "success", researcherService.getResearcherByKeyword(keyword));
    }

    // 按时间搜索论文
    @RequestMapping("/search/{time1}&{time2}")
    public CommonResult searchPaperByTime(@PathVariable("time1") int start, @PathVariable("time2") int end) throws IOException {
        return new CommonResult(200, "success", paperService.getPaperByYear(start, end));
    }
}
