package com.example.demo.controller;

import com.example.demo.bean.CommonResult;
import com.example.demo.bean.User;
import com.example.demo.service.CollectionService;
import com.example.demo.service.DirectoryService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CollectionController {
    @Autowired
    CollectionService collectionService;

    @Autowired
    DirectoryService directoryService;

    //展示用户的收藏夹中的文献列表
    @ResponseBody
    @RequestMapping("/showcollection")
    public CommonResult ShowCollectionByDir(@RequestParam("Did") int Did){
        return new CommonResult(200,"展示收藏",collectionService.ShowCollectionByDir(Did));
    }

    //添加收藏
    @ResponseBody
    @RequestMapping("/collect")
    public CommonResult Collect(@RequestParam("Did") Integer Did,@RequestParam("paper") long paper){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Integer Uid = user.getId();
        return new CommonResult(200,"收藏成功",collectionService.insertCollection(Did,paper,Uid));
    }

    //在文献页面取消收藏
    @ResponseBody
    @RequestMapping("/collection/cancelinpaper")
    public CommonResult DeleteCollectionInPaper(@RequestParam("paper") long paper){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Integer Uid = user.getId();
        return new CommonResult(200,"取消收藏成功",collectionService.DeleteCollectionInPaper(Uid,paper));
    }

    //在收藏夹里取消收藏
    @ResponseBody
    @RequestMapping("/collection/cancelindir")
    public CommonResult DeleteCollectionInDir(@RequestParam("Cid") Integer Cid){
        return new CommonResult(200,"取消收藏成功",collectionService.DeleteCollectionInDir(Cid));
    }

    //文献收藏状态
    @ResponseBody
    @RequestMapping("/collection/status")
    public CommonResult ShowCollectionStatus(@RequestParam("paper") Integer paper){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Integer Uid = user.getId();
        if(collectionService.ShowCollectionStatus(paper,Uid)==0)
            return new CommonResult(200,"未收藏",0);
        else
            return new CommonResult(200,"已收藏",1);
    }
}
