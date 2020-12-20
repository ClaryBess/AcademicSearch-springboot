package com.example.demo.controller;

import com.example.demo.bean.*;
import com.example.demo.service.PaperService;
import com.example.demo.service.ResearcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class EchartsController {
    @Autowired
    ResearcherService researcherService;
    @Autowired
    PaperService paperService;


}
