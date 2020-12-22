package com.example.demo.service;


import com.example.demo.bean.Message;
import com.example.demo.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MessageService {

    @Autowired
    MessageMapper messageMapper;

    //读私信
    public Message ReadMessage(Integer id){
        messageMapper.ReadMessage(id);
        return messageMapper.ShowMessageDetail(id);
    }

    //展示系统消息
    public List<Map<String,Object>> SysMessage(Integer id){
        return messageMapper.ShowAllSysMessage(id);
    }

    //展示学者私信
    public List<Map<String,Object>> ResearcherMessage(Integer id){
        return messageMapper.ShowAllMessageFromRe(id);
    }

    //展示我的私信
    public List<Map<String,Object>> MyMessage(Integer id){
        return messageMapper.ShowMyMessage(id);
    }

    //删除系统消息或学者私信
    public int DeleteOtherMessage(Integer id){
        int i = messageMapper.DeleteMessageByReceiver1(id);
        i+= messageMapper.DeleteMessageByReceiver2(id);
        return i;
    }

    //删除我的私信
    public int DeleteMyMessage(Integer id){
        int i = messageMapper.DeleteMessageBySender1(id);
        i+= messageMapper.DeleteMessageBySender2(id);
        return i;
    }

    //发送私信
    public int SendMessage(Integer from,Integer to,String text){
        return messageMapper.insertMessage(from,to,text);
    }
}
