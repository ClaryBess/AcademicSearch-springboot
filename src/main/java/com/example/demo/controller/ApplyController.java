package com.example.demo.controller;

import com.example.demo.bean.CommonResult;
import com.example.demo.bean.User;
import com.example.demo.service.ApplyService;
import com.example.demo.service.MessageService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplyController {
    @Autowired
    ApplyService applyService;

    @Autowired
    MessageService messageService;

    //发送申请
    @ResponseBody
    @RequestMapping("/apply/send")
    public CommonResult SendApply(@RequestParam("feedback") String feedback,@RequestParam("user")Integer Uid){
        return new CommonResult(200,"发送成功",applyService.SendApply(Uid,feedback));
    }

    //接受申请,同时发送通知到该用户
    @ResponseBody
    @RequestMapping("/apply/accept")
    public CommonResult AcceptApply(@RequestParam("Aid")Integer id,@RequestParam("user")Integer user){
        applyService.AcceptApply(id);
        return new CommonResult(200,"已同意",messageService.SendMessage(100000,user,"您的申请"+id.toString()+"已通过审核。"));
    }

    //拒绝申请,同时发送通知到该用户
    @ResponseBody
    @RequestMapping("/apply/reject")
    public CommonResult RejectApply(@RequestParam("Aid")Integer id,@RequestParam("user")Integer user){
        applyService.RejectApply(id);
        return new CommonResult(200,"已拒绝",messageService.SendMessage(100000,user,"您的申请"+id.toString()+"未通过审核。"));
    }

    //展示用户发送的所有申请
    @ResponseBody
    @RequestMapping("/apply/user")
    public CommonResult ShowAllApplyByUser(@RequestParam("user")Integer Uid){
        return new CommonResult(200,"我的申请列表",applyService.ShowAllApplyByUser(Uid));
    }

    //展示已拒绝的申请
    @ResponseBody
    @RequestMapping("/apply/reject/show")
    public CommonResult ShowReject(){
        return new CommonResult(200,"已拒绝的申请",applyService.ShowReject());
    }

    //展示未处理的申请
    @ResponseBody
    @RequestMapping("/apply/waiting/show")
    public CommonResult ShowWaiting(){
        return new CommonResult(200,"已同意的申请",applyService.ShowAccept());
    }

    //展示同意的申请
    @ResponseBody
    @RequestMapping("/apply/accept/show")
    public CommonResult ShowAccept(){
        return new CommonResult(200,"未处理的申请",applyService.ShowWaiting());
    }
}
