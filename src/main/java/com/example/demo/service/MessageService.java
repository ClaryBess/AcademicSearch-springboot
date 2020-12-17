package com.example.demo.service;


import com.example.demo.bean.CommonResult;
import com.example.demo.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    MessageMapper messageMapper;

    //读私信
    public CommonResult ReadMessage(Integer id){
        messageMapper.ReadMessage(id);
        return new CommonResult(200,"读取成功",messageMapper.ShowMessageDetail(id));
    }

    //展示系统消息
    public CommonResult SysMessage(Integer id){
        return new CommonResult(200,"系统消息",messageMapper.ShowAllSysMessage(id));
    }

    //展示学者私信
    public CommonResult ResercherMessage(Integer id){
        return new CommonResult(200,"学者私信",messageMapper.ShowAllMessageFromRe(id));
    }

    //展示我的私信
    public CommonResult MyMessage(Integer id){
        return new CommonResult(200,"学者私信",messageMapper.ShowMyMessage(id));
    }

    //删除系统消息或学者私信
    public CommonResult DeleteOtherMessage(Integer id){
        int i = messageMapper.DeleteMessageByReceiver1(id);
        i+= messageMapper.DeleteMessageByReceiver2(id);
        return new CommonResult(200,"删除成功",i);
    }

    //删除我的私信
    public CommonResult DeleteMyMessage(Integer id){
        int i = messageMapper.DeleteMessageBySender1(id);
        i+= messageMapper.DeleteMessageBySender2(id);
        return new CommonResult(200,"删除成功",i);
    }

    //发送私信
    public CommonResult SendMessage(Integer from,Integer to,String text){
        return new CommonResult(200,"发送成功",messageMapper.insertMessage(from,to,text));
    }
}
