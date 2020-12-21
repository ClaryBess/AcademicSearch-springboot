package com.example.demo.controller;

import com.example.demo.bean.CommonResult;
import com.example.demo.bean.Follow;
import com.example.demo.bean.Researcher;
import com.example.demo.bean.User;
import com.example.demo.service.FollowService;
import com.example.demo.service.ResearcherService;
import org.elasticsearch.common.recycler.Recycler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
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

    @PostMapping("/follow/getStatus")
    public CommonResult getStatus(@RequestParam("user") Integer user,@RequestParam("researcher") Long researcher){
        if(followService.getFollowByUserAndResearcher(user,researcher)!=null)
            return new CommonResult(200,"already followed",1);
        else
            return new CommonResult(200,"haven't followed",0);
    }

    @PostMapping("/follow/cancel")
    public CommonResult cancelFollow(@RequestParam("user")Integer user,@RequestParam("researcher") Long researcher){
            if(followService.getFollowByUserAndResearcher(user,researcher)!=null){
                followService.deleteByUserAndResearcher(user,researcher);
                return new CommonResult(200,"cancel success",null);
            }
            else
                return new CommonResult(500,"haven't followed",null);
    }

    @PostMapping("/follow/getList")
    public List<Researcher> getList(@RequestParam("id")Integer id) throws IOException {
        List<Follow> followList=followService.getFollowByUser(id);
        List<Researcher> researcherList = new ArrayList<>();
        if(followList == null || followList.size() == 0){
            return null;
        }
        for(Follow follow : followList){
            Long researcherId = follow.getResearcher();
            Researcher researcher = researcherService.searchById(researcherId);
            researcherList.add(researcher);
        }
        return researcherList;
    }
}
