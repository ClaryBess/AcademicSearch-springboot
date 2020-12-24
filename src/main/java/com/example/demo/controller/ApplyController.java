package com.example.demo.controller;

import com.example.demo.bean.Apply;
import com.example.demo.bean.ApplyItem;
import com.example.demo.bean.CommonResult;
import com.example.demo.bean.User;
import com.example.demo.service.ApplyService;
import com.example.demo.service.MessageService;
import com.example.demo.service.ResearcherService;
import com.example.demo.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ApplyController {
    @Autowired
    ApplyService applyService;

    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;

    @Autowired
    ResearcherService researcherService;

    //发送申请
    @ResponseBody
    @RequestMapping("/apply/send")
    public CommonResult SendApply(@RequestParam("feedback") String feedback,@RequestParam("user")Integer Uid,@RequestParam("researcher")String researcher){
        return new CommonResult(200,"发送成功",applyService.SendApply(Uid,feedback,researcher));
    }

    //接受申请,同时发送通知到该用户
    @ResponseBody
    @RequestMapping("/apply/accept")
    public CommonResult AcceptApply(@RequestParam("Aid")Long id,@RequestParam("user")Integer user) throws IOException {
        applyService.AcceptApply(id);
        userService.setResearcher(userService.getUserById(user));
        User user1=userService.getUserById(user);
        user1.setResearcherId(applyService.getApplyById(id).getResearcher());
        userService.updateResearcherId(user1);
        user1.setTrueName(researcherService.searchById(applyService.getApplyById(id).getResearcher()).getName());
        userService.updateTrueName(user1);
        return new CommonResult(200,"已同意",messageService.SendMessage(100001,user,"您的申请"+id.toString()+"已通过审核。"));
    }

    //拒绝申请,同时发送通知到该用户
    @ResponseBody
    @RequestMapping("/apply/reject")
    public CommonResult RejectApply(@RequestParam("Aid")Long id,@RequestParam("user")Integer user){
        applyService.RejectApply(id);
        return new CommonResult(200,"已拒绝",messageService.SendMessage(100001,user,"您的申请"+id.toString()+"未通过审核。"));
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
        return new CommonResult(200,"未处理的申请",applyService.ShowWaiting());
    }

    //展示同意的申请
    @ResponseBody
    @RequestMapping("/apply/accept/show")
    public CommonResult ShowAccept(){
        return new CommonResult(200,"已通过的申请",applyService.ShowAccept());
    }

    @ResponseBody
    @RequestMapping("/apply/show")
    public CommonResult ShowAll() throws IOException {
        List<Apply> applyList = applyService.ShowAll();
        for (Apply apply:applyList){
            System.out.println(apply.getUser());
        }
        List<ApplyItem> applyItemList = new ArrayList<>();
        for (Apply apply : applyList) {
            applyItemList.add(new ApplyItem(apply.getId(), apply.getUser(), apply.getState(), apply.getFeedback(), apply.getResearcher(), researcherService.searchById(apply.getResearcher()).getName()));
        }
        return new CommonResult(200,"所有申请",applyItemList);
    }
}
