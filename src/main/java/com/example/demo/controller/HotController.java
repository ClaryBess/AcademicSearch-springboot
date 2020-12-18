package com.example.demo.controller;

import com.example.demo.bean.CommonResult;
import com.example.demo.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HotController {
    @Autowired
    PaperService paperService;

//    @RequestMapping("/hot/paper")
//    public CommonResult getHotPaper(){
//
//    }
//
//    @RequestMapping("/hot/field")
//    public CommonResult getHotField(){
//
//    }

}
