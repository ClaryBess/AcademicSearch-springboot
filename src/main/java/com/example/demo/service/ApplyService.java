package com.example.demo.service;

import com.example.demo.bean.Apply;
import com.example.demo.mapper.ApplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplyService {
    @Autowired
    ApplyMapper applyMapper;

    //发送申请
    public int SendApply(Integer user,String feedback,long researcher){
        return applyMapper.SendApply(user,feedback,researcher);
    }

    //接受申请
    public int AcceptApply(Long id){
        return applyMapper.Accept(id);
    }

    //拒绝申请
    public int RejectApply(Long id){
        return applyMapper.Reject(id);
    }

    //展示用户发送的所有申请
    public List<Apply> ShowAllApplyByUser(long user){
        return applyMapper.ShowApplyByUser(user);
    }

    //展示已拒绝的申请
    public List<Apply> ShowReject(){
        return applyMapper.ShowReject();
    }

    //展示未处理的申请
    public List<Apply> ShowWaiting(){
        return applyMapper.ShowWaiting();
    }

    //展示同意的申请
    public List<Apply> ShowAccept(){
        return applyMapper.ShowPass();
    }

    //根据id获取申请
    public  Apply getApplyById(Long id){return applyMapper.getApplyById(id);}
}
