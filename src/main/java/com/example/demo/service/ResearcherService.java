package com.example.demo.service;

import com.example.demo.mapper.ResearcherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResearcherService {

    @Autowired
    ResearcherMapper researcherMapper;

}
