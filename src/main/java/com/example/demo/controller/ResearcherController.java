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
import java.util.ArrayList;
import java.util.List;

@RestController
public class ResearcherController {
    @Autowired
    ResearcherService researcherService;
    @Autowired
    PaperService paperService;

}
