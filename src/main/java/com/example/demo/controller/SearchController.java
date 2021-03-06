package com.example.demo.controller;

import com.example.demo.bean.CommonResult;
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

    @RequestMapping("/hello")
    public String sayHello(@RequestParam(value = "hey", required = false) String str) {
        System.out.println("Hello:"+str);
        return "Hello! Member of group 12!";
    }

    // 按学科领域搜索论文
    @RequestMapping(value = "/search/subject", method = RequestMethod.GET)
    public CommonResult searchPaperByField(@RequestParam("subject") String subject) throws IOException {
        CommonResult commonResult =  new CommonResult(200, "success", paperService.getPaperByField(subject));
        if(commonResult.getData().equals(null)) {
            System.out.println("Null pointer");
            return new CommonResult(402, "null pointer", null);
        }
        else
            return commonResult;
    }

    // 按姓名搜索学者
    @RequestMapping(value = "/search/researcher", method = RequestMethod.GET)
    public CommonResult searchResearcherByName(@RequestParam("key") String keyword) throws IOException {
        return new CommonResult(200, "success", researcherService.searchResearcherByName(keyword));
    }

    // 按领域搜索学者
    @RequestMapping(value = "/search/researcher/field", method = RequestMethod.GET)
    public CommonResult searchResearcherByField(@RequestParam("field") String field) throws IOException {
        return new CommonResult(200, "success", researcherService.searchResearcherByField(field));
    }

    // 按时间搜索论文
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public CommonResult searchPaperByTime(@RequestParam(value="time1", required = true) int start, @RequestParam(value="time2", required = true) int end) throws IOException {
        return new CommonResult(200, "success", paperService.getPaperByYear(start, end));
    }

    // 模糊搜索论文，在标题、摘要和关键字中搜索
    @RequestMapping(value = "/fuzzysearch", method = RequestMethod.GET)
    public CommonResult fuzzySearch(@RequestParam(value = "key")String keyword) throws IOException {
        System.out.println(keyword);
        return new CommonResult(200,"success",paperService.fuzzySearch(keyword));
    }

    // 按关键字搜索论文
    @RequestMapping(value = "/search/keyword", method = RequestMethod.GET)
    public CommonResult searchPaperByTitle(@RequestParam("key") String keyword, @RequestParam("field") String field) throws IOException {
        return new CommonResult(200,"success",paperService.getPaperByKeyWord(keyword, field));
    }

    // 返回所有领域
    @RequestMapping(value = "/field", method = RequestMethod.GET)
    public CommonResult getFields() throws IOException{
        return new CommonResult(200, "success for get fields", paperService.getFields());
    }
}
