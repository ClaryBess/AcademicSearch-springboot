package com.example.demo.controller;


import com.example.demo.DTO.CommentDTO;
import com.example.demo.bean.CommentItem;
import com.example.demo.bean.Comments;
import com.example.demo.bean.CommonResult;
import com.example.demo.bean.User;
import com.example.demo.service.CommentsService;
import com.example.demo.service.PaperService;
import com.example.demo.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private CommentsService commentService;

    @Autowired
    private PaperService paperService;


    @Autowired
    private UserService userService;

    /**
     * 进行评论
     * @param paperId
     * @param content
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/comment")
    public CommonResult Comment(@RequestParam Long paperId,
                          @RequestParam String content) {
        //获得当前登录用户
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        //User user=userService.getUserById(5);
        if(user==null) return new CommonResult(400,"please login first",null);
        Comments comments=commentService.insertComments(paperId,content, user);
        return new CommonResult(200,null,comments);
    }

    /**
     * 删除评论
     */
    @ResponseBody
    @RequestMapping(value = "/comment/delete/{id}", method = RequestMethod.DELETE)
    public CommonResult CommentDelete(@PathVariable("id") Integer commentId) {

        Comments comments=commentService.selectById(commentId);
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if(user.getId().equals(comments.getCommentator())) return new CommonResult(400,"only the author can delete",null);
        int result=commentService.deleteById(commentId);
        if(result==0)  return new CommonResult(400,"delete fault",null);
        return new CommonResult(200,null,comments);
    }




}
