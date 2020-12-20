package com.example.demo.service;

import com.example.demo.bean.Researcher;
import com.example.demo.mapper.ResearcherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResearcherService {

    @Autowired
    ResearcherMapper researcherMapper;

    public Researcher insertResearcher(Researcher researcher){
        Researcher researcher1 = researcherMapper.getResearcherByName(researcher.getName());
        Researcher researcher2 = researcherMapper.getResearcherByEmail(researcher.getEmail());
        if(researcher1==null && researcher2==null){
            researcherMapper.insertResearcher(researcher);
            return researcherMapper.getResearcherById(researcher.getId());
        }
        else
            return null;
    }

    public Researcher getResearcherById(long id){
        Researcher researcher = researcherMapper.getResearcherById(id);
        return researcher;
    }

    public List<Researcher> getResearcherByKeyword(String keyword){
        return researcherMapper.getResearcherByKeyword(keyword);
    }

    public List<String> getFieldById(long id){
        Researcher researcher = researcherMapper.getResearcherById(id);
        List<String> fields = new ArrayList<String>();
        if(researcher.getField1() != null){
            fields.add(researcher.getField1());
        }
        if(researcher.getField2() != null){
            fields.add(researcher.getField2());
        }
        if(researcher.getField3() != null){
            fields.add(researcher.getField3());
        }
        return fields;
    }

}
