package com.example.demo.controller;

import com.example.demo.bean.CommonResult;
import com.example.demo.bean.Follow;
import com.example.demo.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FollowController {
    @Autowired
    FollowService followService;

    @PostMapping("/follow/insertFollow")
    public CommonResult insertFollow(@RequestBody Follow follow){
        Follow follow1 = followService.insertFollow(follow);
        return new CommonResult(200,null,follow1);
    }
}
