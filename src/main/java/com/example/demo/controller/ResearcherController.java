package com.example.demo.controller;

import com.example.demo.DTO.FieldPubData;
import com.example.demo.DTO.RelationData;
import com.example.demo.DTO.YearPubData;
import com.example.demo.bean.*;
import com.example.demo.service.PaperService;
import com.example.demo.service.ResearcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@RestController
public class ResearcherController {
    @Autowired
    ResearcherService researcherService;
    @Autowired
    PaperService paperService;

    @RequestMapping("/researcher/info/{researcherId}")
    public CommonResult getResearcherById(@PathVariable("researcherId") String researcherId) throws IOException {
        Researcher researcher = researcherService.searchById(researcherId);
        return new CommonResult(200,"success",researcher);
    }

    @RequestMapping("/researcher/field/{researcherId}")
    public CommonResult getField(@PathVariable("researcherId") String researcherId) throws IOException {
        List<String> field = researcherService.getFieldByResearcher(researcherId);
        if(field == null)
            return new CommonResult(400,"researcher not exist",null);
        else
            return new CommonResult(200,"success",field);
    }

    @RequestMapping("/researcher/citation/{researcherId}")
    public CommonResult getCitation(@PathVariable("researcherId") String researcherId) throws IOException {
        Researcher researcher = researcherService.searchById(researcherId);
        if(researcher != null)
            return new CommonResult(200,"success",researcher.getCitation());
        else
            return new CommonResult(400,"researcher not exist",null);
    }

    @RequestMapping("/researcher/paper")
    public CommonResult getPapersByResearcherName(@RequestParam("AuthorName") String AuthorName) throws IOException{
        List<Paper> papers = paperService.searchByAuthorName(AuthorName);
        return new CommonResult(200,"success",papers);
    }

    @RequestMapping("/researcher/relation/{researcherId}")
    public CommonResult getRelationByResearcher(@PathVariable("researcherId") String researcherId) throws IOException{
        List<Researcher> researchers = new ArrayList<>();
        List<Edge> edges = new ArrayList<Edge>();
        Researcher researcher = researcherService.searchById(researcherId);
        researchers.add(researcher);
        String source = researcher.getName();
        if(source != null){
            List<Paper> papers = paperService.searchByAuthorName(source);
            for(Paper paper : papers){
                String[] authors = paper.getAuthor();
                for(String target : authors){
                    if(!target.equals(source)){
                        researchers.addAll(researcherService.searchResearcherByName(target));
                        edges.add(new Edge(source,target,"合作"));
                    }
                }
            }
        }
        RelationData relationData = new RelationData("学者关系",researchers,edges);
        return new CommonResult(200,"success",relationData);
    }

    @RequestMapping("/researcher/allRelation")
    public CommonResult getAllRelation() throws IOException {
        List<Researcher> researchers = researcherService.searchALLResearcher();
        List<Edge> edges = new ArrayList<Edge>();
        for(Researcher researcher : researchers){
            String source = researcher.getName();
            if(source != null){
                List<Paper> papers = paperService.searchByAuthorName(source);
                for(Paper paper : papers){
                    String[] authors = paper.getAuthor();
                    for(String target : authors){
                        if(!target.equals(source))
                            edges.add(new Edge(source,target,"合作"));
                    }
                }
            }
        }
        RelationData relationData = new RelationData("学者关系",researchers,edges);
        return new CommonResult(200,"success",relationData);
    }

    @RequestMapping(value = "/researcher/yearPub/{name}")
    public CommonResult getYearPub(@PathVariable("name")String name) throws IOException {
        //找到这个学者的所有文章，按年份统计
        List<Paper> papers = paperService.searchByAuthorName(name);
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        List<YearPubData> yearPubDataList = new ArrayList<>();
        for(Paper paper : papers){
            Integer year = paper.getYear();
            if(map.isEmpty() || map.get(year) == null){
                map.put(year,1);
            }
            else{
                Integer count = map.get(year);
                map.replace(year,count+1);
            }
        }
        for(Integer keys : map.keySet()){
            YearPubData data = new YearPubData(keys,map.get(keys));
            yearPubDataList.add(data);
        }
        return new CommonResult(200,"success",yearPubDataList);
    }

    @RequestMapping(value = "/researcher/fieldPub/{name}")
    public CommonResult getFieldPub(@PathVariable("name")String name) throws IOException {
        //找到这个学者的所有文章，按年份统计
        List<Paper> papers = paperService.searchByAuthorName(name);
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        List<FieldPubData> fieldPubDataList = new ArrayList<>();
        for(Paper paper : papers){
            List<String> keyWords=java.util.Arrays.asList(paper.getKeywords());
            for (String keyword:keyWords){
                if(map.isEmpty() || map.get(keyword) == null){
                    map.put(keyword,1);
                }
                else{
                    Integer count = map.get(keyword);
                    map.replace(keyword,count+1);
                }
            }
        }
        for(String keys : map.keySet()){
            FieldPubData data =new FieldPubData(keys,map.get(keys));
            fieldPubDataList.add(data);
        }
        return new CommonResult(200,"success",fieldPubDataList);
    }
}
