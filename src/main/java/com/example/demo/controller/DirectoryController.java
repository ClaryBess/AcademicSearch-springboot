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
public class DirectoryController {
    @Autowired
    CollectionService collectionService;

    @Autowired
    DirectoryService directoryService;

    //创建收藏夹
    @ResponseBody
    @RequestMapping("/makedir")
    public CommonResult DeleteCollectionInDir(@RequestParam("Dname") String Dname){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Integer Uid = user.getId();
        return directoryService.CreateDir(Dname,Uid);
    }

    //展示所有收藏夹
    @ResponseBody
    @RequestMapping("/showdir")
    public CommonResult ShowAllDir(){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Integer Uid = user.getId();
        return directoryService.ShowDirByUser(Uid);
    }

    //删除收藏夹，连带删除收藏夹内所有内容
    @ResponseBody
    @RequestMapping("/dir/delete")
    public CommonResult DeleteDir(@RequestParam("Did") Integer Did){
        directoryService.DeleteDirByDid(Did);
        return collectionService.DeleteCollectionByDir(Did);
    }
}
