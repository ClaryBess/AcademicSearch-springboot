package com.example.demo.controller;

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

    @RequestMapping("/researcher/all")
    public List<Researcher> getAllResearcher() throws IOException{
        return researcherService.searchALLResearcher();
    }

    @RequestMapping("/researcher/info/{researcherId}")
    public CommonResult getResearcherById(@PathVariable("researcherId") String researcherId) throws IOException {
        Researcher researcher = researcherService.searchById(researcherId);
        return new CommonResult(200,"success",researcher);
    }

    @RequestMapping("/researcher/getByName/{name}")
    public CommonResult getResearcherByName(@PathVariable("name") String name) throws IOException {
        Researcher researcher = researcherService.searchResearcherByName(name);
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
        List<Node> nodes = new ArrayList<Node>();
        HashMap<String,Integer> map = new HashMap<>();
        List<Edge> edges = new ArrayList<Edge>();
        Researcher researcher;

        researcher = researcherService.searchById(researcherId);
        if(researcher == null)
            return new CommonResult(400,"researcher not exist",null);
        String source = researcher.getName();
        map.put(source,1);
        nodes.add(new Node(source, researcher.getPaperCount()));

        if(source != null){
            List<Paper> papers = paperService.searchByAuthorName(source);
            for(Paper paper : papers){
                String[] authors = paper.getAuthor();
                for(String target : authors){
                    if( ( !target.equals(source) ) && map.get(target) == null){
                        map.put(target,1);
                        researcher = researcherService.searchResearcherByName(target);
                        if(researcher != null){
                            nodes.add(new Node(target, researcher.getPaperCount()));
                            edges.add(new Edge(source,target,"合作"));
                        }
                    }
                }
            }
        }
        RelationData relationData = new RelationData("学者关系",nodes,edges);
        return new CommonResult(200,"success,nodes: "+nodes.size()+", edges: "+edges.size()+" ",relationData);
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
        yearPubDataList.sort(Comparator.comparing(YearPubData::getYear));
        return new CommonResult(200,"success",yearPubDataList);
    }

}
