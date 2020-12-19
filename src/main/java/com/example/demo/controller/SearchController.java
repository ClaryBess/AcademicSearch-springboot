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


    // 按关键字搜索，模糊搜索
    @RequestMapping("/search/paper/{keyword}")
    public CommonResult searchPaperByTitle(@PathVariable("keyword") String keyword) throws IOException {
        return new CommonResult(200,"success",paperService.getPaperByKeyWord(keyword));
    }

    // 按学科领域搜索
    @RequestMapping("/search/subject/{subject}")
    public CommonResult searchPaperByField(@PathVariable("subject") String subject) throws IOException {
        return new CommonResult(200, "success", paperService.getPaperByFiled(subject));
    }

    // 按姓名搜索学者
    @RequestMapping("/search/researcher/{keyword}")
    public CommonResult searchResearcherByName(@PathVariable("keyword") String keyword) throws IOException {
        return new CommonResult(200, "success", paperService.searchByAuthorId(keyword));
    }
}
