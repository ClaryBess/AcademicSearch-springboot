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
        return collectionService.ShowCollectionByDir(Did);
    }

    //添加收藏
    @ResponseBody
    @RequestMapping("/collect")
    public CommonResult Collect(@RequestParam("Did") Integer Did,@RequestParam("paper") long paper){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Integer Uid = user.getId();
        return collectionService.insertCollection(Did,paper,Uid);
    }

    //在文献页面取消收藏
    @ResponseBody
    @RequestMapping("/collection/cancelinpaper")
    public CommonResult DeleteCollectionInPaper(@RequestParam("paper") long paper){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Integer Uid = user.getId();
        return collectionService.DeleteCollectionInPaper(Uid,paper);
    }

    //在收藏夹里取消收藏
    @ResponseBody
    @RequestMapping("/collection/cancelindir")
    public CommonResult DeleteCollectionInDir(@RequestParam("Cid") Integer Cid){
        return collectionService.DeleteCollectionInDir(Cid);
    }

    //文献收藏状态
    @ResponseBody
    @RequestMapping("/collection/status")
    public int ShowCollectionStatus(@RequestParam("paper") Integer paper){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Integer Uid = user.getId();
        return collectionService.ShowCollectionStatus(paper,Uid);
    }
}
