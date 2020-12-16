package com.example.demo.controller;

import com.example.demo.bean.CommonResult;
import com.example.demo.bean.User;
import com.example.demo.service.MessageService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    @Autowired
    MessageService messageService;

    //读消息
    @ResponseBody
    @RequestMapping("/message/read")
    public CommonResult ReadMessage(@RequestParam("Mid") Integer Mid){
        return messageService.ReadMessage(Mid);
    }

    //系统消息列表
    @ResponseBody
    @RequestMapping("/message/sys")
    public CommonResult SysMessage(){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Integer Uid = user.getId();
        return messageService.SysMessage(Uid);
    }

    //学者私信列表
    @ResponseBody
    @RequestMapping("/message/fromre")
    public CommonResult ReMessage(){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Integer Uid = user.getId();
        return messageService.ResercherMessage(Uid);
    }

    //我的私信列表
    @ResponseBody
    @RequestMapping("/message/mine")
    public CommonResult MyMessage(){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Integer Uid = user.getId();
        return messageService.MyMessage(Uid);
    }

    //发送私信
    @ResponseBody
    @RequestMapping("/message/send")
    public CommonResult SendMessage(@RequestParam("to")Integer to, @RequestParam("text")String text){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Integer Uid = user.getId();
        return messageService.SendMessage(Uid,to,text);
    }

    //回复私信
    @ResponseBody
    @RequestMapping("/message/response")
    public CommonResult ResponseMessage(@RequestParam("from")Integer from, @RequestParam("text")String text){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Integer Uid = user.getId();
        return messageService.SendMessage(Uid,from,text);
    }

    //删除接收的私信
    @ResponseBody
    @RequestMapping("/message/delete1")
    public CommonResult DeleteMessage1(@RequestParam("Mid")Integer Mid){
        return messageService.DeleteOtherMessage(Mid);
    }

    //删除我的私信
    @ResponseBody
    @RequestMapping("/message/delete2")
    public CommonResult DeleteMessage1(@RequestParam("Mid")Integer Mid){
        return messageService.DeleteMyMessage(Mid);
    }
}
