package com.example.demo.controller;

import com.example.demo.bean.CommonResult;
import com.example.demo.bean.User;
import com.example.demo.service.MessageService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class MessageController {
    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;

    //读消息
    @ResponseBody
    @RequestMapping("/message/read")
    public CommonResult ReadMessage(@RequestParam("Mid") Integer Mid){
        return new CommonResult(200,"读取成功",messageService.ReadMessage(Mid));
    }

    //系统消息列表
    @ResponseBody
    @RequestMapping("/message/sys")
    public CommonResult SysMessage(@RequestParam("user")Integer Uid){
        return new CommonResult(200,"系统消息",messageService.SysMessage(Uid));
    }

    //学者私信列表
    @ResponseBody
    @RequestMapping("/message/fromre")
    public CommonResult ReMessage(@RequestParam("user")Integer Uid){
        return new CommonResult(200,"接收的私信",messageService.ResearcherMessage(Uid));
    }

    //我的私信列表
    @ResponseBody
    @RequestMapping("/message/mine")
    public CommonResult MyMessage(@RequestParam("user")Integer Uid){
        return new CommonResult(200,"发送的私信",messageService.MyMessage(Uid));
    }

    //发送私信
    @ResponseBody
    @RequestMapping("/message/send")
    public CommonResult SendMessage(@RequestBody Map<String,Object> items){
        Integer Uid = Integer.valueOf((String)items.get("user"));
        Integer to = Integer.valueOf((String)items.get("to"));
        String text = (String) items.get("text");
        return new CommonResult(200,"发送成功",messageService.SendMessage(Uid,to,text));
    }

    //通过真名发送消息
    @ResponseBody
    @RequestMapping("/message/send/byname")
    public CommonResult SendMessageByName(@RequestBody Map<String,Object> items){
        String name = (String)items.get("user");
        User u = userService.getUserByTrueName(name);
        Integer Uid = u.getId();
        Integer to = Integer.valueOf((String)items.get("to"));
        String text = (String) items.get("text");
        return new CommonResult(200,"发送成功",messageService.SendMessage(Uid,to,text));
    }

    //回复私信
    @ResponseBody
    @RequestMapping("/message/response")
    public CommonResult ResponseMessage(@RequestBody Map<String,Object> items ){
        Integer Uid = (Integer) items.get("user");
        Integer from = (Integer) items.get("from");
        String text = (String) items.get("text");
        return new CommonResult(200,"发送成功",messageService.SendMessage(Uid,from,text));
}

    //删除接收的私信
    @ResponseBody
    @RequestMapping("/message/delete1")
    public CommonResult DeleteMessage1(@RequestParam("Mid")Integer Mid){
        return new CommonResult(200,"删除成功",messageService.DeleteOtherMessage(Mid));
    }

    //删除我的私信
    @ResponseBody
    @RequestMapping("/message/delete2")
    public CommonResult DeleteMessage2(@RequestParam("Mid")Integer Mid){
        return new CommonResult(200,"删除成功",messageService.DeleteMyMessage(Mid));
    }
}
