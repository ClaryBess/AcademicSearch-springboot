package com.example.demo.controller;


import com.example.demo.bean.Comments;
import com.example.demo.bean.CommonResult;
import com.example.demo.bean.User;
import com.example.demo.service.CommentsService;
import com.example.demo.service.PaperService;
import com.example.demo.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/comment")
    public CommonResult Comment(@RequestBody Comments comment) {
        User user=userService.getUserById(comment.getCommentator());
        if(user==null)
            return new CommonResult(400,"please login first",null);
        Comments comments=commentService.insertComments(comment.getPaperId(),comment.getContent(), user);
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
