package com.example.demo.controller;

import com.example.demo.bean.CommonResult;
import com.example.demo.bean.Follow;
import com.example.demo.bean.Researcher;
import com.example.demo.service.FollowService;
import com.example.demo.service.ResearcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FollowController {
    @Autowired
    FollowService followService;
    @Autowired
    ResearcherService researcherService;

    @PostMapping("/follow/insertFollow")
    public CommonResult insertFollow(@RequestBody Follow follow){
        Follow follow1 = followService.insertFollow(follow);
        return new CommonResult(200,null,follow1);
    }

//    @PostMapping("/follow/getList")
//    public List<Researcher> getList(@RequestParam("id")Integer id){
//        List<Follow> followList=followService.getFollowByUser(id);
//        List<Researcher> researcherList = new ArrayList<Researcher>();
//        if(followList == null || followList.size() == 0){
//            return null;
//        }
//        for(Follow follow : followList){
//            Integer researcherId = follow.getResearcher();
//            Researcher researcher = researcherService.getResearcherById(researcherId);
//            researcherList.add(researcher);
//        }
//        return researcherList;
//    }
}
