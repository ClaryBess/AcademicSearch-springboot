package com.example.demo.controller;

import com.example.demo.bean.*;
import com.example.demo.service.CollectionService;
import com.example.demo.service.DirectoryService;
import com.example.demo.service.PaperService;
import com.example.demo.service.UserService;
import javafx.util.Pair;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CollectionController {
    @Autowired
    CollectionService collectionService;

    @Autowired
    DirectoryService directoryService;


    @Autowired
    PaperService paperService;

    @Autowired
    UserService userService;

    //展示用户的收藏夹中的文献列表
    @ResponseBody
    @RequestMapping("/showcollection")
    public CommonResult ShowCollectionByDir(@RequestParam("Did") int Did){
        List<Collection> lc = collectionService.ShowCollectionByDir(Did);
        List mapList = new ArrayList<Map>();
        Map<String,String> map = new HashMap<>();
        try{
            for (int i = 0; i < lc.size(); i++) {
            long id = lc.get(i).getPaper();
            Integer Cid = lc.get(i).getId();
            Integer Dir = lc.get(i).getDirectory();
            Integer User = lc.get(i).getUserId();
            Paper paper = paperService.search(id);
            String[] authors = paper.getAuthor();
            String author = authors[0];
            String[] keywords = paper.getAuthor();
            String keyword = authors[0];
            for (int j = 1;j<authors.length;j++)
                author += "，"+authors[j];
            for (int j = 1;j<authors.length;j++)
                keyword += "，"+keywords[j];
            map.put("Title",paper.getTitle());
            map.put("Keyword",keyword);
            map.put("Author",author);
            map.put("Time",paper.getYear().toString());
            map.put("Cid",Cid.toString());
            map.put("Dir",Dir.toString());
            map.put("Paper",paper.getId().toString());
            map.put("User",User.toString());
            mapList.add(map);
            }
        }catch (IOException e) {
            return new CommonResult(500,"查询异常",null);
        }
        return new CommonResult(200,"展示收藏",mapList);
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
    public CommonResult ShowCollectionStatus(@RequestParam("paper") Integer paper,@RequestParam("user") Integer uid){
        if(collectionService.ShowCollectionStatus(paper,uid)==0)
            return new CommonResult(200,"未收藏",0);
        else
            return new CommonResult(200,"已收藏",1);
    }

    @PostMapping("/getCollection")
    public CommonResult getCollection(@RequestParam("id") Long id,@RequestParam("user") Integer uid){
//        String name=(String) SecurityUtils.getSubject().getPrincipal();
//        User user=userService.getUserByName(name);
//        Integer Uid = user.getId();
        List<Directory> directories=directoryService.ShowDirByUser(uid);
        List<Pair<Integer,String>> pairs=new ArrayList<>();
        for(Directory directory:directories){
            Pair<Integer,String> pair= new Pair<>(directory.getId(),directory.getName());
            pairs.add(pair);
        }
        return new CommonResult(200,"get success",pairs);
    }
}
