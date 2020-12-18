package com.example.demo.controller;

import com.example.demo.bean.Researcher;
import com.example.demo.service.ResearcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ResearcherController {
    @Autowired
    ResearcherService researcherService;

    @GetMapping("/researcher/info/{id}")
    public Researcher getResearcherById(@PathVariable("id") Long id){
        return researcherService.getResearcherById(id);
    }

    @GetMapping("/researcher/field/{id}")
    public List<String> getFieldById(@PathVariable("id") Long id){
        return researcherService.getFieldById(id);
    }
}
